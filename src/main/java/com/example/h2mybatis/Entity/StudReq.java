package com.example.h2mybatis.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudReq {
    private String name;
    private String branch;
    private String email;
}
