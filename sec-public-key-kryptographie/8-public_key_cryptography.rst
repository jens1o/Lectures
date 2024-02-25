.. meta:: 
    :author: Michael Eichberg
    :keywords: Public-key Cryptography
    :description lang=en: Public-Key Cryptography and RSA
    :description lang=de: Public-Key Kryptografie and RSA
    :id: 2023_10-W3M20014-public_key_cryptography
    :first-slide: last-viewed

.. |date| date::

.. role:: incremental
.. role:: ger
.. role:: red
.. role:: green 
.. role:: blue 
    
    

Public-Key Cryptography and RSA
===============================================

:Lecturer: **Prof. Dr. Michael Eichberg**
:Version: |date|
:Based on: *Cryptography and Network Security - Principles and Practice, 8th Edition, William Stallings*


.. image:: logo.svg
    :alt: DHBW CAS Logo
    :scale: 4
    :class: logo


Terminology Related to Asymmetric Encryption
---------------------------------------------

.. container:: smaller

    :Asymmetric Keys:
        Two related keys, a public key and a private key that are used to perform complementary operations, such as encryption and decryption or signature generation and signature verification.
    :Public Key Certificate:
        A digital document issued and digitally signed by the private key of a Certification Authority that binds the name of a subscriber to a public key. The certificate indicates that the subscriber identified in the certificate has sole control and access to the corresponding private key.
    :Public Key (Asymmetric) Cryptographic Algorithm:
        A cryptographic algorithm that uses two related keys, a public key and a private key. The two keys have the property that deriving the private key from the public key is computationally infeasible.
    :Public Key Infrastructure (PKI):
        A set of policies, processes, server platforms, software and workstations used for the purpose of administering certificates and public-private key pairs, including the ability to issue, maintain, and revoke public key certificates.

Misconceptions Concerning Public-Key Encryption
------------------------------------------------

.. class:: incremental

- Public-key encryption is more secure from cryptanalysis than symmetric encryption
- Public-key encryption is a general-purpose technique that has made symmetric encryption obsolete
- There is a feeling that key distribution is trivial when using public-key encryption, compared to the cumbersome handshaking involved with key distribution centers for symmetric encryption
  

Principles of Public-Key Cryptosystems
--------------------------------------- 

- The concept of public-key cryptography evolved from an attempt to attack two of the most difficult problems associated with symmetric encryption:

    .. admonition:: Key Distribution

        How to have secure communications in general without having to trust a KDC with your key.
    
    .. admonition:: Digital Signatures

        How to verify that a message comes intact from the claimed sender.

.. class:: incremental

Principles of Public-Key Cryptosystems
--------------------------------------- 

    **Whitfield Diffie** and **Martin Hellman** from Stanford University achieved a breakthrough in 1976 by coming up with a method that addressed both problems and was radically different from all previous approaches to cryptography.



Ingredients of Public-Key Cryptosystems
---------------------------------------

.. class:: incremental

1. *Plaintext*: The readable message or data that is fed into the algorithm as input
2. *Encryption algorithm*: Performs various transforma-tions on the plaintext
3. *Public Key*: Used for *encryption* or *decryption*
4. *Private Key*: Used for *encryption* or *decryption*
5. *Ciphertext*: The scrambled message produced as output
6. *Decryption algorithm*: Accepts the ciphertext and the matching key and produces the original plaintext.


Encryption with Public Key
---------------------------

.. image::  8-enc_with_pub_key.svg
    :alt: Encryption with public key
    :align: center
    :width: 1600px


Encryption with Private Key
---------------------------

.. image::  8-enc_with_priv_key.svg
    :alt: Encryption with private key
    :align: center
    :width: 1600px


Conventional and Public-key Encryption
----------------------------------------

.. container:: two-columns smaller

    .. container:: column smaller

        **Conventional Encryption**
        
        *Needed to Work*:
        
        1.	The same algorithm with the same key is used for encryption and decryption.

        2.	The sender and receiver must share the algorithm and the key.

        *Needed for Security*:

        1.	The key must be kept secret.

        2.	It must be impossible or at least impractical to decipher a message if the key is kept secret.

        3.	Knowledge of the algorithm plus samples of ciphertext must be insufficient to determine the key.
    
    .. container:: column smaller

        **Public-Key Encryption**

        *Needed to Work*:

        1.	One algorithm is used for encryption and a related algorithm for decryption with a pair of keys, one for encryption and one for decryption.

        2.	The sender and receiver must each have one of the matched pair of keys (not the same one).

        *Needed for Security*:

        1.	One of the two keys must be kept secret.

        2.	It must be impossible or at least impractical to decipher a message if one of the keys is kept secret.
        
        3.	Knowledge of the algorithm plus one of the keys plus samples of ciphertext must be insufficient to determine the other key.


