package com.example.MoodleApp.controllers;

import com.example.MoodleApp.dtos.StudentDTO;
import com.example.MoodleApp.dtos.StudentPatchDTO;
import com.example.MoodleApp.models.Student;
import com.example.MoodleApp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/MoodleApp/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*          GET         */

    @GetMapping
    public List<StudentDTO> getAllStudentsDTO() {
        return studentService.getAllStudentsDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/lectures")
    public ResponseEntity<StudentDTO> getStudentLectures(@PathVariable Long id) {
        Optional<StudentDTO> studentDTO = studentService.getStudentById(id);

        return studentDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*          POST            */

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/{id}/lectures/{lectureCod}")
    public ResponseEntity<StudentDTO> assignLectureToStudent(@PathVariable Long id, @PathVariable String lectureCod) {
        return ResponseEntity.ok(studentService.assignLectureToStudent(id, lectureCod));
    }

    /*          PUT         */

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDetails));
    }

    /*          PATCH           */

    @PatchMapping("/{id}")
    public ResponseEntity<StudentPatchDTO> partialUpdateStudent(@PathVariable Long id, @RequestBody StudentPatchDTO studentPatchDTO) {
        StudentPatchDTO updatedStudent = studentService.partialUpdateStudent(id, studentPatchDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    /*          DELETE          */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
