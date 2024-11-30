interface Foo {
    int m();
}

interface Bar {
    default double m(){return 1;}
}

class FooBar implements Bar, Foo {
    public int m() { /* Main.java:10: error: m() in Main.FooBar 
                                             cannot implement m() in Bar
                                             public int m() {
                                                        ^
                                             return type int is not compatible 
                                             with double */
        return 2;
    }
}

void main() {
    FooBar fb = new FooBar();
    System.out.println(fb.m() + " " + ((Bar)fb).m());
}
