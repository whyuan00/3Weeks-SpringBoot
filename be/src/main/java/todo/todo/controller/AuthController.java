package todo.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todo.dto.request.LoginRequestDto;
import todo.todo.dto.request.SignupRequestDto;
import todo.todo.dto.response.LoginResponseDto;
import todo.todo.dto.response.ResponseDto;
import todo.todo.dto.response.SignupResponseDto;
import todo.todo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<? super SignupResponseDto> signup(
            @RequestBody @Valid SignupRequestDto requestBody
    ) {
        ResponseEntity<? super SignupResponseDto> response = authService.signup(requestBody);
        return response;
    }

    @PostMapping("/login") // @ResponseBody ResponseDto도 가능
    public ResponseEntity<? super LoginResponseDto> login(
        @RequestBody @Valid LoginRequestDto requestBody
    ) {
        ResponseEntity<? super LoginResponseDto> response = authService.login(requestBody);
        return response;
    }
}
//컨트롤러에는 비즈니스 로직이 작성되면 안됨.
//controller에는 리퀘스트 받아서 서비스 레이어로 전달하며,
//서비스 레이어에서 처리한 결과를 다시 클라이언트에 응답하는 역할을 함