package todo.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseStatus;
import todo.todo.common.ResponseMessage;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> success (String code, String message, HttpStatus status) {
        ResponseDto responseBody = new ResponseDto(code, message);
        return ResponseEntity.status(status).body(responseBody);
    }

    // 500 에러
    public static ResponseEntity<ResponseDto> databseError(){
        ResponseDto responseBody = new ResponseDto(ResponseStatus.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
    // 400 validation에러 (
      public static ResponseEntity<ResponseDto> validationError(){
        ResponseDto responseBody = new ResponseDto(ResponseStatus.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    // 나머지 에러
    public static ResponseEntity<ResponseDto> error(String code, String message, HttpStatus status) {
        ResponseDto responseBody = new ResponseDto(code, message);
        return ResponseEntity.status(status).body(responseBody);
    }


}
