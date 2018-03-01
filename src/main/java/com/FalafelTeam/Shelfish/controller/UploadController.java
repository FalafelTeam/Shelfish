import com.FalafelTeam.Shelfish.service.LocalStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private LocalStorageService storage;

    @Autowired
    public UploadController(LocalStorageService storage) {
        this.storage = storage;
    }

    @PostMapping("/upload")
    public UploadResult uploadFile(@RequestParam("token") String token, @RequestParam MultipartFile file) {

        Long applicationid = jwtService.parseToken(token);

        if (applicationid == null)
            return new UploadResult(false);

        try {
            storage.upload(file);
            return new UploadResult(true);
        } catch (IOException e) {
            logger.error("File upload error", e);
            return new UploadResult(false);
        }
    }

    @GetMapping(value = "/uploads/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filename) throws IOException {
        File file = storage.get(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(Files.readAllBytes(file.toPath())));
    }
}