package com.cmpickle.cs3270a8;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
//        ListAdapter listAdapter = new SimpleAdapter(getActivity(), assignments, R.id.list_assignments, null, null);
//        listView.setAdapter();
        return view;
    }

}
