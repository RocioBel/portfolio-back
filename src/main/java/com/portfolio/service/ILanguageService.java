package com.portfolio.service;

import com.portfolio.dto.LanguageDto;
import com.portfolio.dto.PersonResponseDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;

import java.util.List;

public interface ILanguageService {
    List<LanguageDto> getLanguage(Integer id) throws EntityNotFoundException;
    PersonResponseDto addLanguage(Integer id, LanguageDto language) throws EntityNotFoundException;
    PersonResponseDto updateLanguage(Integer id, Integer languageId, LanguageDto language) throws EntityNotFoundException;
    void deleteLanguage(Integer id, Integer languageId) throws EntityNotFoundException, InvalidRequestException;
}
