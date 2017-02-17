package com.cmpickle.cs3270mi;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActionFragment extends Fragment {

    double bmiValue = 0;
    double bfpValue = 0;
    EditText etWeightInPoundsValue;
    EditText etHeightInInchesValue;
    EditText etAgeValue;
    RadioGroup radioGroup;

    public ActionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);

        etWeightInPoundsValue = (EditText) view.findViewById(R.id.weight_in_pounds_value);
        etHeightInInchesValue = (EditText) view.findViewById(R.id.height_in_inches_value);
        etAgeValue = (EditText) view.findViewById(R.id.age_value);

        radioGroup = (RadioGroup) view.findViewById(R.id.sex_radio_group);

        Button calculate = (Button) view.findViewById(R.id.btn_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weight = Double.parseDouble(etWeightInPoundsValue.getText().toString());
                double height = Double.parseDouble(etHeightInInchesValue.getText().toString());
                double age = Double.parseDouble(etAgeValue.getText().toString());
                int sex = (radioGroup.getCheckedRadioButtonId() == R.id.male_radio_button)? 0:1;
                Log.d("test", "sex, - value: " + sex);
                bmiValue = (double)Math.round((weight/(height*height)*703)*100)/100;
                bfpValue = (double)Math.round(((1.2 * bmiValue) + (.23 * age) - (10.8 * sex) - 5.4)*100)/100;

                updateValues(bmiValue, bfpValue);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("etWeightInPoundsValue", etWeightInPoundsValue.getText().toString());
        editor.putString("etHeightInInchesValue", etHeightInInchesValue.getText().toString());
        editor.putString("etAgeValue", etAgeValue.getText().toString());
        editor.putString("radioGroup", String.valueOf(radioGroup.getCheckedRadioButtonId()));
        editor.putString("bmiValue", String.valueOf(bmiValue));
        editor.putString("bfpValue", String.valueOf(bfpValue));
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        etWeightInPoundsValue.setText(sharedPreferences.getString("etWeightInPoundsValue", "0"));
        etHeightInInchesValue.setText(sharedPreferences.getString("etHeightInInchesValue", "0"));
        etAgeValue.setText(sharedPreferences.getString("etAgeValue", "0"));
        radioGroup.check(Integer.parseInt(sharedPreferences.getString("radioGroup", String.valueOf(R.id.female_radio_button))));
        bmiValue = Double.parseDouble(sharedPreferences.getString("bmiValue", "0"));
        bfpValue = Double.parseDouble(sharedPreferences.getString("bfpValue", "0"));
    }

    private void updateValues(double bmi, double bfp) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updateValues(bmi, bfp);
    }

}
