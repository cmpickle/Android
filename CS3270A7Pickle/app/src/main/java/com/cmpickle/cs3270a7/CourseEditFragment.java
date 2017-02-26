package com.cmpickle.cs3270a7;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cmpickle.cs3270a7.courseDatabase.DatabaseHelper;
import com.cmpickle.cs3270a7.courseDatabase.CourseListTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseEditFragment extends Fragment {

    @BindView(R.id.et_id)
    EditText etId;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_course_code)
    EditText etCourseCode;

    @BindView(R.id.et_start_at)
    EditText etStartAt;

    @BindView(R.id.et_end_at)
    EditText etEndAt;

    @State
    long id;

    DatabaseHelper databaseHelper;

    public CourseEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Icepick.restoreInstanceState(this, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null && !bundle.isEmpty())
            id = bundle.getLong("id", 0);
        Log.d(CourseEditFragment.class.getName(), "The received id is " + id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_course_edit, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_edit, container, false);

        ButterKnife.bind(this, view);

        databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getCourseById(id);

        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            etId.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_ID)));
            etName.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_NAME)));
            etCourseCode.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_COURSE_CODE)));
            etStartAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_START_AT)));
            etEndAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_END_AT)));
        }

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseEditFragment.class.getName(), "Setting fragment state CourseEditFragment");
        mainActivity.state = MainActivity.COURSE_EDIT_INT;

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveCourse();
                break;
            case R.id.action_delete:
                DeleteConfirmationDialogFragment deleteConfirmationDialogFragment = new DeleteConfirmationDialogFragment();
                deleteConfirmationDialogFragment.setCancelable(false);
                deleteConfirmationDialogFragment.show(getFragmentManager(), null);
                break;
            default:
                break;
        }
        return false;
    }

    public void saveCourse() {
        databaseHelper.updateCourse(etId.getText().toString(), etName.getText().toString(), etCourseCode.getText().toString(), etStartAt.getText().toString(), etEndAt.getText().toString(), id);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentManager.findFragmentByTag(MainActivity.COURSE_LIST_FRAGMENT)).commit();
    }

    public void deleteCourse() {
        databaseHelper.deleteCourse(id);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.displayCourseListFragment();
    }
}
