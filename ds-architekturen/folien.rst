.. meta:: 
    :author: Michael Eichberg
    :keywords: "Verteilte Anwendungen", "Modelle", "Architekturen", "Architekturelle Stile"
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


.. class:: new-section transition-fade

Middleware
-----------------


.. class:: center-child-elements

Was ist Middleware?
-----------------------

.. admonition:: Definition

   Middleware ist eine Klasse von Software-Technologien, die dazu dienen, 
   
   (I) die Komplexität und 
   
   (II) die Heterogenität verteilter Systeme zu verwalten.


.. class:: tiny

Ein einfacher Server mit Sockets
--------------------------------

.. container:: stack smaller

  .. container:: layer

    .. code:: C

      /* A simple TCP based server. The port number is passed as an argument */
      #include <stdio.h>
      #include <sys/types.h> 
      #include <sys/socket.h>
      #include <netinet/in.h>

      void error(char *msg){perror(msg); exit(1);}

      int main(int argc, char *argv[]){
        int sockfd, newsockfd, portno, clilen;
        char buffer[256]; int n;
        struct sockaddr_in serv_addr, cli_addr;

        sockfd = socket(AF_INET, SOCK_STREAM, 0); // socket() returns a socket descriptor
        if (sockfd < 0) 
        error("ERROR opening socket");

        bzero((char *) &serv_addr, sizeof(serv_addr)); // bzero() sets all values to zero. 
        portno = atoi(argv[1]); // atoi() converts str into an integer

        ...

  .. container:: layer incremental

    .. code:: C

        serv_addr.sin_family = AF_INET;
        serv_addr.sin_addr.s_addr = INADDR_ANY;
        serv_addr.sin_port = htons(portno);

        if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) 
        error("ERROR on binding");
        listen(sockfd,5); // tells the socket to listen for connections
        clilen = sizeof(cli_addr);
        newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
        if (newsockfd < 0) error("ERROR on accept");

        bzero(buffer,256);
        n = read(newsockfd,buffer,255);
        if (n < 0) error("ERROR reading from socket");
        printf("Here is the message: %s\n",buffer);
        n = write(newsockfd,"I got your message",18);

        if (n < 0) error("ERROR writing to socket");

        return 0; 
      }

.. container:: block-footer text-align-center dhbw-gray-background white

   Motivation


.. class:: tiny

Ein einfacher Client mit Sockets
--------------------------------

.. stack:: smaller

    .. layer::
    
      .. code:: c

        #include <stdio.h>
        #include <sys/types.h>
        #include <sys/socket.h>
        #include <netinet/in.h>
        #include <netdb.h> 

        void error(char *msg){ perror(msg);exit(0);}

        int main(int argc, char *argv[]){
          int sockfd, portno, n;
          struct sockaddr_in serv_addr;
          struct hostent *server;
          char buffer[256];

          portno = atoi(argv[2]);

          sockfd = socket(AF_INET, SOCK_STREAM, 0);
          if (sockfd < 0) 
            error("ERROR opening socket");

          ...

    .. layer:: incremental

      .. code:: c

          ...

          server = gethostbyname(argv[1]);
          bzero((char *) &serv_addr, sizeof(serv_addr));
          serv_addr.sin_family = AF_INET;
          bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
          serv_addr.sin_port = htons(portno);

          if (connect(sockfd,&serv_addr,sizeof(serv_addr)) < 0) error("ERROR connecting");

          printf("Please enter the message: "); 
          bzero(buffer,256);
          fgets(buffer,255,stdin);
          n = write(sockfd,buffer,strlen(buffer));
          if (n < 0) error("ERROR writing to socket");
          bzero(buffer,256);
          n = read(sockfd,buffer,255);
          printf("%s\n",buffer);

          return 0;
        }


.. container:: block-footer text-align-center dhbw-gray-background white

   Motivation



Probleme bei der Verwendung von Sockets
------------------------------------------

Wir müssen uns um …

.. class:: incremental negative-list

  - … die Einrichtung eines Kanals und alle Fehler, die während dieses Prozesses auftreten können

  - … die Festlegung eines Protokolls (Wer sendet was, wann, in welcher Reihenfolge und welche Antwort wird erwartet?)

  - … Nachrichtenformate (Umwandlung von Daten der Anwendungsebene in Bytes, die über das Netz übertragen werden können).

kümmern!

.. container:: block-footer text-align-center dhbw-gray-background white

   Motivation



Middleware als Programmierabstraktion
------------------------------------------

.. container:: two-columns 

  .. container:: column no-separator

    - Eine Softwareschicht oberhalb des Betriebssystems und unterhalb des Anwendungsprogramms, die eine gemeinsame Programmierabstraktion in einem verteilten System bietet.

    - Ein Baustein auf höherer Ebene als die vom Betriebssystem bereitgestellten APIs (z. B. Sockets)

  .. container:: column

    .. image:: images/middleware.svg
       :height: 1100px
       :align: center



Middleware als Programmierabstraktion
------------------------------------------

