package model;
import java.util.List;
import java.util.ArrayList;

public class GrafoModel {


    // Método que devuelve la matriz de incidencia
    public int[][] calcularMatrizIncidencia(List<Nodo> nodos, List<Arista> aristas, boolean esDirigido) {
        int n = nodos.size();
        int m = aristas.size();
        int[][] matriz = new int[n][m];

        for (int j = 0; j < m; j++) {
            Arista a = aristas.get(j);
            int origen = a.origen.id - 1;   // -1 para usar índices
            int destino = a.destino.id - 1;

            if (esDirigido) {
                matriz[origen][j] = -1;
                matriz[destino][j] = 1;
            } else {
                matriz[origen][j] = 1;
                matriz[destino][j] = 1;
            }
        }
        return matriz;
    }

    // Fórmula para máximo de aristas
    public int calcularMaximoAristas(int nodos, boolean esDirigido) {
        if (esDirigido) {
            return nodos * (nodos - 1); // dirigido sin bucles
        } else {
            return (nodos * (nodos - 1)) / 2; // no dirigido sin bucles
        }
    }
}
