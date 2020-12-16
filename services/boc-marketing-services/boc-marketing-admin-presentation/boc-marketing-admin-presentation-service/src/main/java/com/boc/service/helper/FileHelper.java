package com.boc.service.helper;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileHelper {
    public String write(byte[] bytes, String directoryName, String fileName) {
        try {
            Files.createDirectories(Paths.get(directoryName));
            Path filePath = Paths.get(directoryName, fileName);
            Files.write(filePath, bytes);
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
