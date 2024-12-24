package todo.todo.repository.pk;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowsPk {
    @Column(name="followerId")
    private int followerId;
    @Column(name="followingId")
    private int followingId;
}
