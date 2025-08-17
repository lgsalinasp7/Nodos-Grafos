package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Un Grafo es como un mapa con ciudades (nodos) conectadas por carreteras (aristas)
 * Es la clase principal que guarda toda la información del grafo
 */
public class Grafo {
    // Lista de todas las ciudades (nodos) en nuestro mapa
    private List<Nodo> nodos = new ArrayList<>();
    
    // Lista de todas las carreteras (aristas) que conectan las ciudades
    private List<Arista> aristas = new ArrayList<>();
    
    // true = grafo dirigido (carreteras de un solo sentido con flechas)
    // false = grafo no dirigido (carreteras de doble sentido)
    private boolean dirigido = true;
    
    // Contador para dar números únicos a cada nodo (1, 2, 3, 4...)
    private int contador = 1;

    /**
     * Agrega una nueva ciudad (nodo) al mapa
     * @param x - posición horizontal donde colocar la ciudad
     * @param y - posición vertical donde colocar la ciudad
     * @return el nodo creado
     */
    public Nodo agregarNodo(int x, int y) {
        // Crea un nuevo nodo con un número único y las coordenadas dadas
        Nodo n = new Nodo(contador++, x, y);
        nodos.add(n);  // Agrega el nodo a la lista
        return n;
    }

    /**
     * Agrega una nueva carretera (arista) que conecta dos ciudades
     * @param origen - la ciudad de donde sale la carretera
     * @param destino - la ciudad a donde llega la carretera
     */
    public void agregarArista(Nodo origen, Nodo destino) {
        aristas.add(new Arista(origen, destino));
    }

    /**
     * Borra todas las carreteras del mapa (pero deja las ciudades)
     * Es como si borraran todas las carreteras pero las ciudades siguen ahí
     */
    public void limpiarAristas() {
        aristas.clear();
    }

    // Métodos para obtener información del grafo
    public List<Nodo> getNodos() { return nodos; }  // Obtiene la lista de ciudades
    public List<Arista> getAristas() { return aristas; }  // Obtiene la lista de carreteras

    // Métodos para saber si el grafo es dirigido o no
    public boolean isDirigido() { return dirigido; }  // ¿Es dirigido?
    public void setDirigido(boolean dirigido) { this.dirigido = dirigido; }  // Cambia el tipo
}
