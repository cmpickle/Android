package com.cmpickle.cs3270a7;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseListFragment extends Fragment {


    public CourseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) mainActivity.floatingActionButton.getLayoutParams();
        lp.gravity = Gravity.BOTTOM | Gravity.START;
        mainActivity.floatingActionButton.setLayoutParams(lp);
        mainActivity.floatingActionButton.setImageResource(android.R.drawable.ic_input_add);

        return view;
    }

}
