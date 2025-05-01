import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.io.Serializable;

public class TermostatoVisual extends JPanel implements Serializable {

    // Propiedades del JavaBean
    private int temperaturaActual = 0;
    private int temperaturaLimite = 50;
    private boolean estadoCritico = false;

    // Componentes gráficos
    private JProgressBar jProgressBar;
    private JLabel jLabel;
    private JSlider jSlider;
    private JTextField jTextField;

    // Constructor sin parámetros
    public TermostatoVisual() {
        initComponents();
    }

    // Inicializa los componentes
    private void initComponents() {
        jProgressBar = new JProgressBar();
        jLabel = new JLabel("Temperatura: 0°C");
        jSlider = new JSlider(0, 100, 0);
        jTextField = new JTextField("50");

        setLayout(new GridLayout(4, 1));

        // JProgressBar setup
        jProgressBar.setMaximum(100);
        jProgressBar.setValue(temperaturaActual);
        jProgressBar.setStringPainted(true);

        // JSlider setup
        jSlider.setValue(temperaturaActual);
        jSlider.setMajorTickSpacing(10);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);

        jSlider.addChangeListener(e -> {
            setTemperaturaActual(jSlider.getValue());
            actualizarTemperatura();
        });

        // JTextField setup
        jTextField.setColumns(5);
        jTextField.addActionListener(e -> {
            setTemperaturaLimite(Integer.parseInt(jTextField.getText()));
        });

        // Añadir componentes al panel
        add(jLabel);
        add(jProgressBar);
        add(jSlider);
        add(jTextField);
    }

    // Método para actualizar la temperatura y verificar si está en estado crítico
    private void actualizarTemperatura() {
        jLabel.setText("Temperatura: " + temperaturaActual + "°C");
        jProgressBar.setValue(temperaturaActual);

        if (temperaturaActual > temperaturaLimite && !estadoCritico) {
            mostrarAlerta();
            estadoCritico = true;
        } else if (temperaturaActual <= temperaturaLimite && estadoCritico) {
            mostrarNormal();
            estadoCritico = false;
        }
    }

    // Método para mostrar un diálogo de alerta no modal
    private void mostrarAlerta() {
        JOptionPane.getFrameForComponent(this); // Se obtiene el frame
        JDialog alerta = new JDialog();
        alerta.setTitle("Alerta: Temperatura Crítica");
        alerta.setSize(200, 100);
        alerta.setLocationRelativeTo(this);
        alerta.setModal(false);  // No modal
        alerta.add(new JLabel("¡Temperatura Crítica!"));
        alerta.setVisible(true);
    }

    // Método para mostrar un mensaje modal cuando la temperatura vuelve a la normalidad
    private void mostrarNormal() {
        JOptionPane.showMessageDialog(this, "La temperatura ha vuelto a la normalidad.");
    }

    // Métodos getter y setter para las propiedades

    public int getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(int temperaturaActual) {
        int oldTemperatura = this.temperaturaActual;
        this.temperaturaActual = temperaturaActual;
        firePropertyChange("temperaturaActual", oldTemperatura, temperaturaActual);
        actualizarTemperatura();
    }

    public int getTemperaturaLimite() {
        return temperaturaLimite;
    }

    public void setTemperaturaLimite(int temperaturaLimite) {
        int oldLimite = this.temperaturaLimite;
        this.temperaturaLimite = temperaturaLimite;
        firePropertyChange("temperaturaLimite", oldLimite, temperaturaLimite);
        actualizarTemperatura();
    }
}
