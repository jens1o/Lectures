.. meta:: 
    :author: Michael Eichberg
    :keywords: "TCP"
    :description lang=de: Verteilte Systeme
    :id: lecture-tcp
    :first-slide: last-viewed

.. |date| date::
.. |at| unicode:: 0x40

.. role:: incremental   
.. role:: eng
.. role:: ger
.. role:: red
.. role:: green
.. role:: blue
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above
.. role:: huge
.. role:: xxl

.. role:: raw-html(raw)
   :format: html


CVSS, CVE und OWASP
=====================================================

:Dozent: **Prof. Dr. Michael Eichberg**
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|


.. class:: new-section transition-fade

CVSS 
----------------


.. class:: center-elements-on-slide

\
---------

.. container:: foundations

    Das `Common Vulnerability Scoring System (CVSS 4.0) <https://www.first.org/cvss/v4.0/specification-document>`__ stellt einen Rahmen bereit für die Beschreibung und Bewertung des Schweregrads von Software-/Hardware-/Firmwareschwachstellen.

    Die Bewertung der Basiskennzahlen ergibt eine Punktzahl zwischen 0,0 und 10,0. Wobei 0 bedeuted, dass die Schwachstelle (bisher) harmlos ist und 10,0 bedeutet, dass die Schwachstelle sehr gefährlich ist.


CVSS umfasst vier Gruppen von Metriken
----------------------------------------

.. class:: incremental li-margin-top-0-75em

1) Basis-Metriken (:eng:`Base Metrics`) erfassen die inhärenten Eigenschaften einer Schwachstelle, die sich nicht ändern, wenn sich die Umgebung ändert.
2) Bedrohungs-Metriken (:eng:`Threat Metric Group`) spiegelt die Merkmale einer Schwachstelle wieder, die sich im Laufe der Zeit verändern.
3) Umgebungs-Metriken (:eng:`Environmental Metric Group`) erfassen die Eigenschaften einer Schwachstelle, die sich ändern, wenn sich die Umgebung ändert.
4) Ergänzende-Metriken (:eng:`Supplemental`) liefern zusätzliche Informationen, die für die Bewertung einer Schwachstelle nützlich sein können, aber den Schweregrad nicht direkt beeinflussen.


CVSS - Basis-Metriken (:eng:`Base Metric Group`)
------------------------------------------------------------

.. container:: two-columns scriptsize

    .. container:: column

        **Bewertung der Ausnutzbarkeit** (:eng:`Exploitability Metrics`)

        .. class:: incremental impressive

        - Angriffsvektor (:eng:`Attack Vector`)
        - Angriffskomplexität (:eng:`Attack Complexity`)
        - Angriffsanforderungen (:eng:`Attack Requirements`)
        - Benötigte Privilegien (:eng:`Privileges Required`)
        - Erforderliche Benutzerinteraktion (:eng:`User Interaction`)

    .. container:: column incrementalr

        **Bewertung der Auswirkungen** (:eng:`Impact Metrics`)

        .. container:: incremental

            *bzgl. des betroffenen Systems* (:eng:`Vulnerable System`)

            .. class:: incremental impressive

            - Vertraulichkeit  (:eng:`Confidentiality Impact`)
            - Integrität (:eng:`Integrity Impact`)
            - Verfügbarkeit (:eng:`Availability Impact`)
        
        .. container:: incremental 

            *bzgl. nachgelagerter Systeme* (:eng:`Subsequent System`)

            .. class:: incremental impressive
                
            - Vertraulichkeit (:eng:`Confidentiality Impact`)
            - Integrität (:eng:`Integrity Impact`)
            - Verfügbarkeit (:eng:`Availability Impact`)



