
/**
 * Returns whether a year is a leap year.
 * 
 * @param year The year to check. Year >= 0.
 * @return true if the year is a leap year, false otherwise.
 */
boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}

/**
 * Returns the number of days in a year.    
 * 
 * @param year The year for which you want to know the number of days. Year >= 0.
 * @return The number of days in the year. 365 or 366.  
 */
int numberOfDays(int year) {
    return isLeapYear(year) ? 366 : 365;
}

/**
 * Returns the day in the year for a given date.
 * 
 * @param year  The year of the date. Year >= 0.
 * @param month The month of the date. 1 <= month <= 12.
 * @param day   The day of the date. 1 <= day <= 28/29/30/31.
 * @return The day in the year for the given date. 1 <= dayInYear <= 366.
 */
int dayInYear(int year, int month, int day) {
    var dayInYear = day;
    switch (month - 1) {
        case 11:
            dayInYear += 30;
        case 10:
            dayInYear += 31;
        case 9:
            dayInYear += 30;
        case 8:
            dayInYear += 31;
        case 7:
            dayInYear += 31;
        case 6:
            dayInYear += 30;
        case 5:
            dayInYear += 31;
        case 4:
            dayInYear += 30;
        case 3:
            dayInYear += 31;
        case 2:
            dayInYear += 28;
            if (isLeapYear(year))
                dayInYear++;
        case 1:
            dayInYear += 31;
        case 0:
            break;
        default:
            println("Fehler: Monat nicht bekannt");
            System.exit(-1);
            return -1; // effectively unreachable code
    }
    return dayInYear;
}

void main() {
    final var birthYear = Integer.parseInt(
            readln("In welchem Jahr sind Sie geboren? "));
    final var birthMonth = Integer.parseInt(
            readln("In welchem Monat sind Sie geboren (1-12)? "));
    final var birtDayInMonth = Integer.parseInt(
            readln("An welchem Tag des Monats sind Sie geboren (1-28/29/30/31)? "));

    final var currentYear = Integer.parseInt(
            readln("Welches Jahr haben wir? "));
    final var currentMonth = Integer.parseInt(
            readln("Welchen Monat haben wir (1-12)? "));
    final var currentDayInMonth = Integer.parseInt(
            readln("Welchen Tag des Monats haben wir (1-28/29/30/31)? "));

    if (currentYear < birthYear) {
        println("Fehler: Das aktuelle Jahr ist kleiner als das Geburtsjahr.");
        System.exit(-1);
    }
    if (currentYear == birthYear && currentMonth < birthMonth) {
        println("Fehler: Der aktuelle Monat ist kleiner als der Geburtsmonat.");
        System.exit(-1);
    }
    if (currentYear == birthYear && currentMonth == birthMonth && currentDayInMonth < birtDayInMonth) {
        println("Fehler: Der aktuelle Tag ist kleiner als der Geburtstag.");
        System.exit(-1);
    }

    if (currentYear == birthYear) {
        println("Sie haben " + (dayInYear(currentYear, currentMonth, currentDayInMonth)
                - dayInYear(birthYear, birthMonth, birtDayInMonth)) + " Tage gelebt.");
    } else {
        var daysLived = dayInYear(currentYear, currentMonth, currentDayInMonth) +
                numberOfDays(birthYear) - dayInYear(birthYear, birthMonth, birtDayInMonth);
        for (int year = birthYear + 1; year < currentYear; year++) {
            daysLived += numberOfDays(year);
        }
        println("Sie haben " + daysLived + " Tage gelebt.");
    }
}