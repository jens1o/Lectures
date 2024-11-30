class Auto {
    String marke;           
    int geschwindigkeit;    

    Auto(String marke) { this.marke = marke; }

    Auto(String marke, int geschwindigkeit) {
        if (geschwindigkeit < 0) throw new IllegalArgumentException("Geschwindigkeit darf nicht negativ sein.");
        this.geschwindigkeit = geschwindigkeit;
        // NOT ALLOWED: System.out.println(this.alsString());
        this(marke); // Aufruf des anderen Konstruktors
    }

    String alsString() {
        return "Auto: " + this.marke + " " + this.geschwindigkeit;
    }
}

void main() {
    new Auto("VW", -1);
}