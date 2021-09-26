package com.example.webfrequenceanalysisoftext.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "texts")
public class AnalysisResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text_name")
    private String textName;

    @Column(name = "text", columnDefinition="text")
    private String text;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "text_id")
    private List<OneLetter> letters;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "text_id")
    private List<TwoLetter> twoLetters;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "text_id")
    private List<ThreeLetter> threeLetters;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "text_id")
    private List<Word> words;

    @Column(name = "amount_numbers")
    private Long amountNumbers;

    @Column(name = "amount_symbols")
    private Long amountSymbols;

    @Column(name = "date_analysis")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateAnalysis;
    public Date getDateAnalysis() {
        return dateAnalysis;
    }

    public void setDateAnalysis(Date dateAnalysis) {
        this.dateAnalysis = dateAnalysis;
    }

    public Long getAmountNumbers() {
        return this.amountNumbers;
    }

    public List<OneLetter>  getLetters() {
        return this.letters;
    }

    public List<TwoLetter>  getTwoLetters() {
        return this.twoLetters;
    }

    public List<ThreeLetter>  getThreeLetters() {
        return this.threeLetters;
    }

    public List<Word>  getWords() {
        return this.words;
    }

    public Long getAmountSymbols() { return this.amountSymbols; }

    public Long getId() { return this.id; }

    public String getTextName() { return this.textName; }

    public String getText() { return this.text; }

    public void setAmountNumbers(Long amountNumbers) {
        this.amountNumbers = amountNumbers;
    }

    public void setLetters(List<OneLetter> letters) {
        this.letters = letters;
    }

    public void setTwoLetters(List<TwoLetter> twoLetters) {
        this.twoLetters = twoLetters;
    }

    public void setThreeLetters(List<ThreeLetter> threeLetters) {
        this.threeLetters = threeLetters;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void setAmountSymbols(Long amountSymbols) {
        this.amountSymbols = amountSymbols;
    }

    public void setId(Long id) { this.id = id; }

    public void setTextName(String textName) { this.textName = textName; }

    public void setText(String text) { this.text = text; }
}
