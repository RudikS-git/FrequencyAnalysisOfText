package analyzetext;

import analyzetext.frequency.IFrequency;
import analyzetext.letterfinder.ILetterFinder;
import analyzetext.numbersFinder.INumbersFinder;
import analyzetext.wordFinder.IWordFinder;

import java.util.Map;

public interface IAnalyzer<T extends IFrequency>{
    Iterable<Letter> getLetters(ILetterFinder letterFinder) throws AnalyzerException;
    Iterable<Word> getWords(IWordFinder wordFinder) throws AnalyzerException;
    Long getAmountDigits(INumbersFinder numbersFinder) throws AnalyzerException;

    String getInputText();
    void setInputText(String input) throws AnalyzerException;

    public T getFrequency() throws AnalyzerException;
    public void setFrequency(T frequency);
}
