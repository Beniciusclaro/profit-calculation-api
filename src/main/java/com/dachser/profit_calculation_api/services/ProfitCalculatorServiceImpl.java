package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.entities.domains.Customer;
import com.dachser.profit_calculation_api.entities.dtos.ProfitCalculatorResponse;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentRequest;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentResponse;
import com.dachser.profit_calculation_api.exceptions.CustomerException;
import com.dachser.profit_calculation_api.mappers.ProfitCalculatorMapper;
import com.dachser.profit_calculation_api.repository.CustomerRepository;
import com.dachser.profit_calculation_api.repository.ProfitCalculatorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.dachser.profit_calculation_api.mappers.ProfitCalculatorMapper.responseToEntity;
import static com.dachser.profit_calculation_api.mappers.ShipmentMapper.requestToEntity;

@Service
public class ProfitCalculatorServiceImpl implements ProfitCalculatorService{

    private final CustomerRepository customerRepository;

    private final ShipmentService shipmentService;
    private final ProfitCalculatorRepository profitCalculatorRepository;

    public ProfitCalculatorServiceImpl(CustomerRepository customerRepository, ShipmentService shipmentService, ProfitCalculatorRepository profitCalculatorRepository) {
        this.customerRepository = customerRepository;
        this.shipmentService = shipmentService;
        this.profitCalculatorRepository = profitCalculatorRepository;
    }

    @Override
    public void calculateShipment(Long customerId, ShipmentRequest shipmentRequest) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("This customer doesn't exist!"));

        ShipmentResponse shipmentResponse = shipmentService.saveShipment(requestToEntity(shipmentRequest, customer));

        ProfitCalculatorResponse response = buildingProfitCalculator(shipmentResponse);

        profitCalculatorRepository.save(responseToEntity(response));
    }

    @Override
    public List<ProfitCalculatorResponse> getAllProfitsByCustomerId(Long customerId) {
        return profitCalculatorRepository.getAllProfitsByCustomerId(customerId)
                .stream()
                .map(ProfitCalculatorMapper::entityToResponse)
                .toList();
    }

    private ProfitCalculatorResponse buildingProfitCalculator(ShipmentResponse shipmentResponse){
        return new ProfitCalculatorResponse(
                shipmentResponse.id(),
                shipmentResponse.income().setScale(2, RoundingMode.HALF_UP),
                this.calculateTotalCost(shipmentResponse),
                this.calculateProfit(shipmentResponse),
                shipmentResponse.customerId());
    }

    private BigDecimal calculateProfit(ShipmentResponse shipment){
        return shipment.income().subtract(this.calculateTotalCost(shipment)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalCost(ShipmentResponse shipment){
        return shipment.cost().add(shipment.additionalCost()).setScale(2, RoundingMode.HALF_UP);
    }
}
