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

	<script src="lib/timeline/timeline.js" type="text/javascript"></script>
	<link href="lib/timeline/timeline.css" type="text/css" rel="stylesheet" />
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
  - Objekte *erben* von anderen Objekten.
  - Objekte als allgemeine Container
  
    (Im Grunde eine Vereinheitlichung von Objekten und Hashtabellen.)
  - seit ES6 werden auch Klassen unterstützt; diese sind aber nur syntaktischer Zucker.
- Skriptsprache

  .. class:: incremental

  - *Loose Typing*/*Dynamische Typisierung*
  - *Load and go-delivery* (Lieferung als Text/Quellcode)
  - Garbage Collected
  - Single-Threaded
- Funktionen sind Objekte erster Klasse
- Ein (globaler) Namespace
- Syntaktisch eine Sprache der "C"-Familie (viele Ähnlichkeiten zu Java)
- Standardisiert durch die ECMA (ECMAScript)
- Verwendet ganz insbesondere in Browsern, aber auch Serverseitig (`Node.js <http://nodejs.org/>`__) oder in Desktop-Anwendungen (Electron)



Datentypen
------------------------------------------------

.. container:: scrollable
      
   .. include:: code/Datatypes.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



Vergleich von Werten
------------------------------------------------

.. container:: scrollable

   .. include:: code/ComparingValues.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



Bedingungen und Schleifen
------------------------------------------------

.. container:: scrollable

   .. include:: code/LoopsAndConditions.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



Functions
------------------------------------------------

.. container:: scrollable

   .. include:: code/Functions.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



Variables
------------------------------------------------

.. container:: scrollable

   .. include:: code/Variables.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



Destructuring
------------------------------------------------

.. container:: scrollable
      
   .. include:: code/Destructuring.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4



JSON
------------------------------------------------

.. container:: scrollable
      
   .. include:: code/JSON.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4


Regular Expressions
------------------------------------------------

- Built-in support by means of regular expression literals and an API
- Use Perl syntax
- Methods on regular expression objects: test (e.g., RegExp.test(String)).
- Methods on strings that take RegExps: search, match, replace, split,...

.. container:: scrollable
      
   .. include:: code/RegularExpressions.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard
      :tab-width: 4


Alles ist ein Objekt
------------------------------------------------

- ``this`` ist ein "zusätzlicher" Parameter, dessen Wert von der aufrufenden Form abhängt
- ``this`` ermöglicht den Methoden den Zugriff auf ihr Objekt
- ``this`` wird zum Zeitpunkt des Aufrufs gebunden (außer bei Arrow-Funktionen)

.. container:: scrollable

   .. include:: code/EverythingIsAnObject.js
      :code: javascript
      :number-lines:
      :class: far-far-smaller copy-to-clipboard 
      :tab-width: 4



Prototype basierte Vererbung
------------------------------------------------

.. stack::

   .. layer::

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-smaller

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain.svg
         :width: 1400px
         :align: center

   .. layer:: incremental
      
      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-smaller

         const p = { s : "p" };
         const c = Object.create(p);
         const gc = Object.create(c);
         gc.t = "q";

      .. image:: images/prototype_chain/object_literals_and_the_prototype_chain_with_update.svg
         :width: 1400px
         :align: center

   .. layer:: incremental
      
      .. rubric:: *Pseudoclassical Inheritance*

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-far-smaller


         function Person(name, title){ this.name = name; this.title = title; } // constructor
         Person.prototype.formOfAddress = function (){
            const foa = "Dear ";
            if(this.title){ foa += this.title+" "; }
            return foa + this.name; 
         }
         function Student(name, title, id, email) { // constructor
            Person.call(this, name, title);
            this.id = id;
            this.email = email;
         }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;
         
         const aStudent = new Student("Emilia Galotti", "Mrs.", 1224441, 'emilia@galotti.com'); 

   .. layer:: incremental
      
      .. rubric:: Objektabhängigkeiten

      .. code:: javascript
         :number-lines:
         :class: copy-to-clipboard far-far-smaller

         function Person(name, title){ … }
         Person.prototype.formOfAddress = function (){ … }

         function Student(name, title, id, email) { … }
         Student.prototype = Object.create(Person.prototype);
         Student.prototype.constructor = Student;
 
         const p = new Person(…); const s = new Student(…);

      .. image:: images/prototype_chain/pseudoclassical_Inheritance.svg
         :width: 1000px
         :align: center




Prototype basierte Vererbung
------------------------------------------------

.. include:: code/Prototypes.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4


Classes
------------------------------------------------

.. include:: code/Classes.js
   :code: javascript
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4



DOM Manipulation
------------------------------------------------

.. include:: code/DOM.html
   :code: html
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4


Interaktion mit Server
--------------------------

.. include:: code/EventHandling.html
   :code: html
   :number-lines:
   :class: far-far-smaller scrollable copy-to-clipboard
   :tab-width: 4



Referenzen
------------------------------------------------

- `HTML DOM API  <https://developer.mozilla.org/en-US/docs/Web/API/HTML_DOM_API>`__