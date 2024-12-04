package com.example.MoodleApp.repositories;

import com.example.MoodleApp.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, String>, JpaSpecificationExecutor<Lecture> {
}
