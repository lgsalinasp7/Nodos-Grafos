package View;

import model.Grafo;
import controller.GrafoController;
import View.GrafoPanel;

import javax.swing.*;
import java.awt.*;

/**
 * GrafoFrame es la ventana principal de nuestra aplicación
 * Es como el marco de una foto donde se muestra todo
 */
public class GrafoFrame extends JFrame {

    /**
     * Constructor: crea la ventana principal con todos sus componentes
     * @param grafo - el grafo que vamos a mostrar
     * @param controller - el controlador que maneja las acciones
     */
    public GrafoFrame(Grafo grafo, GrafoController controller) {
        super("Grafo Interactivo");  // Título de la ventana

        // Creamos el panel donde se dibujará el grafo (es como el lienzo de un pintor)
        View.GrafoPanel panelGrafo = new View.GrafoPanel(grafo);

        // Creamos el botón "Abrir Matriz" que abrirá la tabla de incidencia
        JButton btnMatriz = new JButton("Abrir Matriz");
        // Cuando se haga clic en el botón, se ejecutará el método mostrarTablaMatriz
        btnMatriz.addActionListener(e -> controller.mostrarTablaMatriz(panelGrafo));

        // Organizamos los elementos en la ventana
        setLayout(new BorderLayout());  // BorderLayout organiza elementos en 5 zonas: norte, sur, este, oeste, centro
        add(panelGrafo, BorderLayout.CENTER);  // El panel del grafo va en el centro
        add(btnMatriz, BorderLayout.SOUTH);    // El botón va en la parte inferior

        // Configuramos el tamaño y comportamiento de la ventana
        setSize(800, 600);  // Ancho: 800 píxeles, Alto: 600 píxeles
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Al cerrar la ventana, se cierra el programa
        setVisible(true);  // Hacemos visible la ventana

        // Agregamos el "escuchador" del mouse al panel
        // Esto permite que cuando hagas clic en el panel, se agregue un nodo
        panelGrafo.addMouseListener(controller.crearMouseListener(panelGrafo));
    }
}
