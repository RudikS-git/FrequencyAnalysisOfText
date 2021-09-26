package analyzetext.letterfinder;

import analyzetext.Entity;
import analyzetext.Letter;
import analyzetext.frequency.IFrequency;

import java.util.Map;

public interface ILetterFinder {
    Iterable<Letter> find(String text, IFrequency frequency);
}
