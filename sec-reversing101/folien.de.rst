.. meta:: 
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Reverse Engineering"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: lecture-security-java_reverse_engineering
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!    

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
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

.. role:: raw-html(raw)
   :format: html


Reverse Engineering 101 
=====================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: 1.0

.. supplemental::

  :Folien: 
      :HTML: |html-source|

      :PDF: |pdf-source|
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io/issues



Vorerfahrungen?
-------------------

.. class:: incremental more-space-between-list-items

- Wer hat schon einmal Software or Hardware Reverse Engineering betrieben?
- Wer kennt Java Bytecode?
- Wer hat Erfahrung mit Python?
  


Reverse Engineering
----------------------

Reverse Engineering ist die Analyse von Systemen mit dem Ziel, ihren Aufbau und ihre Funktionsweise zu verstehen.

.. container:: incremental 

        
    Typische Anwendungsfälle:

    .. class:: incremental

    - die Rekonstruktion (von Teilen) des Quellcodes von Programmen, die nur als Binärabbild vorliegen.
    - die Analyse von Kommunikationsprotokollen proprietärer Software 

.. container:: supplemental 

    Vom Reverse Engineering ist das **Reengineering** zu unterscheiden. Im Fall von letzteren geht es :ger-quote:`nur` darum die Funktionalität eines bestehenden Systems mit neuen Techniken wiederherzustellen.


Zweck von Reverse Engineering
--------------------------------

.. class:: incremental

- Herstellung von Interoperabilität 
- Untersuchung auf Schwachstellen
- Untersuchung auf Copyrightverletzungen
- Untersuchung auf Backdoors
- Analyse von Viren, Würmern etc.
- Umgehung von ungerechtfertigten(?) Schutzmaßnahmen (z. B. bei Malware)



Reverse Engineering - grundlegende Schritte
---------------------------------------------

.. class:: incremental impressive

1. Informationsgewinnung zur Gewinnung aller relevanten Informationen über das Produkt
2. Modellierung mit dem Ziel der (Wieder-)Gewinnung eines (abstrakten) Modells der relevanten Funktionalität.
3. Überprüfung (:eng:`review`) des Modells auf seine Richtigkeit und Vollständigkeit.


Informationsgewinnung - Beispiel
----------------------------------

Gegeben sei eine App zum Ver- und Entschlüsseln von Dateien sowie ein paar verschlüsselte Dateien. Mögliche erste Schritte vor der Analyse von Binärcode:

.. container:: stack

    .. container:: layer incremental
    
       - Die ausführbare Datei ggf. mit ``file`` überprüfen (z. B. wie kompiliert und für welches Betriebssystem und Architektur)
    
        Beispiel:

        .. code:: bash
        
            $ file /usr/bin/openssl
            /usr/bin/openssl: Mach-O universal binary with 2 archi...
            /usr/bin/openssl (for architecture x86_64):	Mach-O 64-bit
            /usr/bin/openssl (for architecture arm64e):	Mach-O 64-bit

    .. container:: layer incremental

       - Die Dateien mit einem (guten) Hexeditor auf Auffälligkeiten untersuchen.

         .. image:: pictures/hexeditor.png 
            :alt: Hexeditor mit Dateninterpretation
            :align: center
            :height: 600px

    .. container:: layer incremental warning

        Die Datei auf bekannte Viren und Malware überprüfen.

    .. container:: layer incremental
    
      - Eine Datei mit einem bekannten Inhalt verschlüsseln und danach vergleichen.
  
        Ist die Datei gleich groß? 
  
           Falls ja, dann werden keine Metainformationen gespeichert und das Passwort kann (ggf.) nicht (leicht) verifiziert werden.

    .. container:: layer incremental

      - Eine Datei mit verschiedenen Passworten verschlüsseln.

        Sind die Dateien gleich? 

           Falls ja, dann wäre die Verschlüsselung komplett nutzlos und es gilt nur noch den konstanten Schlüssel zu finden.
 
        Gibt es Gemeinsamkeiten? 
   
           Falls ja, dann wäre es möglich, dass das Passwort (gehasht) in der Datei gespeichert wird.

    .. container:: layer incremental

       - Eine Datei mit einem wohldefinierten Muster verschlüsseln, um ggf. den "Mode of Operation" (insbesondere ECB) zu identifizieren.

    .. container:: layer incremental

       - Mehrere verschiedene Dateien mit dem gleichen Passwort verschlüsseln

         Gibt es Gemeinsamkeiten? 
         
           Falls ja, dann wäre es möglich, dass die entsprechenden Teile direkt vom Passwort abgeleitet werden/damit verschlüsselt werden.
  
    .. container:: layer incremental

       - ...


