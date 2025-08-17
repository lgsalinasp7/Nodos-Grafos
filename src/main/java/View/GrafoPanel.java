package View;

import model.*;
import javax.swing.*;
import java.awt.*;

/**
 * GrafoPanel es como el lienzo donde se dibuja el grafo
 * Es la parte visual donde ves las ciudades (nodos) y las carreteras (aristas)
 */
public class GrafoPanel extends JPanel {
    private Grafo grafo;  // El grafo que vamos a dibujar

    /**
     * Constructor: prepara el panel para dibujar
     * @param grafo - el grafo que se va a mostrar
     */
    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        setLayout(null);  // No usamos layout automático, dibujamos manualmente
        setBackground(Color.WHITE);  // Fondo blanco como un papel
    }

    /**
     * Este método se ejecuta cada vez que necesitamos dibujar el grafo
     * Es como cuando un pintor pinta un cuadro
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Array de colores para las aristas (cada arista tendrá un color diferente)
        int colorIndex = 0;
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};
        
        // PRIMERO: Dibujamos todas las carreteras (aristas)
        for (Arista a : grafo.getAristas()) {
            // Obtenemos las coordenadas de los dos nodos que conecta la arista
            int x1 = a.origen.x;  // Coordenada X del nodo de origen
            int y1 = a.origen.y;  // Coordenada Y del nodo de origen
            int x2 = a.destino.x; // Coordenada X del nodo de destino
            int y2 = a.destino.y; // Coordenada Y del nodo de destino
            
            // Elegimos un color para esta arista
            g.setColor(colors[colorIndex % colors.length]);
            
            // Creamos un Graphics2D para dibujar líneas más gruesas
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setStroke(new BasicStroke(3));  // Línea de 3 píxeles de grosor
            g2.drawLine(x1, y1, x2, y2);  // Dibujamos la línea entre los dos nodos

            // Si es un grafo dirigido, dibujamos una flecha
            if (grafo.isDirigido()) {
                dibujarFlecha(g2, x1, y1, x2, y2);
            }
            g2.dispose();  // Liberamos memoria
            
            colorIndex++;  // Pasamos al siguiente color
        }

        // SEGUNDO: Dibujamos todas las ciudades (nodos)
        for (Nodo n : grafo.getNodos()) {
            // Dibujamos un círculo gris claro para el nodo
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(n.x - 15, n.y - 15, 30, 30);  // Círculo de 30x30 píxeles
            
            // Dibujamos el borde negro del círculo
            g.setColor(Color.BLACK);
            g.drawOval(n.x - 15, n.y - 15, 30, 30);
            
            // Escribimos el número del nodo en el centro
            g.drawString(String.valueOf(n.id), n.x - 5, n.y + 5);
        }
    }

    /**
     * Dibuja una flecha en el extremo de una línea
     * Esto se usa para grafos dirigidos para mostrar la dirección
     */
    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Calculamos la dirección de la flecha
        double dx = x2 - x1;  // Diferencia en X
        double dy = y2 - y1;  // Diferencia en Y
        double angle = Math.atan2(dy, dx);  // Ángulo de la línea
        int len = (int) Math.sqrt(dx * dx + dy * dy);  // Longitud de la línea
        
        // Movemos el sistema de coordenadas al punto de origen
        g2.translate(x1, y1);
        g2.rotate(angle);  // Rotamos para que la flecha apunte en la dirección correcta
        
        // Dibujamos la línea principal
        g2.drawLine(0, 0, len, 0);
        
        // Dibujamos la punta de la flecha (un triángulo)
        g2.fillPolygon(new int[]{len, len - 15, len - 15, len},
                new int[]{0, -8, 8, 0}, 4);
        
        g2.dispose();  // Liberamos memoria
    }
}
