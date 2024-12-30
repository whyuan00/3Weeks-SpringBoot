package todo.todo.service;

import org.springframework.http.ResponseEntity;
import todo.todo.dto.response.GetUserResponseDto;
import todo.todo.dto.response.GetUsersResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserResponseDto> getUser(int userId);
    ResponseEntity<? super GetUsersResponseDto> getUsers();
}
