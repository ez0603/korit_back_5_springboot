package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class StudentReqDto {
    private String name;
    private int age;
    private String phone;
    private String address;

    public StudentRespDto toRespDto() {
        StudentRespDto studentRespDto = StudentRespDto.builder()
                .name(name)
                .age(age)
                .phone(phone)
                .address(address)
                .build();
        return studentRespDto;
    }
}
