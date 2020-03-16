package com.example.dart_counter;

import java.util.ArrayList;

public class Player implements java.io.Serializable {

    public String name;
    public ArrayList<Integer> currentScores;

    public Player(String name){
        this.name = name;
        currentScores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getCurrentScores() {
        return currentScores;
    }
}
