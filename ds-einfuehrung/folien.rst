.. meta:: 
    :author: Michael Eichberg
    :keywords: "Verteilte Systeme"
    :description lang=de: Verteilte Systeme
    :id: lecture-ds-einfuehrung
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



Einführung in verteilte Systeme
================================================

Ein weitgefasster Überblick über verteilte Systeme.

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: |date|
    :Folien: 
        https://delors.github.io/ds-einfuehrung/folien.rst.html :raw-html:`<br>`
        https://delors.github.io/ds-einfuehrung/folien.rst.html.pdf
    :Fehler auf Folien melden:
        https://github.com/Delors/delors.github.io bzw. https://github.com/Delors/delors.github.io/issues



.. container:: footer-left tiny incremental

    Dieser Foliensatz basiert auf Folien von:
    
    (a) Maarten van Steen (Veröffentlicht zum Buch *Distributed Systems*)

    (b) Henning Pagnia (basierend auf seiner Vorlesung *Verteilte Systeme*). 

    Alle Fehler sind meine eigenen.



Verteilte Systeme - W3WI_110.2
----------------------------------

:Modul - W3WI_110: 

    - Entwicklung verteilter Systeme (W3WI_110) 
    - 5 ECTS 
  
:Umfang der Vorlesung: 
    - 22 Vorlesungsstunden (verteilt auf 5 Termine) 
    - 38 Stunden Selbststudium

:Prüfungsleistung: 

    - Portfolio (mit insgesamt 120 Punkten für das Modul W3WI_110)
    - 48 Punkte in verteilte Systeme (22/55)*120 = 0.4*120 = 48
  
      -  12 Punkte Präsentationen in Teams 
      -  36 Punkte Programmieraufgabe in Teams



Verteilte Systeme - *Middleware Lösungen*
------------------------------------------

Für die Präsentationen stehen die folgenden Themen stehen zur Auswahl:

1. Apache Kafka
2. Apache Zookeeper
3. (Scala) Akka
4. Rabbit MQ
5. (Twitter/X) Finagle
6. Apache Hive
7. Apache Cassandra
8. Apache Spark
9.  Hadoop HDFS
10. Hadoop Yarn/MapReduce
11. Apache Mahout
12. `Zeebe <https://github.com/camunda/zeebe>`__

.. container:: supplemental

    .. rubric:: Kerninhalte gem. MHB

    - Terminologie, Konzepte, Architekturen, Anforderungsprofile und Architekturmodelle für verteilte Systeme
    - Entwurfs- und Implementierungsansätze
    - Vergleich unterschiedlicher Middleware-Konzepte
    - Synchrone und asynchrone Kommunikation, entfernter Methodenaufruf 
    - Asynchrone Kommunikation und Messaging-Systeme
    - Sicherheitsaspekte in verteilten Systemen



  Die Präsentationen sind am letzten Termin zu halten. Die Präsentationen sollen sich insbesondere mit den Kerninhalten der Vorlesung beschäftigen und insbesondere konzeptioneller Natur sein. D. h. nach der Darstellung des Anwendungszweckes gilt es  die Architektur darzustellen, wie mit Fehlern umgegangen wird, welche Services angeboten werden, welche Garantien/Sicherheitsaspekte umgesetzt werden, wie wird die Skalierbarkeit erreicht, etc. 



.. class:: padding-none no-title transition-scale

Terminologie verteilter Systeme
----------------------------------

.. image:: images/modern_software_architecture-tag_cloud.png
    :width: 100%
    :align: center



Empfohlene Literatur
---------------------

.. image:: screenshots/distributed-systems.net.png
    :height: 1000px
    :align: center

.. container:: supplemental

    Ergänzend bzw. für interessierte Studierende:

    .. image:: screenshots/microservices.jpg
        :height: 1000px
        :align: center
        :class: box-shadow 



Empfohlener Podcast
---------------------

.. image:: screenshots/se-radio.net.png
    :height: 1000px
    :align: center
    
    

Verteilt vs. Dezentralisiert (:eng:`Distributed vs Decentralized`)
-------------------------------------------------------------------

.. image:: images/distributed-vs-decentralized.svg
    :width: 100%
    :align: center
    :class: margin-bottom-1em

.. container:: question  

    Wann wird ein dezentralisiertes System zu einem verteilten System?



Verteilte Systeme
------------------

.. admonition:: Zwei Ansichten zur Realisierung verteilter Systeme
    :class: definition

    - **Integrative Sichtweise**: Verbindung bestehender vernetzter Computersysteme zu einem größeren System.
    - **Expansive Sichtweise**: ein bestehendes vernetztes Computersystem wird um zusätzliche Computer erweitert.

.. admonition:: Zwei Definitionen
    :class: definition in

    - Ein dezentrales System ist ein vernetztes Computersystem, in dem Prozesse und Ressourcen *notwendigerweise* über mehrere Computer verteilt sind.
    - Ein verteiltes System ist ein vernetztes Computersystem, bei dem Prozesse und Ressourcen *hinreichend* über mehrere Computer verteilt sind.



Häufige Missverständnisse bzgl. zentralisierter Systeme
--------------------------------------------------------

.. class:: incremental 

1. **Zentralisierte Lösungen lassen sich nicht skalieren**
 
    .. container:: scriptsize
   
      
        Es gilt zwischen logischer und physischer Zentralisierung zu unterscheiden. Zum Beispiel ist das *Domain Name System*:

        - logisch zentralisiert
        - physisch (massiv) verteilt
        - dezentralisiert über mehrere Organisationen
  
2. **Zentralisierte Lösungen haben einen Single Point of Failure**

    .. container:: scriptsize
   
      
        Im Allgemeinen nicht zutreffend (z. B. DNS). 
        
        Ein einzelne mögliche Fehlerquelle ist weiterhin oft:

        - leichter zu verwalten
        - einfacher robuster zu machen

.. container:: supplemental 
    
    .. admonition:: Warnung 
        :class: warning

        Es gibt viele, schlecht begründete Missverständnisse in Bezug auf, z. B. Skalierbarkeit, Fehlertoleranz oder Sicherheit. Wir müssen Fähigkeiten entwickeln, mit denen verteilte Systeme leicht verstanden werden können, um solche Missverständnisse zu vermeiden.



Sichtweisen auf verteilte Systeme
----------------------------------

**Verteilte Systeme sind komplex.**

.. class:: incremental

