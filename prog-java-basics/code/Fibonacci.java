// Naive Implementation zur Berechnung der Fibonacci-Zahlen
void main() {
    var n = Integer.parseInt(readln("Berechnung der n-ten Fibonacci-Zahl. Bitte n eingeben: "));
    switch (n) {
        case 0 -> { println("0"); }
        case 1 -> { println("1"); }
        case _ when n > 1 -> {
            var a = 0l;
            var b = 1l;
            long c = a + b;
            for (int i = 2; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }   
            println(c);
        }
        default -> {
            println("die Zahl muss größer gleich 0 sein");
        }
    }
}
