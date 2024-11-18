.. meta:: 
    :lang: de
    :author: Michael Eichberg
    :keywords: "Suche", "Arrays", "Algorithmen", "Datenstrukturen"
    :description lang=de: Suchen auf Arrays
    :id: lecture-theo-algo-suchen_auf_arrays
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

Suchen auf Arrays
======================================================

----

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0

.. container:: minor

    :Quelle: 
        Die Folien sind teilweise inspiriert von oder basierend auf Lehrmaterial von Prof. Dr. Ritterbusch


.. supplemental::

    :Folien: 
        
        |html-source| 

        |pdf-source|

    :Fehler melden:
        https://github.com/Delors/delors.github.io/issues



.. class:: new-section transition-move-to-top

Einführung
--------------------------------------------------------



Skalierung von Daten
--------------------------------------------------------

Welche Skalierung haben gesuchte Daten sind im Array?

:nominal: Nur Vergleich auf Gleichheit, keine natürliche Ordnung oder Zahlbegriff.
:ordinal: Es gibt Größenvergleiche und damit eine Sortierung, aber kein Zahlbegriff.
:kardinal: Es gibt Größenvergleiche und Zahlbegriff.

- *Unsortiert oder nominal* führt (zunächst) zur linearen Suche.
- *Ordinale und kardinale Werte* können sortiert werden für binäre Suche.
- *Kardinale Größen* können modelliert werden für interpolierende Suche.

.. hint::
    :class: smaller

    Für unsere Betrachtung gehen wir im Folgenden davon aus, dass die Daten sortiert sind. Beim Vergleich der Algorithmen beschränken wir uns auf eine Betrachtung der Anzahl der Elementzugriffe.

.. supplemental::

    - Ein Beispiel für eine *nominal* skalierte Datenmenge wäre die Menge der Farben. Es gibt keine natürliche Ordnung der Farben, und es gibt auch keinen natürlichen Zahlenbegriff, der die Farben beschreibt. Ein weiteres Beispiel ist eine Liste von Wohnorten.4
    - Ein Beispiel für eine *ordinale* skalierte Datenmenge wäre die Menge der Kleidergrößen (S,M,L,XL,...). Es gibt eine natürliche Ordnung der Kleidergrößen, aber es gibt keinen natürlichen Zahlenbegriff, der die Kleidergrößen beschreibt. Ein weiteres Beispiel ist die Bewertung von Filmen auf einer Skala von 1 bis 5 Sternen.



.. class:: repetition  smaller-slide-title

Lineare Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: slightly-more-smaller

    Algorithm linearSearch(A,n,needle)
        for i= 1,...,n do
            if A[i] == needle then
                return i
        return nil

Laufzeit und Elementzugriffe kann asymptotisch durch :math:`O(n)` abgeschätzt werden.

.. container:: block-footer white dhbw-gray-background text-align-center

    Wiederholung



.. class:: repetition smaller-slide-title

Binäre Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: slightly-more-smaller

    Algorithm binarySearch(A,l,u,needle)
        upper = u
        lower = l
        repeat
            pos = round((upper+lower)/2)
            value = A[pos]
            if value == needle then
                return pos
            else if value > needle then
                upper = pos−1
            else
                lower = pos + 1
        until upper < lower
        return nil

Laufzeit ist :math:`O(\log(n))`, genauer im Schnitt :math:`\log_2(n)−1` Zugriffe.

.. container:: block-footer white dhbw-gray-background text-align-center

    Wiederholung



.. class:: smaller-slide-title

Effizientere Suche bei bekannter Verteilung (hier linear)
----------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/lagrange/lin-1.svg
            :width: 85%
            :align: center
        
    .. layer:: incremental overlay
    
        .. image:: images/lagrange/lin-2.svg
            :width: 85%
            :align: center


.. supplemental::

    In diesem Beispiel gehen wir davon aus, dass die Werte *im Wesentlichen* linear verteilt sind. Das bedeutet, dass die Differenz zwischen zwei aufeinanderfolgenden Werten immer gleich ist. 

    Sei beispielsweise ein Array ``a`` mit folgenden Werten geben (Auszug):

    .. csv-table::
        :header: "Index i", "Wert"
        :widths: 10, 10

        i = 10, :minor:`a[i = 10] =`  20.0
        ..., ...
        i = 30,49.5
        ..., ...
        i = 50,87.2
        ..., ...
        i = 70,151.3
        ..., ...
        i = 90,169.9
        ..., ...
        i = 110,220.0
        ..., ...
        i = 130,251.2

    Wenn man jetzt exemplarisch die Paare: :math:`(x = 10, y = 20.0)`, und :math:`(x = 110, y = 220)` betrachtet, dann kann man zu dem Schluss kommen, dass die Funktion :math:`f(x) = 2.0\cdot x` eine Approximation der Verteilung der Werte ist. Würde man also nach dem Wert (:math:`y`) 170 suchen wollen, dann wäre es gut als erstes den Wert von ``a[85]`` zu überprüfen, :math:`170 = 2x \rightarrow \frac{170} {2} = 85 = x`.



