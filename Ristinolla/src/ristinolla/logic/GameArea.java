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

    public interface GameAreaCallback {
        public void currentPlayerChanged(int currentPlayer);
        public void lineDetected(int playerId, int x1, int y1, int x2, int y2);
        public void gameOver(int winnerId);
    }
    
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;
    
    public static final int COLUMNDIR[] = {0, 1, 1, 1, 0, -1, -1, -1};
    public static final int ROWDIR[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    private static GameArea instance = null;
    
    private int rows = 20;
    private int columns = 20;
    
    private Cell[][] cells = null;
    
    int currentPlayer = 0;
    int players = 2;
    
    private GameAreaCallback callback = null;
    
    public static GameArea getGameArea() {
        if(instance == null) {
            instance = new GameArea();
        }
        
        return instance;
    }
    
    public GameArea() {
        initData();
    }
    
    public GameArea(int columns, int rows, int players) {
        this.rows = rows;
        this.columns = columns;
        this.players = players;
        
        initData();
    }
    
    public void setCallback(GameAreaCallback cb) {
        this.callback = cb;
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
    
    private void setCellAt(int column, int row, int type) {
        cells[column][row] = new Cell(this, column, row, type);
    }
    
    private void initData() {
        cells = new Cell[columns][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                setCellAt(j, i, Cell.EMPTY);
            }
        }
        
        currentPlayer = 0;
    }
    
    public void cellSelected(int column, int row) {
        if(column > getColumns() || column < 0 || row > getRows() || row < 0) {
            return;
        }
        if(getCellAt(column, row).getType() != Cell.EMPTY) {
            return;
        }
        
        switch(currentPlayer) {
            case 0:
                setCellAt(column, row, Cell.CIRCLE);
                break;
            case 1:
                setCellAt(column, row, Cell.CROSS);
                break;
            default:
                //Uh oh.
                break;
        }
        checkForLines(column, row);
        advanceTurn();
    }

    private void advanceTurn() {
        currentPlayer++;
        if(currentPlayer >= players) {
            currentPlayer = 0;
        }
        
        if(callback != null) {
            callback.currentPlayerChanged(currentPlayer);
        }
    }
    
    private void checkForLines(int column, int row) {
        Cell startingPoint = getCellAt(column, row);
        int playerId = startingPoint.getType();
        
        for(int i = 0; i < 4; i++) {
            int count = 1;
            Cell first = startingPoint;
            Cell last = startingPoint;   
            
            Cell neighbor = startingPoint.getNeighborToDir(i);
            while(neighbor != null && neighbor.getType() == playerId) {
                count++;
                if(count >= 5) {
                    last = neighbor;
                    break;
                }
                last = neighbor;
                neighbor = neighbor.getNeighborToDir(i);
            }
            if(count < 5) {
                neighbor = startingPoint.getNeighborToDir(i + 4);
                while(neighbor != null && neighbor.getType() == playerId) {
                    count++;
                    if(count >= 5) {
                        first = neighbor;
                        break;
                    }
                    neighbor = neighbor.getNeighborToDir(i + 4);
                }
            }
            
            if(count == 5) {
                callback.lineDetected(playerId, first.column, first.row, 
                        last.column, last.row);
                callback.gameOver(playerId);
            }
        }
    }
}

