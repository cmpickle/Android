package com.cmpickle.cs3270a5;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeButtonsFragment extends Fragment implements View.OnClickListener {

    public ChangeButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_buttons, container, false);

        Button fiftyDollars = (Button) view.findViewById(R.id.btn_50_dollars);
        Button twentyDollars = (Button) view.findViewById(R.id.btn_20_dollars);
        Button tenDollars = (Button) view.findViewById(R.id.btn_10_dollars);
        Button fiveDollars = (Button) view.findViewById(R.id.btn_5_dollars);
        Button oneDollar = (Button) view.findViewById(R.id.btn_1_dollar);
        Button fiftyCents = (Button) view.findViewById(R.id.btn_50_cents);
        Button twentyfiveCents = (Button) view.findViewById(R.id.btn_25_cents);
        Button tenCents = (Button) view.findViewById(R.id.btn_10_cents);
        Button fiveCents = (Button) view.findViewById(R.id.btn_5_cents);
        Button oneCent = (Button) view.findViewById(R.id.btn_1_cent);

        fiftyDollars.setOnClickListener(this);
        twentyDollars.setOnClickListener(this);
        tenDollars.setOnClickListener(this);
        fiveDollars.setOnClickListener(this);
        oneDollar.setOnClickListener(this);
        fiftyCents.setOnClickListener(this);
        twentyfiveCents.setOnClickListener(this);
        tenCents.setOnClickListener(this);
        fiveCents.setOnClickListener(this);
        oneCent.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        double amount = 0;
        if(view == getActivity().findViewById(R.id.btn_50_dollars))
                amount = 50;
        if(view == getActivity().findViewById(R.id.btn_20_dollars))
                amount = 20;
        if(view == getActivity().findViewById(R.id.btn_10_dollars))
                amount = 10;
        if(view == getActivity().findViewById(R.id.btn_5_dollars))
                amount = 5;
        if(view == getActivity().findViewById(R.id.btn_1_dollar))
                amount = 1;
        if(view == getActivity().findViewById(R.id.btn_50_cents))
                amount = .5;
        if(view == getActivity().findViewById(R.id.btn_25_cents))
                amount = .25;
        if(view == getActivity().findViewById(R.id.btn_10_cents))
                amount = .1;
        if(view == getActivity().findViewById(R.id.btn_5_cents))
                amount = .05;
        if(view == getActivity().findViewById(R.id.btn_1_cent))
                amount = .01;

        Log.d("test", "ChangeButtonsFragment - onClick - Sending amount: " + amount);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updateChangeTotalSoFar(amount);
    }
}
