package todo.todo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import todo.todo.repository.pk.FollowsPk;

@Entity// 투두 엔티티 등록
@Table(name="follows") // 투두 테이블 생성
@NoArgsConstructor // lombok: 생성자 생략
@Getter // lombok: 게터 생성
@Setter // lombok: 세터 생성
@IdClass(FollowsPk.class) // 복합키 표시
public class Follows {
    @Id
    private int followerId;
    @Id
    private int followingId;
}
