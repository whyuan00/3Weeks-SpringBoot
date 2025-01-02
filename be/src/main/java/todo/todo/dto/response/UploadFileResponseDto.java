package todo.todo.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;

import java.time.LocalDateTime;

@Getter
public class UploadFileResponseDto extends ResponseDto{
    private Response response;

    private UploadFileResponseDto(Response response){
        super(ResponseStatus.SUCCESS, ResponseMessage.CREATED);
        this.response = response;
    }

    @Getter
    @Setter
    public static class Response{
        private String originalFileName;
        private String saveFileName;
        private String extension;
        private String filePath;
        private LocalDateTime uploadTime;

        public Response(String originalFileName, String saveFileName, String extension, String filePath, LocalDateTime uploadTime) {
            this.originalFileName = originalFileName;
                    this.saveFileName = saveFileName;
                    this.extension = extension;
                    this.filePath = filePath;
                    this.uploadTime = uploadTime;
        }

    }

    public static ResponseEntity<UploadFileResponseDto> uploadSuccess(Response response){
        UploadFileResponseDto responseDto = new UploadFileResponseDto(response);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
