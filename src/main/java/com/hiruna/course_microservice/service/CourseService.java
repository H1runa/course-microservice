package com.hiruna.course_microservice.service;

import com.hiruna.course_microservice.data.Course;
import com.hiruna.course_microservice.data.CourseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseInterface cRepo;
    @Autowired
    private KafkaTemplate<String, Course> courseKafkaTemplate;

    public Course createCourse(Course c){
        Course course = cRepo.save(c);
        courseKafkaTemplate.send("course-events", "CourseCreated", course);
        return course;
    }

    public Course updateCourse(Course c){
        Course course = cRepo.save(c);
        courseKafkaTemplate.send("course-events", "CourseUpdated", course);
        return course;
    }

    public List<Course> getAllCourses(){
        return cRepo.findAll();
    }

    public List<Course> getCoursesByName(String name){
        return cRepo.getCourseByName(name);
    }

    public Optional<Course> getCourseById(int id){
        Optional<Course> course =  cRepo.findById(id);
        if (course.isPresent()){
            courseKafkaTemplate.send("course-events", "CourseRetrieved", course.get());
        }
        return course;
    }

    public Boolean courseExistsById(int id){
        return cRepo.existsById(id);
    }

    public void deleteCourse(int id){
        Optional<Course> c = cRepo.findById(id);
        if (c.isPresent()){
            cRepo.delete(c.get());
            courseKafkaTemplate.send("course-events", "CourseDeleted", c.get());
        }

    }
}
