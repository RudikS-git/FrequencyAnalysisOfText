package analyzetext.letterfinder;

public class LetterHelper {
    static public boolean hasOnlyLetters(char [] charArray) {
        for(int i = 0; i < charArray.length; i++) {
            if(!Character.isLetter(charArray[i]) || charArray[i] == ' ') {
                return false;
            }
        }

        return true;
    }
}
