
double bmi(double weight, double height) {
    return weight / (height * height);
}

double bmi(String weight,String height) {
    return bmi(Double.parseDouble(weight), Double.parseDouble(height));
}

void main(String []args) {
    if (args.length != 2) {
        println("java BMI.java <Gewicht in kg> <Größe in Meter>");
        return;
    }
    final var bmi = bmi(args[0],args[1]);
    print("Ihr BMI beträgt: " + bmi);
    if (bmi < 18.5) {
        println(" - Untergewicht: ");
    } else if (bmi >= 18.5 && bmi < 25) {
        println(" - Normalgewicht");
    } else {
        println(" - Übergewicht");
    }
}
