package todo.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseCode;
import todo.todo.common.ResponseMessage;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    // 500 에러
    public static ResponseEntity<ResponseDto> databseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
    // 나머지 에러
    public static ResponseEntity<ResponseDto> error(String code, String message, HttpStatus status) {
        ResponseDto responseBody = new ResponseDto(code, message);
        return ResponseEntity.status(status).body(responseBody);
    }

}
