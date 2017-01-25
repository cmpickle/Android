package com.cmpickle.cs3270a4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.totalsFragmentContainer, new TotalsFragment(), "totals")
                .add(R.id.taxFragmentContainer, new TaxFragment(), "tax")
                .add(R.id.itemsFragmentContainer, new ItemFragment(), "item")
                .commit();
    }

    public void updateTotalValue(double value) {
        Fragment fragment = getFragmentManager().findFragmentByTag("totals");
        TotalsFragment totalsFragment = (TotalsFragment) fragment;
        totalsFragment.updateTotalValue(value);
    }
}
