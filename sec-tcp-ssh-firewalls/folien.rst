.. meta:: 
    :author: Michael Eichberg
    :keywords: "TCP", "DDoS", SSH
    :description lang=de: Network Security - Eine Einführung in die Sicherheit von (verteilten) Systemen
    :id: lecture-a-primer-in-network-security
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
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

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|

.. container:: footer-left tiny
    
    Die Folien basieren in weiten Teilen auf einem Foliensatz von Prof. Dr. Henning Pagnia.
    
    Alle Fehler sind meine eigenen.


Themen
----------------------------------

- `Transmission Control Protocol (TCP)`_ 
- `Einmal-Passwörter`_
- `Secure Shell (SSH)`_
- `Firewalls`_


.. class:: new-section transition-fade

Transmission Control Protocol (TCP) 
-------------------------------------


.. class:: vertical-title

TCP Grundlagen
-------------------

.. class:: incremental more-space-between-list-items 

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
            :class: center-child-elements

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
            :class: center-child-elements

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
            :class: center-child-elements

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

        .. image:: images/idle-scan/idle-scan-step1.svg 
            :alt: Idle Scan - Schritte 1-2
            :align: left
            :width: 700px

    .. container:: column faded-to-white

        Starte Scan:

        .. image:: images/idle-scan/idle-scan-step2.svg 
            :alt: Idle Scan - Schritte 3-5
            :align: left
            :width: 750px

.. container:: supplemental 

    Zombies: ein Rechner (Computer, Drucker oder anderes IoT Gerät) im Internet *möglichst ohne eigenen Netzverkehr* und mit :ger-quote:`altem` Betriebssystem, bei dem die IP ID in vorhersehbarer Weise inkrementiert wird.

    Sollte ein Intrusion Detection System vorhanden sein, so wird dieses den Zombie als Angreifer identifizieren.

    **Grundlegende Idee**: Der Zombie sendet ein RST Paket zurück, da er kein SYN gesendet hat und kein SYN/ACK erwarte. Dadurch erfährt der Angreifer die aktuelle IP ID des Zombies. Über diesen Seitenkanal - d. h. die Veränderung der IP ID des Zombies - kann der Angreifer nun den Zustand des Ports auf dem Zielrechner ermitteln.

