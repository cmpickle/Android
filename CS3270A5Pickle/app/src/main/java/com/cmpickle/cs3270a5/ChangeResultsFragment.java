package com.cmpickle.cs3270a5;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeResultsFragment extends Fragment {

    EditText etChangeToMakeValue;
    TextView tvChangeTotalSoFarValue;
    TextView tvTimeRemainingValue;

    double changeMax = 100;
    double changeTotalSoFarValue = 0;


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
}