Die von Middleware angebotenen Programmierabstraktionen verbergen einen Teil der Heterogenität und bewältigen einen Teil der Komplexität, mit der Programmierer einer verteilten Anwendung umgehen müssen:

.. class:: incremental positive-list

- Middleware maskiert immer die Heterogenität der zugrundeliegenden Netzwerke und Hardware.
- Middleware maskiert meistens die Heterogenität von Betriebssystemen und/oder Programmiersprachen.
- :minor:`Manche Middleware maskiert sogar die Heterogenität zwischen den Implementierungen des gleichen Middleware-Standards durch verschiedene Hersteller`.


.. supplemental::

  Alte Middlewarestandards wie zum Beispiel CORBA waren sehr komplex und die Implementierungen verschiedener Hersteller meist nicht vollständig kompatibel. 



Transparenzziele von Middleware aus Sicht der Programmierung
--------------------------------------------------------------

Middleware bietet (beim Programmieren) Transparenz in Bezug auf eine oder mehrere der folgenden Dimensionen:

- Standort
- Nebenläufigkeit
- Replikation
- Ausfälle (bedingt)

.. container:: assessment

  Middleware ist die Software, die ein verteiltes System (DS) programmierbar macht.



Middleware als Infrastruktur
---------------------------------

.. class:: incremental 

- Hinter Programmierabstraktionen steht eine komplexe Infrastruktur, die diese Abstraktionen implementiert (Middleware-Plattformen sind sehr komplexe Softwaresysteme).
- Da die Programmierabstraktionen immer höhere Ebenen erreichen, muss die zugrunde liegende Infrastruktur, die die Abstraktionen implementiert, entsprechend wachsen
- Zusätzliche Funktionalität wird fast immer durch zusätzliche Softwareschichten implementiert.
- Die zusätzlichen Softwareschichten erhöhen den Umfang und die Komplexität der für die Nutzung der neuen Abstraktionen erforderlichen Infrastruktur.

.. supplemental::

  Seit Jahrzehnten kann beobachtet werden, dass Middleware immer komplexer wird bzw. wurde bis zu dem Punkt an dem die Komplexität kaum mehr beherrschbar war. Zu diesen Zeitpunkten wurden dann häufig neue Ansätze entwickelt, die die Komplexität reduzierten bis diese wiederum Eingang in neue Middleware fand. Ansätze, wie z. B. REST, haben sich als recht erfolgreich erwiesen stellen aber Entwickler vor neue Herausforderungen.



Middleware und nicht-funktionale Anforderungen
------------------------------------------------

Die Infrastruktur kümmert sich um nicht-funktionalen Eigenschaften, die normalerweise von Datenmodellen, Programmiermodellen und Programmiersprachen ignoriert werden: 

- Leistung
- Verfügbarkeit
- Ressourcenmanagement
- Zuverlässigkeit
- usw.



Middleware als Infrastruktur
---------------------------------

Middleware unterstützt zusätzliche Funktionen die die Entwicklung, Wartung und Überwachung einfacher und kostengünstiger machen (Auszug):

.. class:: incremental

- Protokollierung (:eng:`Logging`) 
- Wiederherstellung (:eng:`Recovery`)
- Sprachprimitive für transaktionale Abgrenzung, 
- :minor:`fortgeschrittene Transaktionsmodelle (z.B. transaktionale RPC), transaktionales Dateisystem`



.. class:: smaller-slide-title

Konzeptionelle Darstellung (historischer) Middleware
-----------------------------------------------------

.. container:: footer-left x-tiny minor

    Darstellung nach: Alonso; Web services: Concepts, Architectures and Applications; Springer, 2004

.. image:: images/historische-middleware-konzeptuell.svg
  :height: 900px
  :align: center

.. supplemental::

  Insbesondere die explizite Erzeugung von Stubs und Skeletons durch einen IDL Compiler erfolgt so in der heutigen Zeit nicht mehr. Die Erzeugung von Stubs und Skeletons - wenn überhaupt erforderlich - erfolgt heute automatisch durch die Middleware.

.. class:: vertical-title

Historische Entwicklung von Middleware
------------------------------------------

.. image:: images/historic_middleware_technologies.svg
   :height: 1150px
   :align: center



Entwicklung von Middleware
---------------------------

- Beabsichtigt, Details der Hardware, der Netze und der Verteilung auf niedriger Ebene zu verbergen.
- Anhaltender Trend zu immer leistungsfähigeren Primitiven (*Events*), die zusätzliche Eigenschaften haben oder eine flexiblere Nutzung des Konzepts ermöglichen.
- Die Entwicklung und das Erscheinungsbild für den Programmierer wird von den Trends in den Programmiersprachen diktiert:
  
  - RPC und C
  - CORBA und C++
  - RMI (Corba) und Java
  - "Klassische" Webservices und XML
  - RESTful Webservices und JSON


.. class:: no-title center-child-elements

Middleware - High-level View
------------------------------

.. container:: huge dhbw-red padding-1em dhbw-light-gray-background rounded-corners

  Eine Middleware stellt eine umfassende Plattform für die Entwicklung und den Betrieb komplexer verteilter Systeme zur Verfügung.



