import java.util.Arrays;

class Arzt {
    
    private Patient[] patienten = new Patient[0];

    void addPatient(Patient neuerPatient) {
        for (var patient : patienten) {
           if (patient == neuerPatient) {
               return;
           }
        }
        patienten = Arrays.copyOf(patienten, patienten.length + 1);
        patienten[patienten.length - 1] = neuerPatient;
    }

    
    double berechneDurschnittsgroesse() {
        double durchschnittsGroesse = 0;
        for (var patient : patienten) {
            durchschnittsGroesse += (double)patient.getGroesse() / patienten.length;
        }
        return durchschnittsGroesse;
    }

}



