package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String description;
    private String logo;
    private Integer typeId;
}
