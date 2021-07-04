package uz.pdp.appspringcaching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appspringcaching.entity.Student;
import uz.pdp.appspringcaching.repository.StudentRepository;

import java.util.Optional;

@Service
//@CacheConfig(cacheNames = "student")
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String addStudent(Student student) {
        Student newStudent = new Student(student.getName(), student.getAge());
        studentRepository.save(newStudent);
        return "Student added!";
    }

    @Cacheable(value = "student", key = "#id") // add student to cache with id
    public Student getStudentCache(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()) {
            return new Student();
        }
        System.out.println("Olingan student cachega joylandi!");
        return optionalStudent.get();
    }

    @CacheEvict(value = "student", key = "#id") // delete student from cache memory which is given with #id
    public String editStudent(Integer id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()) {
            return "Student not found!";
        }
        Student editingStudent = optionalStudent.get();
        editingStudent.setAge(student.getAge());
        editingStudent.setName(student.getName());
        studentRepository.save(editingStudent);
        return "Student edited!";
    }

//    @Cacheable(value="student", condition="#name.length<20")
//    public Student findStudent(String name)
//    {
////some code
//    }

    @CacheEvict(value = "student", key = "#id") // delete student from cache when student deleted
    public String deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return "Student deleted!";
    }
}
