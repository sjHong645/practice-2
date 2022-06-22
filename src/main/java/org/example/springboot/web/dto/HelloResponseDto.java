package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    // 둘 다 final로 선언했다.
    // 그러면 @RequiredArgsConstructor의 기능 때문에 final로 선언한 변수가 포함된 생성자를 생성해준다.
    private final String name;
    private final int amount;

}
