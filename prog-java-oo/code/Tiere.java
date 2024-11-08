class Tier {
    
    float maxDecibel = Float.NaN;

    String decibel(){
        return maxDecibel+"db";
    }

    void lautGeben() {
        System.out.println("Ein Tier macht ein Geräusch.");
    }
}

class Hund extends Tier {

    Hund() {
        this.maxDecibel = 100.0f;
    }   

    void lautGeben() {
        System.out.println("Der Hund bellt mit " + decibel()+".");
    }
}

class Katze extends Tier {

    
    
    Katze() {
        this.maxDecibel = 80.0f;
    }

    void lautGeben() {
        System.out.println("Die Katze miaut mit " + decibel()+".");
    }
}

void main() {
    new Tier().lautGeben();
    new Hund().lautGeben();
    new Katze().lautGeben();
}