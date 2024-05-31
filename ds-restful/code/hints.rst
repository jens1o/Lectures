Falls es zu einem EADDRINUSE kommt
===================================

Um die PID des Prozesses zu finden, die den Port (hier: Port 5000) nutzt:

.. code:: bash
   
   $ lsof -i :5000
   $ ls -e | grep <PID>


Senden von HTTP POST mit Hilfe von CURL
=======================================

Request
_________

.. code:: bash

   $ curl -X POST -H "Content-Type: application/json" -d '{    "user4" : {       "name" : "Jango",       "password" : "luke",       "profession" : "headhunter",       "id": 4    } }' http://localhost:4080/


Response
_________

.. code:: json

   {
       "user1": {
           "name": "dingo",
           "password": "1234",
           "profession": "dealer",
           "id": 1
       },
       "user2": {
           "name": "bingo",
           "password": "0987",
           "profession": "farmer",
           "id": 2
       },
       "user3": {
           "name": "ringo",
           "password": "asdf",
           "profession": "boss",
           "id": 3
       },
       "user4": {
           "name": "Jango",
           "password": "luke",
           "profession": "headhunter",
           "id": 4
       }
   }