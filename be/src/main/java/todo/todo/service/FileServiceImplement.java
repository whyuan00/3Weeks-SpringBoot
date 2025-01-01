package todo.todo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.service.service.FileService;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

//    file.path=files/
//    file.url=http://localhost:8080/file/

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 마지막 점부터 가져오기
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        // 경로 절대경로로 바꾸어서 저장해주기
        String savePath = new File(filePath).getAbsolutePath() + File.separator + saveFileName;

        try{
            file.transferTo(new File(savePath));
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;

        try{ // 꺼낼 주소
            resource = new UrlResource("file:" + filePath + fileName);
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
}
