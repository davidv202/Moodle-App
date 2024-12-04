package com.example.MoodleApp.models;


import com.example.MoodleApp.enums.ACADEMIC_GRADE;
import com.example.MoodleApp.enums.ASSOCIATION_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="cadre_didactice")
@Getter
@Setter
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ACADEMIC_GRADE grad_didactic;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ASSOCIATION_TYPE tip_asociere;

    @Column(nullable = false)
    private String afiliere;

    @OneToMany(mappedBy = "titular", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Lecture> discipline;
}
