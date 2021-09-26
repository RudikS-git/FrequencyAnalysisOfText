package com.example.webfrequenceanalysisoftext.repository;

import com.example.webfrequenceanalysisoftext.domain.AnalysisResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultRepository extends CrudRepository<AnalysisResult, Long> {

}