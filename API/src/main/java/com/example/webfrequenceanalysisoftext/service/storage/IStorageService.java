package com.example.webfrequenceanalysisoftext.service.storage;

import com.example.webfrequenceanalysisoftext.exceptions.BusinessException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    void init();
    Resource loadAsResource(String filename);
    Path load(String filename);
}
