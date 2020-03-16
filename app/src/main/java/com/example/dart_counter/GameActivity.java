package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public TextView currentPlayer;
    public TextView currentScore;
    public EditText inputScore;
    public Button submitScore;
    public TextView playerField1;
    public TextView playerField2;
    public TextView playerField3;
    public TextView playerField4;
    public Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        final Intent i = getIntent();

        inputScore = findViewById(R.id.inputScore);
        currentPlayer = findViewById(R.id.currentPlayer);
        currentScore = findViewById(R.id.remainingPoints);
        submitScore = findViewById(R.id.submitScore);
        playerField1 = findViewById(R.id.playerField1);
        playerField2 = findViewById(R.id.playerField2);
        playerField3 = findViewById(R.id.playerField3);
        playerField4 = findViewById(R.id.playerField4);

        int playerCount = Integer.parseInt(i.getStringExtra("playerCount"));
        int maxPoints = Integer.parseInt(i.getStringExtra("gameMode"));

        ArrayList<Player> players = new ArrayList<Player>(){{
            add(new Player(i.getStringExtra("playerName1")));
        }};

        if (playerCount > 1){
            players.add(new Player(i.getStringExtra("playerName2")));
            playerField1.setText(i.getStringExtra("playerName1") + " hat noch " + maxPoints + " Punkte");
            playerField1.setVisibility(View.VISIBLE);
            playerField2.setText(i.getStringExtra("playerName2") + " hat noch " + maxPoints + " Punkte");
            playerField2.setVisibility(View.VISIBLE);
        }
        if (playerCount > 2){
            players.add(new Player(i.getStringExtra("playerName3")));
            playerField3.setText(i.getStringExtra("playerName3") + " hat noch " + maxPoints + " Punkte");
            playerField3.setVisibility(View.VISIBLE);
        }
        if (playerCount > 3){
            players.add(new Player(i.getStringExtra("playerName4")));
            playerField4.setText(i.getStringExtra("playerName4") + " hat noch " + maxPoints + " Punkte");
            playerField4.setVisibility(View.VISIBLE);
        }

        game = new Game(playerCount, maxPoints, players);

        currentPlayer.setText(game.getPlayers().get(0).getName());
        currentScore.setText(String.valueOf(maxPoints));


        submitScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newScore();
            }
        });
    }

    public void newScore(){
        int score = Integer.parseInt(inputScore.getText().toString());
        int returnValue = game.setScore(currentPlayer.getText().toString(), score);
        if (returnValue == 2){
            inputScore.setText("");
            inputScore.setHint("Überworfen");
        } else if (returnValue == 1){
            currentScore.setText(String.valueOf( game.getScore(currentPlayer.getText().toString())));
            inputScore.setText("");
        } else {
            Intent i = new Intent(getApplicationContext(), Finish.class);
            i.putExtra("winner", currentPlayer.getText().toString());
            i.putExtra("finish", String.valueOf(score));
            i.putExtra("average", "" + game.getAverage(currentPlayer.getText().toString()));
            startActivity(i);
            /*try{

                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getFilesDir() + "game.ser"));
                out.writeObject(game);
                out.flush();
                out.close();
            } catch (Exception ex){
                currentPlayer.setText(ex.toString());
                Toast toast = Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG);
                toast.show();
            }*/
            finish();
        }
        nextPlayer();
    }

    public void nextPlayer(){
        if (game.getPlayerCount() != 1){
            int index = (game.getPlayers().indexOf(game.findByName(currentPlayer.getText().toString()))+1);
            String player;
            if (index < game.getPlayers().size()){
                player = game.getPlayers().get(index).getName();
            } else {
                player = game.getPlayers().get(0).getName();
            }
            if (index == 1){
                playerField1.setText(currentPlayer.getText().toString() + " hat noch " + game.getScore(currentPlayer.getText().toString()) + " Punkte");
            } else if (index == 2){
                playerField2.setText(currentPlayer.getText().toString() + " hat noch " + game.getScore(currentPlayer.getText().toString()) + " Punkte");
            } else if (index == 3){
                playerField3.setText(currentPlayer.getText().toString() + " hat noch " + game.getScore(currentPlayer.getText().toString()) + " Punkte");
            } else {
                playerField4.setText(currentPlayer.getText().toString() + " hat noch " + game.getScore(currentPlayer.getText().toString()) + " Punkte");
            }
            currentPlayer.setText(player);
            currentScore.setText(String.valueOf(game.getScore(player)));
            inputScore.setHint("");
        }
    }
}
