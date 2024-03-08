package com.study.mvc.repository;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentRepository {

    public Map<String, String> getStudentListAll();

    public Map<String, String> findStudentNameByIndex(int index);
}

