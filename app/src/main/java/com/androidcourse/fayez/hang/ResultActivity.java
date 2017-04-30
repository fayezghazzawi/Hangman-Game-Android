package com.androidcourse.fayez.hang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ImageView resultImageView = (ImageView)findViewById(R.id.resultImageView);
        String result = getIntent().getExtras().getString("Result");
        if (result.compareTo("Failed") == 0)
            resultImageView.setImageResource(R.drawable.a9);
        else
            resultImageView.setImageResource(R.drawable.a8);

        ((TextView)findViewById(R.id.resultTextView)).setText(result);

        ((TextView)findViewById(R.id.correctWordTextView)).setText("\"" + getIntent().getExtras().getString("Word") + "\"");

        TextView congratsTextView = (TextView)findViewById(R.id.congratsTextView);
        congratsTextView.setText(congratsTextView.getText().toString() + getIntent().getExtras().getString("SecondPlayer"));

        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
                ResultActivity.this.finish();
            }
        });
    }
}
