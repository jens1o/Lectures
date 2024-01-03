.. meta:: 
    :author: Michael Eichberg
    :keywords: "TCP"
    :description lang=de: Verteilte Systeme
    :id: lecture-a-primer-in-network-security
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html


Eine erste Einführung in die Sicherheit von (verteilten) Systemen
===================================================================

:Dozent: **Prof. Dr. Michael Eichberg**
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|

.. container:: footer-left tiny
    
    Die Folien basieren in weiten Teilen auf einem Foliensatz von Prof. Dr. Henning Pagnia.
    
    Alle Fehler sind meine eigenen.


.. class:: new-section transition-move-to-top

Klassische Sicherheitsprinzipien
----------------------------------



Wiederholung: Klassische Sicherheitsprinzipien
-----------------------------------------------

(Jerome Saltzer and Michael Schroeder, 1975)

.. class:: incremental

:Principle of Economy of Mechanism (aka Principle of Simplicity): Die Sicherheitsmechanismen sollten so einfach wie möglich sein.

.. class:: incremental

:Principle of Fail-Safe Defaults: Standardmäßig sollte der Zugriff auf Ressourcen verweigert werden.

.. class:: incremental

:Principle of Complete Mediation: Jeder Zugriff auf eine Ressource sollte überprüft werden.


Wiederholung: Klassische Sicherheitsprinzipien
-----------------------------------------------


:Principle of Least Authority (aka POLA)/ Principle of Least Privilege: Jedes Programm und jeder Benutzer sollte nur die für seine Aufgabe unbedingt notwendigen Rechte besitzen.

.. class:: incremental

:Principle of Separation of Privilege: Ein System sollte in mehrere POLA konforme Komponenten unterteilt sein. Sollte eine Komponente kompromittiert sein, dann sind die Möglichkeiten des Angreifers dennoch begrenzt. (Eng verwandt mit dem POLA.)


Wiederholung: Klassische Sicherheitsprinzipien
-----------------------------------------------

:Principle of Least Common Mechanism: Die Sicherheitsmechanismen sollten über Nutzer hinweg möglichst wenig Gemeinsamkeiten haben.

.. class:: incremental

:Principle of Open Design (vgl. Kerckhoffs Prinzip): Die Sicherheit des Systems sollte nicht von der Geheimhaltung der Sicherheitsmechanismen abhängen (sondern nur vom Schlüssel). 

.. container:: supplemental 

    **Beispiel - Principle of Least Common Mechanism**

    Z.B. sollten keine gemeinsamen Speicherbereiche verwendet werden und es ist deswegen sinnvoll - wenn möglich - auf Implementierungen im Kernel zu verzichten und statt dessen auf User-Space-Implementierungen zu setzen. 
    
       TCP Connection Hijacking wird z.B. durch die Implementierung des TCP Stacks im Kernel ermöglicht (:math:`\Leftrightarrow` :ger-quote:`Principle of Least Common Mechanism`).



Wiederholung: Klassische Sicherheitsprinzipien
-----------------------------------------------


:Principle of Psychological Acceptability: Die Sicherheitsmechanismen sollten einfach zu verstehen und zu benutzen sein.
  
.. class:: incremental

:Principle of Isolation: Die Sicherheitsmechanismen sollten so entworfen sein, dass Fehler in einem Teil des Systems nicht die Sicherheit des gesamten Systems gefährden; d.h. die einzelnen Komponenten sollten möglichst unabhängig voneinander sein und nur über wohldefinierte  Schnittstellen miteinander kommunizieren und entsprechende Sicherheitsüberprüfungen durchführen. 

.. container:: supplemental

    **Beispiel - Principle of Isolation:**

    Typischerweise kommuniziert zum Beispiel ein Basebandchip (WIFI, LTE, 5G, ...) mit dem Betriebssystem über eine minimale Schnittstelle über die nur Nachrichten übermittelt werden können, die leicht auf ihre Korrektheit überprüft werden können. Insbesondere erfolgt kein direkter Zugriff auf den Speicher des Betriebssystems.

    Einen Angreifer ist es somit ggf. möglich den Basebandchip anzugreifen und ggf. zu kompromittieren, aber er kann nicht direkt auf das Betriebssystem zugreifen und Nachrichten, die bereits auf Betriebssystem oder Anwendungsebene verschlüsselt werden, sind weiterhin sicher.


Wiederholung: ergänzende Sicherheitsprinzipien
-----------------------------------------------

:Principle of Modularity: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie unabhängig voneinander implementiert und geprüft werden können.

.. class:: incremental

:Principle of Layering: Die Sicherheitsmechanismen sollten in Schichten organisiert sein.

.. class:: incremental

:Principle of Least Astonishment: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie keine Überraschungen für die Benutzer bereithalten.


.. container:: supplemental

    Beispiel für ein Schutzsystem für Netzwerke, dass mehrere Schichten verwendet:

    - einfache (und effiziente) Paketfilter auf unterster Ebene
    - zustandsbehaftete Paketfilter auf der nächsten bzw. der Anwendungsebene


.. class:: new-section transition-fade

Transmission Control Protocol (TCP) 
-------------------------------------


.. class:: vertical-title

TCP Grundlagen
-------------------

.. class:: incremental more-space-between-list-items inline-block

- Protokoll der Schicht 4 (Transport Layer) basiert auf IP
- verbindungsorientierte Kommunikation zweier Rechner im Internet zuverlässig und geordnet:

  .. class:: incremental

  - Verwerfen von Duplikaten und fehlerhaft übertragener Pakete
  - automatisches Wiederversenden fehlender Pakete
  - Nachrichtenpuffer: Daten werden in korrekter Reihenfolge an Applikation zugestellt

- Verbindungsaufbau immer zwischen zwei Sockets (Socket-Adresse: IP Adresse und 16 Bit-Port-Nummer)


Aufbau einer TCP Verbindung
-----------------------------

Dreifacher Handshake:

.. container:: supplemental

    **Terminologie**:

    :SYN: :eng:`synchronize (session establishment)`
    :ACK: :eng:`acknowledge`
    :RST: :eng:`reset`

    **Verbindungsaufbau - Ablauf**:

    1. Client sendet SYN Paket mit initialer Sequenznummer 1000 an den Server.
    2. Server sendet ein SYN-ACK Paket mit einem SYN mit seiner initialen Sequenznummer 2000 und ein ACK mit der Sequenznummer 1001 an den Client
    3. Client sendet ein ACK Paket mit Sequenznummer 2001 an den Server; danach ist die Verbindung aufgebaut.

    Das Betriebssystem sollte die initialen Sequenznummern zufällig wählen, so dass ein Angreifer diese nicht leicht vorhersagen kann. Beide Seiten haben eigene Sequenznummern, die unabhängig voneinander sind.

    Bei einer laufenden Verbindung werden die Sequenznummern inkrementiert und es ist nicht (mehr) erkennbar wer die Verbindung aufgebaut hat.

.. container:: stack

    .. container:: layer

        .. raw:: html
            :class: center-child-element

            <svg width="1200" height="600" viewBox="0 0 1200 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                <text x="125" y="75" style="font-weight: bolder">Client</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                
                <text x="500" y="65" transform="rotate(6.6)">SYN(1000)</text>
                <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>

    .. container:: layer overlay incremental

        .. raw:: html
            :class: center-child-element

            <svg height="600" width="1200" viewBox="0 0 1200 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>

                <text x="270" y="300" transform="rotate(-6.6)">SYN(2000), ACK(1001)</text>
                <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
                
            </svg>

    .. container:: layer overlay incremental

        .. raw:: html
            :class: center-child-element

            <svg height="600" width="1200" viewBox="0 0 1200 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                
                <text x="555" y="315" transform="rotate(6.6)">ACK(2001)</text>
                <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>




Ports bei TCP
----------------

.. class:: incremental

- Port-Nummern werden für die Kommunikation zwischen zwei Diensten/Prozessen verwendet
- Ports sind 16 Bit Zahlen (0-65535)
- (Unix) Ports < 1024 sind privilegiert (nur root kann diese öffnen)
- einige Port-Nummern sind Standarddiensten zugeordnet
  

.. class:: small

