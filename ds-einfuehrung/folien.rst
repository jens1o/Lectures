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

.. container:: smaller line-above

    :Dozent: **Prof. Dr. Michael Eichberg**
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: |date|

.. container:: tiny line-above incremental

    :seit Okt. 2023: Professor an der DHBW Mannheim
    :4 Jahre: Bundeskriminalamt
    :12 Jahre: Postdoc am Fachgebiet Softwaretechnik der TU Darmstadt
    :2007: Promotion zum Dr. Ing. am Fachgebiet Softwaretechnik der TU Darmstadt

.. container:: footer-left tiny incremental

    Dieser Foliensatz basiert auf Folien von:
    
    (a) Maarten van Steen (Veröffentlicht zum Buch *Distributed Systems*)

    (b) Henning Pagnia (basierend auf seiner Vorlesung *Verteilte Systeme*). 

    Alle Fehler sind meine eigenen.
    


Verteilte Systeme - W3WI_110.2
----------------------------------

:Modul: Entwicklung verteilter Systeme (W3WI_110)
:Umfang: 22 Vorlesungsstunden und 38 Stunden Selbststudium
:Prüfungsleistung: Portfolio 

.. container:: supplemental

    .. rubric:: Kerninhalte gem. MHB

    - Terminologie, Konzepte, Architekturen, Anforderungsprofile und Architekturmodelle für verteilte Systeme
    - Entwurfs- und Implementierungsansätze
    - Vergleich unterschiedlicher Middleware-Konzepte
    - Synchrone und asynchrone Kommunikation, entfernter Methodenaufruf 
    - Asynchrone Kommunikation und Messaging-Systeme
    - Sicherheitsaspekte in verteilten Systemen



.. class:: padding-none no-title transition-scale

Terminologie verteilter Systeme
----------------------------------

.. image:: images/modern_software_architecture-tag_cloud.png
    :width: 100%
    :align: center



Empfohlene Literatur
---------------------

.. image:: screenshots/distributed-systems.net.png
    :height: 800px
    :align: center

.. container:: supplemental

    Ergänzend bzw. für interessierte Studierende:

    .. image:: screenshots/microservices.jpg
        :height: 800px
        :align: center
        :class: box-shadow 

    

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
    - **Expansive Sichtweise**: ein bestehendes vernetztes Computersystem wird um zusätzliche Computer erweitert

.. admonition:: Zwei Definitionen
    :class: definition in

    - Ein dezentrales System ist ein vernetztes Computersystem, in dem Prozesse und Ressourcen *notwendigerweise* über mehrere Computer verteilt sind.
    - Ein verteiltes System ist ein vernetztes Computersystem, bei dem Prozesse und Ressourcen *hinreichend* über mehrere Computer verteilt sind.



Häufige Missverständnisse bzgl. zentralisierter Systeme
--------------------------------------------------------

.. class:: incremental

1. **Zentralisierte Lösungen lassen sich nicht skalieren**
 
   Es gilt zwischen logischer und physischer Zentralisierung zu unterscheiden. Zum Beispiel ist das Domain Name Systems:

   - logisch zentralisiert
   - physisch (massiv) verteilt
   - dezentralisiert über mehrere Organisationen
  
2. **Zentralisierte Lösungen haben einen Single Point of Failure**
   
   Im Allgemeinen nicht zutreffend (z.B. DNS). 
   
   Ein einzelner Fehlerpunkt ist weiterhin oft:

   - leichter zu verwalten
   - einfacher robuster zu machen

.. container:: supplemental 
    
    .. admonition:: Warnung 
        :class: warning

        Es gibt viele, schlecht begründete Missverständnisse in Bezug auf, z.B. Skalierbarkeit, Fehlertoleranz oder Sicherheit. Wir müssen Fähigkeiten entwickeln, mit denen verteilte Systeme leicht verstanden werden können, um solche Missverständnisse zu vermeiden.



Sichtweisen auf verteilte Systeme
----------------------------------

