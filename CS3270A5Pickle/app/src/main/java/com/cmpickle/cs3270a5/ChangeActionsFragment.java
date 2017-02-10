package com.cmpickle.cs3270a5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeActionsFragment extends Fragment implements View.OnClickListener {

    Button btnStartOver;
    Button btnNewAmount;
    TextView tvCorrectChangeCountValue;

    int correctChangeCount = 0;

    public ChangeActionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_actions, container, false);

        btnStartOver = (Button) view.findViewById(R.id.btn_start_over);
        btnNewAmount = (Button) view.findViewById(R.id.btn_new_amount);
        tvCorrectChangeCountValue = (TextView) view.findViewById(R.id.tv_correct_change_count_value);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        sharedPreferences.getInt("correctChangeCount", 0);

        tvCorrectChangeCountValue.setText(String.valueOf(correctChangeCount));

        btnStartOver.setOnClickListener(this);
        btnNewAmount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == getActivity().findViewById(R.id.btn_start_over)) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.startOver();
        }
        if(v == getActivity().findViewById(R.id.btn_new_amount)) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.newAmount();
        }
    }

    public void updateCorrectChangeCount(int count) {
        tvCorrectChangeCountValue.setText(String.valueOf(count));
    }
}