- Welche Architekturen und :ger-quote:`Architekturellen Stile` (:eng:`architectural styles`) gibt es?
- Prozesse: Welche Art von Prozessen gibt es und wie sind deren Beziehungen?
- Kommunikation: Welche Möglichkeiten zum Austausch von Daten gibt es?
- Koordinierung: Wie erfolgt die Koordinierung der beteiligten Systeme?
- Benennung: Wie identifiziert man Ressourcen?
- Konsistenz und Replikation: Welche Tradeoffs müssen in Hinblick auf die Konsistenz der Daten, der Replikation derselben und der Performance getroffen werden?
- Fehlertoleranz: Wie kann eine Aufrechterhaltung des Betriebs auch bei Teilausfällen gewährleistet werden?
- Sicherheit: Wie kann der autorisierte Zugriff auf Ressourcen gewährleistet werden?



Entwurfsziele verteilter Systeme
----------------------------------

.. class:: incremental

- Unterstützung der gemeinsamen Nutzung von Ressourcen 
- Verteilungstransparenz (:eng:`Distribution Transparency`)
- Offenheit
- Skalierbarkeit



Gemeinsame Nutzung von Ressourcen
----------------------------------

Kanonische Beispiele:

- Cloud-basierter gemeinsamer Speicher und Dateien
- Peer-to-Peer-unterstütztes Multimedia-Streaming
- Gemeinsame E-Mail-Dienste (z. B. ausgelagerte E-Mail-Systeme)
- Gemeinsames Webhosting (z. B. *Content Distribution Networks*)



Verteilungstransparenz (:eng:`Distribution Transparency`)
----------------------------------------------------------

.. admonition:: Definition

    Transparenz beschreibt die Eigenschaft, dass ein verteiltes System versucht, die Tatsache zu verbergen, dass seine Prozesse und Ressourcen physisch auf mehrere Computer verteilt sind, die möglicherweise durch große Entfernungen voneinander getrennt sind.

.. container:: incremental

  Die Verteilungstransparenz wird durch viele verschiedene Techniken von der so genannten *Middleware* realisiert - einer Schicht zwischen Anwendungen und Betriebssystemen.



Aspekte der Verteilungstransparenz 
----------------------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover smaller

    Datenzugriff, Verbergen von Unterschieden in der Datendarstellung und der Art des Zugriffs auf ein lokales bzw. entferntes Objekt
    Ort der Datenhaltung, "Verbergen, wo sich ein Objekt befindet"
    Verschieben, "Verbergen, dass ein Objekt während der Verwendung an einen anderen Ort verschoben werden kann"
    Migration, "Verbergen, dass ein Objekt an einen anderen Ort verschoben werden kann" 
    Replikation, "Verbergen, dass ein Objekt repliziert wird"
    Nebenläufigkeit, "Verbergen, dass ein Objekt von mehreren unabhängigen Benutzern gemeinsam genutzt werden kann"
    Fehlertransparenz, Verbergen des Ausfalls und der Wiederherstellung eines Objekts


.. container:: supplemental

    Datendarstellung: Big-Endian vs. Little-Endian; ASCII vs. Iso-Latin 8859-1 vs. UTF-8


Grad der erreichbaren Verteilungstransparenz
--------------------------------------------

.. container:: assessment

    Eine vollständige Verteilungstransparenz ist nicht erreichbar. 

Jedoch kann auch eine sehr hohe Verteilungstransparenz bereits hohe Kosten nach sich ziehen.

.. class:: incremental smaller

- Es gibt Kommunikationslatenzen, die nicht verborgen werden können.
- Es ist (theoretisch und praktisch) unmöglich, Ausfälle von Netzen und Knoten vollständig zu verbergen.
- Man kann einen langsamen Computer nicht von einem ausgefallenen Computer unterscheiden.
- Man kann nie sicher sein, dass ein Server tatsächlich eine Operation durchgeführt hat, bevor er abgestürzt ist.
- Vollständige Transparenz kostet Performance und legt die Verteilung des Systems offen.
  
  - Die Replikate exakt auf dem Stand des Masters zu halten, kostet Zeit 
  - Schreibvorgänge werden zur Fehlertoleranz sofort auf die Festplatte übertragen



Die Verteilung offen zu legen, kann Vorteile bringen
-----------------------------------------------------

- Nutzung von standortbezogenen Diensten (Auffinden von Freunden in der Nähe)
- Beim Umgang mit Benutzern in verschiedenen Zeitzonen
- Wenn es für einen Benutzer einfacher ist, zu verstehen, was vor sich geht (wenn z. B. ein Server lange Zeit nicht antwortet, kann er als ausgefallen gemeldet werden).

.. container:: assessment margin-top-2em

    .. container:: 
    
        Verteilungstransparenz ist ein hehres Ziel, aber oft schwer zu erreichen, und häufig auch nicht erstrebenswert. 



Offene verteilte Systeme
----------------------------------

.. admonition:: Definition

    Ein offenes verteiltes System bietet Komponenten an, die leicht von anderen Systemen verwendet oder in andere Systeme integriert werden können. Ein offenes verteiltes System besteht selbst oft aus Komponenten, die von woanders stammen.

.. container:: incremental smaller margin-top-1em

    Offene verteilte Systeme müssen in der Lage sein, mit Diensten anderer (offener) Systeme zu interagieren, unabhängig von der zugrunde liegenden Umgebung:

    - Sie sollten wohl-definierte Schnittstellen korrekt realisieren
    - Sie sollten leicht mit anderen Systemen interagieren können
    - Sie sollten die Portabilität von Anwendungen unterstützen 
    - Sie sollten leicht erweiterbar sein



Vorgaben/Richtlinien vs. Umsetzungen 
------------------------------------------------------------------------------

.. container:: minor

    (:eng:`Policies vs. Mechanisms`)


.. rubric:: Richtlinien für die Umsetzung von Offenheit

.. class:: incremental

- Welchen Grad an Konsistenz benötigen wir für Daten im Client-Cache?
- Welche Operationen erlauben wir heruntergeladenem Code?
- Welche QoS-Anforderungen passen wir angesichts schwankender Bandbreiten an? 
- Welchen Grad an Geheimhaltung benötigen wir für die Kommunikation?

.. class:: incremental

.. rubric:: Mechanismen bzgl. der Umsetzung von Offenheit

.. class:: incremental

- Ermöglichung der (dynamischen) Einstellung von Caching-Richtlinien
- Unterstützung verschiedener Vertrauensstufen für mobilen Code
- Bereitstellung einstellbarer QoS-Parameter pro Datenstrom 
- Angebot verschiedener Verschlüsselungsalgorithmen


