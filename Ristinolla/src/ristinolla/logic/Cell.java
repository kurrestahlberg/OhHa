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
    private GameArea owner = null;
    int column = 0;
    int row = 0;
    
    Cell(GameArea owner, int column, int row, int type) {
        this.type = type;
        this.owner = owner;
        this.column = column;
        this.row = row;
    }
    
    public int getType() {
        return type;
    }
    
    Cell getNeighborToDir(int direction) {
        if(direction < GameArea.NORTH || direction > GameArea.NORTHWEST) {
            return null;
        }
        
        int row = this.row + GameArea.ROWDIR[direction];
        int column = this.column + GameArea.COLUMNDIR[direction];
        
        return owner.getCellAt(column, row);
    }
}
