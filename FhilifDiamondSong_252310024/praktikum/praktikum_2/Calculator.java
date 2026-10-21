import java.util.Scanner;

public class Calculator {

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
                    throw new Error("Pembagian tidak bleh nol");
                }
                return x / y;
            default :
                throw new Error("Operator invalid");
        }
    }


    public static void main(String[] args) {
        System.err.println("Caculator Sederhana");

        double x, y;
        char operator;
        char again = 'Y';
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Input x: ");
            x = scanner.nextDouble();

            System.out.print("Input y: ");
            y = scanner.nextDouble();

            System.out.print("Operator (+, -, *, /)= ");
            operator = scanner.next().charAt(0);

            double hasil = hitung(x, y, operator);
            System.out.println("Hasil: " + hasil);

            System.out.println("Ulangi (Y/N)");
            again = scanner.next().charAt(0);

        } while (again == 'Y');

    }
}
