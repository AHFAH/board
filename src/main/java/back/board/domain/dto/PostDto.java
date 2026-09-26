package back.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long authorId;
    private final String authorName;
    private final String title;
    private final String content;
}