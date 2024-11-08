.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Programmierung", "Java", "Variablen", "Methoden"
    :description lang=de: Kontrollfragen zu Einführung in die Programmierung mit Java
    :id: lecture-prog-java-basics-kontrollfragen
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!
    
.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf
.. |at| unicode:: 0x40
.. |qm| unicode:: 0x22 

.. role:: incremental
.. role:: appear
.. role:: eng
.. role:: ger
.. role:: dhbw-red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: obsolete
.. role:: line-above
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced
.. role:: copy-to-clipboard
.. role:: kbd
.. role:: java(code)
   :language: java



.. class:: animated-symbol 

Einführung in die Programmierung mit Java - Wiederholung
===========================================================

.. container:: line-above

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0



Kontrollfragen
----------------

.. container:: scrollable

   .. class:: incremental

   1. \
   
      .. exercise:: Welche primitiven Datentypen kennen wir?

         .. solution::
            :pwd: int_und_so

            Wir kennen die primitiven Datentypen :java:`byte`, :java:`short`, :java:`int`, :java:`long`, :java:`float`, :java:`double`, :java:`boolean`, :java:`char`.

   2. \

      .. exercise:: Was sind Literale?

         .. solution::
            :pwd: lits

            Konstante Werte, die direkt in den Code geschrieben werden.

   3. \

      .. exercise:: Welche der folgenden Bezeichner sind für Variablen gültig?

         1. fooBar   
         2. BarFoo
         3. _fooBar
         4. 1fooBar
         5. fooBar1
         6. fooBar!
         7. $fooBar
         8. $_BarFoo

         .. solution::
            :pwd: BezeichNer

            1. ja   
            2. ja
            3. ja
            4. nein
            5. ja
            6. nein
            7. ja
            8. ja

   4. \

      .. exercise:: Welche der folgenden Bezeichner sollte man für eine Variable verwenden?

         1. gewinn   
         2. Gewinn
         3. _private_i
         4. i
         5. $i
         6. _i

         .. solution::
            :pwd: BezeichNer_Teil2

            1. ja
            2. nein
            3. (nein)
            4. ja
            5. nein
            6. (nein)

   5. \

      .. exercise:: Sie definieren eine Konstante, welchen Namen würden Sie verwenden?

         1. ISOLAENDERCODE
         2. ISO_LÄNDERCODE
         3. ISO_LAENDERCODE
         4. ISO_Ländercode
         5. ISO_Laendercode
         6. iso_Laendercode

         .. solution::
            :pwd: BezeichNer_Teil2

            Wenn die Konstante einen deutschen bekommen  soll, dann sollte man dennoch auf Umlaute verzichten. Daher ist 3. die beste Wahl.

   6. \

      .. exercise:: Welchen Typ hat die Variable x in folgendem Code?

         1. :java:`var x = 1;`
         2. :java:`var x = 1.0;`
         3. :java:`var x = '1';`
         4. :java:`var x = 1f;`
         5. :java:`var x = 2F;`
         6. :java:`var x = "x";`

         .. solution::
            :pwd: MeinTypDeinTyp

            1. int
            2. double
            3. char
            4. float
            5. float
            6. String

   7. \

      .. exercise:: Wieviele Bits hat ein int?

         1. 8
         2. 16
         3. 24
         4. 32
         5. 40
         6. 48

         .. solution::
            :pwd: 3_2_Bits

            32

   8. \

      .. exercise:: Wie ist der Wertebereich von byte?

         1. 0 bis 255
         2. -128 bis 128
         3. -128 bis 127
         4. -127 bis 127
         5. -127 bis 128 
                  
         .. solution::
            :pwd: Es gibt nur eine Antwort

            -128 bis 127


   9. \

      .. exercise:: Was passiert bei den folgenden Typumwandlungen?

         1. int i = 42; byte b = (byte) i;
         2. int i = 255; byte b = (byte) i;
         3. int i = 256; byte b = (byte) i;

         .. solution::
            :pwd: 3_2_Bits

            1. b = 42
            2. b = -1
            3. b = 0

   10. \

       .. exercise:: Warum ist der folgende  Ausdruck wahr obwohl dieser mathematisch falsch ist?

         .. container:: far-smaller

            :java:`(long) ((float) (Long.MAX_VALUE - Integer.MAX_VALUE)) == Long.MAX_VALUE;`
         
         .. solution::
            :pwd: VerlusteSindDA!

            Durch die Typkonvertierung wird der Wert von :java:`Long.MAX_VALUE - Integer.MAX_VALUE` in einen :java:`float` umgewandelt. Da ein float nur 24 Bit für die Mantisse hat kommt es zu einem Präzisionsverlust. Der Wert wird also verändert. In (2) wird der Wert als :java:`long` berechnet und ist daher korrekt.
   
   11. \

       .. exercise:: Ist die Länge eines Strings gleich der Anzahl *sichtbarer* Zeichen? 


         .. solution::
            :pwd: x_plus_plus

            Nein - es gibt Zeichen (zum Beispiel Emojis), die mehrere Zeichen (:java:`char`\ s) benötigen.
   
   12. \

       .. exercise:: Sie möchten in einem String ein Anführungszeichen verwenden. Wie machen Sie das?

         .. solution::
            :pwd: backslash

            Sie verwenden ein Backslash: :java:`"\""`.

   13. \

       .. exercise:: Muss ich bei der Variablendeklaration den Typ explizit angeben?

         .. solution::
            :pwd: nein...

            Nein, in Java kann der Typ auch implizit durch den Compiler ermittelt werden, wenn die Variable auch direkt initialisiert wird.

   14. \

       .. exercise:: Wie deklariert man eine Konstante? Sollte man Werte, die man nicht ändern möchte immer als Konstanten deklarieren?

         .. solution::
            :pwd: final var

            Durch die Verwendung des Schlüsselwortes :java:`final`. Ja - es ist eine gute Praxis, Werte, die sich nicht ändern sollen, als Konstanten zu deklarieren.

   15. \

       .. exercise:: Wie ist der Operator für die Modulorechnung (d. h. Restwertberechnung) in Java?

         .. solution::
            :pwd: _-%-_

            Das Prozentzeichen :java:`%`. 

   16. \

       .. exercise:: Wie sieht der ternäre Operator in Java aus?

         .. solution::
            :pwd: if?then:else

            Es ist das Fragezeichen :java:`?` und der Doppelpunkt :java:`:`. Beispiel :java:`x > 18 ? "alt" : "jung"`.

   17. \

       .. exercise:: Welchen Wert haben die folgenden Ausdrücke, wenn x vor der jeweiligen Auswertung den Wert 5 hat?

         (1) :java:`x++`
         (2) :java:`++x`
         (3) :java:`x += 1`
         (4) :java:`x = (x = x - 2 ) + 3 * 4`
         (5) :java:`x = x = x - 2 + 3 * 4`
         (6) :java:`(x = (x = x - 2 ) + 3) * 4`
         (7) :java:`x >= 5 || 2 / (x - 5) == 0`
         (8) :java:`x >= 5 | 2 / (x - 5) == 0`
         (9) :java:`x << 1`
         (10) :java:`x << 1 >> 2`

         .. solution::
            :pwd: mal_so_mal_so

            (1) 5, (2) 6, (3) 6, (4) 15, (5) 15, (6) 24, (7) true, (8) Division durch 0, (9) 10, (10) 2

   18. \

       .. exercise:: Was stellt ein Block in Hinblick auf eine Variable dar?

         .. solution::
            :pwd: gibts_mich_oder_nicht

            Den Gültigkeitsbereich einer Variable.