package analyzetext.letterfinder;

import analyzetext.Entity;
import analyzetext.Letter;
import analyzetext.frequency.IFrequency;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LetterFinder implements ILetterFinder {

    @Override
    public Iterable<Letter> find(String text, IFrequency frequency) {
        var array = SwimParts.swimParts(text, 1);

        var newArray = StreamSupport.stream(array.spliterator(), false)
                .filter(it -> LetterHelper.hasOnlyLetters(it.toCharArray()))
                .toList();

        var list = newArray.stream()
                .collect(Collectors.groupingBy(it -> it.toLowerCase(Locale.ROOT), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(it -> new Letter(it.getValue(), it.getKey(), frequency.get(it.getValue(), newArray.size()))).toList();

        return list;
    }
}