Public-Key Cryptosystem: Confidentiality
-----------------------------------------


.. image:: 8-confidentiality.svg 
    :alt:  Confidentiality
    :align: center
    :width: 1350px


Public-Key Cryptosystem: Authentication
-----------------------------------------

.. image:: 8-authentication.svg 
    :alt: Authentication
    :align: center
    :width: 1350px


Public-Key Cryptosystem: Authentication and Secrecy
----------------------------------------------------

.. image:: 8-authentication_and_secrecy.svg 
    :alt: Authentication and Secrecy
    :align: center
    :width: 1600px




Applications for Public-Key Cryptosystems
------------------------------------------

- Public-key cryptosystems can be classified into three categories:
  
  1. *En-/Decryption*: The sender encrypts a message with the recipient’s public key.
  2. *Digital Signatures*: The sender “signs” a message with its private key.
  3. *Key Exchange*: Two sides cooperate to exchange a session key (i.e., a symmetric key)

- Some algorithms are suitable for all three applications, whereas others can be used only for one or two


Applications for Public-Key Cryptosystems
------------------------------------------

.. csv-table::
    :header: Algorithm, Encryption/Decryption, Digital Signature, Key Exchange

    RSA,Yes,Yes,Yes
    Elliptic Curve,Yes,Yes,Yes
    Diffie-Hellman,No,No,Yes
    DSS,No,Yes,No

.. container:: small

    *DSS = Digital Signature Standard* developed by the NSA (National Security Agency)


Public-Key Requirements
------------------------

Conditions that these algorithms must fulfill:

.. class:: incremental 

- It is computationally easy for a party :math:`B` to generate a pair (public-key :math:`PU_b`, private key :math:`PR_b`).
- It is computationally easy for a sender :math:`A`, knowing the public key and the message to be encrypted, to generate the corresponding ciphertext.
- It is computationally easy for the receiver :math:`B` to decrypt the resulting ciphertext using the private key to recover the original message.
- It is *computationally infeasible* for an adversary, knowing the public key, to determine the private key.
- It is *computationally infeasible* for an adversary, knowing the public key and a ciphertext, to recover the original message.
- The two keys can be applied in either order.


Public-Key Requirements
------------------------

.. class:: incremental 

- Need a trap-door one-way function
  
  A one-way function is one that maps a domain into a range such that every function value has a unique inverse, with the condition that the *calculation of the function is easy*, whereas the *calculation of the inverse is infeasible*

  - :math:`Y = f(X)` easy  
  - :math:`X = f^{–1}(Y)` infeasible
  
- A trap-door one-way function is a family of invertible functions :math:`f_k`, such that
  
  - :math:`Y = f_k(X)` easy, if k and X are known 
  - :math:`X = f_k^{–1}(Y)` easy, if k and Y are known
  - :math:`X = f_k^{–1}(Y)` infeasible, if Y known but k not known
  
- A practical public-key scheme depends on a suitable trap-door one-way function.


Public-Key Cryptanalysis
--------------------------

.. class:: incremental 

- A public-key encryption scheme is vulnerable to a brute-force attack

  .. class:: incremental smaller
  
  - Countermeasure: use large keys!
  - Key size must be small enough for practical encryption and decryption.
  - Key sizes that have been proposed result in encryption/decryption speeds that are too slow for general-purpose use.
  - Public-key encryption is currently confined to key management and signature applications.

- Another form of attack is to find some way to compute the private key given the public key.
  
  *To date it has not been mathematically proven that this form of attack is infeasible for a particular public-key algorithm.*

- Finally, there is a probable-message attack.
  
  *This attack can be thwarted by appending some random bits to simple messages.*


Rivest-Shamir-Adleman (RSA) Algorithm
--------------------------------------

