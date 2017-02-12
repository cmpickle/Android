package com.cmpickle.cs3270a5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeResultsFragment extends Fragment {

    private EditText etChangeToMakeValue;
    private TextView tvChangeTotalSoFarValue;
    private TextView tvTimeRemainingValue;

    private Timer timer;
    private boolean runningTimer = false;
    private double changeMax = 100;
    private double changeTotalSoFarValue = 0;
    private double changeToMakeValue = 100;
    private int timeRemaining = 15;


    public ChangeResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_results, container, false);

        etChangeToMakeValue = (EditText) view.findViewById(R.id.et_change_to_make_value);
        tvChangeTotalSoFarValue = (TextView) view.findViewById(R.id.tv_change_total_so_far_value);
        tvTimeRemainingValue = (TextView) view.findViewById(R.id.tv_time_remaining_value);

        etChangeToMakeValue.setEnabled(false);

        if(etChangeToMakeValue.getText().toString().equals(""))
            newAmount();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        etChangeToMakeValue = null;
        tvChangeTotalSoFarValue = null;
        tvTimeRemainingValue = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("changeToMakeValue", Double.doubleToLongBits(changeToMakeValue));
        editor.putLong("changeTotalSoFarValue", Double.doubleToLongBits(changeTotalSoFarValue));
        editor.putLong("maximumChangeAmount", Double.doubleToLongBits(changeMax));
        editor.putInt("timeRemaining", timeRemaining);
        editor.apply();

        stopTimer();
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setState(0);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        Log.d("test", "ChangeRusultsFragment - onResume() - Setting etChangetoMakeValue to: " + numberFormat.format(Double.longBitsToDouble(sharedPreferences.getLong("changeToMakeValue", 100))));
        etChangeToMakeValue.setText(numberFormat.format(Double.longBitsToDouble(sharedPreferences.getLong("changeToMakeValue", 100))));
        changeToMakeValue = Double.longBitsToDouble(sharedPreferences.getLong("changeToMakeValue", 100));
        Log.d("test", "ChangeRusultsFragment - onResume() - Setting tvChangeTotalSoFarValue to: " + numberFormat.format(Double.longBitsToDouble(sharedPreferences.getLong("changeTotalSoFar", 0))));
        tvChangeTotalSoFarValue.setText(numberFormat.format(Double.longBitsToDouble(sharedPreferences.getLong("changeTotalSoFarValue", 0))));
        changeTotalSoFarValue = Double.longBitsToDouble(sharedPreferences.getLong("changeTotalSoFarValue", 0));
        Log.d("test", "ChangeRusultsFragment - onResume() - Setting changeMax to: " + Double.longBitsToDouble(sharedPreferences.getLong("maximumChangeAmount", 100)));
        changeMax = Double.longBitsToDouble(sharedPreferences.getLong("maximumChangeAmount", 100));
        Log.d("test", "ChangeRusultsFragment - onResume() - Setting tvTimeRemaining to: " + String.valueOf(sharedPreferences.getInt("timeRemaining", 15)));
        tvTimeRemainingValue.setText(String.valueOf(sharedPreferences.getInt("timeRemaining", 15)));
        timeRemaining = sharedPreferences.getInt("timeRemaining", 15);

        startTimer();
    }

    public void updateChangeTotalSoFar(double amount) {
        changeTotalSoFarValue += amount;

        tvChangeTotalSoFarValue.setText(String.format(getResources().getString(R.string.change_total_so_far_value), changeTotalSoFarValue));

        if(Math.abs(changeTotalSoFarValue - changeToMakeValue) < .0000001) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.incrementCorrectCount();
            stopTimer();

            //Call Success Dialog Fragment
            SuccessDialogFragment successDialogFragment = new SuccessDialogFragment();
            successDialogFragment.setCancelable(false);
            successDialogFragment.show(getFragmentManager(), "success");

            newAmount();
        }
        if(changeTotalSoFarValue > changeToMakeValue) {
            stopTimer();

            //Call Change Overage Dialog Fragment
            ChangeOverageDialogFragment changeOverageFragment = new ChangeOverageDialogFragment();
            changeOverageFragment.setCancelable(false);
            changeOverageFragment.show(getFragmentManager(), "overage");

            startOver();
        }
    }

    public void startOver() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        etChangeToMakeValue.setText(numberFormat.format(changeToMakeValue));
        tvChangeTotalSoFarValue.setText(numberFormat.format(0));
        changeTotalSoFarValue = 0;
        tvTimeRemainingValue.setText(getResources().getString(R.string.fifteen));
        timeRemaining = 15;
    }

    public void newAmount() {
        changeToMakeValue = ThreadLocalRandom.current().nextDouble(0.01, changeMax);
        changeToMakeValue = Math.floor(changeToMakeValue*100) /100;
        Log.d("test", "ChangeResultsFragment - newAmount - changeToMakeValue: " + changeToMakeValue);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        etChangeToMakeValue.setText(numberFormat.format(changeToMakeValue));
        tvChangeTotalSoFarValue.setText(getResources().getString(R.string.zero_cents));
        changeTotalSoFarValue = 0;
        tvTimeRemainingValue.setText(getResources().getString(R.string.fifteen));
        timeRemaining = 15;
    }

    private void decrementTime() {
        tvTimeRemainingValue.setText(String.valueOf(timeRemaining));
    }

    private void timeOut() {
        TimeOutDialogFragment timeOutDialogFragment = new TimeOutDialogFragment();
        timeOutDialogFragment.setCancelable(false);
        timeOutDialogFragment.show(getFragmentManager(), "timeOut");

        timeRemaining = 15;

        startOver();
    }

    public void startTimer() {
        if(!runningTimer) {
            timer = new Timer();
            timer.schedule(getTimerTask(), 1000, 1000);
        }
        runningTimer = true;

    }

    private void stopTimer() {
        timer.cancel();
        runningTimer = false;
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                --timeRemaining;
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        decrementTime();
                    }
                });

                if(timeRemaining < 1) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            timeOut();
                        }
                    });
                    runningTimer = false;
                    this.cancel();
                }
            }
        };
    }
}
