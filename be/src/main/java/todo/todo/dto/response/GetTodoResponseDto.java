package todo.todo.dto.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.Todo;

@Getter
public class GetTodoResponseDto extends ResponseDto{
    private final TodoResponse response;

    private GetTodoResponseDto(Todo todo){
        super(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS);
        this.response = new TodoResponse(todo);
    }

    public static ResponseEntity<GetTodoResponseDto> getTodoSuccess(Todo todo){
        GetTodoResponseDto result = new GetTodoResponseDto(todo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