- Developed in 1977 at MIT by Ron Rivest, Adi Shamir & Len Adleman
- General-purpose approach to public-key encryption
- Is a cipher in which the plaintext and ciphertext are integers between :math:`0` and :math:`n – 1` for some :math:`n`

  A typical size for :math:`n` was 1024 bits, or 309 decimal digits.

  Such small numbers are now considered to be grossly insecure; in particular with quatum computing on the horizon and the development of quantum algorithms (https://en.wikipedia.org/wiki/Shor's_algorithm) that can factorize numbers efficiently on them.


RSA Algorithm
--------------

.. class:: incremental 

- RSA makes use of an expression with exponentials
- Plaintext is encrypted in blocks with each block having a binary value less than some number :math:`n` 
- Encryption and decryption are of the following form, for some plaintext block :math:`M` and ciphertext block :math:`C`
  
	:math:`C = M^e\; mod\; n` 

	:math:`M = C^d\; mod\; n = (M^e)^d\; mod\; n = M^{ed}\; mod\; n` 

- Both sender and receiver must know the value of :math:`n`
- The sender knows the value of :math:`e`, and only the receiver knows the value of :math:`d`
- This is a public-key encryption algorithm with a public key of :math:`PU=\lbrace e,n \rbrace` and a private key of :math:`PR=\lbrace d,n \rbrace`


Algorithm Requirements
-----------------------

- For this algorithm to be satisfactory for public-key encryption, the following requirements must be met:

  1.  It is possible to find values of :math:`e`, :math:`d`, :math:`n` such that :math:`M^{ed}\;mod\; n = M` for all :math:`M < n` 
  2.  It is relatively easy to calculate :math:`M^e\;mod\; n` and :math:`C^d\; mod\; n` for all values of :math:`M < n` 
  3.  It is infeasible to determine :math:`d` given :math:`e` and :math:`n`


The RSA Algorithm
-------------------

.. container:: two-columns smaller

    .. container:: column smaller

        **Key Generation by Alice**

        .. csv-table:: 
            :class: invisible 
            
            "Select p, q", ":math:`p` and :math:`b` both prime, :math:`p \neq q` "
            "Calculate n", ":math:`n = p \times q` "
            "Calculate 𝜙(n) ", ":math:`\phi(n) = (p - 1)(q - 1)` "
            "Select Int e", ":math:`gcd(\phi(n),e) = 1; \qquad 1 < e < \phi(n)` "
            Calculate d, :math:`d \equiv e^{-1}\; (mod\; \phi(n)) \Leftrightarrow ed\; mod\; \phi(n)= 1` 
            Public key, ":math:`PU = \lbrace e,n \rbrace` "
            Private key, ":math:`PR = \lbrace d,n \rbrace` "

    .. container:: column

        **Encryption by Bob with Alice's Public Key**

        .. csv-table:: 
            :class: invisible

            Plaintext, :math:`M<n`
            Ciphertext, :math:`C=M^e\; mod\; n` 
    
        **Decryption by Alice with Alice's Private Key**

        .. csv-table:: 
            :class: invisible

            Ciphertext, :math:`C` 
            Plaintext, :math:`M = C^d\; mod\; n`



Example of RSA Algorithm
--------------------------

:p and q: 

    :math:`p = 11; q = 17; n = 187`

:Plaintext:
    88

:Encryption:
    :math:`PU =\lbrace e= 7, n= 187 \rbrace`:

    :math:`88^7\;mod\; 187 = 11 = C`

:Decryption:
    :math:`PR =\lbrace d= 23, n = 187 \rbrace`: 

    :math:`11^{23}\; mod\; 187 = 88 = P`


Exponentiation in Modular Arithmetic
-------------------------------------

- Both encryption and decryption in RSA involve raising an integer to an integer power, :math:`mod\; n`
- Can make use of a property of modular arithmetic:
  
  :math:`[(a\; mod\; n) \times (b\; mod\; n)]\; mod\; n =(a \times b)\; mod\; n`

    Example 
  
    :math:`2^{11} = 2^1 \times 2^2 \times 2^8 = 2  \times  4  \times  256`
    
    :math:`2^9\; mod\; 13 = [(2^1\; mod\; 13) \times (2^8 \; mod\; 13)]\; mod\; 13` 


- With RSA you are dealing with potentially large exponents so efficiency of exponentiation is a consideration!


Algorithm for Computing :math:`a^k\; mod\; n` 
-----------------------------------------------

(Square and Multiply)

The integer :math:`b` is expressed as a binary number ``b[k]b[k-1]...b[0]``: 

.. note:: 
    :class: small

    ``c`` just depicts the component.

.. code:: pseudocode

    c := 0; f := 1
    for i := k downto 0
        do c := 2 * c
           f := (f * f) mod n
        if b[i] = 1
            then c := c + 1
                 f := (f * a) mod n
    return f


Result of the Fast Modular Exponentation Algorithm for :math:`a^b\;mod\;n`
---------------------------------------------------------------------------

:math:`a=7; b = 560 = 1000110000_b`, and :math:`n=561`

.. csv-table::
    :header: i, 9,8,7,6,5,4,3,2,1,0
    :width: 100%

    ":math:`b_i`", 1,0,0,0,1,1,0,0,0,0
    c, 1,2,4,8,17,35,70,140,280,560
    f, 7,49,157,526,160,241,298,166,67,1


Efficient Operation Using the Public Key
-----------------------------------------

- To speed up the operation of the RSA algorithm using the public key, a specific choice of e is usually made
- The most common choice is 65537 (:math:`2^{16} + 1`)
- Two other popular choices are :math:`e=3` and :math:`e=17`
- Each of these choices has only two 1 bits, so the number of multiplications required to perform exponentiation is minimized
- With a very small public key, such as :math:`e = 3`, RSA becomes vulnerable to a simple attack
    

Efficient Operation Using the Private Key
-----------------------------------------

- Decryption uses exponentiation to power :math:`d`
- A small value of d is vulnerable to a brute-force attack and to other forms of cryptanalysis
- Can use the Chinese Remainder Theorem (CRT) to speed up computation:

  The quantities :math:`d\; mod\; (p - 1)` and :math:`d\; mod\; (q - 1)` can be precalculated.

  End result is that the calculation is approximately four times as fast as evaluating :math:`M = C^d\; mod\; n` directly.


Key Generation
---------------

.. container:: two-columns

    .. container:: column 

        Before the application of the public-key cryptosystem each participant must generate a pair of keys:
        
        - Determine two prime numbers :math:`p` and :math:`q` 
        - Select either :math:`e` or :math:`d` and calculate the other

    .. container:: column

        - Because the value of n = pq will be known to any potential adversary, primes must be chosen from a sufficiently large set.
        - The method used for finding large primes must be reasonably efficient.
         
          The Miller-Rabin Algorithm can, e.g., be used.


The Security for RSA - Five possible approaches to attacking RSA
-----------------------------------------------------------------

.. class:: incremental 

- Brute force: Involves trying all possible private keys.
- Mathematical attacks: There are several approaches, all equivalent in effort to factoring the product of two primes.
- Timing attacks: These depend on the running time of the decryption algorithm.
- Hardware fault-based attack: This involves inducing hardware faults in the processor that is generating digital signatures.
- Chosen ciphertext attacks: This type of attack exploits properties of the RSA algorithm.


Factoring Problem
-----------------

We can identify three approaches to attacking RSA mathematically:

1. Factor :math:`n` into its two prime factors. This enables calculation of :math:`\phi(n) = (p - 1) \times (q - 1)`, which in turn enables determination of :math:`d = e^{-1} (mod\; ø(n))`.
2. Determine :math:`\phi(n)` directly without first determining :math:`p` and :math:`q`. Again, this enables determination of :math:`d = e^{-1} (mod\; \phi(n))`.
3. Determine :math:`d` directly without first determining :math:`\phi(n)`. 

Timing Attacks
---------------

- Paul Kocher, a cryptographic consultant, demonstrated that a snooper can determine a private key by keeping track of how long a computer takes to decipher messages
- Are applicable not just to RSA but to other public-key cryptography systems
- Are alarming for two reasons:

  - It comes from a completely unexpected direction
  - It is a ciphertext-only attack

Countermeasures
----------------

.. container:: smaller 

    :Constant exponentation time:
        Ensure that all exponentiations take the same amount of time before returning a result; this is a simple fix but does degrade performance.

    .. class:: incremental 

    :Random delay:
        Better performance could be achieved by adding a random delay to the exponentiation algorithm to confuse the timing attack.

    .. class:: incremental 

    :Blinding: 
        Multiply the ciphertext by a random number before performing exponentiation; this process prevents the attacker from knowing what ciphertext bits are being processed inside the computer and therefore prevents the bit-by-bit analysis essential to the timing attack


Fault-Based Attack
------------------

- An attack on a processor that is generating RSA digital signatures.
  
  - Induces faults in the signature computation by reducing the power to the processor.
  - The faults cause the software to produce invalid signatures which can then be analyzed by the attacker to recover the private key.
  
- The attack algorithm involves inducing single-bit errors and observing the results.
- While worthy of consideration, this attack does not appear to be a serious threat to RSA in many applications.

  - It requires that the attacker have physical access to the target machine and is able to directly control the input power to the processor


Chosen Ciphertext Attack (CCA)
------------------------------

- The adversary chooses a number of ciphertexts and is then given the corresponding plaintexts, decrypted with the target’s private key
  
  - Thus the adversary could select a plaintext, encrypt it with the target’s public key, and then be able to get the plaintext back by having it decrypted with the private key.
  - The adversary exploits properties of RSA and selects blocks of data that, when processed using the target’s private key, yield information needed for cryptanalysis
  
- To counter such attacks, RSA Security Inc. recommends modifying the plaintext using a procedure known as optimal asymmetric encryption padding (OAEP)