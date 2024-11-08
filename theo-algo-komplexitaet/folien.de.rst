.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Komplexität", "Algorithmen"
    :description lang=de: Theoretische Informatik - Algorithmen und Datenstrukturen
    :id: lecture-theo-algo-komplexitaet
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

Komplexität und Algorithmen
====================================================

.. container:: line-above

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0

    .. container:: minor

        :Quelle: 
            Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch, Prof. Dr. Baumgart oder Prof. Dr. Albers.

.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung - Landau’sche O-Notation
--------------------------------------------------------


Berechnungskomplexität
----------------------

.. container:: scrollable

    Analyse des Aufwands zur Berechnung von Ergebnissen ist wichtig ...

    .. class:: incremental

    - im Design,
    - in der Auswahl
    - und der Verwendung von Algorithmen.

    .. container:: incremental

        Für relevante Algorithmen und Eingangsdaten können Vorhersagen getroffen werden:

        .. class:: incremental list-with-explanations

            - Um Zusammenhänge sind zwischen Eingangsdaten und Aufwand zu finden.
            - Aufwand kann Rechenzeit, Speicherbedarf oder auch Komponentennutzung sein.

            **Der Rechenaufwand ist zentral** und wird hier betrachtet, die Verfahren sind aber auch für weitere Ressourcen anwendbar.

    .. container:: incremental

        Die Vorhersagen erfolgen über asymptotische Schätzungen

        - mit Hilfe der Infinitesimalrechnung,
        - durch Kategorisierung im Sinne des Wachstumsverhaltens,
        - damit ist oft keine exakte Vorhersage möglich.
        
        Unterschiedliche Systeme sind unterschiedlich schnell, relativ dazu wird es interessant.

        Im Folgenden geht es um:

        - die Beschreibung des asymptotischen Wachstumsverhaltens
        - die Analyse von iterativen Algorithmen
        - die Analyse von rekursiv teilenden Algorithmen  

.. supplemental::

    Die Infinitesimalrechnung bezeichnet die Differenzial- und Integralrechnung. Es wird mit unendlich kleinen Größen gerechnet.


.. class:: new-subsection

Exkursion: Dynamische Programmierung
--------------------------------------------------------

.. supplemental::

    Der folgende Abschnitt behandelt die dynamische Programmierung, um ein Problem effizient zu lösen. Er zeigt gleichzeitig wie die Wahl des Algorithmus und der Implementierung die Laufzeit dramatisch beeinflussen kann.



.. class:: integrated-exercise

Übung
------------------------------------------

.. exercise::  Berechnung der Fibonacci-Zahlen

    Implementieren Sie eine **rekursive Funktion**, die die :math:`n`-te Fibonacci-Zahl berechnet!

    .. hint::

        Die Fibonacci-Zahlen sind definiert durch die Rekursionsformel :math:`F(n) = F(n-1) + F(n-2)` mit den Anfangswerten :math:`F(0) = 0` und :math:`F(1) = 1`.

    Bis zu welchem :math:`n` können Sie die Fibonacci-Zahlen in vernünftiger Zeit berechnen (d. h. < 10 Sekunden) ?

    .. solution:: 
        :pwd: das ist einfach gewesen

        Je nach Rechner und Laufzeitumgebung dürfte zwischen fib(35) und fib(45) die Grenze liegen, wenn man innerhalb von 10 Sekunden bleiben möchte.

        Lösung in Python:

        .. code:: Python
            :class: smaller copy-to-clipboard

            def fib(n):
                 if n == 0:
                     return 0
                 elif n == 1:
                     return 1
                 else :
                     return fib(n-1) + fib(n-2)

        Lösung in Java 23 (ggf. mit --enable-preview zu starten!):

        .. code:: Java
            :class: smaller copy-to-clipboard

            int fib(int n) {
                return switch(n){
                    case 0 -> 0;
                    case 1 -> 1;
                    default -> fib(n-1) + fib(n-2);
                };
            }



Technik der dynamischen Programmierung
---------------------------------------


:Rekursiver Ansatz: Lösen eines Problems durch Lösen mehrerer kleinerer Teilprobleme, aus denen sich die Lösung für das Ausgangsproblem zusammensetzt.
:Phänomen: Mehrfachberechnungen von Lösungen
:Methode: Speichern einmal berechneter Lösungen in einer Tabelle für spätere Zugriffe.


Beispiel: Berechnung der Fibonacci-Zahlen (rekursiv)
--------------------------------------------------------------------------------

.. container:: scrollable
        
    .. rubric:: Definition

    :math:`F(0) = 0` 

    :math:`F(1) = 1`.

    :math:`F(n) = F(n-1) + F(n-2)` 

    .. container:: incremental

        :math:`F(n)` als stehende Formel:

        .. math::

            F(n) = \left[{ 1 \over \sqrt{5} } (1.618 \ldots)^n  \right]


        .. warning::
            :class: incremental

            Die Berechnung der Fibonacci-Zahlen mit Hilfe einer naiven rekursiven Funktion ist sehr ineffizient.

    .. container:: incremental

        .. rubric:: Aufrufbaum

        .. image:: images/fib.svg
            :height: 600px
            :align: center


Vorgehen beim dynamischen Programmieren
----------------------------------------

.. class:: incremental

1. Rekursive Beschreibung des Problems P
2. Bestimmung einer Menge :math:`T`, die alle Teilprobleme von :math:`P` enthält, auf die bei der Lösung von :math:`P` – auch in tieferen Rekursionsstufen – zurückgegriffen wird.
3. Bestimmung einer Reihenfolge :math:`T_0 , \ldots, T_k` der Probleme in :math:`T`, so dass bei der Lösung von :math:`T_i` nur auf Probleme :math:`T_j`  mit :math:`j < i` zurückgegriffen wird.
4. Sukzessive Berechnung und Speicherung von Lösungen für :math:`T0 ,...,Tk`.


Beispiel: Berechnung der Fibonacci-Zahlen mit dynamischer Programmierung
--------------------------------------------------------------------------------

