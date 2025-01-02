package todo.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.Todo;
import todo.todo.dto.request.PostTodoRequestDto;
import todo.todo.dto.response.*;
import todo.todo.repository.TodoRepository;
import todo.todo.repository.UserRepository;
import todo.todo.service.service.TodoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImplement implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super PostTodoResponseDto> postTodo(PostTodoRequestDto dto, String username,int userId) {
        try{
            boolean existedUsername = userRepository.existsByUsername(username);
            if (!existedUsername) return PostTodoResponseDto.notExistUser();

            // 투두도메인에서 dto받는 public 클래스 직접 생성
            Todo todo = new Todo(dto, username,userId);
            todoRepository.save(todo);
            return PostTodoResponseDto.todoCreated(todo);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTodoResponseDto> getTodo(int todoId) {
        try{
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isEmpty()) {
            return ResponseDto.error(ResponseStatus.NOT_EXIST_TODO, ResponseMessage.NOT_EXIST_TODO, HttpStatus.NOT_FOUND);
        }
            return GetTodoResponseDto.getTodoSuccess(todo.get());
        }catch (Exception e){
            e.printStackTrace();;
            return ResponseDto.databseError();
        }

    }

    @Override
    public ResponseEntity<? super GetTodosResponseDto> getTodos() {
        try{
            List<Todo> todos = todoRepository.findAll();
            System.out.println("todos"+todos);
            return GetTodosResponseDto.getTodosSuccess(todos);
        } catch(Exception e){
            e.printStackTrace();;
            return ResponseDto.databseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTodo(int todoId) {
        try{
            Optional<Todo> todo = todoRepository.findById(todoId);
            if (todo.isEmpty()) return ResponseDto.error(ResponseStatus.NOT_EXIST_TODO, ResponseMessage.NOT_EXIST_TODO, HttpStatus.NOT_FOUND);
            todoRepository.delete(todo.get());
            return ResponseDto.success(ResponseStatus.SUCCESS, ResponseMessage.DELETED, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databseError();
        }
    }
}
