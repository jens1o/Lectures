long fibonacci(int n) {
    return switch(n) {
        case 0 ->  0l;
        case 1 ->  1l;
        default -> { yield fibonacci(n - 1) + fibonacci(n - 2); }
    };
}

void main() {
    var n = Integer.parseInt(readln("Berechnung der n-ten Fibonacci-Zahl. Bitte n eingeben: "));
    if (n >= 0) {
        println(fibonacci(n));
    } else {
        println("die Zahl muss größer gleich 0 sein");
    }
}
