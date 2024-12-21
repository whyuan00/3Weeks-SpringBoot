package todo.todo.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FollowId implements java.io.Serializable {

    private Integer followerId;
    private Integer followingId;

    // equals() and hashCode() implementation
}
