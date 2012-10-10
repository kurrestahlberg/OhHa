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
        
        //These are outside of the game area, should not crash!
        result = instance.getCellAt(54, 45);
        assertNull(result);
        result = instance.getCellAt(-4, -2);
        assertNull(result);
        result = instance.getCellAt(20, 20);
        assertNull(result);
    }

    /**
     * Test of setCellAt method, of class GameArea.
     */
    @Test
    public void testSetCellAt() {
        System.out.println("setCellAt");
        int column = 10;
        int row = 10;
        int type = Cell.CROSS;
        GameArea instance = new GameArea();
        assertTrue(instance.getCellAt(column, row).getType() == Cell.EMPTY);
        instance.setCellAt(column, row, type);
        assertTrue(instance.getCellAt(column, row).getType() == type);
    }

    /**
     * Test of setCellAt method, of class GameArea.
     */
    @Test
    public void testSetCellAtCheckFreeCells() {
        System.out.println("setCellAtCheckFreeCells");
        int column = 10;
        int row = 10;
        int type = Cell.CROSS;
        GameArea instance = new GameArea();
        int freeCells = instance.getFreeCells();
        instance.setCellAt(column, row, type);
        assertTrue(instance.getFreeCells() < freeCells);
    }

    /**
     * Test of setCellAt method, of class GameArea.
     */
    @Test
    public void testSetCellAtWithEmpty() {
        System.out.println("setCellAtWithEmpty");
        int column = 10;
        int row = 10;
        int type = Cell.CROSS;
        GameArea instance = new GameArea();
        instance.setCellAt(column, row, type);
        int freeCells = instance.getFreeCells();
        instance.setCellAt(column, row, Cell.EMPTY);
        assertTrue(instance.getFreeCells() > freeCells);
    }

    /**
     * Test of setCellAt method, of class GameArea.
     */
    @Test
    public void testSetCellAtWithSameType() {
        System.out.println("setCellAtWithSameType");
        int column = 10;
        int row = 10;
        int type = Cell.CROSS;
        GameArea instance = new GameArea();
        instance.setCellAt(column, row, type);
        assertTrue(instance.getCellAt(column, row).getType() == type);
        Cell c = instance.getCellAt(column, row);
        instance.setCellAt(column, row, type);
        assertSame(instance.getCellAt(column, row), c);
    }

    /**
     * Test of getFreeCells method, of class GameArea.
     */
    @Test
    public void testGetFreeCells() {
        System.out.println("getFreeCells");
        int rows = 20;
        int columns = 20;
        
        GameArea instance = new GameArea(rows, columns);
        int expResult = rows * columns;
        int result = instance.getFreeCells();
        assertEquals(expResult, result);

        rows = 22;
        columns = 21;
        
        instance = new GameArea(rows, columns);
        expResult = rows * columns;
        result = instance.getFreeCells();
        assertEquals(expResult, result);
    }

}
