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

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignupResponseDto> signup(SignupRequestDto dto) {
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
        String token = null;
        try{
            String username = dto.getUsername();
            User user = userRepository.findByUsername(username);
            if (user == null) return LoginResponseDto.loginFailed();

            String password = dto.getPassword();
            String encodedPassword = user.getPassword();
            // 암호화된 비번이랑 입력된 비번 비교
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return LoginResponseDto.loginFailed();
            token = jwtProvider.create(username);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
        return LoginResponseDto.success(token);
    }
}
