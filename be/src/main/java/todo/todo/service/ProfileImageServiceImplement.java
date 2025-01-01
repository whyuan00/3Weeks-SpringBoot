package todo.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.domain.User;
import todo.todo.repository.UserRepository;
import todo.todo.service.service.ProfileService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<String> uploadProfileImage(MultipartFile file, int userId) {
        System.out.println("here: "+userId);
        if (file.isEmpty()) return ResponseEntity.badRequest().body("file is empty");

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
            return ResponseEntity.internalServerError().body("failed to save file");
        }
        String url = fileUrl + username + "/" + saveFileName;
        return ResponseEntity.status(HttpStatus.OK).body(url);
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
