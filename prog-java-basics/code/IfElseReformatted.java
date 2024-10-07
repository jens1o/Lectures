void main() {
    println("Anzahl der Tage in einem Monat");

    var month = Integer.parseInt(readln("Welchen Monate haben wir(1-12)? "));

    int days = 31;
    if (month == 2 && readln("Schaltjahr (j/n)? ").charAt(0) == 'j') 
        days = 29;
    else // "nur" umformatiert
        if (month == 2)
            days = 28;
        else 
            if (month == 4)
                days = 30;
            // else ...
    println("days=" + days);
}