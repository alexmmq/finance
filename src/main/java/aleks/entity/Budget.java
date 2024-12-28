package aleks.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Budget {
    public String budgetName;
    private double budgetAmount;
    private ArrayList<Expense> expenses;

    public Budget(String budgetName) {
        this.budgetName = budgetName;
    }

    public Budget(ArrayList<Expense> expenses, String budgetName) {
        this.expenses = expenses;
        this.budgetName = budgetName;
    }

    public boolean checkIfExpensesExceedsBudgetLimit() {
        double expensesAmount = 0;
        for (Expense expense : expenses) {
            expensesAmount = expensesAmount + expense.getExpenseValue();
        }
        return expensesAmount > budgetAmount;
    }
}
