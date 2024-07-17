.. meta:: 
    :author: Michael Eichberg
    :keywords: "IT Sicherheit", "Reverse Engineering"
    :description lang=de: Fortgeschrittene Angewandte IT Sicherheit
    :id: lecture-security-java_reverse_engineering
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


.. class:: animated-symbol organic-red

Cybersecurity 
=====================================================

.. container::

    :Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
    :Kontakt: michael.eichberg@dhbw-mannheim.de
    :Version: 1.0

.. supplemental::

  :Folien: 
      |html-source|

      |pdf-source|
  :Fehler auf Folien melden:
      https://github.com/Delors/delors.github.io/issues



Was ist Cybersecurity?
-----------------------

.. stack::

    .. layer:: incremental

        .. epigraph::

            **Cybersecurity is the practice of protecting systems, networks, and programs from digital attacks**. These cyberattacks are usually aimed at accessing, changing, or destroying sensitive information; extorting money from users via ransomware; or interrupting normal business processes.

            -- July 4th, 2024 - `Cisco <https://www.cisco.com/c/en/us/products/security/what-is-cybersecurity.html>`__

    .. layer:: incremental

        .. epigraph::

            [...] The security precautions related to computer information and access address four major threats: **(1) theft of data**, such as that of military secrets from government computers; **(2) vandalism**, including the destruction of data by a computer virus; **(3) fraud**, such as employees at a bank channeling funds into their own accounts; and **(4) invasion of privacy**, such as the illegal accessing of protected personal financial or medical data from a large database. [...]

            -- July 4th, 2024 - `Britannica <https://www.britannica.com/technology/computer-security>`__

    .. layer:: incremental

        Das Ziel der IT-Sicherheit ist es Systeme vor:

        - Ausfall
        - Missbrauch
        - Sabotage
        - Spionage
        - Betrug und Diebstahl zu schützen


.. class:: no-title center-child-elements

Indonesia - Ransomware Angriff
-------------------------------------------------------------

.. epigraph::

    **Indonesia won’t pay an $8 million ransom after a cyberattack compromised its national data center**

    [...] The attackers have held data hostage and offered a key for access in return for the $8 million ransom, said PT Telkom Indonesia’s director of network & IT solutions, Herlan Wijanarko, without giving further details. Wijanarko said the company, in collaboration with authorities at home and abroad, is investigating and trying to break the encryption that made data inaccessible. [...]

    -- June 25th, 2024 - `AP News <https://apnews.com/article/indonesia-ransomware-attack-national-data-center-213c14c6cc69d7b66815e58478f64cee>`__



.. class:: no-title center-child-elements

Online-Betrug
-----------------

.. epigraph::

    **FAST 4.000 VERHAFTUNGEN: Interpol gelingt großer Schlag gegen Onlinebetrug**

    Die Einsatzkräfte haben nicht nur weltweit Tausende von Verdächtigen verhaftet, sondern auch Vermögenswerte im Umfang von 257 Millionen US-Dollar beschlagnahmt.

    [...] Mit einem Gesamtwert von 135 Millionen US-Dollar besteht laut Interpol mehr als die Hälfte davon aus beschlagnahmten Fiat-Währungen wie US-Dollar, Euro oder Yen. Weitere zwei Millionen Dollar liegen in Form von Kryptowährungen vor. Hinzu kommen andere Vermögenswerte wie etwa Immobilien, Luxusfahrzeuge, teurer Schmuck und andere hochwertige Gegenstände und Sammlungen im Gesamtwert von 120 Millionen US-Dollar. [...]

    --29. Juni 2024 - `Golem.de <https://www.golem.de/news/fast-4-000-verhaftungen-interpol-gelingt-grosser-schlag-gegen-onlinebetrug-2406-186568.html>`__



.. class:: no-title center-child-elements

Snailload
-------------------------------------------------------------

.. epigraph::

    **SnailLoad: Exploiting Remote Network Latency Measurements without JavaScript**

    [Side-Channel Attack to circumvent privacy.]
    
    [...] The attack setup for SnailLoad. A victim downloads data from an attacker's HTTP server while it watches a video on a video-sharing platform, e.g., YouTube. Due to the network bottleneck on the victim's side, the attacker can infer the transmitted amount of data by measuring the packet round trip time. The round trip time traces are unique per video and can be used to classify the video watched by the victim. [...]

    -- 28.6.2024 Snailload: `Paper <https://www.snailload.com/snailload.pdf>`__, `Web <https://www.snailload.com>`__





Ausgewählte Angriffe und Angriffsmethoden
-------------------------------------------------------------

.. class:: incremental

- Backdoors (:ger:`Hintertüren`)
- (Distributed-)Denial-of-service Angriffe
- Direct-access Angriffe
- Eavesdropping (:ger:`Abhören`)
- Malware
- Man-in-the-middle (MITM) Angriffe
- Social engineering (z. B. Phishing)
- Privilege escalation
- Side-Channel attacks/\ :ger:`Seitenkanalangriffe`
- Spoofing (z. B. IP-Spoofing)
- Advanced Persistent Threats (APT)




