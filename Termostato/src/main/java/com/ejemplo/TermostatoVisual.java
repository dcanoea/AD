/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ejemplo;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.Serializable;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author David Cano Escario
 */
public class TermostatoVisual extends JPanel implements Serializable {

    private int temperaturaActual;
    private int temperaturaLimite;

    private JProgressBar barraTemperatura;
    private JLabel etiquetaTemperatura;
    private JSlider sliderTemperatura;
    private JTextField campoLimite;

    private boolean estadoCritico = false;
    private JDialog dialogoAlerta;

    public TermostatoVisual() {
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        barraTemperatura = new JProgressBar(0, 100);
        etiquetaTemperatura = new JLabel("Temperatura: 0°C");
        sliderTemperatura = new JSlider(0, 100, 0);
        campoLimite = new JTextField("50", 5);

        temperaturaLimite = 50;

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(etiquetaTemperatura);
        panelSuperior.add(barraTemperatura);

        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Límite crítico:"));
        panelInferior.add(campoLimite);

        add(panelSuperior, BorderLayout.CENTER);
        add(sliderTemperatura, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.NORTH);
    }

    private void configurarEventos() {
        sliderTemperatura.addChangeListener(e -> setTemperaturaActual(sliderTemperatura.getValue()));

        campoLimite.addActionListener(e -> {
            try {
                setTemperaturaLimite(Integer.parseInt(campoLimite.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor no válido para el límite.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public int getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(int temperatura) {
        this.temperaturaActual = temperatura;
        barraTemperatura.setValue(temperatura);
        etiquetaTemperatura.setText("Temperatura: " + temperatura + "°C");

        verificarEstado();
    }

    public int getTemperaturaLimite() {
        return temperaturaLimite;
    }

    public void setTemperaturaLimite(int limite) {
        this.temperaturaLimite = limite;
        verificarEstado();
    }

    private void verificarEstado() {
        if (temperaturaActual > temperaturaLimite) {
            if (!estadoCritico) {
                estadoCritico = true;
                mostrarAlerta();
            }
        } else {
            if (estadoCritico) {
                estadoCritico = false;
                cerrarAlerta();
                JOptionPane.showMessageDialog(this, "La temperatura ha vuelto a la normalidad.");
            }
        }
    }

    private void mostrarAlerta() {
        Frame frame = JOptionPane.getFrameForComponent(this);
        dialogoAlerta = new JDialog(frame, "¡Alerta crítica!", false);
        dialogoAlerta.add(new JLabel("Temperatura crítica alcanzada"), BorderLayout.CENTER);
        dialogoAlerta.setSize(250, 100);
        dialogoAlerta.setLocationRelativeTo(this);
        dialogoAlerta.setVisible(true);
    }

    private void cerrarAlerta() {
        if (dialogoAlerta != null && dialogoAlerta.isVisible()) {
            dialogoAlerta.dispose();
        }
    }
}
