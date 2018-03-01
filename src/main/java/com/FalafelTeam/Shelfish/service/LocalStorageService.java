import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class LocalStorageService {

    @Value("${storage.directory}")
    private String storageDir;

    public File upload(MultipartFile file) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        File local = new File(storageDir, timestamp + "_" + file.getOriginalFilename());

        file.transferTo(local);

        return local;
    }

    public File get(String filename) throws IOException {
        return Arrays.stream(Optional.ofNullable(new File(storageDir).listFiles()).orElse(new File[0]))
                .filter(f -> f.getName().endsWith(filename))
                .findAny()
                .orElse(null);
    }
}