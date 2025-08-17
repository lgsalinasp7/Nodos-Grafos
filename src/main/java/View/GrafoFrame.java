package View;

import model.Grafo;
import controller.GrafoController;
import View.GrafoPanel;

import javax.swing.*;
import java.awt.*;

public class GrafoFrame extends JFrame {

    public GrafoFrame(Grafo grafo, GrafoController controller) {
        super("Grafo Interactivo");

        View.GrafoPanel panelGrafo = new View.GrafoPanel(grafo);

        JButton btnMatriz = new JButton("Abrir Matriz");
        btnMatriz.addActionListener(e -> controller.mostrarTablaMatriz(panelGrafo));

        setLayout(new BorderLayout());
        add(panelGrafo, BorderLayout.CENTER);
        add(btnMatriz, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        panelGrafo.addMouseListener(controller.crearMouseListener(panelGrafo));
    }
}
