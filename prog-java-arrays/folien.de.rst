.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Arrays", "Software Development"
    :description lang=de: Verwendung von Arrays in Java
    :id: lecture-prog-java-arrays
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

Verwendung von Feldern (:eng:`Arrays`) in Java
===========================================================

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

Eindimensionale Felder (:eng:`Arrays`)  
------------------------------------------------



Deklaration von Feldern (:eng:`Arrays`)  
-------------------------------------------

Eindimensionale Felder sind Datentypen, die es ermöglichen eine Liste mit einer fixen Anzahl von Werten gleichen Datentyps zu verwalten

.. stack::

    .. layer::

        .. container:: incremental

            - Die Tage der verschiedenen Monate können als ein Feld mit der Größe 12 abgelegt werden

            .. admonition:: Hinweis
                :class: note smaller

                Beim Programmieren beginnt der Index eines Feldes immer bei 0.

            .. csv-table::
                :header: Monat (Index):, 0, 1,2,3,4,5,6,7,8,9,10,11
                :class: fake-header-column far-far-smaller
            
                
                Tage:, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31


        .. container:: incremental

            - Variablen mit einem Feld-Datentyp werden durch den Datentyp der einzelnen Elemente gefolgt von eckigen Klammern deklariert.

            :Syntax:   :java:`<Typ>[] <Bezeichner> oder <Typ> <Bezeichner>[]`

    .. layer:: incremental

        Deklaration eines Feldes    

        .. code:: java
            :class: smaller copy-to-clipboard

            int[] daysPerMonth;
            
        .. container:: incremental    

            Alternativ möglich, aber unüblich geworden: 
                
            .. code:: java
                :class: smaller copy-to-clipboard

                int daysPerMonth[];
        
    .. layer:: incremental

        Initialisierung eines leeren Arrays:

        .. code:: java
            :class: smaller copy-to-clipboard

            daysPerMonth = new int[12];
            // daysPerMonth ==> int[12] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            
        :Syntax: <Bezeichner> = new <Typ>[<Größe>]

    .. layer:: incremental

        .. container::    

            Initialisierung eines Arrays mit konkreten Werten:

            .. code:: java 
                :class: smaller copy-to-clipboard

                daysPerMonth = 
                    new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            bzw. ohne Verwendung von :java:`new`:

            .. code:: java
                :class: incremental smaller copy-to-clipboard

                int [] daysPerMonth = 
                    {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                // ⚠️ Diese Art der Initialisierung eines Arrays direkt über 
                // "= {...}"  kann nur bei der Deklaration erfolgen.

            .. container:: incremental

                :Syntax: <Bezeichner> = {<Ausdruck> (, <Ausdruck>)* }
            
    .. layer:: incremental

        - Nach der Initialisierung lässt sich die Größe eines Feldes **nicht** mehr ändern.

          .. container:: incremental

            Die Länge eines Arrays kann mittels :java:`length` abgefragt werden:

            .. code:: java
                :class: smaller copy-to-clipboard

                int numberOfMonths = daysPerMonth.length;
                // numberOfMonths ==> 12

        .. class:: incremental

        - Wird ein Feld nur deklariert und nicht initialisiert, dann hat die Variable den speziellen Wert :java:`null`.

        


Zugriff auf die Elemente eines Feldes
--------------------------------------------

Auf einzelne Elemente eines Feldes kann mittels eines Indexes und dem Feldzugriff-Operator :java:`[]` lesend oder schreibend zugegriffen werden, z.B. mit :java:`a[1]`.

.. stack::

    .. layer::

        - Wertzuweisung eines Feldelementes: Verwendung des Feldzugriffoperators auf der linken Seite einer Zuweisung, z.B. :java:`a[1] = 1`;
        - Auslesen eines Feldelementes: :ger-quote:`jegliche andere Verwendung des Feldzugriffoperators`.
  
        .. class:: incremental

        - Verwendung eines ungültigen Indexes führt zu einer Ausnahme/einem Laufzeitfehler (:java:`ArrayIndexOutOfBoundsException`).
        
          .. code:: java
            :class: far-smaller copy-to-clipboard

            daysPerMonth[13]
            ==> Exception java.lang.ArrayIndexOutOfBoundsException: 
                Index 13 out of bounds for length 12

    .. layer:: incremental

        Beispiel: Lesender Zugriff auf ein Element eines Feldes

        .. code:: java
            :class: smaller copy-to-clipboard

            int daysInFebruary = daysPerMonth[1]; // Index "1" => 2. Element 
            // daysInFebruary ==> 29

        :Syntax: <Bezeichner>[<Index>]

    .. layer:: incremental

        Beispiel: Schreibende und lesende Zugriffe

        .. code:: java
            :class: smaller

            int daysPerMonth[] = new int[12]; // Deklaration
		
            daysPerMonth[0] = 31;
            daysPerMonth[1] = 29;
            //…
            daysPerMonth[10] = 30;
            daysPerMonth[11] = 31;
            
            System.out.println("daysPerMonth[1] = " + daysPerMonth[1]);

    .. layer:: incremental

        Häufig greift man auf Arrays mittels einer Schleife zu:

        .. code:: java
            :class: smaller

            for (int i = 0; i < daysPerMonth.length; i++) {
                println("daysPerMonth[" + i + "] = " + daysPerMonth[i]);
            }
        
        .. container:: incremental
    
            Bzw. mit einer :java:`for-each`-Schleife, wenn der Index nicht benötigt wird:

            .. code:: java
                :class: smaller

                for (int days : daysPerMonth) {
                    println("days = " + days);
                }

.. supplemental::

    .. hint:: Der Index der einzelnen Elemente eines Feldes läuft von 0 bis Größe-1, wobei das erste Element den Index 0 hat.



.. class:: integrated-exercise

Übung
------- 

.. exercise:: Wochentagsberechnung mit Feld

    Nehmen Sie Ihr Programm zur Berechnung des Wochentags und ersetzen Sie die Logik zur Bestimmung des Namens eines Wochentags durch ein Feld mit den Namen der Wochentage:

    .. code:: java

        String[] dayInWeekName = ... 

    .. solution:: 
        :pwd: Und der Wochentag ist... 

        .. include:: code/Wochentag.java
            :code: java
            :number-lines:
            :class: far-smaller copy-to-clipboard



.. class:: new-section transition-move-to-top

Referenzen auf Felder (:eng:`Arrays`)
----------------------------------------



Felder sind Referenzdatentypen
------------------------------------------------

Eine Variable mit einem Feld-Datentyp speichert eine (virtuelle) Speicheradresse zu den Feld-Inhalten (Werten).

.. container:: incremental

    .. code:: java
        :class: smaller copy-to-clipboard

        int[] a = {1, 2, 3};
        int[] b = a; // b referenziert das gleiche Feld wie a; 
                     // ist "nur" eine Kopie des Zeigers auf das Feld.
        b[0] = 4;
        println(a[0]); 
        // ==> 4

.. supplemental::

    Dies ist insbesondere in älteren Programmiersprachen (z. B. C) ein häufiger Fehler, der zu Speicherlecks führt. Dies hat dazu geführt, dass das :ger-quote:`Weisse Hause` die Empfehlung ausgesprochen hat, solche alten Sprachen nicht mehr zu verwenden. In Java und vielen anderen modernen Programmiersprachen ist dies nicht möglich.



Visualisierung von Referenzen auf Felder
------------------------------------------------

.. code:: java
        :class: smaller copy-to-clipboard

        int[] a = { 1, 2, 3, 4, 5, 6, 7 };
        int[] b = a;

.. image:: images/Arrays.svg
    :width: 100%
    :alt: Visualisierung von Referenzen auf Felder

.. hint::
    :class: incremental

    Die Werte eines Referenzdatentyp werden automatisch gelöscht (:eng:`Garbage Collected``), wenn keine Referenz (Variable) auf die Inhalte mehr existiert.


Referenzdatentypen und :java:`final`
-------------------------------------

.. class:: list-with-explanations

- Der :java:`final`-Modifizierer verhindert *nur*, dass die Referenz auf ein Feld geändert werden kann.
- Der Inhalt des Feldes kann jedoch geändert werden.

  D. h. der Nutzen von :java:`final` ist im Zusammenhang mit Referenzdatentypen im Allgemeinen begrenzt.

.. include:: code/IllegalAccess.java
    :code: java
    :number-lines:
    :class: far-smaller copy-to-clipboard



Vergleich von Feldern (:eng:`Arrays`)
--------------------------------------------

Der Vergleich zweier Feldvariablen mit dem :java:`==` bzw. :java:`!=` Operator vergleicht nicht den Inhalt der Felder, sondern die virtuelle Speicheradresse (ähnlich bei Strings).

Der Vergleich der Inhalte muss über den Vergleich der einzelnen Feldelemente erfolgen bzw. über Hilfsmethoden wie z.B. :java:`java.utils.Arrays.equals(...)`.

.. container:: incremental

    .. code:: java
        :class: smaller copy-to-clipboard

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = a;
        
        println(a == b); // ==> false
        println(a == c); // ==> true
        println(Arrays.equals(a, b)); // ==> true


.. supplemental::

    Konzeptionell führt Arrays.equals(...) eine Schleife über die Elemente der beiden Arrays aus und vergleicht die Werte der Elemente. Der Vergleich der Referenzen erfolgt über den Operator :java:`==`.


Felder kopieren
---------------------

Eine Kopie der Inhalte muss über das Erzeugen eines neuen Feldes und Kopie der einzelnen Feldelemente erfolgen bzw. über Hilfsmethoden wie z. B. :java:`System.arraycopy(...)` oder :java:`java.utils.Arrays.copyOf(...)` oder :java:`<Array>.clone()`.

Beispiel mit :java:`<Array>.clone()`:

.. code:: java
    :class: far-smaller copy-to-clipboard

    jshell> final var clone = daysPerMonth.clone();
    // clone ==> int[12] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }
    jshell> clone[0] = 35;
    jshell> daysPerMonth[0]
    // ==> 31

.. warning::
    :class: incremental smaller

    Die Methoden, z. B. :java:`clone` und :java:`arraycopy`, erzeugen nur flache Kopien (:eng:`shallow copies`). 

.. supplemental::

    Dokumentation: `java.util.Arrays <https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/Arrays.html>`__



Felder als Methodenparameter bzw. Rückgabewert
--------------------------------------------------

Die Parameter und der Rückgabewert einer Methode können vom Typ eines Feldes sein. 


.. stack:: incremental

    .. layer:: 

        - Bei der Übergabe eines Feldes an eine Methode bzw. der Rückgabe eines Feldes wird eine Kopie der Referenz auf das Feld erzeugt. Es wird keine Kopie der Arrays als solches erzeugt. 
        - Änderungen an den Feldelementen innerhalb der Methode wirken sich auf das ursprüngliche Feld aus.
        - Der Rückgabewert kann direkt zur Initialisierung eines Feldes verwendet werden.


    .. layer:: incremental

        .. code:: java
            :class: incremental far-smaller copy-to-clipboard

            void incrementAll(int[] a) {
                for (int i = 0; i < a.length; i++) { a[i]++; }
            }

            int[] getLengths(String[] strings) {
                int[] lengths = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    lengths[i] = strings[i].length();
                }
                return lengths;
            }
            getLengths(new String[]{"a","ab","abc"})
            // ==> int[3] { 1, 2, 3 }



.. class:: integrated-exercise

Übung
-------

.. exercise:: Arrays vergleichen

    Schreiben Sie eine Methode, die prüft ob eine Array zum Speichern von :java:`int` Werten, mit dem Beginn eines anderen Arrays von :java:`int`-Werten übereinstimmt. Vergleichen Sie die Elemente der beiden Arrays mit Hilfe des :java:`==` bzw. :java:`!=` Operators.

    D. h. die Methode soll :java:`true` zurückgeben, wenn alle Elemente des ersten Arrays (``a``) mit den ersten Elementen des zweiten Arrays ``b`` übereinstimmen.

    Die Methode soll die folgende Signatur haben und auch alle Sonderfälle abdecken!

    .. code:: java
        :class: copy-to-clipboard smaller

        boolean startsWith(int[] a, int[] b);


    .. solution::
        :pwd: ArraysVergleichenAberSicher

        .. code:: java
            :class: smaller copy-to-clipboard

            boolean startsWith(int[] a, int[] b) {
                if (a == null && b == null) return true;
                if (a == null || b == null || a.length > b.length) return false; 
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) {
                        return false;
                    }
                }
                return true;
            }


.. class:: integrated-exercise

Übung
-------

.. exercise:: Skalarprodukt

    Schreiben Sie eine Methode, die zwei gleich lange Arrays von :java:`int` Werten entgegennimmt und das Skalarprodukt der beiden Arrays berechnet. 
    
    Zur Erinnerung: Das Skalarprodukt ist die Summe der Produkte der Elemente an der gleichen Position in den beiden Arrays (:java:`a[0] * b[0] + a[1] * b[1] + ...`).

    .. solution:: 
        :pwd: Mein Skalarprodukt

        .. code:: java 
            :class: copy-to-clipboard

            int scalarProduct(int[] a, int[] b) {
                assert a != null; // requires "enableassertions" option
                assert b != null; // e. g., "jshell --enable-preview -R -ea"
                assert a.length == b.length;
                int result = 0;
                for (int i = 0; i < a.length; i++) {
                    result += a[i] * b[i];
                }
                return result;
            }



.. class:: integrated-exercise

Übung
-------

.. exercise:: Kommandozeilenparameter 

    .. container:: smaller

        Die :java:`main` Methode eines Java Programms bekommt als Parameter ein Feld von :java:`String`\ s übergeben. Dieser Parameter wird :java:`args` genannt (:java:`void main(String[] args)`).
    
    Nehmen Sie Ihr Programm zur Berechnung des BMIs und verwenden Sie Kommandozeilenargumente  als Parameter für Ihre :java:`bmi` Funktion. 

    .. stack:: smaller

        .. layer:: 

            - Prüfen Sie ob die Anzahl der Parameter korrekt ist und geben Sie eine Fehlermeldung aus, wenn dies nicht der Fall ist. 
            - Faktorisieren Sie (ggf.) die Funktionalität zur Berechnung des BMI in zwei Methoden:
            
              Eine Methode, die Strings entgegennimmt und eine die :java:`double` Werte entgegennimmt. 

        .. layer:: incremental

            Beispielinteraktion:
    
            .. code:: zsh
                :class: far-smaller

                $ java --enable-preview BMIBerechnen.java                   
                Bitte geben Sie Ihr Gewicht in Kilogramm und Ihre Größe in Mete an.

            bzw.

            .. code:: zsh
                :class: far-smaller

                $ java --enable-preview BMI.java 83.1 1.89
                Ihr BMI beträgt: 23.26362643822961 - Normalgewicht


    .. solution::
        :pwd: BMIBerechnenTeil2!

        .. include:: code/BMI.java
            :code: java
            :number-lines:
            :class: smaller copy-to-clipboard663

.. supplemental::

    D. h. der Nutzer übergibt direkt die Werte für das Gewicht und die Größe und wird nicht aufgefordert diese Werte einzugeben.



.. class:: new-section transition-move-to-top

Mehrdimensionale Felder (:eng:`Arrays`)
------------------------------------------------

TODO