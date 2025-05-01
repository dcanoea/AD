/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Termostato;

/**
 *
 * @author David Cano Escario
 */

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormTermostato form = new FormTermostato();
            form.setTitle("Termostato Visual");
            form.setSize(400, 300);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }
}

