package com.popup.anywherepopup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anywherepopup.view.AnimationStyle;
import com.anywherepopup.view.AnyWherePopUp;


public class MainActivity extends ActionBarActivity {
    private int i = 0;
    private AnyWherePopUp pop;
    int[] anims = {AnimationStyle.FADE,
            AnimationStyle.SLIDE_TOP_IN_TOP_OUT,
            AnimationStyle.SLIDE_BOTTOM_IN_BOTTOM_OUT,
            AnimationStyle.SLIDE_RIGHT_IN_RIGHT_OUT,
            AnimationStyle.SLIDE_LEFT_IN_LEFT_OUT,
            AnimationStyle.SLIDE_RIGHT_IN_LEFT_OUT,
            AnimationStyle.SLIDE_BOTTOM_IN_TOP_OUT,
            AnimationStyle.SLIDE_TOP_IN_BOTTOM_OUT,
            AnimationStyle.SLIDE_LEFT_IN_RIGHT_OUT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pop = new AnyWherePopUp(MainActivity.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void toggleAnimation(View v) {

        try {
            pop.setAnimation(anims[i]);
            pop.show(v, R.layout.layout);
            i++;
        } catch (Exception e) {
            i = 0;
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
