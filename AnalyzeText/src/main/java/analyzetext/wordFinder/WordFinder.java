package analyzetext.wordFinder;

import analyzetext.Entity;
import analyzetext.Letter;
import analyzetext.Word;
import analyzetext.frequency.Frequency;
import analyzetext.frequency.IFrequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFinder implements IWordFinder {

    public Iterable<Word> find(String input, IFrequency frequency) {
        var splitStrings = input.split("[ ():;,.\"«»-]");

        var words = Arrays.stream(splitStrings)
                .collect(Collectors.groupingBy(it -> it.toLowerCase(Locale.ROOT), Collectors.counting())).entrySet().stream()
                .filter(it -> {
                    if(it.getKey().length() == 1 && !Character.isLetter(it.getKey().toCharArray()[0])) {
                        return false;
                    }

                    return true;
                })
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(it -> new Word(it.getValue(), it.getKey(), frequency.get(it.getValue(), splitStrings.length))).toList();

        return words;
    }
}
