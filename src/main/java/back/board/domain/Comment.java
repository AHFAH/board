package back.board.domain;

import back.board.domain.dto.CommentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "POST_POST_COMMENT")
public class Comment extends BaseIdAndTime {

    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY)
    private User author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Comment(Post post, User author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
    }


    public CommentDto toDto() {
        return new CommentDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                post.getId(),
                author.getId(),
                author.getNickname(),
                content
        );
    }
}