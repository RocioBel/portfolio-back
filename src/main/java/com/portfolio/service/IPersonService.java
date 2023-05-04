package com.portfolio.service;

import com.portfolio.dto.PersonRequestDto;
import com.portfolio.dto.PersonResponseDto;
import com.portfolio.exception.EntityNotFoundException;

import java.util.List;

public interface IPersonService {
    List<PersonResponseDto> getPeople();
    PersonResponseDto savePerson(PersonRequestDto person);
    void deletePerson(Integer id);
    PersonResponseDto getPerson(Integer id) throws EntityNotFoundException;
    PersonResponseDto updatePerson(Integer id, PersonRequestDto person) throws EntityNotFoundException;
}
