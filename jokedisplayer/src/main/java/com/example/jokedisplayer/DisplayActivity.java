package com.example.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private static final String KEY_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent openIntent = getIntent();
        String joke = openIntent.getStringExtra(KEY_JOKE);

        TextView jokeTextView = findViewById(R.id.joke_text_view);
        jokeTextView.setText(joke);

    }
}
