package com.portfolio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Data
@ToString
public class EducationDto {
    private Integer educationId;
    private String titleName;
    private String institute;
    private String logo;
    private Boolean isActual;
    private LocalDate startDate;
    private LocalDate endDate;
}