.. class:: smaller-slide-title

Effizientere Suche bei bekannter Verteilung (hier expo.)
--------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/lagrange/expo-1.svg
            :width: 85%
            :align: center
        
    .. layer:: incremental overlay
    
        .. image:: images/lagrange/expo-2.svg
            :width: 85%
            :align: center

    .. layer:: incremental overlay
    
        .. image:: images/lagrange/expo-3.svg
            :width: 85%
            :align: center

    .. layer:: incremental overlay
    
        .. image:: images/lagrange/expo-4-quadratic-approx.svg
            :width: 85%
            :align: center


.. supplemental::

    In diesem Beispiel gehen wir davon aus, dass die Werte *im Wesentlichen* exponentiell verteilt sind. Das bedeutet, dass die Differenz zwischen zwei aufeinanderfolgenden Werten immer größer wird.

    Sei beispielsweise ein Array ``a`` mit folgenden Werten geben (Auszug):

    .. csv-table::
        :header: "x", "y"
        :widths: 10, 10

        0, 0
        ..., ...
        20, 5
        ..., ...
        50, 25
        ..., ...
        70, 75.7
        ..., ...
        90, 110
        ..., ...
        110, 380
        ..., ...
        125, 579.5
        ..., ...
        130, 794


    Wenn man jetzt exemplarisch die Paare: :math:`(x = 20, y = 5.0)`, und :math:`(x = 130, y = 794)` betrachtet, und eine lineare Approximation durchführt, dann könnte man zu dem Schluss kommen, dass die Funktion :math:`f(x) = 6.1\cdot x` eine gute Approximation ist.

    Würde man eine quadratische Approximation mit Hilfe von Lagrange durchführen, zum Beispiel mit den Werten :math:`(x = 20, y = 5.0)`, :math:`(x = 90, y = 110)` , und :math:`(x = 130, y = 794)`. Dann wäre der Fehler zwischen der realen Verteilung und der angenommen deutlich geringer, da die quadratische Funktion die Werte besser approximiert.

    In diesem Fall Fall wäre die Funktion: :math:`p(x) = \frac{39}{275}x^2 - \frac{141}{10}x + \frac{2533}{11}`
    In diesem Fall können wir die Position des Wertes 650 im Array besser abschätzen (durch die Aufstellung der Umkehrfunktion und dann einsetzen von 650): :math:`\approx 123`.

    .. warning::

        Eine vernünftige Interpolation ist nur dann möglich, wenn die Verteilung der Werte im Wesentlichen bekannt ist. 




Approximation der Verteilung
--------------------------------------------------------

.. important::

    Wenn wir die Verteilung der Werte kennen, können wir effizientere Algorithmen entwickeln.

.. container:: incremental

    .. rubric:: Beispiel:

    Wenn wir wissen, dass die Werte quadratisch verteilt sind (``Array[10] a = { 1, 4, 9,16, ..., 100 }``), und wir zum Beispiel wissen, dass der kleinste Wert im Array :math:`1` und der größte Wert :math:`100` (an Stelle/mit Index :math:`10`) ist, den wir im Array gespeichert haben, dann macht es „keinen“ Sinn den Wert :math:`85` oder :math:`5` in der Mitte zu suchen! (85 findet sich vermutlich an Stelle :math:`9 = \lfloor\sqrt{85}\rfloor`).



.. class:: smaller-slide-title

Modellierung durch Interpolation: hier Lagrange-Polynome
--------------------------------------------------------

