package com.study.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public String getStudentList() throws JsonProcessingException;

    public String getStudent(int index) throws JsonProcessingException;
}
