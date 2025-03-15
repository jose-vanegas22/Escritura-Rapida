package com.example.escriturarapida3.modelo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * En esta clase tenemos toda la logica de nuestro juego e implementamos la interface interfaceModelo
 * para usar todos sus metodos,
 *
 * @author vaneg
 * @version 1.0
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

    private String palabraObjetivo; //Guarda la palabra aleatoria que se debe escribir
    private int vidas = 4; //Numero de posibilidade para escribir correctamente la palabra
    private int tiempoFaltante; //Contiene el tiempo restante en segundos
    private int nivel = 0;
    private Timer timer; //Timer sirve para ejecutar tareas repetitivas (el cronometro)
    private Runnable onTiempoAgotado; //Runnable nos sirve para almacenar tareas que se ejecutan cuando se acaba el tiempo (en este caso)
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
     * las funciones de la clase Random), se inicializa tiempoFaltante en el constrcutor  para que tome el valor
     * calcularTiempoSegunNivel, el constructor ayuda a que siempre tenga un valor
     */
    public Modelo() {
        random = new Random();
        tiempoFaltante = calcularTiempoSegunNivel();
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
        if (palabraIngresada.equalsIgnoreCase(palabraObjetivo)) {
            return true;
        } else {
            reducirVida();
            return false;
        }
    }

    /**
     * Este es un getter, un metodo el cual nos permite acceder a la variable fuera de esta clase
     * @return el valor de la variable palabraObjetivo
     */
    public String getPalabraObjetivo(){
        return palabraObjetivo;
    }

    /**
     * Este metodo permite tener el tiempo faltante para poder actualizarlo en la interfaz
     * en un label
     * @return el tiempo faltante de ese momento
     */
    public int getTiempoFaltante() {
        return tiempoFaltante;
    }

    /**
     * Este metodo permite tener el nivel actual para poder actualizarlo en la interfaz
     * en un label
     * @return ek nivel de ese momento
     */
    public int getNivel() {
        return nivel;
    }


    /**
     * Este metodo inicia el cronometro que disminuye el tiempo cada segundo y cuando se llega a
     * 0 se ejecuta la tarea que se especifico
     * @param onTiempoAgotado una tarea  Runnable la cual se ejecuta cuando el tiempo sea igual a cero
     */
    public void iniciarCronometro(Runnable onTiempoAgotado) {
        //El this.onTiempoAgotado hace referencia a la variable que esta por fuera y esta guardando
        // la tarea del parametro onTiempoAgotado, en caso de que se llamara diferente no seria necesario
        // el this. debido a que no hay que especificar
        this.onTiempoAgotado = onTiempoAgotado;
        timer = new Timer(); //Se instancia para usar sus metodos
        timer.scheduleAtFixedRate(new TimerTask() { //Sirve para programar la tarea
            @Override
            public void run() { //Obligatoriamente se debe usar este metodo
                if (tiempoFaltante > 0){
                    tiempoFaltante--;
                } else{
                    detenerCronometro(); //Se detiene el Timer
                    onTiempoAgotado.run(); //Aqui se ejecuta la tarea guardada en el this. y esa tarea esta en el Controlador donde inicia el cronometro
                }
            }
        }, 1000, 1000); // Hace que se ejecute TimerTask cada segundo
    }


    /**
     * Este metodo detiene el cronometro y no devuelve nada
     * Si el jugador acierta la palabra se ejecuta este metodo para detener el cronometro anterior
     */
    public void detenerCronometro() {
        if (timer != null) {
            timer.cancel(); //Esto se hace porque la idea es detener el cronometro y como timer es el encargado
                            //de ejecutar las tareas de debe de cancelar para que no ejecute mas tareas
        }
    }

    /**
     * Este metodo reinica el cronometro, empiezan los segundos segun el nivel en el que se encuentre
     * y devuelve nada
     */
    @Override
    public void reiniciarCronometro() {
        detenerCronometro(); //Detiene el cronometro que se esta ejecutando para ejecutar otro
        tiempoFaltante = calcularTiempoSegunNivel(); //Reinicia el tiempo faltante segun el nivel en que se encuentre
        iniciarCronometro(onTiempoAgotado); //Inicia el cronometro nuevamente con el parametro onTiempoAgotado debido que es la tarea que se ejecuta cuando se acaba el tiempo
    }


    /**
     * Este metodo calcula el tiempo inicial segun el nivel en el que este, empieza en 20 segundos
     * cada 5 niveles el tiempo reduce 2 segundos y el tiempo minimo es 2 segundos
     * @return
     */
    public int calcularTiempoSegunNivel(){
        int tiempoBase = 20; //Este es el tiempo con el que inicia
        int decremento = (nivel - 1) / 5; //Se calcula en cuanto reducir el tiempo, como se usa division entera el resultado que da se escoge hacia abajo
        int tiempoCalculado = tiempoBase - (decremento * 2); //Reduce el tiempo 2 segundos

        return Math.max(tiempoCalculado, 2); //El tiempo minimo es 2 segundos, porque devuelve el mayor valor
                                             //en caso de que el nivel sea muy alto la variable tiempoCalculado
                                             //va a dar negativo pero Math.max se encarga de dar siempre el mayor
                                             //que en este caso seria 2 segundos
    }


    /**
     *Sirve para ir aumentando el nivel y usarlo en el Controlador para poder mostrarlo
     * en un label
     */
    public void incrementarNivel(){
        nivel++;
    }

    /**
     * Metodo que ayuda a recudir las vidas, si el jugador ingresa una palabra incorrecta
     * y si vidas es mayor a 0 cada que entre por aqui se le va restando una vida
     */
    public void reducirVida(){
        if (vidas > 0){
            vidas--;
        }
    }


    /**
     * Este metodo sirve para saber si las vidas son igual a 0
     * @return retorna que hay actualmente 0 vidas
     */
    public boolean perdisteVidas(){
        return vidas == 0;
    }

    /**
     * Este metodo permite mostrar las vidas que hay actualemnte para imprimirlo en una
     * cadena de texto y mostrarlo en un label
     * @return la cantidad de vidad o de oportunidades que el jugador tiene
     */
    public int getVidas(){
        return vidas;
    }

}