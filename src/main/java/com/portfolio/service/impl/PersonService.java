package com.portfolio.service.impl;

import com.portfolio.dto.PersonRequestDto;
import com.portfolio.dto.PersonResponseDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.model.Person;
import com.portfolio.repository.IJobExperienceRepository;
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

    @Autowired
    IJobExperienceRepository jobExperienceRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<PersonResponseDto> getPeople() {
        List<Person> result = personRepository.findAll();
        return result.stream().map(p -> mapper.map(p, PersonResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonResponseDto savePerson(PersonRequestDto person) {
        Person mappedPerson = mapper.map(person, Person.class);
        Person result = personRepository.save(mappedPerson);
        return mapper.map(result, PersonResponseDto.class);
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);

    }

    @Override
    @Transactional
    public PersonResponseDto getPerson(Integer id) throws EntityNotFoundException {
        return mapper.map(getPersonById(id), PersonResponseDto.class);
    }

    @Override
    public PersonResponseDto updatePerson(Integer id, PersonRequestDto personRequest) throws EntityNotFoundException {
        Person person = getPersonById(id);

        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setBirthday(personRequest.getBirthday());
        person.setAboutMe(personRequest.getAboutMe());
        person.setEmail(personRequest.getEmail());
        person.setLinkedin(personRequest.getLinkedin());
        person.setPhone(personRequest.getPhone());
        person.setPhoto(personRequest.getTitle());
        person.setTitle(personRequest.getTitle());

        Person result = personRepository.save(person);
        return mapper.map(result, PersonResponseDto.class);
    }

    private Person getPersonById(Integer id) throws EntityNotFoundException {
        Optional<Person> finded = personRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Person with id " + id + " doesn't exist.");
        }

        return finded.get();
    }

}