Rechtliche Aspekte des Reverse Engineering
-------------------------------------------

.. class:: incremental

- **unterschiedliche nationale Gesetzgebung**
- Rechtslage in Deutschland hat sich mehrfach geändert
- Umgehung von Kopierschutzmechanismen ist im Allgemeinen verboten
- Lizenz verbietet das Reverse Engineering häufig

.. admonition:: Warnung
    :class: incremental warning 
    
    Bevor Sie Reverse Engineering von Systemen betreiben, erkundigen sie sich erst über mögliche rechtliche Konsequenzen.


.. class:: new-section transition-scale

Software Reverse Engineering
--------------------------------

Ansätze
-----------

:statische Analyse: Studieren des Programms ohne es auszuführen; typischerweise mittels eines Disassemblers oder eines Decompilers.

.. class:: incremental 

:dynamische Analyse: Ausführen des Programms; typischerweise unter Verwendung eines Debuggers oder eines instrumentations Frameworks (z. B. `Frida <https://frida.re>`__).

.. class:: incremental 

:hybride Analyse: Kombination aus statischer und dynamischer Analyse.

    Ansätze wie `Unicorn <https://www.unicorn-engine.org>`__, welches auf `QEmu <https://www.qemu.org>`__ aufbaut, erlaubt zum Beispiel die Ausführung von (Teilen von) Binärcode auf einer anderen Architektur als der des Hosts.
    
    Ein Beispiel wäre die Ausführung einer Methode, die im Code verschlüsselte hinterlegte Strings entschlüsselt (:eng:`deobfuscation`), um die Analyse zu vereinfachen.

.. container:: incremental 

    Ggf. müssen für Teile des Codes, die die Hostfunktionalität nutzen, Stubs/Mocks bereitgestellt werden.


Disassembler
-------------

Überführt (maschinenlesbaren) Binärcode in Assemblercode

Beispiel:

- objdump -d 
- gdb
- radare
- javap (für Java) 

.. admonition:: Hinweis
    :class: incremental small

    Für einfache Programme ist es häufig möglich direkt den gesamten Assemblercode mittels der entsprechenden Werkzeuge zu erhalten. Im Falle komplexer Binärdateien (z. B. im ELF (Linux) und PE (Windows) Format) gilt dies nicht und erfordert ggf. manuelle Unterstützung zum Beispiel durch das Markieren von Methodenanfängen. 
    
    Im Fall von Java ``.class`` ist die Disassemblierung immer möglich. 


Decompiler
-------------

Überführt (maschinenlesbarem) Binärcode bestmöglich in Hochsprache (meist C oder Java). Eine *kleine* Auswahl von verfügbaren Werkzeugen:

- Hex-Rays IDAPro (kommerziell)
- `Ghidra <https://ghidra-sre.org/>`__ (unterstützt fast jede Platform; die Ergebnisse sind sehr unterschiedlich)
- JadX (Androids ``.dex`` Format)
- CFR (Java ``.class`` Dateien)
- IntelliJ
- `decompiler.com <https://decompiler.com>`__

.. container:: supplemental 

    Mittels Decompiler ist es ggf. möglich Code, der zum Beispiel ursprünglich in Kotlin oder Scala geschrieben und für die JVM kompiliert wurde, als Java Code zurückzubekommen. 
    
    Die Ergebnisse sind für Analysezwecke zwar häufig ausreichend gut - von funktionierendem Code jedoch ggf. ((sehr) weit) entfernt.

