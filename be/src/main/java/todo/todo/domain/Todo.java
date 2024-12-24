package todo.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Todo") // 투두 엔티티 등록
@Table(name="todo") // 투두 테이블 생성
@NoArgsConstructor // lombok: 생성자 생략
@Getter // lombok: 게터 생성
@Setter // lombok: 세터 생성

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String createdAt;
    private String updatedAt;
    private int userId;
}
