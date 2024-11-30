

class Bibliothek{
    String standort;
    String institution;

    int anzahlBuecher;
    Buch[] buecher = new Buch[100];

    int anzahlBenutzer;
    Benutzer[] benutzer = new Benutzer[20];

    Bibliothek(String standort, String institution){
        this.standort = standort;
        this.institution = institution;
    }

    void addBuch(Buch b){
        buecher[anzahlBuecher] = b;
        anzahlBuecher++;
    }

    void addBenutzer(Benutzer b){
        benutzer[anzahlBenutzer] = b;
        anzahlBenutzer++;
    }

    void print(){
        System.out.println("Bibliothek: " + institution + " in " + standort);
        System.out.println("Buecher:");
        System.out.println("Anzahl Buecher: " + anzahlBuecher);
        for (int i = 0; i < anzahlBuecher; i++){
            buecher[i].print();
        }
        System.out.println("Benutzer:");
        for (int i = 0; i < anzahlBenutzer; i++){
            benutzer[i].print();
        }
        System.out.println("Anzahl Benutzer: " + anzahlBenutzer);
    }
}


