package com.cmpickle.cs3270a4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalsFragment extends Fragment {
    TextView txvAmountValue;

    float totalAmount = 0;
    float totalAmountWithTax = 0;
    float[] items = {0,0,0,0};

    public TotalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "TotalsFragment: onCreateView");
        View view = inflater.inflate(R.layout.fragment_totals, container, false);

        txvAmountValue = (TextView) view.findViewById(R.id.txv_amount_value);
        txvAmountValue.setText(String.format(getResources().getString(R.string.total_value), totalAmountWithTax));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("test", "TotalsFragment: onPause");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("totalAmount", totalAmount);
        editor.putFloat("totalAmountWithTax", totalAmountWithTax);
        editor.putFloat("items0", items[0]);
        editor.putFloat("items1", items[1]);
        editor.putFloat("items2", items[2]);
        editor.putFloat("items3", items[3]);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test", "TotalsFragment: onResume");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        totalAmount = preferences.getFloat("totalAmount", 0);
        totalAmountWithTax = preferences.getFloat("totalAmountWithTax", 0);
        items[0] = preferences.getFloat("items0", 0);
        items[1] = preferences.getFloat("items1", 0);
        items[2] = preferences.getFloat("items2", 0);
        items[3] = preferences.getFloat("items3", 0);
    }

    public void updateTotalValue(float value, int itemNum) {
        items[itemNum-1] = value;
        updateTotal();
    }

    public void updateTotalWithTax() {
        totalAmountWithTax = totalAmount + getTaxAmount();
        txvAmountValue.setText(String.format(getResources().getString(R.string.total_value), totalAmountWithTax));
    }

    private void updateTotal() {
        float sum = 0;
        for(float i : items){
            sum += i;
        }
        totalAmount = sum;
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updateTaxAmount();
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    private float getTaxAmount() {
        MainActivity mainActivity = (MainActivity) getActivity();
        return mainActivity.getTaxAmount();
    }
}
