package com.kostomarov.hw1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NumberListFragment extends Fragment {
    public interface IListener {
        public void onNumberClicked(int number);
    }

    protected IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (requireActivity() instanceof IListener) {
            mListener = (IListener) requireActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.content_list
                , container
                , false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recycler = view.findViewById(R.id.recycler);

        recycler.setAdapter(new NumberAdapter(MainActivity.numbers, new NumberClickHandler()));
        if (getResources().getBoolean(R.bool.is_horizontal)) {
            recycler.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        }
        else
            recycler.setLayoutManager(new GridLayoutManager(requireContext(), 3));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    class NumberClickHandler implements NumberViewHolder.IListener {
        @Override
        public void onNumberClicked(int position) {
            final int number = position + 1;

            if (mListener != null) {
                mListener.onNumberClicked(number);
            }
        }
    }
}
