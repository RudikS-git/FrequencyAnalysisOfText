package com.example.webfrequenceanalysisoftext.service;



import analyzetext.AnalyzerException;
import com.example.webfrequenceanalysisoftext.exceptions.BusinessException;
import com.example.webfrequenceanalysisoftext.domain.AnalysisResult;
import com.example.webfrequenceanalysisoftext.model.AnalysisResultDTO;

public interface IAnalysisService {
    AnalysisResultDTO analyze(String input) throws AnalyzerException, BusinessException;
    AnalysisResultDTO analyze(String ...input) throws  AnalyzerException, BusinessException;
    void create(AnalysisResult analysisResultDTO);
    void delete(Long id);
    Iterable<AnalysisResult> get();
    AnalysisResult getById(Long id);
}
