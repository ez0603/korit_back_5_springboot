package com.study.mvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

// Configuration 특징 : Bean 수동 등록(2개 이상 등록 가능)

@Configuration // Configuration역할 = 1. 세팅,설정 2. Bean등록
public class BeanConfig { // config = 설정 관리

    @Bean // bean 등록 , 함수이름 = 컴포넌트 이름 / 리턴되는 값이 ioc에 등록됨, !@bean은 @Configuration이 있는곳에서만 사용가능, @component는 class위에서만 사용가능하기 때문에 @bean을 사용함
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
