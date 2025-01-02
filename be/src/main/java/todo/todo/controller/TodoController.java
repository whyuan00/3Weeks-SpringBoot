package todo.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import todo.todo.config.CustomUser;
import todo.todo.domain.Todo;
import todo.todo.dto.request.PostTodoRequestDto;
import todo.todo.dto.response.GetTodoResponseDto;
import todo.todo.dto.response.GetTodosResponseDto;
import todo.todo.dto.response.PostTodoResponseDto;
import todo.todo.dto.response.ResponseDto;
import todo.todo.service.service.TodoService;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("") //특정 게시글 조회 @RequestParam todoId
    public ResponseEntity<? super GetTodoResponseDto> getTodo(
        @RequestParam int todoId
    ) {
        ResponseEntity<? super GetTodoResponseDto> response = todoService.getTodo(todoId);
        return response;
    }

    @PostMapping("/create")
    public ResponseEntity<? super PostTodoResponseDto> postTodo(
            @RequestBody @Valid PostTodoRequestDto requestbody,
            @AuthenticationPrincipal CustomUser customUser
            ){
        String username = customUser.getUsername();
        int userId = customUser.getUserId();
        ResponseEntity<? super PostTodoResponseDto> response = todoService.postTodo(requestbody, username,userId);
        return response;
    }

    @GetMapping("/findAll") //모든 게시글 조회
    public ResponseEntity<?super GetTodosResponseDto> getTodos(
    ){
        ResponseEntity<?super GetTodosResponseDto> response = todoService.getTodos();
        return response;
    }

    @DeleteMapping("/delete") // @RequestParam todoId 특정 게시글 삭제
    public ResponseEntity<ResponseDto> deleteTodo(
            @RequestParam int todoId
    ) {
        return todoService.deleteTodo(todoId);
    }
//    @PutMapping("/update")@RequestParam todoId 특정 게시글 수정

}
