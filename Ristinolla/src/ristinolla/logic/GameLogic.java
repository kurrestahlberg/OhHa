package ristinolla.logic;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

/**
 * Pelilogiikan pääluokka joka hoitaa pelin sääntöihin liittyvän toiminnan.
 * 
 * Pelilogiikkaan kuuluvia asioita ovat mm. pelivuoroista huolehtiminen sekä
 * mahdollisen voittajan selvittäminen
 * 
 * @author kurre
 */
public class GameLogic {
    /**
     * Palauterajapinta jonka kautta logiikkaluokka ilmoittaa tilassaan
     * tapahtuneista muutoksista
     */
    public interface Callback {
        /**
         * Tätä metodia kutsutaan kun vuoro vaihtuu
         * @param currentPlayer vuorossa oleva pelaaja
         */
        public void currentPlayerChanged(int currentPlayer);
        /**
         * Tätä metodia kutsutaan kun pelilogiikka on havainnut riittävän monta
         * samaa merkkiä vierekkäisissä ruuduissa, eli suoran
         * 
         * @param playerId suoran kokoon saaneen pelaajan id
         * @param x1 suoran alkuruudun sarake
         * @param y1 suoran alkuruudun rivi
         * @param x2 suoran loppuruudun sarake
         * @param y2 suoran loppuruudun rivi
         */
        public void lineDetected(int playerId, int x1, int y1, int x2, int y2);
        /**
         * Tätä metodia kutsutaan kun logiikka toteaa pelin päättyneen
         * @param winnerId pelin voittaneen pelaajan id
         */
        public void gameOver(int winnerId);
    }
    
    private int currentPlayer = 0;
    private int players = 2;
    private int winningLineLength = 5;
    private GameArea gameArea;
    
    private Callback callback = null;
    
    /**
     * Luo uuden GameLogic-olion
     * @param width Pelialueen leveys
     * @param height Pelialueen korkeus
     */
    public GameLogic(int width, int height, int winningLineLength) {
        this.players = 2;
        this.winningLineLength = winningLineLength;
        currentPlayer = 0;
        gameArea = new GameArea(width, height);
    }
    
    /**
     * Hakee logiikan hallitseman pelialueen
     * @return pelialue
     */
    public GameArea getGameArea() {
        return gameArea;
    }

    /**
     * Asettaa palauteen vastaanottajan
     * @param cb palauterajapinnan toteuttava olio
     */
    public void setCallback(GameLogic.Callback cb) {
        this.callback = cb;
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
    
    /*
    private boolean checkForLines(int column, int row) {
        Cell startingPoint = gameArea.getCellAt(column, row);
        int playerId = startingPoint.getType();
        
        for(int i = 0; i < 4; i++) {
            int count = 1;
            int prevCount = 0;
            Cell first = startingPoint;
            Cell last = startingPoint;
            
            while(count != prevCount && count < winningLineLength) {
                prevCount = count;
                Cell temp = first.getNeighborToDir(i+4);
                if(temp != null && temp.getType() == playerId) {
                    first = temp;
                    count++;
                }
                temp = last.getNeighborToDir(i);
                if(temp != null && temp.getType() == playerId) {
                    last = temp;
                    count++;
                }
            }
            if(count >= winningLineLength) {
                callback.lineDetected(playerId, first.column, first.row, 
                        last.column, last.row);
                callback.gameOver(playerId);
                return true;
            }
        }
        return false;
    }
    */
    
    private boolean checkForLines(int column, int row) {
        Cell startingPoint = gameArea.getCellAt(column, row);
        int playerId = startingPoint.getType();
        Cell cells[] = new Cell[8];
        Arrays.fill(cells, startingPoint);
        for(int i = 0; i < 8; i++) {
            Cell temp = cells[i].getNeighborToDir(i);
            while(temp != null && temp.getType() == playerId) {
                cells[i] = temp;
                if(Math.abs(cells[i].column - cells[(i+4)%8].column) >= (winningLineLength-1) ||
                        Math.abs(cells[i].row - cells[(i+4)%8].row) >= (winningLineLength-1)) {
                    callback.lineDetected(playerId, cells[i].column, cells[i].row, 
                            cells[(i+4)%8].column, cells[(i+4)%8].row);
                    callback.gameOver(playerId);
                    return true;
                }
                temp = cells[i].getNeighborToDir(i);
            }
        }
        return false;
    }

    /**
     * Valitsee ruudun pelialueelta.
     * 
     * Pelilogiikka päättelee omien tietojensa perusteella kenen valitsemaksi
     * ruutu tulee ja voiko sen oikeasti valita. Mikäli ruutu todella tulee
     * valituksi vuoro vaihtuu ja siitä ilmoitetaan 
     * {@link Callback#currentPlayerChanged(int) } -metodin välityksellä.
     * 
     * @param column Valittavan ruudun sarake
     * @param row Valittavan ruudun rivi
     */
    public void cellSelected(int column, int row) {
        if(column >= gameArea.getColumns() || column < 0 || row >= gameArea.getRows() || row < 0) {
            return;
        }
        if(gameArea.getCellAt(column, row).getType() != Cell.EMPTY) {
            return;
        }
        
        switch(currentPlayer) {
            case 0:
                gameArea.setCellAt(column, row, Cell.CIRCLE);
                break;
            case 1:
                gameArea.setCellAt(column, row, Cell.CROSS);
                break;
            default:
                //Uh oh.
                break;
        }
        
        if(!checkForLines(column, row) && gameArea.getFreeCells() == 0) {
            callback.gameOver(-1);
        }
        advanceTurn();
    }
    
    public void saveGame(OutputStream stream) throws IOException {
        DataOutputStream dos = new DataOutputStream(stream);
        
        dos.writeInt(currentPlayer);
        dos.writeInt(players);
        dos.writeInt(winningLineLength);
        gameArea.saveGame(dos);
    }
    
    public void loadGame(InputStream stream) throws IOException {
        DataInputStream dis = new DataInputStream(stream);
        
        currentPlayer = dis.readInt();
        players = dis.readInt();
        winningLineLength = dis.readInt();
        gameArea = new GameArea(dis);
    }
}
