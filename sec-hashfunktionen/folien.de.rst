.. meta:: 
    :author: Michael Eichberg
    :keywords: hash functions
    :description lang=en: Cryptographic Hash Functions
    :description lang=de: Kryptografische Hashfunktionen
    :id: lecture-security-hash_functions
    :first-slide: last-viewed
    :exercises-master-password: WirklichSchwierig!

.. |html-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html
.. |pdf-source| source::
    :prefix: https://delors.github.io/
    :suffix: .html.pdf

.. role:: incremental
.. role:: ger
.. role:: eng
.. role:: ger-quote
.. role:: red
.. role:: green 
.. role:: blue 
.. role:: minor
.. role:: far-far-smaller
.. role:: far-smaller
    
    

Kryptografische Hash Funktionen
===============================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Basierend auf: *Cryptography and Network Security - Principles and Practice, 8th Edition, William Stallings*
:Version: 2.1.1

.. supplemental::

  :Folien: 
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues



.. class:: new-section

Hashfunktionen - Grundlagen
------------------------------------------------


Hashfunktionen
-------------------------------

.. class:: incremental

- Eine Hashfunktion :math:`H` akzeptiert eine beliebig lange Nachricht :math:`M` als Eingabe und gibt einen Wert fixer Größe zurück: :math:`h = H(M)`.
- Wird oft zur Gewährleistung der Datenintegrität verwendet. Eine Änderung eines beliebigen Bits in :math:`M` sollte mit hoher Wahrscheinlichkeit zu einer Änderung des Hashwerts :math:`h` führen.
- Kryptographische Hashfunktionen werden für Sicherheitsanwendungen benötigt. Mögliche Anwendungen:
  
  - Authentifizierung von Nachrichten
  - Digitale Signaturen
  - Speicherung von Passwörtern
  

Beispiel: Berechnung von Hashwerten mittels MD5
-------------------------------------------------------

.. class:: monospaced

