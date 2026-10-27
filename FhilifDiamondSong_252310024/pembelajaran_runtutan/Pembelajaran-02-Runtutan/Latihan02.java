import java.util.Scanner;

public class Latihan02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nilai NPM: ");
        long npm = scanner.nextLong();

        boolean isPrima = true;

        if (npm <= 1) {
            isPrima = false;
        } else {
            for (long i = 2; i <= Math.sqrt(npm); i++) {
                if (npm % i == 0) {
                    isPrima = false;
                    break;
                }
            }
        }

        if (isPrima) {
            System.out.println("Termasuk bilangan PRIMA");
        } else {
            System.out.println("Bukan termasuk bilangan prima karena dapat dibagi dengan 2");
        }

        scanner.close();
    }
}
