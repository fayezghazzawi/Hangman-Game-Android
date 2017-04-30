package com.androidcourse.fayez.hang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void startButtonClick(View v)
    {
        EditText player1EditText = (EditText) findViewById(R.id.firstPlayerEditText);
        EditText player2EditText = (EditText) findViewById(R.id.secondPlayerEditText);

        String player1 = player1EditText.getText().toString();
        String player2 = player2EditText.getText().toString();

        if (player1.length() == 0 && player2.length() == 0)
        {
            Log.e(TAG, "Missing Both Players Names");
            Toast.makeText(this, "Please enter Both Players Names", Toast.LENGTH_SHORT).show();
        }
        else if (player1.length() == 0)
        {
            Log.e(TAG, "Missing First Player Name");
            Toast.makeText(this, "Please enter First Player Name", Toast.LENGTH_SHORT).show();
        }
        else if (player2.length() == 0)
        {
            Log.e(TAG, "Missing Second Player Name");
            Toast.makeText(this, "Please enter Second Player Name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i = new Intent(MainActivity.this, FirstPlayerActivity.class);
            i.putExtra("FirstPlayer", player1);
            i.putExtra("SecondPlayer", player2);
            startActivity(i);
            finish();
        }
    }
}
