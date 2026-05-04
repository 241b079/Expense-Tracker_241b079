package com.example.expensetracker241b079.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int color;
    private double budgetLimit;

    public Category(String name, int color, double budgetLimit) {
        this.name = name;
        this.color = color;
        this.budgetLimit = budgetLimit;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
    public double getBudgetLimit() { return budgetLimit; }
    public void setBudgetLimit(double budgetLimit) { this.budgetLimit = budgetLimit; }
}