.. admonition:: Hinweis
    :class: incremental small

    Generell sehr hilfreich, aber gleichzeitig auch sehr fehlerbehaftet. Vieles, dass im Binärcode möglich ist, hat auf Sourcecode Ebene keine Entsprechung. Zum Beispiel unterstützt Java Bytecode beliebige Sprünge. Solche, die  

cfr Decompiler
---------------

.. image:: pictures/cfr.png 
    :alt: The CFR Decompiler (Java)
    :align: center
    :height: 1050px



JD Decompiler
---------------

.. container:: two-columns 

    .. container:: column no-separator
    
        .. image:: pictures/jd.png 
            :alt: The JD Decompiler (Java)
            :width: 875px

    .. container:: column no-separator incremental small
    
        .. figure:: pictures/jd-excerpt.png 
            :class: picture
            :width: 875px

            Beispiel fehlgeschlagener Dekompilierung


JDec Decompiler
---------------

.. image:: pictures/jdec.png 
    :alt: The JDec Decompiler (Java)
    :align: center
    :height: 1050px



Debugger
-----------

Dient der schrittweisen Ausführung des zu analysierenden Codes oder Hardware; ermöglichen zum Beispiel Speicherinspektion und Manipulation.

- gdb
- lldb
- x64dbg (Windows, Open-Source)
- jdb (Java Debugger)

