.. meta:: 
    :author: Michael Eichberg
    :keywords: "HTTP", "Sockets"
    :description lang=de: HTTP und Socketprogrammierung
    :description lang=en: HTTP amd Sockets
    :id: lecture-ds-http-and-sockets
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


HTTP und Sockets (in Java)
===============================================================================

.. container:: line-above padding-bottom-1em

  :Dozent: **Prof. Dr. Michael Eichberg**
  :Kontakt: michael.eichberg@dhbw-mannheim.de
  :Version: |date|

  :Folien: 
      https://delors.github.io/ds-http-and-sockets/folien.rst.html :raw-html:`<br>`
      https://delors.github.io/ds-http-and-sockets/folien.rst.html.pdf
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io bzw. https://github.com/Delors/delors.github.io/issues

.. container:: footer-left tiny 

    Dieser Foliensatz basiert auf Folien von Prof. Dr. Henning Pagnia.
    
    Alle Fehler sind meine eigenen.



.. class:: smaller

IP - Wiederholung
--------------------------------------

Die Vermittlungsschicht (Internet Layer)

- übernimmt das Routing
- realisiert Ende-zu-Ende-Kommunikation
- überträgt Pakete
- ist im Internet durch IP realisiert
- löst folgende Probleme:

  - Sender und Empfänger erhalten netzweit eindeutige Bezeichner (⇒ IP-Adressen)
  - die Pakete werden durch spezielle Geräte (⇒ Router) weitergeleitet



.. class:: smaller

TCP und UDP - Wiederholung 
--------------------------------------

.. container:: two-columns

  .. container:: column

    .. rubric:: Transmission Control Protocol (TCP), RFC 793

    • verbindungsorientierte Kommunikation
    • ebenfalls Konzept der Ports
    • Verbindungsaufbau zwischen zwei Prozessen (Dreifacher Handshake, Full-Duplex-Kommunikation)

      - geordnete Kommunikation
      - zuverlässige Kommunikation
      - Flusskontrolle
      - hoher Overhead ⇒ eher langsam
      - nur Unicasts

  .. container:: column

    .. rubric:: User Datagram Protocol (UDP), RFC 768
    
    • verbindungslose Kommunikation via Datagramme

      - unzuverlässig (⇒ keine Fehlerkontrolle)
      - ungeordnet (⇒ beliebige Reihenfolge)
      - wenig Overhead (⇒ schnell)
    • Größe der Nutzdaten ist 65507 Byte, in der Praxis jedoch meist deutlich kleiner
    • Anwendungsfelder:

      .. class:: smaller

      - Anwendungen mit vorwiegend kurzen Nachrichten (z.B. NTP, RPC, NIS)
      - Anwendungen mit hohem Durchsatz, die ab und zu Fehler tolerieren (z.B. Multimedia)
      - Multicasts sowie Broadcasts



.. class:: new-section transition-scale

Hypertext Transfer Protocol (HTTP)
--------------------------------------



HTTP
--------------------------------------

• `RFC 7230 <http://www.ietf.org/rfc/rfc7230.txt>`__ – 7235: HTTP/1.1 (redigiert im Jahr 2014; urspr. 1999 RFC 2626) 
• RFC 7540: HTTP/2 (seit Mai 2015 standardisiert)
• Eigenschaften:
  
  - Client / Server (Browser / Web-Server)
  - basierend auf TCP, i. d. R. Port 80
  - Server (meist) zustandslos
  - seit HTTP/1.1 auch persistente Verbindungen und Pipelining
  - abgesicherte Übertragung (Verschlüsselung) möglich mittels Secure Socket Layer (SSL) bzw. Transport Layer Security (TLS)



Konzeptioneller Ablauf
--------------------------------------


.. container:: two-columns

  .. container:: center-child-elements

    .. image:: images/http/http.svg
      :width: 1100px

  .. container:: small

    .. rubric:: HTTP-Kommandos 
    
    (:ger-quote:`Verben`)

    - HEAD
    - GET
    - POST
    - PUT
    - PATCH
    - DELETE
    - OPTIONS
    - TRACE
    - CONNECT
    - ...


.. class:: small

Protokolldefinition
--------------------------------------

Aufbau der Dokumentenbezeichner *Uniform Resource Locator (URL)*

.. container:: text-align-center rounded-corners padding-1em dhbw-light-gray-background

  ``http://host[:port][abs_path[?query][#anchor]]``

:``host``: DNS-Name (oder IP-Adresse) des Servers (case-insensitive)
:``port``: (optional) falls leer, 80 bei http
:``abs_path``: (optional) Pfadausdruck relativ zum Server-Root (case-sensitive!)
:``?query``: (optional) direkte Parameterübergabe
:``#anchor``: (optional) Sprungmarke innerhalb des Dokuments