:: 

    md5("Hello") = 8b1a9953c4611296a827abf8c47804d7
    md5("hello") = 5d41402abc4b2a76b9719d911017c592
    md5("Dieses Passwort ist wirklich total sicher 
          und falls Du es mir nicht glaubst, dann
          tippe es zweimal hintereinander blind 
          fehlerfrei ein.") 
                 = 8fcf22b1f8327e3a005f0cba48dd44c8

.. admonition:: Warnung
    :class: warning incremental margin-top-2em

    Die Verwendung von MD5 dient hier lediglich der Illustration. In realen Anwendung sollte MD5 nicht mehr verwendet werden.



Sicherheitsanforderungen an kryptografische Hashfunktion I
----------------------------------------------------------

:Variable Eingabegröße: H kann auf einen Block beliebiger Größe angewendet werden.
:Pseudozufälligkeit: Die Ausgabe von :math:`H` erfüllt die Standardtests für Pseudozufälligkeit.

.. class:: incremental 

:Einweg Eigenschaft: 
    
    Es ist rechnerisch/praktisch nicht machbar für einen gegeben Hashwert :math:`h` ein :math:`N` zu finden so dass gilt: :math:`H(N) = h`

    (:eng:`Preimage resistant; one-way property`)


Sicherheitsanforderungen an kryptografische Hashfunktion II
-------------------------------------------------------------------------

:Schwache Kollisionsresistenz: 

    Es ist rechnerisch nicht machbar für eine gegebene Nachricht M eine Nachricht N zu finden so dass gilt: :math:`M \neq N` mit :math:`H(M) = H(N)` 

    (:eng:`Second preimage resistant; weak collision resistant`)

.. class:: incremental

:Starke Kollisionsresistenz: 
    
    Es ist rechnerisch unmöglich ein paar :math:`(N,M)` zu finden so dass gilt: :math:`H(M) = H(N)`. 

    (:eng:`Collision resistant; strong collision resistant`)

.. supplemental::

    **Hintergrund**

    Im Deutschen wird auch von Urbild-Angriffen gesprochen. In dem Fall ist *preimage resistance* (d. h. die Einweg Eigenschaft) gleichbedeutend damit, dass man nicht effektiv einen :ger-quote:`Erstes-Urbild-Angriff` durchführen kann. Hierbei ist das Urbild die ursprüngliche Nachricht :math:`M`, die *gehasht* wurde.

    *Second preimage resistance* ist dann gleichbedeutend damit, dass man nicht effektiv einen :ger-quote:`Zweites-Urbild-Angriff` durchführen kann. Es ist nicht möglich zu einer Nachricht M eine zweite Nachricht N (d. h. ein zweites Urbild) zu finden, die für eine gegebene Hashfunktion den gleich Hash aufweist.


Beziehung zwischen den Sicherheitsanforderungen an Hashfunktionen
------------------------------------------------------------------

.. image:: drawings/hash_functions/properties.svg 
    :alt: Beziehung zwischen den Eigenschaften von Hashfunktionen
    :align: center
    :width: 1200px



Nachrichtenauthentifizierung - vereinfacht
-------------------------------------------------------

.. class:: far-smaller

Nachrichten können auf verschiedene Weisen authentifiziert werden, so dass *Man-in-the-Middle-Angriffe* (MitM)\ [#]_ verhindert werden können.

.. stack::

    .. layer::

        .. image:: drawings/digests/all_encrypted.svg
            :align: center
            :width: 1326

    .. layer:: incremental

        .. image:: drawings/digests/hash_encrypted.svg
            :align: center
            :width: 1560

    .. layer:: incremental

        .. image:: drawings/digests/secret_appended.svg
            :align: center
            :width: 1560

    .. layer:: incremental

        .. image:: drawings/digests/secret_encrypted.svg
            :align: center
            :width: 1774


.. [#] :eng:`Man` ist hier geschlechtsneutral zu verstehen. 

.. supplemental::
    
    **Szenarien**

    Im ersten Szenario wird der Hash an die Nachricht angehängt und als ganzes verschlüsselt. Wir erhalten Vertraulichkeit und Authentizität.

    Im zweiten Szenario wird der Hash der Nachricht berechnet und dann verschlüsselt. Der Empfänger kann den Hash berechnen und mit dem entschlüsselten Hash vergleichen. Wir erhalten Authentizität, aber keine Vertraulichkeit.

    Im dritten Szenario wird an die Nachricht ein geteiltes Secret angehängt und  alles zusammen gehasht. Die Nachricht wird dann mit dem Ergebnis der vorhergehenden Operation zusammen verschickt.

    Im letzten Szenario werden alle Ansätze kombiniert.

    **Legende**

    :M: die Nachricht
    :H: die Hashfunktion
    :E: der Verschlüsselungsalgorithmus
    :D: der Entschlüsselungsalgorithmus
    :K: ein geheimer Schlüssel
    :S: eine geheime Zeichenkette
    :||: die Konkatenation von zwei Werten (d. h. das Aneinanderhängen von zwei Werten)


    .. admonition:: Hinweis

        Bei *Man-in-the-Middle-Angriffen* handelt es sich um einen Fachbegriff und häufig wird zum Beispiel Eve oder Mallory verwendet, um die Person zu bezeichnen, die den Angriff durchführt. Gelegentlich wird auch *Adversary-in-the-Middle* oder *Person-in-the-Middle* verwendet. 

    .. admonition:: Message-Digests
        
        Im allgemeinen Sprachgebrauch wird auch von :eng:`Message Digests` gesprochen.



Digitale Signaturen - vereinfacht
-------------------------------------------------------

.. class:: far-smaller

Digitale Signaturen dienen dem Nachweis der Authentizität einer Nachricht und der Integrität der Nachricht.  Jeder, der einen öffentlichen Schlüssel hat, kann die Signatur überprüfen, aber nur der Besitzer des privaten Schlüssels kann die Signatur erstellen.

.. stack::

    .. layer::

        .. image:: drawings/signatures/just_authentication.svg
            :align: center
            :width: 1582

    .. layer:: incremental

        .. image:: drawings/signatures/authentication_and_encryption.svg
            :align: center
            :width: 1775

.. supplemental::

    **Legende**

    :M: die Nachricht
    :H: die Hashfunktion
    :E: der Verschlüsselungsalgorithmus
    :D: der Entschlüsselungsalgorithmus
    :`PR_a`:math:: der private Schlüssel von a
    :`PU_a`:math:: der öffentliche Schlüssel von a
    :||: die Konkatenation von zwei Werten (d. h. das Aneinanderhängen von zwei Werten)



Anforderungen an die Resistenz von Hashfunktionen
---------------------------------------------------

.. csv-table::
    :header: "", Preimage Resistant, Second Preimage Resistant, Collision Resistant
    :class: smaller highlight-line-on-hover incremental
    :widths: 28, 10, 10, 10
    
    Hash + Digitale Signaturen, ✓, ✓, ✓
    Einbruchserkennung und Viruserkennung, , ✓ , 
    Hash + Symmetrische Verschlüsselung, , , 
    Passwortspeicherung, ✓, , 
    MAC, ✓, ✓, ✓

.. supplemental:: 

    .. rubric:: Einbruchserkennung und Viruserkennung - Hintergrund

    Bei der Einbruchserkennung und Viruserkennung ist *second preimage* Resistenz erforderlich. Andernfalls könnte ein Angreifer seine Malware so schreiben, ass diese einen Hash wie eine vorhandene gutartige Software hat und so verhindern, dass die Malware auf eine schwarze Liste gesetzt werde kann, ohne den Kollateralschaden, dass auch die gutartige Software fälschlicherweise als Malware erkannt wird.
    
    .. rubric:: Aufwand eines Kollisionsangriffs

    Ein Kollisionsangriff erfordert weniger Aufwand als ein *preimage* oder ein *second preimage* Angriff.

    Dies wird durch das Geburtstagsparadoxon erklärt. Wählt man Zufallsvariablen aus einer Gleichverteilung im Bereich von :math:`0` bis :math:`N-1`, so übersteigt die Wahrscheinlichkeit, dass ein sich wiederholendes Element gefunden wird, nach :math:`\sqrt{N}` Auswahlen :math:`0,5`. Wenn wir also für einen m-Bit-Hashwert Datenblöcke zufällig auswählen, können wir erwarten, zwei Datenblöcke innerhalb von :math:`\sqrt{2^m} = 2^{m/2}` Versuchen zu finden.

    .. admonition:: Beispiel
        :class: smaller

        Es ist relativ einfach, ähnliche Meldungen zu erstellen. Wenn ein Text 8 Stellen hat, an denen ein Wort mit einem anderen ausgetauscht werden kann, dann hat man bereits :math:`2^{8}` verschiedene Texte.

        Es ist relativ trivial(1), vergleichbare(2) Nachrichten(3) zu schreiben(4). Wenn ein Text 8 Stellen hat, an denen ein Ausdruck(5) mit einem vergleichbaren (6) ausgetauscht werden kann, dann erhält(7) man bereits :math:`2^{8}` verschiedene Dokumente(8).



Effizienzanforderungen an kryptografische Hashfunktionen
------------------------------------------------------------------------

:Effizienz bei der Verwendung für Signaturen und zur Authentifizierung:

  Bei der Verwendung zur Nachrichtenauthentifizierung und für digitale Signaturen ist :math:`H(N)` für jedes beliebige :math:`N` relativ einfach zu berechnen. Dies soll sowohl Hardware- als auch Softwareimplementierungen ermöglichen.

.. container:: incremental

    .. container:: text-align-center bold huge
        
        vs.

    :Brute-Force-Angriffe auf Passwörter erschweren:

        Bei der Verwendung für das Hashing von Passwörtern soll es schwierig sein den Hash effizient zu berechnen, selbst auf spezialisierter Hardware (GPUs, ASICs).



Struktur eines sicheren Hash-Codes (vorgeschlagen von Merkle)
------------------------------------------------------------------------

.. image:: drawings/hash_functions/structure_of_secure_hash_codes.svg
    :width: 1400px
    :align: center 

.. container:: two-columns smaller 

    .. container:: column no-separator

        :math:`IV` = Initialer Wert (Algorithmus-abhängig)

        :math:`CV_i` = Verkettungsvariable 
        
        :math:`Y_i` = i-er Eingabeblock

        :math:`f` = Kompressionsfunktion
        
    .. container:: column

        :math:`n` = Länge des Blocks

        :math:`L` = Anzahl der Eingabeblöcke
        
        :math:`b` = Länge des Eingabeblocks


.. supplemental::

    Diese Struktur liegt insbesondere den Hashfunktionen der SHA-2 Familie zugrunde.


.. class:: integrated-exercise

Übung
-------

.. exercise:: XOR als Hashfunktion

    
    Warum ist eine einfache :ger-quote:`Hash-Funktion`, die einen 256-Bit-Hash-Wert berechnet, indem sie ein XOR über alle 256-Bit Blöcke einer Nachricht durchführt, im Allgemeinen ungeeignet?

    .. container:: minor

        Wir nehmen hier an, dass die Nachricht ein Vielfaches von 256 Bit lang ist. Falls nicht, dann wenden wir Padding an.

    .. solution:: 
        :pwd: alles nichts

        Je nach Beschaffenheit der zugrunde liegenden Daten können wir die ursprüngliche Nachricht ggf. wiederherstellen bzw. diese liegt direkt vor. Stellen Sie sich z. B. vor, dass nur ein Block sinnvolle Daten enthält und alle anderen Blöcke einfach "0" sind. 
        
        Darüber hinaus wäre es ggf. sehr einfach eine Nachricht zu finden, die denselben Hashwert hat wie die ursprüngliche Nachricht. Somit wäre die Kollisionsresistenz nicht gegeben und eine Integritätssicherung wäre nicht möglich.

        ::

            Nachricht   A:                 B:
                        10101010101010     01010101010101
                        10101111101011     01010000010100 
            "Hash":
                        00000101000001  =  00000101000001
            


.. class:: integrated-exercise

Übung
---------

.. exercise:: Bewertung der Sicherheit

    .. class:: list-with-explanations
 
    - Eine Nachricht :math:`M` bestehe aus :math:`N` 64-bit Blöcken: :math:`X_1, \ldots, X_n`.
    - Der Hashcode H(M) ist ein simpler XOR über alle Blöcke: :math:`H(M) = h = X_1 \oplus X_2 \oplus \ldots \oplus X_n`.
    - :math:`h` wird als der :math:`X_{N+1}` Block an die Nachricht angehängt und danach wird unter Verwendung des CBC Modus die Nachricht inkl. des Hashcodes verschlüsselt (:math:`C = Y_1, \ldots, Y_{N+1}`).
    - Gegen welche Art von Manipulation ist diese Konstruktion *nicht* sicher?
     
      Studieren Sie ggf. noch einmal den CBC Modus. 

    .. solution::
        :pwd: umsortiert

        Die Konstruktion ist nicht sicher gegenüber Vertauschungen der Blöcke!

        Da :math:`X_1 = IV \oplus D(K,Y_1)`, ... ,\ :math:`X_{N+1} = Y_N \oplus D(K,Y_{N+1})` ist und :math:`X_{N+1} = X_1 \oplus X_2 \oplus \ldots \oplus X_n`. Gilt:

        .. math::

            X_1 \oplus X_2 \oplus \ldots \oplus X_n = [IV \oplus D(K,Y_{1})] \oplus \ldots \oplus [ Y_{N-1} \oplus D(K,Y_{N})]

        Somit kann ein Angreifer die Blöcke vertauschen (:math:`\oplus` ist kommutativ), ohne dass dies erkannt werden könnte.



.. class:: integrated-exercise

Übung
-------

.. exercise:: Irrelevanz von Second-Preimage-Resistenz und Kollisionssicherheit

    Warum sind *Second-Preimage-Resistenz* und Kollisionssicherheit von nachgeordneter Relevanz, wenn der Hash-Algorithmus zum Hashing von Passwörtern verwendet wird?

    .. solution::
        :pwd: kein Startpunkt

        Wir haben keinen Block der Nachricht, mit dem wir arbeiten können, und wir haben keinen Vorteil davon, zwei beliebige aber verschiedene Nachrichten zu finden, die denselben Hash haben. Bei der Passwortwiederherstellung liegt uns immer ein Hashwert vor, und wir versuchen, *eine* Nachricht zu finden, die diesen Hashwert erzeugt hat.



.. class:: new-section transition-move-left

*Message Authentication Codes* (MACs)
----------------------------------------------

.. supplemental::

    .. admonition:: Hinweis
    
        *Message Authentication Codes* könnte ins Deutsche mit 
        Nachrichtenauthentifizierungscodes übersetzt werden, dies ist aber nicht üblich.

        Im allgemeinen (IT-)Sprachgebrauch wird von *MAC*\ s gesprochen.



HMAC (Hash-based Message Authentication Code)
----------------------------------------------

.. container:: small

    Auch als *keyed-hash message authentication code* bezeichnet.

    .. math::

        \begin{array}{rcl}
        HMAC(K,m) & = & H( (K' \oplus opad) || H( ( K' \oplus ipad) || m) ) \\
        K' & = &\begin{cases}
                H(K) & \text{falls K größer als die Blockgröße ist}\\
                K & \text{andernfalls}
                \end{cases}
        \end{array}
    
    :math:`H` is eine kryptografische Hashfunktion.

    :math:`m` ist die Nachricht.

    :math:`K` ist der geheime Schlüssel (*Secret Key*).

    :math:`K'` ist vom Schlüssel K abgeleiteter Schlüssel mit Blockgröße (ggf. *padded* oder *gehasht*).

    :math:`||` ist die Konkatenation.

    :math:`\oplus` ist die XOR Operation.

    :math:`opad` ist das äußere Padding bestehend aus Wiederholungen von 0x5c in Blockgröße.

    :math:`ipad` ist das innere Padding bestehend aus Wiederholungen von 0x36 in Blockgröße.


\ 
----------------------------------------------

.. image:: drawings/hmac/hmac_i_o_key_derivation.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: left
        :width: 1400px

.. image:: drawings/hmac/hmac_message_hashing.svg
        :alt: Schlüsselableitung für den inneren und äußeren Schlüssel K'
        :align: right
        :width: 1300px
        :class: incremental margin-top-1em padding-top-1em

.. supplemental::

    **Padding und Hashing**

    Im Rahmen der Speicherung von Passwörtern und *Secret Keys* ist die Verwendung von Padding Operationen bzw. das Hashing von Passwörtern, um Eingaben in einer wohl-definierten Länge zu bekommen, üblich. Neben dem hier gesehenen Padding, bei dem 0x00 Werte angefügt werden, ist zum Beispiel auch das einfache Wiederholen des ursprünglichen Wertes, bis man auf die notwendige Länge kommt, ein Ansatz. 
    
    Diese Art Padding darf jedoch nicht verwechselt werden mit dem Padding, dass ggf. im Rahmen der Verschlüsselung von Nachrichten notwendig ist, um diese ggf. auf eine bestimmte Blockgröße zu bringen (zum Beispiel bei ECB bzw. CBC Block Mode Operations.)



HMAC Berechnung in Python
---------------------------
    
**Implementierung**

.. code:: python
    :class: small

    import hashlib
    pwd = b"MyPassword"
    stretched_pwd = pwd + (64-len(pwd)) * b"\x00" 
    ikeypad = bytes(map(lambda x : x ^ 0x36 , stretched_pwd)) # xor with ipad 
    okeypad = bytes(map(lambda x : x ^ 0x5c , stretched_pwd)) # xor with opad 
    hash1 = hashlib.sha256(ikeypad+b"JustASalt"+b"\x00\x00\x00\x01").digest()
    hmac  = hashlib.sha256(okeypad+hash1).digest()


.. container:: incremental small

    **Ausführung**

    .. code:: python

        hmac =
        b'h\x88\xc2\xb6X\xb7\xcb\x9c\x90\xc2R...
          \x16\x87\x87\x0e\xad\xa1\xe1:9\xca'


.. supplemental::
    
    HMAC ist auch direkt als Bibliotheksfunktion verfügbar.

    .. code:: python
        :class: black

        import hashlib
        import hmac
        
        hash_hmac = hmac.new(
            b"MyPassword",
            b"JustASalt"+b"\x00\x00\x00\x01",
            hashlib.sha256).digest()

        hash_hmac = 
            b'h\x88\xc2\xb6X\xb7\xcb\x9c\x90\xc2R...
              \x16\x87\x87\x0e\xad\xa1\xe1:9\xca'



MAC: `Poly 1305 <https://datatracker.ietf.org/doc/html/rfc8439#section-2.5>`__
--------------------------------------------------------------------------------

.. stack::

    .. layer::

        - Ein MAC Algorithmus für die Einmalauthentifizierung von Nachrichten
        - Entwickelt von Daniel J. Bernstein
        - Basierend auf einem 256-Bit-Schlüssel und einer Nachricht wird ein 128-Bit-Tag berechnet
        - In Verbindung mit *ChaCha20* in einer Reihe von Protokollen verwendet

    .. layer:: incremental

        .. image:: drawings/poly1305.svg
           :alt: Poly 1305 - Verwendung des Schlüssels
           :align: center
           :width: 1775px

        .. container:: align-center

            .. rubric:: Aufteilung des Schlüssels 

    .. layer:: incremental

        .. rubric:: Verarbeitung der Nachricht

        .. class:: incremental list-with-explanations

        - initialisiere den Akkumulator :math:`a` mit 0
        - die Nachricht wird in Blöcke von 16 Byte aufgeteilt und als *little-endian* Zahl verarbeitet; d. h. ein Block hat 16 Oktette (:math:`16 \times 8` Bit)
        - Füge dem Block :math:`n` ein Bit jenseits der Anzahl der Oktette des aktuellen Blocks hinzu :math:`= n'`; d. h. im Falle eines 16-Byte-Blocks wird die Zahl :math:`2^{128}` addiert 
        
          (Danach haben wir ggf. eine 17-Byte-Zahl.)
        - Addiere :math:`n'` aus dem letzten Schritt zum Akkumulator :math:`a` und multipliziere mit :math:`\text{clamped r}`
        - Aktualisiere den Akkumulator mit dem Ergebnis :math:`modulo\, P` mit :math:`P = 2^{130} - 5`:
          :math:`a = ((a + n') \times \text{clamped r}) \mod\, P`


    .. layer:: incremental

        :far-smaller:`Beispiel`

        .. code:: text
            :class: far-far-smaller

            000  43 72 79 70 74 6f 67 72 61 70 68 69 63 20 46 6f  Cryptographic Fo
            016  72 75 6d 20 52 65 73 65 61 72 63 68 20 47 72 6f  rum Research Gro
            032  75 70                                            up

        .. container:: far-far-smaller margin-bottom-1em margin-top-1em

            Sei :math:`\text{clamped r} = 806d5400e52447c036d555408bed685`

            Sei :math:`s = 1bf54941aff6bf4afdb20dfb8a800301`

        .. stack:: smaller incremental

            .. layer:: 
        
                .. rubric:: Verarbeitung des ersten Blocks

                .. math::
                    :class: far-far-smaller

                    \begin{array}{rl}
                        a      & = & 00 \\
                        n      & = &   6f4620636968706172676f7470797243 \\
                        n'    & = & 016f4620636968706172676f7470797243 \\
                        a + n' & = & 016f4620636968706172676f7470797243 \\
                        (a + n') \times \text{clamped r} & = &
                                b83fe991ca66800489155dcd69e8426ba2779453994ac90ed284034da565ecf \\
                        a = ((a + n') \times \text{clamped r})\, mod\, P & = &
                                2c88c77849d64ae9147ddeb88e69c83fc \\
                    \end{array}

            .. layer:: incremental
        
                .. rubric:: Verarbeitung des letzten Blocks

                .. math::
                    :class: far-far-smaller

                    \begin{array}{rl}
                        a      & = & 2d8adaf23b0337fa7cccfb4ea344b30de \\
                        n      & = &   7075 \\
                        n'    & = & 017075 \\
                        a + n' & = & 2d8adaf23b0337fa7cccfb4ea344ca153 \\
                        (a + n') \times \text{clamped r} & = &
                                16d8e08a0f3fe1de4fe4a15486aca7a270a29f1e6c849221e4a6798b8e45321f \\
                        a = ((a + n') \times \text{clamped r})\, mod\, P & = &
                                28d31b7caff946c77c8844335369d03a7 \\
                    \end{array}

            .. layer:: incremental far-smaller

                .. rubric:: Abschuss

                Addiere auf den Wert des Akkumulators :math:`a` den Wert :math:`s`.
                
                Und somit ist der Tag :math:`2a927010caf8b2bc2c6365130c11d06a8` (als Zahl im little-endian Format)


.. supplemental::

    .. rubric:: Hinweise

    In dieser Diskussion betrachten wir jeden Block der Nachricht als :ger-quote:`große Zahl`.

    :math:`P = 2^{130} - 5 = 3fffffffffffffffffffffffffffffffb`

    Dadurch, dass wir den Block als Zahl in *little-endian* Reihenfolge interpretieren, ist das hinzufügen des Bits jenseits der Anzahl der Oktette gleichbedeutend damit, dass wir den Wert 0x01 am Ende des Blocks hinzufügen.


Zusammenfassung
-------------------

.. class:: incremental list-with-explanations

- Ein Hashwert dient der Integritätssicherung von Nachrichten.
- Ein Mac dient der Authentifizierung von Nachrichten. 
- Ein Mac sichert auch immer die Integrität der Nachricht.
  
  Es ist somit möglich die Integrität einer Nachricht zu sichern ohne Authentizität zu gewährleisten, aber nicht umgekehrt.
- Ein Mac erlaubt es dem Empfänger eine gefälschte Nachricht zu erkennen aber ggf. auch zu erstellen (:eng:`to forge a message`).
- Eine Signatur basiert auf einem Hashwert und einem privaten Schlüssel. 
- Der Empfänger kann bei einer signierten Nachricht, diese nicht verändern und als eine Nachricht des Senders ausgeben. 
- Nur für Nachrichten, die signiert sind, gilt somit die Nichtabstreitbarkeit (:eng:`non-repudation`).


.. TODO discuss CBC-MAC
