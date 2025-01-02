package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.Todo;

@Getter
public class PostTodoResponseDto extends ResponseDto{
    private final TodoResponse response;

    private PostTodoResponseDto(Todo todo){
    super(ResponseStatus.SUCCESS, ResponseMessage.CREATED);
        this.response = new TodoResponse(todo);
    }
    public static ResponseEntity<PostTodoResponseDto> todoCreated(Todo todo){
        PostTodoResponseDto result = new PostTodoResponseDto(todo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistUser(){
        return ResponseDto.error(ResponseStatus.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER, HttpStatus.UNAUTHORIZED);
    }

}
