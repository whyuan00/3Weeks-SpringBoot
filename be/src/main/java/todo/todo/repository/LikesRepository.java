package todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.todo.domain.Likes;
import todo.todo.repository.pk.LikesPk;

@Repository
public interface LikesRepository extends JpaRepository<Likes, LikesPk> {
}
