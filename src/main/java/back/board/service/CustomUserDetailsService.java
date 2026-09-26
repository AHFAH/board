package back.board.service;

import back.board.auth.CustomUserDetails;
import back.board.domain.User;
import back.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Spring Security의 loadUserByUsername 파라미터로 email이 들어온다
    // (SecurityConfig에서 loginProcessingUrl의 파라미터명을 email로 맞춰줌)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다: " + email));

        return new CustomUserDetails(user);
    }
}