.. stack:: 

    .. layer::

        .. class:: incremental

        1. Rekursive Definition der Fibonacci-Zahlen nach gegebener Gleichung.
        2. :math:`T = { f(0),..., f(n-1)}`
        3. :math:`T_i = f(i), i = 0,...,n – 1`
        4. Berechnung von :math:`fib(i)` benötigt von den früheren Problemen nur die zwei letzten Teillösungen :math:`fib(i – 1)` und :math:`fib(i – 2)` für :math:`i ≥ 2`.

    .. layer:: incremental

        .. rubric:: Lösung mit linearer Laufzeit und konstantem Speicherbedarf

        .. code:: pseudocode
            :number-lines:
            :class: far-smaller copy-to-clipboard

            procedure fib (n : integer) : integer
                f_n_m2 := 0; f_n_m1 :=1
                for k := 2 to n do
                    f_n := f_n_m1 + f_n_m2
                    f_n_m2 := f_n_m1
                    f_n_m1 := f_n
                if n ≤ 1 then return n 
                else          return f_n

    .. layer:: incremental

        .. rubric:: Lösung mit Memoisierung (:eng:`Memoization`)

        Berechne jeden Wert genau einmal, speichere ihn in einem Array F[0...n]:

        .. code:: pseudocode
            :number-lines:
            :class: far-smaller copy-to-clipboard
            
            procedure fib (n : integer) : integer
                F[0] := 0; F[1] := 1;
                for i := 2 to n do
                    F[i] := ∞ // Initialisierung
                return lookupfib(n)

            procedure lookupfib (n : integer) : integer
                if F[n] = ∞ then
                    F[n] := lookupfib(n-1) + lookupfib(n-2)
                return F[n]

.. class:: integrated-exercise

Übung
------------------------------------------

.. exercise:: Fibonacci-Zahl effizient berechnen

    Implementieren Sie den Pseudocode der ersten Lösung zur Berechnung der Fibonacci-Zahlen.

    Bis zur welcher Fibonacci-Zahl können Sie die Berechnung nun durchführen?

    .. solution:: 
        :pwd: das ist schnell

        In Python kann die Berechnung (Python 3.13 - Standardinstallation) bis fib(20577) durchgeführt werden, wenn das Ergebnis direkt angezeigt werden soll und keine weiteren Einstellungen verändert werden sollen.

        .. code:: python
            :class: far-smaller copy-to-clipboard

            def fib (n) :
                 f_n_m2 = 0
                 f_n_m1 = 1
                 for k in range( 2, n+1):
                     f_n = f_n_m1 + f_n_m2
                     f_n_m2 = f_n_m1
                     f_n_m1 = f_n
                 if n <= 1:
                     return n
                 else:
                     return f_n



.. class:: new-subsection

Laufzeiten von Algorithmen
--------------------------------------------------------




Folgen
------

Im Allgemeinen werden Laufzeiten oder Aufwände in Abhängigkeit von einer Eingangsgröße als Folge beschrieben:

.. admonition:: Definition

    Eine Folge (:math:`a_n`) ist eine Abbildung, die jedem :math:`n \in \mathbb{N}` ein :math:`a_n` zuweist.

.. class:: incremental

- Folgenglieder
  
  Beispiel: (:math:`a_n`) : :math:`a_1 = 2, a_2 = 3, a_3 = 7, a_4 = 11, ...`

- Rekursive Definition 
  
  Beispiel: (:math:`c_n`) : :math:`c_1 = 1, c_2 = 1, c_{n+2} = c_n + c_{n+1}\; für\; n \in \mathbb(N)`

- Explizite Definition 
  
  Beispiel: (:math:`b_n`) : :math:`b_n = n^2` für :math:`n \in \mathbb{N}`

.. supplemental::

    Eine rekursive Definition ist eine Definition, die sich auf sich selbst bezieht. Häufiger schwieriger zu analysieren. Die explizite Definition ist eine direkte Zuweisung und meist die beste Wahl.



Folgen und Laufzeiten
----------------------

- Die explizite Definition von Laufzeiten ist zur Auswertung vorzuziehen.
- Die rekursive Definition tritt oft bei rekursiven Verfahren auf, und sollte dann in eine explizite Definition umgerechnet werden.

.. container:: incremental

    .. rubric:: Berechnung der Anzahl der Schritte zum Lösen der Türme von Hanoi.

    .. image:: images/hanoi.svg
        :height: 500px
        :align: center

    .. container:: text-align-center minor far-smaller

        Türme von Hanoi mit 3 Scheiben.

.. supplemental::

    .. rubric:: Die Türme von Hanoi (ChatGPT)

    Die Türme von Hanoi sind ein klassisches mathematisches Puzzle. Es besteht aus drei Stäben und einer bestimmten Anzahl von unterschiedlich großen Scheiben, die anfangs alle in absteigender Reihenfolge auf einem Stab gestapelt sind – der größte unten und der kleinste oben.

    Das Ziel des Spiels ist es, alle Scheiben auf einen anderen Stab zu bewegen, wobei folgende Regeln gelten:

    - Es darf immer nur eine Scheibe auf einmal bewegt werden.
    - Eine größere Scheibe darf nie auf einer kleineren liegen.
    - Alle Scheiben müssen auf den dritten Stab bewegt werden, indem sie über den mittleren Stab verschoben werden.


Laufzeit der Lösung der Türme von Hanoi
----------------------------------------

.. container:: scrollable

    Für die Lösung sind für jeden Ring :math:`n` die folgenden an Schritte erforderlich:

    .. class:: incremental

    1. Alle :math:`n−1` kleineren Ringe über Ring :math:`n` müssen mit :math:`a_{n−1}` Schritten auf den Hilfsstab.
    2. Der Ring :math:`n` kommt auf den Zielstab mit einem Schritt.
    3. Alle :math:`n−1` Ringe vom Hilfsstab müssen mit :math:`a_{n−1}` Schritten auf den Zielstab.

    .. container:: incremental

        Bei nur einem Ring ist :math:`a_1 = 1` und sonst :math:`a_n = a_{n−1} + 1+ a_{n−1} = 2a_{n−1} + 1`. 

        Also:
        :math:`a_1 = 1`, :math:`a_2 = 2·1+ 1= 3`, :math:`a_3 = 2·3+ 1= 7`, :math:`a_4 = 2·7+ 1= 15`, ...

        Damit liegt nahe, dass der Aufwand dem Zusammenhang :math:`a_n = 2^n−1` entspricht.

    .. container:: incremental proof smaller rounded-corners padding-1em dhbw-light-gray-background 
        
        .. rubric:: Beweis durch vollständige Induktion

        - Induktionsanfang :math:`n = 1`: :math:`a_1 = 2^n -1 =  2^1−1 = 1`
        - Induktionsvoraussetzung: :math:`a_{n-1} = 2^{n-1}−1` und :math:`a_{n} = 2a_{n-1} + 1`
        - Induktionsschritt (:math:`n-1 \rightarrow n`): 
      
          :math:`a_{n} = 2·(2^{n-1}−1)+1`

          .. container:: incremental  

            :math:`\quad\, = 2^{n}−2+1`

          .. container:: incremental  

            :math:`\quad\, = 2^{n}−1`

        .. container:: incremental

            Damit ist die Vermutung bestätigt.


Eigenschaften von Folgen - Konvergenz
----------------------------------------

