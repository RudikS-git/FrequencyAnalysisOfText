package analyzetext;

import analyzetext.frequency.IFrequency;

import java.util.Objects;

public abstract class Entity {
    private Long amount;
    private String content;
    private Double frequency;

    public Entity(Long amount, String content, Double frequency) {
        this.amount = amount;
        this.content = content;
        this.frequency = frequency;
    }

    public Long getCount() { return amount; }
    public void setCount(Long value) {
        amount = value;
    }

    public String getContent() { return content; }
    public void setContent(String value) {
        content = value;
    }

    public Double getFrequency() { return frequency; }
    public void setFrequency(Double value) {
        frequency = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("Entity{ amount:")
                .append(amount.toString())
                .append(",content:")
                .append(content)
                .append(",frequency")
                .append(frequency.toString())
                .append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(amount, entity.amount) && Objects.equals(content, entity.content) && Objects.equals(frequency, entity.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, content, frequency);
    }
}