.. container:: incremental small

  Uniform Resource Identifier (URI) sind eine Verallgemeinerung von URLs.

  - definiert in RFC 1630 (im Jahr 1994)
  - entweder URL (Location) oder URN (Name) (z. B. ``urn:isbn:1234567890``)
  - Beispiele von URIs, die keine URL sind, sind *XML Namespace Iidentifiers*

    .. code:: XML 
      :class: tiny

      <svg version="1.1" xmlns="http://www.w3.org/2000/svg">...</svg>


.. class:: scriptsize

Das GET Kommando
--------------------------------------

.. stack::

  .. layer::

    - Dient dem Anfordern von HTML-Daten vom Server (Request-Methode).
    - kurze Anfrage (wird von vielen Servern heute ignoriert!)

      :Anfrage: ``GET url``
      :Ergebnis: Server sendet nur HTML-Datei

    - normale minimale Anfrage:
    
      :Anfrage:

        ::

          GET url HTTP/1.1
          Host: hostname
          Connection: close
          Leerzeile (CRLF)

      :Optionen:     
          - Client kann zusätzlich weitere Infos über die Anfrage sowie sich selbst senden
          - Server sendet Status der Anfrage sowie Infos über sich selbst und ggf. die angeforderte HTML-Datei

    - Fehlermeldungen werden ggf. vom Server ebenfalls als HTML-Daten verpackt und als Antwort gesendet

  .. layer:: incremental

    .. rubric:: Beispiel Anfrage des Clients

    .. code:: http

      GET /index.html HTTP/1.1
      Host: cruncher.dhbw−mannheim.de
      ∗∗CRLF∗∗

    .. rubric:: Beispiel Antwort des Servers

    .. code:: http

      HTTP/1.1 200 OK
      Date: Wed, 07 May 2008 07:40:55 GMT
      Server: Apache/2.0.53 (Linux/SUSE)
      Last-Modified: Mon, 12 Jun 2006 09:16:58 GMT
      5 Accept-Ranges : bytes
      Content-Length : 3737
      Content-Type: text/html
      ∗∗CRLF∗∗
      <!DOCTYPE HTML . . .
      10 </html>∗∗CRLF∗∗



.. class:: new-section transition-scale

Sockets
--------------------------------------



Sockets in Java
--------------------------------------

**Sockets sind Kommunikationsendpunkte.**

- Sockets werden adressiert über die IP-Adresse (InetAddress-Objekt) und eine interne Port-Nummer (int-Wert)
- Sockets gibt es bei TCP und auch bei UDP, allerdings mit unterschiedlichen Eigenschaften

  :TCP: verbindungsorientierte Kommunikation über *Streams*
  :UDP: verbindungslose Kommunikation mittels *Datagrams*
- Das Empfangen von Daten ist in jedem Fall blockierend, d. h. der empfangende Thread bzw. Prozess wartet, falls keine Daten vorliegen.


TCP Sockets
--------------------------------------

.. image:: images/http/tcp_sockets.svg
    :height: 950px
    :align: center


.. supplemental::

  (1) Der Server-Prozess wartet an dem bekannten Server-Port.
  (2) Der Client-Prozess erzeugt einen privaten Socket.
  (3) Der Socket baut zum Server-Prozess eine Verbindung auf – falls der Server die Verbindung akzeptiert.
  (4) Die Kommunikation erfolgt Strom-orientiert: Für beide Parteien wird je ein Eingabestrom und ein Ausgabestrom eingerichtet, über den nun Daten ausgetauscht werden können.
  (5) Wenn alle Daten ausgetauscht wurden, schließen im Allg. beide Parteien die Verbindung.


.. class:: smaller-slide-title

(Ein einfacher) Portscanner in Java
--------------------------------------

.. code:: java
  :class: tiny

  import java.net.*;
  import java.io.*;
  
  public class LowPortScanner {
    public static void main(String [] args) {
      String host = "localhost";
      if (args.length > 0) { host = args [0]; }
      for (int i = 1; i < 1024; i++) {
        try {
          Socket s = new Socket(host, i);
          System.out.println ("There is a server on port "+ i + "at "+host);
          s.close();
        } catch (UnknownHostException e) {
          System.err.println(e);
          break ;
        }
        catch (IOException e) {/* probably no server waiting at this port */ }
  } } }



Austausch von Daten
--------------------------------------

.. class:: incremental

- Nach erfolgtem Verbindungsaufbau können zwischen Client und Server mittels des Socket-InputStream und Socket-OutputStream Daten ausgetauscht werden.
- Hierzu leitet man die rohen Daten am besten durch geeignete Filter-Streams, um eine möglichst hohe semantische Ebene zu erreichen.

  - Beispiele: ``PrintWriter``, ``BufferedReader``, ``BufferedInputStream``, ``BufferedOutputStream``
  - Die Netzwerkkommunikation kann dann ggf. bequem über wohlbekannte und komfortable Ein- und Ausgabe-Routinen (z. B. ``readLine`` oder ``println``) durchgeführt werden.
  - Filter-Streams werden auch für den Zugriff auf andere Geräte und Dateien verwendet.


