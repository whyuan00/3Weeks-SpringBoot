package todo.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.config.CustomUser;
import todo.todo.dto.response.UploadFileResponseDto;
import todo.todo.service.service.ProfileService;

@RestController
@RequestMapping("/api/user/profileImage")
@RequiredArgsConstructor
public class ProfileImageController {

    private final ProfileService profileService;

    @GetMapping("")
    public ResponseEntity<Resource> getProfileImage(
        @RequestParam("userId") int userId
    ){
        return profileService.getProfileImage(userId);
    }

    @PostMapping("/upload")
    public ResponseEntity<? super UploadFileResponseDto> uploadProfileImage(
            @RequestParam("file")MultipartFile file,
            @AuthenticationPrincipal CustomUser customUser
//            @RequestParam("userId") int userId
//            @AuthenticationPrincipal Integer userId
            ){
        int userId = customUser.getUserId();
        return profileService.uploadProfileImage(file, userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProfileImage(
//            @RequestParam("userId") int userId
            @AuthenticationPrincipal CustomUser customUser
            ){
        int userId = customUser.getUserId();
        return profileService.deleteProfileImage(userId);
    }

}