.. admonition:: Definition

    - Eine Folge (:math:`a_n`) ist konvergent zum Grenzwert :math:`a`, wenn es zu jeder Zahl :math:`ε > 0` ein :math:`N \in \mathbb{N}` gibt, so dass :math:`|a_n−a|<ε` für alle :math:`n > N` gilt.

    Dies wird dann

    .. math::

        a_n \xrightarrow{n→∞} a , a_n \rightarrow a\; \text{oder}\; \lim_{n → ∞} a_n = a
    
    geschrieben. 

    - Eine Folge ist divergent, wenn es keinen Grenzwert gibt.



Eigenschaften von Folgen - Beispiel für Konvergenz
--------------------------------------------------

Betrachten wir die Folge (:math:`a_n`) mit :math:`a_n = {(−1)^n \over n} + 2`, :math:`n \in \mathbb{N}`:

.. container:: incremental

    Entwicklung der Folge:

    .. math::
        :class: far-smaller minor

        a_1 = -1 + 2 = 1, a_2 = 0.5 + 2 = 2.5, a_3 = -0.33.. + 2 \approx 1.67, a_4 = 0.25 + 2 = 2.25, ...

.. container:: incremental

    Die Folge konvergiert zu 2, da für ein gegebenes :math:`ε > 0` ein :math:`N` existiert so dass :math:`|a_n−a|<ε`:

    .. math::
    
        |a_n−a|= |{ (−1)^n \over n} + 2 − 2| = |{(−1)^n \over n}| = {1 \over n} < ε

    wenn :math:`n > {1 \over ε}` ist, also :math:`a_n \rightarrow 2` oder :math:`lim_{n→∞} a_n = 2`



Konvergenz von Folgen - Rechenregeln
-------------------------------------

.. admonition:: Satz

    Die beiden Folgen (:math:`a_n`) und (:math:`b_n`) seien konvergent :math:`a_n →a`, :math:`b_n →b` und :math:`λ\in\mathbb{C}`, sowie :math:`p,q \in \mathbb{N}` . Dann gilt:

    .. math::

        \begin{array}{rl}
            lim_{n→∞} λa_n & = λa \\
            lim_{n→∞}(a_n ± b_n) & = a ± b \\
            lim_{n→∞}(a_n·b_n) & = a·b \\
            lim_{n→∞} {a_n \over b_n} & = {a \over b},\; \text{für}\; b ≠ 0, b_n ≠ 0 \\
            lim_{n→∞} a^{p/q}_n & = a^{p/q} , \text{wenn}\; a^{p/q}\; \text{existiert} \\
        \end{array}
        

Konvergenz von Folgen - wichtige Grenzwerte
--------------------------------------------

.. math::

    \begin{array}{rl}
        \lim_{{n \to \infty}} q^n & = 0 \quad \text{wenn} \ |q| < 1 \\
        \lim_{{n \to \infty}} q^n & = \infty \quad \text{wenn} \ q > 1 \\
        \lim_{{n \to \infty}} {q^n \over n!} & = 0 \quad \text{für} \ q \in \mathbb{C} \\
        \lim_{{n \to \infty}} \sqrt[n]{a} & = 1 \quad \text{wenn} \ a > 0 \\
        \lim_{{n \to \infty}} \sqrt[n]{n} & = 1 \\
        \lim_{{n \to \infty}} \sqrt[n]{n!} & = \infty \\
    \end{array}


Konvergenz von Folgen - Beispiel
-----------------------------------------------------------------


Die Folge :math:`a_n = {n^2 + 1 \over n^3}` konvergiert gegen :incremental:`0`, da:

.. math::
    :class: incremental

    \lim_{{n \to \infty}} {n^2 + 1 \over n^3} = \lim_{{n \to \infty}} {n^3( 1/n + 1/n^3) \over n^3} = \lim_{{n \to \infty}} {( 1/n + 1/n^3) \over 1} = 0

.. class:: incremental

    Die Folge konvergiert gegen 0, da der Zähler gegen 0 strebt (:math:`\lim_{{n \to \infty}} {( 1/n)} = 0` und :math:`\lim_{{n \to \infty}} {( 1/n^3)} = 0`) und der Nenner konstant ist.

.. supplemental::

    Die allgemeine Vorgehensweise ist es, die größte Potenz im Zähler und Nenner zu finden und dann diese auszuklammern. Im zweiten Schritt kürzen wir dann. In diesem Fall ist es :math:`n^3`.

    D. h. das Ziel ist es den Ausdruck so umzuformen, dass der Grenzwert direkt abgelesen werden kann. Dies ist inbesondere dann der Fall, wenn :math:`n` nur noch im Nenner oder Zähler steht.



Analyse des asymptotischen Verhaltens
----------------------------------------

Wir möchten :math:`f(x) = \frac{\ln(x)}{x^{2/3}}` für :math:`x \to \infty` untersuchen.

.. admonition:: Beobachtung
    :class: far-smaller incremental

    1. Der Zähler, :math:`\ln(x)`, wächst gegen unendlich, aber sehr langsam im Vergleich zu Potenzfunktionen.
    2. Der Nenner, :math:`x^{2/3}`, wächst viel schneller als :math:`\ln(x)` für große :math:`x`.

    .. container:: incremental

        Es liegt somit ein unbestimmter Ausdruck vom Typ :math:`\frac{\infty}{\infty}` vor. Wir verwenden nun die Regel von L'Hôpital.


.. math:: 
    :class: incremental

    \lim_{x \to \infty} \frac{\ln(x)}{x^{2/3}} = \lim_{x \to \infty} \frac{\frac{d}{dx}(\ln(x))}{\frac{d}{dx}(x^{2/3})} = \lim_{x \to \infty} \frac{\frac{1}{x}}{\frac{2}{3}x^{-1/3}}

.. container:: incremental

    Das vereinfacht sich zu:

    .. math:: 

        = \lim_{x \to \infty} \frac{1}{x} \cdot \frac{3}{2}x^{1/3} = \lim_{x \to \infty} \frac{3}{2} \cdot \frac{1}{x^{2/3}} = 0

.. supplemental::

    Die **Regel von L'Hôpital** ermöglicht es Grenzwerte von Ausdrücken des Typs :math:`\frac{0}{0}` oder :math:`\frac{\infty}{\infty}` zu berechnen. In diesem Fall nehmen wir die Ableitungen des Zählers und des Nenners.

    Die Regel besagt:

    Falls :math:`\lim_{x \to a} \frac{f(x)}{g(x)}` den unbestimmten Ausdruck :math:`\frac{0}{0}` oder :math:`\frac{\infty}{\infty}` ergibt, dann gilt:

    .. math::

        \lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)},
    

    sofern der Grenzwert auf der rechten Seite existiert oder unendlich ist.



