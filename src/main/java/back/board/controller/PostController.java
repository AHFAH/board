package back.board.controller;

import back.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mentoring")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
}
