package com.example.MoodleApp.services;

import com.example.MoodleApp.dtos.StudentDTO;
import com.example.MoodleApp.dtos.StudentPatchDTO;
import com.example.MoodleApp.mappers.StudentMapper;
import com.example.MoodleApp.models.Lecture;
import com.example.MoodleApp.models.Student;
import com.example.MoodleApp.repositories.LectureRepository;
import com.example.MoodleApp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LectureRepository lectureRepository;

    public List<StudentDTO> getAllStudentsDTO() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::mapStudentToDTO);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setNume(studentDetails.getNume());
        student.setPrenume(studentDetails.getPrenume());
        student.setEmail(studentDetails.getEmail());
        student.setCiclu_studii(studentDetails.getCiclu_studii());
        student.setAn_studiu(studentDetails.getAn_studiu());
        student.setGrupa(studentDetails.getGrupa());

        return studentRepository.save(student);
    }

    public StudentPatchDTO partialUpdateStudent(Long id, StudentPatchDTO studentPatchDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (studentPatchDTO.getNume() != null) {
            student.setNume(studentPatchDTO.getNume());
        }
        if (studentPatchDTO.getPrenume() != null) {
            student.setPrenume(studentPatchDTO.getPrenume());
        }
        if(studentPatchDTO.getEmail() != null) {
            student.setEmail(studentPatchDTO.getEmail());
        }
        if(studentPatchDTO.getCiclu_studii() != null) {
            student.setCiclu_studii(studentPatchDTO.getCiclu_studii());
        }
        if(studentPatchDTO.getAn_studiu() != null) {
            student.setAn_studiu(studentPatchDTO.getAn_studiu());
        }
        if (studentPatchDTO.getGrupa() != null) {
            student.setGrupa(studentPatchDTO.getGrupa());
        }

        studentRepository.save(student);
        return studentMapper.mapStudentPatchDTO(student);
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO assignLectureToStudent(Long studentId, String lectureCod) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Lecture lecture = lectureRepository.findById(lectureCod)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));

        student.getDiscipline().add(lecture);
        studentRepository.save(student);

        return studentMapper.mapStudentToDTO(student);
    }
}
