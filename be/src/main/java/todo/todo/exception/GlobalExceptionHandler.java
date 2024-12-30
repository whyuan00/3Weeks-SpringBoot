package todo.todo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import todo.todo.dto.response.ResponseDto;

import java.util.List;

@RestControllerAdvice
@Slf4j // 롬복 log 라이브러리
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationException(
            MethodArgumentNotValidException ve,
            HttpServletRequest request)
    {
        // 어떤 api에서 문제 발생했는지 확인
        log.error("Validation error at {}, Method {},", request.getRequestURI(), request.getMethod());
        // 에러 발생한 필드도 확인
        List<FieldError> fieldErrors = ve.getBindingResult().getFieldErrors();
        for(FieldError error : fieldErrors) {
            log.error("Field: {}, Error: {}, Rejected value: {}",
                    error.getField(),
                    error.getDefaultMessage(),
                    error.getRejectedValue());
        }
        return ResponseDto.validationError();
    }
}
