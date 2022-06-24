package org.example.springboot.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Posts 클래스로 DB에 접근하게 해줄 JpaRepository
// 아래와 같이
// 1. 인터페이스를 생성하고나서
// 2. JpaRepository<Entity 클래스, PK 타입>를 사용하면 기본적인 CRUD 메소드가 자동으로 생성됨

// Entity 클래스와 Entity Repository는 같은 곳에 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
