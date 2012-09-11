/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ristinolla.logic.GameArea.GameAreaCallback;

/**
 *
 * @author kurre
 */
public class GameAreaTest {
    
    public GameAreaTest() {
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
     * Test of getGameArea method, of class GameArea.
     */
    @Test
    public void testGetGameArea() {
        System.out.println("getGameArea");
        GameArea expResult = null;
        GameArea result = GameArea.getGameArea();
        assertNotNull(result);
    }

    /**
     * Test of getRows method, of class GameArea.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows");
        GameArea instance = new GameArea(21, 22, 2);
        int expResult = 22;
        int result = instance.getRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumns method, of class GameArea.
     */
    @Test
    public void testGetColumns() {
        System.out.println("getColumns");
        GameArea instance = new GameArea(21,22, 2);
        int expResult = 21;
        int result = instance.getColumns();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellAt method, of class GameArea.
     */
    @Test
    public void testGetCellAt() {
        System.out.println("getCellAt");
        int column = 5;
        int row = 6;
        GameArea instance = new GameArea();
        Cell result = instance.getCellAt(column, row);
        assertNotNull(result);
        assertEquals(Cell.EMPTY, result.getType());
    }

    /**
     * Test of setCallback method, of class GameArea.
     */
    @Test
    public void testSetCallback() {
        System.out.println("setCallback");
        GameAreaCallback cb = null;
        GameArea instance = new GameArea();
        instance.setCallback(cb);
    }

    /**
     * Test of cellSelected method, of class GameArea.
     */
    @Test
    public void testCellSelected() {
        System.out.println("cellSelected");
        int column = 5;
        int row = 5;
        GameArea instance = new GameArea();
        
        Cell c = instance.getCellAt(column, row);
        assertTrue(c.getType() == Cell.EMPTY);
        
        instance.cellSelected(column, row);

        c = instance.getCellAt(column, row);
        assertTrue(c.getType() != Cell.EMPTY);
        
        instance.cellSelected(54, 45);
        instance.cellSelected(-4, -2);
    }

}
