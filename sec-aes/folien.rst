.. meta:: 
    :author: Michael Eichberg
    :keywords: AES
    :description lang=en: Advanced Encryption Standard (AES)
    :description lang=de: Advanced Encryption Standard (AES)
    :id: 2023_10-W3M20014-aes
    :first-slide: last-viewed

.. |date| date::

.. role:: incremental
.. role:: ger
.. role:: red
.. role:: green 
.. role:: blue 
.. role:: smaller
.. role:: much-smaller
.. role:: ger-quote
.. role:: raw-html(raw)
    :format: html




Advanced Encryption Standard (AES)
===============================================

:Dozent: **Prof. Dr. Michael Eichberg**
:Version: |date|
:Basierend auf: *Cryptography and Network Security - Principles and Practice, 8th Edition, William Stallings*



Arithmetik endlicher Körper (Rekapitulation)
----------------------------------------------

.. class:: incremental

- Ein Körper ist eine Menge, in der wir Addition, Subtraktion, Multiplikation und Division durchführen können, ohne die Menge zu verlassen.
- Die Division ist mit der folgenden Regel definiert: :math:`a/b = a(b^{-1})`.

.. admonition:: Beispiel
    :class: example margin-top-2em incremental

    Ein endlicher Körper (mit einer endlichen Anzahl von Elementen) ist die Menge :math:`Z_p`, die aus allen ganzen Zahlen :math:`\lbrace 0,1,....,p-1 \rbrace` besteht, wobei p eine Primzahl ist und in der modulo :math:`p` gerechnet wird.


Arithmetik endlicher Körper (Rekapitulation)
--------------------------------------------------

.. class:: incremental

  - For convenience and for implementation efficiency, we would like to work with integers that fit exactly into a given number of bits with no wasted bit patterns.
  
    • Integers in the range 0 through :math:`2^n – 1`, which fit into an n-bit word.

  - If one of the operations used in the algorithm is division, then we need to work in arithmetic defined over a field.
  
    • Division requires that each nonzero element has a multiplicative inverse.

  - The set of such integers, :math:`Z_{2^n}`, using modular arithmetic, is not a field!
  
    • For example, the integer 2 has no multiplicative inverse in :math:`Z_{2^n}`, that is, there is no integer b, such that :math:`2b\; mod\; 2^n = 1`

  - A finite field containing :math:`2^n` elements is referred to as :math:`GF(2^n)`.

  .. container:: hint

    Every polynomial in :math:`GF(2^n)` can be represented by an n-bit number.


Arithmetik endlicher Körper in Hinblick auf AES
------------------------------------------------

- Beim *Advanced Encryption Standard* (AES) werden alle Operationen mit 8-Bit-Bytes durchgeführt
    
- Die arithmetischen Operationen: Addition, Multiplikation und Division werden über den endlichen Körper :math:`GF(2^8)` durchgeführt.


.. admonition:: Definition

    AES verwendet das irreduzible Polynom:
    
    .. math::
            
            m(x) = x^8 + x^4 + x^3 +x +1


AES Schlüsseleigenschaften
----------------------------

- AES verwendet eine feste Blockgröße von 128 Bit.
- AES operates on a 4x4 column-major order array of 16 bytes/128 bits: :math:`b_0,b_1,\dots,b_{15}` termed the state:
  
  .. math::

    \begin{bmatrix}b_{0}&b_{4}&b_{8}&b_{12}\\b_{1}&b_{5}&b_{9}&b_{13}\\b_{2}&b_{6}&b_{10}&b_{14}\\b_{3}&b_{7}&b_{11}&b_{15}\end{bmatrix}
    



AES Verschlüsselungsprozess
-----------------------------

.. image:: drawings/aes/encryption_process.svg
    :width: 1700px
    :alt: AES Verschlüsselungsprozess
    :align: center



AES Parameter
--------------

.. csv-table::        
    :width: 100%
    :class: highlight-line-on-hover

    Schlüsselgröße (words/bytes/bits), 4/16/128, 6/24/192, 8/32/256
    Blockgröße (*Block Size*) (words/bytes/bits), 4/16/128, 4/16/128, 4/16/128
    Anzahl der Runden, 10, 12, 14
    Größe des Rundenschlüssels (*RoundKeys*) (words/bytes/bits), 4/16/128, 4/16/128, 4/16/128
    Expandierte Schlüsselgröße (words/bytes), 44/176, 52/208, 60/240