.. stack:: 

    .. layer::

        Speichert unser Array kardinal skalierte Daten, so können diese modelliert werden. Das einfachste Prinzip ist die Polynominterpolation mittels Lagrange-Polynomen.

        Das Ziel ist es, ein Polynom :math:`p(x)` zu finden, das eine Funktion :math:`f(x)` an einer gegebenen Menge von Punkten :math:`(x_1, y_1), \dots, (x_n, y_n)` exakt interpoliert. Das heißt:

        .. math::

            p(x_i) = y_i \quad \text{für alle } i = 1, \dots, n

    .. layer:: incremental

        .. admonition:: Satz

            Das Lagrange-Interpolationspolynom :math:`p(x)` wird als Summe von Lagrange-Basispolynomen :math:`l_i(x)` aufgebaut: 

            .. math::

                p(x) = \sum_{i=1}^n \left( y_i \cdot l_i(x) \right)

            wobei :math:`l_i(x)` das :math:`i`-te Lagrange-Basispolynom gegeben ist durch:

            .. math::

                l_i(x) = \prod_{\substack{j= 1 \\ j \neq i}}^{n} \frac{x - x_j}{x_i - x_j}

    .. layer:: incremental

        Sind :math:`n` Tupel :math:`(x_n ,y_n ) ∈ \mathbb{R}_2` reeller Zahlen gegeben mit :math:`x_l \neq x_m` für :math:`l  \neq m`. 
        
        Das Lagrange-Interpolationspolynom hat dann höchstens den Grad :math:`n-1` und es gilt :math:`p(x_i ) = y_i` für alle :math:`i= 1,...,n`.

    .. layer:: incremental

        .. rubric:: Beispiel

        Gegeben sein die zwei Punkte: :math:`(x_1, y_1) = (1, 2)` und :math:`(x_2, y_2) = (3, 4)`.

        Das Lagrange-Polynom :math:`p(x)` wäre dann:

        1. \
           
           .. math::
   
                l_1(x) = \frac{x - x_2}{x_1 - x_2} = \frac{x - 3}{1 - 3} = \frac{3 - x}{2}
           
        2. \
           
           .. math::
   
                l_2(x) = \frac{x - x_1}{x_2 - x_1} = \frac{x - 1}{3 - 1} = \frac{x - 1}{2}
           
        3. \
           
           .. math::
   
                p(x) = y_1 \cdot l_1(x) + y_2 \cdot l_2(x) = 2 \cdot \frac{3 - x}{2} + 4 \cdot \frac{x - 1}{2} = x + 1
           

        Nach Ausmultiplizieren und Zusammenfassen ergibt das ein Polynom, das durch beide Punkte verläuft.

    .. layer:: incremental

        Wenn zwei Punkte gegeben sind, ist das Lagrange Polynom somit:

        .. math::

            p(x) = y_1 \cdot \frac{x - x_2}{x_1 - x_2} + y_2 \cdot \frac{x - x_1}{x_2 - x_1}

        .. incremental::
            
            Wenn drei Punkte gegeben sind ist das Lagrange Polynom somit:

            .. math::

                \begin{array}{rl}
                p(x) = & y_1 \cdot \frac{(x - x_2)(x - x_3)}{(x_1 - x_2)(x_1 - x_3)} + \\ 
                       & y_2 \cdot \frac{(x - x_1)(x - x_3)}{(x_2 - x_1)(x_2 - x_3)} + \\ 
                       & y_3 \cdot \frac{(x - x_1)(x - x_2)}{(x_3 - x_1)(x_3 - x_2)}
                \end{array}


.. supplemental::

    Der Grad unseres Lagrange-Polynoms ist immer um 1 kleiner als die Anzahl der gegebenen Punkte (die Terme des Basispolynom sind nur für :math:`j \neq i` definiert). Das bedeutet, dass wir für zwei Punkte ein lineares Polynom erhalten, für drei Punkte ein quadratisches Polynom, für vier Punkte ein kubisches Polynom, und so weiter.



.. class:: integrated-exercise

Übung
--------    

.. exercise:: Bestimme p(2)

    Bestimmen Sie direkt :math:`p(2)` für das quadratische Polynom mit den Eigenschaften:
    
    .. math::

        p(-10) = 3, p(-8) = 1, p(-4) =-1

    .. solution:: 
        :pwd: korrektes Arbeiten ist wichtig

        .. math::

            \text{d. h.}\qquad (x_1 = -10, y_1 = 3), (x_2 = -8, y_2 = 1), (x_3 = -4, y_3 = -1)

            p(x) = 3 \cdot \frac{(x + 8)(x + 4)}{12} + 1 \cdot \left(-\frac{(x + 10)(x + 4)}{8}\right) + (-1) \cdot \frac{(x + 10)(x + 8)}{24}

            p(2) = 3 \cdot \frac{(2 + 8)(2 + 4)}{12} + 1 \cdot \left(-\frac{(2 + 10)(2 + 4)}{8}\right) + (-1) \cdot \frac{(2 + 10)(2 + 8)}{24} = 1


        Aufstellen des Lagrangepolynoms (hier nicht gefordert!):

        .. math::

            p(x) = \frac{6(x^2 + 12x + 32)}{24} - \frac{3(x^2 + 14x + 40)}{24} - \frac{x^2 + 18x + 80}{24}.
        
            p(x) = \frac{x^2 + 6x - 4}{12}.

