package org.example.springboot.web;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.auth.LoginUser;
import org.example.springboot.config.auth.dto.SessionUser;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.service.posts.PostsService;
import org.example.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    // 머스테치 스타터 덕분에
    // 앞의 경로 src/main/resource/template 와 뒤 경로 .mustache가 자동으로 붙는다.
    // 여기서는 index를 반환하니까 src/main/resource/template/index.mustache로 전환된다.

    /*@GetMapping("/")
    public String index() { return "index"; } */
    // @GetMapping을 받는게 동일하다면 오류가 발생한다.
    // there is '***Controller' been existed.

    // /posts/save가 호출되면 ==> posts-save.mustache가 호출되는 메소드 추가
    // 때문에 위에서 만든 index.mustache와 같은 위치에 posts-save.mustache를 만들어야 한다.
    @GetMapping("/posts/save")
    public String postsSave() { return "posts-save"; }

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) model.addAttribute("userName", user.getName());

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


}
