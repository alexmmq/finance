package aleks.entity;

import lombok.Data;

@Data
public class Income {
    public String incomeName;
    private double incomeValue;

    public Income() {
    }

    public Income(String incomeName, double incomeValue) {
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
    }
}
