import java.util.Scanner;

public class Calculator2 {
    static double hitung(double x, double y, char operator) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                if (y == 0) {
                    throw new ArithmeticException("Pembagian tidak boleh nol");
                }
                return x / y;
            default:
                throw new IllegalArgumentException("Operator invalid");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Calculator Sederhana ===");

        double x, y;
        char operator;
        char again;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Input x: ");
            x = scanner.nextDouble();

            System.out.print("Input y: ");
            y = scanner.nextDouble();

            System.out.print("Operator (+, -, *, /)= ");
            operator = scanner.next().charAt(0);

            try {
                double hasil = hitung(x, y, operator);
                System.out.println("Hasil: " + hasil);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Ulangi (Y/N): ");
            again = scanner.next().charAt(0);

        } while (again == 'Y' || again == 'y');
        scanner.close();
        System.out.println("Program selesai. Terima kasih!");
    }
}