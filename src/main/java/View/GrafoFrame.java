package View;

import model.Grafo;
import controller.GrafoController;
import View.GrafoPanel;

import javax.swing.*;
import java.awt.*;

public class GrafoFrame extends JFrame {

    public GrafoFrame(Grafo grafo, GrafoController controller) {
        super("Grafo Interactivo");

        // Crear panel del grafo
        View.GrafoPanel panelGrafo = new View.GrafoPanel(grafo);

        // Botón para abrir la matriz de incidencia
        JButton btnMatriz = new JButton("Abrir Matriz");
        btnMatriz.addActionListener(e -> controller.mostrarTablaMatriz(panelGrafo));

        // Layout
        setLayout(new BorderLayout());
        add(panelGrafo, BorderLayout.CENTER);
        add(btnMatriz, BorderLayout.SOUTH);

        // Configuración de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Listener para agregar nodos con clic en el panel
        panelGrafo.addMouseListener(controller.crearMouseListener(panelGrafo));
    }
}
