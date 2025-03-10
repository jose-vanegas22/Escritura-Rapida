package com.example.escriturarapida3.controlador;

/**
 * Esta interfaz define un contrato con todos los metodos vacios que se usaran en la clase Modelo
 * para desarrollar la logica del juego escritura rapida
 * @author Jose Vanegas
 * @version 1.0
 */
public interface interfaceModelo {

    /**
     *
     * @return una palabra al azar que esta dentro de un arreglo
     */
    String obtenerPalabraAlAzar();

    /**
     * Valida si la palabra ingresada por el usuario es correcta
     *
     * @param palabraIngresada la palabra ingresada por el usuario
     * @return true si la palabra es correcta, false si es incorrecto
     */
    boolean validarPalabraAlAzar(String palabraIngresada);

    void cronometro();

}
