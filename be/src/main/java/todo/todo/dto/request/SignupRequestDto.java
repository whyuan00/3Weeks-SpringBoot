package todo.todo.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank //필수
    private String username;
    @NotBlank
    private String password;
    private String nickname;

//    @NotNull @AssertTrue //true만받음
//    private Boolean agreedPerson;
}
