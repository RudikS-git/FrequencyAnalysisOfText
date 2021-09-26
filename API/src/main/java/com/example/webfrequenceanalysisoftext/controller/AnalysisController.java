package com.example.webfrequenceanalysisoftext.controller;

import analyzetext.AnalyzerException;
import com.example.webfrequenceanalysisoftext.domain.AnalysisResult;
import com.example.webfrequenceanalysisoftext.model.AnalysisResultDTO;
import com.example.webfrequenceanalysisoftext.model.input.AnalysisData;
import com.example.webfrequenceanalysisoftext.service.IAnalysisService;

import com.example.webfrequenceanalysisoftext.exceptions.BusinessException;
import com.example.webfrequenceanalysisoftext.service.fileanalysis.IFileAnalysisService;
import com.example.webfrequenceanalysisoftext.service.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.Date;


@RestController
public class AnalysisController {

    private final IAnalysisService analysisService;
    private final IFileAnalysisService fileAnalysisService;
    private IStorageService storageService;

    @Autowired
    public AnalysisController(IAnalysisService analysisService, IStorageService storageService, IFileAnalysisService fileAnalysisService) {
        this.analysisService = analysisService;
        this.storageService = storageService;
        this.fileAnalysisService = fileAnalysisService;
    }

    @PostMapping(value = "/analyze")
    public AnalysisResultDTO analyzeText(@Valid @RequestBody AnalysisData analysisData) throws AnalyzerException, BusinessException {

        var analysisResult = analysisService.analyze(analysisData.getInputText());
        return analysisResult;
    }

    @PostMapping(value="/")
    public void save(@RequestBody AnalysisResult analysisResult) {

        analysisResult.setDateAnalysis(new Date());
        analysisService.create(analysisResult);
    }

    @DeleteMapping(value="/")
    public void delete(Long id) {
        analysisService.delete(id);
    }

    @GetMapping(value="/analysis")
    public Iterable<AnalysisResult> get() {

        return analysisService.get();
    }

    @GetMapping(value="/analysis-by-id")
    public AnalysisResult getById(Long id) {

        return analysisService.getById(id);
    }

    @GetMapping("/download-analysis-file")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(Long id) {
        var analysisResult = analysisService.getById(id);
        fileAnalysisService.createFile(analysisResult);
        Resource resource = storageService.loadAsResource(id.toString() + ".txt");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                .body(resource);
    }
}