.. class:: vertical-title smaller-slide-title

AES - Ver- und Entschlüsselungsprozess :raw-html:`<br>` :much-smaller:`(Key Size 128bits)`
---------------------------------------------------------------------------------------------

    

.. image:: drawings/aes/encryption_and_decryption_process.svg
    :height: 1150px
    :alt: AES Encryption and Decryption Process
    :align: center


AES Detailed Structure
-----------------------

- Processes the entire data block as a single matrix during each round using substitutions and permutation.

.. class:: incremental

- The key that is provided as input is expanded into an array of forty-four 32-bit words, :math:`w[i]` if 128 bits are used for the keysize.

- The cipher begins and ends with an AddRoundKey stage.
- Can view the cipher as alternating operations of XOR encryption (AddRoundKey) of a block, followed by scrambling of the block (the other three stages), followed by XOR encryption, and so on.
- Each stage is easily reversible.
- The decryption algorithm makes use of the expanded key in reverse order, however the decryption algorithm is not identical to the encryption algorithm.
- State is the same for both encryption and decryption.
- Final round of both encryption and decryption consists of only three stages.


AES Uses Four Different Stages
-------------------------------

:Substitute bytes: uses an S-box to perform a byte-by-byte substitution of the block
:ShiftRows: is a simple permutation.
:MixColumns: is a substitution that makes use of arithmetic over :math:`GF(2^8)`.
:AddRoundKey: is a simple bitwise XOR of the current block with a portion of the expanded key.


AES Substitute byte transformation
----------------------------------

.. image:: drawings/aes/substitute_byte_transformation.svg
    :align: center
    :width: 1400px
    :alt: AES substitute byte tansformation


AES S-box
-----------

.. csv-table::
    :class: footnotesize monospaced highlight-on-hover       
    :align: center 
    :name: s-box

    :math:`_x\\^y` ,   0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
    0, 63, 7C, 77, 7B, F2, 6B, 6F, C5, 30, 01, 67, 2B, FE, D7, AB, 76
    1, CA, 82, C9, 7D, FA, 59, 47, FO, AD, D4, A2, AF, 9C, A4, 72, CO
    2, B7, FD, 93, 26, 36, 3F, F7, CC, 34, A5, E5, F1, 71, D8, 31, 15
    3, 04, C7, 23, С3, 18, 96, 05, 9A, 07, 12, 80, E2, EB, 27, B2, 75
    4, 09, 83, 2C, 1A, 1B, 6E, 5A, A0, 52, 3B, D6, B3, 29, E3, 2F, 84
    5, 53, D1, 00, ED, 20, FC, B1, 5B, 6A, СВ, BE, 39, 4A, 4C, 58, CF
    6, DO, EF, AA, FB, 43, 4D, 33, 85, 45, F9, 02, 7F, 50, 3C, 9F, A8
    7, 51, A3, 40, 8F, 92, 9D, 38, F5, BC, B6, DA, 21, 10, FF, F3, D2
    8, CD, 0C, 13, EC, 5F, 97, 44, 17, C4, A7, 7E, 3D, 64, 5D, 19, 73
    9, 60, 81, 4F, DC, 22, 2A, 90, 88, 46, EE, B8, 14, DE, 5E, 0B, DB
    A, E0, 32, ЗА, 0A, 49, 06, 24, 5C, C2, D3, AC, 62, 91, 95, E4, 79
    B, E7, C8, 37, 6D, 8D, D5, 4E, A9, 6C, 56, F4, EA, 65, 7A, AE, 08
    C, BA, 78, 25, 2E, 1C, A6, B4, С6, E8, DD, 74, 1F, 4B, BD, 8B, 8A
    D, 70, 3E, B5, 66, 48, 03, F6, 0E, 61, 35, 57, B9, 86, C1, 1D, 9E
    E, E1, F8, 98, 11, 69, D9, 8E, 94, 9B, 1E, 87, E9, CE, 55, 28, DF
    F, 8C, A1, 89, OD, BF, E6, 42, 68, 41, 99, 2D, OF, BO, 54, BB, 16

