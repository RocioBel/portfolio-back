package com.portfolio.service;

import com.portfolio.dto.JobExperienceRequest;
import com.portfolio.dto.JobExperienceResponse;
import com.portfolio.dto.PersonResponse;
import com.portfolio.exception.EntityNotFoundException;
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
    public List<JobExperienceResponse> getExperiences(Integer id) throws EntityNotFoundException {
        List<JobExperienceResponse> experiences = personService.getPerson(id).getExperiences();
        return experiences.stream().map(e -> mapper.map(e, JobExperienceResponse.class)).collect(Collectors.toList());
    }

    @Override
    public JobExperienceResponse addExperience(Integer id, JobExperienceRequest workDto) throws EntityNotFoundException {
        PersonResponse person = personService.getPerson(id);
        JobExperience work = mapper.map(workDto, JobExperience.class);
        Person mappedPerson = mapper.map(person, Person.class);
        work.setPerson(mappedPerson);

        JobType jobType = jobTypeRepository.findById(workDto.getTypeId()).orElseThrow(()->new EntityNotFoundException("Job type doesn't exist"));
        work.setType(jobType);

        JobExperience savedWork = jobExperienceRepository.save(work);
        return mapper.map(savedWork, JobExperienceResponse.class);
    }

    @Override
    public JobExperienceResponse updateExperience(Integer id, Integer expId, JobExperienceRequest updatedJob) throws EntityNotFoundException {
        personService.getPerson(id);
        getExperienceById(expId);
        JobExperience mapppedExperience = mapper.map(updatedJob, JobExperience.class);
        JobExperience savedExperience = jobExperienceRepository.save(mapppedExperience);
        return mapper.map(savedExperience, JobExperienceResponse.class);
    }

    @Override
    public void deleteExperience(Integer id, Integer expId) throws EntityNotFoundException {
        personService.getPerson(id);
        getExperienceById(id);
        jobExperienceRepository.deleteById(expId);
    }


    private JobExperienceResponse getExperienceById(Integer id) throws EntityNotFoundException {
        Optional<JobExperience> finded = jobExperienceRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Job experience with id " + id + " doesn't exist.");
        }

        return mapper.map(finded.get(), JobExperienceResponse.class);
    }
}
