.. meta:: 
    :author: Michael Eichberg
    :keywords: "Verteilte Anwendungen", "Modelle"
    :description lang=de: Architekturen von verteilten Anwendungen
    :id: lecture-ds-architectures-of-distributed-applications
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental
.. role:: eng
.. role:: ger
.. role:: ger-quote
.. role:: minor
.. role:: obsolete
.. role:: dhbw-red
.. role:: dhbw-gray
.. role:: dhbw-light-gray
.. role:: the-blue
.. role:: the-green
.. role:: the-orange
.. role:: shiny-green
.. role:: shiny-red
.. role:: black
.. role:: dark-red
.. role:: huge

.. role:: raw-html(raw)
   :format: html


Modelle und Architekturen verteilter Anwendungen
=================================================================================================

.. container:: line-above padding-bottom-1em

  :Dozent: **Prof. Dr. Michael Eichberg**
  :Kontakt: michael.eichberg@dhbw-mannheim.de
  :Version: |date|



.. class:: new-section

Microservices [Newman2021]_
---------------------------



.. class:: no-title 

Microservice mit REST Schnittstelle
-------------------------------------

.. container:: two-columns no-default-width

  .. container:: column no-separator

    .. rubric:: Microservices

    Ein einfacher Microservice, der eine REST Schnittstelle anbietet und Ereignisse auslöst.

    .. container:: incremental question smaller

      Wo liegen hier die Herausforderungen?

  .. container:: column

    .. image:: images/microservices/basisbeispiel.svg
       :height: 1160px



.. container:: supplemental

  Ein große Herausforderung ist das Design der Schnittstellen. Um wirkliche Unabhängigkeit zu erreichen, müssen die Schnittstellen sehr gut definiert sein. Sind die Schnittstellen nicht klar definiert oder unzureichend, dann kann das zu viel Arbeit und Koordination zwischen den Teams führen, die eigentlich unerwünscht ist!



Schlüsselkonzepte von Microservices
-------------------------------------

.. class:: incremental list-with-explanations

- können unabhängig bereitgestellt werden (:eng:`independently deployable`) 
  
  und werden unabhängig entwickelt
- modellieren eine Geschäftsdomäne
  
  Häufig entlang einer Kontextgrenze (eng. Bounded Context) oder eines Aggregats aus DDD
- verwalten Ihren eigenen Zustand
  
  D.h. keine geteilten Datenbanken
- sind klein
  
  Klein genug, um durch (max.) ein Team entwickelt werden zu können

- flexibel bzgl. Skalierbarkeit, Robustheit, eingesetzter Technik
- erlauben das Ausrichten der Architektur an der Organisation (vgl. Conway's Law)
  

Microservices und Conway's Law
-------------------------------------

.. container:: two-columns no-default-width

  .. container:: column no-separator

    .. rubric:: Traditionelle Schichtenarchitektur 
       
    .. image:: images/microservices/aenderungen-bei-klassischer-architektur.svg
      :height: 835px
       
  .. container:: column

    .. rubric:: Microservices Architektur

    .. image:: images/microservices/aenderungen-bei-microservices-architektur.svg
      :height: 960px
       

Microservices und Technologieeinsatz
-------------------------------------

Microservices sind flexibel bzgl. des Technologieeinsatzes und ermöglichen den Einsatz :ger-quote:`der geeignetsten` Technologie.

.. image:: images/microservices/technologische-flexibilitaet.svg
   :height: 700px
   :align: center


Microservices und Skalierbarkeit
-------------------------------------

Sauber entworfene Microservices können sehr gut skaliert werden.

.. image:: images/microservices/skalierbarkeit.svg
   :height: 899px
   :align: center



.. class:: no-title

Microservices und Transaktionen
-------------------------------------

.. container:: two-columns no-default-width

  .. container:: column no-separator

    .. rubric:: Implementierung einer langlebigen Transaktion?
       
    
       
  .. container:: column

    .. image:: images/sagas/transaktion.svg
      :height: 1160px


.. container:: supplemental

  Die Implementierung von Transaktionen ist eine der größten Herausforderungen bei der Entwicklung von Microservices. 



.. class:: no-title

Transaktionen mit Hilfe von Sagas
-------------------------------------

.. container:: two-columns no-default-width

  .. container:: column no-separator

    .. rubric:: Aufteilung einer langlebigen Transaktion mit Hilfe von Sagas
       
  .. container:: column

    .. image:: images/sagas/transaktion-mit-saga.svg
      :height: 1160px


Langlebige Transaktionen mit Hilfe orchestrierter Sagas
--------------------------------------------------------

.. image:: images/sagas/orchestrierte-saga.svg
   :height: 760px
   :align: center

.. container:: supplemental

  Die orchestrierte Saga ist eine Möglichkeit, um langlebige Transaktionen zu implementieren. 

  .. class:: positive-list
  
  - Mental einfach

  .. class:: negative-list list-with-explanations 

  - Hoher Grad an *Domain Coupling* 
  
    Da es sich im Wesentlichen um fachlich getriebene Kopplung handelt, ist diese Kopplung häufig akzeptabel. Die Kopplung erzeugt keine technischen Schulden (:eng:`technical debt`).
  - Hoher Grad an *Request-Response* Interaktionen
  - Gefahr, dass Funktionalität, die besser in den einzelnen Services (oder ggf. neuen Services) unterzubringen wäre, in den Bestellung Service wandert.


Langlebige Transaktionen mit Hilfe choreografierter Sagas
----------------------------------------------------------

.. image:: images/sagas/choreografierte-saga.svg
   :height: 920px
   :align: center



.. class:: no-title center-child-elements

Die Wahl der richtigen Architektur ist ein Tradeoff!
-----------------------------------------------------

.. container:: assessment bold huge dhbw-red text-align-center

  Die Wahl der Softwarearchitektur ist immer eine Abwägung von vielen Tradeoffs!


.. container:: supplemental

  Weitere Aspekte, die berücksichtigt werden können/müssen:

  - Cloud (und ggf. Serverless)
  - Mechanical Sympathy
  - Testen und Deployment von Mircoservices (Stichwort: *Canary Releases*)
  - Monitoring und Logging
  - Service Meshes
  - ...



Literatur
-------------------------------------

.. [Newman2021] Sam Newman, **Building Microservices: Designing Fine-Grained Systems**, O'Reilly, 2021.
