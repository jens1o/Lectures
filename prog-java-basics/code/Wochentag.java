void main() {
    final var month = Integer.parseInt(readln("Welchen Monat haben wir (1-12)? "));
    final var day = Integer.parseInt(readln("Welchen Tag des Monats haben wir (1-28/29/30/31)? "));
    final var dayOnJanuary1st = Integer.parseInt(readln("Welcher Wochentag war der 1. Januar (0=Montag, 1=Dienstag, ..., 6=Sonntag)? "));
    final var leapYear = readln("Ist das Jahr ein Schaltjahr (j/n)? ").charAt(0) == 'j';

    var dayInYear = day; // Wir summieren hier die Tage des Jahres auf
    switch (month-1) {
        case 12: 
        case 11: dayInYear += 30;
        case 10: dayInYear += 31;
        case 9: dayInYear += 30;
        case 8: dayInYear += 31;
        case 7: dayInYear += 31;
        case 6: dayInYear += 30;
        case 5: dayInYear += 31;
        case 4: dayInYear += 30;
        case 3: dayInYear += 31;
        case 2: dayInYear += 28; if (leapYear) dayInYear++;
        case 1: dayInYear += 31;
                break;
        default: println("Fehler: Monat nicht bekannt");
    };
    println("Tag im Jahr: " + dayInYear);

    var dayInWeek = (dayOnJanuary1st + dayInYear) % 7;
    println("Tag in der Woche: " + dayInWeek);

    var dayInWeekName = switch (dayInWeek-1) {
        case 0 -> "Montag"; 
        case 1 -> "Dienstag"; 
        case 2 -> "Mittwoch"; 
        case 3 -> "Donnerstag"; 
        case 4 -> "Freitag"; 
        case 5 -> "Samstag"; 
        case 6 -> "Sonntag";
        default -> "Fehler: Tag nicht bekannt";
    };
    println("Der " + day + "." + month + ". ist ein " + dayInWeekName);
}