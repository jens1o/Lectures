void main() {
    var age = Integer.parseInt(readln("Wie alt sind Sie?"));
    boolean adult = false;

    if (age >= 18) { // if-Anweisung
        adult = true;
    }

    println("adult=" + adult);
}