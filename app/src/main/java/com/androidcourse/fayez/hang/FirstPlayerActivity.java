package com.androidcourse.fayez.hang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPlayerActivity extends AppCompatActivity {

    private String TAG = "FIRST PLAYER ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_player);
        String firstPlayer = getIntent().getExtras().getString("FirstPlayer");
        TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        helloTextView.setText(helloTextView.getText().toString() + " " + firstPlayer);
    }

    protected void playButtonClick(View v)
    {
        EditText wordEditText = (EditText) findViewById(R.id.wordEditText);
        EditText hintEditText = (EditText) findViewById(R.id.hintEditText);

        String word = wordEditText.getText().toString();
        String hint = hintEditText.getText().toString();

        if (word.length() == 0 && hint.length() == 0)
        {
            Log.e(TAG, "Missing Word and Hint");
            Toast.makeText(this, "Please enter a Word and a Hint", Toast.LENGTH_SHORT).show();
        }
        else if (word.length() == 0)
        {
            Log.e(TAG, "Missing Word ");
            Toast.makeText(this, "Please enter a Word ", Toast.LENGTH_SHORT).show();
        }
        else if (hint.length() == 0)
        {
            Log.e(TAG, "Missing Hint");
            Toast.makeText(this, "Please enter a Hint", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(FirstPlayerActivity.this, GameActivity.class);
            i.putExtra("SecondPlayer", getIntent().getExtras().getString("SecondPlayer"));
            i.putExtra("Word", word);
            i.putExtra("Hint", hint);
            startActivity(i);
            finish();
        }
    }
}