.. [#] `NMap Book <https://nmap.org/book/idlescan.html>`__

    

Port Scans: Idle Scan
-----------------------------

.. container:: two-columns 

    .. container:: column

        Starte Scan:

        .. image:: images/idle-scan/idle-scan-step2.svg 
            :alt: Idle Scan - Schritte 3-5
            :align: left
            :width: 750px

    .. container:: column   

        Sondiere IP ID des Zombies:

        .. image:: images/idle-scan/idle-scan-step3.svg 
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

.. image:: images/connection-hijacking.svg 
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

- durch Ausnutzen von Schwachstellen (:eng:`vulnerabilities`, z. B. Buffer Overflow)
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

    .. image:: images/smurf-angriff.svg 
        :alt: Smurf Angriff
        :align: center
        :height: 800px


Distributed Denial-of-Service (DDoS) Angriff
------------------------------------------------

.. class:: incremental

- Bot-Netze (Botnetze) werden verwendet, um DDoS-Angriffe durchzuführen.
- Bot-Netze können viele 10.000 Rechner umfassen.
- IoT Geräte sind besonders beliebt (z. B. IP-Kameras, Smart-TVs, Smart-Home Geräte, ...), da diese oft nicht ausreichend geschützt sind und trotzdem permanent mit dem Internet verbunden sind.
- Beliebte Ziele:

  - Onlinespieleserver
  - Banking-Portale
  - politische Webseiten
- Firewalls und Intrusion Detection Systeme sind meist wirkungslos, da die Angriffe von vielen verschiedenen IP-Adressen kommen.


Distributed-Reflected-Denial-of-Service (DRDoS) Angriff
------------------------------------------------------------

- Idee:

  .. class:: incremental smaller
  
  - Es wird eine Anfrage an einen Server gesendet, die eine große Antwort auslöst. (z. B. hat(te) der NTP Monlist Befehl eine Antwort, die ca. 200 Fach größer ist als die Anfrage!)
  - Mittels IP-Spoofing wird die IP-Adresse des Opfers als Absenderadresse verwendet.
  - Es werden insbesondere Dienste basierend auf UDP verwendet, da hier keine Verbindung aufgebaut werden muss.

.. class:: incremental smaller

- Nehmen einen signifikanten Teil aller DDoS-Angriffe ein. 
- Die Tatsache, dass die Sender legitime Server sind, erschwert die Abwehr.
- :eng:`Egress Filtering` kann helfen, die Verwendung von IP-Spoofing zu verhindern. 


.. container:: supplemental
    
    Bereits im Jahr 2018 wurde ein Angriff mit einer Bandbreite von 1,7 TBit/s beobachtet.

    :Egress Filtering: Der Router verwirft alle Pakete, die eine Absenderadresse verwenden, die nicht aus dem eigenen Netzwerk stammt. 


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

- Aufrüsten der Ressourcen (z. B. Bandbreite, CPU, RAM, ...) 
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
- Content-Delivery-Networks (CDNs) für statische Inhalte (z. B. Cloudflare, Akamai, ...)
- Distributed Clouds


Password Sniffing
---------------------

:In der Anfangszeit: unverschlüsselte Übertragung von Passwörtern (telnet, ftp, ...)
:In der Übergangszeit (bzw. in bestimmten Szenarien auch heute): Verwendung von Einmal-Passwörtern (S/Key, ...)
:Heute: Passwörter werden verschlüsselt übertragen (ssh, https, ...)


.. container:: supplemental

    Unverschlüsselte Passworte können leicht mittels eines Sniffers, der den Netzwerkverkehr mitschneidet (z. B. Wireshark), abgefangen werden.


Einmal-Passwörter
----------------------

Die Idee ist, dass Passwörter nur genau einmal gültig sind und nicht wiederverwendbar sind.

- Tokens (z. B. RSA SecurID)
- Codebuch: Liste von Einmal-Passwörtern, die das gemeinsame Geheimnis sind.
- S/Key: Passwort "wird mit einem Zähler kombiniert" und dann gehasht.


Das S/Key Verfahren 
------------------------------

Einmal-Passwort-System nach Codebuch-Verfahren, dass im Original auf der kryptographischen Hashfunktion MD4 basiert.

.. stack:: 

    .. layer:: 

        **Initialisierung**

        .. class:: incremental smaller

        1) Der Nutzer gibt sein Passwort W ein; dies ist der geheime Schlüssel. (Sollte W bekannt werden, dann ist die Sicherheit des Verfahrens nicht mehr gewährleistet.)
        2) Eine kryptografische Hash-Funktion H wird n-mal auf W angewandt, wodurch eine Hash-Kette von n einmaligen Passwörtern entsteht. :math:`H(W), H(H(W)), \dots, H^{n}(W)`
        3) Das initiale Passwort wird verworfen.
        4) Der Benutzer erhält die n Passwörter, die in umgekehrter Reihenfolge ausgedruckt werden: :math:`H^n(W), H^{n-1}(W), ..., H(H(W)), H(W)`.
        5) Nur das Passwort :math:`H^n(W)`, das an erster Stelle der Liste des Benutzers steht, der Wert von :math:`n` und ggf. ein Salt, wird auf dem Server gespeichert.

    .. layer:: incremental

        **Anmeldung**

        Identifiziere :math:`n` das letzte verwendete Passwort.

        .. class:: incremental smaller
       
        - Der Server fragt den Nutzer nach dem Passwort :math:`n-1` (d. h. :math:`H^{n-1}(W)`) und übermittelt ggf. auch den Salt. 
        - Der Server hasht das Passwort und vergleicht es dann mit dem gespeicherten Passwort :math:`H^n(W)`.
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


.. image:: images/ssh/initiation.svg 
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



Secure Shell (SSH) - Verbindungsaufbau - Beispiel
------------------------------------------------------------

