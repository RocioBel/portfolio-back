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
@Table(name = "estudios")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer educationId;
    @Column(name = "titulo")
    private String titleName;
    @Column(name = "instituto")
    private String institute;
    @Column(name = "es_actual")
    private Boolean isActual;
    @Column(name = "fecha_inicio")
    private LocalDate startDate;
    @Column(name = "fecha_fin")
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    @JsonBackReference
    private Person person;

}
