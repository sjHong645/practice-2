package org.example.springboot.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity { // 실제 DB 테이블과 매칭될 클래스. 보통 entity 클래스라고 함.
                     // 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 명확히 구분할 수 없기 때문에 entity에서는 Setter를 만들지 않는다.

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK = 기본 키의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false) // 사이즈를 500으로 늘리기 위해 사용함
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입을 TEXT로 변경하기 위해 사용함
    private String content;

    private String author;

    // 기본적으로 '생성자'를 통해서 값을 채운 후에 DB에 삽입할 것이다. 변경이 필요하다면 그에 맞는 public 메소드를 호출하면 된다.
    // 생성자 대신에 @Builder에서 제공하는 빌더 클래스를 사용할 것이다.
    // 생성자나 빌더는 값을 채워주는 것은 똑같지만 빌더는 어떤 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.
    @Builder
    public Posts(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content) {

        this.title = title;
        this.content = content;

    }

}
