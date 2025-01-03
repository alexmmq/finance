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

    public Account() {
    }

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.incomes = new ArrayList<>();
        this.budgets = new ArrayList<>();
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

    public void addExpenseInBudget(Expense expense, Budget budget) {
        ArrayList<Expense> expenses = budget.getExpenses();
        expenses.add(expense);
        this.balance = balance - expense.getExpenseValue();
        budget.setExpenses(expenses);
    }

    public void removeExpenseInBudget(Expense expense, Budget budget) {
        ArrayList<Expense> expenses = budget.getExpenses();
        Iterator<Expense> expenseIterator = expenses.iterator();
        while (expenseIterator.hasNext()) {
            if (expenseIterator.next().equals(expense)) {
                expenseIterator.remove();
                this.balance = balance + expense.getExpenseValue();
                budget.setExpenses(expenses);
            }
        }
    }

    public void editExpenseInBudget(Expense expense, Budget budget) {
        ArrayList<Expense> expenses = budget.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).equals(expense)) {
                expenses.set(i, expense);
                this.balance = balance - expense.getExpenseValue();
            }
        }
        budget.setExpenses(expenses);
    }

    public void transfer(double amount, User sender, User beneficiary) {
        double currentBalance = getBalance();
        beneficiary.getAccount().receive(amount, sender, beneficiary);

        //creating a budget and expense inside sender's entity
        Budget budget = new Budget("Transfer to " + beneficiary.getUsername());
        budget.setBudgetAmount(amount);
        Expense expense = new Expense("Transfer", amount);
        addExpenseInBudget(expense, budget);
        sender.getAccount().addBudget(budget);
    }

    public void receive(double amount, User sender, User beneficiary) {

        //creating a new income from the incoming stream of money
        Income income = new Income("Transfer from " + sender.getUsername(), amount);
        beneficiary.getAccount().addIncome(income);
    }
}
