package todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.todo.domain.User;

// JpaRepo 는 param으로 엔티티와 pk의 타입 받음
@Repository //의존성 주입 위해 등록
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    // 쿼리 메서드 jpa 가 자동작성해줌
//    User findById(int userId); // jparepo가 제공하는 기본메서드임
    User findByUsername(String username);
}
