package com.portfolio.dto;

import com.portfolio.model.JobType;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class JobExperienceResponse {
    private Long experienceId;
    private String jobTitle;
    private String company;
    private Boolean isActual;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private JobType type;
}
