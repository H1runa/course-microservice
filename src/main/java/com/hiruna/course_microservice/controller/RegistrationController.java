package com.hiruna.course_microservice.controller;

import com.hiruna.course_microservice.data.Course;
import com.hiruna.course_microservice.data.Registration;
import com.hiruna.course_microservice.data.dto.StudentDTO;
import com.hiruna.course_microservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService regService;

    @PostMapping(path = "/registrations")
    public ResponseEntity<Registration> createRegistraion(@RequestBody Registration reg){
        Registration registered = regService.createRegistration(reg);
        if (registered == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(registered);
    }

    @GetMapping(path = "/courses/registrations/{id}")
    public List<Course> getCoursesByStudent(@PathVariable int id){
        return regService.getCoursesByStudent(id);
    }

    @GetMapping(path = "/students/registrations/{id}")
    public List<StudentDTO> getStudentsByCourse(@PathVariable int id){
        return regService.getStudentsByCourse(id);
    }

}
