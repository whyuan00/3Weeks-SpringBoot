package todo.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostTodoRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private int userId;
}
//@NOtNull로 하면 빈배열은 인정함