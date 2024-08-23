.. meta:: 
    :author: Michael Eichberg
    :keywords: "Web-Entwicklung", "Vortragsthemen"
    :description lang=de: Themen für Vorträge im Rahmen der Vorlesung Web-Entwicklung
    :id: lecture-web-programming-und-verteilte-systeme-vortragsthemen
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
.. role:: smaller
.. role:: far-smaller
.. role:: monospaced

.. role:: raw-html(raw)
   :format: html



Web-Entwicklung und Verteilte Systeme
=======================================================

.. rubric::  Vortragsthemen

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




.. class:: transition-fade

Rahmenbedingungen - Inhalt
-----------------------------

.. class:: incremental list-with-explanations

- Vorträge, die Webtechnologien zum Gegenstand haben, sollen nur solche Features vorstellen, die von Safari, Chrome, *und* Firefox in den jeweils aktuellen Versionen vollständig unterstützt werden (siehe `caniuse.com <https://caniuse.com>`__).
- Vorträge, die konkrete Technologien vorstellen, sollen direkte Umsetzung erlauben. D. h. es soll möglich sein, das Gelernte direkt in einem Projekt umzusetzen.



.. class:: new-section

Vorträge am 4. Juni
----------------------


Barrierefreiheit und HTML (2 Personen)\ [#]_
--------------------------------------------------------------

.. rubric:: 4. Juni

- Allgemeine Einführung in das Thema Barrierefreiheit (:eng:`Accessibilty`) 
- Barrierefreie HTML Webseiten mit HTML5 Gestalten
- Erste Quellen:

  - https://developer.mozilla.org/en-US/docs/Learn/Accessibility
  - https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA
  - https://web.dev/learn/accessibility
  - https://www.w3.org/TR/wai-aria/

Achtung: *CSS und JavaScript sollen nicht behandelt werden*.

.. [#] Präsentation nach der Einführung in HTML möglich.



HTML Forms (2 Personen)\ [#]_
---------------------------------------------------

.. rubric:: 4. Juni

- Formulare mit HTML; insbesondere Input Typen und Validierung von Daten
- Erste Quellen:
 
  - https://developer.mozilla.org/en-US/docs/Learn/Forms
  - https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form
  - https://web.dev/learn/forms

Achtung: *CSS und JavaScript soll nicht behandelt werden*.

.. [#] Präsentation nach der Einführung in HTML möglich.



SVG - Vektorgrafik auf Webseiten (1 Person)\ [#]_
----------------------------------------------------

.. rubric:: 4. Juni

- Einführung in SVG und Vektorgrafiken
- HTML5 und eingebettete SVGs
- Erste Quellen:
 
  - https://developer.mozilla.org/en-US/docs/Web/SVG
  - https://svg-tutorial.com
  - https://www.w3schools.com/graphics/svg_intro.asp#:~:text=SVG%20stands%20for%20Scalable%20Vector,supported%20by%20all%20major%20browsers.

Achtung: *JavaScript und CSS sollen nicht behandelt werden*.

.. [#] Präsentation nach der Einführung in HTML möglich.




.. class:: new-section

Vorträge am  12. Juni
----------------------


Common.js Modules (1 Person)
-------------------------------------------------------------------------------

.. rubric:: 12. Juni

- Modulsystem (insbesondere) für NodeJS
- https://nodejs.org/api/modules.html



ECMAScript Modules (1 Person)
--------------------------------------------------------------------------------

.. rubric:: 12. Juni

- Modulsystem für JavaScript (NodeJS und Browser)
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Modules


Saas (1 Person)
----------------------------------------------------------

.. rubric:: 12. Juni

- CSS Erweiterung (CSS Preprocessor)
- https://sass-lang.com/


Less (1 Person)
----------------------------------------------------------

.. rubric:: 12. Juni

- CSS Preprocessor
- https://lesscss.org/



.. class:: new-section

Vorträge am 17. Juni
-----------------------


Bootstrap (2 Personen)
----------------------------------------------------------

.. rubric:: 17. Juni

- Entwicklung von *responsive* Webseiten
- https://getbootstrap.com/

Achtung: Sass (und Less) werden getrennt behandelt werden.



.. class:: new-section

Vorträge am 18. Juni
-----------------------


Express (3 Personen)
----------------------------------------------------------

.. rubric:: 18. Juni

- Einführung in Express
- *Programmierung von Endpunkten*
- https://expressjs.com/

.. rubric:: Hinweise

- *Grundlagen des RESTful Designs brauchen nicht in der Präsentation behandelt werden; d. h. die Präsentation soll sich darauf konzentrieren, wie man Express verwendet (Hands-on.)*
- WebSockets sollen nicht behandelt werden (es gibt einen weiteren Vortrag dazu).




.. class:: new-section

Vorträge am 24. Juni
----------------------


Electron (2 Personen)
----------------------------------------------------------

.. rubric:: 24. Juni

- Framework zur Entwicklung von Cross-Platform Desktop-Anwendungen mit Webtechnologien.
- https://www.electronjs.org/


WebSockets (und Express) (1 Person)
-------------------------------------

.. rubric:: 24. Juni

- Einführung in WebSockets
- Verwendung von WebSockets in Browsern
  
  https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API
- Programmierung von WebSockets in Express

  https://www.npmjs.com/package/express-ws?activeTab=readme
  (Abstimmen mit der Gruppe, die Express präsentiert.)




WebRTC (1 Person)
--------------------------------

.. rubric:: 24. Juni

- Echtzeitkommunikation für das Web
- https://webrtc.org/


Vue.js (3 Personen)
-----------------------------------------------

.. rubric:: 24. Juni

- JavaScript Framework für Web User Interfaces
- https://vuejs.org/



.. class:: new-section

Vorträge am 25. Juni
----------------------


Typescript (2 Personen)
------------------------------------------------------------------

.. rubric:: 25. Juni

- JavaScript with types
  
  (Der Vortrag sollte sich auf die Vorteile von statischer Typisierung konzentrieren; insbesondere für das :eng:`Programming in the large`.)
- https://www.typescriptlang.org/


Angular (4 Personen) 
----------------------------------------------------------

.. rubric:: 25. Juni

- Web-Application Framework
- https://angular.io/


Svelte (4 Personen)
----------------------------------------------------------

.. rubric:: 25. Juni

- Javascript Web Framework

  - Svelte
  - SvelteKit (:ger-quote:`Server Side Rendering`)
- https://svelte.dev/
  

React (4 Personen)
----------------------------------------------------------

.. rubric:: 25. Juni

- Frontend orientierte Bibliothek für die Entwicklung von Benutzeroberflächen
- https://react.dev/



*Consensus Algorithms* (2 Personen)\ [#]_
------------------------------------------

.. rubric:: 25. Juni

- Einführung in Paxos
- Einführung in Raft 
- Erste Quellen:

  - https://en.wikipedia.org/wiki/Paxos_(computer_science)
  - https://raft.github.io


.. [#] Präsentation nach den grundlegenden Konzepten (insbesondere 2PC) von verteilten Systemen.
