package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button gameModeButton;
    public Button playerCountButton;
    public Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameModeButton = findViewById(R.id.gameModeButton);
        playerCountButton = findViewById(R.id.playerCountButton);
        startGame = findViewById(R.id.startGame);

        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("playerCount", playerCountButton.getText().toString());
                i.putExtra("gameMode", gameModeButton.getText().toString());
                startActivity(i);
            }
        });

        gameModeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                gameModeChange();
            }
        });

        playerCountButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                playerNumberChange();
            }
        });
    }

    //Adds 1 to the player count
    //returns to 1 when reaching 4
    public void playerNumberChange(){
        if (Integer.parseInt(playerCountButton.getText().toString()) == 4){
            playerCountButton.setText("1");
        } else {
            playerCountButton.setText(String.valueOf(Integer.parseInt(playerCountButton.getText().toString()) + 1));
        }
    }

    //Adds 200 to the game Type
    //returns to 301 when reaching 901
    public void gameModeChange(){
        if (Integer.parseInt(gameModeButton.getText().toString()) == 901){
            gameModeButton.setText("301");
        } else {
            gameModeButton.setText(String.valueOf(Integer.parseInt(gameModeButton.getText().toString()) + 200));
        }
    }
}
