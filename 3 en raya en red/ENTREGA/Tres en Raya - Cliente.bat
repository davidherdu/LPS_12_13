@echo off

REM ############################################################################################
REM # LABORATORIO DE PROGRAMACIÓN DE SISTEMAS 2012/2013
REM # Ingeniería Técnica en Informática de Sistemas
REM # Departamento de Ingeniería del Software e Inteligencia Artificial
REM # Facultad de Informática, Universidad Complutense de Madrid
REM # 
REM # TRES EN RAYA - VERSIÓN CLIENTE/SERVIDOR
REM # Por Federico Peinado
REM # 
REM # Adaptación del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
REM # http://www.deitel.com/Books/Java/JavaHowtoProgram9eEarlyObjectsVersion/tabid/3622/Default.aspx
REM ############################################################################################

REM Descomentar esta línea (cambiando esta ruta por la que resulte adecuada) si se desea uno asegurar de que una versión concreta de Java estará disponible en esta máquina
REM set PATH=%PATH%;C:\Program Files (x86)\Java\jre7\bin\

REM Descomentar esta línea si se quiere mostrar la versión de Java disponible
REM java -version

REM Librerías necesarias porque contienen las clases del cliente y las comunes al cliente y al servidor de este juego
set CLASSPATH=%CLASSPATH%;lib/tresenraya.jar
set CLASSPATH=%CLASSPATH%;cliente.jar

REM echo Tres en Raya - Cliente
start javaw es.ucm.fdi.lps.tresenraya.cliente.Lanzador

