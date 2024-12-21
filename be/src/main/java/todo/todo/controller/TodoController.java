package todo.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todo.todo.domain.Todo;

import java.util.List;
import todo.todo.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자 생략
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{userId}")
    public List<Todo> getTodos(@PathVariable Integer userId) {
        return todoService.getTodosByUserId(userId);
    }

    @PostMapping("/")
    public Todo createTodo(@RequestBody Todo todo) {
        System.out.print("Post 요청 입력");
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Integer todoId) {
        todoService.deleteTodoById(todoId);
    }
}