Port-Nummern einiger Standarddienste [#]_
------------------------------------------

**Ungeschützte Dienste**

.. csv-table::
    :header: Protokoll, Dienst, Portnummer
    :class: highlight-line-on-hover
    :widths: 100, 600, 50

    ftp, Dateitransfer, 21
    smtp, Simple Mail Transfer Protocol, 25
    dns, Domain Name System, 53
    http, Hypertext Transfer Protocol, 80
    login, Login auf entfernte Rechner, 513

**Geschützte Dienste**

.. csv-table::
    :header: Protokoll, Dienst, Portnummer
    :class: highlight-line-on-hover 
    :widths: 100, 600, 50

    ssh, Secure Shell, 22
    https, HTTP über Secure Socket Layer, 443
    smtps, SMTP über Secure Socket Layer, 465
    imaps, IMAP über Secure Socket Layer, 993
    pop3s, POP3 über Secure Socket Layer, 995


.. [#] `Port numbers assigned by IANA <https://www.iana.org/assignments/service-names-port-numbers>`__



Angriffe auf TCP - Motivation
--------------------------------

.. class:: incremental

- Netzwerkprogrammierung mit TCP ist relativ komfortabel. 
- Viele Dienste sind mit TCP implementiert.
- Angreifer nutzen Schwachstellen in TCP Diensten aus.
- Server haben heutzutage i. Allg. alle nicht verwendeten Dienste geschlossen. Angreifer muss verwundbare Dienste zum Beispiel durch Port Scans finden.


Port Scans: TCP Connect Scan
-------------------------------

.. container:: two-columns

    .. container:: column 
        
        .. class:: incremental

          - vollständiger Verbindungsaufbau zu allen bzw. zu ausgewählten Ports
          
          .. container:: incremental

              **Bewertung**:

              - simpelster Port Scan
              - große Entdeckungsgefahr (Scan selbst ist kein Angriff)
              - mögliche Verbesserung: zwischen dem Scannen mehrerer Ports Pausen einstreuen (Wie lange?)

    .. container:: column no-border

        .. raw:: html

            <svg width="900" height="440" viewBox="0 0 1200 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                <text x="125" y="75" style="font-weight: bolder">Scanner</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                
                <text x="500" y="65" transform="rotate(6.6)">SYN</text>
                <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="390" y="300" transform="rotate(-6.6)">SYN / ACK</text>
                <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
                
                <text x="555" y="315" transform="rotate(6.6)">ACK</text>
                <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>


Port Scans: TCP SYN Scan
-----------------------------

.. container:: two-columns

    .. container:: column 

        .. class:: incremental
        
        1. Senden eines TCP-Segments mit gesetztem SYN-Flag an einen Port
        2. falls der *Port offen* ist, kommt SYN/ACK zurück danach RST senden
        3. falls der *Port nicht offen* ist, kommt RST (oder nichts) zurück

        .. container:: incremental 
        
            **Bewertung**:
            
            - kein vollständiger Verbindungsaufbau
            - meist nicht protokolliert
            - geringe(re) Entdeckungsgefahr

    .. container:: column    

        .. raw:: html

            <svg width="900" height="440" viewBox="0 0 1200 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                <text x="125" y="75" style="font-weight: bolder">Scanner</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1000" y1="100" x2="1000" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1000" y1="400" x2="1000" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                
                <text x="500" y="65" transform="rotate(6.6)">SYN</text>
                <line x1="200" y1="110" x2="1000" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="390" y="300" transform="rotate(-6.6)">SYN / ACK</text>
                <line x1="1000" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
                
                <text x="555" y="315" transform="rotate(6.6)">RST</text>
                <line x1="200" y1="300" x2="1000" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>

        
Port Scans: Stealth Scans
-----------------------------

Versenden eines für den Verbindungsaufbau ungültigen TCP-Segments an einen Port:

  .. class:: incremental

  - NULL-Scan (keine Flags)
  - ACK-Scan (ACK-Flag)
  - FIN-Scan (FIN-Flag)
  - XMAS-Scan (alle Flags)

  .. class:: incremental

  Laut RFC kommt RST zurück, falls Port offen. (Reaktion aber abhängig vom Betriebssystem)

.. container:: incremental 
  
    **Bewertung**:

    - Zugriff wird meist nicht protokolliert
    - Scan bleibt unbemerkt


.. container:: supplemental 

    **XMAS-Scan**: 
    
    Bei diesem Scan sind alle Flags gesetzt; ein XMAS-Scan wird auch als Christmas-Tree-Scan bezeichnet, da das Paket erleuchtet ist wie ein Weihnachtsbaum.


Port Scans: Idle Scan [#]_
-----------------------------

Bei allen bisher betrachteten Scans kann der Scanner prinzipiell identifiziert werden. Unter Verwendung eines sog. Zombies geht es auch anders:

.. container:: two-columns 

    .. container:: column

        Sondiere IP ID des Zombies:

        .. image:: idle-scan/idle-scan-step1.svg 
            :alt: Idle Scan - Schritte 1-2
            :align: left
            :width: 700px

    .. container:: column faded-to-white

        Starte Scan:

        .. image:: idle-scan/idle-scan-step2.svg 
            :alt: Idle Scan - Schritte 3-5
            :align: left
            :width: 750px

.. container:: supplemental 

    Zombies: ein Rechner (Computer, Drucker oder anderes IoT Gerät) im Internet *möglichst ohne eigenen Netzverkehr* und mit :ger-quote:`altem` Betriebssystem, bei dem die IP ID in vorhersehbarer Weise inkrementiert wird.

    Sollte ein Intrusion Detection System vorhanden sein, so wird dieses den Zombie als Angreifer identifizieren.

    **Grundlegende Idee**: Der Zombie sendet ein RST Paket zurück, da er kein SYN gesendet hat und kein SYN/ACK erwarte. Dadurch erfährt der Angreifer die aktuelle IP ID des Zombies. Über diesen Seitenkanal - d.h. die Veränderung der IP ID des Zombies - kann der Angreifer nun den Zustand des Ports auf dem Zielrechner ermitteln.

.. [#] `NMap Book <https://nmap.org/book/idlescan.html>`__

    

Port Scans: Idle Scan
-----------------------------

.. container:: two-columns 

    .. container:: column

        Starte Scan:

        .. image:: idle-scan/idle-scan-step2.svg 
            :alt: Idle Scan - Schritte 3-5
            :align: left
            :width: 750px

    .. container:: column   

        Sondiere IP ID des Zombies:

        .. image:: idle-scan/idle-scan-step3.svg 
            :alt: Idle Scan - Schritt 6
            :align: right
            :width: 700px



Port Scans: Idle Scan - Zusammenfassung
----------------------------------------

- Angreifer sendet SYN/ACK Paket an Zombie
- der Zombie antwortet mit RST und enthüllt seine IP ID (:eng:`IP Fragment Identification Number`).
- Angreifer sendet SYN ("vom" Zombie) an Port des Servers
- [**Port offen**] Der Zielrechner antwortet mit SYN/ACK an den Zombie, wenn der Port offen ist.
  
  [**Port geschlossen**] Der Zielrechner antwortet mit RST an den Zombie, wenn der Port geschlossen ist. Dies wird vom Zombie ignoriert.
- [**Port offen**] Der Zombie antwortet mit RST, da er kein SYN gesendet hat und kein SYN/ACK erwartet und erhöht seine IP ID. 
- Der Angreifer sendet wieder ein SYN/ACK an den Zombie, um die IP ID zu erfahren. 

.. container:: supplemental 

    Mit einem IDLE Scan kann nicht unterschieden werden, ob der Port geschlossen oder gefiltert ist.

Port Scans mit nmap
-----------------------

.. class:: incremental

- alle Arten von Port-Scans möglich
- auch OS fingerprinting
- u. U. sogar Ermittlung der Versionsnummern von Diensten

.. code:: bash 
    :class: incremental smaller copy-to-clipboard

    $ nmap 192.168.178.121 -Pn
    Starting Nmap 7.94 ( https://nmap.org ) at 2023-12-14 13:16 PST
    Nmap scan report for Michaels-MacBook-Pro (192.168.178.121)
    Host is up (0.0056s latency).
    Not shown: 995 filtered tcp ports (no-response)
    PORT     STATE SERVICE
    53/tcp   open  domain
    88/tcp   open  kerberos-sec
    445/tcp  open  microsoft-ds
    5000/tcp open  upnp
    7000/tcp open  afs3-fileserver

.. container:: supplemental

    **OS-Fingerprinting**

    Beim OS-Fingerprinting werden Datenpakete analysiert, die aus einem Netzwerk stammen, um Informationen für spätere Angriffe zu gewinnen. Durch die Erkennung des Betriebssystems, mit dem ein Netzwerk arbeitet, haben Hacker es leichter, Schwachstellen zu finden und auszunutzen. OS-Fingerprinting kann auch Konfigurationsattribute von entfernten Geräten sammeln. Diese Art von Aufklärungsangriff ist in der Regel (einer) der erste(n) Schritt(e).

    Es gibt zwei Arten von OS-Fingerprinting: (1) Aktiv und (2) passiv.

        (1) Bei einem aktiven OS-Fingerprinting-Versuch senden die Angreifer ein Paket an das Zielsystem und warten auf eine Antwort, um den Inhalt des TCP-Pakets zu analysieren. 
        
        (2) Bei einem passiven Versuch agieren die Angreifer eher als "Schnüffler", der keine absichtlichen Änderungen oder Aktionen im Netzwerk vornimmt. Passives OS-Fingerprinting ist ein unauffälligerer, aber wesentlich langsamerer Prozess. 


Port Knocking
---------------- 

.. class:: incremental

- Ein Knock-Daemon versteckt offene Ports auf dem Server.
- Zugriffe auf alle Ports werden im Log-File protokolliert.
- Knock-Daemon beobachtet das Log-File.
- Erst nach Erkennen einer vordefinierten (Einmal-)Klopfsequenz öffnet der Knock-Daemon den gewünschten Port für diesen Client.
- Client kann nun die Verbindung aufbauen.

.. container:: supplemental

    
    **Weiterführend**

    Alternativen zu einer Knock-Sequenz ist zum Beispiel, dass der Port nur dann als offen gilt, wenn die IP ID eine bestimmte Sequenznummer aufweist.

    M. Krzywinski: Port Knocking: Network Authentication Across Closed Ports in SysAdmin Magazine 12: 12-17. (2003)

    TCP Stealth

Connection Hijacking
-------------------------

Angreifer übernimmt eine bestehende - zum Beispiel eine bereits durch (Einmal-)Passwort authentisierte - Verbindung.

.. image:: connection-hijacking.svg 
    :alt: Connection Hijacking (einfache Variante)
    :align: center
    :height: 800px


.. container:: supplemental 

    TCP/IP-Hijacking ist eine Form eines Man-in-the-Middle-Angriffs. Der Angreifer bestimmt erst die IP-Adressen der beiden Sitzungsteilnehmer.
    
    Danach gibt es mehrere Möglichkeiten: 

    - Der Angreifer schickt ("in einer Pause") ein Paket mit der passenden Sequenznummer an den Server. 
    
      *(Dies kann dann in einem ACK-Storm enden, was ggf. unterbunden werden muss (zum Beispiel durch das Senden eines RSTs), oder ignoriert werden kann.)*

    - Der Angreifer macht einen Client mit einem DoS-Angriff unerreichbar, um sich dann mit dem Anderen zu verbinden, indem er die Netzwerk-ID des ausgeschalteten Clients nutzt.


Denial-of-Service (DoS) Angriffe
------------------------------------

Ziel des Angreifers: Lahmlegen eines Dienstes oder des ganzen Systems ...

- durch Ausnutzen von Schwachstellen (:eng:`vulnerabilities`, z.B. Buffer Overflow)
- durch Generierung von Überlast (Ausschöpfen von RAM, CPU, Netzwerkbandbreite, ...)

.. admonition:: Beispiel: Ping-of-Death
    :class: incremental smaller

    (Historisch: aus dem Jahr 1997)

    Ein ``ping`` verwendet Internet Control Message Protocol (ICMP) üblicherweise kleine Nachrichten, verwendete Länge ist aber einstellbar.

    Falls zu groß ⇒ Buffer Overflow ⇒ Systemabsturz!
    
    Variante: mittels Fragmentierung ließen sich generell übergroße IP-Pakete (>65,536 Byte) erstellen.


Denial-of-Service: SYN-flooding Angriff
-----------------------------------------

.. class:: incremental

- Angriff auf Design
- Angreifer sendet eine Verbindungsaufbauanforderung (gesetztes SYN-Flag) an Zielmaschine
- Server generiert eine halboffene TCP-Verbindung
- Angreifer wiederholt in schneller Folge dieses erste Paket zum Verbindungsaufbau

  ⇒ vollständiges Füllen der internen Systemtabelle

  ⇒ Anfragen normaler Benutzer werden zurückgewiesen

- Angreifer verwendet i. Allg. IP-Spoofing weswegen Firewalls wirkungslos sind.
- Abwehr: SYN-Cookies 


`SYN-Cookies - D J. Bernstein <https://cr.yp.to/syncookies.html>`__
-----------------------------------------------------------------------

SYN-Cookies sind speziell konstruiert initiale Sequenznummern.

.. container:: stack

    .. container:: layer

        .. raw:: html

            <svg width="1700" height="600" viewBox="0 0 1700 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                <text x="125" y="75" style="font-weight: bolder">Client</text>
                <line x1="200" y1="100" x2="200" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="200" y1="400" x2="200" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                
                <text x="925" y="75" style="font-weight: bolder">Server</text>
                <line x1="1500" y1="100" x2="1500" y2="400" style="stroke:rgb(0,0,0);stroke-width:3" />
                <line x1="1500" y1="400" x2="1500" y2="550" stroke-dasharray="5,5" style="stroke:rgb(0,0,0);stroke-width:3" />
                
                <text x="500" y="75" transform="rotate(4.25)">SYN(1000)</text>
                <line x1="200" y1="110" x2="1500" y2="190" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>

    .. container:: layer overlay incremental

        .. raw:: html

            <svg height="600" width="1700" viewBox="0 0 1700 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>

                <text x="270" y="290" transform="rotate(-4.25)">SYN(2000), ACK(with cookie)</text>
                <line x1="1500" y1="200" x2="200" y2="290" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
            </svg>

        .. container:: smaller

            Der Cookie ermöglicht es, dass keine Informationen im Speicher gehalten werden müssen. Der Cookie encodiert die Informationen, die der Server benötigt, um die Verbindung aufzubauen: Client IP, time window, etc.

    .. container:: layer overlay incremental

        .. raw:: html

            <svg height="600" width="1800" viewBox="0 0 1800 600" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <marker 
                    id="arrow"
                    viewBox="0 0 10 10"
                    refX="10"
                    refY="5"
                    markerWidth="8"
                    markerHeight="8"
                    orient="auto-start-reverse">
                    <path d="M 0 0 L 10 5 L 0 10 z" />
                    </marker>
                </defs>
                
                <text x="555" y="325" transform="rotate(4.2)">ACK(with cookie(+1))</text>
                <line x1="200" y1="300" x2="1500" y2="390" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>

                <text x="1515" y="340" style="font-size:40px">Validierung</text>
                <text x="1515" y="390" style="font-size:40px">des Cookie</text>
                <line x1="1600" y1="400" x2="1600" y2="455" style="stroke:rgb(0,0,0);stroke-width:3" marker-end="url(#arrow)"/>
                <text x="1515" y="490" style="font-size:40px">ggf. </text>
                <text x="1515" y="540" style="font-size:40px">Verbindungs-</text>
                <text x="1515" y="590" style="font-size:40px">aufbau</text>

            </svg>



Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

Opfer wird von sehr vielen Angreifern mit Nachrichten überflutet.

.. container:: incremental

    Ein Beispiel: Smurf-Angriff:

    .. image:: smurf-angriff.svg 
        :alt: Smurf Angriff
        :align: center
        :height: 800px


Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

.. class:: incremental

- Bot-Netze (Botnetze) werden verwendet, um DDoS-Angriffe durchzuführen.
- Bot-Netze können viele 10.000 Rechner umfassen.
- IoT Geräte sind besonders beliebt (z.B. IP-Kameras, Smart-TVs, Smart-Home Geräte, ...), da diese oft nicht ausreichend geschützt sind und trotzdem permanent mit dem Internet verbunden sind.
- Beliebte Ziele:

  - Onlinespieleserver
  - Banking-Portale
  - politische Webseiten
- Firewalls und Intrusion Detection Systeme sind meist wirkungslos, da die Angriffe von vielen verschiedenen IP-Adressen kommen.


Distributed-Reflected-Denial-of-Service (DRDoS) Angriff
------------------------------------------------------------

- Idee:

  .. class:: incremental smaller
  
  - Es wird eine Anfrage an einen Server gesendet, die eine große Antwort auslöst. (z.B. hat(te) der NTP Monlist Befehl eine Antwort, die ca. 200 Fach größer ist als die Anfrage!)
  - Mittels IP-Spoofing wird die IP-Adresse des Opfers als Absenderadresse verwendet.
  - Es werden insbesondere Dienste basierend auf UDP verwendet, da hier keine Verbindung aufgebaut werden muss.

.. class:: incremental smaller

- Nehmen einen signifikanten Teil aller DDoS-Angriffe ein. 
- Die Tatsache, dass die Sender legitime Server sind, erschwert die Abwehr.
- :eng:`Egress Filtering` kann helfen, die Verwendung von IP-Spoofing zu verhindern. In diesem Fall verwirft der Router alle Pakete, die eine Absenderadresse verwenden, die nicht aus dem eigenen Netzwerk stammt. 


.. container:: supplemental
    
    Bereits im Jahr 2018 wurde ein Angriff mit einer Bandbreite von 1,7 TBit/s beobachtet.


`Distributed Denial-of-Service (DDoS) Angriffe - Beispiel <https://cloud.google.com/blog/products/identity-security/google-cloud-mitigated-largest-ddos-attack-peaking-above-398-million-rps>`__
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


.. epigraph::

    [...] Google's DDoS Response Team has observed the trend that distributed denial-of-service (DDoS) attacks are **increasing exponentially in size**. Last year, we blocked the largest DDoS attack recorded at the time. This August [2023], we stopped an even larger DDoS attack — 7½ times larger — that also used new techniques to try to disrupt websites and Internet services.

    This new series of DDoS attacks reached **a peak of 398 million requests per second (rps)**, and relied on a novel HTTP/2 “Rapid Reset” technique based on stream multiplexing that has affected multiple Internet infrastructure companies. By contrast, last year's largest-recorded DDoS attack peaked at 46 million rps.


Distributed Denial-of-Service (DDoS) Angriffe 
------------------------------------------------

.. container:: incremental 

    Beispiele:

    - TCP Stack Attacks (SYN, FIN, RST, ACK, SYN-ACK, URG-PSH, other combinations of TCP Flags, slow TCP attacks)
    - Application Attacks (HTTP GET/POST Floods, slow HTTP Attacks, SIP Invite Floods, DNS Attacks, HTTPS Protocol Attacks)
    - SSL/TLS Attacks (Malformed SSL Floods, SSL Renegotiation, SSL Session Floods)
    - DNS Cache Poisoning
    - Reflection Amplification Flood Attacks (TCP, UDP, ICMP, DNS, mDNS, SSDP, NTP, NetBIOS, RIPv1, rpcbind, SNMP, SQL RS, Chargen, L2TP, Microsoft SQL Resolution Service)
    - Fragmentation Attacks (Teardrop, Targa3, Jolt2, Nestea)
    - Vulnerability Attacks
    - Resource Exhaustion Attacks (Slowloris, Pyloris, LOIC, etc.)
    - Flash Crowd Protection
    - Attacks on Gaming Protocols.


Schutz vor DDoS-Angriffen - On-Site Robustheitsmaßnahmen
--------------------------------------------------------

.. class:: incremental

- Aufrüsten der Ressourcen (z.B. Bandbreite, CPU, RAM, ...) 
- Exemplarische Sofortmaßnahmen bei aktivem Angriff: 
  
  .. class:: incremental smaller

  - Whitelisting von IP-Adressen von besonders wichtigen Clients
  - Blacklisting von IP-Adressen aus bestimmten Bereichen
  - Captchas
  - Überprüfung der Browser-Echtheit
  
- Anti-DDos Appliances 

.. admonition:: Achtung
    :class: warning incremental

    Diese Maßnahmen sind häufig teuer und ggf. begrenzt effektiv; wenn der Angriff die verfügbare Brandbreite übersteigt, sind diese Maßnahmen wirkungslos.



Schutz vor DDoS-Angriffen - Off-Site Robustheitsmaßnahmen
------------------------------------------------------------

.. class:: incremental
  
- Einbinden des ISP
- Einbinden spezialisierter Dienstleister (im Angriffsfall wird mittels BGP-Rerouting der Traffic an den Dienstleister umgeleitet, der dann die DDos Attacke filtert.)
- Content-Delivery-Networks (CDNs) für statische Inhalte (z.B. Cloudflare, Akamai, ...)
- Distributed Clouds


Password Sniffing
---------------------

:In der Anfangszeit: unverschlüsselte Übertragung von Passwörtern (telnet, ftp, ...)
:In der Übergangszeit (bzw. in bestimmten Szenarien auch heute): Verwendung von Einmal-Passwörtern (S/Key, ...)
:Heute: Passwörter werden verschlüsselt übertragen (ssh, https, ...)


.. container:: supplemental

    Unverschlüsselte Passworte können leicht mittels eines Sniffers, der den Netzwerkverkehr mitschneidet (z.B. Wireshark), abgefangen werden.


Einmal-Passwörter
----------------------

Die Idee ist, dass Passwörter nur genau einmal gültig sind und nicht wiederverwendbar sind.

- Tokens (z.B. RSA SecurID)
- Codebuch: Liste von Einmal-Passwörtern, die das gemeinsame Geheimnis sind.
- S/Key: Passwort wird mit einem Zähler kombiniert und dann gehasht.


Das S/Key Verfahren 
------------------------------

.. admonition:: Prinzip

    Einmal-Passwort-System nach Codebuch-Verfahren, dass im Original auf der kryptographischen Hashfunktion MD4 basiert.

.. container:: incremental stack scriptsize

    .. container:: layer

        **Initialisierung**

        .. class:: incremental

        1) Der Nutzer gibt sein Passwort W ein; dies ist der geheime Schlüssel. (Sollte W bekannt werden, dann ist die Sicherheit des Verfahrens nicht mehr gewährleistet.)
        2) Eine kryptografische Hash-Funktion H wird n-mal auf W angewandt, wodurch eine Hash-Kette von n einmaligen Passwörtern entsteht. :math:`H(W), H(H(W)), \dots, H^{n}(W)`
        3) Das initiale Passwort wird verworfen.
        4) Der Benutzer erhält die n Passwörter, die in umgekehrter Reihenfolge ausgedruckt werden: :math:`H^n(W), H^{n-1}(W), ..., H(H(W)), H(W)`.
        5) Nur das Passwort :math:`H^n(W)`, das an erster Stelle der Liste des Benutzers steht, der Wert von :math:`n` und ggf. ein Salt, wird auf dem Server gespeichert.

    .. container:: layer incremental

        **Anmeldung**

        Identifiziere :math:`n` das letzte verwendete Passwort.

        .. class:: incremental
       
        - Der Server fragt den Nutzer nach dem Passwort :math:`n-1` (d.h. :math:`H^{n-1}(W)`) und übermittelt ggf. auch den Salt. 
        - Der Server hasht das Passwort und vergleicht es mit dem gespeicherten Passwort :math:`H^n(W)`.
        - Ist das Passwort korrekt, dann wird der Nutzer angemeldet und der Server speichert das Passwort :math:`H^{n-1}(W)` als neues Passwort :math:`H^n(W)` und dekrementiert n.

