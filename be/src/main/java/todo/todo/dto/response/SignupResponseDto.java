package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseStatus;
import todo.todo.common.ResponseMessage;

@Getter
public class SignupResponseDto extends ResponseDto{
    private SignupResponseDto(){
        super(ResponseStatus.CREATED, ResponseMessage.CREATED);
    }

    //dto만 봐도 어떤 값 반환해주는지 알기 위해서
    public static ResponseEntity<SignupResponseDto> success(){
        SignupResponseDto result = new SignupResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저네임 중복시 응답
    public static ResponseEntity<ResponseDto> duplicateUsername(){
        return ResponseDto.error(ResponseStatus.DUPLICATE_USERNAME, ResponseMessage.DUPLICATE_USERNAME,HttpStatus.CONFLICT);
    }
    // 닉네임 중복시 응답
    public static ResponseEntity<ResponseDto> duplicateNickname(){
        return ResponseDto.error(ResponseStatus.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME,HttpStatus.CONFLICT);
    }


}
