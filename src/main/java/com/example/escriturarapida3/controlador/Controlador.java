package com.example.escriturarapida3.controlador;

import com.example.escriturarapida3.modelo.Modelo;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controlador {

    @FXML
    private Label lbPalabraAleatory, lbMensaje, lbTiempo;

    @FXML
    private TextField tfPalabraIngresada;

    /**
     * Creamos un objeto de la clase Modelo para poder usar todos sus metodos en esta clase
     */
    Modelo modelo = new Modelo();

    /**
     *Metodo que se ejecuta automaticamente de primero al iniciar el programa
     * genera una palabra aleatoria y la muestra en el lbPalabraAleatory
     */
    @FXML
    public void initialize() {
        modelo.obtenerPalabraAlAzar();
        lbPalabraAleatory.setText(modelo.getPalabraObjetivo());
    }

    /**
     * Maneja el evento del boton de validacion
     * Si correcto es true o sea las palabras son iguales, se ejecuta el primer bloque de codigo
     * si correcto es false se ejecuta el segundo bloque de codigo
     *
     * @param event es un evento de accion cuando el usuario presiona el boton
     */
    @FXML
    void onBotonValidar(ActionEvent event) {
        String palabraIngresada = tfPalabraIngresada.getText(); // Se guarda lo ingresadp por el usuario en la variable palabraIngresada
        boolean correcto = modelo.validarPalabraAlAzar(palabraIngresada); // Se crea variable boolean correcto que almacena el resultado (true/false) del metodo validarPalabraAlAzar

        if (correcto) {
            lbMensaje.setText("");//Se limpia el mensaje cuando sea correcto
            modelo.obtenerPalabraAlAzar(); // Generar nueva palabra
            lbPalabraAleatory.setText(modelo.getPalabraObjetivo());
            tfPalabraIngresada.clear(); // Limpiar campo de texto

        } else {
            lbMensaje.setText("Incorrecto. Inténtalo de nuevo.");
        }
    }



}