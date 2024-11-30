interface Saeugetier {
    int[] getAnzahlZitzen();
    default int getAnzahlNachkommen() {
        int durchschnittlicheZitzenAnzahl = 
            java.util.Arrays.stream(getAnzahlZitzen()).sum() / 2;
        return durchschnittlicheZitzenAnzahl / 2;
    }
}
