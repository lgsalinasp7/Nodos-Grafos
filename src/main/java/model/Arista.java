package model;

/**
 * Una Arista es como una carretera que conecta dos ciudades (nodos)
 * - origen: la ciudad de donde sale la carretera
 * - destino: la ciudad a donde llega la carretera
 * 
 * En un grafo dirigido: la carretera es de un solo sentido (como una flecha)
 * En un grafo no dirigido: la carretera es de doble sentido (como una calle normal)
 */
public class Arista {
    public Nodo origen, destino;  // origen = nodo de salida, destino = nodo de llegada

    /**
     * Constructor: crea una nueva arista que conecta dos nodos
     * @param origen - el nodo de donde sale la conexión
     * @param destino - el nodo a donde llega la conexión
     */
    public Arista(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
    }
}
