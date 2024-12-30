package todo.todo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// 제어역전을 통한 의존성주입??? di
@Component
public class JwtProvider {
//    application property에 작성하기
    @Value("${secret-key}")
    private String secretKey;

//    이메일 받아서 웹토큰으로 만들어줌
// HS256은 대칭키 기반 알고리즘, 하나의 비밀키만 가져서 속도 빠르고 토큰 비교적 작음
    public String create(String username) {
//        한시간 토큰
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(username).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        return jwt;
    }
//ES256은 비대칭키 알고리즘. 비밀키로 서명생성/공개키로 검증해서 보안 수준 높음. 토큰크기 작고 성능 우수
// 공개api, 다중사용자 환경에서 주로 사용

// jwt 검증 메서드
    public String validate(String jwt){
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }

}
