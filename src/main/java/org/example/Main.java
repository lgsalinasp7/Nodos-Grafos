package org.example;

import controller.GrafoController;
import model.Grafo;
import View.GrafoFrame;

/**
 * Esta es la clase principal que inicia todo el programa
 * Es como el "botón de encendido" de nuestra aplicación
 */
public class Main {
    public static void main(String[] args) {
        // Creamos un grafo vacío (como un papel en blanco donde dibujaremos)
        Grafo grafo = new Grafo();
        
        // Creamos el controlador (es como el cerebro que maneja todo)
        GrafoController controller = new GrafoController(grafo);
        
        // Creamos la ventana principal (es como abrir una ventana en la computadora)
        new GrafoFrame(grafo, controller);
    }
}
