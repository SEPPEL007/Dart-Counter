package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button gameModeButton;
    public Button playerCountButton;
    public Button startGame;
    public EditText playerName1;
    public EditText playerName2;
    public EditText playerName3;
    public EditText playerName4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameModeButton = findViewById(R.id.gameModeButton);
        playerCountButton = findViewById(R.id.playerCountButton);
        startGame = findViewById(R.id.startGame);
        playerName1 = findViewById(R.id.playerName1);
        playerName2 = findViewById(R.id.playerName2);
        playerName3 = findViewById(R.id.playerName3);
        playerName4 = findViewById(R.id.playerName4);

        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("playerCount", playerCountButton.getText().toString());
                i.putExtra("gameMode", gameModeButton.getText().toString());
                i.putExtra("playerName1", playerName1.getText().toString());
                i.putExtra("playerName2", playerName2.getText().toString());
                i.putExtra("playerName3", playerName3.getText().toString());
                i.putExtra("playerName4", playerName4.getText().toString());
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

    public void playerManagement(View v){
        Intent i = new Intent(getApplicationContext(), PlayerManagement.class);
        startActivity(i);
    }

    //Adds 1 to the player count
    //returns to 1 when reaching 4
    public void playerNumberChange(){
        if (Integer.parseInt(playerCountButton.getText().toString()) == 4){
            playerCountButton.setText("1");
            playerName2.setVisibility(View.INVISIBLE);
            playerName3.setVisibility(View.INVISIBLE);
            playerName4.setVisibility(View.INVISIBLE);
        } else {
            int index = Integer.parseInt(playerCountButton.getText().toString()) + 1;
            playerCountButton.setText(String.valueOf(index));
            if (index == 2){
                playerName2.setVisibility(View.VISIBLE);
            } else if (index == 3){
                playerName3.setVisibility(View.VISIBLE);
            } else {
                playerName4.setVisibility(View.VISIBLE);
            }
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
