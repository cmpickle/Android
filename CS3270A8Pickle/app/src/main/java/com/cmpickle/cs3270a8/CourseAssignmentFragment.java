package com.cmpickle.cs3270a8;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseAssignmentFragment extends Fragment {

    @BindView(R.id.list_assignments)
    ListView listView;

    public CourseAssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_assignment, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> assignments = new ArrayList<>();

        Bundle bundle = getArguments();
        int length = bundle.getInt("length");
        for(int i = 0; i < length; ++i) {
            assignments.add(bundle.getString("a"+i+1));
        }
        ArrayAdapter<String> assignmentsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(assignmentsAdapter);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseListFragment.class.getName(), "Setting fragment state CourseAssignmentFragment");
        mainActivity.state = MainActivity.COURSE_ASSIGNMENT_INT;

        return view;
    }

}
