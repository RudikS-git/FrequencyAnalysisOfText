package analyzetext.letterfinder;

import analyzetext.Entity;
import analyzetext.Letter;
import analyzetext.frequency.IFrequency;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ThreeLetterFinder implements ILetterFinder {
    @Override
    public Iterable<Letter> find(String text, IFrequency frequency) {
        var array = SwimParts.swimParts(text, 3);
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