**Verteilte Systeme sind komplex.**

.. class:: incremental

- Welche Architekturen und Architekturellen Stile (:eng:`architectural styles`) gibt es?
- Prozesse: Welche Art von Prozessen gibt es und wie sind deren Beziehungen?
- Kommunikation: Welche Möglichkeiten zum Austausch von Daten gibt es?
- Koordinierung: Wie erfolgt die Koordinierung der beteiligten Systeme?
- Benennung: Wie identifiziert man Ressourcen?
- Konsistenz und Replikation: Welcher Tradeoffs müssen in Hinblick auf die Konsistenz der Daten, der Replikation der Selbigen und der Performance getroffen werden?
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
- Gemeinsame E-Mail-Dienste (z. B. ausgelagerte E-Mail-Systeme)
- Gemeinsames Webhosting (z.B. *Content Distribution Networks*)



Verteilungstransparenz (:eng:`Distribution Transparency`)
----------------------------------------------------------

.. admonition:: Definition

    Transparenz beschreibt die Eigenschaft, dass ein verteiltes System versucht, die Tatsache zu verbergen, dass seine Prozesse und Ressourcen physisch auf mehrere Computer verteilt sind, die möglicherweise durch große Entfernungen voneinander getrennt sind.

.. container:: incremental

  Die Verteilungstransparenz wird durch viele verschiedene Techniken von der so genannten Middleware realisiert - einer Schicht zwischen Anwendungen und Betriebssystemen.



Aspekte der Verteilungstransparenz (:eng:`Distribution Transparency`)
----------------------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover smaller

    Datenzugriff, Verbergen von Unterschieden in der Datendarstellung und der Art des Zugriffs auf ein lokales bzw. entferntes Objekt
    Ort der Datenhaltung, "Verbergen, wo sich ein Objekt befindet"
    Verschieben, "Verbergen, dass ein Objekt während der Verwendung an einen anderen Ort verschoben werden kann"
    Migration, "Verbergen, dass ein Objekt an einen anderen Ort verschoben werden kann" 
    Replikation, "Verbergen, dass ein Objekt repliziert wird"
    Nebenläufigkeit, "Verbergen, dass ein Objekt von mehreren unabhängigen Benutzern gemeinsam genutzt werden kann"
    Fehlertransparenz, Verbergen des Ausfalls und der Wiederherstellung eines Objekts.



Grad der erreichbaren Verteilungstransparenz
--------------------------------------------

.. container:: assessment

    Eine vollständigen Verteilungstransparenz ist nicht erreichbar. 

Jedoch kann auch eine sehr hohe Verteilungstransparenz bereits hohe Kosten nach sich ziehen.

.. class:: incremental smaller

- Es gibt Kommunikationslatenzen, die nicht verborgen werden können.
- Es ist (theoretisch und praktisch) unmöglich, Ausfälle von Netzen und Knoten vollständig zu verbergen.
- Man kann einen langsamen Computer nicht von einem ausgefallenen Computer unterscheiden.
- Man kann nie sicher sein, dass ein Server tatsächlich eine Operation durchgeführt hat bevor er abgestürzt ist.
- Vollständige Transparenz kostet Performance und legt die Verteilung des Systems offen.
  
  - Die Replikate exakt auf dem Stand des Masters zu halten, kostet Zeit 
  - Schreibvorgänge werden zur Fehlertoleranz sofort auf die Festplatte übertragen



Die Verteilung offen zu legen kann Vorteile bringen
----------------------------------------------------

- Nutzung von standortbezogenen Diensten (Auffinden von Freunden in der Nähe)
- Beim Umgang mit Benutzern in verschiedenen Zeitzonen
- Wenn es für einen Benutzer einfacher ist, zu verstehen, was vor sich geht (wenn z. B. ein Server lange Zeit nicht antwortet kann er als ausgefallen gemeldet werden).

