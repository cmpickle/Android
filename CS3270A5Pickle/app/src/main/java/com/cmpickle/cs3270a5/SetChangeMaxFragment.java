package com.cmpickle.cs3270a5;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetChangeMaxFragment extends Fragment implements View.OnClickListener {

    private EditText etMaximumChangeAmountValue;
    private Button save;

    private Double maximumChangeAmountValue = 100.0;

    public SetChangeMaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_change_max, container, false);

        etMaximumChangeAmountValue = (EditText) view.findViewById(R.id.et_maximum_change_value);

        save = (Button) view.findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        maximumChangeAmountValue = Double.valueOf(etMaximumChangeAmountValue.getText().toString());
        editor.putLong("etMaximumChangeAmountValue", Double.doubleToLongBits(maximumChangeAmountValue));
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setState(1);

        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.findFragmentById(R.id.changeButtonsContainer) != null)
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.changeButtonsContainer)).commit();
        if(fragmentManager.findFragmentById(R.id.changeActionsContainer) != null)
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.changeActionsContainer)).commit();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        maximumChangeAmountValue = Double.longBitsToDouble(sharedPreferences.getLong("etMaximumChangeAmountValue", 100));
        etMaximumChangeAmountValue.setText(String.valueOf(maximumChangeAmountValue));
    }

    @Override
    public void onClick(View v) {
        if(v == save) {
            maximumChangeAmountValue = Double.valueOf(etMaximumChangeAmountValue.getText().toString());
            if(maximumChangeAmountValue<50.01) {
                InvalidMaxValueDialogFragment invalidMaxValueDialogFragment = new InvalidMaxValueDialogFragment();
                invalidMaxValueDialogFragment.setCancelable(false);
                invalidMaxValueDialogFragment.show(getFragmentManager(), "invalid input");
                return;
            }
            //Save the value in a SharedPreference because the changeResultsFragment is currently destroyed and we want to maintain separations of concerns
            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("maximumChangeAmount", Double.doubleToLongBits(maximumChangeAmountValue)); //We have to convert the double to a long in order to not lose precision (putting as a long)
                                                                                 //and to not cause under/overflows
            editor.apply();

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setMainView();
        }
    }
}
