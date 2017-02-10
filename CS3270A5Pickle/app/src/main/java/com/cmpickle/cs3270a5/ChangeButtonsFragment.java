package com.cmpickle.cs3270a5;


import android.app.Activity;
import android.content.DialogInterface;
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

    Button fiftyDollars;
    Button twentyDollars;
    Button tenDollars;
    Button fiveDollars;
    Button oneDollar;
    Button fiftyCents;
    Button twentyfiveCents;
    Button tenCents;
    Button fiveCents;
    Button oneCent;

    public ChangeButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_buttons, container, false);

        fiftyDollars = (Button) view.findViewById(R.id.btn_50_dollars);
        twentyDollars = (Button) view.findViewById(R.id.btn_20_dollars);
        tenDollars = (Button) view.findViewById(R.id.btn_10_dollars);
        fiveDollars = (Button) view.findViewById(R.id.btn_5_dollars);
        oneDollar = (Button) view.findViewById(R.id.btn_1_dollar);
        fiftyCents = (Button) view.findViewById(R.id.btn_50_cents);
        twentyfiveCents = (Button) view.findViewById(R.id.btn_25_cents);
        tenCents = (Button) view.findViewById(R.id.btn_10_cents);
        fiveCents = (Button) view.findViewById(R.id.btn_5_cents);
        oneCent = (Button) view.findViewById(R.id.btn_1_cent);

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
