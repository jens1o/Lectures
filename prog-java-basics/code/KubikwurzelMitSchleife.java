void main() {
    final var n = Double.parseDouble(readln("""
            Geben Sie eine Zahl n ein deren Kubikwurzel w Sie berechnen wollen
            (d.h. n = w*w*w): """));
    final var steps = Integer.parseInt(readln("Wie viele Schritte wollen Sie machen? "));        

    var guess = 1.0d;
    for (int i = 0; i < steps; i++) {
        guess = guess - (guess * guess * guess - n) / (3d * guess * guess);
        println("guess: " + guess);
    }

    println("Das Ergebnis ist: " + guess);
}
// Die Kubikwurzel von 2.251.748.274.470.911 ist (die Primzahl) 131.071 (2^17-1).