package com.kostomarov.hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NumberDetailsFragment extends Fragment {
    protected static final String EXTRAS_NUMBER = "NUMBER";

    public static NumberDetailsFragment newInstance(int number) {
        final Bundle extras = new Bundle();
        extras.putInt(EXTRAS_NUMBER, number);

        final NumberDetailsFragment fragment = new NumberDetailsFragment();
        fragment.setArguments(extras);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.content_details
                , container
                , false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView mImage = view.findViewById(R.id.image);
        final TextView mName = view.findViewById(R.id.name);

        Bundle extras = this.getArguments();
        int number = 0;

        if (extras == null) {
            System.out.println("Null extras");
        }
        else {
            number = extras.getInt(EXTRAS_NUMBER);
        }

        System.out.println(number);

        mName.setText(Integer.toString(number));
        if (number % 2 == 0)
            mImage.setImageResource(R.color.color_red);
        else
            mImage.setImageResource(R.color.color_blue);
    }


}
