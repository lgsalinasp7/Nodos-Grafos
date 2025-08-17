@echo off
echo Compilando proyecto Grafo...

REM Crear directorio bin si no existe
if not exist "bin" mkdir bin

REM Usar la ruta específica de Java 24
set JAVA_HOME=C:\Users\User\.jdks\openjdk-24.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

REM Verificar que javac esté disponible
javac -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: javac no encontrado en %JAVA_HOME%\bin
    pause
    exit /b 1
)

echo Usando Java desde: %JAVA_HOME%

REM Compilar archivos en orden correcto
javac -d bin src/main/java/model/Nodo.java
javac -d bin -cp bin src/main/java/model/Arista.java
javac -d bin -cp bin src/main/java/model/Grafo.java
javac -d bin -cp bin src/main/java/model/GrafoModel.java
javac -d bin -cp bin src/main/java/controller/GrafoController.java
javac -d bin -cp bin src/main/java/View/GrafoPanel.java
javac -d bin -cp bin src/main/java/View/GrafoFrame.java
javac -d bin -cp bin src/main/java/org/example/Main.java

echo Compilacion completada!
echo Para ejecutar: java -cp bin org.example.Main
pause
