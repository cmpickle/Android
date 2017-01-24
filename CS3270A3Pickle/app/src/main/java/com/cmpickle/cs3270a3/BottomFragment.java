package com.cmpickle.cs3270a3;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {
    TextView txvPhoneScore;
    TextView txvMeScore;

    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        txvPhoneScore = (TextView) view.findViewById(R.id.txv_phone_score);
        txvMeScore = (TextView) view.findViewById(R.id.txv_me_score);

        return view;
    }

    public void updateValues(int phoneScore, int meScore) {
        txvPhoneScore.setText(String.valueOf(phoneScore));
        txvMeScore.setText(String.valueOf(meScore));
    }
}
