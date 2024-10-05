void main() {
    var weight = Double.parseDouble(readln("Bitte geben Sie Ihr Gewicht in kg ein: "));
    var height = Double.parseDouble(readln("Bitte geben Sie Ihre Größe in m ein: "));
    var bmi = weight / (height * height);
    println("Ihr BMI beträgt: " + bmi);
    println("Untergewicht: " + (bmi < 18.5 ? (18.5 * height * height) - weight + " kg bis Normalgewicht" : "nein"));
    println("Normalgewicht: " + (bmi >= 18.5 && bmi < 25 ? "ja" : "nein"));
    println("Übergewicht: " + (bmi >= 25 ? -(weight - (25 * height * height)) + " kg bis Normalgewicht" : "nein"));
}
