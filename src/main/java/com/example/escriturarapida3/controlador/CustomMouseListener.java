package com.example.escriturarapida3.controlador;

import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *Esta clase se extiende de MouseAdapter y solo usa dos metodos
 * sin necesidad de hacer el llamado de todos y tiene un metodo
 * el cual pinta el boton de un color segun si el cursor esta
 * dentro o fuera de el
 *
 * @author vaneg
 * @version 1.0
 */

public class CustomMouseListener extends MouseAdapter {

    private Button button; //Variable de tipo boton

    /**
     * Aqui se crea el constructor de la clase y donde tenemos los argumentos
     * button
     * @param button
     */
    public CustomMouseListener(Button button) {
        this.button = button; //El this sirve para decir que el boton sobre el que
                              //se hace el evento es el que pertenece a la clase
    }

    /**
     * Usamos el metodo mouseEntered para cambiar el color del boton
     * cuando el mouse entra
     */
    @Override
    public void mouseEntered() {
        cambioColorBoton(MouseEvent.MOUSE_ENTERED);
    }

    /**
     * Usamos el metodo mouseExited para cambiar el color del boton
     * cuando el mouse sale
     */
    @Override
    public void mouseExited() {
        cambioColorBoton(MouseEvent.MOUSE_EXITED);
    }


    /**
     * Este evento nos sirve para cambiar el boton del boton
     * dependiendo en donde el mouse se encuentre si esta
     * dentro se pinta de color verde y si esta afuera
     * se pinta de color rojo
     * @param eventType
     */
    public void cambioColorBoton(EventType<MouseEvent> eventType) {
        if (eventType == MouseEvent.MOUSE_ENTERED) {
            button.setStyle("-fx-background-color: green;");
        } else if (eventType == MouseEvent.MOUSE_EXITED) {
            button.setStyle("-fx-background-color: red;");
        }
    }

}
