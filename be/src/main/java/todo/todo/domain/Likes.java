package todo.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import todo.todo.repository.pk.LikesPk;

@Entity // 투두 엔티티 등록
@Table(name="likes") // 투두 테이블 생성
@NoArgsConstructor // lombok: 생성자 생략
@Getter // lombok: 게터 생성
@Setter // lombok: 세터 생성
@IdClass(LikesPk.class) // 복합키 표시
public class Likes {
    @Id
    private int userId;
    @Id
    private int todoId;
}
