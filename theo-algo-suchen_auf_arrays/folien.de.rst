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


.. class:: repetition

Lineare Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: smaller

    Algorithmus linear_search(A,n,needle)
        for i= 1,...,n do
            if A[i] == needle then
                return i
        return nil

Laufzeit und Elementzugriffe kann asymptotisch durch :math:`O(n)` abgeschätzt werden.


.. class:: repetition

Binäre Suche
--------------------------------------------------------

.. code:: pascal
    :number-lines:
    :class: smaller

    Algorithmus binary-search(A,l,u,needle)
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



Bei linearer Verteilung der Werte
--------------------------------------------------------

.. stack:: invisible

    .. layer:: 

        .. image:: images/lagrange/lin-1.svg
            :width: 85%
            :align: center
        
    .. layer:: incremental overlay
    
        .. image:: images/lagrange/lin-2.svg
            :width: 85%
            :align: center



Bei exponentieller Verteilung der Werte
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


