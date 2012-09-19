/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kstahlberg
 */
public class LineTest {
    
    public LineTest() {
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
     * Test of getX1 method, of class Line.
     */
    @Test
    public void testGetX1() {
        System.out.println("getX1");
        Line instance = new Line(0, 1, 2, 3, 4);
        int expResult = 1;
        int result = instance.getX1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY1 method, of class Line.
     */
    @Test
    public void testGetY1() {
        System.out.println("getY1");
        Line instance = new Line(0, 1, 2, 3, 4);
        int expResult = 2;
        int result = instance.getY1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX2 method, of class Line.
     */
    @Test
    public void testGetX2() {
        System.out.println("getX2");
        Line instance = new Line(0, 1, 2, 3, 4);
        int expResult = 3;
        int result = instance.getX2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY2 method, of class Line.
     */
    @Test
    public void testGetY2() {
        System.out.println("getY2");
        Line instance = new Line(0, 1, 2, 3, 4);
        int expResult = 4;
        int result = instance.getY2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayerId method, of class Line.
     */
    @Test
    public void testGetPlayerId() {
        System.out.println("getPlayerId");
        Line instance = new Line(6, 1, 2, 3, 4);
        int expResult = 6;
        int result = instance.getPlayerId();
        assertEquals(expResult, result);
    }
}
