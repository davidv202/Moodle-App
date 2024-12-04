package com.example.MoodleApp.controllers;


import com.example.MoodleApp.dtos.ProfessorDTO;
import com.example.MoodleApp.models.Professor;
import com.example.MoodleApp.services.ProfessorService;
import com.example.MoodleApp.specifications.DynamicSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/MoodleApp/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    /*          GET         */

    @GetMapping
    public List<ProfessorDTO> getAllProfessors(@RequestParam Map<String, String> allParams) {
        Map<String, Object> filters = new HashMap<>();
        allParams.forEach((key, value) -> {
            if (value != null) {
                filters.put(key, value);
            }
        });

        DynamicSpecificationBuilder<Professor> specificationBuilder = new DynamicSpecificationBuilder<>();
        Specification<Professor> specification = specificationBuilder.buildSpecification(filters);

        return professorService.getAllProfessorsSpecification(specification);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/lectures")
    public ResponseEntity<ProfessorDTO> getProfessorLectures(@PathVariable Long id){
        Optional<ProfessorDTO> professorDTO = professorService.getProfessorDTOById(id);

        return professorDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*          POST         */

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor){
        return professorService.createProfessor(professor);
    }

    /*          PUT         */

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails){
        return ResponseEntity.ok(professorService.updateProfessor(id, professorDetails));
    }

    /*          DELETE          */

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> deleteProfessor(@PathVariable Long id){
        professorService.deleteProfessor(id);
        return ResponseEntity.ok().build();
    }



}
