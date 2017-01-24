package com.cmpickle.cs3270a3;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.topFragmentContainer, new TopFragment(), "top").add(R.id.bottomFragmentContainer, new BottomFragment(), "bottom").commit();
    }

    public void updateBottomFragment(int phoneScore, int meScore) {
        BottomFragment bottomFragment = (BottomFragment) getFragmentManager().findFragmentByTag("bottom");
        bottomFragment.updateValues(phoneScore, meScore);
    }
}