.. exercise:: Bestimme p(-1)

    Für die gegebenen Punkte, bestimmen Sie erst das Lagrange Polynom :math:`p(x)` im Allgemeinen und rechnen Sie dann den Wert für :math:`p(-1)` aus.

    .. math::

        p(2) = 4, p(4) = 6, p(7) = 3

    .. solution:: 
        :pwd: korrektes Arbeiten ist immer wichtig

        .. math::
                
              \text{d. h.}\qquad  (x_1 = 2, y_1 = 4), (x_2 = 4, y_2 = 6), (x_3 = 7, y_3 = 3)

        1. Basispolynom :math:`l_1(x)`:
   
           .. math::

                l_1(x) = \frac{x - x_2}{x_1 - x_2} \cdot \frac{x - x_3}{x_1 - x_3}

           Setzen der Werte:

           .. math::
    
                l_1(x) = \frac{x - 4}{2 - 4} \cdot \frac{x - 7}{2 - 7} = \frac{x - 4}{-2} \cdot \frac{x - 7}{-5}

           Vereinfachen:

           .. math::

                l_1(x) = \frac{(x - 4)(x - 7)}{10}


        2. Basispolynom :math:`l_2(x)`:
        
           .. math::
                l_2(x) = \frac{x - x_1}{x_2 - x_1} \cdot \frac{x - x_3}{x_2 - x_3}
        
           Setzen der Werte:
            
           .. math::
                l_2(x) = \frac{x - 2}{4 - 2} \cdot \frac{x - 7}{4 - 7} = \frac{x - 2}{2} \cdot \frac{x - 7}{-3}
        
           Vereinfachen:
        
           .. math::
                l_2(x) = -\frac{(x - 2)(x - 7)}{6}
        
        3. Basispolynom :math:`l_3(x)`:
        
           .. math::
                  l_3(x) = \frac{x - x_1}{x_3 - x_1} \cdot \frac{x - x_2}{x_3 - x_2}
        
           Setzen der Werte:
        
           .. math::
                l_3(x) = \frac{x - 2}{7 - 2} \cdot \frac{x - 4}{7 - 4} = \frac{x - 2}{5} \cdot \frac{x - 4}{3}
        
           Vereinfachen:

           .. math::
                l_3(x) = \frac{(x - 2)(x - 4)}{15}

        Multiplizieren und zusammenfassen:

        .. math::
            p(x) = \frac{4(x - 4)(x - 7)}{10} - \frac{6(x - 2)(x - 7)}{6} + \frac{3(x - 2)(x - 4)}{15}

            p(x) = \frac{4x^2 - 44x + 112}{10} - x^2 + 9x - 14 + \frac{3x^2 - 18x + 24}{15}.


            p(x) = -\frac{2x^2}{5} + \frac{17x}{5} - \frac{6}{5}.

            p(-1) = -\frac{2(-1)^2}{5} + \frac{17(-1)}{5} - \frac{6}{5} = - \frac{2}{5} - \frac{17}{5} - \frac{6}{5} = -\frac{25}{5} = -5



Interpolierende Suche - lineare Approximation
--------------------------------------------------------

.. rubric:: Beispiel

:Gegeben: Vom Array :java:`a` sei bekannt: :java:`a[1] = 0`, :java:`a[20] = 30` und :java:`a[40] = 120`.

:Frage: Ist der Wert ``50`` im Array enthalten?

.. incremental:: 

    .. note::
        :class: far-smaller margin-left-1em

        Wir möchten die Position des Wertes 50 im Array abschätzen! Deswegen sind im linearen Modell die Paare :math:`(x_1,y1) = (30,20)` und :math:`(x_2,y_2) = (120,40)` zu wählen. D. h. die Indizes sind unsere y-Werte. 

    :Lösung: Das Lagrangepolynom :math:`p(x)` mit :math:`p(30) = 20` und :math:`p(120) = 40` lautet:

        .. math::

            p(x) = 20· \frac{x−120}{30−120} + 40· \frac{x−30}{120−30}

        .. incremental::

            Für den gesuchten Wert ``50`` ergibt sich als zu untersuchende Position:

            .. math::

                p(50) = 20· \frac{50−120}{30−120} + 40· \frac{50−30}{120−30} = \frac{220}{9} \approx 24

