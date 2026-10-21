import java.util.Scanner;

public class Latihan01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan suhu dalam Celcius: ");
        double celcius = scanner.nextDouble();

        double fahrenheit = (celcius * 9 / 5) + 32;
        double reamur = celcius * 4 / 5;
        double kelvin = celcius + 273.15;

        System.out.printf("Fahrenheit: %.2f%n", fahrenheit);
        System.out.printf("Reamur: %.2f%n", reamur);
        System.out.printf("Kelvin: %.2f%n", kelvin);

        scanner.close();
    }
}
