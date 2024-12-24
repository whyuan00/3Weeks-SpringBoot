package todo.todo.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Comment") // 투두 엔티티 등록
@Table(name="comment") // 투두 테이블 생성
@NoArgsConstructor // lombok: 생성자 생략
@Getter // lombok: 게터 생성
@Setter // lombok: 세터 생성
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String content;
    private int todoId;
    private int userId;

}