.. container:: assessment

    Verteilungstransparenz (:eng:`Distribution Transparency`) ist ein heres Ziel, aber oft schwer zu erreichen, und häufig auch nicht erstrebenswert. 



Offene verteilte Systeme
----------------------------------

.. admonition:: Definition

    Ein offenes verteiltes System bietet Komponenten an, die leicht von anderen Systemen verwendet oder in andere Systeme integriert werden können. Ein offenes verteiltes System besteht selbst oft aus Komponenten, die von anderswoher stammen.

.. container:: incremental

    Offene verteilte Systeme müssen in der Lage sein, mit Diensten anderer (offener) Systeme zu interagieren, unabhängig von der zugrunde liegenden Umgebung:

    - Systeme sollten wohl-definierte Schnittstellen korrekt realisieren
    - Systeme sollten leicht mit anderen Systemen interagieren können
    - Systeme sollten die Portabilität von Anwendungen unterstützen 
    - Systeme sollten leicht erweiterbar sein



Vorgaben/Richtlinien vs. Umsetzungen (:eng:`Policies vs. Mechanisms`)
------------------------------------------------------------------------------

.. rubric:: Richtlinien für die Umsetzung von Offenheit

- Welchen Grad an Konsistenz benötigen wir für Daten im Client-Cache?
- Welche Operationen erlauben wir heruntergeladenem Code?
- Welche QoS-Anforderungen passen wir angesichts schwankender Bandbreiten an? 
- Welchen Grad an Geheimhaltung benötigen wir für die Kommunikation?

.. rubric:: Mechanismen bgzl. der Umsetzung von Offenheit

- Ermöglichung der (dynamischen) Einstellung von Caching-Richtlinien
- Unterstützung verschiedener Vertrauensstufen für mobilen Code
- Bereitstellung einstellbarer QoS-Parameter pro Datenstrom 
- Angebot verschiedener Verschlüsselungsalgorithmen


.. container:: supplemental

    Die harte Kodierung von Richtlinien vereinfacht oft die Verwaltung und reduziert die Komplexität des Systems. Hat jedoch den Preis geringerer Flexibilität.



Verlässlichkeit verteilter Systeme (:eng:`Dependability`)
------------------------------------------------------------

