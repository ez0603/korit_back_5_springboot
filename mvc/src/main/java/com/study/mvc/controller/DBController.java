package com.study.mvc.controller;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.service.DbStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DBController {
    @Autowired
    private DbStudyService dbStudyService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody DBStudyReqDto dbStudyReqDto) {

        return ResponseEntity.ok(dbStudyService.createStudy(dbStudyReqDto));
    }

    @GetMapping("/select/study/{id}")
    public ResponseEntity<?> selectStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.findStudyByID(id));
    }

    @GetMapping("/select/study")    // ?name=이지언 => 파라미터로 가져오기 때문에 이런식으로 가져오게됨
    public  ResponseEntity<?> selectStudy(@RequestParam String name) {

        return ResponseEntity.ok(dbStudyService.findStudyByName(name));
    }

    @GetMapping("/select/studys")
    public ResponseEntity<?> selectStudyAll() {
        return ResponseEntity.ok(dbStudyService.findAll());
    }

    @DeleteMapping("/delete/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.deleteById(id));
    }

    @PutMapping("/update/study/{id}")
    public ResponseEntity<?> putStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
        // 전체 수정 {nickname: aaa, password: 1234} => {nickname: "", password: 1111} => 빈값도 그대로 저장  {nickname: "", password: 1111}

        return ResponseEntity.ok(dbStudyService.putById(id, dbStudyReqDto));
    }
    @PatchMapping("/update/study/{id}")
    public ResponseEntity<?> patchStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
        // 부분 수정 {nickname: aaa, password: 1234} => {nickname: "", password: 1111} => {nickname: aaa, password: 1111} 빈값은 수정 안됨

        return ResponseEntity.ok(dbStudyService.patchById(id, dbStudyReqDto));
    }
}
