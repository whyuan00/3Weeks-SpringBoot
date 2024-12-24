package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseCode;
import todo.todo.common.ResponseMessage;

@Getter
public class SignupResponseDto extends ResponseDto{
    private SignupResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //dto만 봐도 어떤 값 반환해주는지 알기 위해서
    private static ResponseEntity<SignupResponseDto> success(){
        SignupResponseDto result = new SignupResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저네임 중복시 응답
    private static ResponseEntity<ResponseDto> duplicateEmail(){
        return ResponseDto.error(ResponseCode.DUPLICATE_USERNAME, ResponseMessage.DUPLICATE_USERNAME,HttpStatus.BAD_REQUEST);
    }
    // 닉네임 중복시 응답
    private static ResponseEntity<ResponseDto> duplicateNickname(){
        return ResponseDto.error(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME,HttpStatus.BAD_REQUEST);
    }




}
