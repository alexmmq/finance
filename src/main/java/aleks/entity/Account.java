package aleks.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;

@Data
public class Account {
    //holds the information about incomes/expenses/budgets for the User
    private int accountNumber;
    private double balance;

    private ArrayList<Income> incomes;
    private ArrayList<Budget> budgets;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public void addIncome(Income income) {
        this.balance = balance + income.getIncomeValue();
        incomes.add(income);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public void updateIncome(Income income) {
        for (int i = 0; i < incomes.size(); i++) {
            if (incomes.get(i).equals(income)) {
                this.balance = balance - incomes.get(i).getIncomeValue();
                incomes.set(i, income);
                this.balance = balance + income.getIncomeValue();
            }
        }
    }

    public void updateBudget(Budget budget) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).equals(budget)) {
                budgets.set(i, budget);
            }
        }
    }


    public void removeIncome(Income income) {
        Iterator<Income> incomeIterator = incomes.iterator();
        while (incomeIterator.hasNext()) {
            if (incomeIterator.next().equals(income)) {
                incomeIterator.remove();
                this.balance = balance - income.getIncomeValue();
            }
        }
    }

    public void removeBudget(Budget budget) {
        Iterator<Budget> budgetIterator = budgets.iterator();
        while (budgetIterator.hasNext()) {
            if (budgetIterator.next().equals(budget)) {
                budgetIterator.remove();
            }
        }
    }
}
