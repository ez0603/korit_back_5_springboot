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

import javax.naming.spi.ObjectFactoryBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException { // students = 쿠키의 키값 명과 일치 시켜줌
        ObjectMapper objectMapper = new ObjectMapper();  // ObjectMapper = 스프링부트에서의 JSON

        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        System.out.println(students);

        if(students != null) { // 쿠키가 비어있지 않으면 반복
            if(!students.isBlank()) {
                for(Object object : objectMapper.readValue(students, List.class)) { // JSON을 List로 바꿔줌
                    Map<String, Object> studentMap = (Map<String, Object>) object; // List로 바꿔주었기 때문에 student가 들어있는게 아닌 object가 들어있기 때문에 object로 꺼내줌 Map으로 다운캐스팅
                    studentList.add(objectMapper.convertValue(studentMap, Student.class)); // Map을 student객체로 바꿔주고 List에 넣어줌
                }
                lastId = studentList.get(studentList.size() - 1).getStudentId(); // List에 있는 마지막 인덱스를 가져와서 LastId에 넣어줌
            }
        }

        student.setStudentId(lastId + 1);
        studentList.add(student);

        String studentListJson = objectMapper.writeValueAsString(studentList); // writeValueAsString = 객체를 JSON으로 바꾸는것

        System.out.println(studentListJson);

        ResponseCookie responseCookie = ResponseCookie
                .from("students", URLEncoder.encode(studentListJson, "UTF-8")) // students = 쿠키의 키값과 변수명을 일치 시켜줌
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();

        // (")문자 저장x

        return ResponseEntity
                .created(null)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(student);  // created = post요청때 성공하면 201
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
