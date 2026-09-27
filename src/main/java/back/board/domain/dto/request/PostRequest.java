package back.board.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PostRequest {
    @NotBlank
    private String content;

    @NotBlank
    private String title;
}
