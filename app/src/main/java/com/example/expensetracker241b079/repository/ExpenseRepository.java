package com.example.expensetracker241b079.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.expensetracker241b079.database.AppDatabase;
import com.example.expensetracker241b079.database.CategoryDao;
import com.example.expensetracker241b079.database.ExpenseDao;
import com.example.expensetracker241b079.model.Category;
import com.example.expensetracker241b079.model.Expense;
import java.util.List;

public class ExpenseRepository {
    private ExpenseDao mExpenseDao;
    private CategoryDao mCategoryDao;
    private LiveData<List<Expense>> mAllExpenses;
    private LiveData<List<Category>> mAllCategories;

    public ExpenseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mExpenseDao = db.expenseDao();
        mCategoryDao = db.categoryDao();
        mAllExpenses = mExpenseDao.getAllExpenses();
        mAllCategories = mCategoryDao.getAllCategories();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return mAllExpenses;
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void insertExpense(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> mExpenseDao.insert(expense));
    }

    public void updateExpense(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> mExpenseDao.update(expense));
    }

    public void deleteExpense(Expense expense) {
        AppDatabase.databaseWriteExecutor.execute(() -> mExpenseDao.delete(expense));
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> mCategoryDao.insert(category));
    }
}