package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DbStudySelectRespDto {
    private int id;
    private String name;
    private int age;
}
