package back.board.service;

import back.board.auth.CustomUserDetails;
import back.board.domain.Post;
import back.board.domain.User;
import back.board.domain.dto.request.PostRequest;
import back.board.domain.dto.response.PostResponse;
import back.board.repository.CommentRepository;
import back.board.repository.PostRepository;
import back.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    // 게시글 생성 - 인증 필요
    /*
    exception 설정
    */

    public void writePost(CustomUserDetails userDetails, PostRequest request) {
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isEmpty()) {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }


    }

    // 게시글 목록 리스트로 반환
    public List<PostResponse> postList() {
        List<Post> posts = postRepository.findAllByOrderByCreatedDateDesc();
        List<PostResponse> postResponses = new ArrayList<>();

        posts.forEach(post -> {
            PostResponse response = new PostResponse(post);
            postResponses.add(response);
        });
        return postResponses;
    }

    // 특정 게시글 조회 ** exception 나중에 수정
    public PostResponse readPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));

        return new PostResponse(post);
    }


}