.. container:: scrollable 

    .. code:: text
        :class: smallest

        debug1: Reading configuration data /etc/ssh/ssh_config
        debug1: Applying options for *
        debug1: Connecting to example.org [1.2.3.4] port 22.
        debug1: Connection established.
        debug1: identity file /home/user/.ssh/id_rsa type -1
        debug1: identity file /home/user/.ssh/id_rsa-cert type -1
        debug1: identity file /home/user/.ssh/id_dsa type -1
        debug1: identity file /home/user/.ssh/id_dsa-cert type -1
        debug1: Remote protocol version 1.99, remote software version OpenSSH_5.8
        debug1: match: OpenSSH_5.8 pat OpenSSH*
        debug1: Enabling compatibility mode for protocol 2.0
        debug1: Local version string SSH-2.0-OpenSSH_5.5p1 Debian-6
        debug1: SSH2_MSG_KEXINIT sent
        debug1: SSH2_MSG_KEXINIT received
        debug1: kex: server->client aes128-ctr hmac-md5 none
        debug1: kex: client->server aes128-ctr hmac-md5 none
        debug1: SSH2_MSG_KEX_DH_GEX_REQUEST(1024<1024<8192) sent
        debug1: expecting SSH2_MSG_KEX_DH_GEX_GROUP
        debug1: SSH2_MSG_KEX_DH_GEX_INIT sent
        debug1: expecting SSH2_MSG_KEX_DH_GEX_REPLY
        debug1: Host 'example.org' is known and matches the RSA host key.
        debug1: Found key in /home/user/.ssh/known_hosts:1
        debug1: ssh_rsa_verify: signature correct
        debug1: SSH2_MSG_NEWKEYS sent
        debug1: expecting SSH2_MSG_NEWKEYS
        debug1: SSH2_MSG_NEWKEYS received
        debug1: Roaming not allowed by server
        debug1: SSH2_MSG_SERVICE_REQUEST sent
        debug1: SSH2_MSG_SERVICE_ACCEPT received
        debug1: Authentications that can continue: publickey,password,keyboard-interactive,hostbased
        debug1: Next authentication method: publickey
        debug1: Trying private key: /home/user/.ssh/id_rsa
        debug1: Trying private key: /home/user/.ssh/id_dsa
        debug1: Next authentication method: keyboard-interactive
        debug1: Authentications that can continue: publickey,password,keyboard-interactive,hostbased
        debug1: Next authentication method: password
        user@example.org's password: 
        debug1: Authentication succeeded (password).
        debug1: channel 0: new [client-session]
        debug1: Requesting no-more-sessions@openssh.com
        debug1: Entering interactive session.
        debug1: Sending environment.
        debug1: Sending env LANG = en_US.UTF-8



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

- ermöglicht die Übertragung beliebiger Netzwerkdaten über eine verschlüsselte SSH-Verbindung. z. B. 

  - um ältere Anwendungen zu verschlüsseln. 
  - um VPNs (Virtual Private Networks) zu implementieren 
  - um über Firewalls hinweg auf Intranetdienste zuzugreifen.

- ermöglicht auch Port-forwarding (lokale Ports werden auf entfernten Rechner weitergeleitet)

.. image:: images/ssh/tunneling.svg 
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



Schwachstellen in SSH 
--------------------------

.. epigraph::

    **Nearly 11 million SSH servers vulnerable to new Terrapin attacks**
    
    [...]
    It [The Terrapin attack] manipulates sequence numbers during the handshake process to compromise the integrity of the SSH channel, particularly when specific encryption modes like ChaCha20-Poly1305 or CBC with Encrypt-then-MAC are used. 
    [...]

    By Bill Toulas  


    -- `January 3, 2024 10:06 AM <https://www.bleepingcomputer.com/news/security/nearly-11-million-ssh-servers-vulnerable-to-new-terrapin-attacks/>`__



.. class:: integrated-exercise transition-move-left

Übung
------------------------------

