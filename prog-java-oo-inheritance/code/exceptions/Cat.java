import static java.lang.System.err;

void main(String []args){
    try (var in = new BufferedReader(new FileReader(args[0]))) {
        String line;
        while ((line = in.readLine()) != null) { println(line); }   
    } catch (IOException e) {
        err.println("Error: " + e.getMessage());
    }
}

