package com.repsy.ApiStorage.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void store(MultipartFile file, String path);
    byte[] retrieve(String path);
}