.. container:: supplemental

    Jedes einzelne Byte des Zustands (*State*) wird auf folgende Weise auf ein neues Byte abgebildet: Die äußersten linken 4 Bits des Bytes werden als Zeilenwert und die äußersten rechten 4 Bits als Spaltenwert verwendet. Diese beiden Werte dienen als Indizes in der S-Box.


AES Inverse S-box
-----------------

.. csv-table::
    :class: footnotesize monospaced highlight-on-hover        
    :align: center
    :name: inverse-s-box

    :math:`_x\\^y`, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
    0, 52, 09, 6A, D5, 30, 36, A5, 38, BF, 40, A3, 9E, 81, F3, D7, FB
    1, 7C, E3, 39, 82, 9B, 2F, FF, 87, 34, 8E, 43, 44, C4, DE, E9, СВ
    2, 54, 7B, 94, 32, A6, C2, 23, 3D, EE, 4C, 95, 0B, 42, FA, С3, 4E
    3, 08, 2E, A1, 66, 28, D9, 24, B2, 76, 5B, A2, 49, 6D, 8B, D1, 25
    4, 72, F8, F6, 64, 86, 68, 98, 16, D4, A4, 5C, CC, 5D, 65, B6, 92
    5, 6C, 70, 48, 50, FD, ED, B9, DA, 5E, 15, 46, 57, A7, 8D, 9D, 84
    6, 90, D8, AB, 00, 8C, ВС, D3, 0A, F7, E4, 58, 05, B8, B3, 45, 06
    7, DO, 2C, 1E, 8F, CA, 3F, OF, 02, C1, AF, BD, 03, 01, 13, 8A, 6B
    8, ЗА, 91, 11, 41, 4F, 67, DC, EA, 97, F2, CF, CE, FO, B4, E6, 73
    9, 96, AC, 74, 22, E7, AD, 35, 85, E2, F9, 37, E8, 1C, 75, DF, 6E
    A, 47, FI, 1A, 71, 1D, 29, C5, 89, 6F, B7, 62, 0E, AA, 18, BE, 1B
    B, FC, 56, 3E, 4B, С6, D2, 79, 20, 9A, DB, CO, FE, 78, CD, 5A, F4
    C, 1F, DD, A8, 33, 88, 07, C7, 31, B1, 12, 10, 59, 27, 80, EC, 5F
    D, 60, 51, 7F, A9, 19, B5, 4A, OD, 2D, E5, 7A, 9F, 93, С9, 9C, EF
    E, A0, E0, 3B, 4D, AE, 2A, F5, BO, C8, EB, BB, 3С, 83, 53, 99, 61
    F, 17, 2B, 04, 7E, BA, 77, D6, 26, E1, 69, 14, 63, 55, 21, 0C, 7D

.. container:: supplemental

    .. rubric:: Beispiel
    
    Der (Hex)Wert ``0xA3`` (``x=A`` und ``y=3``) wird von der S-Box auf den (Hex)Wert ``0x0A`` abgebildet. 
    
    Die inverse S-Box bildet den Wert ``0x0A`` (``x=0`` und ``y=A``) wieder auf den ursprünglichen Wert ab.

    

S-Box Design Grundlagen
--------------------------

- Die S-Box ist so konzipiert, dass sie gegen bekannte kryptoanalytische Angriffe resistent ist.
- Die Rijndael-Entwickler suchten nach einem Design, das eine geringe Korrelation zwischen Eingabe- und Ausgabebits aufweist und die Eigenschaft hat, dass die Ausgabe keine lineare mathematische Funktion der Eingabe ist.
- Die Nichtlinearität ist auf die Verwendung der multiplikativen Inversen bei der Konstruktion der S-Box zurückzuführen.



Shift Row Transformation
------------------------

.. image:: drawings/aes/shift_row_transformation.svg
    :width: 1600px 
    :alt: Shift Row Transformation
    :align: center 



Shift Row Transformation - Begründung
--------------------------------------

- Wesentlicher als es auf den ersten Blick scheint!

.. class:: incremental

