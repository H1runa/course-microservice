package com.hiruna.course_microservice.service;

import com.hiruna.course_microservice.data.Course;
import com.hiruna.course_microservice.data.CourseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseInterface cRepo;

    public Course createCourse(Course c){
        return cRepo.save(c);
    }

    public Course updateCourse(Course c){
        return cRepo.save(c);
    }

    public List<Course> getAllCourses(){
        return cRepo.findAll();
    }

    public List<Course> getCoursesByName(String name){
        return cRepo.getCourseByName(name);
    }

    public Optional<Course> getCourseById(int id){
        return cRepo.findById(id);
    }

    public Boolean courseExistsById(int id){
        return cRepo.existsById(id);
    }
}
