package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    // 둘의 이름을 다르게 하는 이유
    // 두명이 같은 이름의 파일을 올렸을 시, 파일이 덮어지는 것을 방지하기 위해
    // storeFileName 은 유효 아이디로 설계하기 위함이다.
    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
