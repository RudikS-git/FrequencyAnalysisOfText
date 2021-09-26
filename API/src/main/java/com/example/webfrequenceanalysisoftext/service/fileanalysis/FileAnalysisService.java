package com.example.webfrequenceanalysisoftext.service.fileanalysis;

import com.example.webfrequenceanalysisoftext.domain.AnalysisResult;
import com.example.webfrequenceanalysisoftext.service.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileAnalysisService implements IFileAnalysisService {
    private final Path rootLocation;

    @Autowired
    public FileAnalysisService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public String createFile(AnalysisResult analysisResult) {
        String fileName= analysisResult.getId() + ".txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rootLocation.resolve(fileName).toString())))
        {
            var text = String.format(
                    "id=%d\n\rname=%s\n\rtext=%s\n\rdate=%s\n\ramount-number=%d\n\ramount-symbols=%d,\n\ramount-unique-letters=%d\n\ramount-unique-two-letters=%d,\n\r" +
                            "amount-unique-three-letters=%d,\n\ramount-unique-words=%d,",
                    analysisResult.getId(),
                    analysisResult.getTextName(),
                    analysisResult.getText(),
                    analysisResult.getDateAnalysis().toString(),
                    analysisResult.getAmountNumbers(),
                    analysisResult.getAmountSymbols(),
                    analysisResult.getLetters().size(),
                    analysisResult.getTwoLetters().size(),
                    analysisResult.getThreeLetters().size(),
                    analysisResult.getWords().size()
            );

            bw.write(text);

            return fileName;
        }
        catch(IOException ex){
            throw new FileAnalysisException(ex.getMessage(), ex);
        }
    }
}

