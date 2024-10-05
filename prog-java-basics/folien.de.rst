.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Variablen", "Methoden"
    :description lang=de: Einführung in die Programmierung mit Java
    :id: lecture-prog-java-basics
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


.. class:: animated-symbol

Einführung in die Programmierung mit Java
====================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0

    .. container:: minor far-far-smaller

        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Michael Matt.

.. Foliensatz von Michael Matt: 03_Grundlagen_Teil1.key

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
----------------



"Hello World" - das erste Java-Programm
------------------------------------------------

``HelloWorld.java``\ [#]_

.. include:: code/HelloWorld.java
    :code: java


.. [#]  Die Datei ``HelloWorld.java`` kann `hier <https://delors.github.io/prog-java-basics/code/HelloWorld.java>`__ heruntergeladen werden und mit ``java --enable-preview HelloWorld.java`` ausgeführt werden.

.. supplemental::

    Die Datei enthält ein einfaches Java-Programm, das den Text ``Hello World!`` auf der Konsole ausgibt. 
    
    In der ersten Zeile wird die Methode :java:`main` definiert. Diese die Einstiegsmethode in das Programm. Der Text ``Hello World!`` wird mit der Methode :java:`println` auf der Konsole ausgegeben. Die Methoden :java:`print`, und :java:`println` sind in Java Skripten immer verfügbar und geben den übergebenen Text auf der Konsole aus (ohne bzw. mit Zeilenumbruch am Ende).



Von der Konsole lesen
--------------------------------------------

``HelloYou.java``\ [#]_

.. include:: code/HelloYou.java
    :code: java

.. supplemental::

    Mit Hilfe von ``readln`` können Sie von der Konsole lesen. In Java Skripten ist ``readln`` immer verfügbar. Das Programm gibt den Text ``Hello`` gefolgt von dem eingegebenen Text aus. Die Methode ``readln`` gibt erst den übergebenen String aus und liest dann eine Zeile von der Konsole ein. Der eingelesene Text wird dann an das Wort "Hello " angehängt (mittels des "+" Operators) und als ganzes zurückgegeben.


.. [#] `HelloYou.java <https://delors.github.io/prog-java-basics/code/HelloYou.java>`__     



.. class:: integrated-exercise

Übung - einfache Ein-/Ausgabe
--------------------------------

.. exercise:: Lesen von und Schreiben auf die Konsole

    Schreiben Sie ein Java-Programm (``GutenMorgen.java``), das erst nach dem Namen des Nutzers ``X`` fragt und dann ``Guten Morgen X!`` auf der Konsole ausgibt. Beachten Sie dabei, dass der Text ``X`` durch den eingegebenen Namen ersetzt wird und am Ende ein Ausrufezeichen steht.

    Als zweites soll das selbe Programm dann nach dem Wohnort ``Y`` des Nutzers fragen und dann ``Y ist wirklich schön!`` auf der Konsole ausgeben. 

    Schreiben Sie das Programm und führen Sie es aus!

    .. solution::
        :pwd: ProgrammierenGanzEinfach!

        .. include:: code/GutenMorgen.java
            :code: java



.. class:: transition-fade new-section

Einfache Prozedurale Programmierung
--------------------------------------------


Prozedurale Elemente
---------------------------

.. container:: scrollable
        
    .. class:: incremental

    :Kommentare: Dienen der Codedokumentation und werden vom Compiler ignoriert.

    .. class:: incremental

    :primitive Datentypen: ganze Zahlen (byte, int, long), Fließkommazahlen (float, double), Zeichen (char, :minor:`byte`), Wahrheitswerte (boolean)

    .. class:: incremental

    :Literale: Konstante Wert (z.B. 42, 3.14, 'A', "Hello World!")

    .. class:: incremental

    :Zuweisungen: Speichern eines Wertes in einer Variable 

        (*Eine benannten Stelle im Speicher*)

    .. class:: incremental

    :Ausdrücke: dienen der Berechnung von Werten mit Hilfe von Variablen, Literalen und Operatoren

    .. class:: incremental

    :Kontrollstrukturen: dienen der Ablaufsteuerung mit Hilfe von Schleifen (:java:`while`, :java:`do-while`, :java:`for`) und Verzweigungen (:java:`if`-:java:`else`, :java:`switch`-:java:`case`)

    .. class:: incremental

    :Unterprogramme: Methoden (*Prozeduren* und *Funktionen*), die eine bestimmte Funktionalität wiederverwendbar bereitstellen


Kommentare
---------------------------

- Kommentare dienen der Dokumentation des Codes und helfen anderen Entwicklern den Code zu verstehen. 

.. stack:: incremental

    .. layer:: 

      - In Java unterscheiden wir folgende Arten von Kommentaren:

        .. class:: incremental list-with-explanations

        - Einzeilige Kommentare, die mit ``//`` beginnen und bis zum Ende der Zeile gehen.
        - Mehrzeilige Kommentare, die mit ``/*`` beginnen und mit ``*/`` enden.
        
          Kommentare, die mit ``/**`` beginnen und mit ``*/`` enden, sind so genannte JavaDoc Kommentare und dienen der Erzeugung von Dokumentation.
        - [ab Java 23] Mehrzeilige Kommentare, bei der jede Zeile mit ``///`` beginnt, werden als Markdown basierte JavaDoc Kommentare interpretiert. 

    .. layer:: incremental far-smaller

        Beispiel (ab Java 1.0 - spezifische Tags und HTML)

        .. code:: java

            /**
             * Berechnet die Fakultät von n.
             * 
             * @param n die Zahl, von der die Fakultät berechnet werden soll; (0 <= n <= 20).
             * @return die Fakultät von n. 
             */
            long fak(long n){ // TODO mögliche Fehlerfälle abfangen
                /* Die Verwendung von long als Datentyp limitiert uns auf n <= 20;
                   durch den wechsel von long auf double könnten wir bis n <= 170 rechnen; 
                   sind aber unpräziser. */
                if (n == 0) return 1;
                else return n * fak(n-1);
            }

    .. layer:: incremental far-smaller

        Beispiel (ab Java 23 - spezifische Tags und Markdown)

        .. code:: java

            /// Berechnet die Fakultät von n.
            ///
            /// @param n die Zahl, von der die Fakultät berechnet werden soll;/
            ///          (*0 <= n <= 20*).
            /// @return _die Fakultät von n_.
            long fak(long n){ // TODO mögliche Fehlerfälle abfangen
                /* Die Verwendung von long als Datentyp limitiert uns auf n <= 20;
                   durch den wechsel von long auf double könnten wir bis n <= 170 rechnen; 
                   sind aber unpräziser. */
                if (n == 0) return 1;
                else return n * fak(n-1);
            }

    .. layer:: incremental far-smaller

        Erzeugte Dokumentation (mit Java 23)

        .. image:: images/Fak_java23_javadoc.png
            :alt: Generierte Dokumentation für die Funktion fak(long n) 
            :height: 600px
            :align: center
            :class: box-shadow

.. supplemental::

    .. rubric:: JavaDoc tags

    :@param <name descr>: Dokumentiert einen Parameter einer Methode.
    :@return <descr>: Dokumentiert den Rückgabewert einer Methode.


.. class:: new-section transition-scale

Primitive Datentypen
---------------------------


Verwendung von Datentypen
---------------------------

- Um die erlaubten Werte von Parametern, Variablen und Rückgabewerten genauer spezifizieren zu können, werden Datentypen verwendet.


.. class:: incremental

- Java stellt hierzu primitive Datentypen, Aufzählungen (:java:`enum`), Klassen und Interfaces zur Verfügung

.. stack:: incremental

    .. layer::

        Ein primitiver Datentyp ist z. B. :java:`int` (d. h. :eng:`integer` bzw. :ger:`Ganzzahl`)

        Dieser Datentyp legt fest, dass ein Wert eine Ganzzahl mit dem Wertebereich: :math:`[-2147483648, 2147483647]`


    .. layer:: incremental

        .. csv-table::
            :header: "Art", "Datentyp", "Beispiel"
            :align: center
            :class: highlight-cell-on-hover

            Ganzzahlen, ":java:`byte`, :java:`short`, :java:`int`, :java:`long`", :java:`123`
            Fließkommazahlen, ":java:`float`, :java:`double`", :java:`1.23` oder :java:`3.141d`
            Zeichen, :java:`char`, :java:`'a'`
            Wahrheitswerte, :java:`boolean`, ":java:`true`"

.. supplemental::

    Bitte beachten Sie, dass in Code für Zahlen immer die Englische Schreibweise verwendet wird. D. h. das Dezimalkomma wird durch einen Punkt ersetzt.

    Java kennt neben den primitiven Datentypen auch noch Arrays, Aufzählungen (:java:`enum`) sowie Klassen und Interfaces. Diese werden wir später behandeln.


Ganzzahlige Datentypen - Hintergrund
--------------------------------------------

- Ganzzahlige Werte werden im Speicher als Binärzahlen gespeichert; d. h. als Folge von Nullen und Einsen.
- Um verschieden große Werte zu speichern, stellen Programmiersprachen ganzzahlige Werte mit einer unterschiedlichen Zahl von Bits dar.

.. stack::

    .. layer::

        Zahlen werden immer mit 8 Bit (1 Byte), 16 Bit (2 Byte), 32 (4 Byte) oder 64 Bit (8 Byte) gespeichert. 

        .. hint:: 
          :class: far-smaller
          
          In Java werden Zahlen immer vorzeichenbehaftet gespeichert. D. h. ein Bit wird für das Vorzeichen verwendet; auch wenn es nicht immer benötigt wird.

    .. layer:: incremental

        Umrechnung Binär-Dezimal

        .. csv-table::
            :header: "Binär", "Dezimal"
            :align: center
            :class: highlight-line-on-hover far-smaller

            :java:`0000 0000`, +0
            :java:`0000 0001`, +1
            ..., ...
            :java:`0111 1111`, +127
            :java:`1000 0000`, -128
            ..., ...
            :java:`1111 1111`, -1

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, "Genauigkeit (in Bit)", Wertebereich, Anzahl Werte
            :align: center
            :class: highlight-cell-on-hover

            :java:`byte`, 8, -128 bis 127, :math:`2^8`
            :java:`short`, 16, -32768 bis 32767, :math:`2^{16}`
            :java:`int`, 32, -2147483648 bis 2147483647, :math:`2^{32}`
            :java:`long`, 64, -922337022036854775808 bis 922337022036854775807, :math:`2^{64}`

.. supplemental::

    Die Größenwahl für ``long`` und ``int`` ist teilweise historisch bedingt. Auf gängigen Prozessoren sind jedoch 64 Bit und 32 Bit die natürlichen Größen für Ganzzahlen und können effizient verarbeitet werden. 


Gleitkommatypen - Hintergrund (Konzeptionell)
----------------------------------------------

- Gleitkommazahlen werden in `Java nach Norm IEEE 754 (Seit Java 15 Version 2019) <https://docs.oracle.com/javase/specs/jls/se23/html/jls-4.html#jls-4.2.3>`__ durch die Mantisse :math:`m` und den Exponent :math:`e` dargestellt: :math:`z = m \times 2^e`

.. stack:: incremental

    .. layer::

        Für das Vorzeichen wird das erste Bit verwendet, für Mantisse und Exponent werden zusammen 31- (bei :java:`float`) bzw. 63-Bit (bei :java:`double`)  verwendet.
        
        Die Mantisse und der Exponent sind vorzeichenbehaftete Ganzzahlen.

            Beispiel (vereinfacht)

            .. class:: incremental

            :math:`7 \times 2^{-1} = { 7 \over 2 } = 3.5`

            .. class:: incremental
                
            :math:`-7 \times 2^{-1} = { -7 \over 2 } = -3.5`

            .. class:: incremental
                
            :math:`7 \times 2^{-3} = { 7 \over 8 } = 1.125`

            .. class:: incremental
                
            :math:`7 \times 2^{0} = { 7 \over 1 } = 7`

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, Genauigkeit, Mantisse, Exponent, Wertebereich
            :align: center
            :class: highlight-cell-on-hover smaller
        
            :java:`float`, 32, 23, 8, ca. :math:`-3.4*10^{38}\; \text{bis}\; 3.4 \times 10^{38}`
            :java:`double`, 64, 52, 11, ca. :math:`-1.8*10^{308}\; \text{bis}\; 1.8 \times 10^{308}`



.. supplemental::

    Ganzzahlen :math:`< 2^{24}` können bei Verwendung des Datentyps :java:`float` exakt dargestellt werden; bei :java:`double` sind es Ganzzahlen :math:`< 2^{53}`.

    In beiden Fällen gibt es noch die Möglichkeit +/- Unendlich und NaN (Not a Number) zu repräsentieren.



Gleitkommatypen - Verwendung
--------------------------------------------

.. warning::

    Bei Berechnungen mit Gleitkommazahlen treten Rundungsfehler auf, da nicht alle Werte in beliebiger Genauigkeit dargestellt werden können

    Beispiel: Der Wert ``0.123456789f`` (``float``) wird durch die Darstellung mit Mantisse und Exponent (:math:`m \times 2^e`) zu ``0.12345679``.

    .. container:: incremental

        **Gleitkommazahlen sind somit nicht für betriebswirtschaftliche Anwendungen geeignet.**

    .. container:: incremental margin-top-1em

        Gleitkommazahlen sind z. B. für wissenschaftliche Anwendungen geeignet.

.. supplemental::

    Für betriebswirtschaftliche Anwendungen gibt es den Datentyp :java:`BigDecimal`. Dieser ist aber kein primitiver Datentyp und wird später behandelt. 



Zeichen - Hintergrund
--------------------------------------------

- einzelne Zeichen (z. B. ':java:`a`\ ') werden in Java mit dem Datentyp :java:`char` dargestellt
- ein :java:`char` ist (intern) eine vorzeichenlose Ganzzahl mit 16 Bit (d. h. eine Zahl im Bereich :math:`[0,65536]`), die den Unicode-Wert des Zeichens repräsentiert

  Alle gängigen (westeuropäischen) Zeichen können mit einem :java:`char` dargestellt werden.
  
  .. warning::
    :class: smaller

    Seit Java eingeführt wurde, wurde der Unicode Standard mehrfach weiterentwickelt und heute gibt es Zeichen, die bis zu 32 Bit benötigen. Diese können mit nur einem :java:`char` nicht dargestellt werden und benötigen ggf. zwei :java:`char`\ s.

- Für Zeichenketten (z. B. ``"Hello World"``) existiert ein nicht-primitiver Datentyp :java:`String`.

.. supplemental::

    .. rubric:: Unicode Zeichen und :java:`char`\ s

    Hinweise:
    - 0x1F60E ist der Unicode Codepoint von 😎 und :java:`Character.toChars(<Wert>)` rechnet den Wert um. 
    - In Java ist die Länge (:java:`<String>.length()`) einer Zeichenkette (:eng:`String`) die Anzahl der benötigten :java:`char`\ s und entspricht somit nicht notwendigerweise der Anzahl der (sichtbaren) Zeichen.

    .. code:: java
        :class: far-far-smaller
        :number-lines:

        jshell> var smiley = Character.toChars(0x1F60E)
        smiley ==> char[2] { '?', '?' }

        jshell> var s = new String(smiley)
        s ==> "😎"

        jshell> s.length()
        $1 ==> 2

        jshell> s.getBytes(StandardCharsets.UTF_8)
        $2 ==> byte[4] { -16, -97, -104, -114 }

        jshell> s.codePointCount(0,s.length())
        $3 ==> 1



Wahrheitswerte (Boolesche) - Hintergrund
--------------------------------------------

.. class:: incremental list-with-explanations

- die Wahrheitswerte wahr (:java:`true`) und falsch (:java:`false`) werden in Java mit dem Datentyp :java:`boolean` dargestellt
- häufigste (explizite) Verwendung ist das Speichern des Ergebnisses einer Bedingungsüberprüfung

  (Wahrheitswerte sind zentral für Bedingungsüberprüfungen und Schleifen, werden dort aber selten explizit gespeichert; z. B. beim Test von ``n`` auf 0 im Algorithmus für die Berechnung der Fakultät.)


Konvertierung von Datentypen
--------------------------------------------

.. container:: scrollable

    .. class:: incremental

    - Die (meist verlustfreie,) implizite Konvertierung von Datentypen ist nur in eine Richtung möglich:

        :incremental:`( (byte → short) | char )` :incremental:`→ int` :incremental:`→ long`  :incremental:`→ float` :incremental:`→ double`

    - Konvertierungen in die andere Richtung sind immer explizit anzugeben, da es zu Informationsverlust kommen kann

      Beispiel: :java:`int` zu :java:`byte` (Wertebereich :math:`[-128,127]`)

      .. container:: incremental minor

        Bei der Konvertierung von :java:`int` zu :java:`byte` werden die höherwertigen Bits (9 bis 32) einfach abgeschnitten.

        :incremental:`(byte) 128` :incremental:`⇒ -128`
        
        :incremental:`(byte) 255` :incremental:`⇒ -1`

        :incremental:`(byte) 256` :incremental:`⇒ 0`


.. supplemental::

    - Beispiel für die verlustbehaftete implizite Konvertierung 

      .. code:: java
        :class: far-far-smaller

        jshell> long l = Long.MAX_VALUE - 1;
        l ==> 9223372036854775806

        jshell> float f = l
        f ==> 9.223372E18

        jshell> f == l
        $1 ==> true                     // Warum ?
        
        jshell> ((long) f) == l
        $2 ==> false

        jshell> ((long) f)
        $3 ==> 9223372036854775807      // == Long.MAX_VALUE

    - Wahrheitswerte können nicht konvertiert werden.



.. class:: new-section transition-scale

Literale
---------------------------


Literale - Übersicht
--------------------------------------------

Literale stellen konstante Werte eines bestimmten Datentyps dar

.. csv-table::
    :header: Datentyp, Literal (Beispiele)
    :align: center
    :class: highlight-cell-on-hover

    :java:`int`, :minor:`Dezimal:` 127 :minor:`; Hexadezimal:` 0xcafebabe\ [#]_ :minor:`; Oktal:` 010 :minor:`; Binär:` 0b1010
    :java:`long`, 123_456_789l oder 123456789L  :minor:`("_" dient nur der besseren Lesbarkeit)`
    :java:`float`, 0.123456789f oder 0.123456789F
    :java:`double`, "0.123456789 oder 0.123456789d oder 0.123456789D"
    :java:`char`, "'a' (Zeichen-Darstellung) oder 97 (Zahlen-Darstellung) oder 
    '\u0061' (Unicode-Darstellung) oder Sonderzeichen (siehe nächste Folie)"
    :java:`String`, \"Hallo\"
    :java:`boolean`, true oder false

.. [#] 0xcafebabe ist der Header aller kompilierten Java-Klassen-Dateien.



Literale - Sonderzeichen ("\\" ist das Escape-Zeichen)
-------------------------------------------------------

.. csv-table::
    :align: center
    :class: highlight-cell-on-hover
    :header: Datentyp, Literal (Beispiele)

    \\\', Einfaches Hochkomma
    \\\", Doppeltes Hochkomma
    \\ \ \\, Backslash
    \\b, Rückschrittaste (backspace)
    \\f, Seitenvorschub (form feed)
    \\n, Zeilenschaltung (line feed)
    \\t, Tabulator
    \\r, Wagenrücklauf


.. class:: new-section transition-scale

Variablen und Konstanten
---------------------------


Variablen - Übersicht
--------------------------------------------

.. stack:: 

    .. layer::

        - Variablen stellen einen logischen Bezeichner für einen Wert eines bestimmten Datentyps dar.
        - Variablen müssen erst deklariert werden. Danach können sie weiter initialisiert werden, wenn der Standardwert nicht ausreicht.
        
            .. class:: incremental

            :Deklaration: Variablennamen und Datentyp werden festgelegt

            .. class:: incremental

            :Initialisierung (optional): Variablen werden mit einem bestimmten Wert versehen
        
        .. class:: incremental

        - der Wert einer Variablen kann jederzeit geändert werden

    .. layer:: incremental

        *Beispieldeklaration und -initialisierung*

        .. include:: code/Variables.java
            :code: java
            :class: far-smaller



Konstanten - Übersicht
--------------------------------------------


.. stack:: 

    .. layer::

      - Konstanten sind Variablen, die nach der Initialisierung nicht mehr verändert werden können
      - Konstanten werden in Java mit dem Schlüsselwort :java:`final` deklariert
      - Es wird überprüft, dass keine weitere Zuweisung erfolgt
      - Konvention: Konstanten werden in Großbuchstaben geschrieben

    .. layer:: incremental

      *Beispieldeklaration und -initialisierung*

      .. include:: code/Constants.java
          :code: java
          :class: far-smaller


Bezeichner (:eng:`Identifier`) - Übersicht
--------------------------------------------

.. class:: incremental

- Bezeichner sind Namen für Variablen, Konstanten, Methoden, Klassen, Interfaces, Enums, etc.
- Erstes Zeichen: Buchstabe, Unterstrich (_) oder Dollarzeichen ($); 
- Folgende Zeichen: Buchstaben, Ziffern, Unterstrich oder Dollarzeichen
- Groß- und Kleinschreibung wird unterschieden
- Schlüsselworte (z. B. :java:`var`, :java:`int`, etc.) dürfen nicht als Bezeichner verwendet werden
- Konvention: 
  
    .. class:: incremental smaller

    - Variablen (z. B. :java:`aktuellerHerzschlag`) und Methoden (z. B. :java:`println`) verwenden *lowerCamelCase* 
    
    - Konstanten verwenden *UPPER_CASE* und Unterstriche (z. B. :java:`GEWICHT_BEI_GEBURT`)
    
    - Klassen, Interfaces und Enums verwenden *UpperCamelCase* (z. B. :java:`BigDecimal`)


.. supplemental:: 
    
    In Java ist es unüblich, das Dollarzeichen ($) in eigenem Code zu verwenden und es wird in der Regel nur von der JVM (der Java Virtual Machine; d. h. der Ausführungsumgebung) verwendet; ein Unterstrich am Anfang des Bezeichners sollte ebenfalls vermieden werden. Ganz insbesondere ist darauf zu verzichten den Unterstrich als alleinige Variablennamen zu verwenden, da der "reine" Unterstrich auch andere Bedeutungen hat bzw. bekommen könnte/wird.


Java Shell
------------------------------------------------

.. stack::

    .. layer::

        - Die Java Shell (``jshell``) ist ein interaktives Werkzeug, das es ermöglicht Java-Code (insbesondere kurze Snippets) direkt auszuführen.
        - Starten Sie die Java Shell mit dem Befehl ``jshell --enable-preview`` in der Konsole.
        - Den gültigen Java-Code können Sie direkt in der Java Shell eingeben oder über ``/edit`` als Ganzes bearbeiten.
        - Sie beenden die Java Shell mit dem Befehl ``/exit``.
        - Die Java Shell eignet sich insbesondere für das Ausprobieren von Code-Schnipseln und das Testen von Methoden.

    .. layer:: incremental  

        .. code:: Java
            :class: far-smaller

            # jshell --enable-preview

            |  Welcome to JShell -- Version 23
            |  For an introduction type: /help intro

            jshell> var x = "X";
            x ==> "X"

            jshell> x + "Y"
            $2 ==> "XY"

            jshell> $2.length()
            $3 ==> 2


.. class:: integrated-exercise

Übung - Bezeichner
--------------------------------

Welche der folgenden Bezeichner sind (a) ungültig, (b) gültig aber sollten dennoch nicht verwendet werden oder (c) gültig und entsprechen den Konventionen?


.. exercise:: Bezeichner

    .. container:: three-columns far-smaller

        .. container:: column


            .. code:: Java
                :number-lines:
            
                var 1a = ...
                var 1_a = ...
                var _1a = ...
                var a1 = ...

        .. container:: column

            .. code:: Java
                :number-lines: 5

                int i;
                int _i;
                float $$f;
                final float E = ...;

        .. container:: column

            .. code:: Java
                :number-lines: 9

                String Wohnort;
                String ortDerGeburt;
                void BucheFlug(){...}
                class FlugBuchungen{...}

    .. solution::
        :pwd: Bezeichner_Sind-Wichtig

        :Ungültig: 1. und 2.
        :Gültig aber nicht verwenden: (3.), (6.) , 7., 9., 11.
        :Gültig und entsprechen den Konventionen: (3.), 4., 5., (6.), 8., 10., 12.



.. class:: integrated-exercise

Übung - Variablen und Konstanten
--------------------------------

.. hint::
    :class: far-far-smaller

    Für diese Aufgabe können Sie sowohl die Java Shell verwenden als auch Ihren Code in eine Datei schreiben. Denken Sie in diesem Fall daran, dass der Code in einer Methode :java:`main` stehen muss (:java:`void main(){ <IHRE CODE> }`).

.. exercise:: Grundlegende Datentypen


  - Deklarieren und initialisieren Sie eine Variable x mit dem Ganzzahlwert 42. 
  
    - Welche Datentypen können Sie verwenden, wenn eine präzise Darstellung des Wertes notwendig ist? 
    - Welcher Datentyp wird verwendet, wenn Sie keinen Typ angeben (d. h. wenn Sie :java:`var` schreiben bzw. anders ausgedrückt welchen Typ hat das Literal ``42``)? 

  - Weisen Sie den Wert der Variable ``x`` einer Variable ``f`` vom Typ :java:`float` zu. 
  - Ändern Sie den Wert der Variablen ``x``. Welche Auswirkungen hat das auf die Variable ``f`` vom Typ :java:`float`?
  - Deklarieren und initialisieren Sie die Konstante π (Wert 3.14159265359).

  .. solution::
        :pwd: DatentypenKonstantenUndVariablen

        Der Wert 42 kann von allen primitiven Datentypen präzise dargestellt werden. Wenn Sie 
        keinen Typ angeben, wird der Typ :java:`int` verwendet.

        .. code:: Java
            :class: far-smaller
 
            jshell> int i = 42
            i ==> 42

            jshell> byte b = 42
            b ==> 42

            jshell> char c = 42
            c ==> '*'

            jshell> short s = 42
            s ==> 42

            jshell> long l = 42
            l ==> 42

            jshell> float f = 42
            f ==> 42.0

            jshell> double d = 42
            d ==> 42.0

            jshell> var v = 42
            v ==> 42

            jshell> float vf = v
            vf ==> 42.0

            jshell> v = 43 // Änderung von v
            v ==> 43

            jshell> vf // hat keine Auswirkung auf vf, da vf eine Kopie von v ist
            vf ==> 42.0

            jshell> double PI = 3.14159265359d // π wäre auch ein gültiger, aber ungewöhnlicher Bezeichner 
            d ==> 3.14159265359




.. class:: new-section transition-scale

Ausdrücke und Operatoren
---------------------------


Ausdrücke und Operatoren - Übersicht
--------------------------------------------

- Berechnungen erfolgen über Ausdrücke, die sich aus Variablen, Konstanten, Literalen, Methodenaufrufen und Operatoren zusammensetzen.

.. class:: incremental list-with-explanations 
    
- Jeder Ausdruck hat ein Ergebnis (d. h. Rückgabewert).

  Beispiel: (``age + 1``) addiert zwei Werte und liefert das Ergebnis der Addition zurück.
  
- Einfache Ausdrücke sind Variablen, Konstanten, Literale und Methodenaufrufe.
- Komplexe Ausdrücke werden aus einfachen Ausdrücken und Operatoren (z. B. +, -, \*, /, %, >, <, >=, \<=) zusammengesetzt
- Ergebnisse von Ausdrücken können insbesondere Variablen zugewiesen werden (z.B. :java:`int newAge = age + 1` oder :java:`var isAdult = age >= 18`) 
- Ausdrücke, die einen Wahrheitswerte ergeben können zusätzlich in Bedingungen (z. B. :java:`if(age + 5 >= 18) ...`) verwendet werden.



Ausdrücke und Operatoren - Beispiele
--------------------------------------------

.. include:: code/Expressions.java
    :code: java
    :class: far-smaller


Operatoren und Operanden in der Mathematik
--------------------------------------------

.. container:: smaller

    **Binäre/Zweistellige Operatoren** (:eng:`Binary Operators`)

    *Addition*

    .. math::

        \begin{matrix}
        \text{1. Operand} & \text{Operator} & \text{2. Operand}  \\
        1 & + & 2  \\
        \end{matrix}

.. container:: incremental smaller

    **Unäre/Einstellige Operatoren** (:eng:`Unary Operators`)

    *Negation*

    .. math::

        \begin{matrix}
        \text{Operator} & \text{Operand}  \\
        - & ( 2 )  \\
        \end{matrix}


    *Fakultät*

    .. math::

        \begin{matrix}
        \text{Operator} & \text{Operand}  \\
        2 & !  \\
        \end{matrix}


Operatoren
--------------------------------------------

- Operatoren sind spezielle Zeichen, die auf Variablen, Konstanten und Literale angewendet werden, um Ausdrücke zu bilden.

.. class:: incremental list-with-explanations

- Die Auswertungsreihenfolge wird durch die Priorität der Operatoren bestimmt. 
  
  (Wie aus der Schulmathematik bekannt gilt auch in Java: ``*`` oder ``/`` vor ``+`` und ``-``.)

- Runde Klammern können verwendet werden, um eine bestimmt Auswertungsreihenfolge zu erzwingen bzw. dienen zur Strukturierung

- Es gibt Operatoren, die auf eine, zwei oder drei Operanden angewendet werden: diese nennt man dann ein-, zwei- oder dreistellige Operatoren. 
- Für einstellige Operatoren wird die Präfix- oder Postfix-Notation (z.B. :java:`++a` oder :java:`a++`) verwendet, 
- Für mehrstellige Operatoren wird die Infix-Notation (z.B. :java:`a + b`) verwendet



Klassifikation der Operatoren
--------------------------------------------

.. class:: incremental list-with-explanations

- Arithmetische Operatoren (auf numerische Datentypen)
- Vergleichsoperatoren 
- Logische Operatoren (auf boolean Datentypen)
- Bedingungsoperatoren
- Bitoperatoren (auf ganzzahligen Datentypen)
- Zuweisungs- und Verbundoperatoren (auf alle Datentypen)
- Konkatenationsoperator (String)
- Explizite Typkonvertierung 

.. supplemental::

    Einige Operatoren sind nur auf bestimmten Datentypen anwendbar. So sind Vergleichsoperatoren wie ``<=`` oder ``>=`` nur auf numerische Datentypen anwendbar, aber ``==`` und ``!=`` auf allen Typen. Es gilt immer, dass die linke und die rechte Seite Typkompatibel sein müssen; mit anderen Worten wir können nur Dinge vergleichen, die den gleichen Typ haben oder für die eine automatische Typumwandlung möglich ist. Ein Vergleich von einem String und einer Zahl ist z. B. nicht möglich.

    *Beispiel für unzulässigen Vergleich:*

    .. code:: bash

        jshell> "s" == 1
        |  Error:
        |  bad operand types for binary operator '=='
        |    first type:  java.lang.String
        |    second type: int
        |  "s" == 1


.. class:: no-title center-child-elements transition-move-left

Verwendung der JShell
--------------------------------------------

.. hint:: 

    Wenn Sie die folgenden Codeschnipsel (:eng:`Snippets`) in der Java Shell (``jshell``) ausführen möchten, dann müssen sie noch die Methoden :java:`println` und :java:`readln` definieren: :java:`void println(Object o) { System.out.println(o); }` und :java:`String readln(String s) { return System.console().readln(s); }`. 

    .. container:: incremental smaller margin-top-1em

        Alternativ können Sie den unten verlinkten Code direkt in die JShell laden:

        .. code:: Shell

            jshell --enable-preview <DATEINAME>

    .. container:: incremental smaller margin-top-1em
         
        Alternative können Sie ein Java Script schreiben (inkl. ``main`` Methode). In diesem Fall sind die beiden Methoden direkt verfügbar und müssen nicht extra deklariert werden.

    .. container:: incremental smaller margin-top-1em

        Ich empfehle Ihnen, die Beispiele händisch einzugeben, dann lernen Sie mehr!

.. class:: transition-fade

Zweistellige Arithmetische Operatoren 
----------------------------------------------------------

.. stack::

    .. layer:: 

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :class: highlight-line-on-hover

            ``+``, x + y, Summe von x und y (Additions-Operator)
            ``-``, x - y, Differenz von x und y (Subtraktions-Operator)
            ``*``, x * y, Produkt von x und y (Multiplikations-Operator)
            ``/``, x / y, Quotient von x und y (Divisions-Operator)
            ``%``, x % y, Rest der ganzzahligen Division von x und y (Modulo-Operator)

    .. layer:: incremental

        JShell-Beispiel: `ArithmetischeOperatoren.jshell.java <./code/ArithmetischeOperatoren.jshell.java>`__

        .. include:: code/ArithmetischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2
            :end-before: // Einstellige Operatoren


        .. exercise:: Zweistellige Operatoren - welche Werte werden ausgegeben?
            
            .. solution::
                :pwd: WieZuErwarten

                8, 15, 1 (Ganzzahlarithmetik!), 2


.. supplemental::

    Andere Sprachen (z. B. JavaScript oder Python) haben häufig noch ``**`` für die Potenzierung. Dies ist in Java über ``Math.pow`` möglich.



Einstellige Arithmetische Operatoren 
----------------------------------------------------------

.. stack::

    .. layer::

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :class: highlight-line-on-hover

            ``+``, +x, Positiver Wert von x
            ``-``, -x, Negativer Wert von x
            (Präfix) ``++``, ++x, Prä-inkrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}+1; x_{neu} \}`
            ``++`` (Postfix), x++, Post-inkrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}+1; x_{alt} \}`
            (Präfix) ``--``, --x, Prä-dekrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}-1; x_{neu} \}`
            ``--`` (Postfix), x--, Post-dekrement: Gleichbedeutend mit :math:`\{ x_{neu}=x_{alt}-1; x_{alt} \}`

    .. layer:: incremental

        JShell-Beispiel: `ArithmetischeOperatoren.jshell.java <./code/ArithmetischeOperatoren.jshell.java>`__

        .. include:: code/ArithmetischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 12

        .. exercise:: Einstellige Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: Achtung!

                6, 6, -7


Zweistellige Vergleichsoperatoren
----------------------------------------------------------

.. stack::

    .. layer::

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``==``, x == y, "Überprüft, ob die Werte von x und y **gleich** sind"
            ``!=``, x != y, "Überprüft, ob der Werte von x und y **ungleich** sind"
            ``<``, x < y, "Überprüft, ob der Wert von x **kleiner** dem Wert von y ist"
            ``<=``, x <= y, "Überprüft, ob der Wert von x **kleiner oder gleich** dem Wert von y ist"
            ``>``, x > y, "Überprüft, ob der Wert von x **größer** dem Wert von y ist"
            ``>=``, x >= y, "Überprüft, ob der Wert von x **größer oder gleich** dem Wert von y ist"

    .. layer:: incremental

        JShell-Beispiel: `Vergleichsoperatoren.jshell.java <./code/Vergleichsoperatoren.jshell.java>`__

        .. include:: code/Vergleichsoperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2

        .. exercise:: Einstellige Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: Achtung!

                Strings:

                    true, false, true

                Numerische Werte:

                    true, true, false


Ein- und zweistellige logische Operatoren
----------------------------------------------------------

.. stack:: 

    .. layer:: 

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``!``, !x, Negation (Aus true wird false und umgekehrt)
            ``&``, x & y, Logisches UND (AND)
            ``&&``, x && y, Bedingtes logisches UND (AND Short-circuit Evaluation)
            ``|``, x | y, Logisches ODER (OR)
            ``||``, x || y, Bedingtes logisches ODER (OR Short-circuit Evaluation)
            ``^``, x ^ y, Logisches ENTWEDER-ODER (XOR :eng:`exclusive OR`)

    .. layer:: incremental

        .. rubric:: Wahrheitstabelle

        .. csv-table::
            :header: 
            :width: 100%
            :class: highlight-line-on-hover

            x, y, !x, "x & y oder x && y", "x | y oder x || y", x ^ y
            true, true, false, true, true, false
            true, false, false, false, true, true
            false, true, true, false, true, true
            false, false, true, false, false, false

    .. layer:: incremental

        JShell-Beispiel: `LogischeOperatoren.jshell.java <./code/LogischeOperatoren.jshell.java>`__

        .. include:: code/LogischeOperatoren.jshell.java
            :code: java
            :class: far-smaller margin-bottom-1em
            :number-lines:
            :start-line: 2

        .. exercise:: Logische Operatoren - welche Werte werden ausgegeben?

            .. solution::
                :pwd: &&FehlerVoraus!

                ``&`` und ``&&`` Vergleiche:

                    true, false, ⚡️ (ArithmeticException), true, true

                ``||`` basierte Vergleiche: 

                    true, true, ⚡️ (ArithmeticException)

.. supplemental::

    - Der Unterschied zwischen ``&`` und ``&&`` ist, dass ``&&`` nur den rechten Operanden auswertet, wenn der linke Operand ``true`` ist. 
    - Der Unterschied zwischen ``|`` und ``||`` ist, dass ``||`` nur den rechten Operanden auswertet, wenn der linke Operand ``false`` ist. 
    
    Mit anderen Worten bei ``&&`` und ``||`` wird der Ausdruck nur so weit ausgewertet, wie nötig ist, um das Ergebnis des Ausdrucks als Ganzes zu bestimmen. 


.. class:: integrated-exercise transition-scale

Übung
--------------------------------

.. exercise:: Vergleichsoperatoren

    Lesen Sie zwei Zahlen von der Console ein (siehe `Von der Konsole lesen`_) und vergleichen Sie diese auf Gleichheit. Speichern Sie das Ergebnis in einer Variable und geben Sie das Ergebnis danach auf der Konsole aus.

    Zum  Konvertieren der eingelesenen Zeichenketten in Zahlen verwenden Sie die Methode :java:`Integer.parseInt(<EINGABE>)`. Sie können hier den eingelesen String direkt an die Methode übergeben oder vorher in einer Variable speichern.
    
    .. container:: smaller

        Denken Sie daran, dass Ihr Code in die ``main`` Methode gehört:

        .. code:: java
            :class: far-smaller

            void main() {
                // Ihr Code
            }

        Schreiben Sie ein vollständiges Java Script, dass Sie mit dem Java Interpreter (``java --enable-preview <JAVA-DATEI>``) ausführen können.

    .. solution::
        :pwd: NamenEinlesenUndVergleichen

        .. include:: code/NamenEinlesenUndVergleichen.java
            :code: java
            :class: far-smaller
            :number-lines:


.. class:: transition-fade

Bedingungsoperator
----------------------------------------------------------

.. stack::

    .. layer::

        Der Bedingungsoperator:

            |    <Bedingungsausdruck :math:`c`> ``?`` 
            |        <auszuwertender Ausdruck :math:`a_{(c\,wahr)}` falls :math:`c` wahr > 
            |        : 
            |        <auszuwertender Ausdruck :math:`a_{(c\,falsch)}` falls :math:`c` falsch/unwahr>

        liefert in Abhängigkeit eines Ausdrucks ``c`` (der einen Wahrheitswert liefert) das Ergebnis des ersten Ausdrucks oder des zweiten Ausdrucks  zurück.

    .. layer:: incremental

        :math:`c\; ?\; a_{(c\,wahr)}\; :\; a_{(c\,falsch)}` 

        Beide Ausdrücke :math:`a_{(c\,wahr)}` und :math:`a_{(c\,falsch)}` müssen entweder numerische Werte oder boolean Werte oder Instanzen einer Klasse zurück liefern (d. h. Werte die implizit ineinander konvertiert werden dürfen)

        Von den beiden Ausdrücken wird *nur ein Ausdruck ausgewertet*.

    .. layer:: incremental

        **Beispiele**

        .. code:: java
            :class: far-smaller 

            int n = 0;
            n == 0 ? 1 : 2 

        .. container:: incremental

            Verschachtelung ist möglich aber *nicht*   empfehlenswert:

            .. code:: java
                :class: far-smaller 

                int alter = Integer.parseInt(readln("Wie alt sind Sie?"));
                alter < 18 ? 
                    "jugendlicher" : 
                    alter < 65 ? 
                        "erwachsener" : 
                        "senior"; 


Bitoperatoren
----------------------------------------------------------


.. stack::

    .. layer::

        Bitoperatoren (``>>``, ``<<``, ...) arbeiten auf der binären Darstellung der numerischen, primitiven Datentypen für Ganzzahlen.

        Bitoperationen werden häufig für spezielle Algorithmen verwendet, um die gleiche Operation auf mehreren Daten (den Bits) gleichzeitig anzuwenden (1 CPU Zyklus). Ein Beispiel ist das Ver-/Entschlüsseln von Daten (insbesondere mit ``XOR``).

        Bestimmte mathematische Operationen (z. B. Division durch :math:`2^x`) können durch Bitoperationen ersetzt werden, die effizienter sind (z. B. :java:`16 / 4 == 16 >> 2`).

    .. layer:: incremental

        .. csv-table::
            :header: Operator, Anwendung, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            ``~``, ~x, Bitweise-Negation
            ``&``, x & y, Bitweise UND
            ``|``, x | y, Bitweise ODER
            ``^``, x ^ y, Bitweise ENTWEDER-ODER
            ``<<``, x << y, Bits von x werden um y Positionen nach links verschoben und von rechts mit 0 aufgefüllt
            ``>>``, x >> y, Bits von x werden um y Positionen nach rechts verschoben und von links mit dem höchsten Bit aufgefüllt
            ``>>>``, x >>> y, Bits von x werden um y Positionen nach rechts verschoben und von links mit 0 aufgefüllt

    .. layer:: incremental

        Bits verschieben (:eng:`shiften`) um eine bestimmte Anzahl von Positionen:

        .. code:: java
            :class: far-smaller

            jshell> Integer.toBinaryString(Integer.MIN_VALUE)
            $1 ==> "10000000000000000000000000000000"

            jshell> Integer.toBinaryString(Integer.MIN_VALUE >> 31)
            $2 ==> "11111111111111111111111111111111"

            jshell> Integer.toBinaryString(Integer.MIN_VALUE >>> 31)
            $3 ==>                                "1"

    .. layer:: incremental

        Verschlüsselung mit XOR (`EncryptionWithXOR.jshell.java <./code/EncryptionWithXOR.jshell.java>`__):

        .. code:: java
            :class: far-smaller

            final var key = new java.util.Random().nextInt();   
            Integer.toBinaryString(key);             // ==> "1001101011000011100110101001110"
            
            final var income = 13423;
            Integer.toBinaryString(income);          // ==>                  "11010001101111"
            
            // Verschlüsselung von "income" mit "key" mit Hilfe von XOR:
            final var encryptedIncome = income ^ key;
            Integer.toBinaryString(encryptedIncome); // ==> "1001101011000011111100100100001"

        .. warning::
            :class: far-smaller incremental

            Die dargestellte Verschlüsselung mit XOR ist die Grundlage aller modernen Verschlüsselungsalgorithmen, aber es gibt sehr viel zu beachten, um eine sichere Verschlüsselung zu gewährleisten. 



Zuweisungs- und Verbundoperatoren
----------------------------------------------------------


.. stack::

    .. layer::

        Zuweisungs- und Verbundoperatoren weisen einer Variablen einen neuen Wert zu (z. B. :java:`int newAge = age + 1;`).

        Die Variable steht auf der linken Seite des Operators.

        Der Ausdruck zur Berechnung des neuen Wertes ist durch den Operator selbst und den Ausdruck auf der rechten Seite festgelegt.

        Das Ergebnis des kompletten Ausdruckes ist der zugewiesene Wert mit dem entsprechenden Datentyp. 

    .. layer:: incremental

        Standardbeispiele:

        .. code:: java
            :class: far-smaller

            jshell> int age = 1;
            age ==> 1

            jshell> age = age + 1;
            age ==> 2

            jshell> age += 1;
            age ==> 3


        .. container:: incremental

            Folgendes wäre auch erlaubt, aber *nicht* empfehlenswert, da schwer(er) zu lesen:

            .. code:: java
                :class: far-smaller

                jshell> var newAge = age = age + 1;
                newAge ==> 4

                jshell> var newAge = age += 1;
                newAge ==> 5


    .. layer:: incremental

        .. csv-table::
            :header: Operator, Bedeutung
            :width: 100%
            :class: highlight-line-on-hover

            x = y, **Zuweisung des Wertes** von y an x
            x <Operator>= y, **Zuweisung des Wertes** von x <Operator> y an x

        Operatoren: ``+``, ``-``, ``*``, ``/``, ``%``, ``&``, ``|``, ``^``, ``<<``, ``>>``, ``>>>``

        Zum Beispiel: :java:`x <<= y` ist gleichbedeutend mit :java:`x = x << y`.



String Konkatenation (Verbinden von Zeichenketten)
----------------------------------------------------------


.. stack::

    .. layer::

        Literale, Variablen, Konstanten vom Datentyp String werden durch den Konkatenationsoperator + zu einem neuen String-Wert verkettet.

    .. layer::

        .. code:: java
            :class: far-smaller

            jshell> final String name = "Max";
            name ==> "Max"

            jshell> String greeting = "Hallo " + name + "!";
            greeting ==> "Hallo Max!"


Implizite Typkonvertierung
----------------------------------------------------------

Bei Zuweisungen und arithmetischen Operationen werden die Datentypen von Operanden unter bestimmten Umständen implizit konvertiert.

.. stack::

    .. layer::

        .. class:: incremental list-with-explanations

        - Bei arithmetischen Operationen erfolgt eine Konvertierung in den nächst größeren Datentyp der beteiligten Operanden bzgl. :java:`int`, :java:`long`, :java:`float`, :java:`double`.
        - Bei Operationen auf primitiven, ganzzahligen Datentypen wandelt der Compiler die beteiligten Operanden mindestens in :java:`int` um.
        - Bei Zuweisungen wird das Ergebnis des Ausdruckes auf der rechten Seite in den Datentyp der Variablen auf der linken Seite konvertiert gemäß der Regeln (`Konvertierung von Datentypen`_). 
  
          ⚠️ Die Typkonvertierung erfolgt unabhängig von den konkreten Werten der Operanden.

    .. layer:: incremental
        
        .. code:: java
            :class: far-smaller

            jshell> byte b = 13;
                    short s = Short.MAX_VALUE;
                    float f = b + s;

            b ==> 13
            s ==> 32767
            f ==> 32780.0

    .. layer:: incremental
        
        .. code:: java
            :class: far-smaller

            jshell> int r = Integer.MAX_VALUE + Integer.MAX_VALUE;
            r ==> -2

        .. warning::
            :class: far-smaller incremental

            Hier erfolgt keine Überlaufprüfung und demzufolge auch keine (implizite) Konvertierung (z. B. in Long).

        .. hint::
            :class: far-smaller incremental

            Bei der Addition von :java:`Integer.MAX_VALUE` und :java:`Integer.MAX_VALUE` wird der Wert :java:`-2` zurückgegeben, da der Wert :java:`Integer.MAX_VALUE + 1` den Wert :java:`Integer.MIN_VALUE` ergibt (wir haben einen Überlauf (:eng:`Overflow`)). 

            :java:`Integer.MAX_VALUE + Integer.MAX_VALUE` entspricht also :java:`Integer.MIN_VALUE + (Integer.MAX_VALUE - 1)`.

    .. layer:: incremental

        .. code:: java
            :class: far-smaller

            jshell> short s = Short.MAX_VALUE + Short.MAX_VALUE;
            |  Error:
            |  incompatible types: possible lossy conversion from int to short
            |  short s = Short.MAX_VALUE + Short.MAX_VALUE;



Explizite Typkonvertierung
----------------------------------------------------------

Das Ergebnis eines Ausdruckes kann durch explizite Typkonvertierung in einen anderen primitiven Datentyp umgewandelt werden.


.. stack::

    .. layer::

        .. class:: incremental

        - Bei primitiven Datentypen erlaubt für numerische Datentypen.
        - Wird ein ganzzahliges Ergebnis in einen kleineren ganzzahligen Datentyp konvertiert, dann werden die führenden Bits abgeschnitten.
        - Nachkommastellen gehen bei der Konvertierung von Gleitkommazahlen in Ganzzahlen verloren
        - Bei Konvertierung von :java:`double` in :java:`float` kommt es ebenfalls zu einem Genauigkeitsverlust in der Darstellung (durch Abschneiden der Bits in Mantisse und Exponent)

    .. layer:: incremental smaller

        .. rubric:: Standardfälle

        .. code:: java
            :class: far-smaller

            jshell> int i = 42;
            i ==> 42

            jshell> byte b = (byte) i;
            b ==> 42

        .. container:: incremental

            .. rubric:: Sonderfälle

            .. code:: java
                :class: far-smaller

                jshell> (byte) 128 ;
                $1 ==> -128

                jshell> (byte) 256 ; // Integer.numberOfTrailingZeros(256) == 8
                $2 ==> 0


Überlauf und Unterlauf
----------------------------------------------------------

Unter-/Überschreitet das Ergebnis eines Ausdruckes den minimalen/maximalen Wert des resultierenden Datentyps, erfolgt ein Unter-/Überlauf. (:eng:`Overflow`/:eng:`Underflow`)

.. class:: incremental

- Bei einem Unterlauf bzw. Überlauf werden bei Ganzzahlen die nicht mehr darstellbaren höheren Bits abgeschnitten.
- Bei Fließkommazahlen werden die Konstanten: :java:`Float.NEGATIVE_INFINITY` und :java:`Float.POSITIVE_INFINITY` bzw. :java:`Double.NEGATIVE_INFINITY` und :java:`Double.POSITIVE_INFINITY` verwendet.

.. code:: java
    :class: far-smaller incremental

    Integer.toBinaryString(Integer.MIN_VALUE) //    "10000000000000000000000000000000"
    Integer.toBinaryString(Integer.MIN_VALUE - 1) //"01111111111111111111111111111111"
    Long.toBinaryString(Integer.MIN_VALUE -1l)
                 // "1111111111111111111111111111111101111111111111111111111111111111"

.. supplemental::

    In der Praxis wird häufig der Begriff Overflow verwendet, wenn bei einer Berechnung der Wertebereich eines Datentyps nicht ausreicht, um das Ergebnis zu speichern. D. h. die Unterscheidung zwischen Über- und Unterlauf ist nicht immer eindeutig.

    Bei Double erfolgt der Überlauf erst, wenn man eine Zahl auf :java:`Double.MAX_VALUE` addiert, die mehr als 292 Stellen vor dem Komma hat.

    .. code:: java
        :class: far-smaller
    
        jshell> Double.MAX_VALUE + 1
        $0 ==> 1.7976931348623157E308

        jshell> Double.MAX_VALUE + 1999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999999999999999999999999999999999999999999999999
        9999999d // Eins gefolgt von 291 Neunen(!)
        $1 ==> 1.7976931348623157E308 // 16 Nachkommastellen

        jshell> Double.MAX_VALUE + 1000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        0000000000000000000000000000000000000000000000000000
        00000000d // 1 gefolgt von 292 Nullen(!)
        $2 ==> Infinity



Auswertungsreihenfolge
----------------------------------------------------------

Die Auswertungsreihenfolge von komplexen Ausdrücken mit mehreren Operatoren wird durch die Priorität der Operatoren bestimmt.\ [#]_

.. class:: incremental list-with-explanations

- Kommen in einem Ausdruck mehrere Operatoren mit gleicher Priorität vor, dann wird der Ausdruck von links nach rechts ausgewertet.
- Ausnahmen sind die Verbund- und Zuweisungsoperatoren die von rechts nach links bewertet werden.
- Klammern haben die höchste Priorität und erzwingen die Auswertung des Ausdrucks in den Klammern zuerst. Klammern dienen aber (insbesondere) auch der Strukturierung von Ausdrücken.


.. [#] Die Regeln sind vergleichbar mit der Schulmathematik: Punkt-vor-Strich-Rechnung.


Priorität der Operatoren
----------------------------------------------------------

.. container:: scrollable

    .. csv-table::
        :header: Operatoren, Beschreibung, Priorität
        :class: highlight-line-on-hover incremental far-smaller
        :width: 100%
        
        "=, +=, -=, ...", Zuweisungs- und Verbundoperatoren, 1 (niedrigste)
        "?:", Bedingungsoperator, 2
        "||", Bedingt logisches ODER, 3
        "&&", Bedingt logisches UND, 4
        "\|", Logisches/Bitweises ODER, 5
        "^", Logisches/Bitweises  ENTWEDER-ODER, 6
        "&", Logisches/Bitweises  UND, 7
        "==, !=", "Vergleich: Gleich, Ungleich", 8
        "<, <=, >, >=", "Vergleich: Kleiner (oder Gleich), Größer (oder Gleich)", 9
        "<<, >>, >>>", Bitweise Schiebeoperatoren, 10
        "+, -", "Addition, Subtraktion, String-Konkatentation", 11
        "\*, /, %", "Multiplikation, Division, Rest", 12
        "++, --, +, - (Vorzeichen), ~, !, (cast)", Einstellige Operatoren, 13 (höchste)


Beispiele zur Auswertungsreihenfolge
----------------------------------------------------------


.. exercise:: Auswertung von Ausdrücken
    
    .. container:: far-smaller

        Sind die folgenden Ausdrücke (a) gültig und wie ist (b) ggf. das Ergebnis der folgenden Ausdrücke und (c) welchen Wert haben die Variablen nach der Auswertung (der neue Wert wird dann für den nachfolgenden Ausdruck verwendet)?

        *Initiale* Belegung der Variablen: :java:`int x = 4, y = 2, z = 3;`.

    .. code:: java
        :class: far-smaller incremental
        :number-lines: 1

        x + y * z / x 

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 2

        ( x + - (float) y * 2 ) / x  ==  ( x + ( ( (float) -y ) * 2 ) )/ x 

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 3

        x + ++y * z++ % x

    .. code:: java
        :class: far-smaller incremental 
        :number-lines: 4

        x < 5 && --y <= 1 || z == 3

    .. code:: java
        :class: far-smaller incremental
        :number-lines: 5

        x << 2 * y >> 1

    .. code:: java
        :class: far-smaller  incremental
        :number-lines: 6

        z & 1 % 2 == 0

    .. code:: java
        :class: far-smaller  incremental
        :number-lines: 7

        (z & 1) % 2 == 0

    .. solution::
        :pwd: WasIstDasErgebnis

        1. 5
        2. true
        3. 5 (nach Ausführung ist y = 3, z = 4)
        4. false (nach Ausführung ist y = 2)
        5. 32 (2*y = 4, 4 << 4 = 64, 64 >> 1 = 32)
        6. <ungültig> (== hat eine höhere Priorität als & und somit würde ein boolean Wert mit einer Zahl verglichen werden)
        7. true



.. class:: integrated-exercise transition-scale

Übung
--------    

.. exercise:: BMI berechnen

    Schreiben Sie ein Java Script, dass den Body-Mass-Index (BMI) berechnet. Lesen Sie das Gewicht in Kilogramm und die Größe in Metern von der Konsole ein und geben Sie den BMI auf der Konsole aus. Geben Sie auch aus, ob die Person Untergewicht, Normalgewicht oder Übergewicht hat. Falls die Person nicht das Normalgewicht hat, geben Sie auch an, wie viel Gewicht sie bis zum Normalgewicht zunehmen oder abnehmen muss.

    Der BMI wird nach folgender Formel berechnet: :math:`BMI = \frac{Gewicht}{Größe^2}`.

    Beispielinteraktion:

    .. code:: text
        :class: far-smaller
    
        Bitte geben Sie Ihr Gewicht in Kilogramm ein: 80
        Bitte geben Sie Ihre Größe in Metern ein: 1.80
        Ihr BMI beträgt 24.69
        Untergewicht: nein
        Normalgewicht: nein
        Übergewicht: 5.897499999999994 kg bis Normalgewicht

    .. solution::
        :pwd: BmiBerechnen

        .. include:: code/BmiBerechnen.java
            :code: java
            :class: far-smaller
            :number-lines:


.. supplemental::

    Denken Sie daran, dass Ihr Code in die ``main`` Methode gehört:

    .. code:: java
        :class: far-smaller

        void main() {
            // Ihr Code
        }

    Denken Sie daran, dass Sie einen Zeichenkette (``String``) in eine Zahl umwandeln können, indem Sie die Methode :java:`Double.parseDouble(<String>)` für Fließkommazahlen verwenden oder :java:`Integer.parseInt(<String>)` für Ganzzahlen.

    Schreiben Sie ein vollständiges Java Script, dass Sie mit dem Java Interpreter (``java --enable-preview <JAVA-DATEI>``) ausführen können.



.. class:: integrated-exercise transition-scale

Übung
--------    

.. exercise:: Umrechnung von Sekunden

    Schreiben Sie ein Java Script, dass die Anzahl von Sekunden in Stunden, Minuten und Sekunden umrechnet. Lesen Sie die Anzahl von Sekunden von der Konsole ein und geben Sie die Umrechnung auf der Konsole aus.

    Beispielinteraktion:

    .. code:: text
        :class: far-smaller
    
        Bitte geben Sie die Sekunden ein: 3455
        0 Stunde(n), 57 Minute(n) und 35 Sekunde(n)

    .. solution::
        :pwd: SekundenUmrechnen

        .. include:: code/SekundenUmrechnen.java
            :code: java
            :class: far-smaller
            :number-lines:





Zusammenfassung
--------------------------------------------

.. class:: incremental

- Variablen sind Speicherorte, die einen Wert enthalten.
- Konstanten sind unveränderliche Werte, die an einem Speicherort gespeichert sind.
- Literale sind konstante Werte, die direkt im Code stehen.
- Operatoren haben eine Priorität und bestimmen die Auswertungsreihenfolge von Ausdrücken.
- Ausdrücke sind Kombinationen von Variablen, Konstanten und Operatoren, die einen Wert ergeben.
- Implizite Typkonvertierung erfolgen automatisch und führen meist zu keinem Verlust von Genauigkeit.
