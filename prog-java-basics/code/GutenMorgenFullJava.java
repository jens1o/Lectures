import static java.lang.System.out;
import static java.lang.System.console;


class GutenMorgenFullJava {

    public static void main(String [] args) {
        out.println("Hallo " + console().readln("Wie ist Dein Name? ") + "!");
        out.println(console().readln("Wo wohnst Du? ") + " ist wirklich schön!");
    }
}