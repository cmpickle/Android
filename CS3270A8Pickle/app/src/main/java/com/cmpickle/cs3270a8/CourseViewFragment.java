package com.cmpickle.cs3270a8;


import android.app.Activity;
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

import com.cmpickle.cs3270a8.courseDatabase.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseViewFragment extends Fragment {

    @BindView(com.cmpickle.cs3270a8.R.id.et_id)
    EditText etId;

    @BindView(com.cmpickle.cs3270a8.R.id.tv_name)
    TextView tvName;

    @BindView(com.cmpickle.cs3270a8.R.id.et_name)
    EditText etName;

    @BindView(com.cmpickle.cs3270a8.R.id.et_course_code)
    EditText etCourseCode;

    @BindView(com.cmpickle.cs3270a8.R.id.et_start_at)
    EditText etStartAt;

    @BindView(com.cmpickle.cs3270a8.R.id.et_end_at)
    EditText etEndAt;

    @BindView(com.cmpickle.cs3270a8.R.id.save_fab)
    FloatingActionButton saveFab;

    public CourseViewFragment() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.cmpickle.cs3270a8.R.layout.fragment_course_view, container, false);
        ButterKnife.bind(this, view);

        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()) {
                    tvName.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    return;
                }
                saveCourse();

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.displayCourseListFragment();
            }
        });

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseViewFragment.class.getName(), "Setting fragment state CourseViewFragment");
        mainActivity.state = MainActivity.COURSE_VIEW_INT;

        return view;
    }

    public void saveCourse() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.insertCourse(etId.getText().toString(),
                etName.getText().toString(),
                etCourseCode.getText().toString(),
                etStartAt.getText().toString(),
                etEndAt.getText().toString());
    }
}
