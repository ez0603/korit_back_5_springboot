package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.dto.StudentRespDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue String students,@RequestBody Student student) throws JsonProcessingException {
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        if(students != null) {
            if(!students.isBlank()) {
                ObjectMapper studentCookie = new ObjectMapper();
                studentList = studentCookie.readValue(students, List.class);
                lastId = studentList.get(studentList.size() - 1 ).getStudentId();
            }
        }

        student.setStudentId(lastId + 1);
        studentList.add(student);

        ObjectMapper newStudentList = new ObjectMapper();
        String newStudents = newStudentList.writeValueAsString(studentList);
        ResponseCookie responseCookie = ResponseCookie
                .from("test", "test_data") // from(쿠키의 데이터 이름)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();
        return ResponseEntity
                .created(null)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(student);
    }

    @GetMapping("/student")
    @ResponseBody
       public ResponseEntity<?> getStudentInfo(StudentReqDto studentDto) { // StudentDto = 자료형, studentDto = 객체
        System.out.println(studentDto);
        return ResponseEntity.ok().body(studentDto.toRespDto());
    // ResponseEntity = 상태코드를 바꿔줌 (.ok,.badRequest) -> 에러 처리를 위해
    }
    @GetMapping("/student/{studentId}") // studentId가 동적으로 작동해야하기때문에 {} 안에 넣어줌
    public ResponseEntity<?> getStudent(@PathVariable int studentId) { // 어떤 body데이터가 응답할 지 모르기 때문에 제네릭에 ? 를 넣어둠 , PathVariable = 주소값을 가져올 때 사용
        List<Student> studentList = List.of(
                new Student(1, "이지언"),
                new Student(2, "삼지언"),
                new Student(3, "사지언"),
                new Student(4, "오지언")
        );
        Student findStudent = null;
        for(Student student : studentList) {
            if(student.getStudentId() == studentId) {
                findStudent = student;
            }
        }
        // for문 대신 filter 쓸때
//        1. studentList.stream().filter(student -> student.getStudentId() == studentId).collect(Collectors.toList()).get(0);
//
//        2. Optional<Student> optionalStudent =
//                studentList.stream().filter(student -> student.getStudentId() == studentId).findFirst();
    //        if(optionalStudent.isEmpty()) {
    //            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID입니다."));
    //        }
    //        findStudent = optionalStudent.get();

        if (findStudent == null) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID입니다."));
            // .of를 쓰면 put안하고 key,value순으로 넣어줘서 쓸 수 있음.
        }

        return ResponseEntity.ok().body(findStudent);
    }
}
