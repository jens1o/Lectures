RMI-Beispiel 
=====================================

:Zuletzt getestet: 2024.02. Java 21


Ausführung
-----------

Im Verzeichnis unterhalb des Rootpackages ("rmi") - mit root-Rechten (d. h. **sudo**):

1. [..]/ds-architekturen/private $ sudo rmiregistry
2. [..]/ds-architekturen/private $ java -cp . -Djava.rmi.server.codebase=file:./ -Djava.security.policy=server.policy rmi.TimeRegistrar localhost
3. [..]/ds-architekturen/private $ java -cp . -Djava.rmi.server.codebase=file:./ -Djava.security.policy=client.policy rmi.TimeClient localhost