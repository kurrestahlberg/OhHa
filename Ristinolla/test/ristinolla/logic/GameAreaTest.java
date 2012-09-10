/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        GameArea instance = new GameArea(21, 22);
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
        GameArea instance = new GameArea(21,22);
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
     * Test of setCellAt method, of class GameArea.
     */
    @Test
    public void testSetCellAt() {
        System.out.println("setCellAt");
        int column = 2;
        int row = 3;
        int type = Cell.CIRCLE;
        GameArea instance = new GameArea();
        instance.setCellAt(column, row, type);
        
        Cell result = instance.getCellAt(column, row);
        assertNotNull(result);
        assertEquals(type, result.getType());        
    }
}
