package com.cmpickle.cs3270a4;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    float itemAmount1;
    float itemAmount2;
    float itemAmount3;
    float itemAmount4;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "ItemFragment: onCreateView");
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
                try {
                    itemAmount1 = Float.parseFloat(s.toString());
                } catch (NumberFormatException nfe) {
                    itemAmount1 = 0;
                }
                mainActivity.updateTotalValue(itemAmount1, 1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etItem2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                try {
                    itemAmount2 = Float.parseFloat(s.toString());
                } catch (NumberFormatException nfe) {
                    itemAmount2 = 0;
                }
                mainActivity.updateTotalValue(itemAmount2, 2);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etItem3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                try {
                    itemAmount3 = Float.parseFloat(s.toString());
                } catch (NumberFormatException nfe) {
                    itemAmount3 = 0;
                }
                mainActivity.updateTotalValue(itemAmount3, 3);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etItem4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                try {
                    itemAmount4 = Float.parseFloat(s.toString());
                } catch (NumberFormatException nfe) {
                    itemAmount4 = 0;
                }
                mainActivity.updateTotalValue(itemAmount4, 4);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("test", "ItemFragment: onPause");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("itemAmount1", itemAmount1);
        editor.putFloat("itemAmount2", itemAmount2);
        editor.putFloat("itemAmount3", itemAmount3);
        editor.putFloat("itemAmount4", itemAmount4);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test", "ItemFragment: onResume");
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        itemAmount1 = preferences.getFloat("itemAmount1", 0);
        etItem1.setText(String.valueOf(itemAmount1));
        itemAmount2 = preferences.getFloat("itemAmount2", 0);
        etItem2.setText(String.valueOf(itemAmount2));
        itemAmount3 = preferences.getFloat("itemAmount3", 0);
        etItem3.setText(String.valueOf(itemAmount3));
        itemAmount4 = preferences.getFloat("itemAmount4", 0);
        etItem4.setText(String.valueOf(itemAmount4));
    }
}
