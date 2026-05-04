package com.example.expensetracker241b079.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.expensetracker241b079.database.Converters;
import java.util.Date;
import java.util.List;

@Entity(tableName = "expenses")
@TypeConverters(Converters.class)
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double amount;
    private String description;
    private Date date;
    private int categoryId;
    private String paymentMethod;
    private List<String> tags;
    private boolean isRecurring;
    private String currency;

    public Expense(double amount, String description, Date date, int categoryId, String paymentMethod, List<String> tags, boolean isRecurring, String currency) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
        this.paymentMethod = paymentMethod;
        this.tags = tags;
        this.isRecurring = isRecurring;
        this.currency = currency;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}