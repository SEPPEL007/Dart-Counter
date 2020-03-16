package com.example.dart_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerManagement extends AppCompatActivity {

    private RecyclerView players;
    private PlayerAdapter playerAdapter;
    private RecyclerView.LayoutManager playerLayoutManager;
    private EditText playerName;
    private ArrayList<Player> player = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_management);
        playerName = findViewById(R.id.playerName);

        try {
            File internalStorageDir = getFilesDir();

            File f = new File(internalStorageDir, "players.csv");
            if (f.exists()) {
                getPlayers();
                playerName.setText("new asASDSADfile");
            }
        } catch (Exception ex){
            //todo
            playerName.setText(ex.toString()); //er wirft diese exception -> er kann wahrscheinlich nicht in den file schreiben, weil er nicht existiert oder so
        }

        players = findViewById(R.id.playerList);

        playerLayoutManager = new LinearLayoutManager(this);

        //getPlayers();
        playerAdapter = new PlayerAdapter(player);
        players.setAdapter(playerAdapter);
        players.setLayoutManager(playerLayoutManager);
    }

    public void createPlayer(View v){
        Player newPlayer = new Player(playerName.getText().toString());
        File internalStorageDir = getFilesDir();
        File players = new File(internalStorageDir, "players.csv");
        try {
            FileWriter writer = new FileWriter(players, true);
            char[] name = playerName.getText().toString().toCharArray();
            writer.write(name);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            FileOutputStream fos = openFileOutput("Players.ser", Context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newPlayer);
            oos.flush();
            fos.close();
            oos.close();
        } catch (Exception ex) {
            playerName.setText(ex.toString());
        }*/
        player.add(newPlayer);
        playerAdapter.notifyItemInserted(player.size()-1);
    }

    public void back(View v){
        finish();
    }

    public void reset(View v){
        File internalStorageDir = getFilesDir();
        File f = new File(internalStorageDir, "players.csv");
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
            playerName.setText(e.toString());
        }
    }

    public void getPlayers(){
        File internalStorageDir = getFilesDir();
        File players = new File(internalStorageDir, "players.csv");
        char[] names = new char[99];
        try {
            FileReader reader = new FileReader(players);
            reader.read(names, 0, 99);
            reader.close();
            if (names[0] == 0) {
                playerName.setText("is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
            playerName.setText("suidhfisdhf");
        } catch (NullPointerException ex){
            playerName.setVisibility(View.INVISIBLE);
        }

        if (names[0] != 0){
            Player newPlayer = new Player(Arrays.toString(names));
            player.add(newPlayer);
            playerName.setText(newPlayer.getName() + player.size());

            playerAdapter.notifyItemInserted(player.size()-1);
        }
        /*try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput("Players.ser"));
            try {
                while (true){
                    Player newPlayer = (Player) ois.readObject();
                    player.add(newPlayer);
                    playerName.setText(newPlayer.getName() + player.size());
                }
            } catch (java.io.StreamCorruptedException ex){
                //is ok
            } catch (Exception ex){
                playerName.setText(ex.toString()); //stream corrupted exception
            }
            ois.close();
        } catch (Exception ex){
            //todo
        }*/
    }
}
