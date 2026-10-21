import java.util.Locale;
import java.util.Scanner;

public class Latihan05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan teks: ");
        String teks = scanner.nextLine();
        String teksHurufBesar = teks.toUpperCase(Locale.ROOT);

        System.out.println("Hasil huruf besar: " + teksHurufBesar);

        scanner.close();
    }
}
