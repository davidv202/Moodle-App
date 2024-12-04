package com.example.MoodleApp.models;

import com.example.MoodleApp.dtos.ProfessorDTO;
import com.example.MoodleApp.enums.EXAMINATION_TYPE;
import com.example.MoodleApp.enums.LECTURE_CATEGORY;
import com.example.MoodleApp.enums.LECTURE_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name= "disciplina_studiu")
@Getter
@Setter
public class Lecture {
    @Id
    private String cod;

    @ManyToOne
    @JoinColumn(name="ID_titular", nullable = false)
    private Professor titular;

    @JsonProperty("titular")
    public ProfessorDTO getTitularDTO() {
        if (titular != null) {
            ProfessorDTO dto = new ProfessorDTO();
            dto.setId(titular.getId());
            return dto;
        }
        return null;
    }

    @Column(nullable = false)
    private String nume_disciplina;

    @Column(nullable = false)
    private int an_studiu;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LECTURE_TYPE tip_disciplina;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LECTURE_CATEGORY categorie_disciplina;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EXAMINATION_TYPE tip_examinare;

    @ManyToMany(mappedBy = "discipline", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Student> studenti;
}
