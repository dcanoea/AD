package Termostato;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author David Cano Escario
 */


import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.io.Serializable;

public class TermostatoVisual extends JPanel implements Serializable {
    private int temperaturaActual = 20;
    private int temperaturaLimite = 50;
    private boolean estadoCritico = false;
    private JDialog alertaDialog;

    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JLabel labelTemp = new JLabel(temperaturaActual + "°C");
    private final JSlider slider = new JSlider(0, 100, temperaturaActual);
    private final JTextField textFieldLimite = new JTextField(String.valueOf(temperaturaLimite));

    public TermostatoVisual() {
        setLayout(new GridLayout(4, 1));
        add(progressBar);
        add(labelTemp);
        add(slider);
        add(textFieldLimite);

        progressBar.setValue(temperaturaActual);

        slider.addChangeListener(e -> setTemperaturaActual(slider.getValue()));
        textFieldLimite.addActionListener(e -> {
            try {
                setTemperaturaLimite(Integer.parseInt(textFieldLimite.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Límite inválido");
            }
        });
    }

    public int getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(int temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
        labelTemp.setText(temperaturaActual + "°C");
        progressBar.setValue(temperaturaActual);
        checkEstado();
    }

    public int getTemperaturaLimite() {
        return temperaturaLimite;
    }

    public void setTemperaturaLimite(int temperaturaLimite) {
        this.temperaturaLimite = temperaturaLimite;
        textFieldLimite.setText(String.valueOf(temperaturaLimite));
        checkEstado();
    }

    private void checkEstado() {
        if (temperaturaActual > temperaturaLimite) {
            if (!estadoCritico) {
                estadoCritico = true;
                alertaDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "¡Alerta!", false);
                alertaDialog.add(new JLabel("          Temperatura crítica: " + temperaturaActual + "°C"));
                alertaDialog.setSize(250, 100);
                alertaDialog.setLocationRelativeTo(this);
                alertaDialog.setVisible(true);
            }
        } else {
            if (estadoCritico) {
                estadoCritico = false;
                if (alertaDialog != null) {
                    alertaDialog.setVisible(false);
                    alertaDialog.dispose();
                }
                JOptionPane.showMessageDialog(this, "Temperatura normalizada: " + temperaturaActual + "°C");
            }
        }
    }
}
