package todo.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo.todo.dto.request.SignupRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users") //orm 어노테이션, 어떤 테이블과 mapping시킬지 지정
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String nickname;
    private String createdAt;
    private String profileImage;

    public User(SignupRequestDto dto){
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
    }
}
