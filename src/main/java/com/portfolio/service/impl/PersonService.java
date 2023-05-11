package com.portfolio.service.impl;

import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.model.Person;
import com.portfolio.repository.IPersonRepository;
import com.portfolio.service.IPersonService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {
    @Autowired
    IPersonRepository personRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<PersonDto> getPeople() {
        List<Person> result = personRepository.findAll();
        return result.stream().map(p -> mapper.map(p, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonDto savePerson(PersonDto person) {
        Person mappedPerson = mapper.map(person, Person.class);
        Person result = personRepository.save(mappedPerson);
        return mapper.map(result, PersonDto.class);
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);

    }

    @Override
    @Transactional
    public PersonDto getPerson(Integer id) throws EntityNotFoundException {
        return mapper.map(getPersonById(id), PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(Integer id, PersonDto personRequest) throws EntityNotFoundException {
        Person savedPerson = getPersonById(id);

        Person personToSave = mapper.map(personRequest, Person.class);
        personToSave.setExperiences(savedPerson.getExperiences());
        personToSave.setEducation(savedPerson.getEducation());
        personToSave.setSkills(savedPerson.getSkills());
        personToSave.setLanguages(savedPerson.getLanguages());
        personToSave.setProjects(savedPerson.getProjects());

        Person result = personRepository.save(personToSave);
        return mapper.map(result, PersonDto.class);
    }

    private Person getPersonById(Integer id) throws EntityNotFoundException {
        Optional<Person> finded = personRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Person with id " + id + " doesn't exist.");
        }

        return finded.get();
    }

}
