package org.example;

import controller.GrafoController;

import model.Grafo;
import View.GrafoFrame;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        GrafoController controller = new GrafoController(grafo);
        new GrafoFrame(grafo, controller);
    }
}
