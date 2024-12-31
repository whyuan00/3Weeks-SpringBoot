package todo.todo.service.service;

import org.springframework.http.ResponseEntity;
import todo.todo.dto.request.LoginRequestDto;
import todo.todo.dto.request.SignupRequestDto;
import todo.todo.dto.response.LoginResponseDto;
import todo.todo.dto.response.SignupResponseDto;

// 인터페이스 작성하면 다른 인증방식 추가하거나 의존성 주입해서 테스트 하기 쉬움
public interface AuthService {
    ResponseEntity<? super SignupResponseDto> signup(SignupRequestDto dto);
    ResponseEntity<? super LoginResponseDto> login(LoginRequestDto dto);

}
