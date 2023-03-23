package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStore {

    String getFullPath(String fileName);

    List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException;

    UploadFile storeFile(MultipartFile multipartFile) throws IOException;

    String createStoreFileName(String originalFileName);

    String extractExt(String originalFileName);
}