.. container:: supplemental

    Intern verwendet S/KEY 64-bit Zahlen. Für die Benutzbarkeit werden diese Zahlen auf sechs kurze Wörter, von ein bis vier Zeichen, aus einem öffentlich zugänglichen 2048-Wörter-Wörterbuch (:math:`2048 = 2^{11}`) abgebildet. Zum Beispiel wird eine 64-Bit-Zahl auf "ROY HURT SKI FAIL GRIM KNEE" abgebildet. 


`Secure Shell (SSH) <https://datatracker.ietf.org/doc/html/rfc4254>`__
----------------------------------------------------------------------------

**Verschlüsselte Verbindung**

SSH ermöglicht die sichere Fernanmeldung von einem Computer bei einem anderen (typischerweise über TCP über Port 22). Es bietet mehrere Optionen für eine starke Authentifizierung und schützt die Sicherheit und Integrität der Kommunikation durch starke Verschlüsselung

.. container:: incremental

    **Ablauf**

    (1) Authentisierung des Server-Rechners
    (2) Authentisierung des Benutzers (bzw. des Clients) mittels

        a. Passwort
        b. :obsolete:`.rhosts-Eintrag`
        c. privatem :minor:`(RSA-)`\ Key (hauptsächlich verwendete Methode)

    (3) Kommunikation über symmetrisch verschlüsselte Verbindung


