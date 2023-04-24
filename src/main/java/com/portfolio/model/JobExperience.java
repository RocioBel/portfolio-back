package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@ToString
@Table(name = "experiencia")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer experienceId;
    @Column(name = "puesto")
    private String jobTitle;
    @Column(name = "empresa")
    private String company;
    @Column(name = "es_actual")
    private Boolean isActual;
    @Column(name = "fecha_inicio")
    private LocalDate startDate;
    @Column(name = "fecha_fin")
    private LocalDate endDate;
    @Column(name = "descripcion")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    @JsonBackReference
    private Person person;
    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private JobType type;


}
