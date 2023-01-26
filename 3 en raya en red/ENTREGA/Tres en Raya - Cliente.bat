@echo off

REM ############################################################################################
REM # LABORATORIO DE PROGRAMACI�N DE SISTEMAS 2012/2013
REM # Ingenier�a T�cnica en Inform�tica de Sistemas
REM # Departamento de Ingenier�a del Software e Inteligencia Artificial
REM # Facultad de Inform�tica, Universidad Complutense de Madrid
REM # 
REM # TRES EN RAYA - VERSI�N CLIENTE/SERVIDOR
REM # Por Federico Peinado
REM # 
REM # Adaptaci�n del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
REM # http://www.deitel.com/Books/Java/JavaHowtoProgram9eEarlyObjectsVersion/tabid/3622/Default.aspx
REM ############################################################################################

REM Descomentar esta l�nea (cambiando esta ruta por la que resulte adecuada) si se desea uno asegurar de que una versi�n concreta de Java estar� disponible en esta m�quina
REM set PATH=%PATH%;C:\Program Files (x86)\Java\jre7\bin\

REM Descomentar esta l�nea si se quiere mostrar la versi�n de Java disponible
REM java -version

REM Librer�as necesarias porque contienen las clases del cliente y las comunes al cliente y al servidor de este juego
set CLASSPATH=%CLASSPATH%;lib/tresenraya.jar
set CLASSPATH=%CLASSPATH%;cliente.jar

REM echo Tres en Raya - Cliente
start javaw es.ucm.fdi.lps.tresenraya.cliente.Lanzador

