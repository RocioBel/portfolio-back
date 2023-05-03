package com.portfolio.service.impl;

import com.portfolio.dto.PersonDto;
import com.portfolio.dto.ProjectDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.Person;
import com.portfolio.model.Project;
import com.portfolio.repository.IProjectRepository;
import com.portfolio.service.IPersonService;
import com.portfolio.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    IProjectRepository projectRepository;

    @Autowired
    IPersonService personService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProjectDto> getProjects(Integer id) throws EntityNotFoundException {
        return personService.getPerson(id).getProjects();
    }

    @Override
    public ProjectDto addProject(Integer id, ProjectDto projectDto) throws EntityNotFoundException {
        PersonDto personDto = personService.getPerson(id);
        Project project = mapper.map(projectDto, Project.class);
        Person person = mapper.map(personDto, Person.class);
        project.setPerson(person);

        Project savedProject = projectRepository.save(project);
        return mapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(Integer id, Integer edId, ProjectDto projectDto) throws EntityNotFoundException {
        personService.getPerson(id);
        Project project = getProjectById(edId);
        Project mappedProject = mapper.map(projectDto, Project.class);
        mappedProject.setPerson(project.getPerson());
        Project savedProject = projectRepository.save(mappedProject);
        return mapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public void deleteProject(Integer id, Integer projectId) throws EntityNotFoundException, InvalidRequestException {
        Person person = mapper.map(personService.getPerson(id), Person.class);
        boolean found = person.getProjects().stream().anyMatch(e -> e.getProjectId() == projectId);
        if (!found) {
            throw new InvalidRequestException("The project doesn't belong to the person");
        }
        projectRepository.deleteProject(projectId);
    }


    private Project getProjectById(Integer id) throws EntityNotFoundException {
        Optional<Project> finded = projectRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Project with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
