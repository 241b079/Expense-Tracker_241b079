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
import com.example.expensetracker241b079.model.Category;
import java.util.Locale;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.CategoryViewHolder> {

    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Category category);
    }

    protected CategoryAdapter(OnDeleteClickListener deleteClickListener) {
        super(DIFF_CALLBACK);
        this.deleteClickListener = deleteClickListener;
    }

    private static final DiffUtil.ItemCallback<Category> DIFF_CALLBACK = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getColor() == newItem.getColor() &&
                    oldItem.getBudgetLimit() == newItem.getBudgetLimit();
        }
    };

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category current = getItem(position);
        holder.tvName.setText(current.getName());
        holder.tvBudget.setText(String.format(Locale.getDefault(), "Budget: $%.2f", current.getBudgetLimit()));
        holder.viewColor.setBackgroundColor(current.getColor());

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(current);
            }
        });
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvBudget;
        private final View viewColor;
        private final ImageButton btnDelete;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_category_name);
            tvBudget = itemView.findViewById(R.id.tv_budget_limit);
            viewColor = itemView.findViewById(R.id.view_color);
            btnDelete = itemView.findViewById(R.id.btn_delete_category);
        }
    }
}