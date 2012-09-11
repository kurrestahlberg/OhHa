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

/**
 *
 * @author kurre
 */
public class CellTest {
    
    GameArea area = null;
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        area = new GameArea();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getType method, of class Cell.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Cell instance = new Cell(area, 0, 0, Cell.CIRCLE);
        int expResult = Cell.CIRCLE;
        int result = instance.getType();
        assertEquals(expResult, result);
        
        instance = new Cell(area, 0, 0, Cell.CROSS);
        expResult = Cell.CROSS;
        result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNeighborToDir method, of class Cell.
     */
    @Test
    public void testGetNeighborToDir() {
        System.out.println("getNeighborToDir");
        Cell instance = area.getCellAt(5, 5);
        
        for(int i = -1; i < 10; i++) {
            Cell expResult = null;
            if(i >= GameArea.NORTH && i <= GameArea.NORTHWEST) {
                expResult = area.getCellAt(5 + GameArea.COLUMNDIR[i], 
                        5 + GameArea.ROWDIR[i]);
            }
            
            Cell result = instance.getNeighborToDir(i);
            assertSame("i: " + i, expResult, result);
        }
    }
}
