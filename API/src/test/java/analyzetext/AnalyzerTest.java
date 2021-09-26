package analyzetext;

import analyzetext.frequency.Frequency;
import analyzetext.letterfinder.LetterFinder;
import analyzetext.letterfinder.ThreeLetterFinder;
import analyzetext.letterfinder.TwoLettersFinder;
import analyzetext.numbersFinder.NumbersFinder;
import analyzetext.wordFinder.WordFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    @DisplayName("Get letters in input string")
    void getLetters() throws AnalyzerException {
        IAnalyzer analyzer = new Analyzer<Frequency>();
        analyzer.setFrequency(new Frequency());
        analyzer.setInputText("It is test");

        assertAll(
                () -> assertEquals(Arrays.asList(
                                new Letter(3L, "t", 37.5),
                                new Letter(2L, "s", 25.0),
                                new Letter(2L, "i", 25.0),
                                new Letter(1L, "e", 12.5)
                        ),
                        analyzer.getLetters(new LetterFinder())
                ),

                () -> assertEquals(Arrays.asList(
                            new Letter(1L, "st", 20.0),
                                new Letter(1L, "te", 20.0),
                                new Letter(1L, "is", 20.0),
                                new Letter(1L, "it", 20.0),
                                new Letter(1L, "es", 20.0)
                        ),
                        analyzer.getLetters(new TwoLettersFinder())
                ),

                () -> assertEquals(Arrays.asList(
                                new Letter(1L, "tes", 50.0),
                                new Letter(1L, "est", 50.0)
                        ),
                        analyzer.getLetters(new ThreeLetterFinder()))
        );


        analyzer.setInputText("1 ad 4");
        assertAll(
                () -> assertEquals(Arrays.asList(
                                new Letter(1L, "a", 50.0),
                                new Letter(1L, "d", 50.0)
                        ),
                        analyzer.getLetters(new LetterFinder())
                ),

                () -> assertEquals(Arrays.asList(
                                new Letter(1L, "ad", 100.0)
                        ),
                        analyzer.getLetters(new TwoLettersFinder())
                ),

                () -> assertEquals(Arrays.asList(),
                        analyzer.getLetters(new ThreeLetterFinder()))
        );

        assertThrows(AnalyzerException.class, () -> analyzer.setInputText(""));
    }

    @Test
    @DisplayName("Get words in input string")
    void getWords() throws AnalyzerException {
        IAnalyzer analyzer = new Analyzer();
        analyzer.setFrequency(new Frequency());
        analyzer.setInputText("It is test");
        assertEquals(Arrays.asList(
                            new Word(1L, "test", 33.33333333333333),
                            new Word(1L, "is", 33.33333333333333),
                            new Word(1L, "it", 33.33333333333333)
                        ),
                analyzer.getWords(new WordFinder()));

        analyzer.setInputText("It works");
        assertEquals(Arrays.asList(
                        new Word(1L, "works", 50.0),
                        new Word(1L, "it", 50.0)
                ),
                analyzer.getWords(new WordFinder()));

        analyzer.setInputText("Get words in input string");
        assertEquals(Arrays.asList(
                        new Word(1L, "input", 20.0),
                        new Word(1L, "string", 20.0),
                        new Word(1L, "in", 20.0),
                        new Word(1L, "get", 20.0),
                        new Word(1L, "words", 20.0)
                ),
                analyzer.getWords(new WordFinder()));


    }

    @Test
    @DisplayName("Get digits in input string")
    void getAmountDigits() throws AnalyzerException {
        IAnalyzer analyzer = new Analyzer();
        analyzer.setFrequency(new Frequency());

        analyzer.setInputText("15th March 4 2021");
        assertEquals(3, analyzer.getAmountDigits(new NumbersFinder()));

        analyzer.setInputText("15th5 Ma34rch 4 2021 31");
        assertEquals(6, analyzer.getAmountDigits(new NumbersFinder()));

        analyzer.setInputText("ац ца dada daddfw ge");
        assertEquals(0, analyzer.getAmountDigits(new NumbersFinder()));
    }
}