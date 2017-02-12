package com.cmpickle.cs3270a5;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeOutDialogFragment extends DialogFragment {


    public TimeOutDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setMessage(R.string.you_should_try_again)
                .setCancelable(false)
                .setTitle(R.string.you_took_too_long)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.startTimer();
                    }
                }).create();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
}
