package com.study.mvc.controller;

import com.study.mvc.exception.DuplicatedException;
import com.study.mvc.service.DbStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExceptionController {
    @Autowired
    private DbStudyService dbStudyService;

    @PostMapping("/ex")
    public ResponseEntity<?> duplication(@RequestBody Map<String, String> map) {
        dbStudyService.checkDuplicatedByName(map.get("name"));

        return ResponseEntity.ok("중복되지 않은 이름입니다.");
    }
}
