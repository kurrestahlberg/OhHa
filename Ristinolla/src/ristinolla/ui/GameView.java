/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import ristinolla.logic.Cell;
import ristinolla.logic.GameArea;
import ristinolla.logic.GameArea.GameAreaCallback;

/**
 *
 * @author kestahlb
 */
public class GameView extends javax.swing.JPanel implements GameAreaCallback {
    
    private GameArea gameArea;
    private GameController controller;
    private LinkedList<Line> lineList;

    /**
     * Creates new form GameField
     */
    public GameView() {
        newGame(20, 20);
        initComponents();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        float widthPerColumn = (float)getWidth() / (float)gameArea.getColumns();
        float heightPerRow = (float)getHeight() / (float)gameArea.getRows();
        
        g.clearRect(0, 0, getWidth(), getHeight());
        
        drawGrid(g, widthPerColumn, heightPerRow);
        drawSigns(g, widthPerColumn, heightPerRow);
        drawLines(g, widthPerColumn, heightPerRow);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(480, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void drawSigns(Graphics g, float widthPerColumn, float heightPerRow) {
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        
        for(int i = 0; i < gameArea.getColumns(); i++) {
            for(int j = 0; j < gameArea.getRows(); j++) {
                Cell c = gameArea.getCellAt(i, j);
                switch(c.getType()) {
                    case Cell.CIRCLE:
                        g2.drawArc((int)(i * widthPerColumn) + 2, 
                                (int)(j * heightPerRow) + 2, 
                                (int)(widthPerColumn) - 4, 
                                (int)(heightPerRow) - 4, 
                                0, 360);
                        break;
                    case Cell.CROSS:
                        g2.drawLine((int)(i * widthPerColumn) + 2, 
                                (int)(j * heightPerRow) + 2, 
                                (int)((i + 1) * widthPerColumn) - 4, 
                                (int)((j + 1) * heightPerRow) - 4);
                        g2.drawLine((int)((i + 1) * widthPerColumn) - 4, 
                                (int)(j * heightPerRow) + 2, 
                                 (int)(i * widthPerColumn) + 2,
                                (int)((j + 1) * heightPerRow) - 4);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void drawGrid(Graphics g, float widthPerColumn, float heightPerRow) {
        for(int i = 1; i < gameArea.getColumns(); i++) {
            g.drawLine((int)(i * widthPerColumn), 0, (int)(i * widthPerColumn), getHeight());
        }

        for(int i = 1; i < gameArea.getRows(); i++) {
            g.drawLine(0, (int)(i * heightPerRow), getWidth(), (int)(i * heightPerRow));
        }
    }
    
    private void drawLines(Graphics g, float widthPerColumn, float heightPerRow) {
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.red);

        Iterator<Line> it = lineList.iterator();
        while(it.hasNext()) {
            Line line = it.next();
            g2.drawLine((int)(line.getX1() * widthPerColumn + (widthPerColumn / 2.0f)), 
                        (int)(line.getY1() * heightPerRow + (heightPerRow / 2.0f)), 
                        (int)(line.getX2() * widthPerColumn + (widthPerColumn / 2.0f)), 
                        (int)(line.getY2() * heightPerRow + (heightPerRow / 2.0f)));
        }
    }

    @Override
    public void currentPlayerChanged(int currentPlayer) {
        
    }

    /**
     *
     * @param playerId
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    @Override
    public void lineDetected(int playerId, int x1, int y1, int x2, int y2) {
        lineList.add(new Line(playerId, x1, y1, x2, y2));
        /*
        JOptionPane.showMessageDialog(this, "Player " + (playerId + 1) + " wins!", 
                "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                * */
    }

    @Override
    public void gameOver(int winnerId) {
        if(winnerId == -1) {
            JOptionPane.showMessageDialog(this, "It is a tie!", 
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String winnerType = "Unknown";
            switch(winnerId) {
                case Cell.CIRCLE:
                    winnerType = "Circle";
                    break;
                case Cell.CROSS:
                    winnerType = "Cross";
                    break;
                default:
                    break;
            }
            JOptionPane.showMessageDialog(this, winnerType + " wins!", 
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        }
        controller.setLocked(true);
    }

    public void newGame(int width, int height) {
        lineList = new LinkedList<Line>();
        
        gameArea = new GameArea(width, height);
        gameArea.setCallback(this);
        
        controller = new GameController(this, gameArea);
        addMouseListener(controller);
        
        controller.setLocked(false);
        repaint();
    }
    
    public void newGameDialogTriggered() {
        NewGameDialog dlg = new NewGameDialog(null, true, 
                gameArea.getColumns(), gameArea.getRows());
        dlg.setVisible(true);
        
        if(dlg.isExitOk()) {
            newGame(dlg.getGameAreaWidth(), dlg.getGameAreaHeight());
        }        
    }
}
