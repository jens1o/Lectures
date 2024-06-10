.. meta:: 
    :author: Michael Eichberg
    :keywords: "Web Programmierung", "JavaScript"
    :description lang=de: Webprogrammierung mit JavaScript
    :id: lecture-web-programming-javascript
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
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: the-blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above

.. role:: raw-html(raw)
   :format: html



Webprogrammierung mit JavaScript
================================================

JavaScript und die Webprogrammierung

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: 1.0

.. supplemental::

    :Folien: 
        
        |html-source|

        |pdf-source|

    :Fehler auf Folien melden:
        https://github.com/Delors/delors.github.io/issues



Historie
------------------------------------------------

.. raw:: html

	<script src="lib/Timeline-0.0.0/Timeline.js" type="text/javascript"></script>
	<link href="lib/Timeline-0.0.0/Timeline.css" type="text/css" rel="stylesheet" />
    <svg id="JavaScript-Timeline" class="timeline"/>
    <script type="text/javascript">
        Timeline.draw("JavaScript-Timeline",[
            {d:"03/1996",t:"1.0"},
            {d:"08/1996",t:"1.1"},
            {d:"06/1997",t:"1.2"},
            {d:"10/1998",t:"1.3 - ECMA-262 1st + 2nd edition"},
            {d:"11/2000",t:"1.5 - ECMA-262 3rd edition"},						
            {d:"11/2005",t:"1.6"},
            {d:"10/2006",t:"1.7"},
            {d:"06/2008",t:"1.8"},
            {d:"07/2010",t:"1.8.5 - ECMAScript 5"},
            {d:"06/2015",t:"ECMAScript 6"},
        ]);
    </script>
    
Seit 2016 gibt es jährliche Updates (ECMAScript 2016, 2017, 2018, 2019, 2020, 2021, 2022, ...)



Grundlagen
--------------

.. class:: incremental

- Objektorientiert
  
  .. class:: list-with-explanations incremental

  - Protoypische Vererbung
  - Objekte erben von anderen Objekten.
  - Objekte als allgemeine Container
  
    (Im Grunde eine Vereinheitlichung von Objekten und Hashtabellen.)
  - seit ES6 werden auch Klassen unterstützt; diese sind aber nur syntaktischer Zucker!
- Skriptsprache

  .. class:: incremental

  - *Loose Typing*/*Dynamische Typisierung*
  - *Load and go-delivery* (Lieferung als Text/Quellcode)
  - Garbage Collected
  - Single-Threaded
- Funktionen sind Objekte erster Klasse
- Ein (globaler) Namespace
- Syntaktisch eine Sprache der "C"-Familie
- Standardisiert durch die ECMA (ECMAScript)
- Verwendet in Browsern, auf Servern (`Node.js <http://nodejs.org/>`__), in Desktop-Anwendungen (Electron)



.. ideas
   static vs live nodelists....
   async await...
   Event-Capturing vs. Event-Bubbling
   CORS

Datentypen
------------------------------------------------

.. include:: code/Datatypes.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable
   :tab-width: 4


Functions
------------------------------------------------

.. include:: code/Functions.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable
   :tab-width: 4


Variables
------------------------------------------------

.. include:: code/Variables.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable
   :tab-width: 4


JSON
------------------------------------------------

.. include:: code/JSON.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable
   :tab-width: 4


Referenzen
------------------------------------------------

- `HTML DOM API  <https://developer.mozilla.org/en-US/docs/Web/API/HTML_DOM_API>`__