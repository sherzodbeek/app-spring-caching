package uz.pdp.appspringcaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringcaching.entity.Student;
import uz.pdp.appspringcaching.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        String message = studentService.addStudent(student);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
        Student student = studentService.getStudentCache(id);
        return ResponseEntity.ok(student);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editStudent(@PathVariable Integer id, @RequestBody Student student) {
        String message = studentService.editStudent(id, student);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        String message = studentService.deleteStudent(id);
        return ResponseEntity.ok(message);
    }
}
