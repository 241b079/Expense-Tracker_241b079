package com.example.expensetracker241b079.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.expensetracker241b079.model.Expense;
import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Update
    void update(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    LiveData<List<Expense>> getAllExpenses();

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    LiveData<List<Expense>> getExpensesInRange(long startDate, long endDate);

    @Query("SELECT * FROM expenses WHERE id = :id")
    Expense getExpenseById(int id);
}