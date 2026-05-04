package com.example.expensetracker241b079.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensetracker241b079.R;
import com.example.expensetracker241b079.model.Expense;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ExpenseAdapter extends ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder> {

    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Expense expense);
    }

    protected ExpenseAdapter(OnDeleteClickListener deleteClickListener) {
        super(DIFF_CALLBACK);
        this.deleteClickListener = deleteClickListener;
    }

    private static final DiffUtil.ItemCallback<Expense> DIFF_CALLBACK = new DiffUtil.ItemCallback<Expense>() {
        @Override
        public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getAmount() == newItem.getAmount() &&
                    oldItem.getDate().equals(newItem.getDate());
        }
    };

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense current = getItem(position);
        holder.tvDescription.setText(current.getDescription());
        holder.tvAmount.setText(String.format(Locale.getDefault(), "-$%.2f", current.getAmount()));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        holder.tvDate.setText(sdf.format(current.getDate()));
        
        holder.tvCategory.setText("Category ID: " + current.getCategoryId());

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(current);
            }
        });
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDescription;
        private final TextView tvAmount;
        private final TextView tvDate;
        private final TextView tvCategory;
        private final ImageButton btnDelete;

        private ExpenseViewHolder(View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvCategory = itemView.findViewById(R.id.tv_category);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}