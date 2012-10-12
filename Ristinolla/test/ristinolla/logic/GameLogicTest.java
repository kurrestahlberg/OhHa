package ristinolla.logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kurre
 */
public class GameLogicTest {
    
    boolean currentPlayerChangeCalled = false;
    boolean lineDetectedCalled = false;
    boolean gameOverCalled = false;
    int currentPlayer = -1;
    int winner = -2;
    
    private final GameLogic.Callback cb = new GameLogic.Callback() {

        @Override
        public void currentPlayerChanged(int cp) {
            currentPlayerChangeCalled = true;
            currentPlayer = cp;
        }

        @Override
        public void lineDetected(int playerId, int x1, int y1, int x2, int y2) {
            lineDetectedCalled = true;
        }

        @Override
        public void gameOver(int winnerId) {
            gameOverCalled = true;
            winner = winnerId;
        }

    };
        
    public GameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cellSelected method, of class GameArea.
     */
    @Test
    public void testCellSelected() {
        System.out.println("cellSelected");
        int column = 5;
        int row = 5;
        GameLogic instance = new GameLogic(20, 20, 5);
        
        Cell c = instance.getGameArea().getCellAt(column, row);
        assertTrue("c.getType() == Cell.EMPTY", c.getType() == Cell.EMPTY);
        
        instance.cellSelected(column, row);

        c = instance.getGameArea().getCellAt(column, row);
        assertTrue("c.getType() != Cell.EMPTY", c.getType() != Cell.EMPTY);
        
    }

    /**
     * Test of cellSelected method, of class GameArea.
     */
    @Test
    public void testCellSelectedNonEmptyCell() {
        System.out.println("cellSelected - NonEmptyCell");
        int column = 5;
        int row = 5;
        GameLogic instance = new GameLogic(20, 20, 5);
        instance.setCallback(cb);
        currentPlayerChangeCalled = false;
        
        instance.cellSelected(column, row);
        assertTrue(currentPlayerChangeCalled);
        currentPlayerChangeCalled = false;
        instance.cellSelected(column, row);
        assertFalse(currentPlayerChangeCalled);
    }

        /**
     * Test of cellSelected method, of class GameArea.
     */
    @Test
    public void testCellSelectedOutsideGameArea() {
        System.out.println("cellSelectedOutsideGameArea");
        int column = 5;
        int row = 5;
        GameLogic instance = new GameLogic(20, 20, 5);
        
        //These are outside of the game area, should not crash!
        instance.cellSelected(54, 45);
        instance.cellSelected(-4, -2);
        instance.cellSelected(20, 20);
        
    }

    /**
     * Test of setCallback method, of class GameArea.
     */
    @Test
    public void testSetCallback() {
        System.out.println("setCallback");
        
        currentPlayerChangeCalled = false;
        lineDetectedCalled = false;
        gameOverCalled = false;
        
        GameLogic instance = new GameLogic(20, 20, 5);
        
        instance.setCallback(cb);
        int prevCurPlayer = currentPlayer;
        
        for(int i = 0; i < 5; i++) {
            instance.cellSelected(i + 5, 5);
            assertTrue("currentPlayerChangeCalled " + i, currentPlayerChangeCalled);
            assertTrue("currentPlayer != prevCurPlayer " + i, currentPlayer != prevCurPlayer);
            currentPlayerChangeCalled = false;
            instance.cellSelected(i + 5, 6);
            assertTrue("currentPlayerChangeCalled (2) " + i, currentPlayerChangeCalled);
            assertTrue("currentPlayer != prevCurPlayer (2) " + i, currentPlayer != prevCurPlayer);
            currentPlayerChangeCalled = false;
        }
        
        assertTrue("lineDetectedCalled", lineDetectedCalled);
        assertTrue("gameOverCalled", gameOverCalled);
    }

    /**
     * Test of setCallback method, of class GameArea.
     */
    @Test
    public void testFillGameArea() {
        System.out.println("fill game area");
        
        gameOverCalled = false;
        
        GameLogic instance = new GameLogic(2, 2, 5);
        
        instance.setCallback(cb);
        
        for(int i = 0; i < 2; i++) {
            instance.cellSelected(i, 0);
            instance.cellSelected(i, 1);
        }
        
        assertTrue("gameOverCalled", gameOverCalled);
        assertTrue(winner == -1);
    }
    
    /**
     * Test loading and saving the game with no attention paid to
     * the actual data.
     */
    @Test
    public void testLoadAndSave() {
        try {
            System.out.println("testLoadAndSave");
            int column = 12;
            int row = 13;
            GameLogic instance = new GameLogic(19, 14, 7);
            
            instance.cellSelected(column, row);
            
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            instance.saveGame(str);

            instance = new GameLogic(2, 2, 3);
            
            ByteArrayInputStream is = new ByteArrayInputStream(str.toByteArray());
            instance.loadGame(is);

            Cell c = instance.getGameArea().getCellAt(column, row);
            assertTrue("c.getType() != Cell.EMPTY", c.getType() != Cell.EMPTY);
        } catch (IOException ex) {
            Logger.getLogger(GameLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
