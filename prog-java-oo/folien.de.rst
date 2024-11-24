.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Objektorientierung", "Software Development"
    :description lang=de: Einführung in die Objekt-orientierte Programmierung mit Java
    :id: lecture-prog-oo
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
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: java(code)
   :language: java


.. class:: animated-symbol

Einführung in die Objekt-orientierte Programmierung
===========================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw.de, Raum 149B
    :Version: 1.0

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues


.. class:: new-section transition-move-to-top

Objekt-orientierte Programmierung mit Java
------------------------------------------------



Was ist Objektorientierte Programmierung?
------------------------------------------

:Definition: Objektorientierte Programmierung (OOP) ist ein Programmierparadigma, das auf den Konzepten von Klassen und **Objekten** basiert, die Daten und Funktionen kapseln.

.. class:: incremental

:Hauptziele:
  Code **erweiterbar**, **strukturierter** und wiederverwendbar gestalten.

.. class:: incremental 

:Hauptprinzipien:
  - **Kapselung** (:eng:`Encapsulation`)
  - **Abstraktion** (:eng:`Abstraction`)
  - **Vererbung** (:eng:`Inheritance`)
  - **Polymorphie** (:eng:`Polymorphism`) („vielerlei Gestalt“)

.. supplemental::

    Während es in der Anfangszeit Programmiersprachen gab, die neben der prozeduralen Programmierung insbesondere auch die objektorientierte Programmierung unterstützten, unterstützen heute fast alle Programmiersprachen auch weitere Paradigmen. Insbesondere die funktionale Programmierung.



Klassen
--------------------

.. container:: scrollable

    :Klasse: Ein Bauplan für Objekte, der beschreibt, welche Daten bzw. Felder und Methoden ein Objekt haben kann.
    
    :Syntax: :java:`class <Klassenname> { ... }`

    .. class:: incremental

    :Beispiele: 
    
        .. rubric:: :java:`Auto` ist eine Klasse.

        .. code:: java
            :class: far-smaller copy-to-clipboard

            class Auto {
                // Felder (gel. auch Attribute genannt)
                String marke;
                int geschwindigkeit; // _aktuelle_ Geschwindigkeit

                // Methoden
                void beschleunigen(int wert) {
                    geschwindigkeit += wert; // Zugriff auf das Feld des Objektes
                }
            }

        .. container:: incremental

            .. rubric:: :java:`Button` (bei der Modellierung grafischer Benutzeroberflächen) ist eine Klasse.

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class Button {
                    String text;
                    int state; // 0: normal, 1: pressed, 2: disabled

                    void registerListener() { ... }
                }

        .. container:: incremental

            .. rubric:: :java:`BigDecimal` (zur Repräsentation von Dezimalzahlen mit „beliebiger“ Präzision) ist eine Klasse.

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class BigDecimal {
                    int scale;
                    int precision;
                    void add(BigDecimal b) { ... }
                }


        .. container:: incremental

            .. rubric:: :java:`File` (zum Zugriff auf Dateien) ist eine Klasse.

            .. code:: java
                :class: far-smaller copy-to-clipboard

                class File {
                    String name;
                    long size;
                    void read() { ... }
                }


Objekte und die Selbstreferenz `this`
------------------------------------------

