package todo.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todo.todo.domain.User;
import todo.todo.dto.response.GetUserResponseDto;
import todo.todo.dto.response.GetUsersResponseDto;
import todo.todo.dto.response.ResponseDto;
import todo.todo.repository.UserRepository;
import todo.todo.service.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    // 의존성 주입
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(int userId) {
        try {
            // jpa가 제공하는 findbyId의 반환타입은 Optional
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return GetUserResponseDto.notExistUser();
            }
            return GetUserResponseDto.getUserSuccess(userOptional.get());
        }
        catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUsersResponseDto> getUsers(){
        try{
            List<User> users= userRepository.findAll();
            return GetUsersResponseDto.getUsersSuccess(users);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
    }




}