.. supplemental::

    - Eine binäre Suche würde in diesem Fall mit der Position :math:`{40+20 \over 2} = 30` beginnen.

    - \
    
      .. hint::
        :class: smaller

        Das Lagrangepolynom kann per Konstruktion die Position der Werte :math:`30` und :math:`120` perfekt bestimmen:

        .. math::

                p(30) = 20· \frac{30−120}{30−120} + 40· \frac{30−30}{120−30} = 20

                p(120) = 20· \frac{120−120}{30−120} + 40· \frac{120−30}{120−30} = 40



Interpolierende Suche - quadratische Approximation
--------------------------------------------------------

.. rubric:: Beispiel

:Gegeben: Vom Array :java:`a` sei bekannt: :java:`a[1] = 0`, :java:`a[20] = 30` und :java:`a[40] = 120`.

:Frage: Ist der Wert ``50`` im Array enthalten?

.. incremental:: 

    :Lösung: :math:`p(x)` mit :math:`p(0) = 1`,  :math:`p(30) = 20` und :math:`p(120) = 40` lautet:


        .. math::

            \begin{array}{rl}
            p(x) = & 1 \cdot \frac{(x - 30)(x - 120)}{(0-30)(0-120))} +\\
                  & 20 \cdot \frac{(x - 0)(x - 120)}{(30-0)(30-120)} + \\
                 & 40 \cdot \frac{(x - 0)(x - 30)}{(120-0)(120-30)} 
            \end{array}

        .. incremental::

            Für den gesuchten Wert ``50`` ergibt sich als zu untersuchende Position:

            .. math::

                p(50) \approx 29



Interpolierende Suche - Vergleich
--------------------------------------------------------

.. stack::

    .. layer::

        .. image:: images/lagrange/comparison.svg
            :width: 85%
            :align: center

    .. layer:: incremental

        - Auf gleichverteilten Daten hat lineare Interpolationssuche O(log log n).
        - Auf anderen Verteilungen ist lineare Interpolation oft schlechter als binäre Suche.
        - Quadratische Interpolation hat ein erweitertes Modell und schlägt binäre Suche häufig.


Lineare interpolierende Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: far-smaller

    Algorithm linearInterpolatingSearch(A,needle) 
        lower = 1
        upper = length(A)
        vL = A[lower]
        if vL == needle then return lower
        vU = A[upper]
        if vU == needle then return upper
        while upper > lower do
            pos = round(lower·(needle−vU)/(vL−vU) + 
                        upper·(needle−vL)/(vU−vL))
            value = A[pos]
            if value == needle then 
                return pos
            else if value < needle then 
                lower = max(pos, lower+1), vL = A[lower]
            else                       
                upper = min(pos, upper-1), vU = A[upper]
        return nil



Exponentielle Suche im sortierten (unbeschränkten) *Array*
-----------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: far-smaller

    Algorithmus ExponentialSearch(A,needle)
        i = 1
        while A[i] < needle do
            i = i * 2
        return BinarySearch(A,floor(i/2) + 1,i,needle)


.. supplemental::

    Die Idee ist erst mit einer exponentiellen Schrittweite zu springen, um dann mit einer binären Suche den Wert zu finden. Die Laufzeit ist :math:`O(\log(i))` wobei :math:`i` die Position des gesuchten Wertes ist. Die Laufzeit ist also :math:`O(\log(n))`.


.. class:: integrated-exercise

Übung
--------

