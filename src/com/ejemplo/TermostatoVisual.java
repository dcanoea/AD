package com.ejemplo;

public class TermostatoVisual extends javax.swing.JPanel {

    private int temperaturaActual = 0;
    private int temperaturaLimite = 50;
    private boolean estadoCritico = false;
    private javax.swing.JDialog dialogoAlerta;

    public TermostatoVisual() {
        initComponents();
        barraTemperatura.setValue(temperaturaActual);
        sliderTemperatura.addChangeListener(e -> setTemperaturaActual(sliderTemperatura.getValue()));
        campoLimite.addActionListener(e -> {
            try {
                setTemperaturaLimite(Integer.parseInt(campoLimite.getText()));
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Límite no válido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
            barraTemperatura.setForeground(java.awt.Color.RED);
            if (!estadoCritico) {
                estadoCritico = true;
                mostrarAlerta();
            }
        } else {
            barraTemperatura.setForeground(java.awt.Color.GREEN);
            if (estadoCritico) {
                estadoCritico = false;
                cerrarAlerta();
                javax.swing.JOptionPane.showMessageDialog(this, "Temperatura normalizada.");
            }
        }
    }

    private void mostrarAlerta() {
        java.awt.Frame frame = javax.swing.JOptionPane.getFrameForComponent(this);
        dialogoAlerta = new javax.swing.JDialog(frame, "¡Alerta crítica!", false);
        dialogoAlerta.add(new javax.swing.JLabel("Temperatura crítica alcanzada"), java.awt.BorderLayout.CENTER);
        dialogoAlerta.setSize(250, 100);
        dialogoAlerta.setLocationRelativeTo(this);
        dialogoAlerta.setVisible(true);
    }

    private void cerrarAlerta() {
        if (dialogoAlerta != null && dialogoAlerta.isVisible()) {
            dialogoAlerta.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Este método se llenará automáticamente por NetBeans al guardar el diseño visual
    }

    // Variables declaration - do not modify
    private javax.swing.JProgressBar barraTemperatura;
    private javax.swing.JTextField campoLimite;
    private javax.swing.JLabel etiquetaTemperatura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JSlider sliderTemperatura;
    // End of variables declaration
}