- Der Zustand (*State*) wird ebenso wie die Chiffrierein- und -ausgabe als Array aus vier 4-Byte-Spalten behandelt.
- Bei der Verschlüsselung werden die ersten 4 Bytes des Klartextes in die erste Spalte vom Zustands (*State*) kopiert, und so weiter.
- Der Rundenschlüssel wird spaltenweise auf den Zustand (*State*) angewendet.
- Bei einer Zeilenverschiebung wird also ein einzelnes Byte von einer Spalte in eine andere verschoben, was einem linearen Abstand von einem Vielfachen von 4 Byte entspricht.
- Die Transformation sorgt dafür, dass die 4 Bytes einer Spalte auf vier verschiedene Spalten verteilt werden.



Mix Column Transformation
---------------------------

.. image:: drawings/aes/mix_column_transformation.svg 
    :alt: Mix Column Transformation
    :align: center
    :width: 1500px 



Inverse Mix Column Transformation
---------------------------------

.. image:: drawings/aes/inv_mix_column_transformation.svg
    :alt: Inverse Mix Column Transformation
    :align: center
    :width: 1500px 



Mix Colum Transformation - Beispiel
-----------------------------------

.. container:: three-columns smaller margin-top-0em padding-top-0em
    
    .. csv-table:: Gegeben
        :class: monospaced footnotesize  

        87, F2, 4D, 97
        6E, 4C, 90, EC
        46, E7, 4A, C3
        A6, 8C, D8, 95

    .. csv-table:: Ergebnis
        :class: monospaced footnotesize 

        47,40,A3,4C
        37,D4,70,9F
        94,E4,3A,42 
        ED,A5,A6,BC
        
    .. container:: tiny

        Beispiel für die Berechnung von :math:`S'_{0,0}`:

        .. math::

            \begin{matrix}
            S'_{0,0} = & 02 \times S_{0,0} & \oplus & 03 \times S_{1,0} & \oplus & 01 \times S_{2,0} & \oplus & 01 \times S_{3,0} &  \\
            & (02 \times 87) & \oplus & (03 \times 6E) & \oplus & (46) & \oplus & (A6) & = 47.
            \end{matrix}

.. admonition:: Hilfsrechnungen
    :class: small 
    
    .. math::

        \begin{matrix}
        (02 \times 87) & = & (0000\,1110) \oplus (0001\,1011) = & (0001\,0101) \\
        (03 \times 6E) = 6E \oplus (02 \times 6E) & = & (0110\,1110) \oplus (1101\, 1100)  = & (1011\,0010) \\
        46 & = & & (0100\,0110) \\
        A6 & = & & (1010\,0110) \\
        & & & \overline{  (0100\, 0111) }
        \end{matrix}


.. container:: supplemental

    .. admonition:: Warnung
        :class: warning

        :math:`(03 \times 6E) = 6E \oplus (02 \times 6E)` und **ist nicht** :math:`6E \oplus 6E \oplus 6E`, da wir hier Polynomarithmetik in :math:`GF(2^8)` nutzen und :math:`03` dem Polynom: :math:`x+1` entspricht.



Mix Column Transformation - Begründung
--------------------------------------

- Die Koeffizienten einer Matrix, die auf einem linearen Code mit maximalem Abstand zwischen den Codewörtern basiert, gewährleisten eine gute Mischung zwischen den Bytes jeder Spalte.
  
- Die *Mix Column Transformation* (:ger:`Mischspaltentransformation`) kombiniert mit der *Shift Row Transformation* (:ger:`Zeilenverschiebungstransformation`) stellt sicher, dass nach einigen Runden alle Ausgangsbits von allen Eingangsbits abhängen.



AddRoundKey Transformation
--------------------------

- Die 128 Bits des Zustands (*State*) werden bitweise mit den 128 Bits des Rundenschlüssels XOR-verknüpft.

.. class:: incremental 

- Die Operation wird als spaltenweise Operation zwischen den 4 Bytes einer Spalte des Zustands (*State*) und einem Wort des runden Schlüssels betrachtet.
- *Kann auch als eine Operation auf Byte-Ebene betrachtet werden*.

.. admonition:: Designbebegründung
    :class: incremental margin-top-2em

    - Sie ist so einfach wie möglich und betrifft jedes Bit des Staates.
    - Die Komplexität der runden Schlüsselexpansion plus die Komplexität der anderen Stufen von AES sorgen für Sicherheit!



