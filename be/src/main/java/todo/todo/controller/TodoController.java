package todo.todo.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todo.todo.domain.Todo;
import todo.todo.repository.TodoRepository;

import java.util.List;

@RestController //restapi 컨트롤러
@RequiredArgsConstructor // 생성자 생략
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping("/")
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }
}
