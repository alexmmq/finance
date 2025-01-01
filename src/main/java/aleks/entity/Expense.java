package aleks.entity;

import lombok.Data;

@Data
public class Expense {
    public String expenseName;
    private double expenseValue;

    public Expense() {
    }

    public Expense(String expenseName, double expenseValue) {
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
    }
}
