package todo.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import todo.todo.dto.response.GetUserResponseDto;
import todo.todo.dto.response.GetUsersResponseDto;
import todo.todo.service.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<? super GetUserResponseDto> getUser(
            @AuthenticationPrincipal String username, //인증된 사용자 받기,filter의 context에서 꺼내옴
            @RequestParam int userId
    ){
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(userId);
        return response;
    }

    @GetMapping("/findAll")
    public ResponseEntity<? super GetUsersResponseDto> getUsers(
            @AuthenticationPrincipal String username
    ){
        return userService.getUsers();
    }

}
