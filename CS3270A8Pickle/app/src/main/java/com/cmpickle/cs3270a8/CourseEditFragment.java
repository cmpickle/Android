package com.cmpickle.cs3270a8;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cmpickle.cs3270a8.courseDatabase.CourseListTable;
import com.cmpickle.cs3270a8.courseDatabase.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseEditFragment extends Fragment {

    private final int CREATE = 0;
    private final int UPDATE = 1;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_id)
    EditText etId;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_name)
    TextView tvName;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_name_value)
    EditText etName;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_course_code)
    EditText etCourseCode;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_start_at)
    EditText etStartAt;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_end_at)
    EditText etEndAt;

    @BindView(com.cmpickle.cs3270a8.R.id.save_fab)
    FloatingActionButton saveFab;

    @State
    long _id = -1;

    @State
    int mode = CREATE;

    public CourseEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null && !bundle.isEmpty()) {
            _id = bundle.getLong("_id", -1);
            Log.d(CourseViewFragment.class.getName(), "The received id is " + _id);
        } else {
            Log.d(CourseViewFragment.class.getName(), "New course being created");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.cmpickle.cs3270a8.R.layout.fragment_course_edit, container, false);
        ButterKnife.bind(this, view);

        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()) {
                    tvName.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    return;
                }
                saveCourse();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });


        if(_id != -1) {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Cursor cursor = databaseHelper.getCourseById(_id);
            cursor.moveToFirst();
            etId.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_ID)));
            etName.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_NAME)));
            etCourseCode.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_COURSE_CODE)));
            etStartAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_START_AT)));
            etEndAt.setText(cursor.getString(cursor.getColumnIndex(CourseListTable.COLUMN_END_AT)));
            mode = UPDATE;
        }

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseEditFragment.class.getName(), "Setting fragment state CourseEditFragment");
        mainActivity.state = MainActivity.COURSE_EDIT_INT;

        return view;
    }

    public void saveCourse() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        if(mode==CREATE) {
            databaseHelper.insertCourse(etId.getText().toString(),
                    etName.getText().toString(),
                    etCourseCode.getText().toString(),
                    etStartAt.getText().toString(),
                    etEndAt.getText().toString());
        } else if(mode==UPDATE) {
            databaseHelper.updateCourse(etId.getText().toString(),
                    etName.getText().toString(),
                    etCourseCode.getText().toString(),
                    etStartAt.getText().toString(),
                    etEndAt.getText().toString(), _id);
        }
    }
}