.. class:: vertical-title smaller-slide-title

Eingabe für eine einzelne AES-Verschlüsselungsrunde
-----------------------------------------------------

.. image::  drawings/aes/input_for_a_single_aes_round.svg
    :alt: Eingabe für eine einzelne AES-Verschlüsselungsrunde
    :align: center
    :height: 1150px 



AES Schlüsselexpansion
--------------------------

- Takes as input a four-word (16 byte) key and produces a linear array of 44 words (176) bytes.
- This is sufficient to provide a four-word round key for the initial `AddRoundKey` stage and each of the 10 rounds of the cipher.
- Key is copied into the first four words of the expanded key.
- The remainder of the expanded key is filled in four words at a time.
- Each added word :math:`w[i]` depends on the immediately preceding word, :math:`w[i – 1]`, and the word four positions back, :math:`w[i – 4]`
- In three out of four cases a simple XOR is used.
- For a word whose position in the w array is a multiple of 4, a more complex function :math:`g` is used.



.. class:: vertical-title smaller-slide-title

AES Schlüsselexpansion - Visualisiert
---------------------------------------

.. image:: drawings/aes/key_expansion.svg 
    :alt: AES Schlüsselexpansion
    :align: center
    :height: 1170px



AES *Round Key* Berechnung
-----------------------------

.. math::

    \begin{matrix}
        r_i & = & (r_{c_i},00,00,00) \\
        r_{c_1} & = & 01 \\
        r_{c_{i+1}} & = & xtime(r_{c_i})
    \end{matrix}

.. admonition:: :math:`xtime` Function
    :class: incremental smaller definition

    .. math::
        
        y_7y_6y_5y_5y_4y_3y_2y_1y_0 = xtime(x_7x_6x_5x_5x_4x_3x_2x_1x_0) \qquad (x_i,y_i \in \lbrace 0,1 \rbrace)

        y_7y_6y_5y_5y_4y_3y_2y_1y_0 =
        \begin{cases}
        x_6x_5x_5x_4x_3x_2x_1x_00, & if x_7 = 0\\
        x_6x_5x_5x_4x_3x_2x_1x_00 \oplus 0001 1011,& if x_7 = 1\\
        \end{cases}

.. admonition:: Die *Round Key* Werte sind:
    :class: incremental small

    :math:`r_{c_{1}}=01, r_{c_{2}}=02,r_{c_{3}}=04,r_{c_{4}}=08,r_{c_{5}}=10`
    
    :math:`r_{c_{6}}=20,r_{c_{7}}=40,r_{c_{8}}=80,r_{c_{9}}=1B = 0001 1011, r_{c_{10}}=36`




.. class:: smaller

AES Schlüsselexpansion - Beispiel (Runde 1)
---------------------------------------------

:Gegeben sei: 
        :math:`w[0] = (54,68,61,74)`

        :math:`w[1] = (73,20,6D,79)`
        
        :math:`w[2] = (20,4B,75,6E)`
        
        :math:`w[3] = (67,20,46,75)`

.. container:: incremental

  - :math:`g(w[3])`:

    - zirkuläre Linksverschiebung von  :math:`w[3]`: :math:`(20,46,75,67)`  
    - Bytesubstitution mit Hilfe der s-box: :math:`(B7,5A,9D,85)`
    - Addition der Rundenkonstante :math:`(01,00,00,00)` ⇒ :math:`g(w[3]) = (B6,5A,9D,85)`  
  
.. container:: incremental

  - :math:`w[4] = w[0] \oplus g(w[3]) = (E2,32,FC,F1)` 
  - :math:`w[5] = w[4] \oplus w[1] = (91,12,91,88)` 
  - :math:`w[6] = w[5] \oplus w[2] = (B1,59,E4,E6)` 
  - :math:`w[7] = w[6] \oplus w[3] = (D6,79,A2,93)` 

.. container:: incremental

  - First roundkey is: :math:`w[4] || w[5] || w[6] || w[7]` 



AES Key Expansion - Rationale
------------------------------

.. container:: width-50 note incremental small

    The specific criteria that were used are:

    • Knowledge of a part of the cipher key or round key does not enable calculation of many other round-key bits
    • An invertible transformation
    • Speed on a wide range of processors
    • Usage of round constants to eliminate symmetries
    • Diffusion of cipher key differences into the round keys
    • Enough nonlinearity to prohibit the full determination of round key differences from cipher key differences only
    • Simplicity of description 
    
    

