package com.portfolio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class LanguageDto {
    private Integer languageId;
    private String name;
    private String level;
}
