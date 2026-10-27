import java.util.Scanner;

public class Latihan04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan NPM anda: ");
        long npm = scanner.nextLong();

        System.out.print("Masukkan nilai faktor (minimal 20): ");
        int n = scanner.nextInt();

        if (n < 20) {
            System.out.println("Nilai inputan factor tidak boleh kurang dari 20.");
        } else {
            if (npm % 2 == 0) {
                // Genap: Ascending
                System.out.print("ASC: Faktor " + n + " adalah ");
                boolean first = true;
                for (int i = 1; i <= n; i++) {
                    if (n % i == 0) {
                        if (!first) {
                            System.out.print(", ");
                        }
                        System.out.print(i);
                        first = false;
                    }
                }
                System.out.println();
            } else {
                // Ganjil: Descending
                System.out.print("DESC: Faktor " + n + " adalah ");
                boolean first = true;
                for (int i = n; i >= 1; i--) {
                    if (n % i == 0) {
                        if (!first) {
                            System.out.print(", ");
                        }
                        System.out.print(i);
                        first = false;
                    }
                }
                System.out.println();
            }
        }

        scanner.close();
    }
}