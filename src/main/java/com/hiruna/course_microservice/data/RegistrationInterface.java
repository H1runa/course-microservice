package com.hiruna.course_microservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationInterface  extends JpaRepository<Registration, Integer> {
    @Query("select r from Registration r where r.student_id = ?1")
    public List<Registration> getRegistrationByStudent(int id);

    @Query("select r from Registration r where r.course_id = ?1")
    public List<Registration> getRegistrationByCourse(int id);
}
