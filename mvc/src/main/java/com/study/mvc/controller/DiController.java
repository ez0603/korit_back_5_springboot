package com.study.mvc.controller;

import com.study.mvc.diAndIoc.DiRepository;
import com.study.mvc.diAndIoc.DiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiController {

    /*
    *   DI(Dependency Injection) - 의존성 주입
    *
    * */
    @GetMapping("/di")
    public ResponseEntity<?> diTest() {
        DiRepository diRepository = new DiRepository(); // 컨트롤러에서 쓰기 위해서가 아닌 서비스가 의존하고 있기 때문에 생성
        DiService diService = new DiService(diRepository); // 의존관계이기 때문에 생성될때 넘겨줌(서비스가 레파지토리를 의존)
        Map<String, Object> responseData =
                Map.of(
                        "total", diService.getTotal(),
                        "average", diService.getAverage()
                );
        return ResponseEntity.ok(responseData); // .ok = (body 바로 넣기 가능)
    }
}
