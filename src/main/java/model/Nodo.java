package model;

/**
 * Un Nodo es como un punto en el grafo
 * Imagina que es como una ciudad en un mapa
 * - id: es el número de la ciudad (1, 2, 3, etc.)
 * - x, y: son las coordenadas donde está ubicada la ciudad en el mapa
 */
public class Nodo {
    public int id, x, y;  // id = número del nodo, x,y = posición en la pantalla

    /**
     * Constructor: crea un nuevo nodo
     * @param id - el número que identifica al nodo
     * @param x - posición horizontal en la pantalla
     * @param y - posición vertical en la pantalla
     */
    public Nodo(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
