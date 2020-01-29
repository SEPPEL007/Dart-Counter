package com.example.dart_counter;

import java.util.ArrayList;

public class Game {

    public int playerCount;
    public int maxPoints;
    public ArrayList<Player> players;

    public Game(int playerCount, int maxPoints, ArrayList<Player> players) {
        this.playerCount = playerCount;
        this.maxPoints = maxPoints;
        this.players = players;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int setScore(String name, int score){
        Player player = findByName(name);
        int sum = maxPoints - getScore(name);
        if ((sum+score) > maxPoints){
            //ÜBERWORFEN
            return 2;
        } else if ((sum+score) == maxPoints){
            player.getCurrentScores().add(score);
            return 0;
            //FINISH
        } else {
            player.getCurrentScores().add(score);
            return 1;
        }
    }

    public int getScore(String name){
        int sum = 0;
        for (int i: findByName(name).getCurrentScores()) {
            sum += i;
        }
        return maxPoints - sum;
    }

    public void undo(String name){
        Player player = findByName(name);
        player.getCurrentScores().remove(player.getCurrentScores().size()-1);
    }

    public Player findByName(String name){
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return players.get(0);
    }

    public double getAverage(String name){
        return ((double) Math.round((double) maxPoints/((double) findByName(name).currentScores.size()*3)*100)/100);
    }
}
