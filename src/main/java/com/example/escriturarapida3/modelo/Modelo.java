package com.example.escriturarapida3.modelo;

import com.example.escriturarapida3.controlador.interfaceModelo;

import java.util.Random;


/**
 * En esta clase tenemos toda la logica de nuestro juego e implementamos la interface interfaceModelo
 * para usar todos sus metodos,
 */
public class Modelo implements interfaceModelo {

    /**
     * Arreglo de palabras el cual contiene 50 palabras para que aparezcan de modo aleatorio en el juego
     * @serial palabras al azar
     */
    private String palabras[] = {"rapido", "java", "teclado", "computadora", "pantalla", "juego", "escribir", "programa", "monitor", "raton",
            "codigo", "desarrollo", "hardware", "software", "algoritmo", "estructura", "base", "datos", "inteligencia", "sistema",
            "compilador", "ejecucion", "memoria", "procesador", "tecnologia", "informatica", "lenguaje", "modelo", "servidor", "nube",
            "variable", "metodo", "clase", "objeto", "instancia", "constructor", "interfaz", "herencia", "polimorfismo", "encapsulacion",
            "bucle", "condicion", "operador", "booleano", "excepcion", "depuracion", "libreria", "framework", "documentacion", "interprete"};

    /**
     * Variable en la cual se guarda la palabra aleatoria que se debe escribir
     * @serial palabra aleatoria
     */
    private String palabraObjetivo;


    /**
     * Esta es una variable de instancia en java, Random es una clase de java, se usa para generar numeros aleatorios
     * y en este caso nos sirve para que ese numero se asocie con una palabra del arreglo, segun el numero que salga
     * esa es la palabra que muestra
     * @serial
     */
    private Random random;


    /**
     * Constructor de la clase Modelo
     * Inicializa el objeto Random para generar los numeros aleatorios (sin crear el constructor no podriamos usar
     * las funciones de la clase Random)
     */
    public Modelo() {
        random = new Random();
    }

    /**
     * Selecciona una palabra al azar de la lista palabras y la palabra seleccionada la guarda en la
     * variable palabraObjetivo
     * @return una palabra aleatoria de la lista palabras
     */
    public String obtenerPalabraAlAzar() {
        int aleatorio = random.nextInt(palabras.length); //se crea una variable int aleatorio para almacenar el indice
        palabraObjetivo = palabras[aleatorio]; //Se guarda en palabraObjetivo la palabra escogida por el indice aleatorio
        return palabraObjetivo;
    }

    /**
     * Valida que la palabra ingresada por el usuario sea igual que la palabra escogida al azar guardada en palabraObjetivo
     * @param palabraIngresada la palabra ingresada por el usuario (necesita datos externos que ingresa el usuario)
     * @return true si la palabra es igual, false si la palabra es diferente
     */
    public boolean validarPalabraAlAzar(String palabraIngresada) {
        return palabraIngresada.equalsIgnoreCase(palabraObjetivo);
    }


    /**
     * Este es un getter, un metodo el cual nos permite acceder a la variable fuera de esta clase
     * @return el valor de la variable palabraObjetivo
     */
    public String getPalabraObjetivo(){
        return palabraObjetivo;
    }

    public void cronometro() {

    }
}