CVSS Bedrohungs-Metriken (:eng:`Threat Metric Group`) [#]_
--------------------------------------------------------------

.. container::  scriptsize
    
        .. class:: impressive

        - Reifegrad des Exploits (:eng:`Exploit Maturity`)
        
.. [#] Die Namen und der Gruppenzuschnitt (hier: :eng:`Temporal Metric Group`) waren unter CVSS 3.0 anders: `CVSS 3.0 <https://www.first.org/cvss/v3-0/specification-document>`__


.. container:: supplemental

    Gibt es bisher nur die Beschreibung der Schwachstelle oder gibt es bereits einen Proof-of-Concept (PoC) Exploit?


CVSS Umgebungs-Metriken 
---------------------------------------------------------------

.. container:: scriptsize two-columns

    .. container:: column tiny

        **Angepasste Basis-Metriken** (:eng:`Modified Base Metrics`)

            .. class:: impressive

            - Angriffsvektor (:eng:`Attack Vector`)
    
              Angriffskomplexität (:eng:`Attack Complexity`)

              Angriffsanforderungen (:eng:`Attack Requirements`)

              Benötigte Privilegien (:eng:`Privileges Required`)

              Erforderliche Benutzerinteraktion (:eng:`User Interaction`)

            bzgl. des betroffenen Systems **und** auch der nachgelagerten Systeme:

            .. class:: impressive

            - Vertraulichkeitsverlust   (:eng:`Confidentiality Impact`)
            
              Integritätsverlust (:eng:`Integrity Impact`)

              Verfügbarkeitsverlust (:eng:`Availability Impact`)


    .. container:: column
    
        .. class:: impressive

            - Vertraulichkeitsanforderungen (:eng:`Confidentiality Requirement`)
            
            - Integritätsanforderungen (:eng:`Integrity Requirement`)

            - Verfügbarkeitsanforderungen (:eng:`Availability Requirement`)



CVSS - Bewertung der Ausnutzbarkeit/Exploitability Metrics
------------------------------------------------------------

:Attack Vector (AV): Network, Adjacent, Local, Physical

:Attack Complexity (AC): Low, High

:Attack Requirements (AT): None, Present

:Privileges Required (PR): None, Low, High

:User Interaction (UI): None, Passive, Active


.. container:: supplemental

    **Attack Vector**

    *Network*

    Schwachstellen, die häufig "aus der Ferne ausnutzbar" sind und als ein Angriff betrachtet werden können, der auf Protokollebene über einen oder mehrere Netzknoten hinweg (z. B. über einen oder mehrere Router) ausgenutzt werden kann.

    *Adjacent*

    Der Angriff ist auf eine logisch benachbarte Topologie beschränkt. Dies kann z.B.  bedeuten, dass ein Angriff aus demselben gemeinsamen Nahbereich (z. B. Bluetooth, NFC oder IEEE 802.11) oder logischen Netz (z. B. lokales IP-Subnetz) gestartet werden muss.

    *Local*

    Der Angreifer nutzt die Schwachstelle aus, indem er lokal auf das Zielsystem zugreift (z. B. Tastatur, Konsole) oder über eine Terminalemulation (z. B. SSH); oder der Angreifer verlässt sich auf die Interaktion des Benutzers, um die zum Ausnutzen der Schwachstelle erforderlichen Aktionen durchzuführen (z. B. mithilfe von Social-Engineering-Techniken, um einen legitimen Benutzer zum Öffnen eines bösartigen Dokuments zu verleiten).

    *Physical*

    Der Angreifer muss physisch Zugriff auf das Zielsystem haben, um die Schwachstelle auszunutzen.

    **Attack Complexity**

    Wie aufwendig ist es explizite Schutzmaßnahmen ((K)ASLR, Stack Canaries, ...) zu umgehen. Wie wahrscheinlich ist es, dass ein Angriff erfolgreich ist. Im Falle von :eng:`Race Conditions` können ggf. sehr viele Ausführungen notwendig sein bevor die Race Condition erfüllt ist.

    **Attack Requirements**

    Welcher Vorbedingungen (unabhängig von den expliziten Sicherungsmaßnahmen) müssen erfüllt sein, damit die Schwachstelle ausgenutzt werden kann. (z.B. der Nutzer muss sich an seinem Smartphone mindestens einmal seit dem Boot angemeldet haben (After-First-Use vs. Before-First-Use.))

    **Privileges Required**

    Welche Privilegien muss der Angreifer mindestens haben, um die Schwachstelle auszunutzen (Sind Adminstratorrechte erforderlich oder reichen normale Benutzerrechte).

    **User Interaction**
    
    Passiv bedeuted hier, dass der Nutzer unfreiwillig die Schwachstelle ausnutzt ohne bewusst Schutzmechanismen zu unterlaufen. Aktiv bedeuted, dass der Nutzer aktiv Interaktionen unternimmt, um die Schutzmechanismen des Systems ausnutzen (z.B. durch das Installieren einer nicht-signierten Anwendung aus dem Internet).



CVSS - Bewertung der Auswirkung auf das betroffene System/Vulnerable System Impact Metrics
--------------------------------------------------------------------------------------------

:Confidentiality Impact (C): None, Low, High
:Integrity Impact (I): None, Low, High
:Availability Impact (A): None, Low, High



CVSS - Bewertung der Auswirkung auf das nachgelagerte System/Vulnerable System Impact Metrics
-----------------------------------------------------------------------------------------------

:Confidentiality Impact (C): None, Low, High
:Integrity Impact (I): None, Low, High
:Availability Impact (A): None, Low, High









.. class:: integrated-exercise transition-move-left smaller

Übung: Schwachstellen und Ihre Bewertung (1)
---------------------------------------------------------------

**Szenario** 

Ihnen liegt eine externe Festplatte vor, die Hardwareverschlüsselung unterstützt. D.h. wenn diese Festplatte an einen Computer angeschlossen wird, dann muss ein Passwort eingegeben werden, bevor auf die Daten zugegriffen werden kann. Dieses entsperren der Festplatte geschieht mit Hilfe eines speziellen Programms, dass ggf. vorher installiert werden muss. Die Festplatte ist mit AES-256-XTX verschlüsselt. 
  
Das Clientprogramm hasht erst das Passwort bevor es den Hash an den Controller der Festplatte überträgt. Die Firmware des Controller validiert das Passwort in dem es den gesendeten Hash direkt mit dem bei der Einrichtung übermittelten Hash vergleicht; d.h. es finden keine weiteren sicherheitsrelevanten Operationen außer dem direkten Vergleich statt. Zum Entsperren der Festplatte ist es demzufolge ausreichend den Hash aus der Hardware auszulesen und diesen an den Controller zu senden, um diese zu entsperren. Danach kann auf die Daten frei zugegriffen werden. 

1. Ermitteln Sie den `CVSS 4.0 Score <https://www.first.org/cvss/v4-0/>`__ für diese Schwachstelle. (`CVSS Rechner <https://www.first.org/cvss/calculator/4.0>`__)
2. Welche Anwendungsfälle sind für diese Schwachstelle denkbar?


.. Lösung:
   (ACHTUNG: Diskussionsbedarf!!!)
   CVSS:4.0/AV:P/AC:H/AT:N/PR:N/UI:N/VC:H/VI:H/VA:N/SC:N/SI:N/SA:N
   CVSS v4.0 Score: 5.3 / Medium

.. class:: integrated-exercise transition-move-left  smaller

Übung: Schwachstellen und Ihre Bewertung (2)
---------------------------------------------------------------

**Szenario** 

Durch die Analyse der Firmware eines Basebands haben Sie folgende Erkenntnisse erhalten: Wenn es Ihnen gelingt ein speziell manipuliertes Paket - welches außerhalb der Spezifikation liegt -  an das Baseband zu senden, dann kommt es zu einem Buffer-Overflow. Mit Hilfe dieses Buffer-Overflows ist es dann möglich das Baseband zum Absturz zu bringen, welches daraufhin direkt selbständig neu startet. Aufgrund des Neustarts muss der Nutzer dann jedoch seine SIM-Pin neu eingeben, um sich wieder gegenüber dem Mobilfunknetz zu authentifizieren. 

Intensive weitere Untersuchungen haben ergeben, dass es nicht möglich ist den Buffer-Overflow weitergehend auszunutzen, um zum Beispiel Daten des Smartphones abzugreifen, da die Validierung der Kommunikation mit dem Hauptprozessor effektiv ist. In einem Labortest wurden die Erkenntnisse validiert. Es war möglich ein entsprechendes Paket erfolgreich an ein Baseband zu senden und dadurch ein Neustart des Basebands zu erzwingen.

1. Ermitteln Sie den `CVSS 4.0 Score <https://www.first.org/cvss/v4-0/>`__ für diese Schwachstelle. (`CVSS Rechner <https://www.first.org/cvss/calculator/4.0>`__)
2. Welche Anwendungsfälle sind für diese Schwachstelle denkbar?

.. container:: supplemental 

    .. container:: black

        **Baseband**

        Der Baseband Chip Ihres Smartphones ist für die Kommunikation mit dem Mobilfunknetz zuständig. Als solcher hat das Baseband eine eigene Firmware, die von dem Hersteller des Basebands stammt. Die Kommunikation zwischen dem Baseband und dem Hauptprozessor erfolgt über eine wohl definierte, minimal gehaltene Schnittstelle, um die Auswirkungen von Sicherheitsproblemen ggf. eindämmen zu können.

.. Lösung:
   (ACHTUNG: Diskussionsbedarf!!!)
   CVSS:4.0/AV:A/AC:L/AT:N/PR:N/UI:N/VC:N/VI:N/VA:H/SC:N/SI:N/SA:N
   CVSS v4.0 Score: 7.1 / High ⊕
