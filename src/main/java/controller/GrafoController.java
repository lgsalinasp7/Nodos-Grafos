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
        
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return; 
        }
        
        grafo.setDirigido(seleccion == 0);

        int n = grafo.getNodos().size();
        int m;
        
        if (grafo.isDirigido()) {
            m = n * (n - 1);
        } else {
            m = (n * (n - 1)) / 2;
        }

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnRelacionar, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Matriz de Incidencia");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(panelGrafo);
        frame.setVisible(true);

        btnRelacionar.addActionListener(e -> {
            try {
                if (tabla.isEditing()) {
                    tabla.getCellEditor().stopCellEditing();
                }
                
                grafo.limpiarAristas();

                int aristasCreadas = 0;
              
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    Nodo origen = null, destino = null;
                    
                    for (int i = 0; i < tabla.getRowCount(); i++) {
                        try {
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
                        } catch (NumberFormatException ex) {
                            continue;
                        }
                    }
                    
                    if (origen != null && destino != null) {
                        grafo.agregarArista(origen, destino);
                        aristasCreadas++;
                    }
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                panelGrafo.repaint();
                
                frame.dispose();
                
                if (aristasCreadas > 0) {
                    JOptionPane.showMessageDialog(panelGrafo, 
                        "Se crearon " + aristasCreadas + " aristas/arcos.", 
                        "Relaciones Creadas", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panelGrafo, 
                        "No se crearon aristas. Verifica que hayas configurado valores -1 y 1 correctamente.", 
                        "Sin Relaciones", JOptionPane.WARNING_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelGrafo, 
                    "Error al procesar la matriz: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}

