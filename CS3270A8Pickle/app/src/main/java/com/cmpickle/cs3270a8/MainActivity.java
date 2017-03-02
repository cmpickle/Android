package com.cmpickle.cs3270a8;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity {

    @BindView(com.cmpickle.cs3270a8.R.id.toolbar)
    Toolbar toolbar;

    FragmentManager fragmentManager = getFragmentManager();

    public static final String COURSE_LIST_FRAGMENT = "courseListFragment";
    public static final String COURSE_VIEW_FRAGMENT = "courseViewFragment";
    public static final String COURSE_EDIT_FRAGMENT = "courseEditFragment";

    public static final int NO_FRAGMENT = -1;
    public static final int COURSE_LIST_INT = 0;
    public static final int COURSE_VIEW_INT = 1;
    public static final int COURSE_EDIT_INT = 2;
    @State
    public int state = NO_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cmpickle.cs3270a8.R.layout.activity_main);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setSupportActionBar(toolbar);

        if(state == NO_FRAGMENT || state == COURSE_LIST_INT) {
            displayCourseListFragment();
        } else if(state == COURSE_VIEW_INT) {
            displayCourseViewFragment();
        } else if(state == COURSE_EDIT_INT) {
            displayCourseEditFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public void displayCourseListFragment() {
        CourseListFragment courseListFragment = (CourseListFragment) fragmentManager.findFragmentByTag(COURSE_LIST_FRAGMENT);
        if (courseListFragment == null)
            courseListFragment = new CourseListFragment();
        fragmentManager.beginTransaction().replace(com.cmpickle.cs3270a8.R.id.fragment_container, courseListFragment, COURSE_LIST_FRAGMENT).commit();
    }

    public void displayCourseViewFragment() {
        CourseViewFragment courseViewFragment = (CourseViewFragment) fragmentManager.findFragmentByTag(COURSE_VIEW_FRAGMENT);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(courseViewFragment == null) {
            courseViewFragment = new CourseViewFragment();
            fragmentTransaction.addToBackStack("courseView");
        }
        fragmentTransaction.replace(com.cmpickle.cs3270a8.R.id.fragment_container, courseViewFragment, COURSE_VIEW_FRAGMENT).commit();
    }

    public void displayCourseEditFragment() {
        displayCourseEditFragment(null);
    }

    public void displayCourseEditFragment(Bundle args) {
        CourseEditFragment courseEditFragment = (CourseEditFragment) fragmentManager.findFragmentByTag(COURSE_EDIT_FRAGMENT);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (courseEditFragment == null) {
            courseEditFragment = new CourseEditFragment();
            courseEditFragment.setArguments(args);
            fragmentTransaction.addToBackStack("courseEdit");
        }
        fragmentTransaction.replace(com.cmpickle.cs3270a8.R.id.fragment_container, courseEditFragment, COURSE_EDIT_FRAGMENT).commit();
    }
}
