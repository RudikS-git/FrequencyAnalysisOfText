package analyzetext;

import analyzetext.frequency.IFrequency;
import analyzetext.letterfinder.ILetterFinder;
import analyzetext.numbersFinder.INumbersFinder;
import analyzetext.wordFinder.IWordFinder;

import java.util.Map;
import java.util.Objects;

public class Analyzer<T extends IFrequency> implements IAnalyzer<T> {
    private String inputText;

    public T getFrequency() throws AnalyzerException {
        if(frequency == null) {
            throw new AnalyzerException("frequency mustn't be null");
        }

        return frequency;
    }

    public void setFrequency(T frequency) {

        this.frequency = frequency;
    }

    private T frequency;

    public Analyzer(String input) throws AnalyzerException {
        setInputText(input);
    }

    public Analyzer(String...inputs) throws AnalyzerException {
       setInputText(String.join(" ", inputs));
    }

    public Analyzer() throws AnalyzerException {
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String input) throws AnalyzerException {
        if(input == null || input == "") {
            throw new AnalyzerException("Input text mustn't be null or empty");
        }

        inputText = input;
    }

    @Override
    public Iterable<Letter> getLetters(ILetterFinder letterFinder) throws AnalyzerException {
        if(letterFinder == null) {
            throw new AnalyzerException("letterFinder mustn't be null");
        }

        return letterFinder.find(inputText, getFrequency());
    }

    public Iterable<Word> getWords(IWordFinder wordFinder) throws AnalyzerException {
        if(wordFinder == null) {
            throw new AnalyzerException("wordFinder mustn't be null");
        }

        return wordFinder.find(inputText, getFrequency());
    }

    public Long getAmountDigits(INumbersFinder numbersFinder) throws AnalyzerException {
        if(numbersFinder == null) {
            throw new AnalyzerException("numbersFinder mustn't be null");
        }

        return numbersFinder.count(inputText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analyzer analyzer = (Analyzer) o;
        return Objects.equals(inputText, analyzer.inputText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputText);
    }
}
