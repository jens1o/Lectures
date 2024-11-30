class Person {
    String name;
    int age;
    void display() {
        System.out.print("Name: " + name + " Age: " + age);
    }   
}

class Kind extends Person {
    String hobby;
    void display() {
        super.display(); 
        System.out.print(" Hobby: " + hobby);
    }
}


void main() {
    Kind k = new Kind();
    k.name = "John"; k.age = 12; k.hobby = "Football";
    k.display();
}
