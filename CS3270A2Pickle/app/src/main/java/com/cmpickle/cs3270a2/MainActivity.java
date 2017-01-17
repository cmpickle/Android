package com.cmpickle.cs3270a2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnLoadFragment2;
    Button btnLoadFragment3;
    Button btnLoadFragment4;
    Button btnSwitch3And4;

    Fragment a;
    Fragment b;
    Fragment c;
    Fragment d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragmentContainer1, a = new FragmentA(), "FA").commit();

        btnLoadFragment2 = (Button) findViewById(R.id.btnLoadFragment2);
        btnLoadFragment3 = (Button) findViewById(R.id.btnLoadFragment3);
        btnLoadFragment4 = (Button) findViewById(R.id.btnLoadFragment4);
        btnSwitch3And4 = (Button) findViewById(R.id.btnSwitch3And4);

        btnLoadFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer2, b = new FragmentB(), "FB").commit();
            }
        });

        btnLoadFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer3, c = new FragmentC(), "FC").commit();
            }
        });

        btnLoadFragment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d == null)
                    getFragmentManager().beginTransaction().add(R.id.fragmentContainer4, d = new FragmentD(), "FD").commit();
            }
        });

        btnSwitch3And4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(c).commit();
                getFragmentManager().beginTransaction().remove(d).commit();
                getFragmentManager().beginTransaction().add(R.id.fragmentContainer3, new FragmentD()).commit();
                getFragmentManager().beginTransaction().add(R.id.fragmentContainer4, new FragmentC()).commit();
            }
        });
    }
}