.. class:: integrated-exercise

Übung - Konvergenz von einfachen Folgen
------------------------------------------

.. exercise:: Erste Folge - zum Aufwärmen

    Zeigen Sie, dass die Folge :math:`a_n = {n^2 \over n^2 + 1}` konvergiert und bestimmen Sie den Grenzwert.

    .. solution:: 
        :pwd: das ist wirklich so

        Der Grenzwert der Folge :math:`a_n` ist 1, da:

        .. math::

            \lim_{{n \to \infty}} {n^2 \over n^2 + 1} = \lim_{{n \to \infty}} {1 \over 1 + {1 \over n^2}} = 1

.. exercise:: Zweite Folge

    Bestimmen Sie den Grenzwert der Folge, wenn er denn existiert: :math:`b_n =  {1 − n + n^2 \over n(n+1)}`.

    .. solution::
        :pwd: so und nur so

        Nach Kürzen der höchsten Potenz kann der Grenzwert für die einzelnen Terme bestimmt werden:
    
        .. math::

            \lim_{n→∞} b_n = 

            \qquad \lim_{n→∞} {1−n + n^2 \over n(n+1)} = 

            \qquad \lim_{n→∞} {n^2 - n + 1 \over n^2 + n} = 

            \qquad \lim_{n→∞} {n^2 (1 - 1/n + 1/n^2) \over n^2( 1 + 1/n)} =

            \qquad \lim_{n→∞} {1 - 1/n + 1/n^2 \over 1 + 1/n} = 1



.. class:: integrated-exercise

Übung - Konvergenz von Folgen
------------------------------------------

.. hint::

    Die Binomischen Formeln sind ggf. hilfreich.


.. exercise:: Folge mit Wurzel

    Bestimmen Sie den Grenzwert :math:`\lim_{n→∞} \sqrt{n^2 + n} - n`.

    .. class:: minor

    Hier könnte die dritte Binomische Formel (:math:`(a−b)(a + b) = a^2 −b^2`) hilfreich sein.


    .. solution:: 
        :pwd: da sind sie wieder

        Um die Wurzel loszuwerden, verwenden wir den entsprechenden Term: :math:`\sqrt{n^2 + n} + n`:

        .. math:: 
            
            \lim_{n→∞} {(\sqrt{n^2 + n} - n) \cdot (\sqrt{n^2 + n} + n) \over \sqrt{n^2 + n} + n }

        Anwendung der dritten Binomischen Formel auf den Zähler:

        .. math:: 

            \lim_{n→∞} {n^2 + n - n^2 \over \sqrt{n^2 + n} + n }

            \lim_{n→∞} { n \over \sqrt{n^2 + n} + n }

        Ausklammern der höchsten Potenz:

        .. math:: 

            \lim_{n→∞} {n \over n \left(\sqrt{1 + 1/n} +1 \right) }

            \lim_{n→∞} {1 \over \sqrt{1 + 1/n} +1  } = {1 \over 2} 
        
        (Da gilt: :math:`\lim_{n→∞} \sqrt{1 + 1/n} = 1`)


.. supplemental::

    Um eine Potenz aus einer Wurzel zu bekommen, hilft ggf. das Wurzelgesetz :math:`\sqrt{a} \cdot \sqrt{b} = \sqrt{a \cdot b}`. 
    
    Beispiel: :math:`\sqrt{x^4 + x^2} = \sqrt{x^4 (1 + 1/x ^2)} = \sqrt{x^4} \cdot \sqrt{(1 + 1/x ^2)} = x^2 \cdot \sqrt{(1 + 1/x ^2)}`.


.. exercise:: Folge mit mehreren Termen

    Berechnen Sie den Grenzwert Folge  :math:`b_n = {n^2 -1 \over n + 3 } - {n^2 + 1 \over n - 1}` falls er existiert.

    .. solution:: 
        :pwd: ausmultiplizieren_ist_der_Schluessel

        Vorgehen: Auf einem gemeinsamen Nenner bringen und dann die höchste Potenz ausklammern.

        .. math::

            \lim_{n→∞} {n^2 -1 \over n + 3 } - {n^2 + 1 \over n - 1} = 

            \lim_{n→∞} {n^2 -1 \over n + 3 } \cdot {n - 1 \over n - 1} - {n^2 + 1 \over n - 1} \cdot {n + 3 \over n + 3} = 

            \lim_{n→∞} {n^3 - n - n^2 + 1 - n^3 - 3n^2 - n - 3 \over n^2 + 2n - 3} = 

            \lim_{n→∞} { -4n^2 - 2n -2 \over n^2 + 2n - 3} = 


            \lim_{n→∞} { n^2 (-4 - 2/n -2/n^2) \over n^2 (1 + 2/n - 3/n^2)} = {-4 \over 1} = -4


.. exercise:: Zwei Wurzeln

    Bestimmen Sie den Grenzwert :math:`\lim_{n→∞} \sqrt{n^2 + 1} - \sqrt{n^2 + 4n}`.

    .. solution:: 
        :pwd: Binomische_Teil2

        Auch hier helfen die Binomischen Formeln:

        .. math:: 

            \lim_{n→∞} \sqrt{n^2 + 1} - \sqrt{n^2 + 4n} = 

            \lim_{n→∞} {(\sqrt{n^2 + 1} - \sqrt{n^2 + 4n}) \cdot (\sqrt{n^2 + 1} + \sqrt{n^2 + 4n}) \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} = 

            \lim_{n→∞} {n^2 + 1 - n^2 - 4n \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} = 

            \lim_{n→∞} {1 - 4n \over \sqrt{n^2 + 1} + \sqrt{n^2 + 4n}} = 

            \lim_{n→∞} {1 - 4n \over n \cdot (\sqrt{1 + 1/n^2} + \sqrt{1 + 4/n})} = 

            \lim_{n→∞} {n(1/n - 4) \over n \cdot (\sqrt{1 + 1/n^2} + \sqrt{1 + 4/n})} = 

            \lim_{n→∞} {1/n - 4 \over \sqrt{1 + 1/n^2} + \sqrt{1 + 4/n}} = {-4 \over 1 + 1} = -2


.. class:: new-subsection

Landau-Notation
--------------------------------------------------------


Asymptotische Abschätzung 
--------------------------------------------------------

