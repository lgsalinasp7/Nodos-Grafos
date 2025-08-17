@echo off
echo Ejecutando proyecto Grafo...

REM Verificar que existe el directorio bin
if not exist "bin" (
    echo ERROR: No se encuentra el directorio bin. Ejecuta compile.bat primero.
    pause
    exit /b 1
)

REM Ejecutar la aplicacion
java -cp bin org.example.Main

pause
