.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Modellierung", "UML", "Objektorientierung", "Software Development"
    :description lang=de: Einführung in der Modellierung mit UML
    :id: lecture-prog-uml
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
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced

.. role:: java(code)
   :language: java

.. role:: raw-html(raw)
   :format: html



.. class:: animated-symbol

Einführung in die Objekt-orientierte Modellierung - Erste Grundlagen der Modellierung mit UML
================================================================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Klassendiagramme
-----------------------------------------------------------------------

.. container:: incremental center-child-elements

    „Curtis' Gesetz: [...] Gute Entwürfe erfordern fundierte Anwendungskenntnisse.“

    .. container:: far-far-smaller margin-top-1em minor
    
        **Albert Endres and Dieter Rombach**; *A Handbook of Software and Systems Engineering*; Addison Wesley 2003



Klassen und Objekte
-------------------

.. container:: center-child-elements incremental rounded-corners padding-1em box-shadow dhbw-red-background white

    Eine **Klasse** beschreibt *eine Gruppe von* **Objekten** mit:

    .. class:: incremental

    (1) derselben Semantik, 
    (2) denselben Eigenschaften und
    (3) demselben Verhalten.

.. container:: incremental margin-top-2em

    D. h. eine Klasse definiert einen Typ. 
    
    Konkrete Ausprägungen dieses Typs sind die Objekte.



