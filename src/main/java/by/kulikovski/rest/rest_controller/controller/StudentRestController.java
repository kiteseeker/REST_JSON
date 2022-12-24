package by.kulikovski.rest.rest_controller.controller;

import by.kulikovski.rest.rest_controller.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private final List<Student> students = new ArrayList<>();

    //define @PostConstruct to load student data only once
    @PostConstruct
    public void loadData() {
        students.add(new Student(1, "Alex", "Cooper"));
        students.add(new Student(2, "Mary", "Jones"));
        students.add(new Student(3, "Cristian", "Shuffle"));
    }

    // define endpoint for "/students" - return list of students
    // controller will return an array of JSONs. Student objects will be converted automatically
    // check Content-Type in Postman - application/json
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // define a new endpoint for return of single student by ID
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        return students.stream().filter(student -> student.getId() == studentId).findFirst().orElseThrow();
    }
}
