/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ristinolla.logic.Cell;
import ristinolla.logic.GameArea;

/**
 *
 * @author kestahlb
 */
public class GameController implements MouseListener {
    
    private GameView view;
    private GameArea area;
    
    public GameController(GameView view, GameArea area) {
        this.view = view;
        this.area = area;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        if(e.getButton() != MouseEvent.BUTTON1) {
            return;
        }
        */
        
        int column = (e.getX() * area.getColumns()) / view.getWidth();
        int row = (e.getY() * area.getRows()) / view.getHeight();
        
        System.out.println("CLICK! [" + column + "," + row + "] BUTTON " + e.getButton());
        
        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                area.setCellAt(column, row, Cell.CIRCLE);
                break;
            case MouseEvent.BUTTON2:
            case MouseEvent.BUTTON3:
                area.setCellAt(column, row, Cell.CROSS);
                break;
            default:
                break;                
        }
        
        //TODO: only repaint what's necessary
        view.repaint();
        
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
