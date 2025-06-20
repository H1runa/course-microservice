package com.hiruna.course_microservice.controller;

import com.hiruna.course_microservice.data.Course;
import com.hiruna.course_microservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService cService;

    @PostMapping(path = "/courses")
    public Course createCourse(@RequestBody Course c){
        return cService.createCourse(c);
    }

    @PutMapping(path = "/courses")
    public Course updateCourse(@RequestBody Course c){
        return cService.updateCourse(c);
    }

    @GetMapping(path = "/courses")
    public List<Course> getAllCourses(){
        return cService.getAllCourses();
    }

    @GetMapping(path = "/courses", params = {"name"})
    public List<Course> getCoursesByName(@RequestParam String name){
        return cService.getCoursesByName(name);
    }


}
