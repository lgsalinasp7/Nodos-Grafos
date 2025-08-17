package View;

import model.*;
import javax.swing.*;
import java.awt.*;

public class GrafoPanel extends JPanel {
    private Grafo grafo;

    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        setLayout(null);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int colorIndex = 0;
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};
        
        for (Arista a : grafo.getAristas()) {
            int x1 = a.origen.x;
            int y1 = a.origen.y;
            int x2 = a.destino.x;
            int y2 = a.destino.y;
            
            g.setColor(colors[colorIndex % colors.length]);
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(x1, y1, x2, y2);

            if (grafo.isDirigido()) {
                dibujarFlecha(g2, x1, y1, x2, y2);
            }
            g2.dispose();
            
            colorIndex++;
        }

        for (Nodo n : grafo.getNodos()) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(n.x - 15, n.y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawOval(n.x - 15, n.y - 15, 30, 30);
            g.drawString(String.valueOf(n.id), n.x - 5, n.y + 5);
        }
    }

    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) g.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        g2.translate(x1, y1);
        g2.rotate(angle);
        g2.drawLine(0, 0, len, 0);
        
        g2.fillPolygon(new int[]{len, len - 15, len - 15, len},
                new int[]{0, -8, 8, 0}, 4);
        g2.dispose();
    }
}
