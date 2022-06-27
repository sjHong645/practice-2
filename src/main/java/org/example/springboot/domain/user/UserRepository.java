package org.example.springboot.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 소셜 로그인으로 반환되는 값 중
    // email을 통해 이미 생성된 사용자인지 vs 처음 가입한 사람인지
    // 판단하는 메소드
    Optional<User> findByEmail(String email);
}
