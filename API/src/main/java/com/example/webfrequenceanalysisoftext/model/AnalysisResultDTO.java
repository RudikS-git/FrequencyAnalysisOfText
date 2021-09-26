package com.example.webfrequenceanalysisoftext.model;

import analyzetext.Letter;
import analyzetext.Word;

public class AnalysisResultDTO {
    private Iterable<Letter> letters;
    private Iterable<Letter> twoLetters;
    private Iterable<Letter> threeLetters;
    private Iterable<Word> words;

    private Long amountNumbers;
    private Long amountSymbols;

    public Long getAmountNumbers() {
        return this.amountNumbers;
    }

    public Iterable<Letter>  getLetters() {
        return this.letters;
    }

    public Iterable<Letter>  getTwoLetters() {
        return this.twoLetters;
    }

    public Iterable<Letter>  getThreeLetters() {
        return this.threeLetters;
    }

    public Iterable<Word>  getWords() {
        return this.words;
    }

    public Long getAmountSymbols() { return this.amountSymbols; }

    public void setAmountNumbers(Long amountNumbers) {
        this.amountNumbers = amountNumbers;
    }

    public void setLetters(Iterable<Letter> letters) {
        this.letters = letters;
    }

    public void setTwoLetters(Iterable<Letter> twoLetters) {
        this.twoLetters = twoLetters;
    }

    public void setThreeLetters(Iterable<Letter> threeLetters) {
        this.threeLetters = threeLetters;
    }

    public void setWords(Iterable<Word> words) {
        this.words = words;
    }

    public void setAmountSymbols(Long amountSymbols) {
        this.amountSymbols = amountSymbols;
    }

}
