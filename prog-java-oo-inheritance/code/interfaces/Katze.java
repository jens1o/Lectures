public class Katze implements Haustier, Saeugetier {
    private String rufname;
    public Katze(String rufname) { this.rufname = rufname; }

    public String getRufname() { return rufname; }              // von Haustier
    public int[] getAnzahlZitzen() { return new int[] {6, 8 }; }// von S.-tier

    public String toString() {
        return "Katze { rufname = " + rufname + " }";   
}   }
