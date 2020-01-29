package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends AppCompatActivity {

    public TextView finishScore;
    public TextView playerName;
    public TextView average;
    public Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        finishScore = findViewById(R.id.finishScore);
        playerName = findViewById(R.id.playerName);
        average = findViewById(R.id.average);
        newGame = findViewById(R.id.newGame);

        Intent i = getIntent();
        finishScore.setText("Ihr finish war: " + i.getStringExtra("finish"));
        playerName.setText(i.getStringExtra("winner"));
        average.setText("Ihr average war: " + i.getStringExtra("average"));

        newGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }
}