.. exercise:: Port Scans - IDLE Scan

  - Warum kann mit einem IDLE Scan nicht festgestellt werden warum ein Port geschlossen oder gefiltert ist?
  - Welchen Wert hat die IP ID des Zombies, der einem IDLE Scan durchführt, wenn der Zielport offen bzw. geschlossen ist wenn der Scanner diesen wieder abfragt?

  .. solution::
     :pwd: IDLEPort

     - Wenn der Port geschlossen ist, dann sendet der Zielrechner ein RST Paket an den Zombie. Dieses wird vom Zombie ignoriert. Daher erhöht sich die IP ID des Zombies nicht.
     - Wenn der Port offen ist, dann sendet der Zielrechner ein SYN/ACK Paket an den Zombie. Dieser antwortet mit einem RST Paket und erhöht seine IP ID um 1. d. h. der Wert der IP ID des Zombies ist um 2 höher wenn der Port offen ist und "nur" eins höher sonst.



.. class:: integrated-exercise transition-move-left

Übung
--------------

.. exercise:: S/Key

    1. Welche Vorteile bieten Einmalpasswortsysteme gegenüber Systemen mit mehrfach zu verwendenden Passworten?
    2. Welchen Angriffen sind Einmalpasswortsysteme weiterhin ausgesetzt?
    3. Generieren Sie eine Liste von Einmalpassworten mit Initialwert :math:` r = 769`. Generieren Sie :math:`H(r)` bis :math:`H^6(r)` wenn die Einwegfunktion hier der Einfachheit halber :math:`H(x) = x^2\; mod\; 1000` ist.
    4. Wie oft kann sich der Benutzer anmelden? Wie sieht seine Liste aus?
    5. Welchen Wert speichert der Server vor dem jeweiligen Anmeldevorgang?
    6. Spielen Sie zwei Anmeldevorgänge durch.
    7. Wenn ein Passwort :math:`H^L(W), 1 < L < N` bekannt ist, welche Auswirkungen hat dies auf die Sicherheit des Verfahrens?

    .. solution::
        :pwd: sKey.!

        1. Schutz gegen Lauscher
        2. Man-in-the-middle
        3. Der Benutzer wählt eine Zufallszahl :math:`r`, hier :math:`r = 769`. Berechnet wird nun:
        
           :math:`769^2\; mod\; 1000 = 361`

           :math:`361^2\; mod\; 1000 = 321`
        
           :math:`321^2\; mod\; 1000 = 41`
        
           :math:`41^2\; mod\; 1000 = 681`
        
           :math:`681^2\; mod\; 1000 = 761`
        
           :math:`761^2\; mod\; 1000 = 121`

        4. Fünfmal. Der Benutzer erhält folgende Passwortliste: 761, 681, 41, 321, 361
        5. Der Server speichert: 121
        6. Beim ersten Anmeldevorgang verwendet der Benutzer das erste Passwort auf der Liste, die 761.

           Der Server berechnet nun 7612 mod 1000 = 121 und vergleicht dies mit dem gespeicherten Wert. Da diese übereinstimmen, wird der Benutzer angemeldet.

           Der Server speichert jetzt die 761, und der Benutzer streicht die 761 von der Liste, usw.

        7. Keine


.. class:: integrated-exercise transition-move-left

Übung
--------------

