package com.cmpickle.cs3270a4;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaxFragment extends Fragment {
    SeekBar seekBar;
    TextView txvTaxRate;
    TextView txvTaxAmount;

    public TaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax, container, false);

        txvTaxRate = (TextView) view.findViewById(R.id.txv_tax_rate);
        txvTaxAmount = (TextView) view.findViewById(R.id.txv_tax_amount);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(25);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txvTaxRate.setText(getResources().getString(R.string.tax_rate) + " " + progress + ".00%");
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

}
