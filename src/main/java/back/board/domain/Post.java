package back.board.domain;

import back.board.domain.dto.PostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "POST_POST")
public class Post extends BaseIdAndTime {

    @ManyToOne(fetch = LAZY)
    private User author;
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }


    public PostDto toDto() {
        return new PostDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                author.getId(),
                author.getNickname(),
                title,
                content
        );
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public void addComment(User author, String content) {
        Comment comment = new Comment(this, author, content);

        comments.add(comment);
    }

    public long count() {
        return comments.size();
    }
}