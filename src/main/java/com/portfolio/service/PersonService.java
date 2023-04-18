package com.portfolio.service;

import com.portfolio.dto.PersonRequest;
import com.portfolio.dto.PersonResponse;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.model.Person;
import com.portfolio.repository.IJobExperienceRepository;
import com.portfolio.repository.IPersonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements  IPersonService {
    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IJobExperienceRepository jobExperienceRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<PersonResponse> getPeople() {
        List<Person> result = personRepository.findAll();
        return result.stream().map(p -> mapper.map(p, PersonResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PersonResponse savePerson(PersonRequest person) {
        Person mappedPerson = mapper.map(person, Person.class);
        Person result = personRepository.save(mappedPerson);
        return mapper.map(result, PersonResponse.class);
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);

    }

    @Override
    @Transactional
    public PersonResponse getPerson(Integer id) throws EntityNotFoundException {
        return getPersonById(id);
    }

    @Override
    public PersonResponse updatePerson(Integer id, PersonRequest person) throws EntityNotFoundException {
        getPersonById(id);
        Person mappedPerson = mapper.map(person, Person.class);
        Person result = personRepository.save(mappedPerson);
        return mapper.map(result, PersonResponse.class);
    }

    private PersonResponse getPersonById(Integer id) throws EntityNotFoundException {
        Optional<Person> finded = personRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Person with id " + id + " doesn't exist.");
        }

        return mapper.map(finded.get(), PersonResponse.class);
    }

}
