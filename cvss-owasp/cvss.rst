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


CVSS, CVE, CVD, KEV, VEP und CWE/OWASP
=====================================================

:Dozent: **Prof. Dr. Michael Eichberg**
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: |date|


.. class:: new-section transition-fade

Social-Engineering Angriffe
-------------------------------------------------------------


Social-Engineering Angriffe
-------------------------------------------------------------

.. class:: incremental

- sind häufig die Ursache für erfolgreiche Angriffe
- stellen die größte Bedrohung für die Sicherheit von IT-Systemen dar
- es wird angenommen, dass die betroffenen Personen es in vielen Fällen nicht merken :incremental:`(Beispiel: Fake Bewerbungsgespräch)`
- mittels OSINT kann die Vorbereitung von Social-Engineering Angriffen vereinfacht werden

.. container:: supplemental

    Ein vom Angreifer bewusst eingefädeltes Bewerbungsgespräch für eine Position als Administrator könnte zum Beispiel dazu genutzt werden, um Informationen über das Zielsystem zu erhalten, die für einen Angriff nützlich sind (z.B. welche Software wird eingesetzt, wie sieht die Architektur aus, ...). In diesem Fall ist davon auszugehen, dass ein Bewerber zum Beispiel durch ein Headhunter eine gutes Angebot gemacht wird und er dann im Rahmen des Gesprächs gebeten eine Sicherheitsarchitektur darzustellen, die er einführen würde. Es ist dann davon auszugehen, dass er auf seine bisherige Erfahrung zurückgreift und diese darstellt und er somit die Architektur des Zielsystems offenlegt.


Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------

:Phishing and Spear Phishing: 

    .. container:: incremental

        *Phishing* nutzt elekr. Kommunikationswege (z.B. E-Mail, SMS, ...) um an Informationen zu gelangen. *Spear phishing* ist Phishing, bei der der Angreifer auf eine bestimmte Zielgruppe oder Person abzielt.

:Smishing: 
 
    .. container:: incremental

        Phishing mit Hilfe von SMS.

:Vishing:

    .. container:: incremental

        `Phishing mit Hilfe von Telefonanrufen <https://www.europol.europa.eu/publications-events/publications/vishing-calls>`__. :incremental:`(z.B. Anrufe von Europol)`

:Whaling:

    .. container:: incremental

        Phishing, dass sich gegen hochrangige und sehr ausgewählte Personen richtet (z.B. den CEO eines Unternehmens).


.. container:: supplemental

    **Typische Phishing E-Mail**

    .. image:: phishing-mail-fake-fedex.png 
        :width: 80%
        

Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------

:Pharming:

    .. container:: incremental

        Manipulation des DNS-Servers, um den Nutzer auf eine gefälschte Webseite zu leiten, um dann sensitive Informationen zu erlangen.

:Spam / Spam over Internet messaging (SPIM):


    .. container:: incremental

        Unerwünschte und nicht angeforderte E-Mail-Nachrichten oder Nachrichten in sozialen Medien bzw. Instant Messaging-Diensten.


Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------

:Dumpster Diving:

    .. container:: incremental

        Durchsuchen von :ger-quote:`Müllcontainern` nach Informationen, die für einen Angriff nützlich sein könnten.

:Shoulder Surfing:

    .. container:: incremental

        Beobachten von Personen, die sich an einem Computer anmelden, um das Passwort zu erfahren oder die sensitive Informationen auf dem Schreibtisch liegen haben.   

:Tailgating:

    .. container:: incremental

        Ein Angreifer nutzt die Zugangsberechtigung einer Person, um sich Zugang zu einem Gebäude zu verschaffen ohne das die Person dies bemerkt oder gar zustimmt. Dies kann durch Zugangsschleusen verhindert werden, die immer nur einer Person den Zugang gewähren. 



Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------


:Identity Fraud:

    .. container:: incremental

        Identitätsdiebstahl. Der Angreifer gibt sich als jemand anderes aus, um an Informationen zu gelangen oder um eine Straftat zu begehen.


:Invoice Scams:

    .. container:: incremental

        Versenden von Rechnungen, für Dienstleistungen und Produkte die man nicht gekauft hat. :incremental:`(Z.B. Rechnungen für Postzustellung.)`


Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------


:Credential harvesting:

    .. container:: incremental

        Sammlung von Zugangsdaten, die durch Sicherheitslücken in Systemen oder durch Phishing erlangt wurden. 


:Hoax:

    .. container:: incremental

        Eine bewusste Falschmeldung, die Menschen dazu veranlasst etwas falsches zu glauben. 


:Impersonation or Pretexting: 

    .. container:: incremental

        Vorgabe einer falschen Identität (z.B. als Mitarbeiter des IT-Supports) D.h. der Angreifer gibt sich persönlich als jemand anderes aus, um an Informationen zu gelangen und nutzt dafür keine elektronischen Hilfsmittel.

.. container:: supplemental

    Ein Beispiel eines nicht-harmlosen Streichs (Hoax) ist die Falschmeldung vom 1. April 2003, dass Bill Gates gestorben sei. Diese Falschmeldung wurde von vielen Menschen geglaubt und hatte relevanten Einfluss auf den Aktienmarkt.

    In der Anfangszeit von Github und Bitbucket wurden häufig Zugangsdaten und Zertifikate in öffentlichen Repositories gefunden, da die Nutzer diese im Quellcode hinterlegt hatten oder sogar als Ressourcen direkt eingebunden hatten.


Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------

:Eavesdropping:

    .. container:: incremental
    
        Abhören von Gesprächen, um an relevante Informationen zu gelangen.   


:Eliciting Information:

    .. container:: incremental

        Der Angreifer versucht durch geschicktes Fragen an Informationen zu gelangen, die für einen Angriff nützlich sein könnten.



Ausgewählte Social-Engineering Angriffe bzw. Terminologie
-------------------------------------------------------------


:Baiting (`Ködern`:ger:):

    .. container:: incremental

        Der Angreifer bietet etwas an, um an Informationen zu gelangen. (Z.B. ein USB-Stick mit einem Virus, der sich beim Einstecken des USB-Sticks auf dem Rechner installiert.)


:Watering Hole Attack:

    .. container:: incremental

        Der Angreifer infiziert eine Webseite, die von der Zielgruppe häufig besucht wird, um dann die Besucher der Webseite anzugreifen.

:Typo Squatting:

    .. container:: incremental
            
            Ausnutzen von Tippfehlern durch das Registrieren einer Domain, die der Domain eines Zielunternehmens ähnelt, um dann Besucher der Webseite auf eine gefälschte Webseite zu leiten. (Z.B. `www.gooogle.com`)


:ger-quote:`Motivationstechniken` von Social-Engineers 
-------------------------------------------------------------

.. class:: incremental

- Autorität: Der Angreifer gibt sich z.B. als Mitarbeiter des IT-Supports aus.
- Einschüchterung (:eng:`Intimidation`)
- Dringlichkeit
- Konsens (*"Alle machen das so."*)
- Knappheit (*"Nur noch Heute im Angebot."*)
- Vertrautheit 
- Vertrauen


.. class:: new-section transition-fade

CVSS 
----------------


.. class:: center-elements-on-slide

\
---------

.. container:: foundations

    Das `Common Vulnerability Scoring System (CVSS 4.0) <https://www.first.org/cvss/v4.0/specification-document>`__ stellt einen Rahmen bereit für die Beschreibung und Bewertung des Schweregrads von Software-/Hardware-/Firmwareschwachstellen.

    Die Bewertung der Basiskennzahlen ergibt eine Punktzahl zwischen 0,0 und 10,0. Wobei 0 bedeuted, dass die Schwachstelle (bisher) harmlos ist und 10,0 bedeutet, dass die Schwachstelle sehr gravierend ist.


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



