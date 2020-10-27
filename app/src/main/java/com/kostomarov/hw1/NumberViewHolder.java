package com.kostomarov.hw1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NumberViewHolder extends RecyclerView.ViewHolder {
    public interface IListener {
        void onNumberClicked(int position);
    }

    protected final IListener mListener;
    protected final TextView mName;

    public NumberViewHolder(View itemView, IListener listener) {
        super(itemView);

        mListener = listener;

        mName = itemView.findViewById(R.id.name);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNumberClicked(getAdapterPosition());
            }
        };

        itemView.setOnClickListener(clickListener);
    }

    @SuppressLint("ResourceAsColor")
    void bind(int number) {
        mName.setText(Integer.toString(number));
        if (number % 2 == 0)
            mName.setTextColor(Color.RED);
        else
            mName.setTextColor(Color.BLUE);
    };
}
