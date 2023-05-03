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
public class PersonRequestDto {
    private String firstName;
    private String lastName;
    private String title;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String aboutMe;
    private String photo;
    private String linkedin;

}
