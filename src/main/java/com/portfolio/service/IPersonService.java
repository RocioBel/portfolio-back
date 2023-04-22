package com.portfolio.service;

import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;

import java.util.List;

public interface IPersonService {
    List<PersonDto> getPeople();
    PersonDto savePerson(PersonDto person);
    void deletePerson(Integer id);
    PersonDto getPerson(Integer id) throws EntityNotFoundException;
    PersonDto updatePerson(Integer id, PersonDto person) throws EntityNotFoundException;
}
