package com.oriruapp.oriru;

import java.io.*;
import java.util.PriorityQueue;

public class Leaderboard {

    private PriorityQueue<LeaderboardName> board = new PriorityQueue<>();

    public void add(String name, int score) {
        board.add(new LeaderboardName(name, score));
    }

    public void print() {
        for (LeaderboardName n : board) {
            System.out.println(n.getKey() + " " + n.getValue());
        }
    }

    public String toString() {
        PriorityQueue<LeaderboardName> tmp = new PriorityQueue<LeaderboardName>(board);
        String s = "";
        while(!tmp.isEmpty()) {
            LeaderboardName n = tmp.poll();
            s += n.getKey();
            s += " ";
            s += n.getValue();
            s += "\n";
        }
        return s;
    }

    public PriorityQueue<LeaderboardName> getBoard() {
        return board;
    }

    public void read(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] splitLine = sCurrentLine.split(" ");
                board.add(new LeaderboardName(splitLine[0], Integer.valueOf(splitLine[1])));
            }

        } catch (IOException e) {
            System.out.println("Read leaderboard file error!");
        }
    }

    public void write(String filename) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {
            for (LeaderboardName n : board) {
                writer.write(n.getKey() + " " + n.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Write leaderboard file error!");
        }
    }

    /* test
    public static void main(String []args){
        Leaderboard lb = new Leaderboard();
        lb.read("leaderboard.txt");
        lb.print();
        lb.write("leaderboard.txt");
    }
    */
}