• The Rijndael developers designed the expansion key algorithm to be resistant to known cryptanalytic attacks
• Inclusion of a round-dependent round constant eliminates the symmetry between the ways in which round keys are generated in different rounds


.. class:: vertical-title smaller smaller-slide-title

Lawineneffekt in AES: Änderung im Klartext
--------------------------------------------

.. container:: tiny

    .. csv-table::        
        :class: smaller monospaced highlight-line-on-hover
        :align: center 
        :widths: 90 400 325
        :header: Round,,"# unterschiedlicher Bits"

            ,"0123456789abcdeffedcba9876543210
        0023456789abcdeffedcba9876543210",1
        0,"0e3634aece7225b6f26b174ed92b5588
        0f3634aece7225b6f26b174ed92b5588",1
        1,"657470750fc7ff3fc0e8e8ca4dd02a9c
        c4a9ad090fc7ff3fc0e8e8ca4dd02a9c",20
        2,"5c7bb49a6b72349b05a2317ff46d1294
        fe2ae569f7ee8bb8c1f5a2bb37ef53d5",58
        3,"7115262448dc747e5cdac7227da9bd9c
        ec093dfb7c45343d6890175070485e62",59
        4,"f867aee8b437a5210c24c1974cffeabc
        43efdb697244df808e8d9364ee0ae6f5",61
        5,"721eb200ba06206dcbd4bce704fa654e
        7b28a5d5ed643287e006c099bb375302",68
        6,"0ad9d85689f9f77bc1c5f71185e5fb14
        3bc2d8b6798d8ac4fe36ald891ac181a",64
        7,"db18a8ffa16d30d5f88b08d777ba4eaa
        9fb8b5452023c70280e5c4bb9e555a4b",67
        8,"f91b4fbfe934c9bf8f2f85812b084989
        20264e1126b219aef7feb3f9b2d6de40",65
        9,"cca104a13e678500f£59025f3bafaa34
        b56a0341b2290ba7dfdfbddcd8578205",61
        10,"ff0b844a0853bf7c6934ab4364148fb9
        612b89398d0600cde116227ce72433f0",58



.. class:: vertical-title smaller smaller-slide-title

Lawineneffekt in AES: Änderung im Schlüssel
---------------------------------------------

.. container:: tiny

    .. csv-table::        
        :class: smaller monospaced highlight-line-on-hover
        :align: center 
        :widths: 90 400 325
        :header: Runde,,"# unterschiedlicher Bits"

        , "0123456789abcdeffedcba9876543210
        0123456789abcdeffedcba9876543210", 0
        0, "0e3634aece7225b6f26b174ed92b5588
        0f3634aece7225b6f26b174ed92b5588", 1
        1, "657470750fc7ff3fc0e8e8ca4dd02a9c
        c5a9ad090ec7ff3fcle8e8ca4cd02a9c", 22
        2, "5c7bb49a6b72349b05a2317ff46d1294
        90905fa9563356d15f3760f3b8259985", 58
        3, "7115262448dc747e5cdac7227da9bd9c
        18aeb7aa794b3b66629448d575c7cebf", 67
        4, "f867aee8b437a5210c24c1974cffeabc
        f81015f993c978a876ae017cb49e7eec", 63
        5, "721eb200ba06206dcbd4bce704fa654e
        5955c91b4e769f3cb4a94768e98d5267", 81
        6, "0ad9d85689f9f77bc1c5f71185e5fb14
        dc60a24d137662181e45b8d3726b2920", 70
        7, "db18a8ffa16d30d5f88b08d777ba4eaa
        fe8343b8f88bef66cab7e977d005a03c", 74
        8, "f91b4fbfe934c9bf8f2f85812b084989
        da7dad581d1725c5b72fa0f9d9d1366a", 67
        9, "cca104a13e678500ff59025f3bafaa34
        Occb4c66bbfd912f4b511d72996345e0", 59
        10, "ff0b844a0853bf7c6934ab4364148fb9
        fc8923ee501a7d207ab670686839996b", 53



Equivalent Inverse Cipher
--------------------------

