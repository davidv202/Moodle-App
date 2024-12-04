package com.example.MoodleApp.models;

import com.example.MoodleApp.enums.STUDY_CYCLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "studenti")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key auto-increment
    private Long id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private STUDY_CYCLE ciclu_studii;

    @Column(nullable = false)
    private int an_studiu;

    @Column(nullable = false)
    private int grupa;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "discipline_studenti",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_cod")
    )
    @JsonIgnore
    private Set<Lecture> discipline;
}