.. container:: supplemental

    Die Authentifizierung mittels eines Schlüsselpaars dient primäre der Automatisierung (dann wird auch keine :ger-quote:`Schlüsselphrase` zum Schutz des Passworts verwendet). Auf jeden Fall ist effektives Schlüsselmanagement erforderlich:

    .. epigraph::

        [...] In einigen Fällen haben wir mehrere Millionen SSH-Schlüssel gefunden, die den Zugang zu Produktionsservern in Kundenumgebungen autorisieren, wobei 90 % der Schlüssel tatsächlich ungenutzt sind und für einen Zugang stehen, der zwar bereitgestellt, aber nie gekündigt wurde.

        -- `SSH.com (Dez. 2023) <https://www.ssh.com/academy/ssh/protocol>`__
                                                                                                                                                                

Secure Shell (SSH) - Protokoll
--------------------------------------


.. image:: ssh/initiation.svg 
    :alt: SSH Protokoll
    :align: center
    :width: 1850px

.. container:: incremental small

    Beide Seiten haben einen Public-private Key Schlüsselpaar zur Gegenseitigen Authentifizierung

    :User Keys: 
     - ``Authorized keys`` - Datei mit den öffentlichen Schlüsseln der Nutzer, gespeichert auf Serverseite
     - ``Identity keys`` private Schlüssel der Nutzer

    :Host keys: benötigt für die Authentifizierung von Servern, um Man-in-the-Middle-Angriffe zu verhindern.

    :Session Keys: werden für die symmetrische Verschlüsselung der Daten in einer Verbindung verwendet. Session Keys (:ger:`Sitzungsschlüssel`) werden während des  Verbindungsaufbaus ausgehandelt.

.. container:: supplemental 

    Im Falle von SSH gibt es kein initiales Vertrauen zwischen Server und Client.


Secure Shell (SSH) - Risiken durch mangelnde Schlüsselverwaltung
------------------------------------------------------------------

.. class:: incremental

- Schlüssel werden nicht regelmäßig ausgetauscht
- Schlüssel werden nicht gelöscht, wenn sie nicht mehr benötigt werden
- viele (die meisten) Schlüssel werden nicht verwendet
- Es ist of nicht bekannt, wer Zugriff auf welche Schlüssel hat(te)
- Es ist nicht bekannt, welche Schlüssel auf welche Systeme Zugriff haben
- Malware kann SSH-Schlüssel stehlen
- SSH Keys können ggf. privilegierten Zugriff gewähren
- SSH Keys können benutzt werden, wenn um Backdoors zu verstecken 
- Server keys erlauben ggf. Man-in-the-Middle-Angriffe
   
    
SSH Tunneling
-----------------------

- ermöglicht die Übertragung beliebiger Netzwerkdaten über eine verschlüsselte SSH-Verbindung. Z.B. 

  - um ältere Anwendungen zu verschlüsseln. 
  - um VPNs (Virtual Private Networks) zu implementieren 
  - um über Firewalls hinweg auf Intranetdienste zuzugreifen.

- ermöglicht auch Port-forwarding (lokale Ports werden auf entfernten Rechner weitergeleitet)

.. image:: ssh/tunneling.svg 
    :alt: SSH Protokoll
    :align: center
    :width: 1450px


SSH und :ger-quote:`Back-tunneling`
--------------------------------------

.. class:: incremental

- Der Angreifer richtet einen Server außerhalb des Zielnetzwerks ein
- Nach Infiltration des Zielsystems verbindet der Angreifer sich von innen mit dem externen SSH-Server.  
- Diese SSH-Verbindung wird so eingerichtet, das eine TCP-Port-Weiterleitung von einem Port auf dem externen Server zu einem SSH-Port auf einem Server im internen Netzwerk möglich ist. 
- Die meisten Firewalls bieten wenig bis gar keinen Schutz dagegen.


.. container:: supplemental

    Es ist in diesem Fall besonders interessant für den Angreifer den SSH Server zum Beispiel bei einem Cloud-Anbieter zu betreiben, welcher von dem Unternehmen  standardmäßig verwendet wird (am Anfang steht immer die Aufklärung!). In diesem Fall wird die Firewall keine ausgehenden SSH-Verbindungen dorthin blockieren.


.. class:: integrated-exercise transition-move-left


Übung: Port Scans - IDLE Scan
------------------------------

- Warum kann mit einem IDLE Scan nicht festgestellt werden warum ein Port geschlossen oder gefiltert ist?
- Welchen Wert hat die IP ID des Zombies, der einem IDLE Scan durchführt, wenn der Zielport offen bzw. geschlossen ist wenn der Scanner diesen wieder abfragt?

.. Lösung:
   - Wenn der Port geschlossen ist, dann sendet der Zielrechner ein RST Paket an den Zombie. Dieses wird vom Zombie ignoriert. Daher erhöht sich die IP ID des Zombies nicht.
   - Wenn der Port offen ist, dann sendet der Zielrechner ein SYN/ACK Paket an den Zombie. Dieser antwortet mit einem RST Paket und erhöht seine IP ID um 1. D.h. der Wert der IP ID des Zombies ist um 2 höher wenn der Port offen ist und "nur" eins höher sonst.


.. class:: integrated-exercise transition-move-left

Übung: S/Key
--------------

1. Welche Vorteile bieten Einmalpasswortsysteme gegenüber Systemen mit mehrfach zu verwendenden Passworten?
2. Welchen Angriffen sind Einmalpasswortsysteme weiterhin ausgesetzt?
3. Generieren Sie eine Liste von Einmalpassworten mit Initialwert r = 769. Generieren Sie h(r) bis h6(r) wenn die Einwegfunktion hier der Einfachheit halber :math:`h(x) = x^2\; mod\; 1000` ist.
4. Wie oft kann sich der Benutzer anmelden? Wie sieht seine Liste aus?
5. Welchen Wert speichert der Server vor dem jeweiligen Anmeldevorgang?
6. Spielen Sie zwei Anmeldevorgänge durch.
7. Wenn ein Passwort :math:`H^L(W), 1 < L < N` bekannt ist, welche Auswirkungen hat dies auf die Sicherheit des Verfahrens?

