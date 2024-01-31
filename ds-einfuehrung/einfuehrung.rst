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

.. container:: smaller

    :Dozent: **Prof. Dr. Michael Eichberg**
    :Kontakt: michael.eichberg@dhbw-mannheim.de
    :Unterlagen: Moodle
    :Version: |date|

.. container:: footnotesize line-above

    :seit Okt. 2023: Professor an der DHBW Mannheim
    :4 Jahre: Bundeskriminalamt
    :12 Jahre: Postdoc am Fachgebiet Softwaretechnik der TU Darmstadt
    :2007: Promotion zum Dr. Ing. am Fachgebiet Softwaretechnik der TU Darmstadt



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
    - Ein verteiltes System ist ein vernetztes Computersystem, bei dem Prozesse und Ressourcen *ausreichend* über mehrere Computer verteilt sind.



Häufige Missverständnisse bzgl. zentralisierter Systeme
--------------------------------------------------------

1. **Zentralisierte Lösungen lassen sich nicht skalieren**
 
   Es gilt zwischen logischer und physischer Zentralisierung zu unterscheiden. Zum Beispiel ist das Domain Name Systems:

   - logisch zentralisiert
   - physisch (massiv) verteilt
   - dezentralisiert über mehrere Organisationen
  
2. **Zentralisierte Lösungen haben einen *Single Point of Failure**
   
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

    Datenzugriff, Verbergen von Unterschieden in der Datendarstellung und der Art des Zugriffs auf ein Objekt
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


.. class:: integrated-exercise

Übung: MTBF
----------------

Wenn die MTTF einer Komponente 100 Stunden beträgt und die MTTR 10 Stunden beträgt, wie hoch ist dann die MTBF?
    