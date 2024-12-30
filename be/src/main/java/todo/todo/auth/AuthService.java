package todo.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean authenticate(String username, String password) {
        // 실제 인증 로직 (DB 연결 또는 외부 서비스 사용 가능)
        return "user".equals(username) && "password".equals(password);
    }
}
