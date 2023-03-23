package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStoreImpl implements FileStore{

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Override
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        // 다중 작업을 위해 UploadFile 을 List 에 담는다.
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
                // 각각의 multipartFile 에 대해 storeFile 메서드를 호출한다.
            }
        }

        return storeFileResult;
    }

    @Override
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFileName, storeFileName);
    }

    @Override
    public String createStoreFileName(String originalFileName) {
        // 서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString(); // 하지만.. 확장자는 갖고 가고 싶어..
        String ext = extractExt(originalFileName);
        return  uuid + "." + ext;
    }

    @Override
    public String extractExt(String originalFileName) {
        int position = originalFileName.lastIndexOf(".");// 위치를 가져올 수 있다
        return originalFileName.substring(position + 1);
    }
}
