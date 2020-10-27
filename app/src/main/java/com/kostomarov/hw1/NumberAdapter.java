package com.kostomarov.hw1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    protected final NumberViewHolder.IListener mListener;
    protected final int[] mData;


    public NumberAdapter(int[] data, NumberViewHolder.IListener listener) {
        mListener = listener;
        mData = data;
    }

    public void Notify(){
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View layout = inflater.inflate(R.layout.item, parent, false);

        return new NumberViewHolder(layout, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        final int number = position + 1;

        holder.bind(number);
    }

    @Override
    public int getItemCount() {
        return MainActivity.count;
    }
}
