package com.portfolio.service.impl;

import com.portfolio.dto.PersonResponseDto;
import com.portfolio.dto.SkillDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.Person;
import com.portfolio.model.Skill;
import com.portfolio.repository.ISkillRepository;
import com.portfolio.service.IPersonService;
import com.portfolio.service.ISkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements ISkillService {

    @Autowired
    ISkillRepository skillRepository;

    @Autowired
    IPersonService personService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<SkillDto> getSkill(Integer id) throws EntityNotFoundException {
        return personService.getPerson(id).getSkills();
    }

    @Override
    public PersonResponseDto addSkill(Integer id, SkillDto skillDto) throws EntityNotFoundException {
        PersonResponseDto personResponseDto = personService.getPerson(id);
        Skill skill = mapper.map(skillDto, Skill.class);
        Person person = mapper.map(personResponseDto, Person.class);
        skill.setPerson(person);

        skillRepository.save(skill);

        PersonResponseDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public PersonResponseDto updateSkill(Integer id, Integer skillId, SkillDto skillDto) throws EntityNotFoundException {
        personService.getPerson(id);
        Skill skill = getSkillById(skillId);
        Skill mappedSkill = mapper.map(skillDto, Skill.class);
        mappedSkill.setPerson(skill.getPerson());
        skillRepository.save(mappedSkill);

        PersonResponseDto updatedPerson = personService.getPerson(id);
        return updatedPerson;
    }

    @Override
    public void deleteSkill(Integer id, Integer skillId) throws EntityNotFoundException, InvalidRequestException {
        Person person = mapper.map(personService.getPerson(id), Person.class);
        boolean found = person.getSkills().stream().anyMatch(s -> s.getSkillId() == skillId);
        if (!found) {
            throw new InvalidRequestException("The skill doesn't belong to the person");
        }
        skillRepository.deleteSkill(skillId);
    }

    private Skill getSkillById(Integer id) throws EntityNotFoundException {
        Optional<Skill> finded = skillRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Skill with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
