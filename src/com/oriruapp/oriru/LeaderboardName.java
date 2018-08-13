package com.oriruapp.oriru;

public class LeaderboardName  implements Comparable<LeaderboardName> {
    private String key;
    private int value;

    public LeaderboardName(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int compareTo(LeaderboardName other) {
        if(this.getValue() > other.getValue())
            return -1;
        else if(this.getValue() < other.getValue())
            return 1;
        else
            return 0;
    }

}
