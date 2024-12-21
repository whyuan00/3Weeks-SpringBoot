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
    public String create(String email) {
//        한시간 토큰
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        return jwt;
    }

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
