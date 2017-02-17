package com.cmpickle.cs3270mi;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {

    TextView etBMI;
    TextView etBFP;

    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        etBMI = (TextView) view.findViewById(R.id.body_mass_index_value);
        etBFP = (TextView) view.findViewById(R.id.body_fat_percentage_value);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("etBMI", etBMI.getText().toString());
        editor.putString("etBFP", etBFP.getText().toString());
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        etBMI.setText(sharedPreferences.getString("etBMI", "0"));
        etBFP.setText(sharedPreferences.getString("etBFP", "0"));
    }

    public void updateValues(double bmi, double bfp) {
        etBMI.setText(String.valueOf(bmi));
        etBFP.setText(String.valueOf(bfp));
    }

}
