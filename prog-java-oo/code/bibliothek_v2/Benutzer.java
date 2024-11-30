

class Benutzer {
    String vorname;
    String nachname;
    int nr;

    Benutzer(String vorname, String nachname, int nr) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.nr = nr;
    }

    void print() {
        System.out.print("Vorname: " + vorname);
        System.out.print("; Nachname: " + nachname);
        System.out.println("; Benutzer-Nr: " + nr);
    }
}
