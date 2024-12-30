package todo.todo.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import todo.todo.config.JwtProvider;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // private의 생성자 만들어줌
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String token = parseBearerToken(request);
            if(token == null){
                filterChain.doFilter(request, response);
                return;
            }
            String username = jwtProvider.validate(token);
            if(username == null){
                filterChain.doFilter(request, response);
                return;
            }

        // 다 패스하면 context에 등록
        AbstractAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.NO_AUTHORITIES);
        // 웹 인증 세부 정보 설정
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 빈 context 추가
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        // 외부에서 사용할수 있도록 securityContextHolder에 현재 Context추가
        SecurityContextHolder.setContext(securityContext);

        }catch(Exception exception){
            exception.printStackTrace();
        }
        filterChain.doFilter(request, response);

    }
    // request에서 헤더 가져와서 헤더에 authorization key 찾고 bearer 인증 확인
    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
    // hasText는 스페이스거나 공백이면 false
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null; //
        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

//       여기까지 오면 Bearer 인증 방식 맞음, 7번 인덱스 부터 꺼내오기
        String token = authorization.substring(7);
//        System.out.println("token:"+token);
        return token;
    }

}
