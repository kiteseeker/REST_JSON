package by.kulikovski.rest.rest_controller.controller;

import by.kulikovski.rest.rest_controller.entity.Student;
import by.kulikovski.rest.rest_controller.exception.StudentNotFoundException;
import by.kulikovski.rest.rest_controller.controller.response.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public Student getStudentById(@PathVariable int studentId) throws StudentNotFoundException {
        if (studentId > students.size() || studentId <= 0) {
            throw new StudentNotFoundException("Student with id " + studentId + " not found!");
        }
        return students.stream().filter(student -> student.getId() == studentId).findFirst().orElseThrow();
    }


    // custom exception handler for StudentNotFoundException
    // send an exception as a JSON object StudentErrorResponse
    // ResponseEntity<?> is a Spring wrapper for HTTP response (proxy)
    // Can include various types of Objects into HTTP response
    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // another exception to catch and handle all other types of exceptions (fi bad request etc.)
    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleException(Exception e) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
