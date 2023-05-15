package com.portfolio.service.impl;

import com.portfolio.dto.EducationDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.Education;
import com.portfolio.model.Person;
import com.portfolio.repository.IEducationRepository;
import com.portfolio.repository.IPersonRepository;
import com.portfolio.service.IEducationService;
import com.portfolio.service.IPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService implements IEducationService {

    @Autowired
    IEducationRepository educationRepository;

    @Autowired
    IPersonService personService;

    @Autowired
    IPersonRepository personRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<EducationDto> getEducation(Integer id) throws EntityNotFoundException {
        return personService.getPerson(id).getEducation();
    }

    @Override
    public PersonDto addEducation(Integer id, EducationDto educationDto) throws EntityNotFoundException {
        PersonDto personDto = personService.getPerson(id);
        Education education = mapper.map(educationDto, Education.class);
        education.setPerson(mapper.map(personDto, Person.class));
        education.setLogo("assets\\universidad.png");
        educationRepository.save(education);

        PersonDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public PersonDto updateEducation(Integer id, Integer edId, EducationDto educationDto) throws EntityNotFoundException {
        personService.getPerson(id);
        Education education = getEducationById(edId);
        Education mappedEducation = mapper.map(educationDto, Education.class);
        mappedEducation.setPerson(education.getPerson());
        educationRepository.save(mappedEducation);

        PersonDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public void deleteEducation(Integer id, Integer edId) throws EntityNotFoundException, InvalidRequestException {
        Person person = mapper.map(personService.getPerson(id), Person.class);
        boolean found = person.getEducation().stream().anyMatch(e -> e.getEducationId() == edId);
        if (!found) {
            throw new InvalidRequestException("The education doesn't belong to the person");
        }
        educationRepository.deleteEducation(edId);
    }


    private Education getEducationById(Integer id) throws EntityNotFoundException {
        Optional<Education> finded = educationRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Education with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