.. container:: supplemental 

    Auch für das Debuggen von Hardware gibt es entsprechende Werkzeuge, z. B.
    `Lauterbach Hardware Debugger <https://www.lauterbach.com>`__
    Mittels solcher Werkzeuge ist es möglich die Ausführung von Hardware Schritt für Schritt (:eng:`single step mode``) zu verfolgen und den Zustand der Hardware (Speicher und Register) zu inspizieren. Dies erfordert (z.Bsp.) eine JTAG Schnittstelle.


.. class:: new-section transition-fade

Erschwerung des Reverse Engineering
------------------------------------


Obfuscation (:ger:`Verschleierung`)
------------------------------------

.. class:: incremental

- Techniken, die dazu dienen das Reverse Engineering zu erschweren.
- Häufig eingesetzt ...

  .. class:: incremental 

  -  von Malware
  -  Adware (im Kontext von Android ein häufig beobachtetes Phänomen)
  -  zum Schutz geistigen Eigentums
  -  für DRM / Durchsetzung von Kopierrechten
  -  zur Prävention von :ger-quote:`Cheating` (insbesondere im Umfeld von Online Games)
  -  Wenn das Programm als Source Code vertrieben wird (JavaScript)

- Arbeiten auf Quellcode oder Maschinencode Ebene
- Grenze zwischen *Code Minimization*, *Code Optimization* und *Code Obfuscation* ist fließend.
- Mögliche Werkzeuge (ohne Wertung der Qualität/Effektivität):
  
  - [Java] Proguard / Dexguard
  - [C/C++] `Star Force <https://www.star-force.com/products/starforce-crypto/>`__ 

.. container:: supplemental 

    Gerade im Umfeld von klassischen *Binaries* für Windows, Mac und Linux erhöhen Compiler Optimierungen, z. B. von C/C++ und Rust Compilern (``-O2 / -O3``), bereits den Aufwand, der notwendig ist den Code zu verstehen, erheblich.

    .. admonition:: Hinweis

        Einen ambitionierten und entsprechend ausgestatteten Angreifer wird **Code Obfuscation** bremsen, aber sicher nicht vollständig ausbremsen und das Vorhaben verteilen.


Obfuscation - Techniken (Auszug)
------------------------------------

.. class:: incremental

- :minor:`entfernen aller Debug-Informationen`
- das Kürzen aller möglichen Namen (insbesondere Methoden und Klassennamen)
- das Verschleiern von Konstanten durch den Einsatz vermeintlich komplexer Berechnungen zu deren Initialisierung.

    .. code:: java
        
        ~(((int)Math.PI) ^ Integer.MAX_VALUE >> 16)+Short.MAX_VALUE

    .. class:: incremental
        
        .. code:: java
        
            = 2

.. container:: supplemental 

   Obfuscation auf Source Code Ebene: 
   `International Obfuscated C Code Contest <https://www.ioccc.org/>`__


Obfuscation - Techniken (Auszug)
------------------------------------

.. class:: incremental

- die Verwendung von Unicode Codepoints für Strings oder die Verschleierung von Strings mittels `rot13 <https://cryptii.com/pipes/rot13-decoder>`__ Verschlüsselung.
  
  .. code:: C
    
     /* ??? */ printf("\x48""e\154l\x6F"" \127o\x72""l\144!");

  .. class:: incremental

    .. code:: C
    
        /*  =  */ printf("Hello World!");

- das Umstellen von Instruktionen, um das Dekompilieren zu erschweren
- das Hinzufügen von totem Code

- den relevanten Teil der Anwendung komprimieren und verschlüsseln und erst bei Verwendung entpacken und entschlüsseln.
- ...

.. container:: supplemental 

   **Umstellen von Instruktionen**
    
   Das Umstellen von Instruktionen erschwert die Analyse, da viele Werkzeuge zum Dekompilieren auf die Erkennung von bestimmten Mustern im Code angewiesen sind und ansonsten nur sehr generischen (Spagetti Code) oder gar unsinnigen Code zurückgeben.

   **Verschleierung von Strings**

   Das Verschleiern von Strings kann insbesondere das Reversen von Binärcode erschweren, da ein Angreifer häufig :ger-quote:`nur` an einer ganz bestimmten Funktionalität interessiert ist und dann Strings ggf. einen sehr guten Einstiegspunkt für die weitergehende Analyse bieten. 
   
   Stellen Sie sich eine komplexe Java Anwendung vor, in der alle Namen von Klassen, Methoden und Attributen durch einzelne oder kurze Sequenzen von Buchstaben ersetzt wurden und sie suchen danach wie von der Anwendung Passworte verarbeitet werden. Handelt es sich um eine GUI Anwendung, dann wäre zum Beispiel die Suche nach Text, der in den Dialogen vorkommt (z. B. ``"Password"``) z. B. ein sehr guter Einstiegspunkt.


.. class:: new-section transition-fade

Eine sehr kurz Einführung in Java Bytecode
-----------------------------------------------

Die Java Virtual Machine
------------------------------------------------- 

.. class:: incremental

- **Java Bytecode** ist die Sprache, in der Java (oder Scala, Kotlin, Groovy, ...) Programme auf der Java Virtual Machine (JVM) [#]_ ausgeführt werden.
- :minor:`In den meisten Fällen arbeiten Java Decompiler so gut, dass ein tiefgehendes Verständnis von Java Bytecode selten notwendig ist.`
- Java Bytecode kann, muss aber nicht interpretiert werden. (z. B. können virtuelle Methodenaufrufe in Java schneller sein als in C++)


.. [#] `Java Bytecode Spezifikation <https://docs.oracle.com/javase/specs/jvms/se21/html/index.html>`__


Java Bytecode - stackbasierte virtuelle Maschine
------------------------------------------------- 

.. container:: smaller

   Die JVM ist eine stackbasierte virtuelle Maschine; die getypten Operanden eines Befehls werden auf einem Stack abgelegt und die Operationen arbeiten auf den obersten Elementen des Stacks. Jeder Thread hat seinen eigenen Stack.
   
        .. container:: two-columns footnotesize incremental
    
            .. container:: column 
        
                Instruktion

                .. code:: java

                    nop
                    bipush 100               → int

                    bipush  50               → int


                    iadd        ← 2 ⨉ int    → int


            
            .. container:: column incremental
                
                Stack

                .. code:: java

                    └─────┘
                    │ 100 │
                    └─────┘
                    │  50 │
                    │ 100 │
                    └─────┘
                    │ 150 │
                    └─────┘

   - Die benötigte Höhe des Stacks wird vom Compiler berechnet und von der JVM überprüft.


Java Bytecode - Methodenaufrufe und lokale Variablen
---------------------------------------------------------

.. class:: incremental

- Die Java Virtual Machine verwendet lokale Variablen zur Übergabe von Parametern beim Methodenaufruf. 
- Beim Aufruf von *Klassenmethoden* (``static``) werden alle Parameter in aufeinanderfolgenden lokalen Variablen übergeben, beginnend mit der lokalen Variable 0. 
  d. h. in der aufrufenden Methode werden die Parameter vom Stack geholt und in lokalen Variablen gespeichert.
- Beim Aufruf von *Instanzmethoden* wird die lokale Variable 0 dazu verwendet, um die Referenz (``this``) auf das Objekt zu übergeben, auf dem die Instanzmethode aufgerufen wird. 
  Anschließend werden alle Parameter in aufeinanderfolgenden lokalen Variablen übergeben, beginnend mit der lokalen Variable 1.
- Die Anzahl der benötigten lokalen Variablen wird vom Compiler berechnet und von der JVM überprüft.


.. class:: small

Beispiel: *Default Constructor* In Java Bytecode
-------------------------------------------------

Ein *Constructor* welcher keine expliziten Parameter hat und nur den super Konstruktor aufruft.

.. code:: java

    // Method descriptor #8 ()V
    // Stack: 1, Locals: 1
    public Main();
        0  aload_0 [this]
        1  invokespecial java.lang.Object() [31]
        4  return

Die Zeilennummern und die Informationen über die lokalen Variablen ist optional und wird nur für Debugging Zwecke benötigt.

.. code:: java
    
      Line numbers:         [pc: 0, line: 9]
      Local variable table: [pc: 0, pc: 5]  local: this 
                                            index: 0 
                                            type:  de.dhbw.simplesecurepp.Main

.. container:: supplemental 

    Es gibt weitere Metainformationen, die :ger-quote:`nur` für Debugging-Zwecke benötigt werden, z. B. Informationen über die ursprünglich Quelle des Codes oder die sogenannte "Local Variable Type Table" in Hinblick auf generische Typinformationen. Solche Informationen werden häufig vor Auslieferung entfernt bzw. nicht hineinkompiliert. 


Beispiel: Aufruf einer komplexeren Methode
-------------------------------------------

.. code:: java
    :class: small
        
    // Method descriptor #36 ([Ljava/lang/String;)V
    // Stack: 5, Locals: 8
    public static void main(java.lang.String[] args) throws ...;
        0  aload_0 [args]
        1  arraylength
        2  iconst_2
        3  if_icmpeq 74                // integer comparison for equality
        6  getstatic java.lang.System.err : java.io.PrintStream 
        9  ldc <String "SimpleSecure++">
        11  invokevirtual java.io.PrintStream.println(java.lang.String) : void 
        ...



.. class:: new-section transition-scale

Verschlüsselung von Daten
----------------------------------------------


Alternativen zur Speicherung von Passwörtern
---------------------------------------------

In einigen Anwendungsgebieten ist es möglich auf das explizite Speichern von Passwörtern ganz zu verzichten [*]_. 

.. container:: incremental 

    Stattdessen wird z. B. einfach versucht das Ziel zu entschlüsseln und danach evaluiert ob das Passwort (vermutlich) das Richtige war. 

.. container:: incremental 

    Kann darauf verzichtet werden zu überprüfen ob das Passwort korrekt war, dann sind keine Metainformationen notwendig und die verschlüsselte Datei kann genau so groß sein wie die unverschlüsselte Datei.

.. [*] Bei einer Verschlüsselung mit OpenSSL wird das Passwort nicht gespeichert.



schematische Darstellung der Verschlüsselung von Containern (z. B., Veracrypt)
-------------------------------------------------------------------------------


.. image:: graffles/verschluesselung_von_veracrypt.svg
    :alt: Schematische Darstellung von Containern.
    :align: center
    :width: 1800px



Generische Dateiverschlüsselung ohne explizite Speicherung des Passworts
-------------------------------------------------------------------------



.. image:: graffles/generische_dateiverschluesselung.svg
    :alt: Beispiehafte Verschlüsselung von Containern.
    :align: center
    :width: 1800px




.. class:: center-child-elements

\
--------------------------------------

.. admonition:: Bleibe fokussiert! 
    :class: warning incremental
   
    Analysiere nur was notwendig ist.
