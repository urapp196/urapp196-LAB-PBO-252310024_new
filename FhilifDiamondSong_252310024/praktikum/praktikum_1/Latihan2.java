

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Latihan2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double HARGA_PER_M3 = 5000000.0;

        System.out.println("=== PROGRAM PERHITUNGAN TOTAL HARGA VOLUME ===");
        System.out.println("Tarif: Rp5.000.000 / m³\n");

        System.out.print("Masukkan luas (m²): ");
        double luas = scanner.nextDouble();

        System.out.print("Masukkan tebal/tinggi (m) [masukkan 1 jika luas = volume]: ");
        double tinggi = scanner.nextDouble();

        double volume = luas * tinggi;

        double totalHarga = volume * HARGA_PER_M3;

        Locale localeID = new Locale("id", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        System.out.println("\n--- HASIL PERHITUNGAN ---");
        System.out.printf("Total Volume : %.2f m³\n", volume);
        System.out.println("Total Harga  : " + formatRupiah.format(totalHarga));

        scanner.close();
    }
}
