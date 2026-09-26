package back.board.service;

import back.board.domain.User;
import back.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(String email, String rawPassword, String nickname) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다: " + email);
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.createUser(email, encodedPassword, nickname);
        return userRepository.save(user);
    }
}
