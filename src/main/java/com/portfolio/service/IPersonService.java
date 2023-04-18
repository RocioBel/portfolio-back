package com.portfolio.service;

import com.portfolio.dto.PersonRequest;
import com.portfolio.dto.PersonResponse;
import com.portfolio.exception.EntityNotFoundException;

import java.util.List;

public interface IPersonService {
    List<PersonResponse> getPeople();
    PersonResponse savePerson(PersonRequest person);
    void deletePerson(Integer id);
    PersonResponse getPerson(Integer id) throws EntityNotFoundException;
    PersonResponse updatePerson(Integer id, PersonRequest person) throws EntityNotFoundException;
}
