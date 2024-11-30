
void main(String[] args) {
    // Explicit Handling of the exceptions is necessary:
    // --------------------------------------------------
    try {
        Exceptions.m1();
    } catch (AThrowable e) {
        System.out.println(e.getMessage());
    }

    try {
        Exceptions.m2();
    } catch (AnException e) {
        System.out.println(e.getMessage());
    }

    // Explicit Handling of the exceptions is not 
    // necessary, but ist is possible (and required)
    // to ensure that the program runs to the end!
    // --------------------------------------------------

    try {
        Exceptions.m3();
    } catch (AnError e) {
        System.out.println(e.getMessage());
    }

    try {
        Exceptions.m4();
    } catch (ARuntimeException e) {
        System.out.println(e.getMessage());
    }

    System.out.println("Ende");
}
