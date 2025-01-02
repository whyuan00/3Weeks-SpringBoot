package todo.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.common.ResponseStatus;
import todo.todo.domain.User;
import todo.todo.dto.response.ResponseDto;
import todo.todo.dto.response.UploadFileResponseDto;
import todo.todo.repository.UserRepository;
import todo.todo.service.service.ProfileService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileImageServiceImplement implements ProfileService {

    @Value("${profile.path}")
    private String filePath;

    private File userDir;

    @Value("${file.url}")
    private String fileUrl;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Resource> getProfileImage(int userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String username = user.get().getUsername();
        String profileImage = user.get().getProfileImage();

        if (profileImage == null) {
            return ResponseEntity.notFound().build();
        }

        String extension = profileImage.substring(profileImage.lastIndexOf(".")).toLowerCase();
        MediaType mediaType = extension.equals(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

        try{
            Path imagePath = Paths.get(filePath + File.separator + username + File.separator + profileImage);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()){
                return ResponseEntity.ok().contentType(mediaType).body(resource);
            } else { return ResponseEntity.notFound().build();}

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<? super UploadFileResponseDto> uploadProfileImage(MultipartFile file, int userId) {

        if (file.isEmpty()) return ResponseDto.validationError();

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        String username = user.get().getUsername();

        userDir = new File(filePath + File.separator + username);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid+ extension;

        String savePath = userDir.getAbsolutePath() + File.separator + saveFileName;

        try{
            file.transferTo(new File(savePath));
            user.get().setProfileImage(saveFileName);
            userRepository.save(user.get());

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databseError();
        }
        String url = userDir + saveFileName;

        UploadFileResponseDto.Response response = new UploadFileResponseDto.Response(
            originalFileName,
                saveFileName,
                extension,
                savePath,
                LocalDateTime.now()
        );

        return UploadFileResponseDto.uploadSuccess(response);
    }

    @Override
    public ResponseEntity<String> deleteProfileImage(int userId) {
        try{
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            String username = user.get().getUsername();
            String profileImage = user.get().getProfileImage();

            if (profileImage == null) return ResponseEntity.notFound().build();

            Path imagePath = Paths.get(filePath + File.separator + username + File.separator + profileImage);
            Files.deleteIfExists(imagePath);

            user.get().setProfileImage(null);
            userRepository.save(user.get());

            return ResponseEntity.ok("profileimage deleted");

        }catch(Exception exception){
            exception.printStackTrace();;
            return ResponseEntity.internalServerError().body("delete failed");
        }
    }
}