Schutzziele der IT-Sicherheit: CIA-Triade
--------------------------------------------

.. raw:: html
    :class: center-child-elements

    <style>
    .cia-triangle {
        position: relative;
        width: 400px;
        height: 400px;
        overflow: visible;
        scale: 1.5;
        transform: translate(0, 50px);

        * {
            position: absolute;
        }

        .bottom-left {
            left: 0;
           background: linear-gradient(145.98deg, rgba(255,255,255,0) 50%, var(--dhbw-dark-red) 50%, var(--dhbw-dark-red) 100%);
        }
        .bottom-right {
            right: 0;
            background: linear-gradient(213.98deg, rgba(255,255,255,0) 50%, var(--dhbw-dark-red) 50%, var(--dhbw-dark-red) 100%);
        }
        .bottom-left,
        .bottom-right {
            width: 200px;
            height: 135px;
            z-index: 1;
            bottom: 0;
            right: 0;
            box-shadow: 0 5px 3px -3px rgba(0,0,0,0.5);
        }

        .left {
            width: 200px;         
            height: 400px;
            background: linear-gradient(116.57deg, rgba(0,0,0,0) 0%, rgba(0,0,0,0) 50%, var(--dhbw-light-red) 50%, var(--dhbw-light-red) 100%);
        }
    
        .right {
            width: 200px;         
            height: 400px;
            right: 0;
            background: linear-gradient(243.43deg, rgba(0,0,0,0) 0%, rgba(0,0,0,0) 50%, var(--dhbw-red) 50%, var(--dhbw-red) 100%);
        }

        p {
            font-size: 0.75em;
        
            &.availability {
                bottom: -20%;
                left: 50%;
                transform: translate(-50%);
            }

            &.integrity {
                top: 50%;
                right: -20%;
                transform: translate(0, -50%);
            }

            &.confidentiality {
                top: 50%;
                left: -70%;
                transform: translate(0, -50%);
            }
        }
    }
    </style>

    <div class="cia-triangle incremental">
        <div class="bottom-left"></div>
        <div class="bottom-right"></div>
        <p class="availability">Availability</p>
        <div class="left incremental"><p class="confidentiality">Confidentiality</p></div>
        <div class="right incremental"><p class="integrity">Integrity</p></div>
    </div>

.. supplemental::

    Confidentiality ≘dt. Vertraulichkeit
    
    Integrity ≘dt. Integrität

    Availability ≘dt. Verfügbarkeit


.. class:: new-section transition-fade

Social-Engineering Angriffe
-------------------------------------------------------------


Eigenschaften von Social-Engineering Angriffe
-------------------------------------------------------------

.. class:: incremental with-explanations

- **sind häufig die Ursache für erfolgreiche Angriffe**

  (Der Hacker Kevin Mitnick war praktisch immer aufgrund von Social Engineering erfolgreich.)
- stellen die größte Bedrohung für die Sicherheit von IT-Systemen dar
- es wird angenommen, dass die betroffenen Personen es in vielen Fällen nicht merken :incremental:`(Beispiel: Fake Bewerbungsgespräch)`
- mittels OSINT kann die Vorbereitung von Social-Engineering Angriffen vereinfacht werden
- neue technische Möglichkeiten (z. B. KI generierte Stimmen) erweitern die Angriffsmöglichkeiten

.. container:: supplemental

    .. rubric:: Beispiel eines fortgeschrittenen Social-Engineering Angriffs

    Ein vom Angreifer bewusst eingefädeltes Bewerbungsgespräch für eine Position als Administrator könnte zum Beispiel dazu genutzt werden, um Informationen über das Zielsystem zu erhalten, die für einen Angriff nützlich sind (z. B. welche Software wird eingesetzt, wie sieht die Architektur aus, ...). In diesem Fall ist davon auszugehen, dass ein Bewerber zum Beispiel durch ein Headhunter eine gutes Angebot gemacht wird und er dann im Rahmen des Gesprächs gebeten wird eine Sicherheitsarchitektur darzustellen, die er einführen würde. Es ist dann davon auszugehen, dass er auf seine bisherige Erfahrung zurückgreift und diese darstellt und er somit die Architektur des Zielsystems offenlegt.

    .. rubric:: Neue Gefahren 

    Durch KI generierte Stimmen kann es Angreifern gelingen, z. B. durch das Vortäuschen einer Notlage einer nahestehenden Person, an Informationen zu gelangen.



Ausgewählte Social-Engineering Angriffe 
-------------------------------------------------------------