.. Lösung:
   1. Schutz gegen Lauscher
   2. Man-in-the-middle
   3. Der Benutzer wählt eine Zufallszahl r, hier r = 769. Berechnet wird nun:
   769^2 mod 1000 = 361 
   361^2 mod 1000 = 321 
   321^2 mod 1000 = 41 
   41^2 mod 1000 = 681 
   681^2 mod 1000 = 761 
   761^2 mod 1000 = 121
   1. Fünfmal. Der Benutzer erhält folgende Passwortliste: 761, 681, 41, 321, 361
   2. Der Server speichert: 121
   3. Beim ersten Anmeldevorgang verwendet der Benutzer das erste Passwort auf der Liste, die 761.
   Der Server berechnet nun 7612 mod 1000 = 121 und vergleicht dies mit dem gespeicherten Wert. Da diese übereinstimmen, wird der Benutzer angemeldet.
   Der Server speichert jetzt die 761, und der Benutzer streicht die 761 von der Liste, usw.
   1. Keine


.. class:: integrated-exercise transition-move-left

Übung: DDoS
--------------

1.  Welches Problem entsteht wenn zum Schutze vor Angriffen auf die Verfügbarkeit die Ressourcen von IT-Systemen und deren Internet-Anbindung erhöht werden?
2. Recherchieren Sie was ein "Low and Slow Angriff" ist.
3. Wo kann überall "Egress filtering" statt finden.

