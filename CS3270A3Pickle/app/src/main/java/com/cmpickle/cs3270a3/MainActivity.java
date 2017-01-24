package com.cmpickle.cs3270a3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("txvPhoneChoice", getResources().getString(R.string.placeholder));
        editor.putString("txvMeChoice", getResources().getString(R.string.placeholder));
        editor.putString("txvResult", "");
        editor.putInt("intWinsPhone", 0);
        editor.putInt("intWinsMe", 0);
        editor.putString("txvPhoneScore", getResources().getString(R.string.default_value));
        editor.putString("txvMeScore", getResources().getString(R.string.default_value));
        editor.putBoolean("boolWinsPhoneFlag", false);
        editor.putBoolean("boolWinsMeFlag", false);
        editor.apply();

        getFragmentManager().beginTransaction().add(R.id.topFragmentContainer, new TopFragment(), "top").add(R.id.bottomFragmentContainer, new BottomFragment(), "bottom").commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getFragmentManager().beginTransaction()
                .remove(getFragmentManager().findFragmentByTag("top"))
                .remove(getFragmentManager().findFragmentByTag("bottom"))
                .commit();
    }

    public void updateBottomFragment(int phoneScore, int meScore) {
        BottomFragment bottomFragment = (BottomFragment) getFragmentManager().findFragmentByTag("bottom");
        bottomFragment.updateValues(phoneScore, meScore);
    }

    public void playAgain() {
        getFragmentManager().beginTransaction()
                .replace(R.id.topFragmentContainer, new TopFragment(), "top")
                .replace(R.id.bottomFragmentContainer, new BottomFragment(), "bottom")
                .remove(getFragmentManager().findFragmentByTag("playAgain"))
                .commit();
        SharedPreferences preferences = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("txvPhoneChoice", getResources().getString(R.string.placeholder));
        editor.putString("txvMeChoice", getResources().getString(R.string.placeholder));
        editor.putString("txvResult", "");
        editor.putInt("intWinsPhone", 0);
        editor.putInt("intWinsMe", 0);
        editor.putString("txvPhoneScore", getResources().getString(R.string.default_value));
        editor.putString("txvMeScore", getResources().getString(R.string.default_value));
        editor.putBoolean("boolWinsPhoneFlag", false);
        editor.putBoolean("boolWinsMeFlag", false);
        editor.apply();
        getFragmentManager().findFragmentByTag("top").onResume();
        getFragmentManager().findFragmentByTag("bottom").onResume();
    }
}
