package todo.todo.dto.response;

import com.sun.net.httpserver.HttpsServer;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.Todo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetTodosResponseDto extends ResponseDto{
    private final List<TodoResponse> response;

    private GetTodosResponseDto(List<Todo> todos){
        super(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS);
        this.response = todos.stream()
                .map(TodoResponse::new)
                .collect(Collectors.toList());
    }
    public static ResponseEntity<GetTodosResponseDto> getTodosSuccess(List<Todo> todos){
        GetTodosResponseDto result = new GetTodosResponseDto(todos);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
