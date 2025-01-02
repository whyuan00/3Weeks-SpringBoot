package todo.todo.service.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.dto.response.ResponseDto;
import todo.todo.dto.response.UploadFileResponseDto;

public interface ProfileService {
    ResponseEntity<? super UploadFileResponseDto> uploadProfileImage(MultipartFile file, int userId); // 프로필 이미지 업로드
    ResponseEntity<Resource> getProfileImage(int userId); // 프로필 이미지 가져오기
    ResponseEntity<String> deleteProfileImage(int userId); // 프로필 이미지 삭제
}
