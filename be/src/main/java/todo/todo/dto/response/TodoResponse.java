package todo.todo.dto.response;

import lombok.Getter;
import todo.todo.domain.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {
        private final int todoId;
        private final String title;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public TodoResponse(Todo todo) {
            this.todoId = todo.getTodoId();
            this.title = todo.getTitle();
            this.content = todo.getContent();
            this.createdAt = todo.getCreatedAt();
            this.updatedAt = todo.getUpdatedAt();
        }

}
