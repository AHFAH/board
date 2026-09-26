package back.board.controller;

import back.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인 페이지 자체는 formLogin().loginPage("/login")에서 뷰로 처리하는 것을 권장.
    // 여기서는 회원가입 API만 예시로 제공.
    @PostMapping("/join")
    public String join(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nickname
    ) {
        authService.join(email, password, nickname);
        return "회원가입이 완료되었습니다. /login 에서 로그인하세요.";
    }

    // 현재 로그인 여부 확인용 (403/401 대신 명시적으로 확인하고 싶을 때)
    @GetMapping("/login")
    public String loginPage() {
        return "로그인 페이지 (실제로는 loginPage()에 지정한 뷰/템플릿으로 대체)";
    }
}
