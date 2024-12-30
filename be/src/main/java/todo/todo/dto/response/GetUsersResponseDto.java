package todo.todo.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todo.todo.common.ResponseMessage;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetUsersResponseDto extends ResponseDto {
    private List<UserResponse> response;

    private GetUsersResponseDto(List<User> users) {
        super(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS);
        this.response = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetUsersResponseDto> getUsersSuccess(List<User> users) {
        GetUsersResponseDto result = new GetUsersResponseDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}