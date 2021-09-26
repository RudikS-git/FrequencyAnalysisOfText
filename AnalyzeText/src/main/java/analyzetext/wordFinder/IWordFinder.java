package analyzetext.wordFinder;

import analyzetext.Word;
import analyzetext.frequency.Frequency;
import analyzetext.frequency.IFrequency;

import java.util.Map;

public interface IWordFinder {
    Iterable<Word> find(String input, IFrequency frequency);
}
