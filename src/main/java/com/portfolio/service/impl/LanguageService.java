package com.portfolio.service.impl;

import com.portfolio.dto.LanguageDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.model.Language;
import com.portfolio.model.Person;
import com.portfolio.repository.ILanguageRepository;
import com.portfolio.service.ILanguageService;
import com.portfolio.service.IPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService implements ILanguageService {

    @Autowired
    ILanguageRepository languageRepository;

    @Autowired
    IPersonService personService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<LanguageDto> getLanguage(Integer id) throws EntityNotFoundException {
        return personService.getPerson(id).getLanguages();
    }

    @Override
    public LanguageDto addLanguage(Integer id, LanguageDto languageDto) throws EntityNotFoundException {
        PersonDto personDto = personService.getPerson(id);
        Language language = mapper.map(languageDto, Language.class);
        Person person = mapper.map(personDto, Person.class);
        language.setPerson(person);

        Language savedLanguage = languageRepository.save(language);
        return mapper.map(savedLanguage, LanguageDto.class);
    }

    @Override
    public LanguageDto updateLanguage(Integer id, Integer edId, LanguageDto languageDto) throws EntityNotFoundException {
        personService.getPerson(id);
        Language education = getLanguageById(edId);
        Language mappedLanguage = mapper.map(languageDto, Language.class);
        mappedLanguage.setPerson(education.getPerson());
        Language savedLanguage = languageRepository.save(mappedLanguage);
        return mapper.map(savedLanguage, LanguageDto.class);
    }

    @Override
    public void deleteLanguage(Integer id, Integer edId) throws EntityNotFoundException, InvalidRequestException {
        Person person = mapper.map(personService.getPerson(id), Person.class);
        boolean found = person.getLanguages().stream().anyMatch(l -> l.getLanguageId() == edId);
        if (!found) {
            throw new InvalidRequestException("The language doesn't belong to the person");
        }
        languageRepository.deleteLanguage(edId);
    }


    private Language getLanguageById(Integer id) throws EntityNotFoundException {
        Optional<Language> finded = languageRepository.findById(id);
        if(finded.isEmpty()){
            throw new EntityNotFoundException("Language with id " + id + " doesn't exist.");
        }

        return finded.get();
    }
}
