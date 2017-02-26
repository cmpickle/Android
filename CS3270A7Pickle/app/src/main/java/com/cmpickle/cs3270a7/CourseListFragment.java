package com.cmpickle.cs3270a7;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.cmpickle.cs3270a7.courseDatabase.DatabaseHelper;
import com.cmpickle.cs3270a7.courseDatabase.CourseListTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseListFragment extends ListFragment implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.add_fab)
    FloatingActionButton addFab;

    SimpleCursorAdapter adapter;

    public CourseListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        ButterKnife.bind(this, view);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.displayCourseViewFragment();
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getAllCourses();
        String[] columns = new String[] {CourseListTable.COLUMN_NAME};
        int[] views = new int[] {android.R.id.text1};
        adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, columns, views, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseListFragment.class.getName(), "Setting fragment state CourseListFragment");
        mainActivity.state = MainActivity.COURSE_LIST_INT;

        return view;
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle args = new Bundle();
        args.putLong("id", id);
        Log.d(CourseListFragment.class.getName(), "The List Item that was clicked has an ID of " + id);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.displayCourseEditFragment(args);
    }

    @Override
    public void onBackStackChanged() {
        MainActivity mainActivity = (MainActivity) getActivity();
        Log.d(CourseListFragment.class.getName(), "Setting fragment state CourseListFragment");
        mainActivity.state = MainActivity.COURSE_LIST_INT;
    }
}
