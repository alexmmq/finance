package aleks.entity;

import lombok.Data;

@Data
public class Expense {
    public Budget budget;
    private double expenseValue;

    public Expense(Budget budget, double expenseValue) {
        this.budget = budget;
        this.expenseValue = expenseValue;
    }
}
