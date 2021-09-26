package analyzetext.letterfinder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SwimParts {
    public static Iterable<String> swimParts(String input, int length) {
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i <= input.length() - length; i++) {
            strings.add(input.substring(i, i + length));
        }

        return strings;
    }
}
