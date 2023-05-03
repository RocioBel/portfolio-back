package com.portfolio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class SkillDto {
    private Integer skillId;
    private String name;
    private String level;
}
