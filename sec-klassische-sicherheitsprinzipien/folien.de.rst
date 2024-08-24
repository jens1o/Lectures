.. meta:: 
    :author: Michael Eichberg
    :keywords: "Sicherheitsprinzipien"
    :description lang=de: Einführung in klassische Sicherheitsprinzipien
    :id: lecture-security-einfuehrung-in-sicherheitsprinzipien
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
.. role:: minor
.. role:: ger-quote
.. role:: obsolete
.. role:: line-above

.. role:: raw-html(raw)
   :format: html


Klassische Sicherheitsprinzipien
===================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw-mannheim.de
:Version: 1.0

.. supplemental::

  :Folien: 
      [HTML] |html-source|

      [PDF] |pdf-source|
  :Fehler melden:
      https://github.com/Delors/delors.github.io/issues


Klassische Sicherheitsprinzipien
-----------------------------------------------

(Jerome Saltzer and Michael Schroeder, 1975)

.. class:: incremental

:Principle of Economy of Mechanism (aka Principle of Simplicity): Die Sicherheitsmechanismen sollten so einfach wie möglich sein.

.. class:: incremental

:Principle of Fail-Safe Defaults: Standardmäßig sollte der Zugriff auf Ressourcen verweigert werden.

.. class:: incremental

:Principle of Complete Mediation: Jeder Zugriff auf eine Ressource sollte überprüft werden.


Klassische Sicherheitsprinzipien
-----------------------------------------------


:Principle of Least Authority (aka POLA)/ Principle of Least Privilege: Jedes Programm und jeder Benutzer sollte nur die für seine Aufgabe unbedingt notwendigen Rechte besitzen.

.. class:: incremental

:Principle of Separation of Privilege: Ein System sollte in mehrere POLA konforme Komponenten unterteilt sein. Sollte eine Komponente kompromittiert sein, dann sind die Möglichkeiten des Angreifers dennoch begrenzt. (Eng verwandt mit dem POLA.)


Klassische Sicherheitsprinzipien
-----------------------------------------------

:Principle of Least Common Mechanism: Die Sicherheitsmechanismen sollten über Nutzer hinweg möglichst wenig Gemeinsamkeiten haben.

.. class:: incremental

:Principle of Open Design (vgl. Kerckhoffs Prinzip): Die Sicherheit des Systems sollte nicht von der Geheimhaltung der Sicherheitsmechanismen abhängen (sondern nur vom Schlüssel). 

.. supplemental:: 

    **Beispiel - Principle of Least Common Mechanism**

    z. B. sollten keine gemeinsamen Speicherbereiche verwendet werden und es ist deswegen sinnvoll - wenn möglich - auf Implementierungen im Kernel zu verzichten und statt dessen auf User-Space-Implementierungen zu setzen. 
    
       TCP Connection Hijacking Angriffe wird bzw. wurden z. B. durch die Implementierung des TCP Stacks im Kernel ermöglicht (:math:`\Leftrightarrow` :ger-quote:`Principle of Least Common Mechanism`).



Wiederholung: Klassische Sicherheitsprinzipien
-----------------------------------------------


:Principle of Psychological Acceptability: Die Sicherheitsmechanismen sollten einfach zu verstehen und zu benutzen sein.
  
.. class:: incremental

:Principle of Isolation: Die Sicherheitsmechanismen sollten so entworfen sein, dass Fehler in einem Teil des Systems nicht die Sicherheit des gesamten Systems gefährden; d. h. die einzelnen Komponenten sollten möglichst unabhängig voneinander sein und nur über wohldefinierte  Schnittstellen miteinander kommunizieren und entsprechende Sicherheitsüberprüfungen durchführen. 

.. supplemental:: 

    **Beispiel - Principle of Isolation:**

    Typischerweise kommuniziert zum Beispiel ein Basebandchip (WIFI, LTE, 5G, ...) mit dem Betriebssystem über eine minimale Schnittstelle über die nur Nachrichten übermittelt werden können, die leicht auf ihre Korrektheit überprüft werden können. Insbesondere erfolgt kein direkter Zugriff auf den Speicher des Betriebssystems.

    Einen Angreifer ist es somit ggf. möglich den Basebandchip anzugreifen und ggf. zu kompromittieren, aber er kann nicht direkt auf das Betriebssystem zugreifen und Nachrichten, die bereits auf Betriebssystem oder Anwendungsebene verschlüsselt werden, sind weiterhin sicher.



Ergänzende Sicherheitsprinzipien
-----------------------------------------------

:Principle of Modularity: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie unabhängig voneinander implementiert und geprüft werden können.

.. class:: incremental

:Principle of Layering: Die Sicherheitsmechanismen sollten in Schichten organisiert sein.

.. class:: incremental

:Principle of Least Astonishment: Die Sicherheitsmechanismen sollten so entworfen sein, dass sie keine Überraschungen für die Benutzer bereithalten.


.. supplemental::

    Beispiel für ein Schutzsystem für Netzwerke, dass mehrere Schichten verwendet:

    - einfache (und effiziente) Paketfilter auf unterster Ebene
    - zustandsbehaftete Paketfilter auf der nächsten bzw. der Anwendungsebene

