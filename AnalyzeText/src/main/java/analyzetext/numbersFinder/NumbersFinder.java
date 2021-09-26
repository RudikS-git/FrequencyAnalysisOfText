package analyzetext.numbersFinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersFinder implements INumbersFinder {
    public Long count(String text) {
        final Pattern standaloneNumber = Pattern.compile("\\b*\\D*[\\d]+\\D*\\b*"); // "(?<=\\D)\\d+(?=\\D)
        Matcher matcher = standaloneNumber.matcher(text);
        int pos = 0;
        Long count = 0L;

        while (matcher.find(pos)) {
            pos = matcher.end();
            count++;
        }

        return count;
    }
}
