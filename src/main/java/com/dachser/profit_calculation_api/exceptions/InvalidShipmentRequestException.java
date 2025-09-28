package com.dachser.profit_calculation_api.exceptions;

public class InvalidShipmentRequestException extends RuntimeException {
    public InvalidShipmentRequestException(String message) {
        super(message);
    }
}
