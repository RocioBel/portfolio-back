package com.portfolio.repository;

import com.portfolio.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ILanguageRepository extends JpaRepository<Language, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Language l WHERE l.languageId = :id")
    void deleteLanguage(Integer id);
}
