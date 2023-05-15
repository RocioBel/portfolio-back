package com.portfolio.service.impl;

import com.portfolio.dto.JobExperienceDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.JobExperience;
import com.portfolio.model.JobType;
import com.portfolio.model.Person;
import com.portfolio.repository.IJobExperienceRepository;
import com.portfolio.repository.IJobTypeRepository;
import com.portfolio.service.IJobExperienceService;
import com.portfolio.service.IPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobExperienceService implements IJobExperienceService {

    @Autowired
    IJobTypeRepository jobTypeRepository;

    @Autowired
    IJobExperienceRepository jobExperienceRepository;

    @Autowired
    IPersonService personService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<JobExperienceDto> getExperiences(Integer id) throws EntityNotFoundException {
        return personService.getPerson(id).getExperiences();
    }

    @Override
    public PersonDto addExperience(Integer id, JobExperienceDto workDto) throws EntityNotFoundException {
        PersonDto person = personService.getPerson(id);
        JobExperience work = mapper.map(workDto, JobExperience.class);
        work.setPerson(mapper.map(person, Person.class));

        JobType jobType = jobTypeRepository.findById(workDto.getTypeId()).orElseThrow(()->new EntityNotFoundException("Job type doesn't exist"));
        work.setType(jobType);
        work.setLogo("assets/empresa.png");

        jobExperienceRepository.save(work);

        PersonDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public PersonDto updateExperience(Integer id, Integer expId, JobExperienceDto updatedJob) throws EntityNotFoundException {
        personService.getPerson(id);
        JobExperience jobExperience = getExperienceById(expId);
        JobExperience mapppedExperience = mapper.map(updatedJob, JobExperience.class);
        mapppedExperience.setPerson(jobExperience.getPerson());
        jobExperienceRepository.save(mapppedExperience);

        PersonDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public void deleteExperience(Integer id, Integer expId) throws EntityNotFoundException, InvalidRequestException {
        PersonDto person = personService.getPerson(id);
        boolean found = person.getExperiences().stream().anyMatch(e -> e.getExperienceId() == expId);
        if (!found) {
            throw new InvalidRequestException("The experience doesn't belong to the person");
        }
        jobExperienceRepository.deleteExperience(expId);
    }


    private JobExperience getExperienceById(Integer id) throws EntityNotFoundException {
        Optional<JobExperience> finded = jobExperienceRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Job experience with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
