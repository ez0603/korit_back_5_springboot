package com.study.mvc.controller;

import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class StudyController {
    // ResponseBody가 있는것과 없는것의 차이점 잘 알아놓기
    // MVC
    @GetMapping("/hello") // GetMapping = 핸들러 어댑터 목록에 등록
    public String helloPage(Model model) {
//        model.addAttribute("name","이지언");
//        return new ModelAndView("hello"); // "hello = 파일명" ModelAndView = 파일명을 담기 위한 객체

        HelloModel helloModel = HelloModel.builder()
                .name1("이지언")
                .name2("삼지언")
                .name3("사지언")
                .build();

        model.addAttribute("helloModel", helloModel);
        return "hello";

        // 매개변수 Model 안받고 Map형태로
//        public ModelAndView helloPage() {
//        Map<String, Object> model = new HashMap<>();
//        model.put("name", "이지언");
//        return new ModelAndView("hello", model);
    }

    // REST
    @GetMapping("/test") // 404 뜨면 GetMapping에 오타 확인하기
    @ResponseBody // ResponseBody = Data 응답
    public Map<String, Object> testPage() { // mvc X, map으로 리턴하면 JSON으로 자동으로 바꿔줌(ResponseBody 필수)
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age", 26);
        return testObj;
    }
}
