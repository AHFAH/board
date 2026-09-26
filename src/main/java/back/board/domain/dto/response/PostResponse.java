package back.board.domain.dto.response;

import back.board.domain.Post;
import back.board.domain.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponse {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private Long count;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<CommentDto> comments;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.nickname = post.getAuthor().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.count = post.count();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.comments = post.getComments().stream().map(CommentDto::new).toList();
    }
}
