package com.cmpickle.cs3270a4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "MainActivity: onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "MainActivity: onResume");

        getFragmentManager().beginTransaction()
                .add(R.id.totalsFragmentContainer, new TotalsFragment(), "totals").commit();
        getFragmentManager().beginTransaction()
                .add(R.id.taxFragmentContainer, new TaxFragment(), "tax").commit();
        getFragmentManager().beginTransaction()
                .add(R.id.itemsFragmentContainer, new ItemFragment(), "item").commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test", "MainActivity: onPause");
        getFragmentManager().beginTransaction()
                .remove(getFragmentManager().findFragmentByTag("totals"))
                .remove(getFragmentManager().findFragmentByTag("tax"))
                .remove(getFragmentManager().findFragmentByTag("item"))
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "MainActivity: onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test", "MainActivity: onStop");
    }

    public void updateTotalValue(float value, int itemNum) {
        TotalsFragment totalsFragment = (TotalsFragment) getFragmentManager().findFragmentByTag("totals");
        totalsFragment.updateTotalValue(value, itemNum);
    }

    public void updateTotalWithTax() {
        TotalsFragment totalsFragment = (TotalsFragment) getFragmentManager().findFragmentByTag("totals");
        totalsFragment.updateTotalWithTax();
    }

    public void updateTaxAmount() {
        TaxFragment taxFragment = (TaxFragment) getFragmentManager().findFragmentByTag("tax");
        taxFragment.updateTaxAmount();
    }

    public float getTotalAmount() {
        TotalsFragment totalsFragment = (TotalsFragment) getFragmentManager().findFragmentByTag("totals");
        return totalsFragment.getTotalAmount();
    }

    public float getTaxAmount() {
        TaxFragment taxFragment = (TaxFragment) getFragmentManager().findFragmentByTag("tax");
        return taxFragment.getTaxAmount();
    }
}
