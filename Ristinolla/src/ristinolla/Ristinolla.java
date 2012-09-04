/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ristinolla;

/**
 *
 * @author kestahlb
 */
public class Ristinolla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });    
    }
}
