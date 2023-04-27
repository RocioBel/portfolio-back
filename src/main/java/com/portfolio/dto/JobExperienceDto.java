package com.portfolio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Data
@ToString
public class JobExperienceDto {
    private Integer experienceId;
    private String jobTitle;
    private String company;
    private Boolean isActual;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String logo;
    private Integer typeId;
}
