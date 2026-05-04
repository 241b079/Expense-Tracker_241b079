package com.example.expensetracker241b079.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.expensetracker241b079.model.Category;
import com.example.expensetracker241b079.model.Expense;
import com.example.expensetracker241b079.repository.ExpenseRepository;
import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository mRepository;
    private LiveData<List<Expense>> mAllExpenses;
    private LiveData<List<Category>> mAllCategories;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ExpenseRepository(application);
        mAllExpenses = mRepository.getAllExpenses();
        mAllCategories = mRepository.getAllCategories();
    }

    public LiveData<List<Expense>> getAllExpenses() { return mAllExpenses; }
    public LiveData<List<Category>> getAllCategories() { return mAllCategories; }

    public void insertExpense(Expense expense) { mRepository.insertExpense(expense); }
    public void updateExpense(Expense expense) { mRepository.updateExpense(expense); }
    public void deleteExpense(Expense expense) { mRepository.deleteExpense(expense); }
    public void insertCategory(Category category) { mRepository.insertCategory(category); }
    public void deleteCategory(Category category) { mRepository.deleteCategory(category); }
}