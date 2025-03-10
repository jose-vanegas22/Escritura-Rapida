/**
package com.example.escriturarapida3.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame {
    private JLabel etiqueta;
    private JButton boton;

    public Vista() {
        setTitle("Palabra Al Azar");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        etiqueta = new JLabel("Presiona el botón", SwingConstants.CENTER);
        boton = new JButton("Obtener Palabra");

        JPanel panel = new JPanel();
        panel.add(etiqueta);
        panel.add(boton);

        add(panel);
    }

    public void setTextoEtiqueta(String texto) {
        etiqueta.setText(texto);
    }

    public void agregarListenerBoton(ActionListener listener) {
        boton.addActionListener(listener);
    }
}
 **/