package back.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
}