package com.example.cassandra.controller;

import com.example.cassandra.exception.ResourceNotFoundException;
import com.example.cassandra.model.Student;
import com.example.cassandra.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        studentRepository.save(student);
        return student;

    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Integer studentId){
        Student student=studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not found" + studentId));
        return ResponseEntity.ok().body(student);
    }



    @GetMapping("/students")
    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
                                                 @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);

    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(value = "id") Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not found::: " + studentId));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }
}