Klassische Architekturen
-------------------------

.. image:: images/common_application_architectures/common_architectures.svg
   :height: 800px
   :align: center



.. class:: new-section transition-fade

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

.. supplemental::

  Ein große Herausforderung ist das Design der Schnittstellen. Um wirkliche Unabhängigkeit zu erreichen, müssen die Schnittstellen sehr gut definiert sein. Sind die Schnittstellen nicht klar definiert oder unzureichend, dann kann das zu viel Arbeit und Koordination zwischen den Teams führen, die eigentlich unerwünscht ist!



Schlüsselkonzepte von Microservices
-------------------------------------

.. class:: incremental list-with-explanations

- können unabhängig bereitgestellt werden (:eng:`independently deployable`) 
  
  und werden unabhängig entwickelt
- modellieren eine Geschäftsdomäne
  
  Häufig entlang einer Kontextgrenze (eng. Bounded Context) oder eines Aggregats aus DDD
- verwalten Ihren eigenen Zustand
  
  d. h. keine geteilten Datenbanken
- sind klein
  
  Klein genug, um durch (max.) ein Team entwickelt werden zu können

- flexibel bzgl. Skalierbarkeit, Robustheit, eingesetzter Technik
- erlauben das Ausrichten der Architektur an der Organisation (vgl. Conway's Law)


.. class:: smaller

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



.. class:: vertical-title

Aktuelle Standardtechnologien
-------------------------------------

.. stack::

  .. layer:: tiny
  
    .. image:: screenshots/tiobe_2012-04.png
       :height: 1050px
       :align: center

    Quelle: TIOBE Programming Community Index - April 2012

  .. layer:: incremental tiny

    .. image:: screenshots/tiobe_2024-02.png
       :height: 1050px
       :align: center

    Quelle: `TIOBE Programming Community Index - Feb. 2024 <https://www.tiobe.com/tiobe-index/>`__


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

.. supplemental::

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

.. supplemental::

  Eine *Saga* ist eine Sequenz von Aktionen, die ausgeführt werden, um eine langlebige Transaktion zu implementieren. 
  
  Sagas können keine Atomizität garantieren!. Jedes System für sich kann jedoch ggf. Atomizität garantieren (z. B. durch die Verwendung traditioneller Datenbanktransaktionen).

  Sollte ein Abbruch der Transaktion notwendig sein, dann kann kein traditioneller *Rollback* erfolgen. Die Saga muss dann entsprechende kompensierende Transaktionen durchführen, die alle bisher erfolgreich durchgeführten Aktionen rückgängig machen.



.. class:: smaller-slide-title vertical-title

Optimierung der Abarbeitungsreihenfolge zwecks Minimierung von mgl. *Rollbacks*
--------------------------------------------------------------------------------

.. image:: images/sagas/transaktion-mit-saga-mit-weniger-rollbacks.svg
   :height: 1160px
   :align: center

.. supplemental::

  Die Abarbeitungsreihenfolge der Aktionen kann so optimiert werden, dass die Wahrscheinlichkeit von *Rollbacks* minimiert wird. In diesem Falle ist die Wahrscheinlichkeit, dass es zu einem *Rollback* während des Schritts :ger-quote:`Versand der Bestellung` kommt, wesentlich höher als beim Schritt :ger-quote:`Kundenbonus gutschreiben`.



Langlebige Transaktionen mit orchestrierten Sagas
--------------------------------------------------------

.. image:: images/sagas/orchestrierte-saga.svg
   :height: 760px
   :align: center

.. supplemental::

  Die orchestrierte Saga ist eine Möglichkeit, um langlebige Transaktionen zu implementieren. 

  .. class:: positive-list
  
  - Mental einfach

  .. class:: negative-list list-with-explanations 

  - Hoher Grad an *Domain Coupling* 
  
    Da es sich im Wesentlichen um fachlich getriebene Kopplung handelt, ist diese Kopplung häufig akzeptabel. Die Kopplung erzeugt keine technischen Schulden (:eng:`technical debt`).
  - Hoher Grad an *Request-Response* Interaktionen
  - Gefahr, dass Funktionalität, die besser in den einzelnen Services (oder ggf. neuen Services) unterzubringen wäre, in den Bestellung Service wandert.



Langlebige Transaktionen mit choreografierten Sagas
----------------------------------------------------------

.. image:: images/sagas/choreographierte-saga.svg
   :height: 975px
   :align: center

.. supplemental::

  Ein großes Problem bei choreografierten Sagas ist es den Überblick über den aktuellen Stand zu behalten. Durch die Verwendung einer "Korrelations-ID" kann diese Problem gemindert werden.



.. class:: no-title center-child-elements

Die Wahl der richtigen Architektur ist ein Tradeoff!
-----------------------------------------------------

.. container:: assessment bold huge dhbw-red text-align-center

  Die Wahl der Softwarearchitektur ist immer eine Abwägung von vielen Tradeoffs!

.. supplemental::

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
