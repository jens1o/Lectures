void main() {
    var seconds = Integer.parseInt(readln("Bitte geben Sie die Sekunden ein: "));
    println(seconds / 3600 + " Stunde(n), " + seconds % 3600 / 60 + " Minute(n) und " + seconds % 60 + " Sekunde(n)");   
}