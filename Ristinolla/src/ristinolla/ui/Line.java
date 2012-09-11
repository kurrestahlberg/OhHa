/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.ui;

/**
 *
 * @author kstahlberg
 */
public class Line {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    private int playerId;
    
    public Line(int playerId, int x1, int y1, int x2, int y2) {
        this.playerId = playerId;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * @return the x1
     */
    public int getX1() {
        return x1;
    }

    /**
     * @return the y1
     */
    public int getY1() {
        return y1;
    }

    /**
     * @return the x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * @return the y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * @return the playerId
     */
    public int getPlayerId() {
        return playerId;
    }
}
