package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonRequest {
    private Long personId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String aboutMe;
    private String photo;
    private String linkedin;

}
