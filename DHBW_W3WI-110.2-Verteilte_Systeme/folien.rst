.. meta:: 
    :author: Michael Eichberg
    :keywords: "Verteilte Systeme"
    :description lang=de: Verteilte Systeme
    :id: lecture-w3wi_110.2-verteilte_systeme
    :first-slide: last-viewed
    
.. |date| date::
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
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html



W3WI_110.2 - Verteilte Systeme
================================================

.. container:: line-above tiny

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de, Raum 149B
    :Version: |date|



Kerninhalte gem. MHB
---------------------------

- Terminologie, Konzepte, Architekturen, Anforderungsprofile und Architekturmodelle für verteilte Systeme
- Entwurfs- und Implementierungsansätze
- Vergleich unterschiedlicher Middleware-Konzepte
- Synchrone und asynchrone Kommunikation, entfernter Methodenaufruf 
- Asynchrone Kommunikation und Messaging-Systeme
- Sicherheitsaspekte in verteilten Systemen


Prüfungsleistung - Portfolio
------------------------------------------

.. container:: minor box-shadow rounded-corners padding-1em
    
    .. rubric:: Hintergrund

    - das Modul hat 55 VL
    - Verteilte Systeme hat 22VL
    - Die verteilte Systeme geht mit **50** von 120 Modulpunkten ein

- 2 Bestandteile:

  1. Vorträge - max. 15 Punkte
  2. Programmieraufgabe - max. 35 Punkte


Vorträge - Rahmenbedingungen
------------------------------------------

.. class:: list-with-explanations

- Die Präsentationen sollen sich insbesondere mit den Kerninhalten der Vorlesung beschäftigen und insbesondere konzeptioneller Natur sein.  

  D. h. nach der Darstellung des Anwendungszweckes gilt es die Architektur darzustellen, wie mit Fehlern umgegangen wird, welche Services angeboten werden, welche Garantien/Sicherheitsaspekte umgesetzt werden, wie wird die Skalierbarkeit erreicht, etc. 
  
  Keine Werbevorträge!
- Die Präsentationen sind am Abend vor dem ausgemachten Termin hochzuladen in Moodle.


Vorträge - Themen
------------------------------------------


.. rubric:: Middleware Lösungen

.. container:: two-columns

  .. container::

    1. Apache Kafka
    2. Apache Zookeeper
    3. (Scala) Akka
    4. Rabbit MQ
    5. (Twitter/X) Finagle
    6. Apache Hive
    7. Apache Cassandra
    8. Apache Spark
    9. `Apache Pulsar <https://pulsar.apache.org>`__
   
  .. container::

    10. Hadoop HDFS
    11. Hadoop Yarn/MapReduce
    12. Apache Mahout
    13. `Apache Camel <https://camel.apache.org>`__
    14. `NATS <https://docs.nats.io>`__
    15. Spring Framework 
    16. `Eclipse Glassfish und Jakarta EE <https://glassfish.org>`__
    17. `Tokio <https://tokio.rs>`__
    18. `Google Spanner <https://dl.acm.org/doi/10.1145/3035918.3056103>`__

.. Nicht mehr vergeben:
   `Zeebe <https://github.com/camunda/zeebe>`__



.. Vorträge - Themen (inkl. Einstiegslinks)
  ------------------------------------------
  - `Pxos <https://en.wikipedia.org/wiki/Paxos_(computer_science)>`_
  - `Raft Consensus Algorithm <https://raft.github.io>`_ 
  - `Gossip Protokoll <https://highscalability.com/gossip-protocol-explained/>`_
  - `gRPC <https://grpc.io>`_
  - `AMQP <https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol>`_
  - `GraphQL <https://graphql.org>`_
  - `Django <https://www.djangoproject.com>`_
  - `HTTP/3 und QUIC bzw. HTTP over QUIC <https://en.wikipedia.org/wiki/HTTP/3>`_