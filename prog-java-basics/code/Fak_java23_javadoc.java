
public class Fak_java23_javadoc {

    /// Berechnet die Fakultät von n.
    ///
    /// @param n die Zahl, von der die Fakultät berechnet werden soll; (*0 <= n <= 20*).
    /// @return _die Fakultät von n_.
    public static long fak(long n) { 
        if (n == 0)
            return 1;
        else
            return n * fak(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(fak(5));
    }

}
