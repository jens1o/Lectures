class Auto {
    String marke;
    int geschwindigkeit;

    void beschleunigen(int wert) {
        this.geschwindigkeit += wert; // this. ist hier optional
    }
    String alsString() {
        return "Auto: " + this.marke + " " + /*this.*/geschwindigkeit;
    }
}

void main() {
    Auto a = new Auto();
    a.marke = "VW";
    a.geschwindigkeit = 0;
    a.beschleunigen(50);
    System.out.println(a.alsString());
}