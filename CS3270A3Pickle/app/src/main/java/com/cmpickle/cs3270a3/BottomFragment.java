package com.cmpickle.cs3270a3;


import android.app.Activity;
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

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("txvPhoneScore", txvPhoneScore.getText().toString());
        editor.putString("txvMeScore", txvMeScore.getText().toString());
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        txvPhoneScore.setText(preferences.getString("txvPhoneScore", getResources().getString(R.string.default_value)));
        txvMeScore.setText(preferences.getString("txvMeScore", getResources().getString(R.string.default_value)));
    }

    public void updateValues(int phoneScore, int meScore) {
        txvPhoneScore.setText(String.valueOf(phoneScore));
        txvMeScore.setText(String.valueOf(meScore));
    }
}
