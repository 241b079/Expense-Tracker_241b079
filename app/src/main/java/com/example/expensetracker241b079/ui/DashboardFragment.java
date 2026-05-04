package com.example.expensetracker241b079.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.expensetracker241b079.R;
import com.example.expensetracker241b079.model.Category;
import com.example.expensetracker241b079.model.Expense;
import com.example.expensetracker241b079.viewmodel.ExpenseViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DashboardFragment extends Fragment {
    private ExpenseViewModel mViewModel;
    private TextView tvTotalSpent;
    private PieChart pieChart;
    private LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tvTotalSpent = view.findViewById(R.id.tv_total_spent);
        pieChart = view.findViewById(R.id.pie_chart);
        lineChart = view.findViewById(R.id.line_chart);

        mViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        mViewModel.getAllExpenses().observe(getViewLifecycleOwner(), expenses -> {
            updateDashboard(expenses);
        });

        return view;
    }

    private void updateDashboard(List<Expense> expenses) {
        if (expenses == null || expenses.isEmpty()) return;

        double total = 0;
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        Map<Integer, Double> categoryTotals = new HashMap<>();
        
        for (Expense e : expenses) {
            cal.setTime(e.getDate());
            if (cal.get(Calendar.MONTH) == currentMonth && cal.get(Calendar.YEAR) == currentYear) {
                total += e.getAmount();
            }
            
            categoryTotals.put(e.getCategoryId(), categoryTotals.getOrDefault(e.getCategoryId(), 0.0) + e.getAmount());
        }

        tvTotalSpent.setText(String.format(Locale.getDefault(), "$%.2f", total));

        setupPieChart(categoryTotals);
        setupLineChart(expenses);
    }

    private void setupPieChart(Map<Integer, Double> categoryTotals) {
        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : categoryTotals.entrySet()) {
            entries.add(new PieEntry(entry.getValue().floatValue(), "Cat " + entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expenses by Category");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void setupLineChart(List<Expense> expenses) {
        List<Entry> entries = new ArrayList<>();
        // Simple implementation: sort by date and show cumulative spending or daily
        // For now, just show a placeholder or basic trend
        for (int i = 0; i < Math.min(expenses.size(), 10); i++) {
            entries.add(new Entry(i, (float) expenses.get(i).getAmount()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Recent Expenses");
        dataSet.setColor(Color.BLUE);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.invalidate();
    }
}