package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() // 이걸 선언해야 antMatchers를 사용할 수 있음
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상을 지정하는 옵션
                                                                                                                  // permitAll()을 통해 전체 열람 권한 부여
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // "/api/v1/**"는 USER 권한을 가진 사람만 API 가능
                    .anyRequest().authenticated() // 설정된 값 이외의 사용자들은 인증된 사용자들에게만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃을 성공했을 때 "/" 주소로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보들을 가져올 때 설정
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록

    }

}
