package com.androidcourse.fayez.hang;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private String TAG = "GAME ACTIVITY";

    private int numberOfFails = 0;
    private int numberOfSuccesses = 0;

    private String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        word = getIntent().getExtras().getString("Word");
        LinearLayout wordLayout = (LinearLayout) findViewById(R.id.wordLayout);
        for (int i = 0; i < word.length(); i++) {
            TextView letter = (TextView) getLayoutInflater().inflate(R.layout.letter_textview, null);
            wordLayout.addView(letter);
        }

        String secondPlayer = getIntent().getExtras().getString("SecondPlayer");
        TextView helloGameView = (TextView)findViewById(R.id.helloGameView);
        helloGameView.setText(helloGameView.getText().toString() + " " + secondPlayer);

        Button hintButton = (Button) findViewById(R.id.hintButton);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity.this, getIntent().getExtras().getString("Hint"), Toast.LENGTH_LONG).show();
                numberOfFails++;
                setHang();
            }
        });
    }

    protected void checkButtonClick(View v)
    {
        EditText letterEditText = (EditText)findViewById(R.id.letterEditText);
        String letterString = letterEditText.getText().toString();

        if (letterString.length() == 0)
        {
            Log.e(TAG, "Missing Letter");
            Toast.makeText(this, "Please enter a Letter", Toast.LENGTH_SHORT).show();
        }
        else
        {
            boolean correctLetter = false;
            LinearLayout wordLayout = (LinearLayout) findViewById(R.id.wordLayout);
            char letter = letterString.charAt(0);
            for (int i = 0; i < word.length(); i++)
            {
                if (letter == word.charAt(i))
                {
                    correctLetter = true;
                    numberOfSuccesses++;
                    TextView matchingLetter = (TextView)wordLayout.getChildAt(i);
                    matchingLetter.setText(Character.toString(letter));
                }
            }

            if (!correctLetter)
            {
                numberOfFails++;
                setHang();
            }
            letterEditText.setText("");
        }

        if (numberOfSuccesses == word.length())
        {
            Intent i = new Intent(GameActivity.this, ResultActivity.class);
            i.putExtra("Result", "Succeeded");
            i.putExtra("SecondPlayer", getIntent().getExtras().getString("SecondPlayer"));
            i.putExtra("Word", word);
            startActivity(i);
            finish();
        }
    }

    private void setHang()
    {
        ImageView hangImage = (ImageView)findViewById(R.id.hangImageView);
        switch (numberOfFails)
        {
            case 1:
                hangImage.setImageResource(R.drawable.a2);
                break;
            case 2:
                hangImage.setImageResource(R.drawable.a3);
                break;
            case 3:
                hangImage.setImageResource(R.drawable.a4);
                break;
            case 4:
                hangImage.setImageResource(R.drawable.a5);
                break;
            case 5:
                hangImage.setImageResource(R.drawable.a6);
                break;
            case 6:
                hangImage.setImageResource(R.drawable.a7);
                break;
            case 7:
                Intent i = new Intent(GameActivity.this, ResultActivity.class);
                i.putExtra("Result", "Failed");
                i.putExtra("SecondPlayer", getIntent().getExtras().getString("SecondPlayer"));
                i.putExtra("Word", word);
                startActivity(i);
                finish();
        }
    }
}
