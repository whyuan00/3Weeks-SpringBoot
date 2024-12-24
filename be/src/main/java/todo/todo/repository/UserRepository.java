package todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.todo.domain.User;

// JpaRepo 는 param으로 엔티티와 pk의 타입 받음
@Repository //의존성 주입 위해 등록
public interface UserRepository extends JpaRepository<User, Integer> {
}
