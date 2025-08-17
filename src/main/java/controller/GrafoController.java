package controller;

import model.*;
import View.GrafoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GrafoController es como el cerebro de la aplicación
 * Maneja todas las acciones que hace el usuario (clics, botones, etc.)
 */
public class GrafoController {
    private Grafo grafo;  // El grafo que estamos controlando

    /**
     * Constructor: crea el controlador para un grafo
     * @param grafo - el grafo que vamos a controlar
     */
    public GrafoController(Grafo grafo) {
        this.grafo = grafo;
    }

    /**
     * Crea un "escuchador" del mouse que detecta cuando haces clic
     * Cuando haces clic en el panel, se agrega un nuevo nodo en esa posición
     */
    public MouseAdapter crearMouseListener(GrafoPanel panel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cuando haces clic, agregamos un nodo en las coordenadas del clic
                grafo.agregarNodo(e.getX(), e.getY());
                panel.repaint();  // Redibujamos el panel para mostrar el nuevo nodo
            }
        };
    }

    /**
     * Muestra la ventana de la matriz de incidencia
     * La matriz de incidencia es una tabla que muestra cómo están conectados los nodos
     */
    public void mostrarTablaMatriz(GrafoPanel panelGrafo) {
        // Verificamos que haya nodos antes de crear la matriz
        if (grafo.getNodos().isEmpty()) {
            JOptionPane.showMessageDialog(panelGrafo, "Agrega nodos antes de crear la matriz.");
            return;
        }

        // Preguntamos al usuario si quiere un grafo dirigido o no dirigido
        String[] opciones = {"Dirigido", "No dirigido"};
        int seleccion = JOptionPane.showOptionDialog(panelGrafo, "Selecciona el tipo de grafo",
                "Tipo de Grafo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        
        // Si el usuario cancela, no hacemos nada
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return; 
        }
        
        // Configuramos el tipo de grafo según la selección del usuario
        grafo.setDirigido(seleccion == 0);  // 0 = dirigido, 1 = no dirigido

        // Calculamos el tamaño de la matriz
        int n = grafo.getNodos().size();  // n = número de nodos (filas)
        int m;  // m = número máximo de aristas posibles (columnas)
        
        // FÓRMULAS PARA EL MÁXIMO DE CONEXIONES:
        // Grafo dirigido: n(n-1) - cada nodo puede conectarse a todos los demás
        // Grafo no dirigido: n(n-1)/2 - cada conexión cuenta una sola vez
        if (grafo.isDirigido()) {
            m = n * (n - 1);  // Ejemplo: 4 nodos = 4*3 = 12 conexiones máximas
        } else {
            m = (n * (n - 1)) / 2;  // Ejemplo: 4 nodos = (4*3)/2 = 6 conexiones máximas
        }

        // Creamos los nombres de las columnas (E1, E2, E3, etc.)
        String[] columnNames = new String[m];
        for (int j = 0; j < m; j++) {
            columnNames[j] = "E" + (j + 1);  // E1, E2, E3, E4...
        }

        // Creamos la matriz llena de ceros
        Object[][] data = new Object[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = 0;  // Inicialmente todas las celdas tienen valor 0
            }
        }

        // Creamos la tabla con la matriz
        JTable tabla = new JTable(data, columnNames);
        JScrollPane scroll = new JScrollPane(tabla);

        // Creamos el botón "Relacionar" que procesará la matriz
        JButton btnRelacionar = new JButton("Relacionar");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);  // La tabla va en el centro
        panel.add(btnRelacionar, BorderLayout.SOUTH);  // El botón va abajo

        // Creamos la ventana de la matriz
        JFrame frame = new JFrame("Matriz de Incidencia");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(panelGrafo);
        frame.setVisible(true);

        // Cuando se hace clic en "Relacionar", procesamos la matriz
        btnRelacionar.addActionListener(e -> {
            try {
                // Aseguramos que la tabla termine de editarse
                if (tabla.isEditing()) {
                    tabla.getCellEditor().stopCellEditing();
                }
                
                // Limpiamos todas las aristas existentes
                grafo.limpiarAristas();

                int aristasCreadas = 0;  // Contador de aristas creadas
              
                // Recorremos cada columna de la matriz (cada posible arista)
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    Nodo origen = null, destino = null;
                    
                    // Recorremos cada fila (cada nodo) en esta columna
                    for (int i = 0; i < tabla.getRowCount(); i++) {
                        try {
                            // Obtenemos el valor de la celda
                            int valor = Integer.parseInt(tabla.getValueAt(i, j).toString());
                            
                            if (grafo.isDirigido()) {
                                // Para grafos dirigidos:
                                // -1 = nodo de origen (de donde sale la flecha)
                                //  1 = nodo de destino (a donde llega la flecha)
                                if (valor == -1) origen = grafo.getNodos().get(i);
                                if (valor == 1) destino = grafo.getNodos().get(i);
                            } else {
                                // Para grafos no dirigidos:
                                //  1 = nodo conectado (no hay dirección)
                                if (valor == 1) {
                                    if (origen == null) origen = grafo.getNodos().get(i);
                                    else destino = grafo.getNodos().get(i);
                                }
                            }
                        } catch (NumberFormatException ex) {
                            // Si hay un error al leer el número, lo ignoramos
                            continue;
                        }
                    }
                    
                    // Si encontramos origen y destino, creamos la arista
                    if (origen != null && destino != null) {
                        grafo.agregarArista(origen, destino);
                        aristasCreadas++;
                    }
                }
                
                // Pequeña pausa para que se vea el proceso
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                // Redibujamos el panel para mostrar las nuevas aristas
                panelGrafo.repaint();
                
                // Cerramos la ventana de la matriz
                frame.dispose();
                
                // Mostramos un mensaje con el resultado
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
                // Si hay algún error, lo mostramos
                JOptionPane.showMessageDialog(panelGrafo, 
                    "Error al procesar la matriz: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}

