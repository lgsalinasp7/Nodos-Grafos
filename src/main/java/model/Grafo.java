package model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Nodo> nodos = new ArrayList<>();
    private List<Arista> aristas = new ArrayList<>();
    private boolean dirigido = true;
    private int contador = 1;

    public Nodo agregarNodo(int x, int y) {
        Nodo n = new Nodo(contador++, x, y);
        nodos.add(n);
        return n;
    }

    public void agregarArista(Nodo origen, Nodo destino) {
        aristas.add(new Arista(origen, destino));
        if (!dirigido) {
            aristas.add(new Arista(destino, origen));
        }
    }

    public void limpiarAristas() {
        aristas.clear();
    }

    public List<Nodo> getNodos() { return nodos; }
    public List<Arista> getAristas() { return aristas; }

    public boolean isDirigido() { return dirigido; }
    public void setDirigido(boolean dirigido) { this.dirigido = dirigido; }
}
