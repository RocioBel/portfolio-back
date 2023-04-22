package com.portfolio.service;

import com.portfolio.dto.JobExperienceDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.JobExperience;
import com.portfolio.model.JobType;
import com.portfolio.model.Person;
import com.portfolio.repository.IJobExperienceRepository;
import com.portfolio.repository.IJobTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobExperienceService implements  IJobExperienceService {

    @Autowired
    IJobTypeRepository jobTypeRepository;

    @Autowired
    IJobExperienceRepository jobExperienceRepository;

    @Autowired
    IPersonService personService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<JobExperienceDto> getExperiences(Integer id) throws EntityNotFoundException {
        List<JobExperienceDto> experiences = personService.getPerson(id).getExperiences();
        return experiences.stream().map(e -> mapper.map(e, JobExperienceDto.class)).collect(Collectors.toList());
    }

    @Override
    public JobExperienceDto addExperience(Integer id, JobExperienceDto workDto) throws EntityNotFoundException {
        PersonDto person = personService.getPerson(id);
        JobExperience work = mapper.map(workDto, JobExperience.class);
        Person mappedPerson = mapper.map(person, Person.class);
        work.setPerson(mappedPerson);

        JobType jobType = jobTypeRepository.findById(workDto.getTypeId()).orElseThrow(()->new EntityNotFoundException("Job type doesn't exist"));
        work.setType(jobType);

        JobExperience savedWork = jobExperienceRepository.save(work);
        return mapper.map(savedWork, JobExperienceDto.class);
    }

    @Override
    public JobExperienceDto updateExperience(Integer id, Integer expId, JobExperienceDto updatedJob) throws EntityNotFoundException {
        personService.getPerson(id);
        getExperienceById(expId);
        JobExperience mapppedExperience = mapper.map(updatedJob, JobExperience.class);
        JobExperience savedExperience = jobExperienceRepository.save(mapppedExperience);
        return mapper.map(savedExperience, JobExperienceDto.class);
    }

    @Override
    public void deleteExperience(Integer id, Integer expId) throws EntityNotFoundException, InvalidRequestException {
        PersonDto person = personService.getPerson(id);
        JobExperience experience = getExperienceById(expId);
        if (!person.getExperiences().contains(experience)) {
            throw new InvalidRequestException("The experience doesn't belong to the person");
        }
        person.getExperiences().remove(experience);
        personService.savePerson(mapper.map(person, PersonDto.class));
        jobExperienceRepository.deleteById(Integer.parseInt(experience.getExperienceId().toString()));
    }


    private JobExperience getExperienceById(Integer id) throws EntityNotFoundException {
        Optional<JobExperience> finded = jobExperienceRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Job experience with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
