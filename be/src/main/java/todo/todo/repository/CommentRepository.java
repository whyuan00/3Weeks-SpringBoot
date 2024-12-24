package todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.todo.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
