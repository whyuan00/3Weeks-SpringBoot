package todo.todo.repository.pk;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikesPk implements Serializable {
    @Column(name="userId")
    private int userId;
    @Column(name="todoId")
    private int todoId;
// 복합키의 경우 새로운 타입을 생성해서 JPArepo에 인지로 넣어줌
}
