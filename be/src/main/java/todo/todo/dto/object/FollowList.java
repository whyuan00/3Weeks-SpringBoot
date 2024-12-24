package todo.todo.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowList {
    private Long followerId;   // 팔로우 하는 사용자 ID
    private Long followingId;  // 팔로우 당하는 사용자 ID
}
