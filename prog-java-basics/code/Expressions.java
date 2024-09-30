void main() {
    String s = readln("Enter your age: ");
    int age = Integer.parseInt(s);

    if (age >= 18) {
        println("You are an adult.");
    } else {
        println("You are a minor.");
    }

    var yearsUntil100 = 100-age;
    println("You will be 100 in " + yearsUntil100 + " years.");
}