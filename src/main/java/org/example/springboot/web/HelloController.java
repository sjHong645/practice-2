package org.example.springboot.web;

import org.example.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 간단한 API 예시
    // "/hello" 요청이 오면 문자열 hello를 반환하는 기능
    // 그래서 테스트 파일에서 "/hello"를 요청해서 반환되는 문자열이 hello가 맞는지 확인한 것 - HelloControllerTest 파일에서
    @GetMapping("/hello")
    public String hello() { return "hello"; }


    // "/hello/dto" 요청이 오면 HelloResponseDto 클래스를 반환.
    // 이때, name과 amount의 값은 파라미터 값으로 전달받은 HelloResponseDto를 반환한다.
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // 외부에서 넘긴 name(@RequestParam("name"))을 메소드의 매개변수 name(String name)에 저장
        // amount도 마찬가지

        return new HelloResponseDto(name, amount);

    }

}
