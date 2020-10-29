package com.kostomarov.hw1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    protected static final String COUNT = "COUNT";
    protected static final int DEFAULT = 100;

    protected static int count = 0;

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
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null){
            if (count == 0)
                count = DEFAULT;
        }
        else{
            count = savedInstanceState.getInt(COUNT);
            System.out.println(count);
        }

        final RecyclerView recycler = view.findViewById(R.id.recycler);

        final NumberAdapter adapter = new NumberAdapter(count, new NumberClickHandler());

        Button btn = requireActivity().findViewById(R.id.button);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                adapter.mData++;
                adapter.notifyDataSetChanged();
            }
        };

        btn.setOnClickListener(clickListener);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.columns)));
    }


    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT, count);
        System.out.println(count);
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
