package com.cmpickle.cs3270a3;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    Button play;
    TextView txvPhoneChoice;
    TextView txvMeChoice;
    TextView txvResult;

    int winsPhone;
    int winsMe;
    boolean winsPhoneFlag;
    boolean winsMeFlag;
    String[] choices;
    String[] results;
    Random rand;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        txvPhoneChoice = (TextView) view.findViewById(R.id.txv_phone_result);
        txvMeChoice = (TextView) view.findViewById(R.id.txv_me_result);
        txvResult = (TextView) view.findViewById(R.id.txv_result);

        winsPhone = 0;
        winsMe = 0;
        winsPhoneFlag = false;
        winsMeFlag = false;

        choices = getResources().getStringArray(R.array.choices);
        results = getResources().getStringArray(R.array.results);
        rand = new Random();

        play = (Button) view.findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int phoneChoice = rand.nextInt(3);
                int meChoice = rand.nextInt(3);
                txvPhoneChoice.setText(choices[phoneChoice]);
                txvMeChoice.setText(choices[meChoice]);

                if (phoneChoice == meChoice) {
                    txvResult.setText(results[2]);
                } else if (phoneChoice == 0 && meChoice == 1 || phoneChoice == 1 && meChoice == 2 || phoneChoice == 2 && meChoice == 0) {
                    txvResult.setText(results[1]);
                    ++winsMe;
                } else if (phoneChoice == 0 && meChoice == 2 || phoneChoice == 1 && meChoice == 0 || phoneChoice == 2 && meChoice == 1) {
                    txvResult.setText(results[0]);
                    ++winsPhone;
                }

                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.updateBottomFragment(winsPhone, winsMe);

                if(winsPhone == 4 && !winsPhoneFlag) {
                    Toast.makeText(mainActivity, "The Phone needs one point to win!", Toast.LENGTH_SHORT).show();
                    winsPhoneFlag = !winsPhoneFlag;
                }
                if(winsMe == 4 && !winsMeFlag) {
                    Toast.makeText(mainActivity, "You only need one point to win!", Toast.LENGTH_SHORT).show();
                    winsMeFlag = !winsMeFlag;
                }
                if(winsPhone == 5 || winsMe == 5) {
                    PlayAgainDialogFragment dialog = new PlayAgainDialogFragment();
                    dialog.setCancelable(false);
                    dialog.show(getFragmentManager(), "playAgain");
                    winsMe = winsPhone = 0;
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("txvPhoneChoice", txvPhoneChoice.getText().toString());
        editor.putString("txvMeChoice", txvMeChoice.getText().toString());
        editor.putString("txvResult", txvResult.getText().toString());
        editor.putInt("intWinsPhone", winsPhone);
        editor.putInt("intWinsMe", winsMe);
        editor.putBoolean("boolPhoneMeFlag", winsPhoneFlag);
        editor.putBoolean("boolWinsMeFlag", winsMeFlag);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        txvPhoneChoice.setText(preferences.getString("txvPhoneChoice", getResources().getString(R.string.placeholder)));
        txvMeChoice.setText(preferences.getString("txvMeChoice", getResources().getString(R.string.placeholder)));
        txvResult.setText(preferences.getString("txvResult", ""));
        winsPhone = preferences.getInt("intWinsPhone", 0);
        winsMe = preferences.getInt("intWinsMe", 0);
        winsPhoneFlag = preferences.getBoolean("boolWinsPhoneFlag", false);
        winsMeFlag = preferences.getBoolean("boolWinsMeFlag", false);
    }
}
