package com.devinfusion.eventia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements BaseDTO {
    private int statusCode;
    private String message;
    private String details;
}

