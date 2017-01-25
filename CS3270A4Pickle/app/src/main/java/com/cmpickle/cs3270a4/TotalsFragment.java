package com.cmpickle.cs3270a4;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalsFragment extends Fragment {
    TextView txvAmountValue;

    public TotalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_totals, container, false);

        txvAmountValue = (TextView) view.findViewById(R.id.txv_amount_value);
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void updateTotalValue(double value) {
        txvAmountValue.setText("$" + String.valueOf(value));
    }
}
