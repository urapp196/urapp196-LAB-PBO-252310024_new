import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Latihan04 {
    private static final String NAMA_TOKO = "TOKO SERBAGUNA IBIK";
    private static final String NAMA_PRODUK = "ROTI ENAK";
    private static final long HARGA_SATUAN = 6300;
    private static final int DISKON_PERSEN = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah produk yang dibeli: ");
        int jumlahProduk = scanner.nextInt();

        if (jumlahProduk <= 0) {
            System.out.println("Jumlah produk harus lebih dari 0.");
            scanner.close();
            return;
        }

        long subtotal = hitungSubtotal(jumlahProduk);
        int persentaseDiskon = jumlahProduk % 3 == 0 ? DISKON_PERSEN : 0;
        long nilaiDiskon = hitungDiskon(subtotal, persentaseDiskon);
        long total = subtotal - nilaiDiskon;
        LocalDateTime waktuTransaksi = LocalDateTime.now();

        cetakKwitansi(jumlahProduk, subtotal, persentaseDiskon, nilaiDiskon, total, waktuTransaksi);

        scanner.close();
    }

    private static long hitungSubtotal(int jumlahProduk) {
        return HARGA_SATUAN * jumlahProduk;
    }

    private static long hitungDiskon(long subtotal, int persentaseDiskon) {
        return subtotal * persentaseDiskon / 100;
    }

    private static void cetakKwitansi(
            int jumlahProduk,
            long subtotal,
            int persentaseDiskon,
            long nilaiDiskon,
            long total,
            LocalDateTime waktuTransaksi) {
        NumberFormat formatAngka = NumberFormat.getIntegerInstance(Locale.forLanguageTag("id-ID"));
        DateTimeFormatter formatWaktu =
                DateTimeFormatter.ofPattern("d MMM uuuu (HH:mm)", Locale.ENGLISH);

        System.out.println("============================================================");
        System.out.printf("%35s%n", NAMA_TOKO);
        System.out.println("============================================================");
        System.out.println("Waktu transaksi: " + waktuTransaksi.format(formatWaktu));
        System.out.printf("%-20s %8s %14s %14s%n", "ITEM", "QTY", "HARGA", "TOTAL");
        System.out.println("------------------------------------------------------------");
        System.out.printf(
                "%-20s %8d %14s %14s%n",
                NAMA_PRODUK + ".",
                jumlahProduk,
                formatRupiah(formatAngka, HARGA_SATUAN),
                formatRupiah(formatAngka, subtotal));
        System.out.println("------------------------------------------------------------");
        System.out.printf("Diskon (%d%%): %s%n", persentaseDiskon,
                formatRupiah(formatAngka, nilaiDiskon));
        System.out.printf("Sub Total: %s%n", formatRupiah(formatAngka, total));
        System.out.println("============================================================");
    }

    private static String formatRupiah(NumberFormat formatAngka, long nilai) {
        return "Rp " + formatAngka.format(nilai) + ",-";
    }
}
