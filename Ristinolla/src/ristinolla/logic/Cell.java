package ristinolla.logic;

/**
 * Yksittäinen pelilaudan ruutu.
 * 
 * Jokaista pelilaudan ruutua kohden on yksi Cell-luokan olio joka pitää kirjaa
 * ruudun tilasta (vapaa, risti tai nolla) sekä osaa etsiä oman naapurinsa
 * 
 * @author kestahlb
 */
public class Cell {
    /**
     * Merkitsee tyhjää ruutua
     */
    public static final int EMPTY = 0;
    /**
     * Merkitsee ruutua jonka on valinnut risti
     */
    public static final int CROSS = 1;
    /**
     * Merkitsee ruutua jonka on valinnut nolla
     */
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
    
    /**
     * Palauttaa ruudun tilan, eli tyhjä tai joku pelaajista
     * 
     * @return ruudun tila
     */
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
