void main() {
    var age = Integer.parseInt(readln("Wie alt sind Sie?"));
    var adult = false;
    char status = 'c';

    if (age >= 18) {
        adult = true;
        status = 'b';
        if (age >= 30 && readln("Geschlecht (m/w/d)?").charAt(0) == 'm')
            status = 'a';
    }
    println("adult=" + adult+ ", status=" + status);
}
