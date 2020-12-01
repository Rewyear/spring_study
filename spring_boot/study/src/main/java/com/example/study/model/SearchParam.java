package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 멤버변수를 인자로 갖는 생성자 생성
public class SearchParam {
    private String account;
    private String email;
    private int page;
}