.. container:: scrollable

    :Objekt: Eine Instanz einer Klasse.

    :Definition: :java:`this` ist eine Referenz auf das aktuelle Objekt. Es wird verwendet, um auf die Felder und Methoden des aktuellen Objekts zuzugreifen.
    
    .. class:: incremental

    :Beispiel: 

        .. include:: code/Auto.java
            :code: java
            :class: far-smaller copy-to-clipboard
            :end-before: void main() {

.. supplemental::

    Wenn es keine Zweideutigkeit gibt, dann kann auf die Angabe von :java:`this` verzichtet werden.



Objekterzeugung/Instanziierung einer Java Klasse
--------------------------------------------------

.. container:: scrollable

    Um eine Objekts zu erzeugen bzw. eine Klasse zu instanziiern, wird der :java:`new` Operator verwendet. 

    Der :java:`new` Operator ...

    .. class:: incremental

    - reserviert den benötigten Speicher, und stellt sicher, dass alle Felder mit dem Defaultwert initialisiert sind.
    - ruft dann den Konstruktor der Klasse auf. 

      Der Konstruktor ist eine spezielle Methode, die einmalig beim Erzeugen eines Objekts aufgerufen wird und der Initialisierung des Objekts dient.
    

    .. class:: incremental

    :Syntax: :java:`new <Klassenname>(<Parameter>)`

    .. class:: incremental

    :Beispiel: 

        `meinAuto` referenziert ein Objekt der Klasse `Auto`. 

        .. code:: java
            :class: far-smaller faded-to-white copy-to-clipboard

            class Auto {
                String marke;           // der Standardwert ist null
                int geschwindigkeit;    // der Standardwert ist 0

                void beschleunigen(int wert) { ... }
            }

        .. code:: java
            :class: far-smaller copy-to-clipboard

            var meinAuto = new Auto(); // Aufruf des impliziten Konstruktors

.. supplemental::

    Der Konstruktor ist eine spezielle Methode, die nur beim Erzeugen eines Objekts aufgerufen wird. Wird kein Konstruktor explizit definiert, wird ein (impliziter) Standardkonstruktor verwendet.

    Der implizite Konstruktor ist ein Konstruktor, der automatisch vom Java compiler generiert wird, wenn kein Konstruktor explizit definiert wurde. Der implizite Konstruktor hat keine Parameter und initialisiert die Felder mit Standardwerten.



Explizite Konstruktoren
---------------------------------------------------------

Ein Konstruktor hat immer den Namen der Klasse und kann Parameter enthalten. 

:Syntax: :java:`<Klassenname>(<Parameter>) { ... }`

.. class:: incremental

:Beispiel: 

    .. code:: java
        :class: far-smaller copy-to-clipboard

        class Auto {
            String marke;           // der Standardwert ist null
            int geschwindigkeit;    // der Standardwert ist 0

            Auto(String marke, int geschwindigkeit) {
                // ⚠️ "this." ist notwendig!
                this.marke = marke;                     
                this.geschwindigkeit = geschwindigkeit;notwendig!
            }
        }

    .. code:: java
        :class: far-smaller incremental copy-to-clipboard

        var meinAuto = new Auto("BMW",0); // Aufruf des impliziten Konstruktors

.. supplemental::

    Ein Konstruktor hat (in Java) keinen Rückgabewert. 

    Es ist möglich, mehrere Konstruktoren zu definieren, wenn diese unterschiedliche Parameter haben.

    Der Konstruktor wird aufgerufen, wenn ein Objekt erzeugt wird. Ein Konstruktor kann auch andere Konstruktoren der Klasse aufrufen.


Verwendung eines Objektes
--------------------------------------------------

Auf Felder und Methoden eines beliebigen Objektes kann über den **Punktoperator** zugegriffen werden.

:Syntax: :java:`<Objektinstanz>.<Attribut/Methode>`

:Beispiel: 

    `meinAuto` referenziert ein Objekt der Klasse `Auto`. 


    .. code:: java
        :class: far-smaller faded-to-white

        class Auto {
            String marke;
            int geschwindigkeit;
            void beschleunigen(int wert) { ... }
        }

    .. code:: java
        :class: far-smaller

        var meinAuto = new Auto();
        meinAuto.marke = "BMW";
        meinAuto.beschleunigen(10);



Abstraktion (Abstraction)
--------------------------------------------------

.. container:: scrollable
        
    :Definition: Abstraktion bedeutet, die wesentlichen Eigenschaften und Funktionen eines Objekts hervorzuheben und Details zu verstecken, die für die Nutzung des Objekts nicht relevant sind.
    :Ziel: Details und Komplexität verstecken; d. h. wir möchten von unnötigen Details abstrahieren.

    .. class:: incremental

    :Beispiel: Eine `Form`-Klasse, die über verschiedene Unterklassen wie `Kreis`, `Quadrat` und `Dreieck` abstrahiert. Alle Formen bieten eine Möglichkeit zur Berechnung der Fläche obwohl diese ggf. sehr verschieden berechnet wird.

        .. code:: java
            :class: far-smaller copy-to-clipboard incremental

            abstract class Form {
                abstract double berechneFlaeche();
            }

        .. container:: two-columns incremental margin-top-1em

            .. container:: column no-separator

                .. code:: java
                    :class: far-smaller copy-to-clipboard

                    class Kreis extends Form {
                        double r = 0.0;
                        double berechneFlaeche() {
                            return Math.PI * r * r;
                        }
                    }

            .. container:: column no-separator 

                .. code:: java
                    :class: far-smaller copy-to-clipboard

                    class Quadrat extends Form {
                        double seite = 0.0;
                        double berechneFlaeche() {
                            return seite * seite;
                        }
                    }




Vererbung (:eng:`Inheritance`)
--------------------------------------------------

:Definition: Erlaubt es, eine Klasse von einer anderen abzuleiten und deren Eigenschaften und Methoden zu erben.
:Vorteile:
  - Wiederverwendbarkeit des Codes
  - Hierarchische Strukturierung
:Beispiel: `Auto` als Basisklasse und `Elektroauto` als abgeleitete Klasse

    .. code:: java
        :class: far-smaller copy-to-clipboard

        class Auto {
            String marke;

            void fahren() {
                System.out.println("Das Auto fährt.");
            }
        }

        class Elektroauto extends Auto {
            int batteriestand;

            void aufladen() {
                System.out.println("Das Elektroauto wird aufgeladen.");
            }
        }


Polymorphie (Polymorphism)
--------------------------------------------------

:Definition: Fähigkeit von Objekten, verschiedene Formen anzunehmen.
:Typen:
  - **Überladen** von Methoden (:eng:`Compile-Time Polymorphism`)
  - **Überschreiben** von Methoden (:eng:`Runtime Polymorphism``)
  
:Vorteil: Ermöglicht flexiblen und dynamischen Code

**Beispiel**: Methode `fahren` wird in verschiedenen Klassen unterschiedlich implementiert.

.. code:: java
    :class: far-smaller

    class Auto {
        void fahren() {
            System.out.println("Das Auto fährt.");
        }
    }

    class Elektroauto extends Auto {
        void fahren() { // Überschreiben der Methode
            System.out.println("Das Elektroauto fährt leise.");
        }
    }



Zusammenfassung und Vorteile von Objekt-orientierter Programmierung\ [#]_
--------------------------------------------------------------------------

:Kapselung: Schützt die Daten und kontrolliert den Zugriff.
:Abstraktion: Vereinfacht die Komplexität des Codes.
:Vererbung: Ermöglicht Code-Wiederverwendung und Hierarchien.
:Polymorphie: Erlaubt flexiblen Code durch unterschiedliche Implementierungen.

.. [#] Diese Vorteile gelten im Wesentlichen für alle objektorientierten Programmiersprachen.


.. class:: integrated-exercise transition-move-to-top

Übung
--------------------------------------------------

.. exercise:: Meine Erste Klassenhierarchie

    Erstelle eine einfache `Tier`-Klasse mit einer Methode `lautGeben()`. Erstelle dann die Klassen `Hund` und `Katze`, die `Tier` erweitern, und überschreibe die Methode `lautGeben()` mit unterschiedlichen Ausgaben.

    .. solution::
        :pwd: DerAnfangIstGemacht

        .. include:: code/Tiere.java
            :code: java
            :number-lines:
            :class: far-smaller





Kapselung (:eng:`Encapsulation`)\ [#]
--------------------------------------------------

:Ziel: 
   Daten eines Objekts vor direktem Zugriff von außen schützen.

   Zugriff auf Daten erfolgt über **Getter** und **Setter**.
:Vorteile:
  - Schutz der Datenintegrität
  - Kontrollierter Zugriff auf die Daten; fördert die Wartbarkeit

.. code:: java
    :class: far-smaller

    class Auto {
        private int geschwindigkeit;

        public int getGeschwindigkeit() {
            return geschwindigkeit;
        }

        public void setGeschwindigkeit(int geschwindigkeit) {
            if (geschwindigkeit >= 0) {
                this.geschwindigkeit = geschwindigkeit;
            }
        }
    }

.. [#] Kapselung dient vor allem dem Programming-in-the-Large. Sprachen wie zum Beispiel Python bieten diesbezüglich zum Beispiel deutlich weniger Konzepte.