package com.cmpickle.cs3270a5;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.changeResultsContainer, new ChangeResultsFragment(), "changeResultsFragment");
        fragmentTransaction.replace(R.id.changeButtonsContainer, new ChangeButtonsFragment(), "changeButtonsFragment");
        fragmentTransaction.replace(R.id.changeActionsContainer, new ChangeActionsFragment(), "changeActionsFragment");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
