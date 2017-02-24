package com.cmpickle.cs3270a7;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    public static final String COURSE_LIST_FRAGMENT = "courseListFragment";
    public static final String COURSE_VIEW_FRAGMENT = "courseViewFragment";
    public static final String COURSE_EDIT_FRAGMENT = "courseEditFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new CourseListFragment(), COURSE_LIST_FRAGMENT).commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            CourseListFragment courseListFragment = (CourseListFragment) getFragmentManager().findFragmentByTag(COURSE_LIST_FRAGMENT);
            if(courseListFragment != null && courseListFragment.isVisible()) {
//                floatingActionButton.setLayoutParams();
            } else {
                floatingActionButton.show();
            }
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void setFABIcon(final int resId) {
        floatingActionButton.hide(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onHidden(FloatingActionButton fab) {
                fab.setImageResource(resId);
                fab.show();
            }
        });
    }

    public void onFABClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        String tag = currentFragment.getTag();

        switch (tag) {
            case COURSE_LIST_FRAGMENT:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new CourseViewFragment(), COURSE_VIEW_FRAGMENT).addToBackStack(COURSE_VIEW_FRAGMENT).commit();
                break;
            case COURSE_VIEW_FRAGMENT:
                break;
            case COURSE_EDIT_FRAGMENT:
                break;
        }
    }
}
