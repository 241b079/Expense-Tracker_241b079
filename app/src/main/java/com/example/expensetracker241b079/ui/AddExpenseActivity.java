package com.example.expensetracker241b079.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.expensetracker241b079.R;
import com.example.expensetracker241b079.model.Category;
import com.example.expensetracker241b079.model.Expense;
import com.example.expensetracker241b079.viewmodel.ExpenseViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {
    private EditText etAmount, etDescription, etPaymentMethod;
    private Spinner spinnerCategory;
    private Button btnSelectDate, btnSave;
    private Date selectedDate = new Date();
    private ExpenseViewModel viewModel;
    private List<Category> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.et_amount);
        etDescription = findViewById(R.id.et_description);
        etPaymentMethod = findViewById(R.id.et_payment_method);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnSelectDate = findViewById(R.id.btn_select_date);
        btnSave = findViewById(R.id.btn_save);

        viewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        viewModel.getAllCategories().observe(this, categories -> {
            this.categoriesList = categories;
            List<String> categoryNames = new ArrayList<>();
            for (Category c : categories) {
                categoryNames.add(c.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategory.setAdapter(adapter);
        });

        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnSave.setOnClickListener(v -> saveExpense());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            btnSelectDate.setText(sdf.format(selectedDate));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void saveExpense() {
        String amountStr = etAmount.getText().toString();
        String description = etDescription.getText().toString();
        String paymentMethod = etPaymentMethod.getText().toString();

        if (amountStr.isEmpty() || description.isEmpty() || categoriesList.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        int categoryId = categoriesList.get(spinnerCategory.getSelectedItemPosition()).getId();

        Expense expense = new Expense(amount, description, selectedDate, categoryId, paymentMethod, new ArrayList<>(), false, "USD");
        viewModel.insertExpense(expense);
        finish();
    }
}