package com.dachser.profit_calculation_api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

    public ErrorResponse(String message)
    {
        super();
        this.message = message;
    }
}