.. supplemental::

  Durch die Verwendung des *Decorater-Patterns* können die Filter-Streams beliebig geschachtelt werden und vielfältig verwendet werden. Dies macht die Anwendungsprogrammierung  einfacher und erlaubt zum Beispiel das einfache Umwandeln von Zeichenketten, Datenkomprimierung, Verschlüsselung, usw.



.. class:: smaller-slide-title
  
(Schachtelung von Streams) Ein einfacher Echo-Dienst 
------------------------------------------------------

.. stack:: tiny

  .. layer::
        
    .. code:: Java

      import java.net.*;
      import java.io.*;

      public class EchoClient {
        public static void main(String [] args) throws IOException {
          BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
          while (true) {
            String theLine = userIn.readLine();
            if (theLine.equals(".")) break;
            try (Socket s = new Socket("localhost"/*hostname*/, 7/*serverPort*/)) {
              BufferedReader networkIn = 
                  new BufferedReader(new InputStreamReader(s.getInputStream()));
              PrintWriter networkOut = new PrintWriter(s.getOutputStream());
              networkOut.println(theLine);
              networkOut.flush();
              System.out.println(networkIn.readLine());
            }
      } } }

  .. layer:: incremental

    .. code:: Java

      import java.net.*;
      import java.io.*;

      public class EchoServer {
        public static void main(String [] args) {
          BufferedReader in = null ;
          try {
            ServerSocket server = new ServerSocket(7 /*DEFAULT PORT*/);
            while (true) {
              try (Socket con = server.accept()) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.println(in.readLine()) ;
                out.flush() ;
              } catch (IOException e) { System.err.println(e); }
            } 
          } catch (IOException e) { System.err.println(e); }
      } }



UPD Sockets
--------------------------------------

.. container:: two-columns

  .. container:: column

    .. rubric:: Clientseitig

    1. ``DatagramSocket`` erzeugen
    2. ``DatagramPacket`` erzeugen 
    3. ``DatagramPacket`` absenden
    4. ggf. Antwort empfangen und verarbeiten


  .. container:: column

    .. rubric:: Serverseitig

    1. ``DatagramSocket`` auf festem Port erzeugen
    2. Endlosschleife beginnen
    3. ``DatagramPacket`` vorbereiten
    4. ``DatagramPacket`` empfangen
    5. ``DatagramPacket`` verarbeiten
    6. ggf. Antwort erstellen und absenden


.. class:: smaller-slide-title
  
UDP basierter Echo Server
------------------------------------------------------

.. stack:: tiny

  .. code:: Java

    import java.net.*;
    import java.io.*;

    public class UDPEchoServer {
      public final static int DEFAULT PORT = 7; // privileged port !
      public static void main(String [] args) {
        try (DatagramSocket server = new DatagramSocket(DEFAULT PORT)) {
          while(true) {
            try {
              byte[] buffer = new byte[65507]; // room for incoming message
              DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
              server.receive(dp) ;
              String data = new String(dp.getData(),0,dp.getLength());
              DatagramPacket dp2 = 
                new DatagramPacket(data.getBytes(),
                  data.getBytes().length, dp.getAddress(), dp.getPort());
              server.send(dp2) ;
            } catch (IOException e) {System.err.println(e);}
        } }
    } }



.. class:: integrated-exercise transition-fade

Übung - Ein einfacher HTTP-Client
------------------------------------------------------

.. class:: list-with-explanations

(a) Schreiben Sie einen HTTP-Client der den Server ``archive.org`` kontaktiert, die Datei ``/web/web.php`` anfordert und die Antwort des Servers auf dem Bildschirm ausgibt.

    Verwenden Sie HTTP/1.1 und eine Struktur ähnlich dem in der Vorlesung vorgestellten Echo-Client.

    Senden Sie das GET-Kommando, die Host-Zeile sowie eine Leerzeile als Strings an den Server.
(b) Modifizieren Sie Ihren Client, so dass eine URL als Kommandozeilenparameter akzeptiert wird.

    Verwenden Sie die (existierende) Klasse URL, um die angegebene URL zu zerlegen.
(c) Modifizieren Sie Ihr Programm, so dass die Antwort des Servers als lokale Datei abgespeichert wird. Laden Sie die Datei zum Anzeigen in einen Browser.

    Nutzen Sie die Klasse ``FileOutputStream`` oder ``FileWriter`` zum Speichern der Datei.

    Kann Ihr Programm auch Bilddateien (z. B. "/images/logo_wayback_210x77.png") korrekt speichern?


.. class:: integrated-exercise

Übung - Protokollaggregation
------------------------------------------------------

Schreiben Sie ein UDP-basiertes Java-Programm mit dem sich Protokoll-Meldungen auf einem Server
zentral anzeigen lassen. Das Programm soll aus mehreren Clients und einem Server bestehen. Jeder
Client liest von der Tastatur eine Eingabezeile in Form eines Strings ein, der dann sofort zum Server gesendet wird. Der Server wartet auf Port 4999 und empfängt die Meldungen beliebiger Clients, die er dann unmittelbar auf den Bildschirm ausgibt.