AES decryption cipher is not identical to the encryption cipher.

• The sequence of transformations differs although the form of the key schedules is the same.
• Has the disadvantage that two separate software or firmware modules are needed for applications that require both encryption and decryption.

.. class:: incremental

  Two separate changes are needed to bring the decryption structure in line with the encryption structure:

  .. class:: incremental

    1. The first two stages of the decryption round need to be interchanged.
    2. The second two stages of the decryption round need to be interchanged.


Interchanging `InvShiftRows` and `InvSubBytes`
----------------------------------------------

• `InvShiftRows` :red:`affects the sequence` of bytes in State but does not alter byte contents and does not depend on byte contents to perform its transformation
• `InvSubBytes` :blue:`affects the contents` of bytes in State but does not alter byte sequence and does not depend on byte sequence to perform its transformation


.. admonition:: Important 
    
    Thus, these two operations commute and can be interchanged.


Interchanging `AddRoundKey` and `InvMixColumns`
------------------------------------------------

- The transformations `AddRoundKey` and `InvMixColumns` do not alter the sequence of bytes in State.
- If we view the key as a sequence of words, then both AddRoundKey and InvMixColumns operate on State one column at a time.
- These two operations are linear with respect to the column input.

  That is, for a given State :math:`S_i` and a given round key :math:`w_j`:
  
  .. math:: 

    InvMixColumns(S_i \oplus w_j) = InvMixColumns(S_i) \oplus InvMixColumns(w_j)


.. class:: vertical-title

Äquivalente Inverse Chiffre
----------------------------

.. image:: drawings/aes/equivalent_inverse_cipher.svg
    :height: 1170px
    :align: center



Aspekte der Umsetzung auf 8-bit Prozessoren
----------------------------------------------

AES kann sehr effizient auf einem 8-Bit-Prozessor implementiert werden:
 
:AddRoundKey: ist eine byteweise XOR-Operation.
:ShiftRows: ist eine einfache Byte-Verschiebeoperation.
:SubBytes: arbeitet auf Byte-Ebene und benötigt nur eine Tabelle von 256 Bytes.
:MixColumns: erfordert eine Matrixmultiplikation im Körper :math:`GF(2^8)`, was bedeutet, dass alle Operationen mit Bytes durchgeführt werden.



Aspekte der Umsetzung auf 32-bit Prozessoren
------------------------------------------------

AES kann effizient auf einem 32-Bit-Prozessor implementiert werden:

- Die einzelnen Schritte können so umdefiniert werden, dass sie 32-Bit-Wörter verwenden.
- Es ist möglich die 4 Tabellen mit je 256 Wörtern vorzuberechnen.
  
  - Dann kann jede Spalte in jeder Runde mit 4 Tabellen-Lookups + 4 XORs berechnet werden.
  - Die Kosten für die Speicherung der Tabellen belaufen sich auf :ger-quote:`4Kb`.

- Die Entwickler glauben, dass die Möglichkeit einer effizienten Implementierung ein Schlüsselfaktor für die Wahl der AES-Chiffre zum neuen Standard war.



.. class:: integrated-exercise

Übung (AES-128) - Berechnung des *RoundKey*
--------------------------------------------

Sei der folgende *RoundKey* gegeben: 

:math:`rc_1=w[4]\,||\,w[5]\,||\,w[6]\,||\,w[7]` :math:`=` 

``-w[4]------   -w[5]------   -w[6]------   -w[7]------``  

``E2 32 FC F1   91 12 91 88   B1 59 E4 E6   D6 79 A2 93``  

Berechne :math:`rc_2`; d.h. den Rundschlüssel (*Roundkey*) für die zweite Runde.

1. Bevor Sie die konkrete Berechnung durchführen, schreiben Sie zunächst die Formeln auf:

   :math:`w[8]\;\; =\; \ldots \oplus ... \quad w[9]\;\; =\; ... \oplus ...  \quad w[10] =\; ... \oplus ... \quad w[11] =\; ... \oplus ...`

2. Berechne `w[8]` und `w[9]`.

.. protected-exercise-solution:: Formeln für die Berechnung des *RoundKey*

    .. math::

        w[8] = w[4] \oplus g(w[7])

        w[9] = w[5] \oplus w[8]

        w[10] = w[6] \oplus w[9]

        w[11] = w[7] \oplus w[10]
      
