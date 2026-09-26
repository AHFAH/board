package back.board.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
// 모든 엔티티들의 조상
public abstract class BaseEntity{
    public abstract Long getId();

    public abstract LocalDateTime getCreateDate();

    public abstract LocalDateTime getModifyDate();
}