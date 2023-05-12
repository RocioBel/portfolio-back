package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
