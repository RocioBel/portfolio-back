package com.portfolio.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private Integer code;
    private String description;
}
