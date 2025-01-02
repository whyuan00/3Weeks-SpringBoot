package todo.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import todo.todo.dto.request.PostTodoRequestDto;
import todo.todo.dto.response.PostTodoResponseDto;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity// 투두 엔티티 등록
@Table(name="todos") // 투두 테이블 생성
@NoArgsConstructor // lombok: 생성자 생략
@Getter // lombok: 게터 생성
@Setter // lombok: 세터 생성
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private int userId;

    // 엔티티 업데이트 전 호출
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

//    Date now = Date.from(Instant.now());
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String createdAt = simpleDateFormat.format(now);

    public Todo(PostTodoRequestDto dto, String username, int userId){
        this.userId = userId;
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

}
