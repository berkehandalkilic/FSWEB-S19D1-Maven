package com.workintech.s18d2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ErrorDetail {
    private String message;

    public ErrorDetail(String message) {
        this.message = message;
    }
}
