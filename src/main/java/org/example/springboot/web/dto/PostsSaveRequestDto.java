package org.example.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    // Entity 클래스는 DB와 맞닿은 핵심 클래스.
    // 때문에 request, response 클래스로 사용하면 안된다.

    // request, response로 사용되는 dto는 view를 위한 클래스라고 자주 변경이 필요하지만
    // entity 클래스의 변경은 여러 클래스에 영향을 끼친다.

    // 때문에, view layer와 db layer의 역할 분리를 철저하게 하는 게 좋다.

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;

    }

    public Posts toEntity() {

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

    }
}
