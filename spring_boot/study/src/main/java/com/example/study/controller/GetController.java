package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //localhost:8008/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String password) {
        System.out.println("id: " + id);
        System.out.println("passwrod: " + password);
        return id + password;
    }

    // r
    // localhost:8080/api/getMultiParameter?account=abcd&email=kim@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // http에서 기본 규격으로 대개 json을 사용하기 때문에 스프링 부트에서 객체를 리턴시
        // json관련 라이브러리가가 동으로 json format으로 변환시켜줌
        // { "account" : "", "email" : "", "page" : 0}
        return searchParam;
    }
}
