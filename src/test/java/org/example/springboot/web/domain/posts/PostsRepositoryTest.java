package org.example.springboot.web.domain.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 H2 DB를 자동으로 실행해 줌
public class PostsRepositoryTest { // PostsRepository의 save 기능과 findAll 기능을 테스트

    @Autowired
    PostsRepository postsRepository;

    // 단위 테스트가 끝날 때마다 수행되는 메소드 지정.
    // 보통은 배포하기 전에 전체 테스트를 수행할 때 데이터 침범을 막기 위해 사용
    @After
    public void cleanup() { postsRepository.deleteAll(); }

    @Test
    public void post_load() {

        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // @Builer를 이용해서 클래스를 생성 - save 기능
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("hongSJ")
                .build());

        // when - findAll 기능
        // 테이블 posts에 있는 모든 데이터를 조회하는 메소드
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0); // 데이터를 하나만 넣었으니까 0번째에 있는 값을 가져왔다.
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    // Jpa Auditing 테스트 코드 작성
    @Test
    public void BaseTimeEntity_saved() {

        // given
        LocalDateTime now = LocalDateTime.of(2022, 6, 23, 0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate()+ ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
