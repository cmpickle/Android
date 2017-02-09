package edu.weber.cs3270.rhilton.week5practice;


import android.content.DialogInterface;
import android.os.Bundle;
// non-support library import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    View view;
    Button btnDoSomething;
    TextView txvMessage;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_fragment1, container, false);

        btnDoSomething =
                (Button) view.findViewById(R.id.btnDoSomething);
        btnDoSomething.setOnClickListener(buttonPress);

        return view;
    }

    View.OnClickListener buttonPress = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txvMessage =
                    (TextView) view.findViewById(R.id.txvMessage);
            txvMessage.setText(btnDoSomething.getText().toString());
            Button clickedButton = (Button) v;
            String buttonCaption = clickedButton.getText().toString();
            txvMessage.setText(buttonCaption);
        }
    };


}
