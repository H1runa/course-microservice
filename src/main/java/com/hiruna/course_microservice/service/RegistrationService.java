package com.hiruna.course_microservice.service;

import com.hiruna.course_microservice.data.*;
import com.hiruna.course_microservice.data.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationInterface regRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentClient stdClient;
    @Autowired
    private KafkaTemplate<String, String> registrationKafkaTemplate;
    private final java.util.Date date = new java.util.Date();

    public Registration createRegistration(Registration reg){
        if (stdClient.studentExists(reg.getStudent_id()) && courseService.courseExistsById(reg.getCourse_id())){
            StudentDTO std = stdClient.getStudentById(reg.getStudent_id());
            Optional<Course> course = courseService.getCourseById(reg.getCourse_id());

            Date sqlDate = Date.valueOf(LocalDate.now());
            reg.setReg_date(sqlDate);

            Registration registration = regRepo.save(reg);

            //logging
            String log = String.format("[%tc] [%s][%d] [%s] was registered for [%s]%n", date, "RegistrationCreated", registration.getId(), std.getName(), course.get().getName());
            registrationKafkaTemplate.send("registration-events", "RegistrationCreated", log);

            return registration;
        }
        return null;
    }

    public List<Course> getCoursesByStudent(int stdId){
        List<Registration> regList = regRepo.getRegistrationByStudent(stdId);
        if (regList.isEmpty()){
            return new ArrayList<Course>();
        }

        List<Course> courses = regList.stream().map(r -> courseService.getCourseById(r.getCourse_id()).get()).toList();
        return courses;

    }

    public List<StudentDTO> getStudentsByCourse(int courseId){
        List<Registration> regList = regRepo.getRegistrationByCourse(courseId);
        if (regList.isEmpty()){
            return new ArrayList<StudentDTO>();
        }

        List<StudentDTO> students = regList.stream().map(r -> stdClient.getStudentById(r.getStudent_id())).toList();
        return students;
    }
}
