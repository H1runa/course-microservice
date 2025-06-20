package com.hiruna.course_microservice.service;

import com.hiruna.course_microservice.data.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentClient {
    @Value("${student.service.url}")
    private String url;

    final RestTemplate restTemplate = new RestTemplate();

    public Boolean studentExists(int id){
        String req_url = url+"/students/"+id+"/exists";
//        System.out.println(url);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(req_url, Boolean.class);
        return response.getBody()!=null && response.getBody();
    }

    public StudentDTO getStudentById(int id){
        String req_url = url+"/students/"+id;
//        System.out.println(req_url);
        ResponseEntity<StudentDTO> response = restTemplate.getForEntity(req_url, StudentDTO.class);
        return response.getBody();
    }
}
