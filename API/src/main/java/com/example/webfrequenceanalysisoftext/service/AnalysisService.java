package com.example.webfrequenceanalysisoftext.service;


import analyzetext.*;


import analyzetext.frequency.Frequency;
import analyzetext.letterfinder.LetterFinder;
import analyzetext.letterfinder.ThreeLetterFinder;
import analyzetext.letterfinder.TwoLettersFinder;
import analyzetext.numbersFinder.NumbersFinder;
import analyzetext.wordFinder.WordFinder;
import com.example.webfrequenceanalysisoftext.exceptions.BusinessException;
import com.example.webfrequenceanalysisoftext.domain.AnalysisResult;
import com.example.webfrequenceanalysisoftext.model.AnalysisResultDTO;
import com.example.webfrequenceanalysisoftext.repository.AnalysisResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService implements IAnalysisService {

    @Autowired
    private AnalysisResultRepository analysisResultRepository;

    @Autowired
    private ApplicationContext context;

    @Override
    public AnalysisResultDTO analyze(String ...input) throws  AnalyzerException, BusinessException {
        return analyze(String.join(" ", input));
    }

    @Override
    public AnalysisResultDTO analyze(String input) throws AnalyzerException, BusinessException {
        try {
            IAnalyzer analyzer = context.getBean(Analyzer.class);
            analyzer.setFrequency(new Frequency());
            analyzer.setInputText(input);
            var analysisResult = new AnalysisResultDTO();

            Iterable<Letter> letters = analyzer.getLetters(new LetterFinder());
            Iterable<Letter> twoLetters = analyzer.getLetters(new TwoLettersFinder());
            Iterable<Letter> threeLetters = analyzer.getLetters(new ThreeLetterFinder());
            Iterable<Word> words = analyzer.getWords(new WordFinder());

            analysisResult.setLetters(letters);
            analysisResult.setTwoLetters(twoLetters);
            analysisResult.setThreeLetters(threeLetters);
            analysisResult.setWords(words);

            analysisResult.setAmountSymbols(Long.valueOf(input.length()));
            analysisResult.setAmountNumbers(analyzer.getAmountDigits(new NumbersFinder()));

            return analysisResult;
        }
        catch (AnalyzerException analyzerException) {
            throw new BusinessException(analyzerException.getMessage());
        }
    }

    @Override
    public void create(AnalysisResult analysisResult) {
        analysisResultRepository.save(analysisResult);
    }

    @Override
    public void delete(Long id) {
        analysisResultRepository.deleteById(id);
    }

    @Override
    public Iterable<AnalysisResult> get() {
        return analysisResultRepository.findAll();
    }

    @Override
    public AnalysisResult getById(Long id) {
        return analysisResultRepository.findById(id).get();
    }
}
