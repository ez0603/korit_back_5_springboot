package com.study.mvc.diAndIoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component // @Component = ioc에 등록
//@RequiredArgsConstructor // 필수생성자 -> Autowired대신 씀 / 필수 신경안쓰고 Autowired가 여러개면 RequiredArgsConstructor로 자동주입(무조건True) 할 때도 있음 Autowired의 특성을 사용못함
public class IoCService {

    @Autowired //  @Autowired = 생성될 때 알아서 Di -> 생성(), 생성자 필요 X
    private IcCRepository iocRepository; // RequiredArgsConstructor는 final이 붙어있어야 사용 가능
    public String getJson() throws JsonProcessingException {
        Map<String, String> nameMap = iocRepository.convertNameMap();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(nameMap); // Map을 JSON으로 바꾸는 문장
    }
}
