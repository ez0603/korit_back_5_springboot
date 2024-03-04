package com.study.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 요청 Dto는 AllArgsConstructor, Builder을 잘 사용하지않음
//@AllArgsConstructor // 모든값이 필수값
//@NoArgsConstructor // get, set을 가지고 만듦
@Data
public class HelloDto {
    private String name;
    private int age;
}