.. admonition:: Definition

    .. rubric:: Landau-Notation

    Folgenden Mengen von Funktionen können asymptotisch von :math:`g(n)`
    ...

    .. class:: incremental

    - nach oben abgeschätzt werden, :math:`\mathcal{O}(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{x→∞} {f(n) \over g(n)} < ∞\}`
    - nach unten abgeschätzt werden, :math:`Ω(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{x→∞} {f(n) \over g (n)} > 0\}`
    - in gleicher Ordnung abgeschätzt werden, :math:`Θ(g) := \{f : \mathbb{N} →\mathbb{R}_{≥0} | \lim_{x→∞} {f(n) \over g(n)} = C \in \mathbb{R}_{>0}\}`


.. container:: incremental smaller
        
    Es gilt der folgende Zusammenhang für die Mengen :math:`\mathcal{O}(g)`\ [#]_, :math:`Ω(g)` und :math:`Θ(g)`:
            
    .. math::
        :class: smaller

        Θ(g) = \mathcal{O}(g) ∩ Ω(g)

    .. [#] Im Folgenden verwenden wir einfach :math:`O` statt :math:`\mathcal{O}`.


.. supplemental::

    Wenn eine Funktion :math:`f` in der Menge :math:`O(g)` ist, dann wächst die Funktion :math:`g` schneller als die Funktion :math:`f`. Typischerweise ist der Grenzwert von :math:`f(n)/g(n)` für :math:`n \to \infty` in diesem Falle 0.

    Die Verwendung der O-Notation zur Beschreibung der Komplexität von Algorithmen wurde von Donald
    E. Knuth eingeführt.



Alternative Schreibweisen
----------------------------------------

.. container:: center-child-elements

    Insbesondere für die obere Abschätzung :math:`O(g)` gibt es eine alternative Schreibweise:

    .. math:: 
        
        f(n) ∈ O(g(n)) ⇔ ∃c_0, n_0 ∀n : n > n_0 ⇒ f (n) ≤ c_0· g(n)

    D. h. ab einem Wert :math:`n_0` liegt die Komplexität der Funktion :math:`f` unter der :math:`c_0`-fachen Komplexität der Funktion :math:`g`.

    Beispiel: :math:`f(n) = 4n + 7 ∈ O(n)`
    
    :math:`4n + 7 ≤ c_0· n ⇔ n· (4− c_0) ≤ −7`

    Wähle: :math:`c_0 = 5` und :math:`n_0 = 7` sowie :math:`g(n) = n`.



Verstehen von Aufwandsklassen
----------------------------------------


.. image:: images/aufwandsklassen.svg
    :height: 950px
    :align: center

.. container:: incremental minor far-smaller

    Häufige Vergleichsfunktionen sind zum Beispiel Monome wie :math:`n^k` für :math:`k ∈ \mathbb{N}_0`.





Achtung bei asymptotischen Abschätzungen
----------------------------------------

Asymptotische Laufzeitabschätzungen können zu Missverständnissen führen:

.. class:: incremental

1. Asymptotische Abschätzungen werden nur für steigende Problemgrößen genauer, für kleine Problemstellungen liegt oft eine ganz andere Situation vor.
2. Asymptotisch nach oben abschätzende Aussagen mit :math:`O(g)`-Notation können die tatsächliche Laufzeit beliebig hoch überschätzen, auch wenn möglichst scharfe Abschätzungen erwünscht sein sollten, gibt es diese teilweise nicht in beliebiger Genauigkeit, oder sind nicht praktikabel.
3. Nur Abschätzungen von gleicher Ordnung :math:`Θ(g)` können direkt verglichen werden, oder wenn zusätzlich zu :math:`O(g)` auch :math:`Ω(h)` Abschätzungen vorliegen.



.. class:: integrated-exercise  transition-move-to-top

Übung
------------------------------------------

.. exercise:: Gegenseitige asymptotische Abschätzung I

    Bestimmen Sie welche Funktionen sich gegenseitig asymptotisch abschätzen:

    :math:`f_1(x) = \sqrt[3]{x},\; f_2(x) = e^{−1+ln\, x} , f_3(x) = {x \over ln(x) + 1}`.

    D. h. berechnen Sie:

    .. math::

        \lim_{x→∞} {f_1(x) \over f_2(x)}, \lim_{x→∞} {f_2(x) \over f_3(x)},\; \text{und ggf.}\; \lim_{x→∞} {f_1(x) \over f_3(x)}

    .. solution::  
        :pwd: viel_zu-berechnen

        1. Aufgabe
        
        .. math:: 
        
            \lim_{x→∞} {f_1(x) \over f_2(x)} = \lim_{x→∞} {\sqrt[3]{x} \over e^{-1+ln\, x}} = \lim_{x→∞} {x^{1/3}  \over {e^{-1} \cdot e^{ln\,x}}}  = \lim_{x→∞} e \cdot x^{-2/3} = \lim_{x→∞} {e \over \sqrt[3]{x^2}} = 0

        2. Aufgabe

        .. math::

            \lim_{x→∞} {f_2(x) \over f_3(x)} = {e^{−1+ln\, x} \over {x \over ln(x) + 1}} = {e^{−1} \cdot e^{ln\, x} \over {x \over ln(x) + 1}} = { x (ln(x) + 1) \over x \cdot e} =  { ln(x) + 1 \over e} = \infty 

        3. Aufgabe
        
        .. math::

            \lim_{x→∞} {f_1(x) \over f_3(x)} = \lim_{x→∞}  {\sqrt[3]{x} \over {x \over ln(x) + 1}} = \lim_{x→∞} x^{1/3} \cdot x^{-1} \cdot (ln(x) + 1) 
    
            =\lim_{x→∞} x^{-2/3} \cdot (ln(x) + 1) 

        Sowohl Zähler als auch Nenner gehen gegen unendlich. Deswegen ist die Anwendung von L'Hôpital erforderlich! (D. h. getrennte Ableitung von Zähler und Nenner):
    
        .. math::

            = \lim_{x→∞} {{d \over dx} (ln(x) + 1)  \over {d \over dx}  (x^{2/3}) }


            = \lim_{x→∞} {{1 \over x} \over 2/3 \cdot x^{-1/3}} = \lim_{x→∞} x^{-1} \cdot x^{1/3} \cdot {3 \over 2} = \lim_{x→∞} {3 \over \sqrt[3]{x^2} \cdot 2} = 0

        Also ist :math:`f_1  \notin Θ(f_2)`, :math:`f_1 ∈ O(f_2)` und :math:`f3 ∈ Ω(f_2)`.

        Also ist :math:`f_2 \notin Θ(f_3)`, :math:`f_2 ∈ Ω(f_3)` und :math:`f3 ∈O(f_2)`.

        Also ist :math:`f_1 \notin Θ(f_3)`, :math:`f_2 ∈O(f_3)` und :math:`f3 ∈Ω(f_1)`.

.. supplemental::

    Denken Sie daran, dass die erste Ableitung von :math:`f(x) = ln(x)` die Funktion :math:`f'(x)= {1 \over x}` ist.
    


.. class:: integrated-exercise

Übung - Asymptotische Abschätzungen
------------------------------------------

.. exercise:: Gegenseitige asymptotische Abschätzung II

    Vergleichen Sie: :math:`f_1(x) = e^{2ln(x)+1}` und :math:`f_2(x) = {x^3+1 \over x}`.

    .. solution::
        :pwd: ganz_und_gar-vergleichbar

        .. math::

            e^{2ln(x)+1} = e \cdot x^2

            {x^3+1 \over x} = x^2 + {1 \over x}

            \lim_{x→∞} {f_1(x) \over f_2(x)} = \lim_{x→∞} {e^{2ln(x)+1} \over {x^3+1 \over x}} = \lim_{x→∞} {e^{2ln(x)+1} \cdot x \over x^3+1} 
            
            = \lim_{x→∞} {e \cdot x^2 \over x^2+1/x} = e

        Somit sind die Funktionen :math:`f_1` und :math:`f_2` asymptotisch äquivalent.

.. exercise:: Gegenseitige asymptotische Abschätzung III

    Vergleichen Sie: :math:`f_1(x) = 2^{1+2x}` und :math:`f_2(x) = 4^x + 2^x`.

    .. solution::
        :pwd: auch+ganz_und_gar-vergleichbar

        .. math::

            2^{1+2x} = 2 \cdot 2^{2^{x}} = 2 \cdot 4^x

            \lim_{x→∞} {2 \cdot 4^x \over  4^x + 2^x} = \lim_{x→∞} {2 \cdot 4^x \over  4^x \cdot (1 + 1/2^x)} = 2
        

        Somit sind die Funktionen :math:`f_1` und :math:`f_2` asymptotisch äquivalent.


.. class:: new-section

Algorithmische Komplexität 
--------------------------------------------------------


Algorithmen
----------------------------------------

Algorithmen sind Verfahren, die gegebene Ausprägungen von Problemen in endlich vielen Schritten lösen können.

.. container:: incremental

    Dabei muss jeder Schritt

    - ausführbar und
    - reproduzierbar sein.

.. container:: incremental

    Es gibt aber oft viele Methoden die Probleme zu lösen:

    - Daher ist es wichtig, Eigenschaften von Algorithmen zu analysieren!
    - Insbesondere z.B.
    - Zeitaufwand und
    - Speicherbedarf
    - in Abhängigkeit von der Problemgröße.


.. supplemental::

    .. rubric:: Problemumfang (Problemgröße) n

    Konkrete Beispiele für Problemgrößen:

    - Konkreter Wert von :math:`n`: :math:`f (n)`
    - Stellenanzahl des Eingabewertes (der Eingabewerte) → :math:`f (z_1z_2 . . . z_n) (z_i ∈ { 0, . . . , 9 })`
    - Anzahl der Eingabewerte: :math:`f(x_1, x_2, . . . , x_n)`


Aufwand - Übersicht
----------------------------------------

.. image:: images/aufwand.svg
    :height: 900px
    :align: center



Algorithmen - Zeitaufwand
----------------------------------------

.. note::
    :class: far-smaller incremental

    Wir unterscheiden:

    - Komplexität eines Algorithmus
    
      Asymptotischer Aufwand (n → ∞) der Implementierung des Algorithmus.
    - Komplexität eines Problems
    
      Minimale Komplexität eines Algorithmus zur Lösung des Problems Algorithmus.


Tatsächlicher Zeitaufwand hängt vom ausführenden Rechnersystem ab.

.. class:: incremental

- Beeindruckende Entwicklung der Rechentechnik.
- Größere Probleme können gelöst werden.
- **Langsamere Algorithmen bleiben langsamer auch auf schnellen Systemen.**
  
.. container:: incremental
        
    Eine möglichst sinnvolle Annahme eines Rechnersystems gesucht:

    .. class:: incremental

    - Von-Neumann System
    - *mit einer Recheneinheit*
    - genaue Geschwindigkeit nicht relevant.



.. supplemental::

    Die Komplexität eines Problems zu bestimmen ist oft ausgesprochen schwierig, da man hierfür den besten Algorithmus kennen muss. Es stellt sich dann weiterhin die Frage wie man beweist, dass der beste Algorithmus vorliegt.

    **Bei vielen Komplexitätsanalysen steht die Zeitkomplexität im Vordergrund.**

    Die Zeitkomplexität misst nicht konkrete Ausführungszeiten (z. B. 1456 ms), da die Ausführungszeit von sehr vielen Randbedingungen abhängig ist, die direkt nichts mit demAlgorithmus zu tun haben, z. B.:

    - Prozessortyp und Taktfrequenz
    - Größe des Hauptspeichers
    - Zugriﬀszeiten der Peripheriegeräte
    - Betriebssystem → wird z. B. ein virtueller Speicher unterstützt
    - Compiler- oder Interpreter-Version
    - Systemlast zum Zeitpunkt der Ausführung


Wichtige Komplexitätsklassen
----------------------------------------

.. csv-table::
    :header: Klasse, Eigenschaft
    :class: incremental

    :math:`O(1)`, Die Rechenzeit ist unabhängig von der Problemgröße
    ":math:`O(log\, n)`", Die Rechenzeit wächst logarithmisch (zur Basis 2) mit der Problemgröße
    :math:`O(n)`, Die Rechenzeit wächst linear mit der Problemgröße
    ":math:`O(n \cdot log\, n)`", Die Rechenzeit wächst linear logarithmisch mit der Problemgröße
    :math:`O(n^2)`, Die Rechenzeit wächst quadratisch mit der Problemgröße
    :math:`O(n^3)`, Die Rechenzeit wächst kubisch mit der Problemgröße
    :math:`O(2^n)`, Die Rechenzeit wächst exponentiell (zur Basis 2) mit der Problemgröße
    :math:`O(n!)`, Die Rechenzeit wächst entsprechend der Fakultätsfunktion mit der Problemgröße


Komplexität und bekannte Algorithmen/Probleme
----------------------------------------------

.. container:: scrollable

    .. container:: 

        :math:`O(1)`

        .. class:: incremental

        - Liegt typischerweise dann vor, wenn das Programm nur einmal linear durchlaufen wird.
        - Es liegt keine Abhängigkeit von der Problemgröße vor, d. h. beispielsweise keine Schleifen in Abhängigkeit von :math:`n`.
      
        - Beispiel:
        
            Die Position eines Datensatzes auf einem Datenträger kann mit konstanten Aufwand berechnet werden.


    .. container:: incremental

        :math:`O(log\, n)`

        .. class:: incremental

        - Beispiel: 

            Binäre Suche; d. h. in einem sortierten Array mit :math:`n` Zahlen eine Zahl suchen.

    .. container:: incremental

        :math:`O(n)`

        .. class:: incremental

        - Beispiel: 
        
            Invertieren eines Bildes oder sequentielle Suche in einem unsortierten Array.

    .. container:: incremental

        :math:`O(n \cdot log\, n)`

        .. class:: incremental
            
        - Beispiel: 
        
            Bessere Sortierverfahren wie z. B. Quicksort.


    .. container:: incremental

        :math:`O(n^2)`

        - Häufig bei zwei ineinander geschachtelten Schleifen.
        
        .. class:: incremental

        - Beispiel: 
          
            Einfache Sortierverfahren wie z. B. Bubble-Sort oder die Matrixaddition.


    .. container:: incremental

        :math:`O(n^3)`

        - Häufig bei drei ineinander geschachtelten Schleifen.

        .. class:: incremental

        - Beispiel: 
          
            Die Matrixmultiplikation.

            .. container:: far-smaller

                :math:`M(m, t)` ist eine Matrix mit m Zeilen und t Spalten.
                
                :math:`C(m, t) = A(m, n)· B(n, t)` mit

                :math:`c_{i,j} = \sum_{k = 1}^n a_{i,k}· b_{k,j}\qquad i = 1, . . . , m \qquad j = 1, . . . , t`


    .. container:: incremental

        :math:`O(2^n)`
            
        - Typischerweise der Fall, wenn für eine Menge mit n Elementen alle Teilmengen berechnet und verarbeitet werden müssen.

        .. class:: incremental

        - Beispiel: 

            Rucksackproblem (:eng:`Knapsack Problem`)

            Ein Rucksack besitzt eine maximale Tragfähigkeit und :math:`n` Gegenstände unterschiedlichen Gewichts liegen vor, deren Gesamtgewicht über der Tragfähigkeit des Rucksacks liegt. Ziel ist es jetzt eine Teilmenge von Gegenständen zu finden, so dass der Rucksack optimal gefüllt wird.

    .. container:: incremental

        :math:`O(n!)`

        - Typischerweise der Fall, wenn für eine Menge von :math:`n` Elementen alle Permutationen dieser Elemente zu berechnen und zu verarbeiten sind.
        
        .. class:: incremental

        - Beispiel: 
  
            Problem des Handlungsreisenden (:eng:`Traveling Salesman Problem (TSP)`)

            Gegeben sind :math:`n` Städte, die alle durch Straßen direkt miteinander verbunden sind und für jede Direktverbindung ist deren Länge bekannt.

            Gesucht ist die kürzeste Rundreise, bei der jede Stadt genau einmal besucht wird.


Approximation von Laufzeiten
----------------------------------------

Sei die Problemgröße :math:`n = 128`:

.. note::
    :class: far-smaller 

    Für die Approximation sei ein Rechner mit 4 GHz Taktrate angenommen und ein Rechenschritt soll einen Takt benötigen.

    .. container:: smaller

        Verwendete Abkürzungen:

        - :math:`1ns = 10^{-9}s` → Nanosekunde
        - :math:`1µs = 10^{-6}s` → Mikrosekunde
        - :math:`1ms = 10^{-3}s` → Millisekunde
        - :math:`1h = 3 600s` → Stunde
        - :math:`1d = 86 400s` → Tag
        - :math:`1a` → Jahr

.. csv-table::
    :header: Klasse, Laufzeit
    :class: highlight-line-on-hover 

    ":math:`O(log_2\, n)`", ":math:`1,75\,ns`"
    :math:`O(n)`, ":math:`32\,ns`"
    ":math:`O(n \cdot log_2\, n)`", ":math:`224\,ns`"
    :math:`O(n^2)`, ":math:`4,096\,µs`"
    :math:`O(n^3)`, ":math:`524,288\,µs`"
    :math:`O(2^n)`, ":math:`2,70 \cdot 10^{21}\,a`"
    :math:`O(3^n)`, ":math:`9,35 \cdot 10^{43}\,a`"
    :math:`O(n!)`, ":math:`3,06 \cdot 10^{198}\,a`"


.. container:: incremental

    Dies zeigt, dass Algorithmen mit einer Komplexität von :math:`O(n^3)` oder höher für große bzw. nicht-triviale Problemgrößen nicht praktikabel sind.


.. class:: new-subsection

Iterative Algorithmen
--------------------------------------------------------

Elementare Kosten als Approximation
----------------------------------------

.. csv-table::
    :header: "Elementare Operation", "Anzahl der Rechenschritte"
    :class: smaller highlight-line-on-hover

    "elementare Arithmetik: +    ,-    ,    *    , /, etc.", 1
    "elementare logische Operationen: &&, ||, !, etc.", 1
    "Ein- und Ausgabe", 1
    "Wertzuweisung", 1
    "return, break, continue", 1
    "Kontrollstrukturen", Anzahl der Rechenschritte
    Methodenaufruf, 1 + Komplexität der Methode
    "Fallunterscheidung", "Komplexität des logischen Ausdrucks + Maximum der Komplexität der Rechenschritte der Zweige"
    Schleife, "Annahme: :math:`m` Durchläufe:
    Komplexität der Initialisierung + :math:`m` mal die Komplexität des
    Schleifenkörpers + Komplexität aller Schleifenfortschaltungen"


Beispielanalyse von ``ist_primzahl``
--------------------------------------------------------

.. code:: python
    :class: far-smaller

    def ist_primzahl(n):
        prim = True                 # Wertzuweisung:            1
        i = 2                       # Wertzuweisung:            1
        if n < 2:                   # Vergleich:                1
            prim = False            # Wertzuweisung:            1
        else:                       # Durchläufe:               n-2 * (
            while prim and i < n:   #   Vergleiche, und:            3
                if n % i == 0:      #   modulo, Vergleich:          2
                    prim = False    #       Wertzuweisung:              1
                i += 1              #   Inkrement:                  1
                                    #                           )
                                    # letzte Bedingungsprüfung  3
        return prim                 # Befehl:                   1

.. container:: incremental

    Im schlechtesten Fall, d. h. es gilt :math:`i==n` nach der while-Schleife, werden :math:`7 + (n− 2)· 7 = 7· n− 7` Rechenschritte benötigt. Die Anzahl der Rechenschritte hängt somit linear vom Eingabewert :math:`n` ab.

.. supplemental::

    Beachte, dass in keinem Falle alle Instruktionen ausgeführt werden.

    .. hint::

        Dies kein effizienter Algorithmus zum Feststellen ob eine Zahl Primzahl ist.


.. class:: integrated-exercise transition-scale

Übung 
------------------------------------------


.. exercise:: Bestimmung der asymptotischen Laufzeit eines Algorithmus 
    
    Die Funktion :math:`p(n)` hat die Laufzeit :math:`T_p(n) = c_p \cdot n^2` und :math:`q(n)` die Laufzeit :math:`T_q(n) = c_q \cdot log(n)`.

    .. code:: pseudocode
        :number-lines:
        :class: far-smaller

        Algorithmus COMPUTE(n)
        p(n);
        for j = 1...n do
            for k = 1...j do
                q(n);
            end
        end

    Bestimmen Sie die asymptotische Laufzeit des Algorithmus in Abhängigkeit von :math:`n` durch zeilenweise Analyse.

    .. solution:: 
        :pwd: log(n)*n^2

        Die Komplexität ergibt sich zu: :math:`log(n)\cdot n²`

        :math:`p(n)` hat die Komplexität :math:`n^2`

        :math:`q(n)` hat die Komplexität :math:`log(n)`

        Eine Analyse der Schleifen ergibt, dass q(n):

            :math:`1\cdot q(n)+2\cdot q(n)+3\cdot q(n)+…+n\cdot q(n) = q(n) \cdot \sum_{i = 1}^n i = q(n) \cdot {n(n+1)\over 2} = { n^2+n \over 2 } \cdot q(n)`
        
        aufgerufen wird. 
        Daraus folgt: 
        
            :math:`(n(n+1))/2 \cdot q(n)` bzw. :math:`n^2 \cdot log(n)`



.. class:: integrated-exercise transition-scale

Übung 
------------------------------------------

.. container:: smaller

    .. exercise:: „Naive“ Power Funktion

        Bestimmen Sie die algorithmische asymptotische Komplexität des folgenden Algorithmus durch Analyse jeder einzelnen Zeile. Jede Zeile kann für sich mit konstantem Zeitaufwand abgeschätzt werden. Bestimmen Sie die Laufzeitkomplexität für den schlimmstmöglichen Fall in Abhängigkeit von :math:`k` für eine nicht-negative Ganzzahl :math:`n` mit :math:`k` Bits.
        
        .. container:: far-smaller
        
            (Beispiel: die Zahl :math:`n = 7_d` benötigt drei Bits :math:`n= 111_b`, die Zahl :math:`4d` benötigt zwar auch drei Bits :math:`100_b` aber dennoch weniger Rechenschritte.). 

        .. code:: pseudocode
            :number-lines:
            :class: far-smaller

            Algorithmus Power(x,n)
                r = 1
                for i = 1...n do
                    r = r * x
                return r

        .. solution::
            :pwd: Zaehlen_der_Schritte

            .. code:: pseudocode
                :number-lines:
                :class: far-smaller

                Algorithmus Power(x,n)      # Anzahl der Rechenschritte
                    r = 1                   # 1
                    for i = 1...n do        # n + 1 
                        r = r * x           # n
                    return r                # 1
                
            Sei c ein konstanter Faktor, der gleich dem größten Faktor ist, der von einem Rechenschritt benötigt wird.

            :math:`T(n) \leq c \cdot (1 + (n + 1) + n + 1)`

            :math:`T(n) \leq c \cdot (3 + 2n)`

            Im schlimmsten Fall, d. h. :math:`n_{worst} = 2^k - 1`:

            :math:`T_{worst}(k) \leq c \cdot (3 + 2 \cdot (2^k - 1)) = c \cdot (1 + 2^{k+1})`

            Somit gilt: :math:`T_{worst}(k) \in \Theta(2^{k})`



.. class:: integrated-exercise transition-scale

Übung 
------------------------------------------

.. container:: smaller
        
    .. exercise:: Effizientere Power Funktion

        Bestimmen Sie die algorithmische asymptotische Komplexität des folgenden Algorithmus durch Analyse jeder einzelnen Zeile. Jede Zeile kann für sich mit konstantem Zeitaufwand abgeschätzt werden. 
        Bestimmen Sie die Laufzeitkomplexität mit Indikator :math:`t_i` für gesetzte Bits in :math:`n` für den schlimmstmöglichen Fall in Abhängigkeit von :math:`k` für eine nicht-negative Ganzzahl :math:`n` mit :math:`k` Bits. 
        
        .. container:: far-smaller
        
            (D. h. :math:`t_i = 1`, wenn der i-te Bit von :math:`n` gesetzt ist, sonst ist :math:`t_i = 0`; sei :math:`n = 5_d = 101_b` dann ist :math:`t_1 = 1, t_2 = 0, t_3 = 1`).

        .. code:: pseudocode
            :number-lines:
            :class: far-smaller

            Algorithmus BinPower(x,n)
                r = 1
                while n > 0 do
                    if n mod 2== 1 then
                        r = r * x
                        n = (n-1)/2
                    else
                        n = n/2
                    x = x *x
                return r

        .. solution::
            :pwd: Zaehlen_der_Schritte

            Bestimmung der Anzahl Rechenschritte in Abhängigkeit von der Anzahl an Bits von :math:`n`:

            .. code:: pseudocode
                :number-lines:
                :class: far-smaller

                Algorithmus BinPower(x,n)       # Anzahl der Rechenschritte
                    r = 1                       # 1
                    while n > 0 do              # 1 + (max i für das gilt t_i = 1)
                        if n mod 2 == 1 then    # max i für das gilt t_i = 1
                            r = r * x           # Summe aller t_i; d.h. Anzahl der 1-Bits in n 
                            n = (n-1)/2         # Summe aller t_i
                        else
                            n = n/2             # Summe aller (1-t_i) ; d.h. Anzahl der relevanten 0-Bits in n
                        x = x *x                # max i für das gilt t_i = 1
                    return r                    # 1
                
            Sei c ein konstanter Faktor, der gleich dem größten Faktor ist, der von einem Rechenschritt benötigt wird.

            Sei :math:`l = \underset{t_i = i}{max}\; i` und :math:`m = \sum_{k=1}^l t_i`: 
            
                .. math::

                    \begin{array}{rl}
                    T(n) & ≤ c· \left( 1+ 1+ l + l + 2 \sum_{k=1}^l t_i + \sum_{k=1}^l (1- t_i) + l +1 \right) \\
                    & = c·(3+ 4l + m)
                    \end{array}

            

            Im schlimmsten Fall, d. h. :math:`n_{worst} = 2^k - 1` und :math:`l_{worst} = m_{worst} = k` : :math:`T_{worst}(k) ≤ c · (3+ 4k + k) = c· (5k + 3)`

            gilt: :math:`T_{worst}(k) \in \Theta(k)`