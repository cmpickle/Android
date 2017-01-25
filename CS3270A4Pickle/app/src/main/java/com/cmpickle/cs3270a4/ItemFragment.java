package com.cmpickle.cs3270a4;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    EditText etItem1;
    EditText etItem2;
    EditText etItem3;
    EditText etItem4;


    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        etItem1 = (EditText) view.findViewById(R.id.et_item1);
        etItem2 = (EditText) view.findViewById(R.id.et_item2);
        etItem3 = (EditText) view.findViewById(R.id.et_item3);
        etItem4 = (EditText) view.findViewById(R.id.et_item4);

        etItem1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.updateTotalValue(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
