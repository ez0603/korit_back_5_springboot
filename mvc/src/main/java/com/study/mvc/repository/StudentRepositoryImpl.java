package com.study.mvc.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    public List<String> studentList = List.of("전주환", "서창현", "예홍렬");
    @Override
    public Map<String, String> getStudentListAll() {
        Map<String, String> studentMap = new HashMap<>();
        for(int i = 0; i < studentList.size(); i++) {
            studentMap.put("name" + (i + 1), studentList.get(i));
        }
        return studentMap;
    }

    @Override
    public Map<String, String> findStudentNameByIndex(int index) {
        Map<String, String> findMap = new HashMap<>();
        findMap.put("name", studentList.get(index));

        return findMap;
    }
}