.. exercise:: DDoS

  1.  Welches Problem entsteht wenn zum Schutze vor Angriffen auf die Verfügbarkeit die Ressourcen von IT-Systemen und deren Internet-Anbindung erhöht werden?
  2. Recherchieren Sie was ein "Low and Slow Angriff" ist.
  3. Wo kann überall "Egress filtering" statt finden.

  .. solution::
    :pwd: DDoSVerstehen

    1. Ressourceverschwendung wenn gerade kein Angriff stattfindet. Wenn der Angriff stattfindet, dann ist es immer noch möglich bzw. sogar wahrscheinlich, dass die Ressourcen nicht ausreichen.
    2. (vgl. https://www.cloudflare.com/de-de/learning/ddos/ddos-low-and-slow-attack/)
    
       Ein Low-and-Slow-Angriff ist eine Art von DoS- oder DDoS-Angriff, der sich auf einen kleinen Strom sehr langsamen Traffics stützt, der auf Anwendungs- oder Serverressourcen abzielt. Im Gegensatz zu herkömmlichen Brute-Force-Angriffen benötigen Low-and-Slow-Angriffe nur sehr wenig Bandbreite und können schwer bekämpft werden, da sie Traffic erzeugen, der nur sehr schwer von normalem Traffic zu unterscheiden ist. Während groß angelegte DDoS-Angriffe wahrscheinlich schnell bemerkt werden, können Low-and-Slow-Attacken über lange Zeiträume unentdeckt bleiben, während der Dienst für echte Nutzer verweigert oder verlangsamt wird.

       Da sie nicht viele Ressourcen benötigen, können Low-and-Slow-Angriffe von einem einzigen Computer aus erfolgreich durchgeführt werden, im Gegensatz zu verteilten Angriffen, für die ein Botnet erforderlich sein kann. Zwei der beliebtesten Tools für Low-and-Slow-Angriffe heißen Slowloris und R.U.D.Y.

       .. rubric:: R.U.D.Y.

       :ger-quote:`R U Dead Yet?` oder R.U.D.Y. ist ein Denial-of-Service-Angriffstool, das zum Ziel hat, einen Webserver durch Senden von Formulardaten bei unsinnig niedriger Geschwindigkeit zu blockieren. Ein R.U.D.Y.-Exploit wird als Low-and-Slow-Angriff kategorisiert, weil er darauf abzielt, einige wenige langwierige Anfragen zu erzeugen, anstatt einen Server mit einem hohen Volumen schneller Anfragen zu überfluten. Ein erfolgreicher R.U.D.Y.-Angriff bewirkt, dass der Ursprungsserver des Opfers für legitimen Traffic unzugänglich wird.

       .. rubric:: Slowloris

       Slowloris ist ein *low and slow* DDoS-Angriffsvektor. Die Idee des Slowloris-Angriffs besteht darin, den gesamten TCP-Stack für den HTTP/S-Daemon zu sättigen. Dies geschieht, indem langsam Verbindungen geöffnet und dann eine unvollständige Anfrage gesendet wird, um die Verbindung so lange wie möglich am Leben zu erhalten. Das Tool geht dabei langsam vor, so dass es in einigen Fällen möglich ist, dass ein einziger Angreifer einen Webserver zum Absturz bringen kann. Wenn das Limit der gleichzeitigen Verbindungen auf dem angegriffenen Server erreicht ist, kann der Server nicht mehr auf legitime Anfragen von anderen Benutzern reagieren, was zu einer Dienstverweigerung führt.
    
       Der Slowloris-Angriff zielt darauf ab, die Verbindungstabelle zu füllen, so dass der Server nicht mehr in der Lage ist, neue legitime Anfragen von legitimen Benutzern zu bedienen. Dies wird durch den Einsatz von zwei Hauptfunktionen erreicht: 1. Instabile Öffnungsrate für neue Verbindungen - neue TCP-Verbindungen werden stoßweise angefordert, wobei zwischen jedem Stoß eine gewisse Zeit gewartet wird, was es schwierig macht, von ratenbasierten Abhilfemaßnahmen entdeckt zu werden. 2. Aufrechterhaltung neu eingerichteter TCP-Verbindungen - neu eingerichtete TCP-Verbindungen werden aufrechterhalten, indem Teildaten über mehrere HTTP-Anforderungen unter Verwendung derselben TCP-Verbindung gesendet werden. Dadurch wird das Ziel gezwungen, die Verbindungen offen zu halten, während gleichzeitig Platz in der Verbindungstabelle und Speicherplatz verbraucht werden.


    3. Dies kann zum Beispiel auf Seiten eines ISPs geschehen.


.. class:: new-section transition-fade

Firewalls
------------

Unabhängiges Netz - :ger-quote:`Ideale Situation` 
---------------------------------------------------

.. image:: images/firewalls/all-save.png
    :alt: Ideale Situation
    :align: center

:Vorteile: 
    
    - keinerlei Angriffsmöglichkeiten von außen 

:Nachteile:

    - kein Schutz gegen Insider
    - kein Zugang zum Internet



Von der Notwendigkeit des Schutzes von Rechnern
--------------------------------------------------------

.. epigraph:: 
    
    [...] Züger und sein Team hätten [...] erst kürzlich ein Experiment durchgeführt, [...]. Sie hätten einen Computer "ohne jeglichen Schutz" mit dem Internet verbunden, um zu sehen, wie lange es dauere, bis er befallen sei. Konkrete Details zur Konfiguration dieses Systems werden zwar nicht genannt, angeblich war der Rechner aber schon nach 20 Minuten infiltriert.

    -- `Golem.de 6.2.2024 <https://www.golem.de/news/iot-hacker-missbrauchen-zahnbuersten-fuer-ddos-angriffe-2402-181921.html>`__



Schutzschicht zwischen internem und externem Netz
------------------------------------------------------

.. image:: images/firewalls/firewall.png
    :alt: Schutzschicht zwischen internem und externem Netz
    :align: center

- Kontrolle des Nachrichtenverkehrs durch Filterung 
- begrenzte Isolation mit begrenztem Schutz

.. container:: supplemental

    Eine Firewall schafft zwischen verbundenen Netzen Sicherheitsdomänen mit unterschiedlichem Schutzbedarf. Eine wichtige Teilaufgabe ist das Ausarbeiten von Sicherheitsrichtlinien.


Realisierung von Virtual Private Networks (VPN)
------------------------------------------------------

.. image:: images/firewalls/vpn.png
    :alt: Realisierung von Virtual Private Networks (VPN)
    :align: center

- Aufbau einer scheinbar privaten Verbindung von Firmenteilnetzen über das (öffentliche) Internet
- Zusätzliche Verbindungsverschlüsselung zwischen den Firewalls.

.. container:: supplemental

    Ziel ist es aktive und passive Angriffe zu unterbinden. 
    Selbst bei verschlüsselten Verbindungen kann die Verkehrsflussanalyse noch Informationen liefern über die Verbindungen liefern.


Kommerzielle VPNs für Endnutzer
---------------------------------

.. image:: images/firewalls/vpn-commercial.svg
    :alt: Einsatz von Virtual Private Networks (VPN) für Privatnutzer
    :align: center
    :width: 1400px

.. container:: supplemental

    **Motivation**

    - Schutz der Privatsphäre; der ISP kennt nicht mehr die Webseiten, die man aufruft
    - Die IP-Adresse des Nutzers ist den aufgerufenen Webseiten nicht mehr bekannt und kann deswegen der Umgehung von Geo-Blocking dienen.

    **Nachteile?**

    - Vertrauen in den VPN-Anbieter muss vorhanden sein. Insbesondere, beim Einsatz zum Stärken der Privatsphäre, muss der VPN-Anbieter vertrauenswürdig sein und sollte ein so genannter "no-log" Anbieter sein. Es gibt auch (kostenlose) VPN-Anbieter, die die Daten der Nutzer verkaufen (ehemals: `Facebook Onavo <https://techcrunch.com/2019/02/21/facebook-removes-onavo/?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAAGVIppEgEOd9Z0FoMbmk2TCleRmD9wCMWDmIzGYEjIo1c7Cmz8NpiSoibthFG5IZQzmZ-kiJq-5Wj1bj21byh7YUrC_aSJJk1Bapwz80GSgzLFS-LHCF2OOetUYLSKwEG7W75znuqJJBJcNTTbtJ1UGB95Yu90saK9aIIkEywcRq>`__).





Schutz auf den Schichten des TCP/IP Stacks
-----------------------------------------------------------

Zentraler Schutz des gesamten internen Netzwerks durch:

.. class:: incremental

- Paket Filter (:eng:`Packet Filtering`) 

  - Blockieren bestimmter IP-Empfänger-Adressen (extern / intern)
  - Blockieren bestimmter IP-Absender-Adressen (extern / intern)
    
    :minor:`(z. B. aus dem Internet mit internen IP-Absender-Adressen.)`
  
  - Blockieren bestimmter Dienste; ggf. nur für bestimmte IP-Adressen

- Filter auf Anwendungsebene (:eng:`Application-level Filtering`)

  - inhaltsbezogene Filterung der Verkehrsdaten eines Dienstes
  - z. B. Virenfilter
  - wirkungslos bei verschlüsselten Verkehrsdaten

- Protokollierungsmöglichkeit der Kommunikation von / nach extern


.. container:: supplemental

    Firewalls (alleine) können die Struktur des Netzwerks nicht verbergen.


.. class:: smaller

DoS Attacke auf Anwendungsebene
------------------------------------------------

.. epigraph::

    [...]

    Angriff auf die Kleinen

    Waren bei früheren Spamangriffen massenhaft Accounts auf der größten Mastodon-Instanz mastodon.social angelegt worden, die dann von dort ihre Inhalte verbreiteten, trifft es nun nicht die größte, sondern die kleinsten. Automatisiert werden dabei Instanzen ausgesucht, auf denen eine Registrierung ohne Überprüfung und sogar ohne ein Captcha möglich ist. Das können etwa solche mit wenigen Accounts sein, die von Enthusiasten etwa für eine Gemeinde betrieben werden. Waren die Verantwortlichen in den vergangenen Tagen nicht aufmerksam, wurden diese Instanzen dann regelrecht überrannt. Die Spam-Accounts verschickten massenhaft Nachrichten mit einem Bild des namensgebenden Frühstücksfleischs und Links zu Discord-Servern, die wohl lahmgelegt werden sollten.

    -- `Mastodon: Spamwelle zeigt Schwächen auf [...] <https://www.heise.de/news/Mastodon-Spamwelle-zeigt-Schwaechen-auf-und-weckt-Sorge-vor-schlimmerer-Methode-9632055.html>`__


Realisierungsmöglichkeiten von Firewalls
------------------------------------------------

.. class:: incremental

- Hardware-Firewall
 
  - Screening Router
  - Application Gateway (auch Bastion Host)
  
    - Proxy-Server für bestimmte Dienste
    - Client-Software (HTTP-Browser, telnet, ftp, ...) 
    - Server-Software 

- Software-Firewall (*Personal Firewall*)


.. container:: supplemental

    Im Falle eines :eng:`Bastion Host`, ist dies der einzige unmittelbar erreichbare Rechner.


.. .. class:: vertical-title

Dual-Homed Host
----------------

.. image:: images/firewalls/dual-homed-host.png 
    :alt: Dual-Homed Host
    :align: right
    :class: margin-1em

**Aufbau**

- zwei Netzwerkkarten: ggf. private interne Adressen
- Screening Router & Gate: Packet Filter und Application-Level Filter
- Proxy-Dienste installieren 
- Benutzer-Logins von extern
- Konf. der Netzwerkkarten: IP-Pakete nicht automat. weiterleiten


Screening Router
---------------------

.. container:: two-columns no-default-width

    .. container:: column no-separator

        **Aufbau**

        - programmierbarer Hardwarerouter 
        - simple Filterfunktionen:

        - nur Paket-Header prüfen  
        - schnelle Auswertung ermöglicht hohen Durchsatz

        - Realisierung eines Packet Filters

        **Bewertung**

        .. container:: two-columns width-60

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

    .. container:: column

        .. image:: images/firewalls/screening-router.png 
            :alt: Screening Router
            :class: margin-1em
            :align: center



Screened Host
-----------------

.. image:: images/firewalls/screened-host.png 
    :alt: Screened Host
    :align: right
    :class: margin-1em padding-left-1em

**Aufbau**

- Screening Router blockiert:

  - Pakete von / an interne Rechner (nicht Gate)
  - Source-Routed Pakete

- von extern nur Gate sichtbar 
- Pakete von intern nur via Gate
- Gate bietet Proxy-Server (z. B. für E-Mail)


.. container:: supplemental

    Gibt es für eine bestimmte Anwendung kein Application-level Proxy, dann kann auf einen für TCP/UDP generischen Proxy zurückgegriffen werden. Dieser arbeitet auf dem Session Layer und kann nur die Header-Informationen auswerten. Es handelt sich dann um ein :eng:`Circuit-level Proxy/Gateway`. Im Vergleich zu einem Application-level Proxy ist die Sicherheit geringer, da der Circuit-level Proxy nicht in der Lage ist, die Daten zu interpretieren.

    Ein allgemeines Problem ist, dass viele Anwendungen auf generische Protokolle wie HTTP aufsetzen. Weiterhin betreiben einige Anwendungen :ger-quote:`Port Hopping`, d. h. sie wechseln den Port wenn der Standardport nicht offen ist.

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

.. image:: images/firewalls/screened-subnet.png 
    :alt: Screened Subnet
    :align: right


**Aufbau**

.. class:: incremental

- interner Screening Router als weiterer Schutzwall

  - blockiert Dienste, die nicht einmal bis zum Gate gelangen sollen
  - lässt nur Pakete zum / vom Gate durch

- äußeres Netz realisiert Demilitarisierte Zone (DMZ) für HTTP-Server, Mail-Server, ...


Intrusion Detection Systeme (IDS)
--------------------------------------

.. admonition:: Definition

    Ein IDS ist ein Gerät (meist ein speziell konfigurierter Rechner), das vielfältige Techniken zur Erkennung von Angriffen anwendet und Angriffe meldet und ggf. abwehrt, in dem (z. B.) die Firewall automatisch umkonfiguriert wird.

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

Übung
------------------

.. exercise:: Firewalls

    1. Was sind Vorteile eines Dual Homed Host gegenüber einem Paketfilter? Was sind die Nachteile?

    2. Benennen Sie zwei konzeptionelle Grenzen von Firewalls. D. h. zwei Szenarien gegen die Firewalls nicht schützen können.

    3. Für welche der folgenden Cybersicherheitsstrategien können Firewalls eingesetzt werden:
    
       1. Angriffe vermeiden
       2. Angriffe erkennen
       3. Angriffe abwehren/Angriffen entgegenwirken
       4. Reaktion auf Angriffe

    4. Sie werden beauftragt die Firewall so einzurichten, dass Mails mit Schadsoftware nicht durchgelassen werden. Wie reagieren Sie?

    .. solution::
        :pwd: fIREwall

        1. Ein Dual Homed Host ist ein Computer mit zwei Netzwerkschnittstellen. Zur Verwendung als Firewall wird das Routing, also die Weiterleitung von IP-Paketen zwischen den Schnittstellen, abgeschaltet. Damit können keine Pakete direkt zwischen den Netzen ausgetauscht werden und alle Verbindungen enden am Dual Homed Host. Um Daten weiterzuleiten, muss auf dem Dual Homed Host ein Proxy laufen, der eine Verbindung annimmt und eine neue Verbindung in das andere Netz aufbaut (gesteuert über Regel- und Berechtigungstabellen). Man kann über diese Application Level Gateways eine gute inhaltliche Kontrolle der übertragenen Daten durchführen, bei E-Mail beispielsweise eine Längenbegrenzung oder eine Erkennung von mitgeschickten ausführbaren Programmen, die dann automatisch geprüft oder entfernt werden könnten. Für jeden freigeschalteten Dienst benötigt man einen speziellen Proxy.

           Ein Risiko bei Dual Homed Hosts ist die Übernahme des Hosts durch einen Angreifer. Dieser hat dann über die entsprechende Netzwerkschnittstelle des Dual Homed Hosts vollständigen Zugriff auf das interne Netz.

        3. \

           - Hintertüren - sollte es Kommunikationsübergänge an der Firewall vorbei geben,  so können diese von Angreifern genutzt werden.
           - Interne Angriffe - diesbezüglich gibt es keine Unterschiede zu einem Netzwerk ohne Firewall.
           - Vertrauenswürdigkeit der Kommunikationspartner.

        4. Die Hauptaufgabe von Firewalls ist es Angriffen entgegenzuwirken (3.). Eine Reaktion auf Angriffe ist für klassische Firewalls nicht möglich. Eine Reaktion auf Angriffe ist Aufgabe von Intrusion Detection Systemen. Moderne Firewalls integrieren jedoch häufig auch Funktionen von *Intrusion Detection Systemen*. (Angriffe können nicht vermieden werden, da dies nicht in der Macht der Firewall liegt. Klassische/Einfache Firewalls können keine Angriffe erkennen.)
        5. ... die Mails sollen ja den Mailserver erreichen; eine inhaltsbasierte Beurteilung des Inhalts einer Mail ist nicht Aufgabe einer Firewall. 

