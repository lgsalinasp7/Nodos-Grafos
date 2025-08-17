package View;

import model.*;
import javax.swing.*;
import java.awt.*;

public class GrafoPanel extends JPanel {
    private Grafo grafo;
    private JTable tablaMatriz;
    private JScrollPane scroll;

    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        tablaMatriz = new JTable();
        scroll = new JScrollPane(tablaMatriz);
        add(scroll, BorderLayout.SOUTH); // matriz abajo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar aristas
        g.setColor(Color.BLACK);
        for (Arista a : grafo.getAristas()) {
            int x1 = a.origen.x;
            int y1 = a.origen.y;
            int x2 = a.destino.x;
            int y2 = a.destino.y;
            g.drawLine(x1, y1, x2, y2);

            if (grafo.isDirigido()) {
                dibujarFlecha(g, x1, y1, x2, y2);
            }
        }

        // Dibujar nodos
        for (Nodo n : grafo.getNodos()) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(n.x - 15, n.y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawOval(n.x - 15, n.y - 15, 30, 30);
            g.drawString(String.valueOf(n.id), n.x - 5, n.y + 5);
        }

        // Actualizar tabla de incidencia
        actualizarMatriz();
    }

    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) g.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        g2.translate(x1, y1);
        g2.rotate(angle);
        g2.drawLine(0, 0, len, 0);
        g2.fillPolygon(new int[]{len, len - 10, len - 10, len},
                new int[]{0, -5, 5, 0}, 4);
        g2.dispose();
    }

    private void actualizarMatriz() {
        GrafoModel gm = new GrafoModel();
        int[][] matriz = gm.calcularMatrizIncidencia(grafo.getNodos(), grafo.getAristas(), grafo.isDirigido());

        // Encabezados: E1, E2, ...
        String[] columnas = new String[grafo.getAristas().size() + 1];
        columnas[0] = "Nodo";
        for (int j = 1; j <= grafo.getAristas().size(); j++) {
            columnas[j] = "E" + j;
        }

        // Filas
        String[][] datos = new String[grafo.getNodos().size()][grafo.getAristas().size() + 1];
        for (int i = 0; i < grafo.getNodos().size(); i++) {
            datos[i][0] = String.valueOf(grafo.getNodos().get(i).id);
            for (int j = 0; j < grafo.getAristas().size(); j++) {
                datos[i][j + 1] = String.valueOf(matriz[i][j]);
            }
        }

        tablaMatriz.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}
