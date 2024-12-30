package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.User;

@Getter
public class GetUserResponseDto extends ResponseDto {
    private Object response;

  // 생성자
    private GetUserResponseDto(User user) {
        super(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS);
        UserResponse userResponse = new UserResponse(user);
        this.response = userResponse;
    }

    // 성공
    public static ResponseEntity<GetUserResponseDto> getUserSuccess(User user){
        GetUserResponseDto result = new GetUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 400 잘못된 요청 // 500 서버에러 // 401 권한 에러
    public static ResponseEntity<ResponseDto> notExistUser(){
        return ResponseDto.error(ResponseStatus.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER, HttpStatus.NOT_FOUND);
    }

}
