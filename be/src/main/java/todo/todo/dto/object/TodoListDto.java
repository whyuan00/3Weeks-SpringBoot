package todo.todo.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDto {
    private int todoId;
    private int userId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
}