.. admonition:: Abhängigkeiten
    :class: foundations
    
    Eine **Komponente**\ [#]_ stellt seinen **Clients** **Dienste** zur Verfügung. Dafür kann die Komponente jedoch wiederum Dienste von anderen Komponenten benötigen und somit ist eine Komponente  von einer anderen Komponente abhängig (:eng:`depend`).


    Eine Komponente C hängt von C\ :sup:`*` ab, wenn die Korrektheit des Verhaltens von C von der Korrektheit des Verhaltens von C\ :sup:`*` abhängt. 

.. [#] Komponenten sein Prozesse oder Kanäle.


Anforderungen an die Verlässlichkeit verteilter Systeme
------------------------------------------------------------

.. csv-table::
    :class: highlight-line-on-hover
    :header: "Anforderung", "Beschreibung"

    "Verfügbarkeit", "Das System ist nutzbar."
    "Zuverlässigkeit", "Kontinuität der korrekten Leistungserbringung"
    Sicherheit (:eng:`Safety`\ [#]_), "Niedrige Wahrscheinlichkeit für ein katastrophales Ereignis"
    "Wartbarkeit", "Wie leicht kann ein fehlgeschlagenes System wiederhergestellt werden?"

.. [#] :eng:`Safety` und :eng:`Security` werden beide im Deutschen gleich mit Sicherheit übersetzt und sind daher leicht zu verwechseln. :eng:`Safety` bezieht sich auf die Sicherheit von Personen und Sachen, während :eng:`Security` sich auf die Sicherheit von Daten und Informationen bezieht.


Zuverlässigkeit (:eng:`Reliability`) vs. Verfügbarkeit (:eng:`Availability`) in verteilten Systemen
--------------------------------------------------------------------------------------------------------------

.. rubric:: Verlässlichkeit :math:`R(t)` der Komponente :math:`C`

Bedingte Wahrscheinlichkeit, dass :math:`C` während :math:`[0,t)` korrekt funktioniert hat, wenn :math:`C` zum Zeitpunkt :math:`T = 0` korrekt funktionierte.

.. rubric:: Traditionelle Metriken

- Mittlere Zeit bis zum Versagen (:eng:`Mean Time to Failure` ((MTTF)): Die durchschnittliche Zeit bis zum Ausfall einer Komponente. 
- Mittlere Zeit bis zur Reparatur (:eng:`Mean Time to Repair` (MTTR)): Die durchschnittliche Zeit, die für die Reparatur einer Komponente benötigt wird.
- Mittlere Zeit zwischen Ausfällen (:eng:`Mean Time Between Failures` (MTBF)): MTTF + MTTR.

.. container:: supplemental

    - Zuverlässigkeit: Wie wahrscheinlich ist es, dass ein System korrekt arbeitet?
    - Verfügbarkeit: Wie wahrscheinlich ist es, dass ein System zu einem bestimmten Zeitpunkt verfügbar ist?


Sicherheit in verteilten Systemen
--------------------------------------

.. container:: assessment

    Ein verteiltes System, das nicht sicher ist, ist nicht verlässlich.

Grundlegende Schutzziele:

:Vertraulichkeit: Informationen werden nur an autorisierte Parteien weitergegeben.
:Integrität: Änderungen an den Werten eines Systems dürfen nur auf autorisierte Weise vorgenommen werden können.

.. container:: smaller margin-top-2em incremental

    .. rubric:: Autorisierung, Authentifizierung, Vertrauen

    .. class:: incremental

    - Authentifizierung (:eng:`Authentication`): Überprüfung der Korrektheit einer behaupteten Identität
    - Autorisierung (:eng:`Authorization`): Verfügt eine identifizierte Einheit über die richtigen Zugriffsrechte?
    - Vertrauen (:eng:`Trust`): Eine Komponente kann sich sicher sein, dass eine andere Komponente bestimmte Handlungen gemäß den Erwartungen ausführt.



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

                **Symmetrische Verschlüsselung von Nachrichten**
                
                Alice sendet eine Nachricht an Bob und nutzt dazu den öffentlichen Schlüssel von Bob.

                .. math::
                    Y = E(PU_{Bob},X) \\
                    X = D(PR_{Bob},Y) 

            .. container:: layer incremental

                **Asymmetrische Verschlüsselung von Nachrichten**

                Alice signiert (:math:`S`) eine Nachricht mit ihrem privaten Schlüssel.

                .. math::
                    Y = E(PR_{Alice},X) \\
                    X = D(PU_{Alice},Y)


Sicherheit - Sicheres Hashing (:eng:`Secure Hashing`)
------------------------------------------------------- 

Eine sichere Hash-Funktionen: :math:`Digest(X)` gibt eine Zeichenkette fester Länge zurück (:math:`H`).
- Jede Änderung - noch so klein - der Eingabedaten führt zu einer völlig anderen Zeichenkette.
- Bei einem Hash-Wert ist es rechnerisch unmöglich die ursprüngliche Nachricht X basierend auf :math:`Digest(X)` zu finden.


**Signieren von Nachrichten**

Alice signiert eine Nachricht mit ihrem privaten Schlüssel.

.. math::
    Alice: [E(PR_{Alice},H=Digest(X)),X] \\

Bob prüft die Nachricht X auf Authentizität:

.. math::
    Bob: D(PU_{Alice},H) \stackrel{?}{=} Digest(X)



Skalierbarkeit in verteilten Systemen
-----------------------------------------

Wir können mind. drei Arten von Skalierbarkeit unterscheiden:

- Anzahl der Benutzer oder Prozesse (Skalierbarkeit der Größe)
- Maximale Entfernung zwischen den Knoten (geografische Skalierbarkeit) 
- Anzahl der administrativen Domänen (administrative Skalierbarkeit)


Ursachen für Skalierbarkeitsprobleme bei zentralisierten Lösungen:
---------------------------------------------------------------------

- Die Rechenkapazität, da diese begrenzt ist durch die CPUs
- Die Speicherkapazität, einschließlich der Übertragungsrate zwischen CPUs und Festplatten 
- Das Netzwerk zwischen dem Benutzer und dem zentralisierten Dienst

.. container:: supplemental

    Die Skalierbarkeit bzgl. der der Größe kann oft durch den Einsatz von mehr und leistungsstärkeren Servern, die parallel betrieben werden, erreicht werden.

    Die geografische und administrative Skalierbarkeit ist häufig eine größere Herausforderung.


.. class:: smaller

Formale Analyse der Skalierbarkeit zentralisierter Systeme
------------------------------------------------------------


Ein zentralisierter Dienst kann als einfaches Warteschlangensystem modelliert werden:

.. container:: stack

    .. container:: layer


        .. image:: images/queuing-system.svg
            :width: 75%
            :align: center

        Annahmen:

        Die Warteschlange hat eine unendliche Kapazität; d.h. die Ankunftsrate der Anfragen wird nicht durch die aktuelle Länge der Warteschlange oder durch das, was gerade bearbeitet wird, beeinflusst.

    .. container:: layer incremental

        .. container:: two-columns no-default-width

            .. container:: column

                - Ankunftsrate der Anfragen: λ
                - Verarbeitungskapazität des Services: μ Anfragen pro Sekunde

                Anteil der Zeit mit :math:`x` Anfragen im System:

                .. math::

                    p_x  = \bigl(1 - \frac{\lambda}{\mu}\bigr)\bigl(\frac{\lambda}{\mu}\bigr)^x

            .. container:: column

                .. image:: images/number_of_requests_in_system.svg
                    :width: 1300px


    .. container:: layer incremental

        .. note::

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


    .. container:: layer incremental

        .. container:: two-columns no-default-width

            .. container:: column

                Die Antwortszeit (:eng:`response time`) ist die Gesamtzeit für die Bearbeitung einer Anfrage


                .. math::
                    R = \frac{\bar{N}}{X} = \frac{S}{1-U} \Rightarrow \frac{R}{S} = \frac{1}{1-U} 

                mit :math:`S = \frac{1}{\mu}` für die durchschnittliche Servicezeit. 
            .. container:: column
                    
                .. image:: images/response_time.svg
                   :width: 1100px

        - Wenn :math:`U` klein ist, liegt die Antwortzeit nahe bei 1; d.h. eine Anfrage wird sofort bearbeitet.
        - Wenn :math:`U` auf 1 ansteigt, kommt das System zum Stillstand. 

.. container:: supplemental

    Für eine `unendliche geometrische Reihe <https://de.wikipedia.org/wiki/Geometrische_Reihe#Konvergenz_und_Wert_der_geometrischen_Reihe>`__ mit dem Quotienten :math:`U` gilt:

    .. math::
        \sum_{k\geq 0} k\cdot U^k  = \frac{U}{(1-U)^2} 

    Visualisierung der durchschnittlichen Anzahl der Anfragen im System in Abhängigkeit von der Auslastung :math:`U`:

    .. image:: images/average_number_of_requests_in_system.svg
        :width: 1100px
        :align: center


Probleme der geografischen Skalierbarkeit
--------------------------------------------

- Viele verteilte Systeme gehen von synchronen Client-Server-Interaktionen aus und dies verhindert einen Übergang vom LAN zum WAN. Die Latenzzeiten können prohibitiv sein, wenn der Client auf eine Anfrage lange auf die Antwort warten muss.
- WAN-Verbindungen sind oft von Natur aus unzuverlässig.


Probleme der administrativen Skalierbarkeit
--------------------------------------------

.. container:: assessment

    Widersprüchliche Richtlinien in Bezug auf Nutzung (und damit Bezahlung), Verwaltung und Sicherheit

.. container:: footnotesize

    .. rubric:: Beispiele

    - Gridcomputing: gemeinsame Nutzung teurer Ressourcen über verschiedene Domänen hinweg.
    - Gemeinsam genutzte Geräte: Wie kontrolliert, verwaltet und nutzt man ein gemeinsam genutztes Radioteleskop, das als groß angelegtes gemeinsames Sensornetz konstruiert wurde?

    .. rubric:: Ausnahme 

    Verschiedene Peer-to-Peer-Netze [#]_ bei denen Endnutzer zusammenarbeiten und nicht Verwaltungseinheiten:

    - File-Sharing-Systeme (z. B. auf der Grundlage von BitTorrent) 
    - Peer-to-Peer-Telefonie (frühe Versionen von Skype) 

.. [#] :eng:`Peer` ist im hier im Sinne von :ger-quote:`Gleichgestellter` zu verstehen. D.h. wir haben ein Netz von gleichgestellten Rechnern.

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
    :height: 900px
    :align: center


Ansätze, um Skalierung zu erreichen
------------------------------------

**Einsatz von Replikation und Caching, um Kopien von Daten auf verschiedenen Rechnern verfügbar zu machen.**

- Replizierte Dateiserver und Datenbanken 
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

    Inwieweit Inkonsistenzen toleriert werden können ist anwendungsspezifisch. Können diese jedoch toleriert werden, dann kann der Bedarf an globaler Synchronisation verringert werden.


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


.. class:: smaller

Cluster Computing
--------------------

Eine Gruppe von :ger-quote:`High-End-Systemen`, die über ein LAN verbunden sind.

.. image:: images/cluster-computing.svg
    :width: 60%
    :align: center

.. container:: supplemental

    Die einzelnen Rechner/Compute Nodes sind oft identisch (Hardware und Software) und werden von einem Verwaltungsknotenpunkt (:eng:`management node`) verwaltet.


.. rubric:: Grid Computing

Weiterführung des Cluster Computing. Viele heterogene, weit und über mehrere Organisationen verstreute Knotenpunkte. Die Knotenpunkte sind über das WAN verbunden. Die Zusammenarbeit erfolgt im Rahmen einer virtuellen Organisation.


Grundlegende Architektur für Grid-Computing
---------------------------------------------

.. container:: two-columns no-default-width

    .. container:: column center-child-elements

        .. image:: images/architecture-for-grid-computing.svg
            :width: 600px
            :align: center

    .. container:: column footnotesize

        
      :Fabric Layer: Bietet Schnittstellen zu lokalen Ressourcen (zur Abfrage von Status und Fähigkeiten, Sperren usw.)
      :Konnektivitätsschicht: Kommunikations- / Transaktions- /Authentifizierungsprotokolle, z. B. für die Übertragung von Daten zwischen Ressourcen.
      :Ressourcenschicht: Verwaltet eine einzelne Ressource, z. B. das Erstellen von Prozessen oder das Lesen von Daten.
      :Collective Layer: Verwaltet den Zugriff auf mehrere Ressourcen: Auffindung (:eng:`Discovery`), Einplanung (:eng:`Scheduling`) und Replikation.
      :Anwendungen: Enthält tatsächliche Grid-Anwendungen in einer einzelnen Organisation.



Peer-to-Peer-Systeme
----------------------

:Vision: :ger-quote:`Das Netzwerk ist der Computer.` Es gibt einen Datenbestand, der immer weltweit erreichbar ist.
:Idee: 
   Keine dedizierten Clients und Server, jeder Teilnehmer (Peer) ist gleichzeitig Anbieter und Kunde.

   Selbstorganisierend, ohne zentrale Infrastruktur (Koordinator, Datenbestand, Teilnehmerverzeichnis).

   Jeder Peer ist autonom und kann jederzeit off-line sein, Netzwerkadressen können sich beliebig ändern.

:Hauptanwendung: 
   File-Sharing-Systeme (insbesondere BitTorrent)

.. container:: supplemental

    Die große Zeit der klassischen Peer-to-Peer-Systeme war in den 2000er Jahren. 

    Vorteile von P2P Systemen: billig, fehlertolerant, dynamisch, selbstkonfigurierend, immens hohe Speicherkapazität, hohe Datenzugriffsgeschwindigkeit

    Probleme von P2P Systemen: Start-Up, schlecht angebundene, leistungsschwache Peers; *Free-Riders*; Copyright-Probleme


Cloud-Computing
------------------

.. container:: definition

    Weiterentwicklung des Grid-Computing. Ziel ist die Bereitstellung von Rechenleistung, Speicher und Anwendungen als Dienstleistung.

:Varianten: 

    - Public Cloud ( ⇒ Amazon EC2, Google Apps, Microsoft Azure, ...)
    - Private Cloud
    - Hybrid Cloud (Private Cloud wird bei Bedarf durch Public Cloud ergänzt)
  

.. container:: supplemental

    Vorteile des Clous-Computings: Kosten, Aktualit ̈at von Daten und Diensten, keine eigene Infrastruktur notwendig, Unterstützung von mobilen Teilnehmern

    Probleme des Clous-Computings: Sicherheit und Vertrauen, Verlust von eigenem Know-How, Umgang mit klassifizierten Daten


Integration von Anwendungen
---------------------------------

.. container:: assessment
    
    Die Standardanwendungen in Unternehmen sind vernetzte Anwendungen und die Herstellung der Interoperabilität zwischen diesen Anwendungen ist eine große Herausforderung.

.. container:: incremental margin-top-1em

    .. rubric:: Grundlegender Ansatz

    *Clients* kombinieren Anfragen für (verschiedene) Anwendungen, senden diese, sammeln die Antworten und präsentieren dem Benutzer ein kohärentes Ergebnis.

.. container:: incremental margin-top-1em

    .. rubric:: Weiterentwicklung

    Die direkte Kommunikation zwischen den Anwendung führt zur Integration von Unternehmensanwendungen (:eng:`Enterprise Application Integration (EAI)`).


.. container:: supplemental

    Eine vernetzte Anwendung ist eine Anwendung, die auf einem Server läuft und ihre Dienste für entfernte Clients verfügbar macht. 


Transaktionen
---------------------------------

.. container:: two-columns 

    .. container:: column center-child-elements

        .. image:: images/transactions/transaction.svg
            :width: 850px
            :align: center

        .. container:: bold margin-top-2em line-above

            :ger-quote:`Alles oder nichts.`

    .. container:: column footnotesize

        .. container:: stack

            .. container:: layer

                .. csv-table::
                    :header: "Primitiv", "Beschreibung"
                    :width: 900px

                    BEGINN DER TRANSAKTION, Zeigt den Beginn einer Transaktion an.
                    ENDE DER TRANSAKTION, Beendigung der Transaktion mit dem Versuch eines COMMIT.
                    ABBRUCH DER TRANSAKTION, Beenden der Transaktion und Wiederherstellung des alten Zustands.
                    LESEN, "Lesen von Daten aus (z.B.) einer Datei oder einer Tabelle."
                    SCHREIBEN, "Schreiben von Daten (z.B.) in eine Datei oder eine Tabelle."

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

    Daten, die im Rahmen einer Transaktion benötigt werden, sind of verteilt über mehrere Server. 

.. image:: images/transactions/tpm.svg
    :width: 65%
    :align: center
    :class: incremental

.. container:: incremental

    Ein TPM ist für die Koordination der Ausführung einer Transaktion verantwortlich.


*Middleware* und *Enterprise Application Integration (EAI)*
------------------------------------------------------------

Middleware ermöglicht Kommunikation zwischen den Anwendungen.

.. image:: images/middleware.svg
    :height: 800px
    :align: center
    :class: incremental

.. container:: supplemental

    :Remote Procedure Call (RPC): Anfragen werden über einen lokalen Prozeduraufruf gesendet, als Nachricht verpackt, verarbeitet, von einer Nachricht beantwortet und das Ergebnis ist dann der Rückgabewert des Prozeduraufrufs.

    :Nachrichtenorientierte Middleware `Message Oriented Middleware (MOM)`:eng:: Nachrichten werden an einen logischen Kontaktpunkt gesendet (d.h. veröffentlicht) und Anwendungen weitergeleitet, die diese Nachrichten abonnieren.


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
4. :eng:`Autonomy`: Die Geräte arbeiten autonom, ohne menschliches Eingreifen, und verwalten sich in hohem Maße selber.
5. :eng:`Intelligece`: Das System als Ganzes kann ein breites Spektrum dynamischer Aktionen und Interaktionen bewältigen.


Mobile Computing - Auszeichnende Merkmale
--------------------------------------------

.. class:: incremental smaller

- Eine Vielzahl unterschiedlicher mobiler Geräte (Smartphones, Tablets, GPS-Geräte, Fernbedienungen, aktive Ausweise).
- Mobil bedeutet, dass sich der Standort eines Geräts im Laufe der Zeit ändern kann mit Auswirkung, z.B., auf die lokalen Dienste oder die Erreichbarkeit.
- Die Aufrechterhaltung einer stabilen Kommunikation kann zu ernsthaften Problemen führen.

.. container:: stack incremental

    .. container:: layer
        
        .. container:: assessment

            Aktueller stand ist, dass mobile Geräte Verbindungen zu stationären Servern herstellen, wodurch diese im Prinzip *Clients* von Cloud-basierten Diensten sind.

    .. container:: layer incremental

        .. image:: images/mobile_computing/mobile_cloud_computing.svg
            :width: 1350px
            :align: center

    .. container:: layer incremental

        .. image:: images/mobile_computing/mobile_edge_computing.svg
            :width: 1350px
            :align: center


Sensor Networks als verteilte Datenbanken
-----------------------------------------

Die Knoten, an denen Sensoren angebracht sind:

- :ger-quote:`Viele`
- Einfach (geringe Speicher- / Rechen- / Kommunikationskapazität) 
- oft batteriebetrieben (oder sogar batterielos)

.. container:: stack incremental

    .. container:: layer

        .. image:: images/sensor_networks/operator_stores_and_processes_data.svg
            :width: 1625px
            :align: center


    .. container:: layer incremental

        .. image:: images/sensor_networks/nodes_store_and_process_data.svg
            :width: 1625px
            :align: center


Das *Cloud-Edge Continuum*
--------------------------------

.. image:: images/cloud_edge_continuum.svg
    :width: 1750px
    :align: center


Fallstricke bei der Entwicklung verteilter Systeme
-----------------------------------------------------

.. container:: assessment

    Viele verteilte Systeme sind unnötig komplex aufgrund fehlerhafter Annahmen sowie von Architektur- und Design Fehlern, die später nachgebessert werden müssen.

.. container:: incremental

    .. rubric:: Falsche (und oft versteckte) Annahmen

    .. class:: incremental

    - Das Netzwerk ist zuverlässig
    - Das Netzwerk ist sicher
    - Das Netz ist homogen 
    - Die Topologie ändert sich nicht 
    - Die Latenz ist gleich Null
    - Die Bandbreite ist unendlich
    - Die Transportkosten sind gleich Null
    - Es gibt nur einen Administrator



.. class:: integrated-exercise

Übung: MTBF
----------------

Wenn die MTTF einer Komponente 100 Stunden beträgt und die MTTR 10 Stunden beträgt, wie hoch ist dann die MTBF?

.. protected-exercise-solution:: Berechnung des MTBF
   :class: smaller
    
    .. math::
        MTBF = MTTF + MTTR = 100 + 10 = 110
