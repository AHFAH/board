package back.board.config;


import back.board.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    // 비밀번호 암호화. Member 가입 시에도 반드시 이 Bean으로 encode() 해서 저장해야 함
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // UserDetailsService 등록
                .userDetailsService(customUserDetailsService)

                // 세션 기반 인증이므로 CSRF는 기본 활성화 상태로 둔다 (JWT와 달리 꺼서는 안 됨)
                // 폼 기반 로그인이 아닌 REST API로만 로그인 요청을 보낼 경우
                // 프런트에서 CSRF 토큰을 함께 전송해야 한다.
                // .csrf(csrf -> csrf.disable())  // ← REST API 전용이라면 필요 시에만 비활성화 고려

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/join", "/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // 폼 로그인 (로그인 파라미터명을 email로 지정)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")   // 실제 로그인 처리 URL (POST)
                        .usernameParameter("email")     // 로그인 아이디 = email
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                // 세션 관리: 세션이 있을 때만 사용, 최대 세션 1개로 제한 (중복 로그인 방지 예시)
                .sessionManagement((SessionManagementConfigurer<HttpSecurity> session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false) // true: 나중 로그인 차단, false: 기존 세션 만료
                );

                // 인증 안 된 상태로 API 접근 시 302 리다이렉트 대신 401을 주고 싶다면 아래처럼 예외 처리 분리 가능
//                .exceptionHandling(ex -> ex
////                        .defaultAuthenticationEntryPointFor(
////                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
////                                request -> request.getRequestURI().startsWith("/api/")
////                        )
////                );

        return http.build();
    }
}
