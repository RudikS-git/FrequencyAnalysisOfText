package analyzetext.frequency;

import analyzetext.Entity;

public class Frequency implements IFrequency {
    @Override
    public double get(Long amount, int amountTotal) {
        return (double)amount / amountTotal * 100D;
    }
}

