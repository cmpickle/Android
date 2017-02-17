package com.cmpickle.cs3270mi;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.action_fragment_container, new ActionFragment(), "actionFragment")
                .replace(R.id.results_fragment_container, new ResultsFragment(), "resultsFragment")
                .commit();
    }

    public void updateValues(double bmi, double bfp) {
        FragmentManager fragmentManager = getFragmentManager();
        ResultsFragment resultsFragment = (ResultsFragment) fragmentManager.findFragmentByTag("resultsFragment");
        resultsFragment.updateValues(bmi, bfp);
    }
}
