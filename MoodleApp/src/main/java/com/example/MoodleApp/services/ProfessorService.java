package com.example.MoodleApp.services;

import com.example.MoodleApp.dtos.ProfessorDTO;
import com.example.MoodleApp.mappers.ProfessorMapper;
import com.example.MoodleApp.models.Professor;
import com.example.MoodleApp.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorMapper professorMapper;

    public List<ProfessorDTO> getAllProfessorsSpecification(Specification<Professor> specification) {

        return professorRepository.findAll(specification).stream()
                .map(professorMapper::mapProfessorToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Professor> getProfessorById(Long id){
        return professorRepository.findById(id);
    }

    public Optional<ProfessorDTO> getProfessorDTOById(Long id){
        return professorRepository.findById(id)
                .map(professorMapper::mapProfessorToDTO);
    }

    public Professor createProfessor(Professor professor){
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor professorDetails){
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));

        professor.setNume(professorDetails.getNume());
        professor.setPrenume(professorDetails.getPrenume());
        professor.setEmail(professorDetails.getEmail());
        professor.setGrad_didactic(professorDetails.getGrad_didactic());
        professor.setTip_asociere(professorDetails.getTip_asociere());
        professor.setAfiliere(professorDetails.getAfiliere());

        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

    public List<ProfessorDTO> getAllProfessorsDTO() {
        return professorRepository.findAll().stream()
                .map(professorMapper::mapProfessorToDTO)
                .collect(Collectors.toList());
    }
}
