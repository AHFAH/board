package back.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "AUTH_USER")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    // 로그인 아이디로 사용하는 이메일. 반드시 unique해야 함
    @Column(unique = true, nullable = false)
    private String email;

    // BCrypt 등으로 암호화된 비밀번호만 저장 (평문 저장 금지)
    @Column(nullable = false)
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User(String email, String encodedPassword, String nickname, Role role) {
        this.email = email;
        this.password = encodedPassword;
        this.nickname = nickname;
        this.role = role;
    }

    public static User createUser(String email, String encodedPassword, String nickname) {
        return new User(email, encodedPassword, nickname, Role.USER);
    }
}
