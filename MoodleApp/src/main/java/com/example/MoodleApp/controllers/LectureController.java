package com.example.MoodleApp.controllers;


import com.example.MoodleApp.dtos.LectureDTO;
import com.example.MoodleApp.models.Lecture;
import com.example.MoodleApp.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MoodleApp/lectures")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    /*          GET         */

    @GetMapping
    public ResponseEntity<Page<LectureDTO>> getLectures(
            @RequestParam(required = false) Map<String, String> allParams,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page, // Parametru implicit pentru pagina
            @RequestParam(name = "items_per_page", defaultValue = "10", required = false) int items_per_page // Parametru implicit pentru nr. de elemente per pagină
    ) {
        Map<String, Object> filters = allParams.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("page") && !entry.getKey().equals("items_per_page"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("filters: " + filters);

        // Creează obiectul de paginare
        Pageable pageable = PageRequest.of(page, items_per_page);

        // Obține pagina de date
        Page<LectureDTO> lectures = lectureService.getLecturesPage(filters, pageable);

        return ResponseEntity.ok(lectures);
    }

    @GetMapping("/{code}")
    public ResponseEntity<LectureDTO> getLectureByCode(@PathVariable String code) {
        return lectureService.getLectureByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*          POST            */

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        Lecture createdLecture = lectureService.createLecture(lecture);
        return ResponseEntity.ok(createdLecture);
    }

    /*          PUT         */

    @PutMapping("/{code}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable String code, @RequestBody Lecture lectureDetails) {
        return ResponseEntity.ok(lectureService.updateLecture(code, lectureDetails));
    }

    /*          DELETE          */

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteLecture(@PathVariable String code) {
        lectureService.deleteLecture(code);
        return ResponseEntity.noContent().build();
    }
}
