var express = require('express');
var app = express();
var expressWs = require('express-ws')(app);

app.get('/', function (_, res) {
    res.end("Hello World");
})

app.ws('/time', function(ws, req) {
    setInterval(function() {
        ws.send(new Date().toLocaleTimeString());
    }, 1000);
    //ws.on('message', function(msg) {
    //  ws.send(msg);
    //});
});



var server = app.listen(8888, function () {
    console.log("Express App running at http://127.0.0.1:8888/");
})

/**
 * Verwendung (auf Client-Seite):
 * 
 * const ws = new WebSocket("ws://localhost:8888/time");
 * ws.addEventListener("message", (event) => {console.log(event.data)})
 * ws.close()
 */