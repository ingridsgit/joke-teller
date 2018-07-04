package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.jokejavalib.JokeTeller;
import com.example.jokedisplayer.DisplayActivity;


public class MainActivity extends AppCompatActivity {

    private static final String KEY_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void tellJoke(View view) {
        // Retrieves a random joke from the Java JokeTeller library
        // and passes it as an intent extra to start the DisplayActivity
        // from the Android JokeDisplayer library
        Intent startDisplayActivity = new Intent(this, DisplayActivity.class);
        startDisplayActivity.putExtra(KEY_JOKE, JokeTeller.getAJoke());
        startActivity(startDisplayActivity);
    }


}
