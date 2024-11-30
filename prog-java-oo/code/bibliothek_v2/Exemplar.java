

class Exemplar {
    int nr;
    int regal;
    int position;

    Benutzer ausgeliehenAn;

    Exemplar(int nr, int regal, int position) {
        this.nr = nr;
        this.regal = regal;
        this.position = position;
    }

    void verleihe(Benutzer benutzer) {
        ausgeliehenAn = benutzer;
    }

    void print() {
        System.out.println("Exemplar-Nr: " + nr);
        System.out.println("Regal: " + regal);
        System.out.println("Position: " + position);
        if (ausgeliehenAn != null) {
            System.out.println("Ausgeliehen an: " + ausgeliehenAn.vorname + " " + ausgeliehenAn.nachname);
        }
    }
}