CVSS - Bedrohungs-Metriken (:eng:`Threat Metric Group`) [#]_
--------------------------------------------------------------

.. container::  scriptsize
    
        .. class:: impressive

        - Reifegrad des Exploits (:eng:`Exploit Maturity`)
        
.. [#] Die Namen und der Gruppenzuschnitt (hier: :eng:`Temporal Metric Group`) waren unter CVSS 3.0 anders: `CVSS 3.0 <https://www.first.org/cvss/v3-0/specification-document>`__


.. container:: supplemental

    Gibt es bisher nur die Beschreibung der Schwachstelle oder gibt es bereits einen Proof-of-Concept (PoC) Exploit?


CVSS - Umgebungs-Metriken 
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

    Welcher Vorbedingungen (unabhängig von den expliziten Sicherungsmaßnahmen) müssen erfüllt sein, damit die Schwachstelle ausgenutzt werden kann. (z.B. der Nutzer muss sich an seinem Smartphone mindestens einmal seit dem Boot angemeldet haben (*After-First-Use* vs. *Before-First-Use*.))

    **Privileges Required**

    Welche Privilegien muss der Angreifer mindestens haben, um die Schwachstelle auszunutzen (Sind Adminstratorrechte erforderlich oder reichen normale Benutzerrechte).

    **User Interaction**
    
    Passiv bedeuted hier, dass der Nutzer unfreiwillig die Schwachstelle ausnutzt ohne bewusst Schutzmechanismen zu unterlaufen. Aktiv bedeuted, dass der Nutzer aktiv Interaktionen unternimmt, um die Schutzmechanismen des Systems auszuhebeln (z.B. durch das Installieren einer nicht-signierten Anwendung aus dem Internet).



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


.. class:: new-section transition-fade

Common Vulnerabilities and Exposures (`CVE <https://cve.org/>`__)
--------------------------------------------------------------------



Common Vulnerabilities and Exposures (`CVE <https://cve.org/>`__)
------------------------------------------------------------------

.. epigraph:: CVE definiert eine Sicherheitslücke als:

    "Eine Schwachstelle in der Berechnungslogik (z. B. Code), die in Software- und Hardwarekomponenten gefunden wird und die, wenn sie ausgenutzt wird, zu einer negativen Auswirkung auf die **Vertraulichkeit**, **Integrität** oder **Verfügbarkeit** führt. Die Behebung der Schwachstellen in diesem Zusammenhang umfasst in der Regel Änderungen am Code, kann aber auch Änderungen an der Spezifikation oder sogar die Ablehnung der Spezifikation (z. B. die vollständige Entfernung der betroffenen Protokolle oder Funktionen) beinhalten."

    -- https://nvd.nist.gov/vuln (Übersetzt mit DeepL)

.. container:: incremental margin-top-2em

   In der Praxis werden n-Day und 0-Day Schwachstellen unterschieden.


Zweck von CVEs
------------------

.. class:: incremental

- Schwachstellen eindeutig identifizieren und bestimmten Versionen eines Codes (z. B. Software und gemeinsam genutzte Bibliotheken) mit diesen Schwachstellen verknüpfen. 
- Kommunikationsgrundlage bilden, damit mehrere Parteien über eine eindeutig identifizierte Sicherheitslücke diskutieren können. `National Vulnerabilities Database - NIST <https://nvd.nist.gov>`__


.. class:: scriptsize

16. Jan. 2024 - zuletzt bewertete CVEs
-------------------------------------------------------------

.. container::  incremental

    .. die folgende Liste wurde per Copy-and-Paste des HTML Code von der NIST Webseite erzeugt:

    .. raw:: html

                        <ul>
							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20672" id="cveDetailAnchor-0">CVE-2024-20672</a></strong>  - .NET Denial of Service Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-0">
										<span id="cvss3-link-0"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20672&amp;vector=AV:N/AC:L/PR:N/UI:N/S:U/C:N/I:N/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-danger" data-testid="vuln-cvss3-link-0" aria-label="V3 score for CVE-2024-20672">7.5 HIGH</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20666" id="cveDetailAnchor-1">CVE-2024-20666</a></strong>  - BitLocker Security Feature Bypass Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-1">
										<span id="cvss3-link-1"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20666&amp;vector=AV:P/AC:L/PR:L/UI:N/S:U/C:H/I:H/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-warning" data-testid="vuln-cvss3-link-1" aria-label="V3 score for CVE-2024-20666">6.6 MEDIUM</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20680" id="cveDetailAnchor-2">CVE-2024-20680</a></strong>  - Windows Message Queuing Client (MSMQC) Information Disclosure
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-2">
										<span id="cvss3-link-2"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20680&amp;vector=AV:N/AC:L/PR:L/UI:N/S:U/C:H/I:N/A:N&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-warning" data-testid="vuln-cvss3-link-2" aria-label="V3 score for CVE-2024-20680">6.5 MEDIUM</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20676" id="cveDetailAnchor-3">CVE-2024-20676</a></strong>  - Azure Storage Mover Remote Code Execution Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-3">
										<span id="cvss3-link-3"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20676&amp;vector=AV:N/AC:H/PR:H/UI:N/S:C/C:H/I:H/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-danger" data-testid="vuln-cvss3-link-3" aria-label="V3 score for CVE-2024-20676">8.0 HIGH</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20674" id="cveDetailAnchor-4">CVE-2024-20674</a></strong>  - Windows Kerberos Security Feature Bypass Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-4">
										 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20682" id="cveDetailAnchor-5">CVE-2024-20682</a></strong>  - Windows Cryptographic Services Remote Code Execution Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-5">
										<span id="cvss3-link-5"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20682&amp;vector=AV:L/AC:L/PR:L/UI:N/S:U/C:H/I:H/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-danger" data-testid="vuln-cvss3-link-5" aria-label="V3 score for CVE-2024-20682">7.8 HIGH</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20683" id="cveDetailAnchor-6">CVE-2024-20683</a></strong>  - Win32k Elevation of Privilege Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-6">
										<span id="cvss3-link-6"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20683&amp;vector=AV:L/AC:L/PR:L/UI:N/S:U/C:H/I:H/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-danger" data-testid="vuln-cvss3-link-6" aria-label="V3 score for CVE-2024-20683">7.8 HIGH</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li>
								<div class="col-lg-9">
									<p>
										<strong><a href="/vuln/detail/CVE-2024-20681" id="cveDetailAnchor-7">CVE-2024-20681</a></strong>  - Windows Subsystem for Linux Elevation of Privilege Vulnerability
									</p>
								</div>
								<div class="col-lg-3">
									<p id="severity-score-7">
										<span id="cvss3-link-7"> <em>V3.1:</em> <a href="/vuln-metrics/cvss/v3-calculator?name=CVE-2024-20681&amp;vector=AV:L/AC:L/PR:L/UI:N/S:U/C:H/I:H/A:H&amp;version=3.1&amp;source=Microsoft%20Corporation" class="label label-danger" data-testid="vuln-cvss3-link-7" aria-label="V3 score for CVE-2024-20681">7.8 HIGH</a><br>
										</span> 
									</p>
								</div>
							</li>

							<li> ... </li>
						</ul>




.. class:: smaller

Beschreibung eines `CVEs <https://github.com/CVEProject/cvelistV5>`__
----------------------------------------------------------------------

Jeder CVE ist mit Hilfe eines wohldefinierten JSON-Dokuments beschrieben. Gekürztes Beispiel

.. code:: json
    :class: footnotesize

    { "dataVersion": "5.0",
      "cveMetadata": {
          "cveId": "CVE-2023-51034",
          "assignerOrgId": "8254265b-2729-46b6-b9e3-3dfca2d5bfca",
          "assignerShortName": "mitre",
          "datePublished": "2023-12-22T00:00:00"
      },
      "containers": { "cna": { ...,
            "descriptions": [ {
               "value": "TOTOlink [...] vulnerable to command execution [...]"
            } ], ...,
            "references": [{
               "url": "815yang.github.io/[...]totolink_UploadFirmwareFile/"
              } ], ...
    } } }




National Vulnerability Database (`NVDs <https://nvd.nist.gov/>`__)
---------------------------------------------------------------------

.. class:: incremental

- Auflistung aller CVEs und deren Bewertung
- Alle Schwachstellen in der NVD wurden sind einer CVE-Kennung versehen 
- Die NVD ist ein Produkt der NIST Computer Security Division, Information Technology Laboratory
- Verlinkt häufig weiterführend Seiten, die Lösungshinweise und Tools bereitstellen, um die Schwachstelle zu beheben;
- Verweist auf entsprechende Schwachstellen gemäß `CWEs <https://cwe.mitre.org/>`__
- Verlinkt gelegentlich *PoC* Exploits (:eng:`Proof-of-Concept Exploits`)



Common Weakness Enumeration (`CWE <https://cwe.mitre.org/>`__)
----------------------------------------------------------------

- eine kollaborativ entwickelte, vollständig durchsuchbare, kategorisierte Liste von Typen von Software- und Hardware-Schwachstellen und deren Beschreibung, dient als:
  
  .. class:: incremental

  - gemeinsame Sprache, 
  - Messlatte für Sicherheitstools,
  - als Grundlage für die Identifizierung von Schwachstellen sowie für Maßnahmen zur Abschwächung und Prävention.


CWE - Schwachstellenkatalog `TOP 8 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18
    
    1 , CWE-787 , Out-of-bounds Write  , 0
    2 , CWE-79 , Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting') , 0
    3 , CWE-89 , Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection') , 0
    4 , CWE-416 , Use After Free , +3
    5 , CWE-78 , Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection') , +1
    6 , CWE-20 , Improper Input Validation , -2
    7 , CWE-125 , Out-of-bounds Read , -2
    8 , CWE-22 , Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal') , 0

CWE - Schwachstellenkatalog `TOP 9-16 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18
    
    9 , CWE-352 , Cross-Site Request Forgery (CSRF) , 0
    10 , CWE-434 , Unrestricted Upload of File with Dangerous Type , 0
    11 , CWE-862 , Missing Authorization ,  +5
    12 , CWE-476 , NULL Pointer Dereference , -1
    13 , CWE-287 , Improper Authentication , +1
    14 , CWE-190 , Integer Overflow or Wraparound , -1
    15 , CWE-502 , Deserialization of Untrusted Data , -3
    16 , CWE-77 , Improper Neutralization of Special Elements used in a Command ('Command Injection') , +1


.. container:: supplemental

    Request Forgery = :ger:`Anfragefälschung`


CWE - Schwachstellenkatalog `TOP 17-25 in 2023 <https://cwe.mitre.org/top25/archive/2023/2023_top25_list.html#tableView>`__
--------------------------------------------------------------------------------------------------------------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header:     Rank , ID , Name, Rank Change vs. 2022
    :widths: 7, 12, 63, 18

    17 , CWE-119 , Improper Restriction of Operations within the Bounds of a Memory Buffer , +2
    18 , CWE-798 , Use of Hard-coded Credentials , -3
    19 , CWE-918 , Server-Side Request Forgery (SSRF) , +2
    20 , CWE-306 , Missing Authentication for Critical Function , -2
    21 , CWE-362 , Concurrent Execution using Shared Resource with Improper Synchronization ('Race Condition') , +1
    22 , CWE-269 , Improper Privilege Management , +7
    23 , CWE-94 , Improper Control of Generation of Code ('Code Injection') , +2
    24 , CWE-863 , Incorrect Authorization ,  +4
    25 , CWE-276 , Incorrect Default Permissions , -5

Beispiel eines CVEs für eine *XSS Schwachstelle*
------------------------------------------------

.. epigraph:: CVE-2023-50712

   Iris is a web collaborative platform aiming to help incident responders sharing technical details during investigations. A stored Cross-Site Scripting (XSS) vulnerability has been identified in iris-web, affecting multiple locations in versions prior to v2.3.7. The vulnerability may allow an attacker to inject malicious scripts into the application, which could then be executed when a user visits the affected locations. This could lead to unauthorized access, data theft, or other related malicious activities. An attacker needs to be authenticated on the application to exploit this vulnerability. The issue is fixed in version v2.3.7 of iris-web. No known workarounds are available.

   -- Published: December 22, 2023

.. container:: footnotesize
    
    **Bewertung**: CVSS V3.1: 5.4 MEDIUM


Beispiel eines CVEs für eine *Arbitrary Code Execution Schwachstelle*
----------------------------------------------------------------------

.. epigraph:: CVE-2023-51034

   TOTOlink EX1200L V9.3.5u.6146_B20201023 is vulnerable to arbitrary command execution via the cstecgi.cgi UploadFirmwareFile interface.

   -- Published: December 22, 2023; Last modified: January 2, 2024

.. container:: footnotesize
    
    :Bewertung: CVSS V3.1: 9.8 Critical
    :PoC Exploit: https://815yang.github.io/2023/12/12/ex1200l/totolink_ex1200L_UploadFirmwareFile/
    :Weakness Enumeration: CWE-434 Unrestricted Upload of File with Dangerous Type

.. container:: supplemental

    Bei TOTOlink EX1200L handelt es sich um einen Wifi Range Expander.



.. class:: smaller

CWE-434 Unrestricted Upload of File with Dangerous Type
--------------------------------------------------------

.. class:: footnotesize

.. epigraph::
    
    Beschreibung:

        Das Produkt ermöglicht es dem Angreifer, Dateien gefährlicher Typen hochzuladen oder zu übertragen, die in der Produktumgebung automatisch verarbeitet werden können.

    Arten der Einführung:

        Diese Schwäche wird durch das Fehlen einer Sicherheitstaktik während der Architektur- und Entwurfsphase verursacht. 

    Scope: Integrität, Vertraulichkeit, Verfügbarkeit

        Willkürliche Codeausführung ist möglich, wenn eine hochgeladene Datei vom Empfänger als Code interpretiert und ausgeführt wird. [...] 

    -- https://cwe.mitre.org/data/definitions/434.html (Übersetzt mit DeepL) 



.. class:: smaller

CVE-2023-51034 - PoC (gekürzt)
-------------------------------------------------

Initiale Anfrage:

.. code:: http

    POST /cgi-bin/cstecgi.cgi HTTP/1.1
    [...]    
    {
        "FileName":";ls../>/www/yf.txt;",
        "topicurl":"UploadFirmwareFile"

    }

.. container:: incremental

    Abfrage der Datei (hier: :code:`yf.txt`):

    .. code:: http

        GET /yf.txt HTTP/1.1
        [...]
        Connection: close

    Das Ergebnis ist die Auflistung der Dateien im Verzeichnis.


.. class:: smaller

CVE-2023-51034 - zugrundeliegende Schwachstelle
-------------------------------------------------

.. code:: C
    :class: scriptsize

    Var = (const char *)websGetVar(a1, "FileName", &byte_42FE28);
    v3 = (const char *)websGetVar(a1, "FullName", &byte_42FE28);
    v4 = (const char *)websGetVar(a1, "ContentLength", &word_42DD4C);
    v5 = websGetVar(a1, "flags", &word_42DD4C);
    v6 = atoi(v5);
    Object = cJSON_CreateObject();
    v8 = fopen("/dev/console", "a");
    v9 = v8;
    if ( v8 )
    {
        fprintf(v8, "[%s:%d] FileName=%s,FullName=%s,ContentLength=%s\n", 
                    "UploadFirmwareFile", 751, Var, v3, v4);
        fclose(v9);
    }
    v10 = strtol(v4, 0, 10) + 1;
    strcpy(v52, "/tmp/myImage.img");
    doSystem("mv %s %s", Var, v52);

.. container:: supplemental

    Die Lücke ist auf die folgenden Zeilen zurückzuführen:

    .. code:: c

        Var = (const char *)websGetVar(a1, “FileName”, &byte_42FE28);
        doSystem(“mv %s %s”, Var, v52);

    Der Aufruf von :code:`doSystem` ermöglicht die Ausführung von beliebigem Code. Der Angreifer kann den Wert von :code:`Var` so manipulieren, dass er quasi beliebigen Code ausführen kann.


Ausgenutzte Schwachstellen
---------------------------


Der `Known Exploited Vulnerabilities (KEV) Katalog der CISA <https://www.cisa.gov/known-exploited-vulnerabilities-catalog>`__ umfasst Produkte deren Schwachstellen ausgenutzt wurden oder aktiv ausgenutzt werden.  
 
.. class:: incremental

- Kriterien für die Aufnahme in den KEV Katalog:

  1. Eine CVE-Id liegt vor
  2. Aktive Ausnutzung (:eng:`Active Exploitation`) (ggf. reicht es wenn :ger-quote:`nur` ein *Honeypot* aktiv angegriffen wurde) - ein PoC reicht nicht aus
  3. eine Handlungsempfehlung liegt vor (z.B. Patch, Workaround oder vollständige Abschaltung)
- Firmen sollten die KEV Schwachstellen priorisieren, um die Wahrscheinlichkeit eines erfolgreichen Angriffs zu verringern. (Ausgewählte Amerikanische Behörden sind sogar verpflichtet innerhalb vorgegebener Zeiträume zu reagieren.)
 

2023 CWE Top 10 KEV Weaknesses
-------------------------------

.. csv-table::
    :class: small highlight-line-on-hover
    :header: Schwachstelle, CWE ID, # CVE Mappings in KEV, Avg. CVSS

    Use After Free, 416, 44, 8.54
    Heap-based Buffer Overflow, 122, 32, 8.79
    Out-of-bounds Write, 787, 34, 8.19
    Improper Input Validation, 20, 33, 8.27
    Improper Neutralization of Special Elements used in an OS Command ("OS Command Injection"), 78, 25, 9.36
    Deserialization of Untrusted Data, 502, 16, 9.06
    Server-Side Request Forgery (SSRF), 918, 16, 8.72
    Access of Resource Using Incompatible Type ("Type Confusion"), 843, 16, 8.61
    Improper Limitation of a Pathname to a Restricted Directory ("Path Traversal"), 22, 14, 8.09
    Missing Authentication for Critical Function, 306,  8, 8.86


Offenlegung von Sicherheitslücken nach `CISA <https://www.cisa.gov/coordinated-vulnerability-disclosure-process>`__ [#]_
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

:eng:`Coordinated Vulnerability Disclosure (CVD)`

.. class:: incremental smaller

1. Sammlung von Schwachstellenmeldungen
   
   - Eigene Schwachstellenanalysen
   - Überwachung öffentlicher Quellen
   - Direkte Meldungen von Herstellern, Forschern und Nutzern
  
2. Analyse der Schwachstellenmeldungen zusammen mit den Herstellern, um die Sicherheitsauswirkungen zu verstehen
3. Entwicklung von Strategien zur Eindämmung der Schwachstellen; insbesondere Entwicklung von notwendigen Patches
4. Anwendung der Strategien zur Eindämmung der Schwachstellen in Zusammenarbeit mit dem Hersteller und ggf. betroffenen Nutzern
5. Veröffentlichung der Schwachstellenmeldung in Abstimmung mit der Quelle des Schwachstellenberichts und dem Hersteller

.. container:: supplemental

    **CISA** (America's Cybersecurity and Infrastructure Security Agency/Cyber Defense Agency).

.. [#] Das BSI verfährt ähnlich.



Zeitlicher Rahmen für die Offenlegung von Sicherheitslücken
--------------------------------------------------------------

Der Zeitrahmen für die Offenlegung von Sicherheitslücken wird durch folgende Faktoren bestimmt:

.. class:: incremental 

- Aktive Ausnutzung der Schwachstelle
- besonders kritische Schwachstellen
- Auswirkungen auf Standards
- bereits öffentlich bekannt zum Beispiel durch einen Forscher
- Auswirkungen auf die kritische Infrastruktur, öffentliche Gesundheit und Sicherheit
- die Verfügbarkeit von effektiven Eindämmungsmaßnahmen
- das Verhalten des Herstellers und die Möglichkeit der Entwicklung eines Patches
- Schätzung des Herstellers wie lange es dauert einen Patch zu entwickeln, zu testen und auszurollen.



Welche neuen Schwachstellen werden in absehbarer Zeit ausgenutzt?
----------------------------------------------------------------------------

.. admonition:: Beobachtung 
    :class: the-blue-background

    Am 1. Oktober 2023 hat die NVD 139.473 CVSS veröffentlicht. In den folgenden 30 Tagen wurden 3.852 CVEs beobachtet, die ausgenutzt (:eng:`exploited`) wurden. 

    Ca. 5-6% aller Schwachstellen werden :ger-quote:`irgendwann` ausgenutzt. [#]_
    
.. admonition:: Frage
    :class: question incremental 

    Wie stelle ich sicher, dass ich meine Bemühungen zum Beseitigen der Schwachstellen auf diejenigen konzentriere, die am wahrscheinlichsten zeitnahe ausgenutzt werden?

.. [#] Fortinet, `Threat Landscape Report Q2 2018 <https://www.fortinet.com/content/dam/fortinet/assets/threat-reports/q2-2018-threat-landscape-report.pdf>`__

Nutzung des CVSS als Grundlage für die Schätzung?
----------------------------------------------------------------------------

Annahme: Schwachstellen mit einem CVSS Score :math:`\geq` 7 (d.h. mit einer Bewertung von Hoch oder kritisch) werden ausgenutzt.

.. class:: incremental

- 80.024 Schwachstellen haben einen CVSS Score :math:`\geq` 7
  
  **Ausgenutzt wurden: 3.166**
- 59.449 Schwachstellen haben eine CVSS :math:`<` 7
  
  **Ausgenutzt wurden: 686**

.. container:: incremental assessment smaller

    **Zusammenfassung:**

    D.h. die Strategie "Priorisierung von Schwachstellen mit einem CVSS Score :math:`\geq` 7" ist keine geeignete Strategie, da sie nicht alle relevanten  Schwachstellen erfasst (686 *False Negatives*) und - ganz insbesondere - zu viele Schwachstellen (76.858 *False Positives*) erfasst, die nicht ausgenutzt werden.



`Exploit Prediction Scoring System (EPSS) <https://www.first.org/epss/>`__
--------------------------------------------------------------------------

.. class:: incremental

- EPSS ist eine Methode zur *Bewertung der Wahrscheinlichkeit*, dass eine Schwachstelle in den nächsten 30 Tagen ausgenutzt wird
- EPSS basiert auf der Analyse von Schwachstellen, die in den letzten 12 Monaten ausgenutzt wurden
- EPSS nutzt KI basierend auf folgenden Informationen (Stand Jan. 2024):

  .. class:: incremental smaller

  - Hersteller
  - Alter der Schwachstelle (Tage seit der Veröffentlichung des CVEs)
  - die Beschreibung der Schwachstelle
  - betroffene CWEs
  - CVSS Bewertungen der Schwachstellen
  - Wird der CVE auf bekannten Listen diskutiert bzw. aufgelistet?
  - Gibt es öffentliche verfügbare Exploits?


Nutzung des EPSS für die Schätzung? [#]_
----------------------------------------------------------------------------

Annahme: Schwachstellen mit EPSS 10% und größer sind werden ausgenutzt werden.


.. class:: incremental

- 3.735 Schwachstellen haben ein Wahrscheinlichkeit von EPSS 10% und größer
  
  **Ausgenutzt wurden: 2.4356**
- 135.738 Schwachstellen haben ein EPSS :math:`<` 10%
    
  **Ausgenutzt wurden: 1.417**

.. container:: incremental assessment smaller

    **Zusammenfassung:**

    D.h. die Strategie "Priorisierung von Schwachstellen mit einem EPSS von 10% und höher" ist eine geeignetere Strategie, da noch immer sehr viele  relevante Schwachstellen erfasst werden und - ganz insbesondere - die Anzahl der zu beachtenden Schwachstellen ganz massiv reduziert wird ohne die Gesamtqualität zu stark zu beeinflussen.

.. [#] `Enhancing Vulnerability Prioritization: Data-Driven Exploit Predictions with Community-Driven Insights <https://arxiv.org/abs/2302.14172>`__



`Vulnerabilities Equities Process (VEP) (USA) <https://trumpwhitehouse.archives.gov/sites/whitehouse.gov/files/images/External%20-%20Unclassified%20VEP%20Charter%20FINAL.PDF>`__ [#]_
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

.. epigraph::

    [...] Der *Vulnerability-Equity-Process (VEP)* wägt ab, ob Informationen über Schwachstellen an den Hersteller/Lieferanten weitergegeben werden sollen, in der Erwartung, dass sie gepatcht werden, oder ob die Kenntnis der Schwachstelle vorübergehend auf die US-Regierung und möglicherweise andere Partner beschränkt werden soll, damit sie für Zwecke der nationalen Sicherheit und der Strafverfolgung, wie z. B. nachrichtendienstliche Erfassung, militärische Operationen und/oder Spionageabwehr, genutzt werden können. [...]

    -- Übersetzt von www.DeepL.com/Translator 

.. [#] die rechtlichen Rahmenbedingungen bzgl. eines effektiven Schwachstellenmanagement sind in Deutschland gerade in der Diskussion. (Stand Jan. 2024); Schwachstellen, die direkt an das BSI gemeldet werden, unterliegen dem CVD.

.. container:: supplemental

    Insbesondere durch die föderale Struktur in Deutschland kann es ggf. dazu kommen, dass bezüglich der Handhabung von Schwachstellen unterschiedliche rechtliche Regelungen gelten werden - je nachdem ob die Behörde eine Bundes- oder Landesbehörde ist.


`Vulnerabilities Equities Process (VEP) (USA) <https://trumpwhitehouse.archives.gov/sites/whitehouse.gov/files/images/External%20-%20Unclassified%20VEP%20Charter%20FINAL.PDF>`__
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


.. epigraph::

    [...] Die Entscheidung der US-Regierung, ob eine Schwachstelle veröffentlicht oder eingeschränkt werden soll, ist nur ein Element des Prozesses zur Bewertung der Schwachstellen und ist nicht immer eine binäre Entscheidung. Andere Optionen, die in Betracht gezogen werden können, sind die Verbreitung von Informationen zur Schadensbegrenzung an bestimmte Stellen, ohne die jeweilige Schwachstelle offenzulegen, die Einschränkung der Nutzung der Schwachstelle durch die US-Regierung in irgendeiner Weise, die Information von Regierungsstellen der USA und verbündeter Staaten über die Schwachstelle [...]. 
    -- Übersetzt von www.DeepL.com/Translator 
    
`Vulnerabilities Equities Process (VEP) (USA) <https://trumpwhitehouse.archives.gov/sites/whitehouse.gov/files/images/External%20-%20Unclassified%20VEP%20Charter%20FINAL.PDF>`__
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


.. epigraph::

    [...] Alle diese Entscheidungen müssen auf der Grundlage des Verständnisses der Risiken einer Verbreitung, des potenziellen Nutzens von Schwachstellen durch die Regierung sowie der Risiken und Vorteile aller dazwischen liegenden Optionen getroffen werden. [...]

    -- Übersetzt von www.DeepL.com/Translator 


Schwachstellenmanagement in Deutschland (2021-2025)
-------------------------------------------------------------


.. epigraph::

    [...] Die Ausnutzung von Schwachstellen von IT-Systemen steht in einem hochproblematischen Spannungsverhältnis zur IT-Sicherheit und den Bürgerrechten. Der Staat wird daher keine Sicherheitslücken ankaufen oder offenhalten, sondern sich in einem Schwachstellenmanage- ment unter Federführung eines unabhängigeren Bundesamtes für Sicherheit in der Informa- tionstechnik immer um die schnellstmögliche Schließung bemühen.[...]

    -- KOALITIONSVERTRAG 2021—2025 (SPD, BÜNDNIS 90/DIE GRÜNEN, FDP)
    



.. class:: integrated-exercise transition-move-left

CVEs - Übung
---------------------------------------------------------------

1. Finden Sie Schwachstellen, die macOS Sonoma betreffen.
   
   .. auf https://nvd.nist.gov/search "macOS Sonoma" eingeben:
     https://nvd.nist.gov/vuln/search/results?form_type=Basic&results_type=overview&query=macOS+Sonoma&search_type=all&isCpeNameSearch=false

2. Finden Sie heraus um was es bei CVE-2020-20095 geht.

   .. es handelt sich um eine URI Spoofing Attacke bei der Nutzer dazu verleitet werden können, auf einen scheinbar harmlosen Link zu klicken, der sie auf eine bösartige Webseite umleitet.
      Poc:  https://github.com/zadewg/RIUS




.. class:: new-section transition-fade

Diskussion relevanter Schwachstellen (CWEs)
-------------------------------------------------------------


.. No 1 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-787: Out-of-bounds Write (Memory Corruption)
--------------------------------------------------------

CWE-787: Out-of-bounds Write
----------------------------

:Beschreibung: Es werden Daten hinter oder vor den Bereich des Puffers geschrieben.
:Programmiersprachen: C /C++
:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technische Auswirkungen: Speichermodifikation; DoS: Crash, Beendigung oder Neustart; Ausführen von nicht autorisiertem Code oder Befehlen


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 1
--------------------------------------------------------


.. code:: c

    int id_sequence[3];

    /* Populate the id array. */

    id_sequence[0] = 123;
    id_sequence[1] = 234;
    id_sequence[2] = 345;
    id_sequence[3] = 456;


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 2
--------------------------------------------------------

.. code:: C

    int returnChunkSize(void *) {

        /* if chunk info is valid, return the size of usable memory,

        * else, return -1 to indicate an error

        */
        ...
    }

    int main() {
        ...
        memcpy(destBuf, srcBuf, (returnChunkSize(destBuf)-1));
        ...
    }

.. container:: post-lecture-exercise-solution

    `memcpy` erwartet als dritten Parameter einen :code:`unsigned int`. Wenn :code:`returnChunkSize -1 zurückgibt, dann wird :code:`MAX_INT-1` verwendet.


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 3
--------------------------------------------------------

.. code:: C

    void host_lookup(char *user_supplied_addr){
        struct hostent *hp;
        in_addr_t *addr;
        char hostname[64];
        in_addr_t inet_addr(const char *cp);

        /* routine that ensures user_supplied_addr is in the right format for 
           conversion */

        validate_addr_form(user_supplied_addr);
        addr = inet_addr(user_supplied_addr);
        hp = gethostbyaddr( addr, sizeof(struct in_addr), AF_INET);
        strcpy(hostname, hp->h_name);
    }

.. container:: post-lecture-exercise-solution

    - Problem 1: hostname hat nur 64 Bytes, aber der Name des Hosts kann länger sein.
    - Problem 2: `gethostbyaddr` kann NULL zurückgeben, wenn der Host nicht gefunden werden kann. (Null pointer dereference)


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 4
--------------------------------------------------------

.. code:: C

    char * copy_input(char *user_supplied_string){
      int i, dst_index;
      char *dst_buf = (char*)malloc(4*sizeof(char) * MAX_SIZE);
      if ( MAX_SIZE <= strlen(user_supplied_string) ) die("string too long");
      dst_index = 0;
      for ( i = 0; i < strlen(user_supplied_string); i++ ){
        if( '&' == user_supplied_string[i] ){
          dst_buf[dst_index++] = '&';
          dst_buf[dst_index++] = 'a';
          dst_buf[dst_index++] = 'm';
          dst_buf[dst_index++] = 'p';
          dst_buf[dst_index++] = ';';
        }
        else if ('<' == user_supplied_string[i] ){ /* encode to &lt; */ }
        else dst_buf[dst_index++] = user_supplied_string[i];
      }
      return dst_buf;
    }

.. container:: post-lecture-exercise-solution

    - Problem: :code:`dst_buf` hat nur :code:`4*sizeof(char) * MAX_SIZE`` Bytes. Wenn der Nutzer einen sehr langen String mit (fast) nur `&` übermittelt, dann wird der Puffer überlaufen, da das Encoding 5 Zeichen benötigt.


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 5
--------------------------------------------------------

.. code:: C

    char* trimTrailingWhitespace(char *strMessage, int length) {
      char *retMessage;
      char message[length+1];                    // copy input string to a 
      int index;                                 //      temporary string
      for (index = 0; index < length; index++) { //
        message[index] = strMessage[index];      //
      }                                          //
      message[index] = '\0';                     //

      int len = index-1;                         // trim trailing whitespace
      while (isspace(message[len])) {            //
        message[len] = '\0';                     //
        len--;                                   //
      }                                          //
      
      retMessage = message;
      return retMessage;                         // return trimmed string
    }

.. container:: supplemental

    :isspace: If an argument (character) passed to the isspace() function is a white-space character, it returns non-zero integer. If not, it returns 0.

.. container:: post-lecture-exercise-solution

    - Problem: Zeichenketten, die nur aus Whitespace bestehen, werden nicht korrekt behandelt. In diesem Fall kommt es zu einem Buffer-Underflow (d.h. es wird auf den Speicherbereich vor dem Puffer zugegriffen).
    


.. class:: scriptsize

CWE-787: Out-of-bounds Write - Beispiel 6
--------------------------------------------------------

.. code:: C

    int i;
    unsigned int numWidgets;
    Widget **WidgetList;

    numWidgets = GetUntrustedSizeValue();
    if ((numWidgets == 0) || (numWidgets > MAX_NUM_WIDGETS)) {
      ExitError("Incorrect number of widgets requested!");
    }
    WidgetList = (Widget **)malloc(numWidgets * sizeof(Widget *));
    printf("WidgetList ptr=%p\n", WidgetList);
    for(i=0; i<numWidgets; i++) {
      WidgetList[i] = InitializeWidget();
    }
    WidgetList[numWidgets] = NULL;
    showWidgets(WidgetList);


.. container:: post-lecture-exercise-solution

    - Problem 1: Der Rückgabewert von :code:`malloc` wird nicht überprüft.
    - Problem 2: :code:`WidgetList[numWidgets] = NULL;` schreibt außerhalb des Puffers. (Buffer-Overflow)
    

CWE-787: Out-of-bounds Write - Mögliche Abhilfemaßnahmen
----------------------------------------------------------

.. class:: incremental

- Verwendung einer sicheren Programmiersprache (Java, ...)
- Verwendung von Bibliotheken, die sicherer sind (z.B. :code:`strncpy` statt :code:`strcpy`)
- Kompilierung mit entsprechenden Flags, die entsprechende Prüfung aktivieren (z.B. :code:`-D_FORTIFY_SOURCE=2`)
- Kompilierung als Position-Independent-Code 

  :minor:`Dies löst nicht das Problem, aber es macht es schwerer eine Schwachstelle auszunutzen.`
- Statische Analyse Werkzeuge
- Dynamische Analyse Werkzeuge (z.B. *Fuzzing*, *Fault Injection*, ...)



.. No 2 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-79: Improper Neutralization of Input During Web Page Generation (*Cross-site Scripting* or *XSS*)
----------------------------------------------------------------------------------------------------------

CWE-79: Improper Neutralization of Input During Web Page Generation
---------------------------------------------------------------------

:Kurzbeschreibung: Nutzereingaben werden nicht oder falsch bereinigt, bevor sie in die Ausgabe eingefügt werden, die als Webseite für andere Benutzer verwendet wird.

.. The product does not neutralize or incorrectly neutralizes user-controllable input before it is placed in output that is used as a web page that is served to other users.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technische Auswirkungen: Speichermodifikation; DoS: Crash, Beendigung oder Neustart; Ausführen von nicht autorisiertem Code oder Befehlen
:Betrifft: Zugriffskontrolle, Vertraulichkeit
:Typen: Stored XSS (Typ 2), Reflected XSS (Typ 1), DOM-based XSS (Typ 0)

.. container:: supplemental

    Durch eine XSS Lücke werden häufig Informationen abgegriffen (z.B. Session Cookies). Allerdings ist es ggf. auch möglich, dass der Angreifer die Session des Nutzers übernimmt und sich als dieser ausgibt. 

Stored XSS (Typ 2)
-------------------

.. image:: xss/stored-xss.svg
   :alt: Stored XSS
   :width: 1700px
   :align: center


Reflected XSS (Typ 1)
----------------------

.. image:: xss/reflected-xss.svg
   :alt: Reflected XSS
   :width: 1650px
   :align: center

.. container:: supplemental

    Reflected XSS ist häufig schwerer auszunutzen, da der Angreifer den Nutzer dazu bringen muss, einen Link zu klicken, der den Angriffsvektor enthält. Bei Stored XSS ist dies nicht notwendig, da der Angriffsvektor bereits auf dem Server gespeichert ist.


Dom-based XSS (Typ 0)
----------------------

.. image:: xss/dom-based-xss.svg
   :alt: Dom-based XSS
   :width: 1500px
   :align: center

.. container:: supplemental

    Dom-based XSS ist am schwersten Auszunutzen, da der Angreifer den Nutzer dazu bringen muss den Schadcode in die Informationen einzubringen, die von dem Script verarbeitet werden (z.B. durch das Eingeben in ein Formular).




.. class:: scriptsize

CWE-79: XSS - Beispiel 1 - XSS Typ 1 (Php)
--------------------------------------------------------


.. code:: php

    # Rückgabe einer Willkommensnachricht basierend auf dem 
    # HTTP Get username Parameter
    $username = $_GET['username'];
    echo '<div class="header"> Welcome, ' . $username . '</div>';



.. container:: post-lecture-exercise-solution

    - Problem: der Nutzername kann "beliebig lange" sein und insbesondere beliebigen JavaScript Code enthalten. Beispiel :code:`http://trustedSite.example.com/welcome.php?username=<Script Language="Javascript">alert("You've been attacked!");</Script>`. Komplexerer Code könnte zum Beispiel ein Fakelogin nachbauen und so die Zugangsdaten des Nutzers abgreifen. Entsprechende Links könnten mit Hilfe von Werkzeugen so verschleiert werden, dass der Nutzer nicht bemerkt, dass er auf einen Link mit Schadfunktion klickt.


.. class:: scriptsize

CWE-79: XSS - Beispiel 2 - XSS Typ 2 (JSP)
--------------------------------------------------------

.. code:: jsp

    <%  String eid = request.getParameter("eid");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emp where id="+eid);
        if (rs != null) {
          rs.next();
          String name = rs.getString("name");
        }
    %>

    Employee Name: <%= name %>

.. container:: post-lecture-exercise-solution

    - Problem: Falls der Nutzer in der Lage war seinen Namen selber zu wählen und beim Anlegen keine ausreichenden Prüfungen stattgefunden haben, ist ggf. ein XSS Angriff möglich. 
    - Weiteres Problem : In dem Beispiel wird der Parameter :code:`eid` nicht validiert. Der Angreifer kann beliebige SQL-Statements ausführen. 


.. class:: scriptsize

CWE-79: XSS - Beispiel 3 - XSS Typ 2 (PHP)
--------------------------------------------------------

.. code:: php

    $username = mysql_real_escape_string($username);
    $fullName = mysql_real_escape_string($fullName);
    $query = sprintf('Insert Into users (username,password) Values ("%s","%s","%s")', 
                     $username, 
                     crypt($password),
                     $fullName) ;
    mysql_query($query);
    ...

.. container:: post-lecture-exercise-solution

    - Problem: Hier wird zwar die Eingabe validiert (mysql_real_escape_string) aber *nur* in Hinblick auf SQL Injections! Der Angreifer kann so einen Nutzer anlegen, der HTML code enthält.



CWE-79: Improper Neutralization of Input During Web Page Generation - Abhilfemaßnahmen und Erkennung
-------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs
- Verringerung der Angriffsfläche mit dem Ziel möglichst wenig Daten in Cookies etc. zu speichern.
- Prüfung dass alle Client-seitigen Prüfungen auch Server-seitig vorgenommen werden.
- Prüfe jeden Input.
- Verwendung von HttpOnly Cookies (d.h. Cookies, die nicht über JavaScript ausgelesen werden können)
- Statische Analyse Werkzeuge
- Beherzigen von Best Practices (`XSS Prevention Cheat Sheet <https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html>`__)



.. No 3 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-89: Improper Neutralization of Special Elements used in an SQL Command (*SQL Injection*)
----------------------------------------------------------------------------------------------

CWE-89: Improper Neutralization of Special Elements used in an SQL Command 
----------------------------------------------------------------------------

:Kurzbeschreibung: Ein SQL-Befehl wird ganz oder teilweise unter Verwendung extern beeinflusster Eingaben von einer vorgelagerten Komponente erzeugt, bereinigt aber spezielle Elemente nicht oder falsch, die den beabsichtigten SQL-Befehl verändern könnten, wenn er an eine nachgelagerte Komponente gesendet wird.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Technologie: Datenbanken
:Betrifft: Zugriffskontrolle, Vertraulichkeit, Integrität



.. class:: scriptsize

CWE-89: SQL Injection - Beispiel 1 (MS SQl)
--------------------------------------------------------

.. code:: sql

    SELECT ITEM,PRICE FROM PRODUCT WHERE ITEM_CATEGORY='$user_input' ORDER BY PRICE

.. admonition:: Hintergrund
    :class: margin-top-2em

    MS SQL hat eine eingebaute Funktion, die es erlaubt Shell Befehle auszuführen. Diese Funktion kann auch in einem SQL Statement verwendet werden.


.. container:: post-lecture-exercise-solution   

    - Problem: Sollte der Nutzername :code:`'; exec master..xp_cmdshell 'dir' --` sein, dann wird das entsprechende Kommando ausgeführt.


.. class:: scriptsize

CWE-89: SQL Injection - Beispiel 2 (PHP)
--------------------------------------------------------

.. code:: php

    $id = $_COOKIE["mid"];
    mysql_query("SELECT MessageID, Subject FROM messages WHERE MessageID = '$id'");


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`$id`, welcher aus einem Cookie ausgelesen wird, wird nicht validiert. Auch wenn Cookies nicht trivial von einem Nutzer bzw. Angreifer manipuliert werden können, so ist es dennoch möglich. Der Angreifer kann so beliebige SQL Statements ausführen. Deswegen gilt: *Alle* Eingaben müssen validiert werden.
    - 

CWE-89: Improper Neutralization of Special Elements used in an SQL Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs.
- Verwendung von *Prepared Statements*.
- Datenbank nur mit den notwendigen Rechten betreiben (*Principle of Least Privilege*)
- Sollte es notwendig sein einen dynamischen SQL Befehl zu erstellen, dann sollten geprüfte Escapefunktionen verwendet werden.
- Statische Analyse Werkzeuge
- ggf. Application-level Firewall einsetzen




.. No 4 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-416: Use After Free (UAF)
----------------------------------------------------------------------------------------------

CWE-416: Use After Free 
----------------------------------------------------------------------------

:Kurzbeschreibung: Referenzierung von Speicher nach der Freigabe kann dazu führen, dass ein Programm abstürzt, unerwartete Werte verwendet oder Code ausführt.

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Programmiersprachen: C, C++
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität



.. class:: scriptsize

CWE-416: Use After Free - Triviales Beispiel
----------------------------------------------------------------------------

.. code:: C

    char* ptr = (char*)malloc (SIZE);
    if (err) {
      abrt = 1;
      free(ptr);
    }
    ...
    if (abrt) {
      logError("operation aborted before commit", ptr); // Use of ptr after free
    }

.. admonition:: Hinweis
    :class: margin-top-2em

    Ziel ist es im Allgemeinen eine Referenz auf einen interessanten Speicherbereich zu erhalten, der bereits freigegeben wurde und dann den Inhalt dieses Speicherbereichs auszulesen bzw. zu manipulieren, um die nächste Verwendung zu kontrollieren.


.. class:: scriptsize

CWE-416: Use After Free - Beispiel
----------------------------------------------------------------------------

.. container:: two-columns

    .. container:: column

        .. code:: C

            #include <stdlib.h>
            #include <stdio.h>
            #include <string.h>
            #define BUFSIZER1 512
            int main(int argc, char **argv) {
              char *buf1R1, *buf2R1, *buf2R2;
              buf1R1 = (char *) malloc(BUFSIZER1);
              buf2R1 = (char *) malloc(BUFSIZER1);
              printf("buf2R1 -> %p\n",buf2R1); 
              free(buf2R1);
              buf2R2 = (char *) malloc(BUFSIZER1);
              strncpy(buf2R1, argv[1], BUFSIZER1-1);
              printf("[FREED]   %p\n",buf2R1);
              printf("buf2R2 -> %p\n",buf2R2);
              printf("buf2R2  = %s\n",buf2R2);
              free(buf1R1);
              free(buf2R2);
            }

    .. container:: column

        **Fragen**:

        Wird dieses Program bis zum Ende laufen oder abstürzen? 
        
        Welche Ausgabe erzeugt das Programm?

        Ist die Ausgabe bei jedem Lauf gleich?

.. container:: post-lecture-exercise-solution   

    Das Programm wird (immer) bis zum Ende laufen!

    Ausgabe - 1. Lauf:

    .. code:: text

        buf2R1 -> 0xaaaabc1fc4b0
        [FREED]   0xaaaabc1fc4b0
        buf2R2 -> 0xaaaabc1fc4b0
        buf2R2  = Test

    Ausgabe - 2. Lauf:

    .. code:: text

        buf2R1 -> 0xaaaad5de54b0
        [FREED]   0xaaaad5de54b0
        buf2R2 -> 0xaaaad5de54b0
        buf2R2  = Test


    Der Inhalt von :code:`buf2R2` ist :code:`Test`, obwohl dort nie explizit etwas hinkopiert wurde. Die Ausgabe ist bei jedem Lauf anders, da wir Position-Independent-Code haben und der Kernel ASLR verwendet.

    Die Ausgabe wird bei jedem Lauf gleich, wenn man beides explizit unterbindet.

    .. code:: bash
    
        gcc uaf.c -fno-stack-protector -D_FORTIFY_SOURCE=0 -no-pie -fno-pic
        echo 0 | sudo tee /proc/sys/kernel/randomize_va_space
    
        $ ./a.out Test
        buf2R1 -> 0x4214b0
        [FREED]   0x4214b0
        buf2R2 -> 0x4214b0
        buf2R2  = Test
        $ ./a.out Test
        buf2R1 -> 0x4214b0
        [FREED]   0x4214b0
        buf2R2 -> 0x4214b0
        buf2R2  = Test



.. class:: scriptsize

CWE-416: CVE-2006-4997 IP over ATM clip_mkip dereference freed pointer (Linux Kernel)
---------------------------------------------------------------------------------------


.. code:: c

   // clip_mkip (clip.c):
      198 static void clip_push(struct atm_vcc *vcc,struct sk_buff *skb) {
      ...
      234         memset(ATM_SKB(skb), 0, sizeof(struct atm_skb_data));
      235         netif_rx(skb);
      236 }
      ...
      510         clip_push(vcc,skb);
      511         PRIV(skb->dev)->stats.rx_packets--;
      512         PRIV(skb->dev)->stats.rx_bytes -= len;

   // netif_rx (dev.c):
      1392 int netif_rx(struct sk_buff *skb) {
      ...
      1428        kfree_skb(skb);	//drop skb
      1429        return NET_RX_DROP;

.. container:: post-lecture-exercise-solution   

    - Problem: In Zeile 511 wird auf den Speicherbereich von :code:`skb->dev` zugegriffen, obwohl dieser bereits freigegeben wurde in netif_rx in Zeile 1428.


CWE-416: Use After Free - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------------

.. class:: incremental

- Wahl einer sicheren Programmiersprache (z.B. RUST)
- explizites :code:`NULL` setzen, nachdem der Speicherbereich freigegeben wurde 
- Fuzzing
- Statische Analyse Werkzeuge

.. container:: supplemental

    Empfohlene Lektüre: `One day short of a full chain: Real world exploit chains explained <https://github.blog/2021-03-24-real-world-exploit-chains-explained/>`__ (In Teil 1 wird eine UAF Schwachstelle genutzt.)



.. No 5 in CWE Top 2023

.. class:: new-subsection transition-move-to-top
    
CWE-78: Improper Neutralization of Special Elements used in an OS Command (*OS Command Injection*)
----------------------------------------------------------------------------------------------------------


CWE-78: Improper Neutralization of Special Elements used in an OS Command
----------------------------------------------------------------------------

:Kurzbeschreibung: Alles oder zumindest ein Teil eines Betriebssystembefehls hängt von extern beeinflussten Eingaben ab. Es erfolgt jedoch keine Bereinigung spezieller Elemente, die den beabsichtigten Betriebssystembefehl verändern könnten.

.. The product constructs all or part of an OS command using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify the intended OS command when it is sent to a downstream component.  

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität
:Arten:
    1. Ein bestimmtes Program wird ausgeführt und die Nutzerdaten werden als Parameter übergeben.
    2. Die Anwendung bestimmt basierend auf den Nutzerdaten welches Program mit welchen Parametern ausgeführt wird.


.. class:: scriptsize

CWE-78: Improper Neutralization of Special Elements used in an OS Command - Beispiel (Java)
-------------------------------------------------------------------------------------------

.. code:: java

    ...
    String btype = request.getParameter("backuptype");
    String cmd = new String("cmd.exe /K \"
    c:\\util\\rmanDB.bat "
    +btype+
    "&&c:\\utl\\cleanup.bat\"")

    System.Runtime.getRuntime().exec(cmd);
    ...


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`btype` wird nicht validiert und dewegen kann der Angreifer  beliebige Befehle ausführen, da die Shell (:code:`cmd.exe``) mehrere Befehle, die mit :code:`&&` verknüpft sind hintereinander ausführt.


CWE-78: Improper Neutralization of Special Elements used in an OS Command - Abhilfemaßnahmen und Erkennung
--------------------------------------------------------------------------------------------------------------

.. class:: incremental

- Verwendung von geprüften/sicheren APIs.
- Anwendung bzw. Befehl nur mit den notwendigen Rechten betreiben (*Principle of Least Privilege*) bzw. in einer Sandbox ausführen.
- Statische Analyse Werkzeuge
- Dynammische Analyse in Kombination mit Fuzzing
- Manuelle Code Reviews/Statische Analyse
- ggf. Application-level Firewall einsetzen





.. No 6 in CWE Top 2023

.. class:: new-subsection transition-move-to-top
    
CWE-20: Improper Input Validation
-------------------------------------------


CWE-20: Improper Input Validation
-------------------------------------------


:Kurzbeschreibung:  Empfangene Eingaben oder Daten werden nicht nicht oder falsch validiert in Hinblick darauf, dass die Eingaben die Eigenschaften haben, die für eine sichere und korrekte Verarbeitung der Daten erforderlich sind.   

.. The product receives input or data, but it does not validate or incorrectly validates that the input has the properties that are required to process the data safely and correctly.   

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Verfügbarkeit, Vertraulichkeit, Integrität
:Anwendungsbereiche:
    - Rohdaten - Strings, Zahlen, Parameter, Dateiinhalte, etc.
    - Metadaten - Information über die Rohdaten, wie zum Beispiel *Header* oder Größe


CWE-20: Improper Input Validation - zu verifizierende Werte und Eigenschaften
-------------------------------------------------------------------------------

.. class:: incremental smaller

- **Größen** wie Größe, Länge, Häufigkeit, Preis, Rate, Anzahl der Vorgänge, Zeit usw.
- **implizite oder abgeleitete Größen**, wie z. B. die tatsächliche Größe einer Datei anstelle einer angegebenen Größe
- **Indizes**, Offsets oder Positionen in komplexeren Datenstrukturen
- **Schlüssel** von Hashtabellen, assoziativen Feldern usw.
- **syntaktische Korrektheit** - Übereinstimmung mit der erwarteten Syntax
- Bestimmung des **tatsächlichen Typs der Eingabe** (oder das, was die Eingabe zu sein scheint)
- **Konsistenz** zwischen den Rohdaten und Metadaten, zwischen Referenzen usw.
- **semantische Korrektheit** bzw. Konformität mit domänenspezifischen Regeln, z. B. Geschäftslogik
- **Authentizität** von z. B. kryptografischen Signaturen 



.. class:: center-elements-on-slide

\ 
-------------------------------------------------------------------------------

.. admonition:: Improper Input Validation vs. Injection
    
    Ein Name wie ``O'Reily`` stellt ein Problem dar, wenn er in ein SQL Statement eingefügt wird, sollte jedoch von der Anwendung verarbeitet werden können und die Eingabevalidierung passieren.

    Die Validierung muss immer in Hinblick auf den Kontext erfolgen.


.. class:: scriptsize

CWE-20: Improper Input Validation - Beispiel partielle Validierung
---------------------------------------------------------------------

C:

.. code:: c

    #define MAX_DIM 100   
    int m,n, error; /* m,n = board dimensions */
    board_square_t *board;
    printf("Please specify the board height: \n");
    error = scanf("%d", &m);
    if ( EOF == error ) die("No integer passed!\n");
    printf("Please specify the board width: \n");
    error = scanf("%d", &n);
    if ( EOF == error ) die("No integer passed!\n");
    if ( m > MAX_DIM || n > MAX_DIM ) die("Value too large!\n");

    board = (board_square_t*) malloc( m * n * sizeof(board_square_t));
    ...

.. admonition:: Warnung
    :class: incremental margin-top-1em

    Ein vergleichbares Problem ist auch in sicheren Programmiersprachen möglich.

.. container:: post-lecture-exercise-solution   

    - Problem: n und m werden nicht vollständig validiert. Sind die Werte negativ, dann wird ggf. sehr viel Speicher alloziiert oder das Programm stürzt ab. 

    


CWE-20: Improper Input Validation - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- (begrenzt) Statische Analyse Werkzeuge
- Manuelle statische Analyse insbesondere in Hinblick auf die zugrundeliegende Semantik
- Dynamische Analyse mit Fuzzing





.. No 7 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-125: Out-of-bounds Read
-------------------------------------------



CWE-125: Out-of-bounds Read
-------------------------------------------


:Kurzbeschreibung: Daten vor oder nach einem Puffer werden gelesen.

.. The product reads data past the end, or before the beginning, of the intended buffer. 

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Programmiersprachen: C, C++
:Betrifft: Vertraulichkeit
:Auswirkungen: Umgehung von Schutzmaßnahmen; Lesen von Speicher

.. container:: supplemental

    Die Ausnutzung dieser Schwachstelle ist häufig schwierig, da nicht immer bekannt ist welche und wieviele Daten gelesen werden können. Es kann allerdings möglich sein Speicheradressen auszulesen. Dies kann ggf. genutzt werden, um Mechanismen wie ASLR zu umgehen.


.. class:: scriptsize

CWE-125: Out-of-bounds Read - Beispiel: partielle Validierung
-------------------------------------------------------------

C:

.. code:: C

    int getValueFromArray(int *array, int len, int index) {
      int value;

      // check that the array index is less than the maximum length of the array
      if (index < len) {
        // get the value at the specified index of the array
        value = array[index];
      }
      // if array index is invalid then output error message
      // and return value indicating error
      else {
        printf("Value is: %d\n", array[index]);
        value = -1;
      }
      return value;
    }


.. container:: post-lecture-exercise-solution   

    - Problem: Der Wert von :code:`index` wird nicht gegen zu kleine Werte validiert. Der Angreifer kann so beliebige Speicherbereiche auslesen.


CWE-125: Out-of-bounds Read - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- eine sichere Programmiersprache verwenden
- Fuzzing
- Statische Analyse Werkzeuge welche Kontroll- und Datenflussanalyse durchführen



.. No 8 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-22: Improper Limitation of a Pathname to a Restricted Directory (*Path Traversal*)
-------------------------------------------------------------------------------------------


CWE-22: Improper Limitation of a Pathname to a Restricted Directory
----------------------------------------------------------------------------


:Kurzbeschreibung:  Externe Eingaben werden für die Konstruktion eines Pfadnamens verwendet, der eine Datei oder ein Verzeichnis identifizieren soll, das sich unterhalb eines eingeschränkten übergeordneten Verzeichnisses befindet. Eine Bereinigung spezieller Elemente innerhalb des Pfadnamens erfolgt jedoch nicht ordnungsgemäß, was dazu führen kann, dass der Pfadname zu einem Ort außerhalb des eingeschränkten Verzeichnisses aufgelöst wird. 

.. The product uses external input to construct a pathname that is intended to identify a file or directory that is located underneath a restricted parent directory, but the product does not properly neutralize special elements within the pathname that can cause the pathname to resolve to a location that is outside of the restricted directory. 

:Wahrscheinlichkeit des Missbrauchs: Hoch
:Betrifft: Vertraulichkeit, Integrität, Verfügbarkeit


.. class:: scriptsize

CWE-22: Path Traversal - Beispiel: fehlende Validierung
--------------------------------------------------------

PHP:

.. code:: php

    <?php
    $file = $_GET['file'];
    include("/home/www-data/$file");
    ?>

.. container:: post-lecture-exercise-solution

    - Problem: Der Wert von :code:`file` wird nicht validiert. Der Angreifer kann so beliebige Dateien auslesen.


.. class:: scriptsize

CWE-22: Path Traversal - Beispiel: partielle Validierung
--------------------------------------------------------

Perl:

.. code:: Perl

    my $Username = GetUntrustedInput();
    $Username =~ s/\.\.\///;                # Remove ../
    my $filename = "/home/user/" . $Username;
    ReadAndSendFile($filename);

.. container:: incremental margin-top-2em

    Java: 

    .. code:: Java

        String path = getInputPath();
        if (path.startsWith("/safe_dir/")) {
          File f = new File(path);
          f.delete()
        }

.. container:: post-lecture-exercise-solution

    - Problem im Perl Beispiel: :code:`Username` wird nur bzgl. ../ am Anfang der Zeichenkette gesäubert. Beginnt der Nutzername mit :code:`../../` dann kann der Angreifer dennoch zum darüber liegenden Verzeichnis wechseln. Es fehlt im Wesentlichen das :code:`g` Flag (vgl. Reguläre Ausdrücke in ``sed``)

    - Problem im Java Beispiel: Auch in diesem Falle wird zwar der Anfang geprüft, d.h. ob der Pfad mit :code:`/safe_dir/` beginnt, aber dies verhindert nicht, dass der Pfad im Weiteren :code:`../` verwendet und der Angreifer darüber zu einem höherliegenden Verzeichnis wechseln kann.


.. class:: scriptsize

CWE-22: Path Traversal - Beispiel: verwirrende API
--------------------------------------------------------

.. container:: two-columns

    .. container:: column

        Python:

        .. code:: Python

            import os
            import sys
            def main():
            filename = sys.argv[1]
            path = os.path.join(os.getcwd(), 
                                filename)
            try:
                with open(path, 'r') as f:
                file_data = f.read()
            except FileNotFoundError as e:
                print("Error - file not found")
    
            # do something with file_data

    .. container:: column

        Dokumentation ``os.path.join``:

        .. epigraph:: 

            Join one or more path components intelligently. The return value is the concatenation of path and any members of \*paths with exactly one directory separator following each non-empty part except the last, meaning that the result will only end in a separator if the last part is empty. 
            
            If a component is an absolute path [...], all previous components are thrown away and joining continues from the absolute path component.
            
            -- `Python 3.11.7 <https://docs.python.org/3.11/library/os.path.html#os.path.join>`__



CWE-22: Path Traversal - Abhilfemaßnahmen und Erkennung
----------------------------------------------------------------------

.. class:: incremental

- Eingabe vollständig validieren; zum Beispiel über kanonische Pfade
- Sandboxen
- Umgebung härten
- Bei Fehlerausgaben darauf achten, dass keine Informationen über das Dateisystem preisgegeben werden
- den Code mit minimalen Rechten ausführen


.. No 9 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-352: Cross-Site Request Forgery (*CSRF*)
-------------------------------------------------------------------------------------------


CWE-352: Cross-Site Request Forgery (CSRF)
----------------------------------------------------------------------------


:Kurze Beschreibung: 

    Die Webanwendung prüft nicht bzw. kann nicht prüfen, ob eine Anfrage absichtlich von dem Benutzer gestellt wurde, von dessen Browser sie übermittelt wurde.

    D.h. eine CSRF Schwachstelle nutzt das Vertrauen aus, das eine Webseite in den Browser eines Nutzers hat. Bei einem CSRF-Angriff wird ein legitimer Nutzer von einem Angreifer dazu gebracht, ohne sein Wissen eine Anfrage zu übermitteln, die er nicht beabsichtigt hat und auch nicht bemerkt.

:Missbrauchswahrscheinlichkeit: Mittel
:Auswirkung: Hängt von den Nutzerrechten ab
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit


.. class:: scriptsize

CWE-352: Cross-Site Request Forgery (CSRF) - ursprüngliche Form
------------------------------------------------------------------


.. image:: csrf.svg
    :alt: Cross-Site Request Forgery (CSRF) - ursprüngliche Form
    :height: 1050px



CWE-352: Cross-Site Request Forgery (CSRF) in 2023
----------------------------------------------------------

.. epigraph::   

    Fiber ist ein von Express inspiriertes Web-Framework, das in Go geschrieben wurde. In der Anwendung wurde eine Cross-Site Request Forgery (CSRF)-Schwachstelle entdeckt, die es einem Angreifer ermöglicht, beliebige Werte zu injizieren und bösartige Anfragen im Namen eines Benutzers zu fälschen. Diese Schwachstelle kann es einem Angreifer ermöglichen, beliebige Werte ohne Authentifizierung einzuschleusen oder verschiedene böswillige Aktionen im Namen eines authentifizierten Benutzers durchzuführen, wodurch die Sicherheit und Integrität der Anwendung gefährdet werden kann. Die Schwachstelle wird durch eine unsachgemäße Validierung und Durchsetzung von CSRF-Tokens innerhalb der Anwendung verursacht.

    -- `CVE-2023-45128 <https://nvd.nist.gov/vuln/detail/CVE-2023-45128>`__ (übersetzt mit DeepL)

.. container:: small margin-top-1em

    Identifizierte Schwachstellen: *CWE-20* Improper Input Validation, *CWE-807* Reliance on Untrusted Inputs in a Security Decision, *CWE-565* Reliance on Cookies without Validation and Integrity Checking, **CWE-352** Cross-Site Request Forgery


CWE-352: Cross-Site Request Forgery (CSRF) in 2023
----------------------------------------------------------

Standardtechniken, die CSRF verhindern *sollen*:

.. class:: incremental

- Same-site Cookies (für Authentifizierung)
- CSRF-Tokens, wenn diese die folgenden Eigenschaften haben:
  
  - Einmalig pro Nutzersession
  - Geheim
  - nicht vorhersagbar (z.B. eine sehr große, sicher erzeugte Zufallszahl)
 
- Validierung des Referer-Header 
- Custom Request Header, da diese nur vom JavaScript Code gesetzt werden können, der den gleichen Ursprung hat (siehe *Same Origin Policy* (SOP)).

.. container:: incremental small foundations

    Auch diese Techniken lassen sich ggf. (alle zusammen) aushebeln, `wenn die Anwendung weitere Schwachstellen aufweist <https://portswigger.net/web-security/csrf>`__. So gibt/gab es Anwendungen, die Anfragen, die nur über ein POST request gestellt werden sollten, auch bei einem GET akzeptiert haben. 


.. container:: supplemental

    In allen Browsern wird in der Zwischenzeit für Cookies die Same-site Policy angewandt mit dem Wert :code:`Lax`. Dieser Wert hat zur Folge, dass Cookies nur dann gesendet werden, wenn der Nutzer explizit auf einen Link klickt oder sich innerhalb der selben Seite befindet.
    


.. No 10 in CWE Top 2023

.. class:: new-subsection transition-move-to-top

CWE-434: Unrestricted Upload of File with Dangerous Type
-------------------------------------------------------------------------------------------



CWE-434: Unrestricted Upload of File with Dangerous Type
----------------------------------------------------------------------------

:Kurze Beschreibung: 

    Es ist möglich potentiell gefährliche Dateien hochzuladen bzw. zu transferieren, die von der Anwendung automatisch im Kontext der Anwendung verarbeitet werden.

:Missbrauchswahrscheinlichkeit: Mittel
:Auswirkung: Bis hin zur Ausführung von beliebigen Befehlen
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit



.. class:: scriptsize

CWE-434: Unrestricted Upload of File with Dangerous Type - Beispiel
----------------------------------------------------------------------------

HTML:

.. code:: HTML

    <form action="upload_picture.php" method="post" enctype="multipart/form-data">
        Choose a file to upload:
        <input type="file" name="filename"/>
        <br/>
        <input type="submit" name="submit" value="Submit"/>
    </form>


PHP:

.. code:: PHP

    // Define the target location where the picture being
    // uploaded is going to be saved.
    $target = "pictures/" . basename($_FILES['uploadedfile']['name']);

    // Move the uploaded file to the new location.
    move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target)


.. container:: post-lecture-exercise-solution

   Problem: Die Datei :code:`$_FILES['uploadedfile']['name']` wird nicht validiert. Sollte der Nutzer statt einem Bild eine PHP Datei hochladen, dann wird diese beim einem späteren Aufruf im Kontext der Anwendung ausgeführt.
  
   Eine einfache Möglichkeit die Schwachstelle auszunutzen wäre die Datei:

    .. code:: PHP

        // malicious.php
   
        <?php
        system($_GET['cmd']);
        ?>

    Mit einer Anfrage wie:

        ``...malicious.php?cmd=ls%20-l``


CWE-434: Unrestricted Upload of File with Dangerous Type - Abhilfemaßnahmen und Erkennung
-------------------------------------------------------------------------------------------

- Beim Speichern von Dateien niemals den ursprünglichen Dateinamen verwenden sondern einen vom Server generierten.
- Speicher die Daten nicht im Kontext der Webanwendung sondern außerhalb des Webroots.
- Prüfe die Dateiendung. Prüfe den Inhalt der Datei gegen die Erwartung.
- Ausführen der Webanwendung mit minimalen Rechten.
- Sandbox.



.. No 2 in 2023 CWE Top 10 KEV Weaknesses

.. class:: new-subsection transition-move-to-top

CWE-122: Heap-based Buffer Overflow
-------------------------------------------------------------------------------------------


CWE-122: Heap-based Buffer Overflow
------------------------------------------------------


:Kurze Beschreibung: 

    Ein Pufferüberlauf, bei dem der Puffer, der überschrieben wird, auf dem Heap alloziiert wurde, was im Allgemeinen bedeutet, dass der Puffer mit einer Routine wie malloc() allloziiert wurde.

:Missbrauchswahrscheinlichkeit: Hoch
:Sprachen: C/C++
:Auswirkung: Bis hin zur Ausführung von beliebigen Befehlen
:Ausmaß: Vertraulichkeit, Integrität, Verfügbarkeit, Zugriffskontrolle


.. class:: scriptsize

CWE-122: Heap-based Buffer Overflow
-------------------------------------------------------------------

:ger-quote:`Basisbeispiel` in C:

.. code:: C

    #define BUFSIZE 256
    int main(int argc, char **argv) {
        char *buf;
        buf = (char *)malloc(sizeof(char)*BUFSIZE);
        strcpy(buf, argv[1]);
    }


.. container:: post-lecture-exercise-solution

    Problem: Die Größe von buf ist unabhängig von der Größe von :code:`argv[1]`. 



CWE-122: Heap-based Buffer Overflow - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------

- Verwendung einer sicheren Programmiersprache
- Verwendung von sicheren APIs
- Kompilierung unter Verwendung entsprechender Schutzmechanismen (Position-Independent Executables (PIE), Canaries, ...)
- Härtung der Umgebung (z.B. ASLR)
- Statische Analyse Werkzeuge
- Fuzzing




.. No 6 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top


CWE-502: Deserialization of Untrusted Data
--------------------------------------------------------------------------------


CWE-502: Deserialization of Untrusted Data
------------------------------------------------------


:Kurze Beschreibung: 

    Nicht vertrauenswürdige Daten werden deserialisiert ohne - *je nach Bibliothek notwendige vorhergehende* - Prüfung, dass die Daten die erwarteten Eigenschaften haben.

:Missbrauchswahrscheinlichkeit: Mittel
:Sprachen: Java, Ruby, Python, PHP, JavaScript, ...
:Ausmaß: Insbesondere: Integrität und Verfügbarkeit (DoS); weitere Effekte sind vom Kontext abhängig.

:Alternative Begriffe: (Un-)Marshalling, (Un-)Picking


.. container:: supplemental

    Bei der Serialisierung werden programminterne Objekte so verpackt, dass die Daten extern gespeichert und/oder übertragen werden können. Die Deserialisierung kehrt diesen Prozess um.




.. class:: scriptsize

CWE-502: Deserialization of Untrusted Data - Beispiel
-------------------------------------------------------------------

Java

.. code:: Java

    File file = new File("object.obj");
    try ( FileInputStream fin = new FileInputStream(file);
          ObjectInputStream oin = new ObjectInputStream(fin)
        ) {
        javax.swing.JButton button = (javax.swing.JButton) oin.readObject();
        ...
    } 

.. container:: supplemental

    In diesem Beispiel wird ein Objekt aus einer Datei gelesen und in eine Variable vom Typ :code:`javax.swing.JButton` geschrieben. Der Typ des Objekts wird nicht geprüft. Es ist möglich, dass die Datei ein Objekt enthält, welches vom Typ :code:`javax.swing.JButton` ist, aber nicht die Eigenschaften hat, die ein Button haben sollte. In diesem Fall wird keine Exception geworfen, aber das Objekt kann nicht wie erwartet verwendet werden bzw. es kommt zur Ausführung von beliebigem Code.


.. class:: scriptsize

CWE-502: Deserialization of Untrusted Data - Beispiel
-------------------------------------------------------------------

Python

.. code:: Python

    
    class ExampleProtocol(protocol.Protocol):

        def dataReceived(self, data):
            # ... parse the incoming data and 
            # after receiving headers, call confirmAuth() to authenticate

        def confirmAuth(self, headers):
            try:
                token = cPickle.loads(base64.b64decode(headers['AuthToken']))
                if not check_hmac(token['signature'], token['data'], getSecretKey()):
                    raise AuthFail
                self.secure_data = token['data']
            except:
                raise AuthFail
    


.. container:: post-lecture-exercise-solution

    In diesem Fall könnte man der Funktion ein Objekt unterschieben, dass bei der Deserialisierung beliebigen Code ausführt (zum Beispiel um einen weitere Prozess zu starten.).

    Dieses Problem wird in der Dokumentation  auch explizit erwähnt:

    .. epigraph::

        Warning The pickle module is not secure. Only unpickle data you trust.
        It is possible to construct malicious pickle data which will execute arbitrary code during unpickling. Never unpickle data that could have come from an untrusted source, or that could have been tampered with.

        -- `Python 3.12 <https://docs.python.org/3/library/pickle.html>`__

    

CWE-502: Deserialization of Untrusted Data - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------


- ggf. Einsatz von Signaturen, um sicherzustellen, dass der serialisierte Code nicht manipuliert wurde 
- Serialisiere nur Daten, die auch wirklich serialisiert werden müssen
- Verwendung von sicheren Formaten (z.B. JSON)
- statische Analyse

.. class:: supplemental

    Empfohlene Lektüre: `Deserialization Vulnerabilities <https://portswigger.net/web-security/deserialization>`__




.. No 7 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top



CWE-918: Server-Side Request Forgery (SSRF)
--------------------------------------------------------------------------------


CWE-918: Server-Side Request Forgery 
------------------------------------------------------


:Kurze Beschreibung: 
    Der Webserver erhält eine URL oder eine ähnliche Anfrage und ruft den Inhalt dieser URL ab, stellt aber nicht sicher, dass die Anfrage an das erwartete Ziel gesendet wird.

:Technologien: Webserver
:Ausmaß: Vetraulichkeit, Integrität 



CWE-918: Server-Side Request Forgery 
------------------------------------------------------

.. image:: ssrf.svg
    :alt: Server-Side Request Forgery (SSRF)
    :width: 1800px



.. class:: scriptsize

CWE-918: Server-Side Request Forgery - Beispiel: CVE-2002-1484
-----------------------------------------------------------------

:Beschreibung:  
    Wenn der DB4Web-Server so konfiguriert ist, dass er ausführliche Debug-Meldungen verwendet, können entfernte Angreifer DB4Web als Proxy verwenden und über eine Anfrage an eine URL, die die Ziel-IP-Adresse und den Port angibt, TCP-Verbindungen zu anderen Systemen (Port-Scan) versuchen, was einen Verbindungsstatus in der resultierenden Fehlermeldung erzeugt.
    
.. class:: incremental

:PoC: http://127.0.0.1/DB4Web/172.31.93.30:22/foo

.. class:: incremental

:Workaround:
    Der Hersteller betrachtet die Funktionalität nicht als Fehler, sondern als nützliches Feature für Entwickler. Um die Ausnutzung dieses Features zu verhindern, muss die Standardfehlerseite durch eine benutzerdefinierte Fehlerseite ersetzt werden.


.. class:: scriptsize

CWE-918: Server-Side Request Forgery - Beispiel: NodeJS Unicode Handling Fehler [#]_
---------------------------------------------------------------------------------------

JavaScript:

.. code:: JavaScript

    var base = "http://orange.tw/sandbox/";
    var path = req.query.path;
    if (path.indexOf("..") == -1) { // check for no directory traversal
        http.get(base + path, callback);
    }

.. container:: incremental

    Beispiel URL (*U+FF2E Full width Latin capital letter N*):

    .. code:: restructuredtext
        :class: incremental

          http://orange.tw/sandbox/ＮＮ/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/\xFF\x2E\xFF\x2E/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/\x2E\x2E/passwd

    .. code:: restructuredtext
        :class: incremental

        ≙ http://orange.tw/sandbox/../passwd

    
.. [#] `Exploiting URL Parsers <https://www.blackhat.com/docs/us-17/thursday/us-17-Tsai-A-New-Era-Of-SSRF-Exploiting-URL-Parser-In-Trending-Programming-Languages.pdf>`__


.. class:: scriptsize

CWE-918: Server-Side Request Forgery - Beispiel: URL Parser vs. Abfrage der URL
---------------------------------------------------------------------------------

PHP (> 7.0.13):

.. code:: php

    $url = 'http://foo@127.0.0.1⬜@google.com:11211/'; // ⬜ is "just" a space
    $parsed = parse_url($url);
    var_dump($parsed[host]); // string(10) "google.com"
    var_dump($parsed[port]); // int(11211)
    curl($url);

Ergebnis:

.. container:: incremental

    ``curl`` fragt die URL ``127.0.0.1:11211`` abfragen.

CWE-918: Server-Side Request Forgery - Variante: Blind SSRF
-----------------------------------------------------------------------------

Bei *Blind SSRF*-Schwachstellen werden auch Back-End-HTTP-Anfragen an eine bereitgestellte URL gestellt, die Antwort der Back-End-Anfrage jedoch nicht an die Front-End-Antwort der Anwendung zurückgegeben.

.. container:: supplemental

    Empfohlene Lektüre: `Blind Server-Side Request Forgery (SSRF) <https://portswigger.net/web-security/ssrf/blind>`__


CWE-918: Server-Side Request Forgery - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------

- keine (Wieder-)Verwendung der Eingabe URL
- sichere APIs
- statische Analyse (insbesondere Datenflußanalysen)
- Behandlung von Zugriffen von lokalen Maschinen sollte mit der gleichen sorgfalt überprüft werden wie Zugriffe von externen Maschinen; andernfalls können kritische SSRF Angriffe durchgeführt werden
- Firewall/Network Policy um Zugriff auf interne Systeme zu verhindern



.. No 8 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top


CWE-843: Access of Resource Using Incompatible Type (Type Confusion)
------------------------------------------------------------------------------


CWE-843: Access of Resource Using Incompatible Type (Type Confusion)
----------------------------------------------------------------------

:Beschreibung: 

        Eine Anwendung initialisiert eine Ressource mit einem bestimmten Typ (z.B. Zeiger (:eng:`Pointer`), Objekt, etc.). Später wird auf die Ressource (Variable) dann mit einem anderen Typ zugegriffen. 

:Sprachen: insbesondere (aber nicht ausschließlich) C/C++; im Prinzip in jeder Sprache, die automatische Typkonvertierungen durchführt. 
:Ausmaß: Integrität, Verfügbarkeit, Vertraulichkeit


.. class:: scriptsize

CWE-843: Access of Resource Using Incompatible Type - Beispiel in C
----------------------------------------------------------------------

.. code:: c

    #define NAME_TYPE 1
    #define ID_TYPE 2
    struct MessageBuffer {
        int msgType;
        union {
            char *name;
            int nameID;
    };  };
    int main (int argc, char **argv) {
        struct MessageBuffer buf;
        char *defaultMessage = "Hello World";
        buf.msgType = NAME_TYPE;
        buf.name = defaultMessage;              // printf("*buf.name %p", buf.name);
        buf.nameID = (int)(defaultMessage + 1); // printf("*buf.name %p", buf.name);
        if (buf.msgType == NAME_TYPE) printf("%s\n", buf.name);
        else                          printf("ID %d\n", buf.nameID);
    }

.. container:: post-lecture-exercise-solution

    Der Zugriff auf ``buf.nameId`` manipuliert den Zeiger auf ``buf.name``. Dieser zeigt nun auf die Speicherstelle ``defaultMessage +1`` weswegen der nachfolgende Zugriff ``buf.name`` :ger-quote:`nur` noch ``ello World`` ausgibt und nicht mehr ``Hello World``.



.. class:: scriptsize

CWE-843: Access of Resource Using Incompatible Type - Beispiel in Perl
------------------------------------------------------------------------

.. code:: perl

    my $UserPrivilegeArray = ["user", "user", "admin", "user"];
    my $userID = get_current_user_ID();
    if ($UserPrivilegeArray eq "user") {
        print "Regular user!\n";
    }
    else {
        print "Admin!\n";
    }

    print "\$UserPrivilegeArray = $UserPrivilegeArray\n";


.. container:: post-lecture-exercise-solution

    In der Zeile: :code:`if ($UserPrivilegeArray eq "user")` wurde vergesen die Indizierung (:code:`$userID`) zu verwenden (:code:`$UserPrivilegeArray->{$userID}`). Es wird also das Array als Ganzes mit dem String ``user`` verglichen und der Vergleich ist immer ``falsch (:eng:`false`)``.



.. No 10 in 2023 CWE Top 10 KEV Weaknesses https://cwe.mitre.org/top25/archive/2023/2023_kev_list.html

.. class:: new-subsection transition-move-to-top


CWE-306: Missing Authentication for Critical Function
--------------------------------------------------------------------------------


CWE-306: Missing Authentication for Critical Function
----------------------------------------------------------------------


:Beschreibung: 

    Eine Anwendung führt eine kritische Funktion aus, ohne die Identität des Nutzers zu überprüfen. Kritischer Funktionen sind solche, die entweder signifikante Ressourcen verbrauchen oder nur von privilegierten Nutzern ausgeführt werden sollten.

:Sprachen: "alle"


CWE-306: Missing Authentication for Critical Function - Abhilfemaßnahmen und Erkennung
-----------------------------------------------------------------------------------------

.. class:: incremental

- manuelle Code Reviews 
- statische Analyse (Binärcode und/oder Quellcode)
- 


.. class:: new-section

Open Worldwide Application Security Project (OWASP)
----------------------------------------------------------------------


OWASP
-----------------------------------------------------------------------

.. class:: incremental
    
- gemeinnützige Stiftung, die sich für die Verbesserung der Sicherheit von Software einsetzt
- 2001 gegründet
- weltweit tätig
- Stellt insbesondere Foren, Dokumente und Werkzeuge bereit
- Dokumente, die bei der Entwicklung sicherer Anwendungen unterstützen:

  - `OWASP Web Security Testing Guide <https://owasp.org/www-project-web-security-testing-guide/>`__
  - `OWASP Code Review Guide <https://owasp.org/www-project-code-review-guide/>`__
- Ausgewählte Projekte:
  
  .. class:: incremental

  - `OWASP Top 10 (die relevantesten Sicherheitsprobleme bei Webanwendungen) <https://owasp.org/www-project-top-ten/>`__
  - `Cheat Sheets <https://owasp.org/www-project-cheat-sheets/>`__
  - `OWASP Dependency-Track <https://owasp.org/www-project-dependency-track/>`__
  - `OWASP Web Security Testing Guide <https://owasp.org/www-project-web-security-testing-guide/>`__
  


.. class:: integrated-exercise 

Übung: Schwachstelle(n) (1)
-----------------------------------------------------------------------

.. class:: scriptsize

1. Benenne die Schwachstelle(n) entsprechend der CWEs (ohne ID).
2. Identifiziere die für die Schwachstelle(n) relevanten Zeilen im Code.
3. Gebe - falls möglich - einen Angriffsvektor an.
4. Skizziere mögliche Auswirkung der Schwachstelle(n) (z.B. Verlust der Vertraulichkeit, Integrität oder Verfügbarkeit; Umgehung der Zugriffskontrolle; beliebige Codeausführung, ...) 

.. code:: C
    :class: tiny
    
    #include <stdio.h>
    #include <string.h>
    void process(char *str) {
        char *buffer = malloc(16);
        strcpy(buffer, str);
        ...
        // ... definitively executed in the future: free(buffer);
    }
    int main(int argc, char *argv[]) {
        if (argc < 2) { printf("Usage: %s <string>\n", argv[0]); return 1; }
        process(argv[1]);
        return 0;
    }

.. container:: post-lecture-exercise-solution

    Die Länge von :code:`str` wird nicht validiert. Es kommt somit potentiel zu einem "Out-of-bounds Write" (:code:`strcpy(buffer,str)`). Ein String wäre jeder String, der länger als 16 Zeichen ist. Ein Angriffsvektor wäre z.B. ein String, der 17 Zeichen lang ist und am Ende ein :code:`\0` enthält. Die Auswirkung wäre ein Pufferüberlauf, der ggf. zur Ausführung von beliebigem Code führt.


.. class:: integrated-exercise 

Übung: Schwachstelle(n) (2)
-----------------------------------------------------------------------

.. container:: scriptsize

    Sie analysieren eine REST API die folgendes Verhalten aufweist, wenn man einem Blog einen Kommentar hinzufügen möchte:

    .. code:: HTTP
        :class: tiny

        POST /post/comment HTTP/1.1
        Host: important-website.com
        Content-Length: 100

        postId=3&comment=This+<post>+was+helpful.&name=Karl+Gustav

    Fragt man danach den Webservice nach dem Kommentar, dann erhält man folgendes zurück:

    .. code:: HTML
        :class: tiny

        <div class="comment">
            <div class="name">Karl Gustav</div>
            <div class="comment">This <post> was helpful.</div>
        </div>

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.




.. class:: integrated-exercise 

Übung: Schwachstelle(n) (3)
-----------------------------------------------------------------------


.. container:: scriptsize

    TODO

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.


.. class:: integrated-exercise 

Übung: Schwachstelle(n) (4)
-----------------------------------------------------------------------

.. note::  
    :class: tiny the-blue-background

    **URL Encoding**

    :%20: Leerzeichen

    :%22: "

    :%3C: <

    :%3E: >

    :%2F: /


.. container:: scriptsize

    Sie beobachten folgendes Verhalten einer Webseite:

    **Anfrage**

    .. code:: http

        https://my-website.com/search?
              term=This%20is%20a%20%3C%22%3Egift%3C%2F%22%3E

    **Antwort**

    .. code:: HTML

        <div class="search-result">
            <div class="title">This is a <">gift</"></div>
        </div>   

    Bewerten Sie die Schwachstelle: CWE Name, problematische Codestelle(n), möglicher Angriffsvektor und mögliche Auswirkung.


.. container:: post-lecture-exercise-solution

    Es handelt sich um eine *Reflected Cross-Site Scripting* Schwachstelle. Der Angreifer kann beliebigen Code ausführen, wenn er es schafft der angegriffenen Person den richtigen Link unterzuschieben. In diesem Fall wird der Code in der Variable :code:`term` ausgeführt. Der Angreifer könnte also z.B. folgende Anfrage stellen:

    :code:`https://my-website.com/search?term=<script>/*+Bad+stuff+here...+*/</script>``
