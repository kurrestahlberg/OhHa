/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.logic;

/**
 *
 * @author kestahlb
 */
public class Cell {
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int CIRCLE = 2;
    
    private int type = EMPTY;
    
    public Cell(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
}
