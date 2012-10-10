package ristinolla.logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Pelialueen hallintaluokka.
 * 
 * Tämä luokka kapseloi pelialueen kokonaisuudessaan ja pitää hallussaan
 * viitteet Cell-luokan olioihin jotka kukin edustavat yhtä pelialueen ruutua.
 * GameArea-luokka hallinnoi ruutuihin tulevia muutoksia sekä pitää kirjaa 
 * ruudukon vapaista ruuduista.
 * @author kestahlb
 */
public class GameArea {

    /**
     * Suunta ylös
     */
    public static final int NORTH = 0;
    /**
     * Suunta yläoikealle
     */
    public static final int NORTHEAST = 1;
    /**
     * Suunta oikealle
     */
    public static final int EAST = 2;
    /**
     * Suunta alaoikealle
     */
    public static final int SOUTHEAST = 3;
    /**
     * Suunta alas
     */
    public static final int SOUTH = 4;
    /**
     * Suunta alavasemmalle
     */
    public static final int SOUTHWEST = 5;
    /**
     * Suunta vasemmalle
     */
    public static final int WEST = 6;
    /**
     * Suunta ylävasemmalle
     */
    public static final int NORTHWEST = 7;
    
    /**
     * Taulukko jossa kaikkien suuntien sarakesiirrokset, auttaa iteroimaan 
     * eri suunnat läpi
     */
    public static final int COLUMNDIR[] = {0, 1, 1, 1, 0, -1, -1, -1};
    /**
     * Taulukko jossa kaikkien suuntien rivisiirrokset, auttaa iteroimaan 
     * eri suunnat läpi
     */
    public static final int ROWDIR[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    private int rows = 20;
    private int columns = 20;
    
    private int freeCells = 0;
    
    private Cell[][] cells = null;

    GameArea() {
        initData();
    }
    
    GameArea(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        
        initData();
    }
    
    GameArea(DataInputStream dis) throws IOException {
        this.rows = dis.readInt();
        this.columns = dis.readInt();
        this.freeCells = dis.readInt();
        
        initData(dis);
    }
    
    /**
     * Rivien määrä
     * @return Rivien määrä
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Sarakkeiden määrä
     * @return Sarakkeiden määrä
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Hakee ruudun annetulta sarakkeelta ja riviltä
     * @param column Halutun ruudun sarake
     * @param row Halutun ruudun rivi
     * @return haettu ruutu
     */
    public Cell getCellAt(int column, int row) {
        if(column < 0 || column >= getColumns() || row < 0 || row >= getRows()) {
            return null;
        }
        
        return cells[column][row];
    }
    
    void setCellAt(int column, int row, int type) {
        Cell c = getCellAt(column, row);
        if(c.getType() == type) {
            return;
        }
        
        if(c.getType() == Cell.EMPTY) {
            freeCells--;
        }
        if(type == Cell.EMPTY) {
            freeCells++;
        }
        
        cells[column][row] = new Cell(this, column, row, type);
    }
    
    private void initData() {
        cells = new Cell[columns][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                cells[j][i] = new Cell(this, j, i, Cell.EMPTY);
            }
        }
        
        freeCells = columns * rows;
    }

    private void initData(DataInputStream dis) throws IOException {
        cells = new Cell[columns][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                cells[j][i] = new Cell(this, j, i, dis.readInt());
            }
        }
    }
    
    /**
     * Hakee vapaiden ruutujen määrän
     * @return vapaiden ruutujen määrä
     */
    public int getFreeCells() {
        return freeCells;
    }
    
    public void saveGame(DataOutputStream dos) throws IOException {
        dos.writeInt(rows);
        dos.writeInt(columns);
        dos.writeInt(freeCells);
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                dos.writeInt(cells[j][i].getType());
            }
        }
        
    }
    
}

