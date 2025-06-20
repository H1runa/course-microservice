package com.hiruna.course_microservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface CourseInterface extends JpaRepository<Course, Integer> {
    @Query("select c from Course c where c.name = ?1")
    public List<Course> getCourseByName(String name);
}
