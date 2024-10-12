boolean isPrim(int n) {
    if (n < 2) {
        return false;
    }

    boolean isPrim = true;    
    for (int i = 2; i < n / 2; i++) {
        if (n % i == 0) {
            isPrim = false;
            break;
        }
    }
    return isPrim;
}

void main() {
    final var n = Integer.parseInt(readln("Geben Sie eine ganze positive Zahl ein? "));
    if (isPrim(n)) {
        System.out.println(n + " ist eine Primzahl");
    } else {
        System.out.println(n + " ist keine Primzahl");
    }
}
