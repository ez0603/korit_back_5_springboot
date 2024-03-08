package com.study.mvc.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
// 컴포넌트 이름과 필드명이 일치하면 자동주입
@Repository("a")
public class CarRepositoryImpl implements CarRepository {

    @Override
    public List<String> getCarNames() {
        return List.of("아반떼", "쏘나타");
    }

    @Override
    public int insertCar(String carName) {
        System.out.println("등록된 차량: " + carName);
        return 1;
    }
}
