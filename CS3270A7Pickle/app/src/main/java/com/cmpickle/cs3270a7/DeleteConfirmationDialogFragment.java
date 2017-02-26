package com.cmpickle.cs3270a7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 2/23/2017.
 */

public class DeleteConfirmationDialogFragment extends DialogFragment {

    public DeleteConfirmationDialogFragment() {
        //required public empty constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setMessage(R.string.permanently_delete)
                .setCancelable(false)
                .setTitle(R.string.are_you_sure)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentManager fragmentManager = getFragmentManager();
                        CourseEditFragment courseEditFragment = (CourseEditFragment) fragmentManager.findFragmentByTag(MainActivity.COURSE_EDIT_FRAGMENT);
                        courseEditFragment.deleteCourse();
                    }
                })
                .create();
    }
}
