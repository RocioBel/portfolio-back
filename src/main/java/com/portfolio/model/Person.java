package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@ToString
@Table(name = "persona")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long personId;
    @Column(name = "nombre")
    private String firstName;
    @Column(name = "apellido")
    private String lastName;
    @Column(name = "fecha_nacimiento")
    private LocalDate birthday;
    @Column(name = "telefono")
    private String phone;
    @Column(name = "correo")
    private String email;
    @Column(name = "sobre_mi")
    private String aboutMe;
    @Column(name = "url_foto")
    private String photo;
    @Column(name= "url_linkedin")
    private String linkedin;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,  fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<JobExperience> experiences = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,  fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Education> education = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,  fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Project> projects = new ArrayList<>();

}
