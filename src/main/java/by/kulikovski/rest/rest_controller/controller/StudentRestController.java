package by.kulikovski.rest.rest_controller.controller;

import by.kulikovski.rest.rest_controller.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return list of students
    // controller will return an array of JSONs. Student objects will be converted automatically
    // check Content-Type in Postman - application/json

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Alex", "Cooper"));
        students.add(new Student("Mary", "Jones"));
        students.add(new Student("Cristian", "Shuffle"));

        return students;
    }
}
