package com.cmpickle.cs3270a5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 2/9/2017.
 */

public class ChangeOverageDialogFragment extends DialogFragment {

    public ChangeOverageDialogFragment() {
        //mandatory empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setMessage(R.string.you_should_try_again)
                .setCancelable(false)
                .setTitle(R.string.thats_too_much_change)
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
