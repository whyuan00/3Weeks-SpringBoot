package todo.todo.dto.response;

import lombok.Getter;
import todo.todo.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserResponse{
    private String username;
    private String nickname;
    private LocalDateTime createdAt;
    private String profileImage;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.createdAt = user.getCreatedAt();
        this.profileImage = user.getProfileImage();
    }
}