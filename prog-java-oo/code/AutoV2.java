class Auto {
    String marke;           
    int geschwindigkeit;    

    Auto(String marke) {
        this.marke = marke;                     
    }

    Auto(String marke, int geschwindigkeit) {
        this(marke); // Aufruf des anderen Konstruktors

        this.geschwindigkeit = geschwindigkeit;
    }
}

void main() {
    new Auto("VW", 0);
}