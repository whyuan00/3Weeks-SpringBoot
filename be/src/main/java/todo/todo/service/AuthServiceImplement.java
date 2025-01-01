package todo.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.todo.config.JwtProvider;
import todo.todo.domain.User;
import todo.todo.dto.request.LoginRequestDto;
import todo.todo.dto.request.SignupRequestDto;
import todo.todo.dto.response.LoginResponseDto;
import todo.todo.dto.response.ResponseDto;
import todo.todo.dto.response.SignupResponseDto;
import todo.todo.repository.UserRepository;
import todo.todo.service.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignupResponseDto> signup(SignupRequestDto dto) {
//        System.out.println("Singup method called");
       try{
        String username = dto.getUsername();
        boolean existedUsername = userRepository.existsByUsername(username);
        if (existedUsername) return SignupResponseDto.duplicateUsername();

        String nickname = dto.getNickname();
        boolean existedNickname = userRepository.existsByNickname(nickname);
        if (existedNickname) return SignupResponseDto.duplicateNickname();

        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        dto.setPassword(encodedPassword);

        User user = new User(dto);
        userRepository.save(user);
       }
       catch(Exception exception){
           exception.printStackTrace();;
           return ResponseDto.databseError();
       }
        return SignupResponseDto.success();
    }

    @Override
    public ResponseEntity<? super LoginResponseDto> login(LoginRequestDto dto) {
//        System.out.println("Login method called");
        String token = null;
        try{
            String username = dto.getUsername();
            User user = userRepository.findByUsername(username);
            if (user == null) return LoginResponseDto.loginFailedInvalidUsername();

            String password = dto.getPassword();
            String encodedPassword = user.getPassword();
            // 암호화된 비번이랑 입력된 비번 비교
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return LoginResponseDto.loginFailedIncorrectPassword();

            token = jwtProvider.create(user); // 토큰 생성

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
        return LoginResponseDto.loginSuccess(token);
    }
}
