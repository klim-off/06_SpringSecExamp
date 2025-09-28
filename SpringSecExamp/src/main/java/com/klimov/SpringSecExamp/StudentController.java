package com.klimov.SpringSecExamp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StudentController {
    private List<Student> studentList = new ArrayList<>(List.of(
            new Student(1, "John Doe", 85),
            new Student(2, "Jane Smith", 90),
            new Student(3, "Alice Johnson", 78)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentList;
    }
     @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/students")
    public Student caddStudent(@RequestBody Student student){
               studentList.add(student);
               return student;
    }
}
