package back.board.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class JoinRequest {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;

    @NotBlank
    String nickname;
}