.. exercise:: Wer sucht, der findet 5?

    Folgende Werte sind vom Array A bekannt:

    :math:`A[1] = -27,\; A[15] = 13,\; A[29] = 29`

    Gesucht wird der potentielle Index des Wertes ``5``. Welcher Index ``i`` sollte als nächstes untersucht werden bei binärer, linearer oder quadratisch interpolierender Suche?

    .. solution::
        :pwd: wer_sucht_der_sucht

        :binäre Suche:

            Da 5 zwischen :math:`-27 = A[1]` und :math:`13 = A[15]` liegt, sollte bei binärer Suche als nächstes der Index :math:`i = (1 + 15) / 2 = 8` untersucht werden.

        :linear interpolierende Suche:

            Für die linear interpolierende Suche wird :math:`p_{lin}(5)` berechnet unter den Bedingungen, dass :math:`p_{lin}(-27) = 1` und :math:`p_{lin}(13) = 15` ist. Es ergibt sich:

            :math:`p_{lin}(15) = 1·{5-13 \over -27-13} + 15·{5+27 \over 13+7} = \frac{1}{5} + 12 ≈12`

        :quadratisch interpolierende Suche:

            Für die quadratisch interpolierende Suche wird :math:`p_{quad}(5)` berechnet unter den Bedingungen, dass :math:`p_{quad}(-27) = 1`, :math:`p_{quad}(13) = 15` und :math:`p_{quad}(29) = 29` ist. Es ergibt sich:

            :math:`p_{quad}(5) = \frac{49}{5} ≈ 10`

.. exercise:: Wer sucht, der findet -1?

    Folgende Werte sind vom Array A bekannt:

    :math:`A[1] = -13,\; A[7] = -4,\; A[13] = 11`

    Gesucht wird der potentielle Index des Wertes ``-1``. Welcher Index ``i`` sollte als nächstes untersucht werden bei binärer, linearer oder quadratisch interpolierender Suche?

    .. solution::
        :pwd: wer_sucht_der_findet_vielleicht

        :binäre Suche:
        
            Da :math:`-1` zwischen :math:`-4 = A[7]` und :math:`11 = A[13]` liegt, sollte bei binärer Suche als nächstes der Index :math:`i= (7+13)/ 2 = 10` untersucht werden.

        :linear interpolierende Suche:
            
            Für die linear interpolierende Suche wird :math:`p_{lin}(-1)` berechnet unter den Bedingungen, dass :math:`p_{lin}(-4) = 7` und :math:`p_{lin}(11) = 13` ist. Es ergibt sich:
    
            :math:`p_{lin}(-1) = 7·{-1-11 \over -4-11} + 13·{-1+4 \over 11+4} = \frac{41}{5} ≈ 8`

        :quadratisch interpolierende Suche: 
            
            Für die quadratisch interpolierende Suche wird :math:`p_{quad}(-1)` berechnet unter den Bedingungen, dass :math:`p_{quad}(-13) = 1`, :math:`p_{quad}(-4) = 7` und :math:`p_{quad}(11) = 13` ist. Es ergibt sich:
    
            :math:`p_{quad}(-1) = \frac{43}{5} ≈ 9`



.. class:: integrated-exercise

Übung
--------

.. exercise:: Lineare Interpolierende Suche

    Setzen Sie den Algorithmus für die lineare interpolierende Suche in einer Programmiersprache Ihrer Wahl um.

    Testen Sie den Algorithmus mit folgenden Arrays:

    .. code:: python
        :class: far-smaller

        A = [1, 3, 5, 7, 9, 11, 13, 15] # linear verteilt (2x-1)

        B = [0, 7, 13, 22, 27, 32, 44, 49] # approx. linear verteilt (approx. 7x)

        C = [0, 4, 16, 36, 64, 100, 144, 196] # quadratisch verteilt (4x^2)

    Wie viele Schritte (im Sinne von Schleifendurchläufen) sind maximal notwendig, um festzustellen ob ein Wert im Array enthalten ist oder nicht?

    .. solution:: 
        :pwd: Ist Schnell!

        Für Array A sind maximal 3 Schritte notwendig, für Array B sind auch maximal 3 Schritte und für Array C maximal 5 Schritte.

        .. include:: code/linear_interpolating_search.py
            :code: python
            :class: smaller
            :number-lines: 


.. class:: integrated-exercise

Übung
--------

.. exercise:: Exponentiell Interpolierende Suche

    Wann macht es Sinn die exponentiell interpolierende Suche zu verwenden?

    Bedenken Sie zum Beispiel ein (virtuelles) Array. D. h. ein Array, dass durch Bildungsgesetz definiert ist.

    .. solution:: 
        :pwd: sonst_ist_er_nicht_wirklich besser

        1. Wenn es keine (echte) obere Grenze gibt, da dann kein oberster Wert für die binäre Suche bestimmt werden kann.

        2. Insbesondere wenn die Werte am unteren Rande sind. 
         
           Zum Beispiel ist die Suche nach einem Wert wie 1.01^{10/20} sehr viel schneller als eine reine binäre Suche!

        .. include:: code/exponential_search.py
            :code: python
            :class: smaller
            :number-lines:
