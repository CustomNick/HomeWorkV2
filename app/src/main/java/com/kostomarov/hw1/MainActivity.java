package com.kostomarov.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NumberListFragment.IListener {

    protected static final String TAG_DETAILS = "DETAILS";
    protected static final String EXTRAS_NUMBER = "NUMBER";

    public static int[] numbers = new int[1000];

    public static int count = 100;

    public boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linear01, new NumberListFragment())
                .commit();

        if (savedInstanceState == null){

        }
        else {
            checkFrag();
        }
    }

    @Override
    public void onNumberClicked(int number) {
        showDetails(number);
    }

    @Override
    public void onBackPressed() {
        check = false;
        getSupportFragmentManager()
                .popBackStack("Recycler", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void addNumber(View view){
        count++;
        RecyclerView recycler = this.findViewById(R.id.recycler);
        if (recycler != null){
            NumberAdapter adapter = (NumberAdapter) recycler.getAdapter();
            if (adapter == null){
                System.out.println("Adapter is null");
            } else{
                adapter.Notify();
            }
        }
        else {
            System.out.println("Recycler is null");
        }
    }

    protected void showDetails(int number) {
        if (number == 0) {
            return;
        }

        check = true;
        System.out.println(number);

        final NumberDetailsFragment detailsFragment = NumberDetailsFragment.newInstance(number);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linear01, detailsFragment, TAG_DETAILS)
                .addToBackStack("Recycler")
                .commit();
    }

    protected void checkFrag(){
        final NumberDetailsFragment temp = (NumberDetailsFragment) getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);

        if (temp == null){

        }
        else {
            Bundle extras = temp.getArguments();

            final int number = extras.getInt(EXTRAS_NUMBER);

            showDetails(number);
        }
    }
}