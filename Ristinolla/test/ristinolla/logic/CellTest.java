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
public class CellTest {
    
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
        Cell instance = new Cell(Cell.CIRCLE);
        int expResult = Cell.CIRCLE;
        int result = instance.getType();
        assertEquals(expResult, result);
        
        instance = new Cell(Cell.CROSS);
        expResult = Cell.CROSS;
        result = instance.getType();
        assertEquals(expResult, result);
    }
}
