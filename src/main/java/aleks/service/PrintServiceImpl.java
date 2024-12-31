package aleks.service;

import aleks.entity.Budget;
import aleks.entity.Expense;
import aleks.entity.Income;
import aleks.entity.User;

import java.util.ArrayList;

public class PrintServiceImpl implements PrintService {

    @Override
    public void getPrettyBudgets(User user) {
        System.out.printf("%-15s%15s%n", "Budget", "Amount");
        ArrayList<Budget> budgets = user.getAccount().getBudgets();
        for (Budget budget : budgets) {
            System.out.printf("%-15s%15s%n", budget.getBudgetName(), budget.getBudgetAmount());
            ArrayList<Expense> expenses = budget.getExpenses();
            for (Expense expense : expenses) {
                System.out.printf("%-15s%15s%n", expense.getExpenseName(), expense.getExpenseValue());
            }
        }
    }

    @Override
    public void getPrettyIncomes(User user) {
        System.out.printf("%-15s%15s%n", "Income","Amount");
        ArrayList<Income> incomes = user.getAccount().getIncomes();
        for (Income income : incomes) {
            System.out.printf("%-15s%15s%n", income.getIncomeName(), income.getIncomeValue());
        }
    }

    @Override
    public void getOverallInformation(User user) {
        System.out.println("Overall Information");
        System.out.println(user.getUsername() + user.getAccount().getAccountNumber());
        System.out.println();
        getPrettyIncomes(user);
        System.out.println();
        getPrettyBudgets(user);
    }
}
