package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    private Integer personId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String aboutMe;
    private String photo;
    private String linkedin;
    private List<JobExperienceDto> experiences = new ArrayList<>();
    private List<EducationDto> education = new ArrayList<>();
    private List<ProjectDto> projects = new ArrayList<>();


}
