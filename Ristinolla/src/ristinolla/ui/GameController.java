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
    
    private boolean locked = false;
    
    public GameController(GameView view, GameArea area) {
        this.view = view;
        this.area = area;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(locked) {
            return;
        }
        
        int column = (e.getX() * area.getColumns()) / view.getWidth();
        int row = (e.getY() * area.getRows()) / view.getHeight();
        
        System.out.println("CLICK! [" + column + "," + row + "] BUTTON " + e.getButton());
        
        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                area.cellSelected(column, row);
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
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
