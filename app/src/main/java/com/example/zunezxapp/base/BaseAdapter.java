package com.example.zunezxapp.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public abstract class BaseAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected T binding;

    protected abstract int getLayoutId();

    protected abstract RecyclerView.ViewHolder solvedOnCreateViewHolder(T binding);
    protected abstract void solvedOnBindViewHolder(RecyclerView.ViewHolder holder,int position);

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), parent, false);
        return solvedOnCreateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        solvedOnBindViewHolder(holder,position);
    }

    protected abstract class BaseViewHolder<U> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View view) {
            super(view);
        }

        protected abstract void bind(U data);

    }
}
