package com.study.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.repository.CarRepository;
import com.study.mvc.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Service를 다고 들어가면 component가 들어가있음
public class CarServiceImpl implements CarService{
    @Autowired
    @Qualifier("a") // @Qualifier("컴포넌트명") = 여러개의 컴포넌트가 있을 때 선택해서 가져올 수 있음
    private CarRepository carRepository; // 인터페이스 쓰는이유 : 인터페이스를 쓰는것끼리 타입변환 가능(ex.업캐스팅)

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getCarNames() {
        return String.join(", ", carRepository.getCarNames()); // ,(쉼표)로 구분하여 문자열
        // 출력결과 = 문자열: k3, k5
    }

    @Override
    public int addCar(String carName) {
        return 0;
    }
}
