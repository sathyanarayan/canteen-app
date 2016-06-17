package com.example.ubundu.canteen;

/**
 * Created by ubundu on 7/6/16.
 */
public class Team {
    private int position;
    private String name;
    private String wins, draws, losses,snk1;
    private String points,snk2;

    public Team(int position, String name, String wins, String draws, String losses, String points, String snk1, String snk2)
    {
        this.setPosition(position);
        this.setName(name);
        this.setWins(wins);
        this.setDraws(draws);
        this.setLosses(losses);
        this.setPoints(points);
        this.setSnack1(snk1);
        this.setSnack2(snk2);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setSnack1(String snk1) {this.snk1 = snk1;}

    public String getSnack1(){return snk1;}

    public void setSnack2(String snk2) {this.snk2 = snk2;}

    public String getSnack2(){return snk2;}
}