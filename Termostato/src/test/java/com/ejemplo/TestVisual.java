package com.ejemplo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class TestVisual {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba TermostatoVisual");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.add(new TermostatoVisual());
            frame.setVisible(true);
        });
    }
}