.. container:: supplemental

    Die harte Kodierung von Richtlinien vereinfacht oft die Verwaltung und reduziert die Komplexität des Systems. Hat jedoch den Preis geringerer Flexibilität.



Verlässlichkeit verteilter Systeme 
------------------------------------------------------------

.. container:: minor

    (:eng:`Dependability`)

.. admonition:: Abhängigkeiten
    :class: foundations
    
    Eine **Komponente**\ [#]_ stellt ihren **Clients** **Dienste** zur Verfügung. Dafür kann die Komponente jedoch wiederum Dienste von anderen Komponenten benötigen und somit ist eine Komponente  von einer anderen Komponente abhängig (:eng:`depend`).


    Eine Komponente :math:`C` hängt von :math:`C^*` ab, wenn die Korrektheit des Verhaltens von :math:`C` von der Korrektheit des Verhaltens von :math:`C^*` abhängt. 

.. [#] Komponenten seien Prozesse oder Kanäle.


Anforderungen an die Verlässlichkeit verteilter Systeme
------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover
    :header: "Anforderung", "Beschreibung"

    "Verfügbarkeit", "Das System ist nutzbar."
    "Zuverlässigkeit", "Kontinuität der korrekten Leistungserbringung."
    "Sicherheit 
    (:eng:`Safety`\ [#]_)", "Niedrige Wahrscheinlichkeit für ein katastrophales Ereignis"
    "Wartbarkeit", "Wie leicht kann ein fehlgeschlagenes System wiederhergestellt werden?"

.. [#] :eng:`Safety` und :eng:`Security` werden beide im Deutschen gleich mit Sicherheit übersetzt und sind daher leicht zu verwechseln. :eng:`Safety` bezieht sich auf die Sicherheit von Personen und Sachen, während :eng:`Security` sich auf die Sicherheit von Daten und Informationen bezieht.


Zuverlässigkeit (:eng:`Reliability`) vs. Verfügbarkeit (:eng:`Availability`) in verteilten Systemen
--------------------------------------------------------------------------------------------------------------

.. rubric:: Verlässlichkeit :math:`R(t)` der Komponente :math:`C`

Bedingte Wahrscheinlichkeit, dass :math:`C` während :math:`[0,t)` korrekt funktioniert hat, wenn :math:`C` zum Zeitpunkt :math:`T = 0` korrekt funktionierte.

.. rubric:: Traditionelle Metriken

- Mittlere Zeit bis zum Versagen (:eng:`Mean Time to Failure` (MTTF)): Die durchschnittliche Zeit bis zum Ausfall einer Komponente. 
- Mittlere Zeit bis zur Reparatur (:eng:`Mean Time to Repair` (MTTR)): Die durchschnittliche Zeit, die für die Reparatur einer Komponente benötigt wird.
- Mittlere Zeit zwischen Ausfällen (:eng:`Mean Time Between Failures` (MTBF)): MTTF + MTTR.

.. container:: supplemental

    - Zuverlässigkeit: Wie wahrscheinlich ist es, dass ein System *korrekt* arbeitet?
    - Verfügbarkeit: Wie wahrscheinlich ist es, dass ein System zu einem bestimmten Zeitpunkt verfügbar ist?



.. class:: integrated-exercise smaller-slide-title

Übung - Verfügbarkeit und Ausfallwahrscheinlichkeit
------------------------------------------------------

1. Wenn die MTTF einer Komponente 100 Stunden beträgt und die MTTR 10 Stunden beträgt, wie hoch ist dann die MTBF?

.. protected-exercise-solution:: Berechnung des MTBF
   :class: smaller
    
    .. math::
        MTBF = MTTF + MTTR = 100 + 10 = 110

2. Gegeben sei ein größeres verteiltes System bestehend aus 500 unabhängigen Rechnern, die auch unabhängig voneinander ausfallen. Im Mittel ist jeder Rechner innerhalb von zwei Tagen zwölf Stunden lang nicht erreichbar.

   (a) Bestimmen Sie die Intaktwahrscheinlichkeit eines einzelnen Rechners.
   (b) Ein Datensatz ist aus Gründen der Fehlertoleranz auf drei Rechnern identisch repliziert gespeichert. Wie hoch ist seine mittlere Zugriffsverfügbarkeit beim Lesen?
   (c) Auf wie vielen Rechnern müssen Sie identische Kopien dieses Datensatzes speichern, damit die mittlere Zugriffsverfügbarkeit beim Lesen bei 99,999 % liegt 
   (d) Für wie viele Minuten im Jahr ist im Mittel bei einer Verfügbarkeit von 99,999 % *kein Lesen des Datensatzes* möglich?

.. protected-exercise-solution:: Ausfallwahrscheinlichkeit

    (a) Die Verfügbarkeit eines einzelnen Rechners beträgt p = 36h/48h = 0,75 (MTBF = 36H, MTTR = 12H)
    (b) Die mittlere Zugriffsverfügbarkeit (für :math:`p = 0.75`) beim Lesen beträgt :math:`1 - (1 - p)^3 = 0,984375`; :math:`(1-p)` ist die Ausfallwahrscheinlichkeit.
    (c) (Erinnerung: :math:`log_a(u^v) = v \cdot log_a(u)`).
        
        Wahrscheinlichkeit, dass alle gleichzeitig ausfallen muss kleiner(gleich) der erlaubten Nichtverfügbarkeit sein:  :math:`(1-p)^x \leq (1-0,99999) \Leftrightarrow x \cdot log(1-p) \geq log(1-0,99999)`

        :math:`\Rightarrow x \geq log(1-0,99999)/log(1-p) \approx 8,3`
        
        Die Anzahl der Rechner, auf denen der Datensatz repliziert werden muss, beträgt :math:`\lceil \frac{log(1-0,99999)}{log(1-p)} \rceil = 9`




Sicherheit in verteilten Systemen - Schutzziele
-------------------------------------------------

.. container:: assessment

    Ein verteiltes System, das nicht sicher ist, ist nicht verlässlich.

Grundlegende Schutzziele:

:Vertraulichkeit: Informationen werden nur an autorisierte Parteien weitergegeben.
:Integrität: Änderungen an den Werten eines Systems dürfen nur auf autorisierte Weise vorgenommen werden können.


Sicherheit in verteilten Systemen - Autorisierung, Authentifizierung, Vertrauen
-------------------------------------------------------------------------------------

.. class:: incremental

:Authentifizierung `Authentication`:eng:: Prozess zur Überprüfung der Korrektheit einer behaupteten Identität.

.. class:: incremental

:Autorisierung `Authorization`:eng:: Verfügt eine identifizierte Einheit über die richtigen Zugriffsrechte?

.. class:: incremental

:Vertrauen `Trust`:eng:: Eine Komponente kann sich sicher sein, dass eine andere Komponente bestimmte Handlungen gemäß den Erwartungen ausführt.



Sicherheit - Verschlüsselung und Signaturen
---------------------------------------------

Es geht im Wesentlichen um das Ver- und Entschlüsseln von Daten (:math:`X`) mit Hilfe von Schlüsseln.

.. container:: stack

    .. container:: layer

        :math:`E(K,X)` bedeutet, dass wir die Nachricht X mit dem Schlüssel :math:`K`  verschlüsseln (:eng:`encryption`). 
        
        :math:`D(K,X)` bezeichnet die Umkehrfunktion, die die Daten wieder entschlüsselt (:eng:`decryption`).


    .. container:: layer incremental

        .. rubric:: Symmetrische Verschlüsselung

        Der Schlüssel zur Verschlüsselung ist identisch mit dem Schlüssel zur Entschlüsselung (:eng:`decryption` (:math:`D`)).

        .. math::
            X = D(K,E(K,X)) 
    
    .. container:: layer incremental

        .. rubric:: Asymmetrische Verschlüsselung

        Wir unterscheiden zwischen einem privaten (:math:`PR`) und einem öffentlichen Schlüssel (:math:`PU`) (:math:`PU \neq PR`). Der private Schlüssel ist immer geheim zu halten.
        
        .. container:: stack

            .. container:: layer

                **Verschlüsselung von Nachrichten**
                
                Alice sendet eine Nachricht an Bob und nutzt dazu den öffentlichen Schlüssel von Bob.

                .. math::
                    Y = E(PU_{Bob},X) \\
                    X = D(PR_{Bob},Y) 

            .. container:: layer incremental

                **Signierung von Nachrichten**

                Alice :ger-quote:`signiert` (:math:`S`) eine Nachricht mit ihrem privaten Schlüssel.

                .. math::
                    Y = E(PR_{Alice},X) \\
                    X = D(PU_{Alice},Y)



Sicherheit - Sicheres Hashing (:eng:`Secure Hashing`)
------------------------------------------------------- 

Eine sichere Hash-Funktion :math:`Digest(X)` gibt eine Zeichenkette fester Länge (:math:`H`) zurück.

- Jede Änderung - noch so klein - der Eingabedaten führt zu einer völlig anderen Zeichenkette.
- Bei einem Hash-Wert ist es rechnerisch unmöglich die ursprüngliche Nachricht X basierend auf :math:`Digest(X)` zu finden.


**Signieren von Nachrichten**

Alice signiert eine Nachricht mit ihrem privaten Schlüssel.

.. math::
    Alice: [E(PR_{Alice},H=Digest(X)),X] \\

Bob prüft die Nachricht :math:`X` auf Authentizität:

.. math::
    Bob: D(PU_{Alice},H) \stackrel{?}{=} Digest(X)



.. class:: integrated-exercise

Übung
----------------

Wenn Alice eine mit Bobs öffentlichen Schlüssel verschlüsselte Nachricht an Ihn schickt, welches Sicherheitsproblem kann dann aufkommen?

.. protected-exercise-solution:: Person-in-the-Middle-Angriff
   :class: smaller

    Alice kann nicht sicher sein, dass Ihre Nachricht nicht verfälscht wurde! Jeder, der die Nachricht abfängt kann sie verändern und dann mit Bobs öffentlichen Schlüssel verschlüsseln.



Skalierbarkeit in verteilten Systemen
-----------------------------------------

Wir können mind. drei Arten von Skalierbarkeit unterscheiden:

- Anzahl der Benutzer oder Prozesse (Skalierbarkeit der Größe)
- Maximale Entfernung zwischen den Knoten (geografische Skalierbarkeit) 
- Anzahl der administrativen Domänen (administrative Skalierbarkeit)


Ursachen für Skalierbarkeitsprobleme bei zentralisierten Lösungen:
---------------------------------------------------------------------

- Die Rechenkapazität, da diese begrenzt ist durch die Anzahl CPUs
- Die Speicherkapazität, einschließlich der Übertragungsrate zwischen CPUs und Festplatten 
- Das Netzwerk zwischen dem Benutzer und dem zentralisierten Dienst

.. container:: supplemental

    Die Skalierbarkeit bzgl. der Größe kann oft durch den Einsatz von mehr und leistungsstärkeren Servern, die parallel betrieben werden, erreicht werden.

    Die geografische und administrative Skalierbarkeit ist häufig eine größere Herausforderung.


.. class:: smaller

Formale Analyse der Skalierbarkeit zentralisierter Systeme
------------------------------------------------------------

Ein zentralisierter Dienst kann als einfaches Warteschlangensystem modelliert werden:

.. image:: images/queuing-system.svg
    :width: 75%
    :align: center

Annahmen:

Die Warteschlange hat eine unendliche Kapazität; d. h.die Ankunftsrate der Anfragen wird nicht durch die aktuelle Länge der Warteschlange oder durch das, was gerade bearbeitet wird, beeinflusst.



.. class:: smaller

Formale Analyse der Skalierbarkeit zentralisierter Systeme
------------------------------------------------------------

.. container:: two-columns no-default-width

    .. container:: column no-separator

        - Ankunftsrate der Anfragen: λ *(in Anfragen pro Sekunde)*
        - Verarbeitungskapazität des Services: μ *(in Anfragen pro Sekunde)*

        Anteil der Zeit mit :math:`x` Anfragen im System:

        .. math::

            p_x  = \bigl(1 - \frac{\lambda}{\mu}\bigr)\bigl(\frac{\lambda}{\mu}\bigr)^x

    .. container:: column

        .. image:: images/number_of_requests_in_system.svg
            :width: 1200px

        .. container:: text-align-center tiny

            # Anfragen in Bearbeitung und Warteschlange

            (z. B. ist der Anteil der Zeit in der der Rechner *idle* ist (d. h.es gibt keine Anfragen) 90 %, 60 % und 30 %.)


.. class:: smaller

Formale Analyse der Skalierbarkeit zentralisierter Systeme
------------------------------------------------------------

.. container:: note width-30

    **Hinweis**
    
    :math:`x` = # Anfragen im Sys.

    .. math::
        p_x  = \bigl(1 - \frac{\lambda}{\mu}\bigr)\bigl(\frac{\lambda}{\mu}\bigr)^x
    

:math:`U` ist der Anteil der Zeit, in der ein Dienst ausgelastet ist:

.. math::

    U = \sum_{x > 0} p_x = 1 - p_0 = \frac{\lambda}{\mu} \Rightarrow p_x = (1-U) U^x

Durchschnittliche Anzahl der Anfragen:

.. math::

    \bar{N} = \sum_{x\geq 0} x \cdot p_x 
    = \sum_{x \geq 0} x \cdot (1-U)U^x 
    = (1-U)\sum_{x\geq 0} x\cdot U^x  
    = \frac{(1-U)U}{(1-U)^2} = \frac{U}{1-U}

Durchschnittlicher Durchsatz:

.. math::

    X = \underbrace{U \cdot \mu}_{\mbox{ausgelastet}} + \underbrace{(1-U) \cdot 0}_{\mbox{ungenutzt}} = \frac{\lambda}{\mu} \cdot \mu = \lambda 


.. container:: supplemental

    Für eine `unendliche geometrische Reihe <https://de.wikipedia.org/wiki/Geometrische_Reihe#Konvergenz_und_Wert_der_geometrischen_Reihe>`__ mit dem Quotienten :math:`U` gilt:

    .. math::
        \sum_{k\geq 0} k\cdot U^k  = \frac{U}{(1-U)^2} 

    Darstellung der durchschnittlichen Anzahl an Anfragen im System in Abhängigkeit von der Auslastung :math:`U`:

    .. image:: images/average_number_of_requests_in_system.svg
        :width: 1100px
        :align: center



.. class:: smaller

Formale Analyse der Skalierbarkeit zentralisierter Systeme
------------------------------------------------------------

.. container:: two-columns 

    .. container:: 

        Die Antwortszeit (:eng:`response time`) ist die Gesamtzeit für die Bearbeitung einer Anfrage


        .. math::
            R = \frac{\bar{N}}{X} = \frac{S}{1-U} \Rightarrow \frac{R}{S} = \frac{1}{1-U} 

        mit :math:`S = \frac{1}{\mu}` für die durchschnittliche Servicezeit. 
    
    .. image:: images/response_time.svg
        :width: 100%

- Wenn :math:`U` klein ist, liegt die Antwortzeit nahe bei 1; d. h.eine Anfrage wird sofort bearbeitet.
- Wenn :math:`U` auf 1 ansteigt, kommt das System zum Stillstand. 




Probleme der geografischen Skalierbarkeit
--------------------------------------------

- Viele verteilte Systeme gehen von synchronen Client-Server-Interaktionen aus und dies verhindert einen Übergang vom LAN zum WAN. Die Latenzzeiten können prohibitiv sein, wenn der Client auf eine Anfrage lange warten muss.
- WAN-Verbindungen sind oft von Natur aus unzuverlässig.


Probleme der administrativen Skalierbarkeit
--------------------------------------------

.. container:: assessment

    Widersprüchliche Richtlinien in Bezug auf Nutzung (und damit Bezahlung), Verwaltung und Sicherheit

.. container:: footnotesize

    .. rubric:: Beispiele

    - Grid Computing: gemeinsame Nutzung teurer Ressourcen über verschiedene Domänen hinweg.
    - Gemeinsam genutzte Geräte: Wie kontrolliert, verwaltet und nutzt man ein gemeinsam genutztes Radioteleskop, das als groß angelegtes gemeinsames Sensornetz konstruiert wurde?

    .. rubric:: Ausnahme 

    Verschiedene Peer-to-Peer-Netze [#]_ bei denen Endnutzer zusammenarbeiten und nicht Verwaltungseinheiten:

    - File-Sharing-Systeme (z. B. auf der Grundlage von BitTorrent) 
    - Peer-to-Peer-Telefonie (frühe Versionen von Skype) 

.. [#] :eng:`Peer` ist im hier im Sinne von :ger-quote:`Gleichgestellter` zu verstehen. D. h. wir haben ein Netz von gleichgestellten Rechnern.

Ansätze, um Skalierung zu erreichen
------------------------------------

.. container::

    **Verbergen von Kommunikationslatenzen** durch:

    - Nutzung asynchroner Kommunikation
    - Verwendung separater *Handler* für eingehende Antworten 

    .. container:: assessment

        Dieses Modell ist jedoch nicht immer anwendbar.

.. container:: line-above margin-top-2em padding-top-1em

    **Partitionierung von Daten und Berechnungen über mehrere Rechner.**

    - Verlagerung von Berechnungen auf Clients 
    - Dezentrale Namensgebungsdienste (DNS)
    - Dezentralisierte Informationssysteme (WWW)

Verlagerung von Berechnungen auf Clients
------------------------------------------

.. image:: images/moving-computations.svg
    :height: 1025px
    :align: center


Ansätze, um Skalierung zu erreichen
------------------------------------

**Einsatz von Replikation und Caching, um Kopien von Daten auf verschiedenen Rechnern verfügbar zu machen.**

- replizierte Dateiserver und Datenbanken 
- gespiegelte Websites
- Web-Caches (in Browsern und Proxies) 
- Datei-Caching (auf Server und Client)


Herausforderungen bei der Replikation 
---------------------------------------

.. class:: incremental

- Mehrere Kopien (zwischengespeichert (:eng:`cached`) oder repliziert) führen zwangsläufig zu Inkonsistenzen. Die Änderung einer Kopie führt dazu, dass sich diese Kopie von den anderen unterscheidet.
- Zur Erreichung von Konsistenz ist bei jeder Änderung eine globale Synchronisierung erforderlich.
- Die globale Synchronisierung schließt Lösungen im großen Maßstab aus.

.. container:: supplemental

    Inwieweit Inkonsistenzen toleriert werden können, ist anwendungsspezifisch. Können diese jedoch toleriert werden, dann kann der Bedarf an globaler Synchronisation verringert werden.


Paralleles Rechnen (:eng:`Parallel Computing`)
------------------------------------------------

.. container:: two-columns

    .. container:: column

        Multiprozessor

        .. image:: images/multiprocessor-vs-multicomputer/multiprocessor.svg
            :width: 85%
            :align: center

    .. container:: column 

        Multicomputer

        .. image:: images/multiprocessor-vs-multicomputer/multicomputer.svg
            :width: 85%
            :align: center


.. container:: supplemental

    Das verteilte Hochleistungsrechnen begann mit dem parallelen Rechnen

    **Verteilte Systeme mit gemeinsamem Speicher** (:eng:`Multicomputer with shared memory`) als alternative Architektur haben die Erwartungen nicht erfüllt und sind daher nicht mehr relevant.



Amdahls Gesetz - Grenzen der Skalierbarkeit
-----------------------------------------------------

.. container:: stack

    .. container:: layer

        .. class:: list-with-explanations

        - Lösen von **fixen Problemen** in möglichst kurzer Zeit
        
          (Beispiel: Hochfahren (:eng:`Booten`) eines Rechners. Inwieweit lässt sich durch mehr CPUs/Kerne die Zeit verkürzen?)
        - Es modelliert die erwartete Beschleunigung (Speedup) eines zum Teil parallelisierten/parallelisierbaren Programms relativ zu der nicht-parallelisierten Variante

        .. container:: note width-40 tiny

            **Legende**

            :math:`C` = Anzahl CPUs 

            :math:`P` = Parallelisierungsgrad
            
            :math:`S` = Speedup 

        .. admonition:: Definition 
            
            .. class:: huge

                :math:`S(C) = \frac{1}{(1-P) + \frac{P}{C}}`


Amdahls Gesetz visualisiert - Grenzen der Skalierbarkeit
---------------------------------------------------------

.. image:: images/amdahl.svg
    :height: 900px
    :align: center



Gustafsons Gesetz - Grenzen der Skalierbarkeit
-----------------------------------------------------

.. class:: list-with-explanations

- Lösen von Problemen mit (sehr) großen, sich strukturell wiederholenden Datensätzen in **fixer Zeit**; der serielle Anteil des Programms wird als  konstant angenommen.

  (Beispiel: Erstelle innerhalb der nächsten 24 Stunden die Wettervorhersage für übermorgen. Inwieweit lässt sich durch mehr CPUs/Rechner die Präzision der Vorhersage verbessern?)

Beschleunigung (Speedup) eines parallelisierten Programms relativ zu der nicht-parallelisierten Variante:

.. container:: stack

    .. container:: layer

        .. container:: note width-50 tiny

            **Legende**

            :math:`C` = Anzahl CPUs 

            :math:`P` = Parallelisierungsgrad in Abhängigkeit von der Problemgröße n
            
            :math:`S` = Speedup 
        
        .. admonition:: Definition 
                    
            .. class:: huge

                :math:`S(C) = 1 + P(n) \cdot (C-1)`

    .. container:: layer incremental

        .. admonition:: Beispiel
            :class: tiny 

            Sei der Parallelisierungsgrad ab einer relevanten Problemgröße n 80 %. Dann ergibt sich für 4 CPUs ein Speedup von :math:`(1+0.8*3) = 3.4`, für 8 CPUs ein Speedup von 6.6 und für 16 CPUs ein Speedup von 13.



.. class:: integrated-exercise

Übung
----------------

Sie sind Pentester und versuchen in ein System einzudringen indem Sie die Passwörter der Administratoren angreifen. Momentan setzen Sie dazu 2 Grafikkarten mit je 2048 Compute Units ein. Der serielle Anteil des Angriffs beträgt 10 %. Wie hoch ist der Speedup, den Sie erwarten können, wenn Sie zwei weitere vergleichbare Grafikkarten mit weiteren 2048 Compute Units je GPU hinzufügen?

   Hintergrund: Die Angriffe sind hochgradig parallelisierbar und hängen effektiv von der Anzahl an CUs ab. Die Grafikkarten sind in der Lage, die Angriffe effektiv zu beschleunigen.

.. protected-exercise-solution:: Berechnung des Speedup
   :class: smaller

   Es handelt sich hierbei um ein Problem mit sich strukturell wiederholenden Datensätzen, d. h. Gustafsons Gesetz ist anwendbar. Der serielle Anteil beträgt 10 %, d. h.der Parallelisierungsgrad beträgt 90 %. Der Speedup beträgt dann:

   .. math::

        S(4096) = 1 + 0.9 * 4096 = 3.687,4

        S(2048) = 1 + 0.9 * 2048 = 1844,2

    Das Rechnen mit den GPUs als solches führt somit zu einem etwas geringeren Speedup, da der serielle Anteil des Angriffs noch mehr in Gewicht fällt.

    .. math::

        S(4096) / S(2048) \approx 1.9995 

        S(4) / S(2) \approx 1,9474 



.. class:: smaller

MapReduce - ein Programmiermodell für paralleles Rechnen
----------------------------------------------------------

.. class:: incremental 

- MapReduce ist ein Programmiermodel und eine entsprechende Implementierung (ein Framework entwickelt von Google) zur Verarbeitung sehr großer Datenmengen (ggf. TBytes).
- Programme, die mit Hilfe von MapReduce implementiert werden, werden automatisch parallelisiert und auf einem großen Cluster von handelsüblichen Rechnern ausgeführt.

  .. container:: smaller dhbw-gray

    Die Laufzeitumgebung übernimmt:

    - Partitionierung der Eingabedaten und Verteilung selbiger auf die Rechner des Clusters
    - Einplanung und Ausführung der “Map”- und “Reduce”- Funktionen auf den Rechnern des Clusters
    - Behandlung von Fehlern und die Kommunikation zwischen den Rechnern

.. admonition:: Hinweis
    :class: warning

    Nicht alle Arten von Berechnungen können mit Hilfe von MapReduce durchgeführt werden.



.. class:: smaller-slide-title

MapReduce - Visualisierung und Beispiel
----------------------------------------------------------


.. image:: images/map-reduce.png
    :width: 90%
    :align: center

.. container:: supplemental


    Beispiel: Berechnung der Häufigkeit von Wörtern in einem sehr großen Datensatz.

    :K1: URLs
    :V1: HTML Dokumente
    :K2: Wörter in einem HTML Dokument
    :V2: Anzahl pro gefundenem Wort
    :V3: Häufigkeit des Wortes

    Ein weiteres Beispiel ist die Berechnung eines invertierten Indexes.


Cluster Computing
--------------------

Eine Gruppe von :ger-quote:`High-End-Systemen`, die über ein LAN verbunden sind.

.. image:: images/cluster-computing.svg
    :width: 60%
    :align: center

.. container:: supplemental

    Die einzelnen Rechner/Compute Nodes sind oft identisch (Hardware und Software) und werden von einem Verwaltungsknotenpunkt (:eng:`management node`) verwaltet.



Grid Computing
-------------------

Weiterführung des Cluster Computing. 

- Viele heterogene, weit und über mehrere Organisationen verstreute Knotenpunkte. 
- Die Knotenpunkte sind über das WAN verbunden. 
- Die Zusammenarbeit erfolgt im Rahmen einer virtuellen Organisation.

.. container:: supplemental

    (Volunteer) Grid Computing - Beispiel:

    https://scienceunited.org

    https://einsteinathome.org


Grundlegende Architektur für Grid-Computing
---------------------------------------------

.. container:: two-columns no-default-width

    .. container:: column center-child-elements no-separator

        .. image:: images/architecture-for-grid-computing.svg
            :width: 600px
            :align: center

    .. container:: column footnotesize margin-left-1em

        
      :Fabric Layer: Bietet Schnittstellen zu lokalen Ressourcen (zur Abfrage von Status und Fähigkeiten, Sperren usw.)
      :Konnektivitätsschicht: Kommunikations- / Transaktions- /Authentifizierungsprotokolle, z. B. für die Übertragung von Daten zwischen Ressourcen.
      :Ressourcenschicht: Verwaltet eine einzelne Ressource, z. B. das Erstellen von Prozessen oder das Lesen von Daten.
      :Collective Layer: Verwaltet den Zugriff auf mehrere Ressourcen: Auffindung (:eng:`Discovery`), Einplanung (:eng:`Scheduling`) und Replikation.
      :Anwendungen: Enthält tatsächliche Grid-Anwendungen in einer einzelnen Organisation.



Peer-to-Peer-Systeme
----------------------

:Vision: :ger-quote:`Das Netzwerk ist der Computer.` Es gibt einen Datenbestand, der immer weltweit erreichbar ist.
:Idee: 
   Keine dedizierten Clients und Server, jeder Teilnehmer (Peer) ist gleichzeitig Anbieter und Kunde.

   Selbstorganisierend, ohne zentrale Infrastruktur (Koordinator, Datenbestand, Teilnehmerverzeichnis).

   Jeder Peer ist autonom und kann jederzeit offline sein, Netzwerkadressen können sich beliebig ändern.

:Hauptanwendung: 
   File-Sharing-Systeme (insbesondere BitTorrent)

.. container:: supplemental

    Die große Zeit der klassischen Peer-to-Peer-Systeme war in den 2000er Jahren. 

    .. class:: positive-list

    - Vorteile von P2P Systemen sind: billig, fehlertolerant, dynamisch, selbstkonfigurierend, immens hohe Speicherkapazität, hohe Datenzugriffsgeschwindigkeit.

    .. class:: negative-list

    - Probleme von P2P Systemen sind: Start-Up, schlecht angebundene, leistungsschwache Peers; *Free-Riders*; Copyright-Probleme.


Cloud-Computing
------------------

.. container:: definition

    Weiterentwicklung des Grid-Computing. Ziel ist die Bereitstellung von Rechenleistung, Speicher und Anwendungen als Dienstleistung.

:Varianten: 

    - Public Cloud ( ⇒ Amazon EC2, Google Apps, Microsoft Azure, ...)
    - Private Cloud
    - Hybrid Cloud (Private Cloud wird bei Bedarf durch Public Cloud ergänzt)
  

.. container:: supplemental

    .. class:: positive-list

    - Vorteile des Cloud-Computings: Kosten, Aktualität von Daten und Diensten, keine eigene Infrastruktur notwendig, Unterstützung von mobilen Teilnehmern

    .. class:: negative-list

    - Probleme des Cloud-Computings: Sicherheit und Vertrauen, Verlust von eigenem Know-How, Umgang mit klassifizierten Daten


Integration von Anwendungen
---------------------------------

.. container:: assessment
    
    Die Standardanwendungen in Unternehmen sind vernetzte Anwendungen und die Herstellung der Interoperabilität zwischen diesen Anwendungen ist eine große Herausforderung.

.. container:: incremental margin-top-1em

    .. rubric:: Grundlegender Ansatz

    *Clients* kombinieren Anfragen für (verschiedene) Anwendungen, senden diese, sammeln die Antworten und präsentieren dem Benutzer ein kohärentes Ergebnis.

.. container:: incremental margin-top-1em

    .. rubric:: Weiterentwicklung

    Die direkte Kommunikation zwischen den Anwendungen führt zur Integration von Unternehmensanwendungen (:eng:`Enterprise Application Integration (EAI)`).


.. container:: supplemental

    Eine vernetzte Anwendung ist eine Anwendung, die auf einem Server läuft und ihre Dienste für entfernte Clients verfügbar macht. 


Transaktionen
---------------------------------

.. container:: two-columns 

    .. container:: column center-child-elements no-separator

        .. image:: images/transactions/transaction.svg
            :width: 750px
            :align: center

        .. container:: bold margin-top-2em line-above

            :ger-quote:`Alles oder nichts.`

    .. container:: column footnotesize

        .. container:: stack

            .. container:: layer

                .. csv-table::
                    :header: "Primitiv", "Beschreibung"
                    :width: 875px

                    BEGINN DER TRANSAKTION, Zeigt den Beginn einer Transaktion an.
                    ENDE DER TRANSAKTION, Beendigung der Transaktion mit dem Versuch eines COMMIT.
                    ABBRUCH DER TRANSAKTION, Beenden der Transaktion und Wiederherstellung des alten Zustands.
                    LESEN, "Lesen von Daten aus (z. B.) einer Datei oder einer Tabelle."
                    SCHREIBEN, "Schreiben von Daten (z. B.) in eine Datei oder eine Tabelle."

            .. container:: layer incremental
        
                :Atomar `Atomic`:eng:: geschieht untrennbar (scheinbar)
                :Konsistent `Consistent`:eng:: keine Verletzung von Systeminvarianten
                :Isoliert `Isolated`:eng:: keine gegenseitige Beeinflussung
                :Dauerhaft `Durable`:eng:: Nach einem Commit sind die Änderungen dauerhaft
        
                ≙ :eng:`ACID`\ -Eigenschaften

.. class:: smaller

*Transaction Processing Monitor (TPM)*
---------------------------------------

.. container:: assessment

    Daten, die im Rahmen einer Transaktion benötigt werden, sind oft verteilt über mehrere Server. 

.. image:: images/transactions/tpm.svg
    :width: 80%
    :align: center
    :class: incremental

.. container:: incremental smaller

    Ein TPM ist für die Koordination der Ausführung einer Transaktion verantwortlich.


.. container:: supplemental

    Insbesondere im Zusammenhang mit Microservices ist der Einsatz von TPMs und 2PC zum Zwecke der Koordination von Geschäftsprozessen häufig nicht die 1. Wahl. 

    Nichtsdestotrotz sind verteilte Transaktionen ein wichtiger Bestandteil von verteilten Systemen und Google hat z. B. mit Spanner eine Lösung entwickelt, die Transaktionen im globalen Maßstab ermöglicht  (*Global Consistency*). (https://cloud.google.com/spanner?hl=en und https://www.youtube.com/watch?v=iKQhPwbzzxU).
       


*Middleware* und *Enterprise Application Integration (EAI)*
------------------------------------------------------------

Middleware ermöglicht Kommunikation zwischen den Anwendungen.

.. image:: images/middleware.svg
    :height: 800px
    :align: center
    :class: incremental

.. container:: supplemental

    :Remote Procedure Call (RPC): Anfragen werden über einen lokalen Prozeduraufruf gesendet, als Nachricht verpackt, verarbeitet, von einer Nachricht beantwortet und das Ergebnis ist dann der Rückgabewert des Prozeduraufrufs.

    :Nachrichtenorientierte Middleware `Message Oriented Middleware (MOM)`:eng:: Nachrichten werden an einen logischen Kontaktpunkt gesendet (d. h.veröffentlicht) und Anwendungen weitergeleitet, die diese Nachrichten abonnieren.


.. class:: smaller

Wie kann die Anwendungsintegration erreicht werden?
-----------------------------------------------------

.. class:: incremental

:Dateiübertragung: 

  Technisch einfach, aber nicht flexibel:

  - Dateiformat und Layout herausfinden
  - Dateiverwaltung regeln
  - Weitergabe von Aktualisierungen und Aktualisierungsbenachrichtigungen.
 
.. class:: incremental

:Gemeinsame Datenbank: Sehr viel flexibler, erfordert aber immer noch ein gemeinsames Datenschema neben dem Risiko eines Engpasses.

.. class:: incremental

:Entfernter Prozeduraufruf `Remote Procedure Call (RPC)`:eng:: Wirksam, wenn die Ausführung einer Reihe von Aktionen erforderlich ist.

.. class:: incremental

:Nachrichtenübermittlung `Messaging`:eng:: Ermöglicht eine zeitliche und räumliche Entkopplung im Vergleich zu RPCs.


Distributed Pervasive/Ubiquitous Systems (:ger:`verteilte, allgegenwärtige/durchdringende Systeme`)
------------------------------------------------------------------------------------------------------------

.. container:: assessment

    Moderne verteilte Systeme zeichnen sich dadurch aus, dass die Knoten klein, mobil und oft in ein größeres System eingebettet sind. Das System bettet sich auf natürliche Weise in die Umgebung des Benutzers ein. Die Vernetzung ist drahtlos.


.. container:: incremental footnotesize

    Drei (sich überschneidende) Untertypen

    :Ubiquitous Computing: *allgegenwärtig und ständig präsent*, d. h., es besteht eine ständige Interaktion zwischen System und Benutzer.
    :Mobile Computing: *allgegenwärtig*; der Schwerpunkt liegt auf der Tatsache, dass Geräte von Natur aus mobil sind.
    :Sensor-/Actuator Networks: *allgegenwärtig*; Schwerpunkt liegt auf der tatsächlichen (kollaborativen) Erfassung (:eng:`sensing`) und Betätigung (:eng:`actuation`).


Ubiquitous Systems - Kernbestandteile
--------------------------------------------

.. class:: incremental

1. :eng:`Distribution`: Die Geräte sind vernetzt, verteilt und ohne Hürde zugänglich.
2. :eng:`Interaction`: Die Interaktion zwischen Benutzern und Geräten ist in hohem Maße unaufdringlich 
3. :eng:`Context Awareness`: Das System kennt den Kontext eines Benutzers, um die Interaktion zu optimieren.
4. :eng:`Autonomy`: Die Geräte arbeiten autonom, ohne menschliches Eingreifen, und verwalten sich in hohem Maße eigenständig.
5. :eng:`Intelligence`: Das System als Ganzes kann ein breites Spektrum dynamischer Aktionen und Interaktionen bewältigen.


Mobile Computing - Auszeichnende Merkmale
--------------------------------------------

.. class:: incremental smaller

- Eine Vielzahl unterschiedlicher mobiler Geräte (Smartphones, Tablets, GPS-Geräte, Fernbedienungen, aktive Ausweise).
- Mobil bedeutet, dass sich der Standort eines Geräts im Laufe der Zeit ändern kann. Dies kann z. B. Auswirkung haben auf die lokalen Dienste oder die Erreichbarkeit.
- Die Aufrechterhaltung einer stabilen Kommunikation kann zu ernsthaften Problemen führen.

        
.. container:: assessment margin-top-2em

    Aktueller Stand ist, dass mobile Geräte Verbindungen zu stationären Servern herstellen, wodurch diese im Prinzip *Clients* von Cloud-basierten Diensten sind.

Mobile Cloud Computing
-------------------------------------------- 

.. image:: images/mobile_computing/mobile_cloud_computing.svg
    :width: 100%


Mobile Edge Computing
--------------------------------------------

.. image:: images/mobile_computing/mobile_edge_computing.svg
    :width: 100%
                
            


*Sensor Networks* 
--------------------------------------------

Die Knoten, an denen Sensoren angebracht sind:

- :ger-quote:`viele`
- einfach (geringe Speicher- / Rechen- / Kommunikationskapazität) 
- oft batteriebetrieben (oder sogar batterielos)

.. image:: images/sensor_networks/operator_stores_and_processes_data.svg
    :width: 95%



*Sensor Networks* als verteilte Datenbanken
--------------------------------------------

.. image:: images/sensor_networks/nodes_store_and_process_data.svg
    :width: 95%



Das *Cloud-Edge Continuum*
--------------------------------

.. image:: images/cloud_edge_continuum.svg
    :width: 1750px
    :align: center


Fallstricke bei der Entwicklung verteilter Systeme
-----------------------------------------------------

.. container:: assessment

    Viele verteilte Systeme sind unnötig komplex aufgrund fehlerhafter Annahmen sowie von Architektur- und Designfehlern, die später nachgebessert werden müssen.

.. container:: incremental

    .. rubric:: Falsche (und oft versteckte) Annahmen

    .. class:: incremental

    - Das Netzwerk ist zuverlässig
    - Das Netzwerk ist sicher
    - Das Netz ist homogen 
    - Die Topologie ändert sich nicht 
    - Die Latenz ist gleich null
    - Die Bandbreite ist unendlich
    - Die Transportkosten sind gleich null
    - Es gibt nur einen Administrator

