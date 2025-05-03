package com.repsy.ApiStorage.controller;

import com.repsy.ApiStorage.Database.Mapper.PackageServiceImpl;
import com.repsy.ApiStorage.storage.StorageService;
import com.repsy.ApiStorage.storage.StorageServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class PackageController {

    private final StorageServiceFactory storageServiceFactory;
    private final PackageServiceImpl packageServiceImpl;

    public PackageController(StorageServiceFactory storageServiceFactory,
                             PackageServiceImpl packageServiceImpl) {
        this.storageServiceFactory = storageServiceFactory;
        this.packageServiceImpl = packageServiceImpl;
    }

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<String> uploadPackage(@PathVariable String packageName,
                                                @PathVariable String version,
                                                @RequestParam("file")
                                                MultipartFile file) {
System.out.println("hello minio");
        String filename = file.getOriginalFilename();

        if (!filename.endsWith(".rep") && !filename.endsWith(".json")) {
            return ResponseEntity
                    .badRequest()
                    .body("Sadece .rep ve .json dosyaları yüklenebilir!");
        }

        StorageService storageService = storageServiceFactory.getStorageService();

        storageService.store(file, packageName +
                "/" + version + "/" + file.getOriginalFilename());
        String message = "File uploaded successfully to MinIO.";

        if (filename.endsWith(".json")) {
            try {
                packageServiceImpl.savePackageFromFile(file);

                message += " JSON file also saved to database.";
            } catch (IOException e) {

                System.err.println("JSON dosyası veritabanına kaydedilemedi: " + e.getMessage());
                message += " However, JSON file could not be saved to database.";
            }
        }

        return ResponseEntity.status(HttpStatus.
                CREATED).body(message);
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<byte[]> downloadPackage(@PathVariable String packageName,
                                                  @PathVariable String version,
                                                  @PathVariable String fileName) {
        StorageService storageService = storageServiceFactory.getStorageService();
        byte[] fileContent = storageService.retrieve(packageName +
                "/" + version + "/" + fileName);
        return ResponseEntity.ok(fileContent);
    }

    }

