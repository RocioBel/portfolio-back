package com.portfolio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class ProjectDto {
    private Integer projectId;
    private String name;
    private String description;
    private String github;
    private String url;
}
