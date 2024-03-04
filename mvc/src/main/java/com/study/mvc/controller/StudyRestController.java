package com.study.mvc.controller;

import com.study.mvc.dto.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
*   Controller명: StudentController
*
*   메소드명: getStudentInfo()
*   요청 메소드: Get
*   요청 URL: /student
*   요청 Params: name, age, phone, address
*   응답데이터: JSON(name, age, phone, address)
* */


@RestController // 모든 Controller은 데이터 응답용 (html 불가)
public class StudyRestController {
    @GetMapping("/hello/test")
    public String hello(HelloDto helloDto) { // 변수명과 키값이 일치하면 @RequestParam("name") 생략 가능 , 주소창은 문자열인데 형변환 할필요 X int를 자동으로 바꿔줌
        // 변수명과 키값이 일치하면 매개변수대신 객체 사용가능

        System.out.println(helloDto);

        return "Hello";
    }
}