.. Lösung:
   1. Ressourceverschwendung wenn gerade kein Angriff stattfindet. Wenn der Angriff stattfindet, dann ist es immer noch möglich bzw. sogar wahrscheinlich, dass die Ressourcen nicht ausreichen.
   2. Was ist ein Low-and-Slow-Angriff?
   (https://www.cloudflare.com/de-de/learning/ddos/ddos-low-and-slow-attack/)
   Ein Low-and-Slow-Angriff ist eine Art von DoS- oder DDoS-Angriff, der sich auf einen kleinen Strom sehr langsamen Traffics stützt, der auf Anwendungs- oder Serverressourcen abzielt. Im Gegensatz zu herkömmlichen Brute-Force-Angriffen benötigen Low-and-Slow-Angriffe nur sehr wenig Bandbreite und können schwer bekämpft werden, da sie Traffic erzeugen, der nur sehr schwer von normalem Traffic zu unterscheiden ist. Während groß angelegte DDoS-Angriffe wahrscheinlich schnell bemerkt werden, können Low-and-Slow-Attacken über lange Zeiträume unentdeckt bleiben, während der Dienst für echte Nutzer verweigert oder verlangsamt wird.
   Da sie nicht viele Ressourcen benötigen, können Low-and-Slow-Angriffe von einem einzigen Computer aus erfolgreich durchgeführt werden, im Gegensatz zu verteilten Angriffen, für die ein Botnet erforderlich sein kann. Zwei der beliebtesten Tools für Low-and-Slow-Angriffe heißen Slowloris und R.U.D.Y.
   3. Dies kann zum Beispiel auf Seiten eines ISPs geschehen.


.. class:: new-section transition-fade

Firewalls
------------

Unabhängiges Netz - :ger-quote:`Ideale Situation` 
---------------------------------------------------

.. image:: firewalls/all-save.png
    :alt: Ideale Situation
    :align: center

:Vorteile: 
    
    - keinerlei Angriffsmöglichkeiten von außen 

:Nachteile:

    - kein Schutz gegen Insider
    - kein Zugang zum Internet



Schutzschicht zwischen internem und externem Netz
------------------------------------------------------

.. image:: firewalls/firewall.png
    :alt: Schutzschicht zwischen internem und externem Netz
    :align: center

- Kontrolle des Nachrichtenverkehrs durch Filterung 
- begrenzte Isolation mit begrenztem Schutz

.. container:: supplemental

    Eine Firewall schafft zwischen verbundenen Netzen Sicherheitsdomänen mit unterschiedlichem Schutzbedarf. Eine wichtige Teilaufgabe ist das Ausarbeiten von Sicherheitsrichtlinien.


Realisierung von Virtual Private Networks (VPN)
------------------------------------------------------

.. image:: firewalls/vpn.png
    :alt: Realisierung von Virtual Private Networks (VPN)
    :align: center

- Aufbau einer scheinbar privaten Verbindung von Firmenteilnetzen über das (öffentliche) Internet
- Zusätzliche Verbindungsverschlüsselung zwischen den Firewalls.

.. container:: supplemental

    Ziel ist es aktive und passive Angriffe zu unterbinden. (Selbst bei verschlüsselten Verbindungen kann die Verkehrflussanalyse noch Informationen liefern über die Verbindungen liefern.)


Kommerzielle VPNs für Endnutzer
---------------------------------

.. image:: firewalls/vpn-commercial.svg
    :alt: Einsatz von Virtual Private Networks (VPN) für Privatnutzer
    :align: center
    :width: 1400px

.. container:: supplemental

    **Motivation**

    - Schutz der Privatsphäre; der ISP kennt nicht mehr die Webseiten, die man aufruft
    - Die IP-Adresse des Nutzers ist den aufgerufenen Webseiten nicht mehr bekannt und kann deswegen der Umgehung von Geo-Blocking dienen.

    **Nachteile?**

    - Vertrauen in den VPN-Anbieter muss vorhanden sein. Insbesondere, beim Einsatz zum Stärken der Privatsphäre, muss der VPN-Anbieter vertrauenswürdig sein und sollte ein so genannter "no-log" Anbieter sein. Es gibt auch (kostenlose) VPN-Anbieter, die die Daten der Nutzer verkaufen (`Facebook Onavo <https://techcrunch.com/2019/02/21/facebook-removes-onavo/?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAAGVIppEgEOd9Z0FoMbmk2TCleRmD9wCMWDmIzGYEjIo1c7Cmz8NpiSoibthFG5IZQzmZ-kiJq-5Wj1bj21byh7YUrC_aSJJk1Bapwz80GSgzLFS-LHCF2OOetUYLSKwEG7W75znuqJJBJcNTTbtJ1UGB95Yu90saK9aIIkEywcRq>`__).





Schutz auf den Schichten des TCP/IP Stacks
-----------------------------------------------------------

Zentraler Schutz des gesamten internen Netzwerks durch:

.. class:: incremental

- Paket Filter (:eng:`Packet Filtering`) 

  - Blockieren bestimmter IP-Empfänger-Adressen (extern / intern)
  - Blockieren bestimmter IP-Absender-Adressen (extern / intern)
    
    :minor:`(Z.B. aus dem Internet mit internen IP-Absender-Adressen.)`
  
  - Blockieren bestimmter Dienste; ggf. nur für bestimmte IP-Adressen

- Filter auf Anwendungsebene (:eng:`Application-level Filtering`)

  - inhaltsbezogene Filterung der Verkehrsdaten eines Dienstes
  - z.B. Virenfilter
  - wirkungslos bei verschlüsselten Verkehrsdaten

- Protokollierungsmöglichkeit der Kommunikation von / nach extern


.. container:: supplemental

    Firewalls (alleine) können die Struktur des Netzwerks nicht verbergen.



Realisierungsmöglichkeiten von Firewalls
------------------------------------------------

.. class:: incremental

- Hardware-Firewall
 
  - Screening Router
  - Application Gateway (auch Bastion Host)
  
    - Proxy-Server für bestimmte Dienste
    - Client-Software (HTTP-Browser, telnet, ftp, ...) 
    - Server-Software 

- Software-Firewall (Personal Firewall)


.. container:: supplemental

    Im Falle eines :eng:`Bastion Host`, ist dies der einzige unmittelbar erreichbare Rechner.


.. .. class:: vertical-title

Dual-Homed Host
----------------

.. image:: firewalls/dual-homed-host.png 
    :alt: Dual-Homed Host
    :align: right
    :class: border-transparent-1em

**Aufbau**

- zwei Netzwerkkarten: ggf. private interne Adressen
- Screening Router & Gate: Packet Filter und Application-Level Filter
- Proxy-Dienste installieren 
- Benutzer-Logins von extern
- Konf. der Netzwerkkarten: IP-Pakete nicht automat. Weiterleiten


Screening Router
---------------------

.. image:: firewalls/screening-router.png 
    :alt: Screening Router
    :align: right
    :class: border-transparent-1em 

**Aufbau**

- programmierbarer HW-Router 
- simple Filterfunktionen:

  - nur Paket-Header prüfen  
  - schnelle Auswertung ermöglicht hohen Durchsatz

- Realisierung eines Packet Filters

**Bewertung**

.. container:: two-columns no-default-width

    .. container:: column
        
        .. class:: positive-list

        - einfach und billig
        - flexibel
        
    .. container:: column

        .. class:: negative-list

        - schwer zu testen
        - Protokollierung
        - Fernwartung 
        - keine Inhaltsfilterung 


Screened Host
-----------------

.. image:: firewalls/screened-host.png 
    :alt: Screened Host
    :align: right
    :class: border-transparent-1em

**Aufbau**

- Screening Router blockiert:

  - Pakete von / an interne Rechner (nicht Gate)
  - Source-Routed Pakete

- von extern nur Gate sichtbar 
- Pakete von intern nur via Gate
- Gate bietet Proxy-Server (z.B. für E-Mail)


.. container:: supplemental

    Gibt es für eine bestimmte Anwendung kein Application-level Proxy, dann kann auf einen für TCP/UDP generischen Proxy zurückgegriffen werden. Dieser arbeitet auf dem Session Layer und kann nur die Header-Informationen auswerten. Es handelt sich dann um ein :eng:`Circuit-level Proxy/Gateway`. Im Vergleich zu einem Application-level Proxy ist die Sicherheit geringer, da der Circuit-level Proxy nicht in der Lage ist, die Daten zu interpretieren.

    Ein allgemeines Problem ist, dass viele Anwendungen auf generische Protokolle wie HTTP aufsetzen. Weiterhin betreiben einige Anwendungen :ger-quote:`Port Hopping`, d.h. sie wechseln den Port wenn der Standardport nicht offen ist.

    Eine Anforderung an :ger-quote:`Next-generation Firewalls` ist, dass diese die Analyse von den Daten einer Anwendung unabhängig vom Port und Protokoll ermöglichen.


Konfiguration eines Gateways
---------------------------------

Das Ziel der Konfiguration muss eine minimale angreifbare Oberfläche sein.

.. class:: incremental

- Abschalten aller nicht-benötigten Netzdienste
- Löschen aller nicht benötigter Programme
- Rechte von /bin/sh auf 500 setzen
- Rechte aller Systemverzeichnisse auf 711 setzen
- keine regulären Benutzerkennungen
- root-Login mit Einmal-Passwortsystem
- setzen von Platten- und Prozess-Quotas
- volle Protokollierung, möglichst auf Hardcopy-Gerät
- möglichst sichere, stabile und regelmäßig aktualisierte Betriebssystemversion einsetzen


Screened Subnet
----------------

.. image:: firewalls/screened-subnet.png 
    :alt: Screened Subnet
    :align: right


**Aufbau**

.. class:: incremental

- interner Screening Router als dritter Schutzwall

  - blockiert Dienste, die nicht einmal bis zum Gate gelangen sollen
  - lässt nur Pakete zum / vom Gate durch

- äußeres Netz realisiert Demilitarisierte Zone (DMZ) für HTTP-Server, Mail-Server, ...


Intrusion Detection Systeme (IDS)
--------------------------------------

.. admonition:: Definition

    Ein IDS ist ein Gerät (meist ein speziell konfigurierter Rechner), das vielfältige Techniken zur Erkennung von Angriffen anwendet und Angriffe meldet und ggf. abwehrt, in dem (z.B.) die Firewall automatisch umkonfiguriert wird.

.. container:: incremental

    **Motivation**

    .. class:: incremental

    - Firewalls alleine sind zu statisch und deswegen häufig nicht ausreichend
    - bessere Aufzeichnung und flexiblere Erkennung notwendig 
    - angepasste Reaktion notwendig

.. container:: incremental

    **Umsetzung**

    An verschiedenen, neuralgischen Stellen werden spezielle Sensoren platziert, die (hier) den Netzwerkverkehr überwachen und verdächtige Aktivitäten melden.

.. container:: supplemental

    Miteinander verwandt bzw. typischerweise in einem Produkt zu finden:

    - Intrusion Detection (IDS) 
    - Intrusion Response (IRS)
    - Intrusion Prevention (IPS)



IDS-Erkennungstechniken
----------------------------

- Signaturerkennung
- statistische Analyse
- Anomalieerkennung


.. admonition:: Probleme
    :class: incremental

    - Fälschlicherweise gemeldete Angriffe (false positives) 
    - nicht gemeldete Angriffe (false negatives) (insb. bei neuartigen Angriffen)
    - Echtzeitanforderung, insb. bei Hochgeschwindigkeitsnetzen 
    - Aufzeichnung bei Netzwerken mit Switches ( ⇒ spez. SPAN Port)
    - Sensoren sollen unbeobachtbar sein (stealth)


.. class:: integrated-exercise transition-move-left

Übung: Firewalls
------------------

1. Was sind Vorteile eines Dual Homed Host gegenüber einem Paketfilter? Was sind die Nachteile?

2. Benennen Sie die zwei konzeptionelle Grenzen von Firewalls. D.h. zwei Szenarien gegen die Firewalls nicht schützen können.

3. Für welche der folgenden Cybersicherheitsstrategien können Firewalls eingesetzt werden:
   
   1. Angriffe vermeiden
   2. Angriffe erkennen
   3. Angriffe abwehren/Angriffen entgegenwirken
   4. Reaktion auf Angriffe

4. Sie werden beauftragt die Firewall so einzurichten, dass Mails mit Schadsoftware nicht durchgelassen werden. Wie reagieren Sie?

.. Lösung:
    1.
    Ein Dual Homed Host ist ein Computer mit zwei Netzwerkschnittstellen. Zur Verwendung als Firewall wird das Routing, also die Weiterleitung von IP-Paketen zwischen den Schnittstellen, abgeschaltet. Damit können keine Pakete direkt zwischen den Netzen ausgetauscht werden und alle Verbindungen enden am Dual Homed Host. Um Daten weiterzuleiten, muss auf dem Dual Homed Host ein Proxy laufen, der eine Verbindung annimmt und eine neue Verbindung in das andere Netz aufbaut (gesteuert über Regel- und Berechtigungstabellen). Man kann über diese Application Level Gateways eine gute inhaltliche Kontrolle der übertragenen Daten durchführen, bei E-Mail beispielsweise eine Längenbegrenzung oder eine Erkennung von mitgeschickten ausführbaren Programmen, die dann automatisch geprüft oder entfernt werden könnten. Für jeden freigeschalteten Dienst benötigt man einen speziellen Proxy.
    Ein Risiko bei Dual Homed Hosts ist die Übernahme des Hosts durch einen Angreifer. Dieser hat dann über die entsprechende Netzwerkschnittstelle des Dual Homed Hosts vollständigen Zugriff auf das interne Netz.
    2.
    - Hintertüren - sollte es Kommunikationsübergänge an der Firewall vorbei geben,  so können diese von Angreifern genutzt werden.
    - Interne Angriffe - diesbezüglich gibt es keine Unterschiede zu einem Netzwerk ohne Firewall.
    - Vertrauenswürdigkeit der Kommunikationspartner
    3. Die Hauptaufgabe von Firewalls ist es Angriffen entgegenzuwirken; (3.) Eine Reaktion auf Angriffe ist für klassische Firewalls nicht möglich. Eine Reaktion auf Angriffe ist Aufgabe von Intrusion Detection Systemen. Moderne Firewalls integrieren jedoch häufig auch Funktionen von Intrusion Detection Systemen.
    4. ... die Mails sollen ja den Mailserver erreichen; eine inhaltsbasierte Beurteilung des Inhalts einer Mail ist nicht Aufgabe einer Firewall. 




Tor (The Onion Router)
---------------------------

.. class:: incremental

- Anwendungsunabhängiger **low-latency Anonymisierungsdienst für TCP-Verbindungen**, der den Standort und die IP des Nutzers verschleiert
- Typische Anwendung: anonymes Surfen im Internet und Instant Messaging (z.B. Briar)
- Frei und Open Source
- gegründet 2002, öffentlich nutzbar seit 2003, Code seit 2004 frei verfügbar
- Baut ein *Overlay-Netzwerk* auf
- Grundlegendes Prinzip: Onion Routing

.. container:: supplemental

    :low-latency: Die Verzögerung durch die Anonymisierung ist so gering, dass Tor für Instant Messaging und das Surfen im Internet verwendet werden kann.

    :Overlay-Netzwerk: Tor baut ein eigenes Netzwerk auf, welches auf dem Internet aufsetzt. Die Verbindungen zwischen den Tor-Knoten werden von Tor zusätzlich verschlüsselt. 



Tor - Verwendung und Sicherheit
--------------------------------

.. class:: incremental

- legale/intendierte Nutzungen: Nutzer mit allg. Datenschutzbedürfnissen, *Whistleblowers*, Dissidenten, Journalisten, ...
- illegale Zwecke (Darknet). 

  .. container:: smaller minor

    Es wird geschätzt, dass etwa 80% des Datenverkehrs im Zusammenhang mit dem Zugriff auf Kinderpornografie steht. Solche Schätzungen sind allerdings mit Vorsicht zu genießen!

- Mehrere Sicherheitslücken wurden in der Vergangenheit gefunden und geschlossen. Die Angriffe [#]_ waren:
  
  - :minor:`DoS Attacken`
  - Deanonymisierungsattacken
  - Identifikation von *Onion Services*

.. [#] `Aufstellung von Angriffen auf Tor <https://github.com/Attacks-on-Tor/Attacks-on-Tor#correlation-attacks>`__.


.. container:: supplemental

    .. epigraph::

        [Sicherheitslücke gefunden in 2013] Wenn ein einzelner Nutzer Tor über einen längeren Zeitraum [3 bis 6-Monate, abhängig von einigen Faktoren] regelmäßig nutzt, ist es fast sicher, dass er *de-anonymisiert* werden kann.
        
        [Übersetzt mit DeepL.]

        -- https://www.infosecurity-magazine.com/news/tor-is-not-as-safe-as-you-may-think/


    Surface web vs. Deep web vs. Dark web


Tor - Hintergrund
------------------

.. class:: incremental

- Die grundlegende Idee ist es eine Trennung zwischen der Quelle und dem Ziel des Datenverkehrs zu schaffen.
- Der Datenverkehr wird über *mehrere Knoten* (*Relays*) umgeleitet, die jeweils nur den vorherigen und den nächsten Knoten kennen. Der Weg den ein Datenpaket nimmt, wird als *Circuit* oder *Path* bezeichnet.
- Der Pfad wird dazu vorher ausgewählt und der gesamte Datenverkehr entsprechend des Pfades verschlüsselt.
- Tor bietet Anonymität auch für die Serverseite durch *Onion Services* (auch *Hidden Services*), die nur über eine von Tor vergebene Onion-Adresse erreicht werden können.


Tor - Bedrohungsmodel
----------------------

Tor bietet Schutz for folgenden Angreifern: Einem Angreifer dem es gelingt ...

- einen Teil der Kommunikation zu beobachten und 
- nur einen Teil der Tor-Knoten zu kontrollieren, indem er entweder einen eigenen Tor-Knoten (*Relay*\ ; früher *Onion-Router*) betreibt oder einen bereits laufenden Knoten kompromittiert.

.. admonition:: Warnung
   :class: incremental margin-top-2em

   Folgendes Szenario ist nicht abgedeckt: Ein Angreifer, der beide Enden der Kommunikation, den ``Entry Guard`` und den ``Exit Node`` überwachen kann.
   
   Gegen solche Angreifer bietet Tor keine Anonymität.



Tor - Aufbau
----------------

.. container:: stack

    .. container:: layer

        .. image:: tor/tor-1.svg
            :alt: Tor Network
            :align: center

    .. container:: layer incremental overlay

        .. image:: tor/tor-directory-authority-2.svg
            :alt: Tor Directory Authority
            :align: center

    .. container:: layer incremental overlay

        .. image:: tor/tor-bridge-3.svg
            :alt: Tor Bridge
            :align: center


.. container:: supplemental

    :Tor-Knoten: Rechner, die das Tor-Netzwerk bilden. Es gibt drei Arten von Tor-Knoten:

      - *Entry Nodes* (auch *Guard Nodes*): Diese Knoten sind die ersten Knoten in der Kette. Sie kennen die IP-Adresse des Clients. Sie können den Datenverkehr nicht entschlüsseln. Sie können aber sehen, dass der Datenverkehr von einem bestimmten Client kommt. 
      - *Middle Nodes*: Diese Knoten sind die mittleren Knoten in der Kette. Sie kennen weder die IP-Adresse des Clients noch die IP-Adresse des Ziels. Sie können den Datenverkehr nicht entschlüsseln. Sie können aber sehen, dass der Datenverkehr von einem bestimmten Entry Node kommt und an einen bestimmten Exit Node geht. 
      - *Exit Nodes*: Diese Knoten sind die letzten Knoten in der Kette. Sie kennen die IP-Adresse des Ziels. Sie können den Datenverkehr entschlüsseln. Sie können aber nicht sehen, von welchem Entry Node der Datenverkehr kommt. 
      - *Bridge Nodes*: Diese Knoten sind *Entry Nodes*, die nicht bzw. nicht vollständig öffentlich bekannt. Diese dienen ggf. dazu in Ländern, in denen Tor blockiert wird, den Zugang zu Tor zu ermöglichen. Sollte eine Verbindung zu einer Bridge nicht hergestellt werden können, aufgrund der Struktur der Nachrichten - zum Beispiel aufgrund der Verwendung von *Deep Packet Inspection* - dann ist es möglich diese mit Hilfe von *Pluggable Transports* zu verschleiern. 

    :Tor-Netzwerk: besteht aus mehreren tausend Tor-Knoten. Viele Knoten sind freiwillig betriebene Knoten. 

    :Circuit/Path: Ein Circuit besteht typischerweise aus drei Knoten: *Entry Node*, *Middle Node* und *Exit Node*. Mehr Knoten sind möglich, haben jedoch nur einen geringen Einfluss auf die Sicherheit. Die Übertragung der Daten zwischen diesen Knoten erfolgt verschlüsselt. In welcher Form die Daten vom *Exit Node* zum Ziel übertragen werden, ist nicht Teil von Tor. Hat der Client eine verschlüsselte Verbindung initiiert (HTTPS), dann ist auch der Datenverkehr zwischen dem Exit Node und dem Ziel (noch) verschlüsselt ansonsten nicht und der Exit Node kann den Datenverkehr lesen.

    :Directory Authority: 
    
        Knoten, die die Liste der aktiven Tor-Knoten verwalten. Diese Liste wird von allen Tor-Knoten regelmäßig in Hinblick auf das *Consensus Document* bzgl. der Knoten und deren Eigenschaften sowie Zustand abgefragt. Das *Consensus Document* wird von den *Directory Authorities* einmal pro Stunde gemeinsam erstellt und beschreibt die relevanten Eigenschaften jedes Tor-Knotens. Die Authentizität des *Consensus Document* wird durch die Signaturen der *Directory Authorities* nachgewiesen.
    
        Es gibt (Stand 2023) 9 *Directory Authorities*. 

    `Spezifikation <https://spec.torproject.org>`__


    .. admonition:: Hinweis
    
        In älteren Dokumenten wird der *Client* auch als *Onion Proxy (OP)* bezeichnet und die Tor-Knoten als *Onion Router (OR)*. Die Tor-Knoten (:eng:`Nodes`) werden auch als *Onion Relay* bezeichnet.


Onion Routing
---------------

.. image:: tor/tor-onion-routing.svg
    :alt: Tor Onion Routing
    :align: left
    :width: 1600px


.. container:: supplemental

    :`Onion Routing`:eng:: bedeutet, dass die Datenpakete mehrfach verschlüsselt werden. Jeder Tor-Knoten kann nur die Verschlüsselungsschicht entfernen, für die er den Schlüssel hat. Die Schlüssel werden mit dem Client während des Aufbaus des Circuits ausgehandelt. Es gibt für jeden Tor-Knoten einen eigenen Schlüssel und die Nachrichten werden in umgekehrter Reihenfolge der Tor-Knoten entlang des Pfades verschlüsselt. D.h. die Verschlüsselung für den Entry Node wird als letztes angewendet, da diese als erstes entfernt wird.

    :Cells: sind die Datenpakete, die zwischen den Tor-Knoten ausgetauscht werden. Cells sind immer 512Byte groß, um es unmöglich zu machen anhand der Größe der Datenpakete Rückschlüsse auf die Daten zu ziehen.



.. class:: vertical-title tiny

Initiierung eines Circuits (konzeptionell)
--------------------------------------------

.. image:: tor/tor-circuit-creation.svg
    :alt: Initiierung eines Circuits
    :align: center
    :width: 1800px

.. container:: supplemental

    Jeder Tor-Knoten verfügt über mehrere Keys. Für den Aufbau der Verbindung werden die *Onion Keys* verwendet. Mit Hilfe dieser werden die initialen Datenpakete mittels Public-Key Kryptografie verschlüsselt. Dies wird benötigt, um den AES Key - einer pro Knoten - der für den eigentlichen Versand benötigt wird, auszuhandeln und sicher zu übertragen.

    In der Grafik wird der Aufbau eines Circuits mit zwei Tor-Knoten dargestellt. Der Client kennt die Onion Keys der Tor-Knoten (``OR1`` und ``OR2``). Die Onion Keys werden verwendet, um die *Create* Zelle zu verschlüsseln. Der Entry Node verwendet diese Onion Keys um die *Create* Zelle zu entschlüsseln und den gemeinsamen Schlüssel zu erzeugen. Um einen längeren Pfad aufzubauen, muss der Client ggf. einfach mehrere ``Extend`` Nachrichten versenden. Erhält ein Knoten eine Relay Nachricht, dann kann der Knoten diese mit dem mit ihm ausgehandelten AES Key entschlüsseln und die Nachricht weiterleiten. Er kann den Inhalt (zum Beispiel eine weitere Relay Nachricht oder eine Extend Nachricht) nicht lesen.
   
   




Tor Relays in Deutschland
----------------------------

.. image:: tor-metrics-relays.png
   :alt: Tor Relays gelistet von Tor Metrics Jan. 2024
   :align: center
   :height: 1050px

.. container:: supplemental

    **Flags**

    Beschreibung jedes Tor-Knotens in Hinblick auf die Rolle des Knotens im Tor-Netzwerk. Zum Beispiel: kann der Knoten als Entry Node verwendet werden? Ist der Knoten schnell genug um als Exit Node verwendet zu werden? 

    Auszug wichtiger *Flags*:

    :HSDir: Ein Router ist ein *v2 Hidden Service Directory*
    :Running: Eine Authority konnte sich innerhalb der letzten 45 Minuten mit dem Router verbinden.
    :Stable: die gewichtet Zeit zwischen zwei Fehlern (*weighted MTBF*) ist größer als 7 Tage oder größer als der Median aller aktiven Router. 
    :Valid: eine Version von Tor wird ausgeführt, die von den Authorities als aktuell angesehen wird und keine bekannten Schwachstellen aufweist.

Informationen über Tor Relays
-------------------------------

.. container:: two-columns

    .. container:: column

        .. image:: tor-relay-snorlax.png
            :alt: Tor Relay Snorlax
            :align: left
            :width: 900px

    .. container:: column incremental smaller margin-left-1em

        .. container:: stack

            .. container:: layer

              - Viele Tor Relays werden von Freiwilligen betrieben 
              - In Deutschland gibt es viele Relays
              - Hetzner ist diesbezüglich beliebt...
              
                .. container:: incremental

                  ... und deswegen steht Hetzner auf der Liste der zu `vermeidenden Hoster <https://community.torproject.org/relay/community-resources/good-bad-isps/>`__ (Stand Jan. 2024).


            .. container:: layer footnotesize incremental

                Ein Tor-Knoten wird as ``schnell`` (*fast*) eingestuft, wenn er aktiv ist und eine Bandbreite von mindestens 100KB/s hat oder unter den Top 7/8tel aller bekannten aktiven Router ist.
                
                Zum Vergleich: Die durchschnittliche Bandbreite in Deutschland ist 80Mbit/s (cf. `Statista <https://www.statista.com/statistics/1338657/average-internet-speed-germany/>`__).

                (Stand Jan. 2024)


.. container:: supplemental

    Pfade, die über die ganze Welt gehen verhindern, dass der ``Entry-`` und ``Exit-node`` beim gleichen Anbieter liegen.

    .. image:: tor-circuit.png
        :alt: Tor Circuit
        :align: center
        :class: picture

    **Jan. 2024 - zu vermeidende Hoster**:

    .. code:: text

        Frantech / Ponynet / BuyVM (AS53667)
        OVH SAS / OVHcloud (AS16276)
        Online S.A.S. / Scaleway (AS12876)
        Hetzner Online GmbH (AS24940)
        IONOS SE (AS8560)
        netcup GmbH (AS197540)
        Psychz Networks (AS40676)
        1337 Services GmbH / RDP.sh (AS210558)


Tor Exit Nodes
-----------------

Die Anzahl der Exit nodes ist deutlich kleiner (2. Jan. 2024 - 1314 Einträge) als die Anzahl der Knoten. Dies liegt daran, dass die technischen Anforderungen höher sind (z.B. stabile IP Adressen) und insbesondere daran, dass die Betreiber der ``Exit nodes`` darauf vorbereitet sein müssen ggf. (zahlreiche) Anfragen von den Behörden zu bekommen. [#]_

.. image:: tor-german-exit-node.png
    :alt: Deutscher Tor Exit Node von der TU Berlin
    :align: center
    :class: picture

.. container:: supplemental

    Reverse IP Lookup für 130.149.80.199 durchgeführt mit `IP Location Service <https://www.iplocation.net/ip-lookup>`__.

.. [#] `Tor Exit Node Guidelines <https://community.torproject.org/relay/community-resources/tor-exit-guidelines/>`__




Tor Relays: ``Exit Policy``
-----------------------------

Jeder ``Node`` legt in seiner ``Exit Policy`` genau fest welchen Datenverkehr weiterleiten möchte:

- Es gibt offene Exit Nodes, die alle Anfragen weiterleiten.
- Es gibt Knoten, die die Daten nur an weitere Tor-Knoten weiterleiten.
- Es gibt Knoten, die nur bestimmte Dienste (z.B. HTTPs) weiterleiten.
- Es gibt :ger-quote:`private Exit Nodes`, die nur zu einem bestimmten Netz Verbindungen aufbauen.






Onion Services/Hidden Services
-----------------------------------

- Server, die Anfragen nur aus dem Tor-Netzwerk annehmen, werden als *Onion Services* (bzw. *Hidden Services*) bezeichnet. 
- ``.onion`` ist eine *Pseudo*-Top-Level-Domain, die für Onion Services verwendet wird.
- Onion Services können nur über das Tor-Netzwerk erreicht werden. 
  
  :minor:`Onion-Adresse der New-York-Times im Tor Netzwerk: https://nytimesn7cgmftshazwhfgzm37qxb44r64ytbb2dj3x62d2lljsciiyd.onion (Aus Deutschland faktisch nicht nutzbar.)` 



Tor Browser
---------------

Standardanwendung für den Zugriff auf das Tor-Netzwerk.

.. container:: stack

    .. container:: layer 
        
        .. image:: tor-onion-service-nyt.png
            :alt: Tor Browser mit Ney-York-Times - 01.01.2024
            :align: left
            :width: 1000px

        .. container:: scriptsize

            Ergebnis nach mehreren Minuten Wartezeit und zwei Versuchen überhaupt eine Verbindung aufzubauen.

    .. container:: layer incremental

        **Sicherheitseinstellungen des Tor Browsers**       
        
        :Standard: alle Browserfunktionen sind aktiviert.
        :Sicherer: JavaScript ist auf Nicht-HTTPS-Seiten deaktiviert. Wenn JavaScript aktiviert ist, dann sind die Leistungsoptimierungen deaktiviert. Audio und Video (HTML5-Medien) sowie WebGL werden nur nach Mausklick abgespielt.
        :Sicher: (zusätzlich) JavaScript ist immer deaktiviert. Einige Schriftarten, Symbole, mathematische Symbole und Bilder sind deaktiviert.


.. container:: supplemental

    Das Tor-Netzwerk erlaubt ggf. das Setzen des ``Exit Nodes``, um zum Beispiel geografische Sperren zu umgehen. Entsprechende Dienstanbieter können dies jedoch leicht erkennen, da die Knoten des Tor Netzwerkes bekannt sind (https://check.torproject.org/torbulkexitlist) und verweigern dann den Zugriff.




Tor
----

.. class:: positive-list incremental

- Schützt vor der Analyse des Datenverkehrs. 

  Von `SecureDrop <https://securedrop.org/>`__ wird zum Beispiel für Whistleblower empfohlen sich mit dem SecureDrop Service über Tor zu verbinden und erst dann Dokumente hochzuladen.

- Tor Browser schützt relativ effektiv vor Website-Fingerprinting.

.. class:: negative-list incremental

- Teilweise sehr langsam (insbesondere bei Onion Services).
- Monitoring des Netzwerks ist an den Grenzen möglich.
- Ende-zu-Ende Korrelation von Datenverkehr ist möglich.
- Die Anonymität hängt auch von der Anzahl der Nutzer ab.


.. container:: supplemental

    *Website Fingerprinting*

    Website Fingerprinting ermöglicht es die besuchten Websites anhand des Datenverkehrs zu identifizieren. Dabei wird nicht der Inhalt der Datenpakete analysiert, sondern die statistischen Eigenschaften des Datenverkehrs. Wie groß sind die Datenpakete (d.h. die ausgelieferten Dateien)? Wie viele Datenpakete werden wann verschickt? Wie lange dauert es bis ein Datenpaket verschickt wird (d.h. Geschwindigkeit der Webseite)? Wie lange dauert es bis ein Datenpaket ankommt?

    *(Cross-)Browser Fingerprinting*

    Durch das Sammeln vieler (auch kleiner) Informationen über den/die Browser und das Betriebssystem kann ein für praktische Zwecke hinreichend eindeutiger Fingerabdruck erstellt werden. Dieser kann dann zur Identifikation des Nutzers verwendet werden.

    Kleiner Auszug aus den möglichen Informationen:

    - System Fonts
    - Werden Cookies unterstützt?
    - Betriebssystem
    - Betriebssystem Sprache 
    - Keyboard layout
    - Art/Version des Browsers
    - verfügbare Sensoren: Beschleunigungssensor, Näherungssensor, Gyroskop
    - verfügbare Browser Plugins
    - HTTP-Header Eigenschaften
    - CPU Klasse
    - HTML 5 Canvas Fingerprinting 
    - Unterstützung von Multitouch

    *Monitoring des Netzwerks an den Grenzen*

    Hat in der Vergangenheit dazu geführt, dass Nutzer von Tor-Netzwerken identifiziert werden konnten.

    *Ende-zu-Ende Korrelation von Datenverkehr* 

    Auch als *Traffic Confirmation* bekannt. Diese Art von Attacke ist möglich, wenn *Relays* am Anfang und am Ende der Verbindung kontrolliert werden. Die Angreifer können dann den Datenverkehr an beiden Enden beobachten und die Datenpakete korrelieren z.B. basierend auf statistischen Informationen über die Zeitpunkte und Volumen von Datenflüssen. 




.. class:: integrated-exercise transition-move-left

Übung: Tor
-----------

- Ist es für Onion Services notwendig auf HTTPS zu setzen oder reicht HTTP für eine sichere Kommunikation?

.. **Antwort**
   Im Allgemeinen ist es ausreichend wenn Onion Service "nur" HTTP anbieten, da der gesamte Verkehr zwischen Client und Server durch Tor verschlüsselt ist.
   Für Onion Services ist es sinnvoller über HTTP zu kommunizieren. HTTPS bietet keinen relevanten zusätzlichen Schutz. Auf der anderen Seite gefährdet HTTPS die Anonymität des Servers, da die TLS Zertifikate öffentlich sind und damit die Existenz des Servers preisgeben.

   https://support.torproject.org/https/https-1/

- Wie wird der DNS Lookup für normale Webseiten durchgeführt, wenn der Tor Browser verwendet wird? Warum ist dies wichtig?

.. **ANTWORT**
   Der DNS Lookup wird nicht durch den Tor Browser durchgeführt. Der DNS Lookup wird durch den Exit Node durchgeführt.
   Ein standardmäßiger DNS Lookup würde die Anonymität des Nutzers gefährden. Der Exit Node könnte den DNS Lookup mit dem Datenverkehr des Nutzers korrelieren und damit die Identität des Nutzers ermitteln. 

- Warum hätte das Abschalten von TOR auf kriminelle Aktivitäten im Internet vermutlich nur einen geringeren Einfluss?
  
.. **ANTWORT**
   Es gibt zahlreicher weitere Dienste, die ähnliche Funktionalität bieten. Darüber hinaus haben kriminelle Organisationen ggf. die Mittel sich alternative Lösungen zu schaffen.

- Was ist der Unterschied zwischen einem Proxy und einem Tor-Knoten?

.. **Antwort**
   Ein Proxy ist ein Server, der als Vermittler zwischen einem Client und einem Server fungiert. Ein Tor-Knoten ist ein Server, der als Vermittler zwischen einem Client und einem Server fungiert, der selbst ein Tor-Knoten ist. Ein Tor-Knoten ist also ein Proxy, aber ein Proxy ist nicht unbedingt ein Tor-Knoten.

- Wie unterscheidet sich Tor von einem VPN?
  
.. **Antwort**
   Ein VPN ist ein Tunnel zwischen zwei Netzwerken. Tor ist ein Tunnel zwischen einem Client und einem Server. 
   - In beiden Fällen kennt der Zielwebserver nicht die IP-Adresse des Clients.
   - Tor ist dezentralisiert und anonym. VPNs sind zentralisiert und nicht anonym; der VPN Anbieter kennt die IP-Adresse des Clients. 
   - Tor ist sehr langsam; VPNs sind schnell(er).
   - Bei Tor ist dem exit node nicht bekannt wer der Client ist; bei VPNs ist dem VPN Anbieter bekannt wer der Client ist.
   - Tor erlaubt den Zugriff auf .onion Adressen; VPNs nicht.

- Macht es Sinn ein VPN über Tor oder anders herum zu betreiben?

.. **Anwort**
   Es macht nur selten Sinn ein VPN über Tor zu betreiben. In diesem Fall ist zum Beispiel kein Zugriff auf .onion Adressen möglich. Weiterhin kennt der VPN Anbieter seine Kunden. Jedoch wird der Standort des Clients vor dem VPN Anbieter verborgen.
   => (less useful) Tor then VPN (VPN over Tor): Starting with the Tor network is a tricky process and may not be supported by all VPN providers. One of the benefits of this method is that your internet traffic is encrypted as it enters and exits the Tor network. While this method will protect your internet traffic from exit node vulnerability, your ISP will know that you are using Tor, therefore decreasing your anonymity. However, if a VPN is forbidden, you can hide that fact.

   Es macht meistens mehr Sinn Tor über ein VPN zu betreiben.
   => + VPN then Tor: In this method, also known as “Tor over VPN,” you’ll connect with a VPN and then download and  use the Tor browser. This grants you all of the privacy perks of the Tor network and additional IP address protection from your VPN, preventing your ISP from knowing that you use Tor and keeping any Tor node from seeing your IP address.

- Was passiert wenn eine Angreifer in der Lage ist :math:`50\% + 1` der ``Directory Authority`` Server zu kontrollieren?

.. **Antwort**
   "Hell breaks loose." Er kann zum Beispiel auf die Entry und Exitnodes verweisen, die er kontrolliert. Damit kann er den Datenverkehr entschlüsseln. Er kann auch die Onion Services verweisen, die er kontrolliert. Damit kann er die Identität der Nutzer der Onion Services ermitteln.


