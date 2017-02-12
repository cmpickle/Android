package com.cmpickle.cs3270a5;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.changeResultsContainer, new ChangeResultsFragment(), "changeResultsFragment");
        fragmentTransaction.replace(R.id.changeButtonsContainer, new ChangeButtonsFragment(), "changeButtonsFragment");
        fragmentTransaction.replace(R.id.changeActionsContainer, new ChangeActionsFragment(), "changeActionsFragment");
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("state", state);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        int currentState = sharedPreferences.getInt("state", 0);
        if(currentState == 0) {
            setMainView();
        }
        if(currentState == 1) {
            setChangeMaxFragmentView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.zero_correct_count) {
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("correctChangeCount", 0);
            editor.apply();

            if(state == 0) {
                ChangeActionsFragment changeActionsFragment = (ChangeActionsFragment) getFragmentManager().findFragmentByTag("changeActionsFragment");
                changeActionsFragment.zeroCorrectChangeCount();
            }
            return true;
        }
        if(id == R.id.set_change_max) {
            if(state != 1)
                setChangeMaxFragmentView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }

    public void updateChangeTotalSoFar(double amount) {
        ChangeResultsFragment changeResultsFragment = (ChangeResultsFragment) getFragmentManager().findFragmentByTag("changeResultsFragment");
        changeResultsFragment.updateChangeTotalSoFar(amount);
    }

    public void setMainView() {
        state = 0;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.changeResultsContainer, new ChangeResultsFragment(), "changeResultsFragment");
        fragmentTransaction.replace(R.id.changeButtonsContainer, new ChangeButtonsFragment(), "changeButtonsFragment");
        fragmentTransaction.replace(R.id.changeActionsContainer, new ChangeActionsFragment(), "changeActionsFragment");
        fragmentTransaction.addToBackStack("setMainView");
        fragmentTransaction.commit();
    }

    private void setChangeMaxFragmentView() {
        state = 1;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.changeResultsContainer, new SetChangeMaxFragment(), "setChangeMaxFragment");
        fragmentTransaction.remove(fragmentManager.findFragmentByTag("changeButtonsFragment"));
        fragmentTransaction.remove(fragmentManager.findFragmentByTag("changeActionsFragment"));
        fragmentTransaction.addToBackStack("setChangeMaxView");
        fragmentTransaction.commit();
    }

    public void startOver() {
        FragmentManager fragmentManager = getFragmentManager();
        ChangeResultsFragment changeResultsFragment = (ChangeResultsFragment) fragmentManager.findFragmentByTag("changeResultsFragment");
        changeResultsFragment.startOver();
    }

    public void newAmount() {
        FragmentManager fragmentManager = getFragmentManager();
        ChangeResultsFragment changeResultsFragment = (ChangeResultsFragment) fragmentManager.findFragmentByTag("changeResultsFragment");
        changeResultsFragment.newAmount();
    }

    public void incrementCorrectCount() {
        FragmentManager fragmentManager = getFragmentManager();
        ChangeActionsFragment changeActionsFragment = (ChangeActionsFragment) fragmentManager.findFragmentByTag("changeActionsFragment");
        changeActionsFragment.incrementCorrectCount();
    }

    public void startTimer() {
        ChangeResultsFragment changeResultsFragment = (ChangeResultsFragment) getFragmentManager().findFragmentByTag("changeResultsFragment");
        changeResultsFragment.startTimer();
    }

    public void setState(int state) {
        this.state = state;
    }
}
