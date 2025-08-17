# Proyecto de Grafos Interactivos

## ¿Qué es este proyecto?

Este es un programa que te permite crear y visualizar **grafos** de manera interactiva. Un grafo es como un mapa con ciudades conectadas por carreteras.

## ¿Qué puedes hacer con este programa?

1. **Crear nodos (ciudades)**: Haz clic en la pantalla blanca para agregar ciudades
2. **Crear conexiones (carreteras)**: Usa la matriz de incidencia para conectar las ciudades
3. **Ver el resultado**: El programa dibuja automáticamente las conexiones

## Conceptos importantes:

### ¿Qué es un Nodo?
- Es como una **ciudad** en el mapa
- Se representa como un círculo con un número
- Cada nodo tiene una posición (x, y) en la pantalla

### ¿Qué es una Arista?
- Es como una **carretera** que conecta dos ciudades
- En grafos dirigidos: la carretera tiene dirección (flecha)
- En grafos no dirigidos: la carretera es de doble sentido

### ¿Qué es la Matriz de Incidencia?
Es una tabla que muestra cómo están conectados los nodos:

```
      E1  E2  E3  E4
1    -1   0   1   1
2     1  -1   0   0
3     0   1  -1  -1
```

**Para grafos dirigidos:**
- `-1` = nodo de origen (de donde sale la flecha)
- `1` = nodo de destino (a donde llega la flecha)
- `0` = no hay conexión

**Para grafos no dirigidos:**
- `1` = nodo conectado
- `0` = no hay conexión

## Fórmulas importantes:

### Máximo de conexiones posibles:
- **Grafo dirigido**: `n(n-1)`
- **Grafo no dirigido**: `n(n-1)/2`

Donde `n` es el número de nodos.

**Ejemplo con 4 nodos:**
- Dirigido: 4 × 3 = 12 conexiones máximas
- No dirigido: (4 × 3) ÷ 2 = 6 conexiones máximas

## Cómo usar el programa:

1. **Ejecuta el programa** con `run.bat`
2. **Agrega nodos**: Haz clic en la pantalla blanca
3. **Abre la matriz**: Presiona el botón "Abrir Matriz"
4. **Elige el tipo**: Selecciona "Dirigido" o "No dirigido"
5. **Configura conexiones**: Llena la matriz con -1, 0, 1 según necesites
6. **Relaciona**: Presiona "Relacionar" para ver las conexiones

## Estructura del código (MVC):

### Model (Datos):
- `Grafo.java`: Guarda toda la información del grafo
- `Nodo.java`: Representa una ciudad
- `Arista.java`: Representa una carretera

### View (Interfaz):
- `GrafoFrame.java`: La ventana principal
- `GrafoPanel.java`: Donde se dibuja el grafo

### Controller (Lógica):
- `GrafoController.java`: Maneja las acciones del usuario

## Archivos importantes:
- `Main.java`: Inicia el programa
- `compile.bat`: Compila el código
- `run.bat`: Ejecuta el programa

## Notas para el desarrollo:
- El código está comentado para facilitar la comprensión
- Usa Java Swing para la interfaz gráfica
- Sigue el patrón MVC (Model-View-Controller)
