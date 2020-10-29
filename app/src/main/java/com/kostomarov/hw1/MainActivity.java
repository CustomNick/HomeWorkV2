package com.kostomarov.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NumberListFragment.IListener {

    protected static final String TAG_DETAILS = "DETAILS";

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