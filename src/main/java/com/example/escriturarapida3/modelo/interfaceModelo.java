package com.example.escriturarapida3.modelo;

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

    /**
     * Nos ayuda a capturar el tiempo faltante para mostrarlo en la interfaz
     * @return
     */
    int getTiempoFaltante();

    /**
     * Inicia el cronometro para saber cuanto tiempo nos queda
     * @param onTiempoAgotado
     */
    void iniciarCronometro(Runnable onTiempoAgotado);

    /**
     * Detiene el cronometro cuando suceden ciertas circunstancias como cuando se acaba el tiempo,
     * se pierden todas las vidas entre otras
     */
    void detenerCronometro();

    /**
     * Reinicia el cronometro cuando el jugador ingresa la palabra correcta o cuando pierde una vida
     * y sale una nueva palabra por escribir
     */
    void reiniciarCronometro();

    /**
     * Nos ayuda a capturar el nivel para poder mostrarlo o actualizarlo en la interfaz
     * @return
     */
    int getNivel();

    /**
     * Incrementa el nivel cuando el usuario ingresa correcta la palabra
     */
    void incrementarNivel();

    /**
     * Calcula el tiempo segun el nivel donde se encuentre, cada 5 niveles se disminuyen 2 segundos
     * @return
     */
    int calcularTiempoSegunNivel();

    /**
     *Reduce una vida cada que se hace hace el llamado al metodo
     */
    void reducirVida();

    /**
     *
     * @return true si el jugador perdio todas las vidas y false si aun no las ha perdido
     */
    boolean perdisteVidas();

    /**
     * Captura las vidas actuales que tiene y lo usamos para mostrar el sol con el % segun las
     * vidas que tenga
     * @return
     */
    int getVidas();
}
