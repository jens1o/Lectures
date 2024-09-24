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



"Hello World" - das erste Java-Programm\ [#]_
------------------------------------------------

.. include:: code/HelloWorld.java
    :code: java


.. [#]  Die Datei `HelloWorld.java` kann `hier <https://delors.github.io/prog-java-basics/code/HelloWorld.java>`__ heruntergeladen werden und mit ``java --enable-preview HelloWorld.java`` ausgeführt werden.

.. supplemental::

    Die Datei enthält ein einfaches Java-Programm, das den Text ``Hello World!`` auf der Konsole ausgibt. 
    
    In der ersten Zeile wird die Methode :code:`main` definiert. Diese die Einstiegsmethode in das Programm. Der Text ``Hello World!`` wird mit der Methode :code:`println` auf der Konsole ausgegeben. Die Methoden :code:`print`, und :code:`println` sind in Java Skripten immer verfügbar und geben den übergebenen Text auf der Konsole aus (ohne bzw. mit Zeilenumbruch am Ende).



Von der Konsole lesen\ [#]_
--------------------------------------------

.. include:: code/HelloYou.java
    :code: java

.. supplemental::

    Mit Hilfe von ``readln`` können Sie von der Konsole lesen. In Java Skripten ist ``readln`` immer verfügbar. Das Programm gibt den Text ``Hello`` gefolgt von dem eingegebenen Text aus. Die Methode ``readln`` gibt erst den übergebenen String aus und liest dann eine Zeile von der Konsole ein. Der eingelesene Text wird dann an das Wort "Hello " angehängt (mittels des "+" Operators) und als ganzes zurückgegeben.


.. [#] `HelloWorld.java <https://delors.github.io/prog-java-basics/code/HelloWorld.java>`__     


.. class:: integrated-exercise

Übung - einfache Ein-/Ausgabe
--------------------------------

.. exercise:: Lesen von und Schreiben auf die Konsole

    Schreiben Sie ein Java-Programm, das erst nach dem Namen des Nutzers ``X`` fragt und dann ``Guten Morgen X!`` auf der Konsole ausgibt. Beachten Sie dabei, dass der Text ``X`` durch den eingegebenen Namen ersetzt wird und am Ende ein Ausrufezeichen steht.

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

    :Kontrollstrukturen: dienen der Ablaufsteuerung mit Hilfe von Schleifen (:code:`while`, :code:`do-while`, :code:`for``) und Verzweigungen (:code:`if`-:code:`else`, :code:`switch`-:code:`case`)

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
        - [ab Java 23] Mehrzeilige Kommentare, bei der jede Zeile mit ``///`` beginnt, werden ebenfalls als JavaDoc Kommentare interpretiert.

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

        .. image:: code/Fak_java23_javadoc.png
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

- Java stellt hierzu primitive Datentypen, Aufzählungen (:code:`enum`), Klassen und Interfaces zur Verfügung

.. stack:: incremental

    .. layer::

        Ein primitiver Datentyp ist z. B. :code:`int` (d. h. :eng:`integer` bzw. :ger:`Ganzzahl`)

        Dieser Datentyp legt fest, dass ein Wert eine Ganzzahl mit dem Wertebereich: :math:`[-2147483648, 2147483647]`


    .. layer:: incremental

        .. csv-table::
            :header: "Art", "Datentyp", "Beispiel"
            :align: center
            :class: highlight-cell-on-hover

            Ganzzahlen, ":code:`byte`, :code:`short`, :code:`int`, :code:`long`", :code:`123`
            Fließkommazahlen, ":code:`float`, :code:`double`", :code:`1.23` oder :code:`3.141d`
            Zeichen, :code:`char`, :code:`'a'`
            Wahrheitswerte, :code:`boolean`, ":code:`true`"

.. supplemental::

    Bitte beachten Sie, dass in Code für Zahlen immer die Englische Schreibweise verwendet wird. D. h. das Dezimalkomma wird durch einen Punkt ersetzt.

    Java kennt neben den primitiven Datentypen auch noch Arrays, Aufzählungen (:code:`enum`) sowie Klassen und Interfaces. Diese werden wir später behandeln.


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

            :code:`0000 0000`, +0
            :code:`0000 0001`, +1
            ..., ...
            :code:`0111 1111`, +127
            :code:`1000 0000`, -128
            ..., ...
            :code:`1111 1111`, -1

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, "Genauigkeit (in Bit)", Wertebereich, Anzahl Werte
            :align: center
            :class: highlight-cell-on-hover

            :code:`byte`, 8, -128 bis 127, :math:`2^8`
            :code:`short`, 16, -32768 bis 32767, :math:`2^{16}`
            :code:`int`, 32, -2147483648 bis 2147483647, :math:`2^{32}`
            :code:`long`, 64, -922337022036854775808 bis 922337022036854775807, :math:`2^{64}`

.. supplemental::

    Die Größenwahl für ``long`` und ``int`` ist teilweise historisch bedingt. Auf gängigen Prozessoren sind jedoch 64 Bit und 32 Bit die natürlichen Größen für Ganzzahlen und können effizient verarbeitet werden. 


Gleitkommatypen - Hintergrund
--------------------------------------------

- Gleitkommazahlen werden in Java nach Norm IEEE 754 durch die Mantisse :math:`m` und den Exponent :math:`e` dargestellt: :math:`z = m \times 2^e`

.. stack:: incremental

    .. layer::

        Für das Vorzeichen wird das erste Bit verwendet, für Mantisse und Exponent werden zusammen 31- (bei :code:`float`) bzw. 63-Bit (bei :code:`double`)  verwendet.
        
        Die Mantisse und der Exponent sind vorzeichenbehaftete Ganzzahlen.

            Beispiel (vereinfacht)

            .. class:: incremental

            :math:`7 \times 2^{-1} = { 7 \over 2 } = 3.5`

            .. class:: incremental
                
            :math:`-7 \times 2^{-1} = { -7 \over 2 } = -3.5`

            .. class:: incremental
                
            :math:`-7 \times 2^{-3} = { 7 \over 8 } = 1.125`

            .. class:: incremental
                
            :math:`-7 \times 2^{0} = { 7 \over 1 } = 7`

    .. layer:: incremental

        .. csv-table::
            :header: Datentyp, Genauigkeit, Mantisse, Exponent, Wertebereich
            :align: center
            :class: highlight-cell-on-hover
        
            :code:`float`, 32, 23, 8, ca. :math:`-2*10^{38} \text{bis} 2 \times 10^{38}`
            :code:`double`, 64, 52, 11, ca. :math:`-2*10^{308} \text{bis} 2 \times 10^{308}`

.. supplemental::

    Ganzzahlen :math:`< 2^{23}` können bei Verwendung des Datentyps :code:`float` exakt dargestellt werden; bei :code:`double` sind es Ganzzahlen :math:`< 2^{52}`.



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

    Für betriebswirtschaftliche Anwendungen gibt es den Datentyp :code:`BigDecimal`. Dieser ist aber kein primitiver Datentyp und wird später behandelt. 



Zeichen - Hintergrund
--------------------------------------------

- einzelne Zeichen (z. B. ':code:`a`\ ') werden in Java mit dem Datentyp :code:`char` dargestellt
- ein :code:`char` ist (intern) eine vorzeichenlose Ganzzahl mit 16 Bit (d. h. eine Zahl im Bereich :math:`[0,65536]`), die den Unicode-Wert des Zeichens repräsentiert

  Alle gängigen (westeuropäischen) Zeichen können mit einem :code:`char` dargestellt werden.
  
  .. warning::
    :class: smaller

    Seit Java eingeführt wurde, wurde der Unicode Standard mehrfach weiterentwickelt und heute gibt es Zeichen, die bis zu 32 Bit benötigen. Diese können mit nur einem :code:`char` nicht dargestellt werden und benötigen ggf. zwei :code:`char`\ s.

- Für Zeichenketten (z. B. ``"Hello World"``) existiert ein nicht-primitiver Datentyp :code:`String`.

.. supplemental::

    .. rubric:: Unicode Zeichen und :code:`char`\ s

    Hinweise:
    - 0x1F60E ist der Unicode Codepoint von 😎 und :code:`Character.toChars(<Wert>)` rechnet den Wert um. 
    - In Java ist die Länge (:code:`<String>.length()`) einer Zeichenkette (:eng:`String`) die Anzahl der benötigten :code:`char`\ s und entspricht somit nicht notwendigerweise der Anzahl der (sichtbaren) Zeichen.

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

- die Wahrheitswerte wahr (:code:`true`) und falsch (:code:`false`) werden in Java mit dem Datentyp :code:`boolean` dargestellt
- häufigste (explizite) Verwendung ist das Speichern des Ergebnisses einer Bedingungsüberprüfung

  (Wahrheitswerte sind zentral für Bedingungsüberprüfungen und Schleifen, werden dort aber selten explizit gespeichert; z. B. beim Test von ``n`` auf 0 im Algorithmus für die Berechnung der Fakultät.)


Konvertierung von Datentypen
--------------------------------------------

.. container:: scrollable

    .. class:: incremental

    - Die (meist verlustfreie), implizite Konvertierung von Datentypen ist nur in eine Richtung möglich:

        :incremental:`( (byte → short) | char )` :incremental:`→ int` :incremental:`→ long`  :incremental:`→ float` :incremental:`→ double`

    - Konvertierungen in die andere Richtung sind immer explizit anzugeben, da es zu Informationsverlust kommen kann

      Beispiel: :code:`int` zu :code:`byte` (Wertebereich :math:`[-128,127]`)

      .. container:: incremental minor

        Bei der Konvertierung von :code:`int` zu :code:`byte` werden die höherwertigen Bits (9 bis 32) einfach abgeschnitten.

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

    :code:`int`, 127 
    :code:`long`, 123_456_789l oder 123456789L
    :code:`float`, 0.123456789f oder 0.123456789F
    :code:`double`, "0.123456789 oder 0.123456789d oder 0.123456789D"
    :code:`char`, "'a' (Zeichen-Darstellung) oder 97 (Zahlen-Darstellung) oder 
    '\u0061' (Unicode-Darstellung) oder Sonderzeichen (siehe nächste Folie)"
    :code:`String`, \"Hallo\"
    :code:`boolean`, true oder false


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


.. TODO ab Folie 29 in 03_Grundlagen_Teil1.key