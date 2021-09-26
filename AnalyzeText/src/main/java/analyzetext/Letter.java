package analyzetext;

public class Letter extends Entity {
    public Letter(Long amount, String content, Double frequency) {
        super(amount, content, frequency);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("Letter{ amount:")
                .append(getCount().toString())
                .append(",content:")
                .append(getContent())
                .append(",frequency")
                .append(getFrequency().toString())
                .append("}");

        return stringBuilder.toString();
    }
}
