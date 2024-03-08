package com.study.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String getStudentList() throws JsonProcessingException {
        Map<String, String> studentMap = studentRepository.getStudentListAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(studentMap);
    }
//    @Override
//    public List<?> getStudentList() {
//        List<Map<String, String>> studentMapList = new ArrayList<>(); // List<Map<String, String>> List안에 Map을 해주는 이유 : 리스트 안에 맵들을 넣어주기 위해서(객체로 꺼내보기 위해)
//        List<String> studentList = studentRepository.getStudentListAll();
//    }

    @Override
    public String getStudent(int index) throws JsonProcessingException {
        Map<String, String> findIndex = studentRepository.findStudentNameByIndex(index);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(findIndex);
    }
}
