Anmerkung:
Es handelt sich um eine Erweiterung des Dakin-Solvers. Deshalb ist es zu empfehlen 
die Quellen des bisherigen Solvers zu sichern.

Vorgehensweise:
Zun�chst muss das Verzeichnis komplett in das Verzeichnis EXEC des Dakin-Solvers auf Neptun 
kopiert werden. 
Au�erdem sollte das Verzeichnis src in das Verzeichnis SOURCE des Dakin-Solvers auf Neptun
kopiert werden.



Zu beachten:
Beim Programmstart m�ssen zus�tzlich noch die beiden Jar-Files
"jcommon-0.7.1.jar" und "jfreechart-0.9.4.jar" angegeben werden. 
Dazu ist folgenderma�en vorzugehen:


Der classpath in der Datei Dakin.bat muss noch an die entsprechenden Verzeichnisse
des Neptun angepasst werden:

Folgende Zeile muss ge�ndert werden

java -classpath "C:\TEMP\Dakin\Dakin\classes;C:\TEMP\Dakin\Dakin\classes\jcommon-0.7.1.jar;C:\TEMP\Dakin\Dakin\classes\jfreechart-0.9.4.jar" Dakin.dakin

in beispielsweise

java -classpath "\Neptun\BESF\Solver\Dakin\EXEC;\Neptun\BESF\Solver\Dakin\EXEC\jcommon-0.7.1.jar;\Neptun\BESF\Solver\Dakin\EXEC\jfreechart-0.9.4.jar" Dakin.dakin