Modellierung einer Party - Klassen\ [#]_
-----------------------------------------------

.. image:: images/uml-cd/klassen.svg
    :class: incremental
    :alt: Klassen
    :height: 1000px
    :align: center

.. [#] Das Beispiel ist stark angelehnt an Abb. 6.1 aus UML2 Glasklar, Hanser Verlag

.. supplemental::

    Wir haben erst einmal nur die Klassen identifiziert/modelliert, die für Parties zentral sind. 

    Hierbei repräsentieren die Klassen verschiedene :ger-quote:`Dinge``:

    - Eine Party als virtuelles Konstrukt, das eine bestimmte Anzahl von Partyteilnehmern hat.
    - Ein Gast, der an einer Party teilnimmt.
    - Ein Cocktail, welcher aus verschiedenen (konkreten) Zutaten besteht.
    - Ein Partyteilnehmer welcher eine Abstraktion für Gäste und Gastgeber darstellt.
  


Attribute 
-------------------

- Attribute sind logische Datenwerte eines Objekts und haben immer einen Datentyp.

.. class:: list-with-explanations incremental

- Die Attribute in einem Modell sollten vorzugsweise „primitive“ Datentypen sein. 
  
  Sehr häufig betrachten wir als primitive Datentypen: Boolesche Werte (Boolean), Datumsangaben (:eng:`Date`), Zahlen (:eng:`Number`), Zeichen (:eng:`Character`), Strings, Adressen, Farben, Telefonnummern,...

- Häufig macht es Sinn Mengen (x Liter, y Kilogramm, etc.) als Klassen zu modellieren, um Einheiten zuordnen zu können. 
  
  Z. B. sollte der Datentyp des Attributs „Betrag“ einer Zahlung die Währung angeben.

.. class:: incremental

- Attribute können weiterhin:

  .. class:: incremental

  - abgeleitet sein und ggf.
  - Defaultwerte haben sowie
  - Sichtbarkeiten haben.



.. class:: transition-fade

Modellierung einer Party - Attribute 
---------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/uml-cd/klassen.svg
            :alt: Klassen
            :height: 1000px
            :align: center

    .. layer:: overlay incremental

        .. image:: images/uml-cd/attribute_einfach.svg
            :alt: Einfache Attribute
            :height: 1000px
            :align: center

    .. layer:: overlay incremental

        .. image:: images/uml-cd/attribute_mit_default.svg
            :height: 1000px
            :align: center    

    .. layer:: overlay incremental

        .. image:: images/uml-cd/attribute_mit_mengen.svg
            :height: 1000px
            :align: center            

    .. layer:: overlay incremental

        .. image:: images/uml-cd/attribute_keine.svg
            :height: 1000px
            :align: center            
    
    .. layer:: overlay incremental

        .. image:: images/uml-cd/attribute_abgeleitet.svg
            :height: 1000px
            :align: center            



.. supplemental::

    Grundlegende Attributdeklarationen:

    :Syntax: [<Sichtbarkeit>] [ **/** ] <Attributname> [**:** <Datentyp>] [ **[** <Multiplizität> **\]** ] [ **=** <Defaultwert>]

    .. container:: smaller line-above
            
        :Sichtbarkeiten:

          - **+** : public; d. h. alle Instanzen dürfen auf das Attribut zugreifen.
          - **-** : private; d. h. nur Instanzen der Klasse dürfen auf das Attribut zugreifen.
          - **#** : protected; d. h. nur Instanzen der Klasse und von Subklassen dürfen auf das Attribut zugreifen.
          - **~** : package; d. h. nur Instanzen der Klasse und von Klassen im selben Package dürfen auf das Attribut zugreifen. 
          - Ist die Sichtbarkeit nicht explizit angegeben, so ist die typische Annahme **private**.

        :/: Bedeutet, dass das Attribut abgeleitet ist. Es kann aus anderen vorliegenden Daten jederzeit berechnet werden.

        :Datentyp: Der Datentyp des Attributs. Es können primitive oder auch komplexe Datentypen sein.

        :Multiplizität: Die Anzahl der Instanzen, die das Attribut haben kann. Übliche Multiplizitäten sind 0..1 (d. h. optional), 1 (d. h. genau einmal), 0..* (d. h. beliebig oft), 1..* (d. h. mind. einmal), 2..*.



.. class:: transition-fade

Modellierung einer Party - Operationen/Methoden 
---------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/uml-cd/klassen.svg
            :alt: Klassen
            :height: 1000px
            :align: center

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_einfach.svg
            :alt: Einfache Attribute
            :height: 1000px
            :align: center

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_mit_default.svg
            :height: 1000px
            :align: center    

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_mit_mengen.svg
            :height: 1000px
            :align: center            

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_keine.svg
            :height: 1000px
            :align: center            
    
    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_abgeleitet.svg
            :height: 1000px
            :align: center            

    .. layer:: overlay incremental faded-to-white

        :raw-html:`&nbsp;`

    .. layer:: overlay incremental

        .. image:: images/uml-cd/methoden_einfach.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/methoden_mit_in_out.svg
            :height: 1000px
            :align: center  


.. supplemental::

    Methoden bzw. Operationen sind die Verhaltensbeschreibungen einer Klasse. Sie beschreiben, was ein Objekt einer Klasse tun kann.

    Grundlegende Methodendeklarationen:

    :Syntax: [<Sichtbarkeit>] <Methodenname> [ **(** <Parameterliste> **)** ] [ **:** <Rückgabetyp>]

    .. container:: smaller line-above

        :Sichtbarkeiten: (*wie bei Attributen*)

        :Parameterliste: Die Liste der Parameter, die die Methode erwartet. 

            :Syntax: <Übergaberichtung> <Parametername> **:** <Datentyp> [ **[** <Multiplizität> **\]** ] [ **=** <Defaultwert>]

            :Übergaberichtung: Die Übergaberichtung gibt an, ob der Parameter nur gelesen (**in**), nur beschrieben (**out**) oder sowohl gelesen als auch beschrieben (**inout**) wird. Wird die Übergaberichtung nicht explizit angegeben, so wird **in** angenommen.

            :Multiplizität: (*wie bei Attributen*)

        :Rückgabetyp: Der Datentyp des Rückgabewertes der Methode. Es können primitive oder auch komplexe Datentypen sein.



.. class:: transition-fade

Modellierung einer Party - Beziehungen 
---------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/uml-cd/klassen.svg
            :alt: Klassen
            :height: 1000px
            :align: center

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_einfach.svg
            :alt: Einfache Attribute
            :height: 1000px
            :align: center

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_mit_default.svg
            :height: 1000px
            :align: center    

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_mit_mengen.svg
            :height: 1000px
            :align: center            

    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_keine.svg
            :height: 1000px
            :align: center            
    
    .. layer:: overlay 

        .. image:: images/uml-cd/attribute_abgeleitet.svg
            :height: 1000px
            :align: center            

    .. layer:: overlay 

        .. image:: images/uml-cd/methoden_einfach.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay

        .. image:: images/uml-cd/methoden_mit_in_out.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/assoziation_einfach.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/assoziation_gerichtet.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/assoziation_komposition.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/assoziation_aggregation.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/assoziation_generalisierung.svg
            :height: 1000px
            :align: center  

    .. layer:: overlay incremental

        .. image:: images/uml-cd/benennung.svg
            :height: 1000px
            :align: center  


.. supplemental::

    Um zu beschreiben, wie Instanzen der Klassen miteinander in Verbindung stehen, unterscheiden wir folgende grundlegende Beziehungen:   

    - **Assoziation**: Eine Assoziation beschreibt eine Beziehung zwischen zwei Klassen. Sie kann eine Richtung haben und eine Multiplizität. 
  
      Zwischen zwei Klassen können mehrere Assoziationen bestehen.
      
      Eine Assoziation kann zyklisch sein.

      Am Ende einer Assoziation kann ein Name und eine Multiplizität stehen, die die Beziehung aus Sicht der Klasse am anderen Ende der Assoziation beschreiben.

      Ein Pfeil gibt die Navigationsrichtung an.

        Im Beispiel ist explizit modelliert, dass ein Cocktail immer genau von einem Bartender produziert wird. Ein Bartender kann aber mehrere Cocktails produzieren.

      - **Aggregation**: Eine Aggregation (:math:`\lozenge`) ist eine spezielle Form der Assoziation, bei der eine Klasse eine andere Klasse besitzt.
      
      - **Komposition**: Eine Komposition (:math:`\blacklozenge`) ist eine spezielle Form der Aggregation, bei der die Lebensdauer des Besitzers die Lebensdauer des Besitzten bestimmt.
    
          Im Beispiel ist modelliert, dass ein Cocktail aus mehreren Zutaten besteht. Weiterhin gilt, dass nach dem Genuss des Cocktails die Zutaten nicht mehr existieren.

    - **Generalisierung**: Eine Klasse (:java:`Sub`) kann von einer anderen Klasse (:java:`Sup`) *erben* (:java:`Sub` :math:`\triangleright` :java:`Sup`). Die abgeleitete Klasse ist eine Spezialisierung der Basisklasse, die alle Attribute und Methoden der Basisklasse übernimmt und ggf. erweitert. 
      
      .. warning:: 

        Technisch ist es in den meisten Programmiersprachen möglich bestehendes Verhalten ggf. so zu verändern, dass es nicht mehr kompatibel ist mit dem Verhalten der Basisklasse. 
        
        **Dies ist unter allen Umständen zu vermeiden, da es zu schwerwiegenden Fehlern führen kann.**

      (Beispiele wären Methodenparameter oder Rückgabewerte, die auf einmal einen anderen Wertebereich haben. Oder, wenn andere Seiteneffekte auftreten.)




.. class:: integrated-exercise

Übung
-------------------

Im Folgenden wird ein Teil eines Kursmanagementsystems für Universitäten modelliert. Setzen Sie das Modell in UML um. 

- Eine Vorlesung hat immer einen Namen, eine Nummer und einen Raum. 
- Ein Dozent liest ggf. mehrere Vorlesungen. 
- Ein Student besucht in der Regel eine oder mehrere Vorlesungen.
- Zu einer Vorlesung gibt es ggf. mehrere optionale Übungen.
- Eine Prüfung kann entweder eine Klausur oder eine Portfolio-Prüfung sein. Letztere besteht aus einer Präsentation zu einem Thema und einer schriftlichen Ausarbeitung. Beide haben eine festgelegte Anzahl an Punkten. Die Endnote ergibt sich aus dem Durchschnitt der beiden Noten.
- Hat die Veranstaltung eine Portfolio-Prüfung, dann ist jeder Studierende für das gesamte Semester einer bestimmten Studiengruppe zugeordnet.



Modellierungsfehler
-----------------------

.. warning::

    Ein falsches Verständnis von **Generalisierung** kann zu schweren Fehlern in der Modellierung führen.


.. image:: images/srp.svg
    :alt: Quadrat erbt von Rechteck
    :width: 1100px
    :align: center
    :class: incremental

.. supplemental::

   Der schwerwiegende Modellierungsfehler, der in diesem Beispiel gemacht wurde, ist einfach. Es wurde bei der Modellierung vergessen, dass es bei der Programmierung ggf. nicht nur um mathematische Konzepte geht, sondern auch das Verhalten zu berücksichtigen ist. 
   
   In Hinblick auf das Verhalten ist es falsch, dass ein Quadrat von einem Rechteck erbt. Ein Quadrat ist ein Spezialfall eines Rechtecks, bei dem die Seitenlängen gleich sind. Würden wir in unserem Code glauben, dass wir - zum Beispiel - die Breite eines Rechtecks verändern, da der Datentyp :java:`Rectangle` ist, sich hinter dem :java:`Rectangle` ein Objekt vom Typ :java:`Square` verbergen, dann würde sich auch die Höhe des :java:`Rectangle` verändern. Das ist nicht das Verhalten, das wir als Nutzer einer Instanz der Klasse erwarten würden.




.. class:: new-section transition-move-to-top

Sequenzdiagramme
-----------------------------------------------------------------------







