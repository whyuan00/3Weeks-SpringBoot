package todo.todo.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentList {
    private Long commentId;   // 댓글 ID
    private Long userId;      // 작성자 ID
    private Long todoId;   // 댓글 대상 콘텐츠 ID
    private String content;      // 댓글 내용
}
