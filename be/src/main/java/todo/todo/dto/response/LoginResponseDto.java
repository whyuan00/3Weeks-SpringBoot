package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseStatus;
import todo.todo.common.ResponseMessage;

@Getter
public class LoginResponseDto extends ResponseDto{
    // ResponseDto 가 code + msg 기본 응답 타입
    private String token;
    private int expirationTime;

    // 생성자 설정해서 객체생성 로직 제한
    private LoginResponseDto(String token){
        super(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS);
        this.token=token;
        this.expirationTime = 3600; // 한시간 제한,provider에서 확인가능
    }

    // 로그인 성공시 로직
    public static ResponseEntity<LoginResponseDto> loginSuccess(String token){
        LoginResponseDto result = new LoginResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인 실패시
    // username 에러
    public static ResponseEntity<ResponseDto> loginFailedInvalidUsername(){
        return ResponseDto.error(ResponseStatus.SIGN_IN_FAIL, ResponseMessage.USERNAME_ERROR, HttpStatus.UNAUTHORIZED);
    }
    // passowrd mismatch
    public static ResponseEntity<ResponseDto> loginFailedIncorrectPassword(){
        return ResponseDto.error(ResponseStatus.SIGN_IN_FAIL, ResponseMessage.PASSWORD_MISMATCH, HttpStatus.UNAUTHORIZED);
    }

}
