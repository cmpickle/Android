package com.cmpickle.cs3270a4;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaxFragment extends Fragment {
    SeekBar seekBar;
    TextView txvTaxRate;
    TextView txvTaxAmount;

    float taxRate = 10;
    float taxAmount = 0;

    public TaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "TaxFragment: onCreateView");
        View view = inflater.inflate(R.layout.fragment_tax, container, false);

        txvTaxRate = (TextView) view.findViewById(R.id.txv_tax_rate);
        txvTaxRate.setText(String.format(getResources().getString(R.string.tax_rate), taxRate));
        txvTaxAmount = (TextView) view.findViewById(R.id.txv_tax_amount);
        txvTaxAmount.setText(String.format(getResources().getString(R.string.tax_amount), taxAmount));
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(25);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                taxRate = progress;
                txvTaxRate.setText(String.format(getResources().getString(R.string.tax_rate), taxRate));
                updateTaxAmount();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("test", "TaxFragment: onPause");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("taxRate", taxRate);
        editor.putFloat("taxAmount", taxAmount);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test", "TaxFragment: onResume");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        taxRate = preferences.getFloat("taxRate", 10);
        txvTaxRate.setText(String.format(getResources().getString(R.string.tax_rate), taxRate));
        seekBar.setProgress((int)taxRate);
        taxAmount = preferences.getFloat("taxAmount", 0);
        txvTaxAmount.setText(String.format(getResources().getString(R.string.tax_amount), taxAmount));
    }

    public void updateTaxAmount() {
        taxAmount = getTotalAmount()*taxRate/100;
        txvTaxAmount.setText(String.format(getResources().getString(R.string.tax_amount),taxAmount));
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updateTotalWithTax();
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    private float getTotalAmount() {
        Activity activity = getActivity();
        MainActivity mainActivity = (MainActivity) activity;
        return mainActivity.getTotalAmount();
    }
}
