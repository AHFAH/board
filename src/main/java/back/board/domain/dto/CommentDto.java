package back.board.domain.dto;

import back.board.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long postId;
    private final Long authorId;
    private final String authorName;
    private final String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.createDate = comment.getCreateDate();
        this.modifyDate = comment.getModifyDate();
        this.postId = comment.getPost().getId();
        this.authorId = comment.getAuthor().getId();
        this.authorName = comment.getAuthor().getNickname();
        this.content = comment.getContent();
    }
}