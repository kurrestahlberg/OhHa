/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla;

import java.awt.Graphics;

/**
 *
 * @author kestahlb
 */
public class GameView extends javax.swing.JPanel {
    
    private GameArea gameArea;
    private GameController controller;

    /**
     * Creates new form GameField
     */
    public GameView() {
        gameArea = new GameArea();
        
        initComponents();
        
        controller = new GameController(this, gameArea);
        addMouseListener(controller);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        float widthPerColumn = (float)getWidth() / (float)gameArea.getColumns();
        float heightPerRow = (float)getHeight() / (float)gameArea.getRows();
        
        g.clearRect(0, 0, getWidth(), getHeight());
        
        drawLines(g, widthPerColumn, heightPerRow);
        drawSigns(g, widthPerColumn, heightPerRow);
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
        for(int i = 0; i < gameArea.getColumns(); i++) {
            for(int j = 0; j < gameArea.getRows(); j++) {
                Cell c = gameArea.getCellAt(i, j);
                switch(c.getType()) {
                    case Cell.CIRCLE:
                        g.drawArc((int)(i * widthPerColumn) + 2, 
                                (int)(j * heightPerRow) + 2, 
                                (int)(widthPerColumn) - 4, 
                                (int)(heightPerRow) - 4, 
                                0, 360);
                        break;
                    case Cell.CROSS:
                        g.drawLine((int)(i * widthPerColumn) + 2, 
                                (int)(j * heightPerRow) + 2, 
                                (int)((i + 1) * widthPerColumn) - 4, 
                                (int)((j + 1) * heightPerRow) - 4);
                        g.drawLine((int)((i + 1) * widthPerColumn) - 4, 
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

    private void drawLines(Graphics g, float widthPerColumn, float heightPerRow) {
        for(int i = 1; i < gameArea.getColumns(); i++) {
            g.drawLine((int)(i * widthPerColumn), 0, (int)(i * widthPerColumn), getHeight());
        }

        for(int i = 1; i < gameArea.getRows(); i++) {
            g.drawLine(0, (int)(i * heightPerRow), getWidth(), (int)(i * heightPerRow));
        }
    }
}
