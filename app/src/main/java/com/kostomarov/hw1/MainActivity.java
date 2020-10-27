package com.kostomarov.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements NumberListFragment.IListener {

    protected static final String TAG_DETAILS = "DETAILS";

    public static int count = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.linear01, new NumberListFragment())
                    .commit();
        }
    }

    public void addNumber(View view){
        count++;
        RecyclerView recycler = view.getRootView().findViewById(R.id.recycler);
        if (recycler != null){
            NumberAdapter adapter = (NumberAdapter) recycler.getAdapter();
            if (adapter == null){
                System.out.println("Adapter is null");
            } else{
                adapter.addNumber();
            }
        }
        else {
            System.out.println("Recycler is null");
        }
    }

    @Override
    public void onNumberClicked(int number) {
        showDetails(number);
    }

    protected void showDetails(int number) {
        if (number == 0) {
            return;
        }

        System.out.println(number);

        final NumberDetailsFragment detailsFragment = NumberDetailsFragment.newInstance(number);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linear01, detailsFragment, TAG_DETAILS)
                .addToBackStack("Recycler")
                .commit();
    }
}