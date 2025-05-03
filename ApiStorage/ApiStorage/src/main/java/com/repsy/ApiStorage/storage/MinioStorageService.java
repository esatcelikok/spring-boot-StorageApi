package com.repsy.ApiStorage.storage;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MinioStorageService implements StorageService {
    private final MinioClient minioClient;
    private final String bucketName;


    public MinioStorageService(MinioClient minioClient,
                               @Value("${minio.bucket-name}")
                               String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    @Override
    public void store(MultipartFile file, String path) {
        try {

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());

        } catch (Exception e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public byte[] retrieve(String path) {
        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .build()  )   ) {
            return inputStream.readAllBytes();
        } catch (Exception e) {
            throw new StorageException("Failed to retrieve file", e);
        }
    }
}


