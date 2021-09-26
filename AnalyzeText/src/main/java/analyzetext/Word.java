package analyzetext;

public class Word extends Entity {
    public Word(Long amount, String content, Double frequency) {
        super(amount, content, frequency);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("Word{ amount:")
                .append(getCount().toString())
                .append(",content:")
                .append(getContent())
                .append(",frequency")
                .append(getFrequency().toString())
                .append("}");

        return stringBuilder.toString();
    }
}
