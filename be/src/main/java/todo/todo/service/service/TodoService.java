package todo.todo.service.service;

import org.springframework.http.ResponseEntity;
import todo.todo.dto.request.PostTodoRequestDto;
import todo.todo.dto.response.GetTodoResponseDto;
import todo.todo.dto.response.GetTodosResponseDto;
import todo.todo.dto.response.PostTodoResponseDto;
import todo.todo.dto.response.ResponseDto;

public interface TodoService {
    ResponseEntity<? super PostTodoResponseDto> postTodo(PostTodoRequestDto dto, String username,int userId);
    ResponseEntity<? super GetTodoResponseDto> getTodo(int todoId);
    ResponseEntity<? super GetTodosResponseDto> getTodos();
    ResponseEntity<ResponseDto> deleteTodo(int todoId);
}
