package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class PostController {

    // HTML <Form>, ajax 검색 등에서 주로 POST사용
    // http post body에 data를 추가하여
    // json, xml, multipart-form / text-plain

    // @RequestMapping(method = RequestMethod.POST, path = "/postMethod")로도 가능
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
}
