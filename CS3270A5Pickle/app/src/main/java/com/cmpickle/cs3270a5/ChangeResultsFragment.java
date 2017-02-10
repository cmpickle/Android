package com.cmpickle.cs3270a5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeResultsFragment extends Fragment {

    EditText etChangeToMakeValue;
    TextView tvChangeTotalSoFarValue;
    TextView tvTimeRemainingValue;

    double changeMax = 100;
    double changeTotalSoFarValue = 0;
    double changeToMakeValue = 100;


    public ChangeResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_results, container, false);

        etChangeToMakeValue = (EditText) view.findViewById(R.id.et_change_to_make_value);
        tvChangeTotalSoFarValue = (TextView) view.findViewById(R.id.tv_change_total_so_far_value);
        tvTimeRemainingValue = (TextView) view.findViewById(R.id.tv_time_remaining_value);

        etChangeToMakeValue.setEnabled(false);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        changeMax = Double.longBitsToDouble(sharedPreferences.getLong("maximumChangeAmount", 100)); //don't need to cast default long 100 to double bits, it works as a long

        newAmount();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        etChangeToMakeValue = null;
        tvChangeTotalSoFarValue = null;
        tvTimeRemainingValue = null;
    }

    public void updateChangeTotalSoFar(double amount) {
        changeTotalSoFarValue += amount;

        tvChangeTotalSoFarValue.setText(String.format(getResources().getString(R.string.change_total_so_far_value), changeTotalSoFarValue));
    }

    public void startOver() {

    }

    public void newAmount() {
        changeToMakeValue = ThreadLocalRandom.current().nextDouble(0, changeMax);
        changeToMakeValue = Math.floor(changeToMakeValue*100) /100;

        changeTotalSoFarValue = 0;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        etChangeToMakeValue.setText(numberFormat.format(changeToMakeValue));
        tvChangeTotalSoFarValue.setText(getResources().getString(R.string.zero_cents));
        tvTimeRemainingValue.setText(getResources().getString(R.string.thirty));
    }
}