.. container:: scrollable

    .. class:: incremental

    :Phishing and Spear Phishing: 

        *Phishing* nutzt elekr. Kommunikationswege um an Informationen zu gelangen (z. B. E-Mail oder SMS). 
        
        *Spear phishing* ist Phishing, bei der der Angreifer auf eine bestimmte Zielgruppe oder Person abzielt.

    .. class:: incremental

    :Smishing: 
    
        Phishing mit Hilfe von SMS.

    .. class:: incremental

    :Vishing:

        Phishing mit Hilfe von Telefonanrufen.
        
        .. incremental::

            (Z. B. `Anrufe von Europol <https://www.europol.europa.eu/publications-events/publications/vishing-calls>`__)

    .. class:: incremental

    :Whaling:

        Phishing, dass sich gegen hochrangige und sehr ausgewählte Personen richtet (z. B. den CEO eines Unternehmens).

    .. class:: incremental

    :Pharming:

        Manipulation des DNS-Servers, um den Nutzer auf eine gefälschte Webseite zu leiten, um dann sensitive Informationen zu erlangen.

    .. class:: incremental

    :Spam / Spam over Internet messaging (SPIM):

        Unerwünschte und nicht angeforderte E-Mail-Nachrichten oder Nachrichten in sozialen Medien bzw. Instant Messaging-Diensten.

    .. class:: incremental

    :Dumpster Diving:

        Durchsuchen von :ger-quote:`Müllcontainern` nach Informationen, die für einen Angriff nützlich sein könnten.

    .. class:: incremental

    :Shoulder Surfing:

        Beobachten von Personen, die sich an einem Computer anmelden, um das Passwort zu erfahren oder die sensitive Informationen auf dem Schreibtisch liegen haben.   

    .. class:: incremental

    :Tailgating:

        Ein Angreifer nutzt die Zugangsberechtigung einer Person, um sich Zugang zu einem Gebäude zu verschaffen ohne dass die Person dies bemerkt oder gar zustimmt. 
        
        Dies kann z. B. durch Zugangsschleusen verhindert werden, die immer nur einer Person den Zugang gewähren. 

    .. class:: incremental

    :Identity Fraud:

        Identitätsdiebstahl. Der Angreifer gibt sich als jemand anderes aus, um an Informationen zu gelangen oder um eine Straftat zu begehen.

    .. class:: incremental

    :Invoice Scams:

        Versenden von Rechnungen, für Dienstleistungen und Produkte die man nicht gekauft hat (z. B. Rechnungen für Postzustellung.)

    .. class:: incremental

    :Credential Harvesting:
    
        Sammlung von Zugangsdaten, die durch Sicherheitslücken in Systemen oder durch Phishing erlangt wurden. 

    .. class:: incremental

    :Hoax:
        Eine bewusste Falschmeldung, die Menschen dazu veranlasst etwas falsches zu glauben. 

    .. class:: incremental

    :Impersonation oder Pretexting: 
        Vorgabe einer falschen Identität (z. B. als Mitarbeiter des IT-Supports); d. h. der Angreifer gibt sich persönlich als jemand anderes aus, um an Informationen zu gelangen und nutzt dafür keine elektronischen Hilfsmittel.

    .. class:: incremental

    :Eavesdropping:
        Abhören von Gesprächen, um an relevante Informationen zu gelangen.   

    .. class:: incremental

    :Eliciting Information:
        Der Angreifer versucht durch geschicktes Fragen an Informationen zu gelangen, die für einen Angriff nützlich sein könnten.

    .. class:: incremental

    :Baiting (`Ködern`:ger:):
        Der Angreifer bietet etwas an, um an Informationen zu gelangen (z. B. ein USB-Stick mit einem Virus, der sich beim Einstecken des USB-Sticks auf dem Rechner installiert.)

    .. class:: incremental

    :Watering Hole Attack:
        Der Angreifer infiziert eine Webseite, die von der Zielgruppe häufig besucht wird, um dann die Besucher der Webseite anzugreifen.
    
    .. class:: incremental

    :Typo Squatting:
        Ausnutzen von Tippfehlern durch das Registrieren einer Domain, die der Domain eines Zielunternehmens ähnelt, um dann Besucher der Webseite auf eine gefälschte Webseite zu leiten. (z. B. `www.gooogle.com`)


.. container:: supplemental

    .. rubric:: HOAX

    Ein Beispiel eines nicht-harmlosen Streichs (Hoax) ist die Falschmeldung vom 1. April 2003, dass Bill Gates gestorben sei. Diese Falschmeldung wurde von vielen Menschen geglaubt und hatte relevanten Einfluss auf den Aktienmarkt.

    .. rubric:: *Credential harvesting*

    In der Anfangszeit von Github und Bitbucket wurden häufig Zugangsdaten und Zertifikate in öffentlichen Repositories gefunden, da die Nutzer diese im Quellcode hinterlegt hatten oder sogar als Ressourcen direkt eingebunden hatten.


    .. rubric:: Typische Phishing E-Mail

    .. image:: images/phishing-mail-fake-fedex.png 
        :align: center
        :width: 80%



:ger-quote:`Motivationstechniken` von Angreifern
-------------------------------------------------------------

.. class:: incremental

- Autorität: Der Angreifer gibt sich z. B. als Mitarbeiter des IT-Supports aus.
- Einschüchterung (:eng:`Intimidation`)
- Dringlichkeit
- Konsens (*"Alle machen das so."*)
- Knappheit (*"Nur noch Heute im Angebot."*)
- Vertrautheit 
- Vertrauen
