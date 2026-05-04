package com.example.expensetracker241b079.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensetracker241b079.R;
import com.example.expensetracker241b079.model.Category;
import com.example.expensetracker241b079.viewmodel.ExpenseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryListFragment extends Fragment {
    private ExpenseViewModel mViewModel;
    private CategoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_categories);
        mAdapter = new CategoryAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        mViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            mAdapter.submitList(categories);
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_add_category);
        fab.setOnClickListener(v -> showAddCategoryDialog());

        return view;
    }

    private void showAddCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Category");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_category, (ViewGroup) getView(), false);
        final EditText inputName = viewInflated.findViewById(R.id.et_category_name);
        final EditText inputBudget = viewInflated.findViewById(R.id.et_budget_limit);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            String name = inputName.getText().toString();
            String budgetStr = inputBudget.getText().toString();
            if (!name.isEmpty() && !budgetStr.isEmpty()) {
                double budget = Double.parseDouble(budgetStr);
                // Using a default color (e.g., Gray) for now
                Category category = new Category(name, 0xFF888888, budget);
                mViewModel.insertCategory(category);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }
}