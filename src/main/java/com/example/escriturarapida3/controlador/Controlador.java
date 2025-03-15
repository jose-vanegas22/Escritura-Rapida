package com.example.escriturarapida3.controlador;

import com.example.escriturarapida3.modelo.Modelo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * En esta clase manejamos los eventos y la interaccion entre la interfaz grafica y el modelo, actualiza
 * la interfaz segun lo que vaya ocurriendo en el juego, como el tema de tiempo, las vidas, el nivel
 * en el que se encuentre y entre otras cosas
 *
 * @author vaneg
 * @version 1.0
 */
public class Controlador {

    @FXML
    private Label lbPalabraAleatory, lbMensaje, lbTiempo, lbMensajePerdida; //Variables de instancia

    @FXML
    private TextField tfPalabraIngresada; //Variables de instancia


    @FXML
    private ImageView imgVidas; //Variables de instancia

    @FXML
    private Button btnValidar; //Variables de instancia


    /**
     * Creamos un objeto de la clase Modelo para poder usar todos sus metodos en esta clase
     */
    Modelo modelo = new Modelo();

    /**
     * Metodo que se ejecuta automaticamente de primero al iniciar el programa
     * genera una palabra aleatoria y la muestra en el lbPalabraAleatory, ademas contiene
     * la tarea que se ejecuta cuando el cronometro llegue a 0 y actualiza constantemente el
     * label del tiempo para que se muestre en cuantos segundos va, permite que se valide
     * la palabra cuando se presiona enter y tiene unos MouseEvent de cuando el cursor
     * entra y sale del boton lo cambia de color
     */
    @FXML
    public void initialize() { //Esto se ejecuta en el hilo principal, lo que contiene @FXML
        modelo.obtenerPalabraAlAzar();
        lbPalabraAleatory.setText(modelo.getPalabraObjetivo()); //En este label se muestra la palabra al azar
        //lbVidas.setText("Vidas: " + modelo.getVidas());

        // Asegurar que la imagen de 4 vidas se cargue correctamente
        Platform.runLater(() -> actualizarImagenVidas());

        //Iniciar el cronometro
        //Esto se esta ejecutando en un hilo secundario para poder actualizar la interfaz sin bloquear
        //el resto de codigo
        modelo.iniciarCronometro(() -> { //Se llama al metodo y espera una expresion lambda, se ejecuta esta tarea cuando el cronometro llegue a 0
            Platform.runLater(() -> { //Se usa para actualizar cuando se esta fuera del hilo principal

                String palabraIngresada = tfPalabraIngresada.getText();
                boolean correcto2 = modelo.validarPalabraAlAzar(palabraIngresada);
                if (correcto2) {
                    lbMensaje.setText("");//Se limpia el mensaje cuando sea correcto
                    modelo.obtenerPalabraAlAzar(); // Generar nueva palabra
                    lbPalabraAleatory.setText(modelo.getPalabraObjetivo());
                    tfPalabraIngresada.clear(); // Limpiar campo de texto
                    modelo.incrementarNivel(); //Incrementa nivel
                    //lbNivel.setText("Nivel: " + modelo.getNivel());
                    modelo.reiniciarCronometro(); //Reinicia el cronometro
                } else {
                    tfPalabraIngresada.setDisable(true); // Bloquea el campo de texto
                    btnValidar.setDisable(true); //Bloquea el boton
                    lbMensaje.setText("Se acabo el tiempo, has perdido");
                    lbMensajePerdida.setText("El nivel conseguido es: " + modelo.getNivel());
                }
            });
        });

        //Actualizar el tiempo en el label lbTiempo
        new Thread(() -> { //Aqui  se esta creando un hilo secundario que se ejecuta al mismo tiempo con el principal
            while (true) { //Hace que el hilo siempre se ejecute mientras la app se esta ejecutando, su proposito es actualizar siempre lbTiempo
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } //Ese try y catch lo uso porque lo que esta adentro me ayuda a detener 0.1 s este cliclo infinito, de lo ccontrario se pondria lenta la app, consume toda la CPU
                Platform.runLater(() -> { //Permite actualizar lo del hilo secundario en el principal
                    lbTiempo.setText(modelo.getTiempoFaltante() + " Segundos");
                });
            }
        }).start(); //Permite ejecutar el hilo

        // Hacer que presionar "Enter" en el TextField valide la palabra, es un evento de teclado
        tfPalabraIngresada.setOnAction(event -> onBotonValidar(null));


        // Aplicar CustomMouseListener para cambiar color del botón
        CustomMouseListener mouseListener = new CustomMouseListener(btnValidar); //objeto de la clase CustomMouseListener
        btnValidar.setOnMouseEntered(event -> mouseListener.mouseEntered()); //Evento cuando el mouse entra al boton
        btnValidar.setOnMouseExited(event -> mouseListener.mouseExited()); //Evento cuando el mouse sale del boton

    }


    /**
     * Metodo el cual actualiza la iamgen segun la cantidad de vidas o intentoss que tenga el usuario
     */
    private void actualizarImagenVidas() {
        int vidas = modelo.getVidas();
        if (vidas == 4) {
            imgVidas.setImage(new Image(getClass().getResourceAsStream("/com/example/escriturarapida3/Imagenes/Sol Redondo 100%.png")));
        } else if (vidas == 3) {
            imgVidas.setImage(new Image(getClass().getResourceAsStream("/com/example/escriturarapida3/Imagenes/Sol Redondo 75%.png")));
        } else if (vidas == 2) {
            imgVidas.setImage(new Image(getClass().getResourceAsStream("/com/example/escriturarapida3/Imagenes/Sol-Redondo-50_.png")));
        } else if (vidas == 1) {
            imgVidas.setImage(new Image(getClass().getResourceAsStream("/com/example/escriturarapida3/Imagenes/Sol-Redondo-25_.png")));
        } else {
            imgVidas.setImage(new Image(getClass().getResourceAsStream("/com/example/escriturarapida3/Imagenes/Sol-Redondo-0_.png")));
        }
    }


    /**
     * Maneja el evento del boton de validacion
     * Si correcto es true o sea las palabras son iguales, se ejecuta el if que permite continuar con el juego
     * sin ningun problema
     * si correcto es false se ejecutan unas lineas de codigo las cuales lanza unos mensajes y entra a otro
     * if en donde si se perdio todas las vidas se tiene el juego y lanza unos mensajes pero de lo
     * contrario solo le quita una vida y le permite continuar con el juego
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
            modelo.incrementarNivel(); //Incrementa nivel
            modelo.reiniciarCronometro(); //Reinicia el cronometro

        } else {
            lbMensaje.setText("Incorrecto. Acabas de perder una vida.");
            actualizarImagenVidas(); // Actualizar la imagen de las vidas


            if (modelo.perdisteVidas()) {//Cuando se pierden todas las vidas se acaba el juego
                lbMensaje.setText("Has perdido todas las vidas, GRACIAS POR JUGAR!!!!!");
                lbMensajePerdida.setText("El nivel conseguido es: " + modelo.getNivel());
                tfPalabraIngresada.setDisable(true);
                btnValidar.setDisable(true);
                modelo.detenerCronometro();

            }else{ //Este else lo que hace es que cuando se pierde una vida, muestre una nueva palabra
            modelo.obtenerPalabraAlAzar();
            lbPalabraAleatory.setText(modelo.getPalabraObjetivo());
            tfPalabraIngresada.clear();
            modelo.reiniciarCronometro();
            }
        }
    }


}