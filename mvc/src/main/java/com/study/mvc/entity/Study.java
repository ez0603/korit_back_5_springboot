package com.study.mvc.entity;

import com.study.mvc.dto.DbStudySelectRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Study {
    private int id;
    private String name;
    private int age;
    private LocalDateTime createDate;

    public DbStudySelectRespDto toDto() {
        return DbStudySelectRespDto.builder()
                .id(id)
                .name(name)
                .age(age)
                .build();
    }
}
