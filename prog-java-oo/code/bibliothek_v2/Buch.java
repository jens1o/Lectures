class Buch {
    String titel;
    String autor;
    int jahr;
    String isbn;

    int anzahlExemplare;
    Exemplar[] exemplare = new Exemplar[10];

    Buch(String titel, String autor, int jahr, String isbn) {
        this.titel = titel;
        this.autor = autor;
        this.jahr = jahr;
        this.isbn = isbn;
    }

    void addExemplar(Exemplar e) {
        exemplare[anzahlExemplare] = e;
        anzahlExemplare++;
    }

    void print() {
        System.out.println("Buch[ " + titel + "; " + autor + "; " + jahr + "ISBN: " + isbn + " Exemplare["
                + anzahlExemplare + "]: ");
        for (int i = 0; i < anzahlExemplare; i++) {
            exemplare[i].print();
        }
        System.out.println("]");
    }
}
