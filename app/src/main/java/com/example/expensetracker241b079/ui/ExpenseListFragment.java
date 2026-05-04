package com.example.expensetracker241b079.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensetracker241b079.R;
import com.example.expensetracker241b079.viewmodel.ExpenseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpenseListFragment extends Fragment {
    private ExpenseViewModel mViewModel;
    private ExpenseAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        mViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.rv_expenses);
        mAdapter = new ExpenseAdapter(expense -> mViewModel.deleteExpense(expense));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getAllExpenses().observe(getViewLifecycleOwner(), expenses -> {
            mAdapter.submitList(expenses);
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_add_expense);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExpenseActivity.class);
            startActivity(intent);
        });

        return view;
    }
}