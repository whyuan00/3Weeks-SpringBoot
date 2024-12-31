package todo.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import todo.todo.service.service.FileService;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(
        @RequestParam("file") MultipartFile file
    ){
        String url = fileService.upload(file);
        return url;
    }

    // 파일이름 요청을 동적으로 처리 produces: 반환할 타입 설정
    @GetMapping(value="{fileName}", produces={MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage( // pathVariable url 경로에서 파일이름 추출해서 fileName 으로 지정
            @PathVariable("fileName") String fileName
    ){
        Resource resource = fileService.getImage(fileName);
        return resource;
    }
}
