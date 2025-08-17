package controller;

import model.*;
import View.GrafoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GrafoController {
    private Grafo grafo;

    public GrafoController(Grafo grafo) {
        this.grafo = grafo;
    }

    public MouseAdapter crearMouseListener(GrafoPanel panel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                grafo.agregarNodo(e.getX(), e.getY());
                panel.repaint();
            }
        };
    }

    public void mostrarTablaMatriz(GrafoPanel panelGrafo) {
        if (grafo.getNodos().isEmpty()) {
            JOptionPane.showMessageDialog(panelGrafo, "Agrega nodos antes de crear la matriz.");
            return;
        }

        String[] opciones = {"Dirigido", "No dirigido"};
        int seleccion = JOptionPane.showOptionDialog(panelGrafo, "Selecciona el tipo de grafo",
                "Tipo de Grafo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        grafo.setDirigido(seleccion == 0);

        int n = grafo.getNodos().size();
        int m = Math.max(1, grafo.getAristas().size());

        String[] columnNames = new String[m];
        for (int j = 0; j < m; j++) {
            columnNames[j] = "E" + (j + 1);
        }

        Object[][] data = new Object[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = 0;
            }
        }

        JTable tabla = new JTable(data, columnNames);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnRelacionar = new JButton("Relacionar");
        btnRelacionar.addActionListener(e -> {
            if (tabla.isEditing()) {
                tabla.getCellEditor().stopCellEditing();
            }
            grafo.limpiarAristas();

            for (int j = 0; j < tabla.getColumnCount(); j++) {
                Nodo origen = null, destino = null;
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    int valor = Integer.parseInt(tabla.getValueAt(i, j).toString());
                    if (grafo.isDirigido()) {
                        if (valor == -1) origen = grafo.getNodos().get(i);
                        if (valor == 1) destino = grafo.getNodos().get(i);
                    } else {
                        if (valor == 1) {
                            if (origen == null) origen = grafo.getNodos().get(i);
                            else destino = grafo.getNodos().get(i);
                        }
                    }
                }
                if (origen != null && destino != null) {
                    grafo.agregarArista(origen, destino);
                }
            }
            panelGrafo.repaint();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnRelacionar, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Matriz de Incidencia");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(panelGrafo);
        frame.setVisible(true);
    }
}
