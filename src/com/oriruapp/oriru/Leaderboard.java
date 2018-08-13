package com.oriruapp.oriru;

import java.util.PriorityQueue;

public class Leaderboard {

    private PriorityQueue<LeaderboardName> board = new PriorityQueue<>();

    public void add(String name, int score){
        board.add(new LeaderboardName(name, score));
    }

    public void print(){
        for(LeaderboardName n : board){
            System.out.println(n.getKey() + " " + n.getValue());
        }
    }

    public static void main(){
        Leaderboard lb = new Leaderboard();
        lb.add("aa",10);
        lb.add("bb", 11);
        lb.add("cc", 4);
        lb.print();
    }
}