.. protected-exercise-solution:: Berechnung von :math:`w[8]` und :math:`w[9]`

    :: 

        g(w[7]): 
            1. after left shift of w[7]:              79 A2 93 D6
            2. after s-box substituion:               B6 3A DC F6
            3. after add RoundConstant (02 00 00 00):  B4 3A DC F6

        w[8] = E2 32 FC F1 xor B4 3A DC F6 = 56 08 20 07
        w[9] = w[8]                      xor 91 12 91 88 = C7 1A B1 8F



.. class:: integrated-exercise

Übung (AES-128)
---------------------

Nehmen wir an, dass der Zustand (*State*) folgendermaßen sei:

::   

   00 3C 6E 47
   1F 4E 22 74
   0E 08 1B 31
   54 59 0B 1A
   
1. Führen Sie den *Substitute Bytes* Schritt durch (Anwendung der S-box Transformation).

   
2. Führen Sie die *Shift Rows Transformation* auf dem Ergebnis des vorherigen Schrittes durch.


.. protected-exercise-solution:: Substitute Bytes

    ::

        63 EB 9F A0
        C0 2F 93 92
        AB 30 AF C7
        20 CB 2B A2

.. protected-exercise-solution:: Shift Rows

    ::

        63 EB 9F A0
        2F 93 92 C0
        AF C7 AB 30
        A2 20 CB 2B



.. class:: integrated-exercise

Übung (AES-128) - *Mix Columns Transformation*
-------------------------------------------------

Nehmen wir an, dass der Zustand (*State*) folgendermaßen sei:

::

   6A 59 CB BD
   4E 48 12 A0
   98 9E 30 9B
   8B 3D F4 9B

Führen Sie die *Mix Columns Transformation* durch für das fehlende Feld (:math:`S'_{0,0}`):

::

    ?? C9 7F 9D
    CE 4D 4B C2
    89 71 BE 88
    65 47 97 CD

.. protected-exercise-solution:: Mix Columns Transformation

     .. math::

        0x02 \times 0x6A = (simple\; left\; shift\; of\; 6A): 1101\, 0100_b

        0x03 \times 0x4E = 0x4E \oplus (0x02 \times 0x4E) = 0100\, 1110_b \oplus 1001\, 1100_b = 1101 0010_b

        S'_{0,0} = 1101\, 0100_b \oplus 1101\, 0010_b \oplus 0x98 \oplus 0x8B = 0x15



.. class:: integrated-exercise

Übung (AES-128) - *RoundKey* Anwendung
-------------------------------------------

Wenden Sie den folgenden *RoundKey*: 
   
``-w[x]------   -w[x+1]----   -w[x+2]----   -w[x+3]----``  

``D2 60 0D E7   15 7A BC 68   63 39 E9 01   C3 03 1E FB`` 

auf die folgende Zustandsmatrix (*State*):

::

    AA 65 FA 88
    16 0C 05 3A
    3D C1 DE 2A
    B3 4B 5A 0A

.. protected-exercise-solution:: RoundKey Anwendung

    Denken Sie daran, dass der *RoundKey* auf die Spalte angewendet wird!
    
    ::

        78 70 99 4B
        76 76 3C 39
        30 7D 37 34
        54 23 5B F1



.. class:: integrated-exercise

Übung (AES-128)
---------------------

Fragen Sie sich, was passiert, wenn Sie einen Block, der nur aus ``0x00`` Werten besteht, mit einem Schlüssel verschlüsseln, der ebenfalls nur aus ``0x00`` Werten besteht?

.. protected-exercise-solution:: 0x00 Schlüssel und Daten

    - Die erste Substitution wird alle Werte auf denselben Wert abbilden: :math:`0x63`'.
    - Die Zeilenverschiebung hat keine Auswirkung.
    - Spalten mischen (weil die Werte nicht mehr :math:`0x00` sind, führt dies zu einer gewissen Diffusion :math:`0x02 \times 0x63` und :math:`0x03 \times 0x63` ist nicht :math:`0x63`. )
    - *AddRoundKey* wirkt sich ebenfalls aus und führt (schon in der ersten Runde) zu einiger Konfusion.

