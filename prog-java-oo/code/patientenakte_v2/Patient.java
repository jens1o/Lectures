
class Patient {
    private String geburtsdatum;
    private String name;
    private int groesse; // in Zentimeter
    private double gewicht; // in Kilogramm

    Patient(String geburtsdatum, String name, int groesse, double gewicht) {
        this.geburtsdatum = geburtsdatum;
        this.name = name;
        this.groesse = groesse;
        this.gewicht = gewicht;
    }

    public int getGroesse() {
        return groesse;
    }

    public double getGewicht() {
        return gewicht;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getName() {
        return name;
    }

}



