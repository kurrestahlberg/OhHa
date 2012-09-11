/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.logic;

/**
 *
 * @author kestahlb
 */
public class GameArea {
    private static GameArea instance = null;
    
    private int rows = 20;
    private int columns = 20;
    
    private Cell[][] cells = null;
    
    public static GameArea getGameArea() {
        if(instance == null) {
            instance = new GameArea();
        }
        
        return instance;
    }
    
    public GameArea() {
        initData();
    }
    
    public GameArea(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        
        initData();
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public Cell getCellAt(int column, int row) {
        return cells[column][row];
    }
    
    public void setCellAt(int column, int row, int type) {
        cells[column][row] = new Cell(type);
    }
    
    private void initData() {
        cells = new Cell[columns][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                cells[j][i] = new Cell(Cell.EMPTY);
            }
        }
    }
}

