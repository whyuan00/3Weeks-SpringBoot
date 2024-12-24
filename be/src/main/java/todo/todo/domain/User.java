package todo.todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(name="user") //orm 어노테이션, 어떤 테이블과 mapping시킬지 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String nickname;
    private String createdAt;
    private String profileImage;
}
