package com.portfolio.service;

import com.portfolio.dto.EducationDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;

import java.util.List;

public interface IEducationService {
    List<EducationDto> getEducation(Integer id) throws EntityNotFoundException;
    PersonDto addEducation(Integer id, EducationDto education) throws EntityNotFoundException;
    PersonDto updateEducation(Integer id, Integer expId, EducationDto education) throws EntityNotFoundException;
    void deleteEducation(Integer id, Integer expId) throws EntityNotFoundException, InvalidRequestException;
}
