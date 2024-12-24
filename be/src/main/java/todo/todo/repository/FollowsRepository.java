package todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.todo.domain.Follows;
import todo.todo.repository.pk.FollowsPk;

public interface FollowsRepository extends JpaRepository<Follows, FollowsPk> {
}
