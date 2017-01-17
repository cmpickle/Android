package com.cmpickle.cs3270a2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnLoadFragment2;
    Button btnLoadFragment3;
    Button btnLoadFragment4;
    Button btnSwitch3And4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragmentContainer1, new FragmentA(), "FA").commit();

        btnLoadFragment2 = (Button) findViewById(R.id.btnLoadFragment2);
        btnLoadFragment3 = (Button) findViewById(R.id.btnLoadFragment3);
        btnLoadFragment4 = (Button) findViewById(R.id.btnLoadFragment4);
        btnSwitch3And4 = (Button) findViewById(R.id.btnSwitch3And4);

        btnLoadFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager().findFragmentById(R.id.fragmentContainer2) == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer2, new FragmentB(), "FB").addToBackStack(null).commit();
            }
        });

        btnLoadFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager().findFragmentById(R.id.fragmentContainer3) == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer3, new FragmentC(), "FC").addToBackStack(null).commit();
            }
        });

        btnLoadFragment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager().findFragmentById(R.id.fragmentContainer4) == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer4, new FragmentD(), "FD").addToBackStack(null).commit();
            }
        });

        btnSwitch3And4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer3, new FragmentD(), "FD").replace(R.id.fragmentContainer4, new FragmentC(), "FC").addToBackStack(null).commit();
            }
        });
    }
}
