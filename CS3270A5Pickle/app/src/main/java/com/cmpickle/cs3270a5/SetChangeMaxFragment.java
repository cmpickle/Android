package com.cmpickle.cs3270a5;


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

    EditText etMaximumChangeAmountValue;

    Button save;

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
    public void onClick(View v) {
        if(v == save) {
            ChangeResultsFragment changeResultsFragment = (ChangeResultsFragment) getFragmentManager().findFragmentByTag("ChangeResultsFragment"); //null fragment because it is being replaced to show this one...
            changeResultsFragment.updateMaximumChangeAmount(Double.parseDouble(etMaximumChangeAmountValue.getText().toString()));
        }
    }
}
