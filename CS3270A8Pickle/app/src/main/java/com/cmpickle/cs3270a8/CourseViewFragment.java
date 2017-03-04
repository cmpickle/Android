package com.cmpickle.cs3270a8;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
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
import android.widget.TextView;

import com.cmpickle.cs3270a8.courseDatabase.DatabaseHelper;
import com.cmpickle.cs3270a8.courseDatabase.CourseListTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseViewFragment extends Fragment {

    @BindView(com.cmpickle.cs3270a8.R.id.tv_id)
    TextView tvId;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_name_value)
    TextView tvName;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_course_code)
    TextView tvCourseCode;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_start_at)
    TextView tvStartAt;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_end_at)
    TextView tvEndAt;

    @State
    long id;

    DatabaseHelper databaseHelper;

    public CourseViewFragment() {
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
        Log.d(CourseViewFragment.class.getName(), "The received id is " + id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(com.cmpickle.cs3270a8.R.menu.menu_course_view, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.cmpickle.cs3270a8.R.layout.fragment_course_view, container, false);

        ButterKnife.bind(this, view);

        new GetCourseById().execute("");

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseViewFragment.class.getName(), "Setting fragment state CourseViewFragment");
        mainActivity.state = MainActivity.COURSE_VIEW_INT;

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case com.cmpickle.cs3270a8.R.id.action_edit:
                editCourse();
                break;
            case com.cmpickle.cs3270a8.R.id.action_delete:
                DeleteConfirmationDialogFragment deleteConfirmationDialogFragment = new DeleteConfirmationDialogFragment();
                deleteConfirmationDialogFragment.setCancelable(false);
                deleteConfirmationDialogFragment.show(getFragmentManager(), null);
                break;
            default:
                break;
        }
        return false;
    }

    public void editCourse() {
        Bundle bundle = new Bundle();
        bundle.putLong("_id", id);
        FragmentManager fragmentManager = getFragmentManager();
        CourseEditFragment courseEditFragment = new CourseEditFragment();
        courseEditFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, courseEditFragment, MainActivity.COURSE_EDIT_FRAGMENT).addToBackStack("courseEdit").commit();
    }

    public void deleteCourse() {
        databaseHelper.deleteCourse(id);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public class GetCourseById extends AsyncTask<String, Integer, Cursor> {
        @Override
        protected Cursor doInBackground(String... params) {
            databaseHelper = new DatabaseHelper(getActivity());
            Cursor cursor = databaseHelper.getCourseById(id);

            cursor.moveToFirst();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            if(cursor.getCount()>0) {
                tvId.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_ID)));
                tvName.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_NAME)));
                tvCourseCode.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_COURSE_CODE)));
                tvStartAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_START_AT)));
                tvEndAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_END_AT)));
            }
        }
    }
}
