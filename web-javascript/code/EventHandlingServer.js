const express = require('express');
const cors = require('cors')
const bodyParser = require('body-parser')

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/users', cors(), function (req, res) {
    res.end(`{
        "user1" : {
           "name" : "dingo",
           "password" : "1234",
           "profession" : "dealer",
           "id": 1
        },
        
        "user2" : {
           "name" : "bingo",
           "password" : "0987",
           "profession" : "farmer",
           "id": 2
        },
        
        "user3" : {
           "name" : "ringo",
           "password" : "asdf",
           "profession" : "boss",
           "id": 3
        }
     }`);
})

app.listen(4080, function () {
    console.log("Express App running at http://127.0.0.1:4080/");
})