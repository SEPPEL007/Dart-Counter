package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    public TextView playerCount;
    public TextView currentScore;
    public EditText inputScore;
    public Button submitScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        Intent i = getIntent();

        inputScore = findViewById(R.id.inputScore);
        playerCount = findViewById(R.id.playerCount);
        currentScore = findViewById(R.id.remainingPoints);
        submitScore = findViewById(R.id.submitScore);

        playerCount.setText(i.getStringExtra("playerCount"));
        currentScore.setText(i.getStringExtra("gameMode"));

        submitScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newScore();
            }
        });
    }

    public void newScore(){
        int points = Integer.parseInt(inputScore.getText().toString());
        int score = Integer.parseInt(currentScore.getText().toString());
        if ((score - points) < 0) {
            inputScore.setText("Überworfen");
        } else if ((score - points) > 0) {
            currentScore.setText(String.valueOf(score-points));
            inputScore.setText("");
        } else{
            currentScore.setText("U WON");
            inputScore.setText("Your finish was: " + points);
        }
    }
}
