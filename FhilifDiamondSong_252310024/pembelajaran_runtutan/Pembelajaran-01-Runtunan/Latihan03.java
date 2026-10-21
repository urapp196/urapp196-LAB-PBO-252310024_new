import java.util.Scanner;

public class Latihan03 {
    private static final double PI = Math.PI;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Program Luas Permukaan dan Volume Bangun Ruang ===");
        System.out.println("1. Kerucut");
        System.out.println("2. Tabung");
        System.out.println("3. Prisma Segitiga");
        System.out.print("Pilih bangun ruang (1-3): ");
        int pilihan = scanner.nextInt();

        if (pilihan == 1) {
            System.out.print("Masukkan jari-jari kerucut: ");
            double jariJari = scanner.nextDouble();
            System.out.print("Masukkan tinggi kerucut: ");
            double tinggi = scanner.nextDouble();

            if (jariJari <= 0 || tinggi <= 0) {
                System.out.println("Jari-jari dan tinggi harus lebih dari 0.");
            } else {
                double garisPelukis = Math.sqrt(jariJari * jariJari + tinggi * tinggi);
                double luasPermukaan = hitungLuasPermukaanKerucut(jariJari, garisPelukis);
                double volume = hitungVolumeKerucut(jariJari, tinggi);

                System.out.printf("Luas permukaan kerucut: %.2f%n", luasPermukaan);
                System.out.printf("Volume kerucut: %.2f%n", volume);
            }
        } else if (pilihan == 2) {
            System.out.print("Masukkan jari-jari tabung: ");
            double jariJari = scanner.nextDouble();
            System.out.print("Masukkan tinggi tabung: ");
            double tinggi = scanner.nextDouble();

            if (jariJari <= 0 || tinggi <= 0) {
                System.out.println("Jari-jari dan tinggi harus lebih dari 0.");
            } else {
                double luasPermukaan = hitungLuasPermukaanTabung(jariJari, tinggi);
                double volume = hitungVolumeTabung(jariJari, tinggi);

                System.out.printf("Luas permukaan tabung: %.2f%n", luasPermukaan);
                System.out.printf("Volume tabung: %.2f%n", volume);
            }
        } else if (pilihan == 3) {
            System.out.print("Masukkan sisi pertama alas segitiga: ");
            double sisiA = scanner.nextDouble();
            System.out.print("Masukkan sisi kedua alas segitiga: ");
            double sisiB = scanner.nextDouble();
            System.out.print("Masukkan sisi ketiga alas segitiga: ");
            double sisiC = scanner.nextDouble();
            System.out.print("Masukkan panjang prisma: ");
            double panjangPrisma = scanner.nextDouble();

            if (!segitigaValid(sisiA, sisiB, sisiC) || panjangPrisma <= 0) {
                System.out.println("Ukuran sisi segitiga atau panjang prisma tidak valid.");
            } else {
                double luasAlas = hitungLuasAlasSegitiga(sisiA, sisiB, sisiC);
                double luasPermukaan = hitungLuasPermukaanPrisma(
                        luasAlas, sisiA, sisiB, sisiC, panjangPrisma);
                double volume = hitungVolumePrisma(luasAlas, panjangPrisma);

                System.out.printf("Luas permukaan prisma segitiga: %.2f%n", luasPermukaan);
                System.out.printf("Volume prisma segitiga: %.2f%n", volume);
            }
        } else {
            System.out.println("Pilihan tidak valid. Pilih angka 1 sampai 3.");
        }

        scanner.close();
    }

    private static double hitungLuasPermukaanKerucut(double jariJari, double garisPelukis) {
        return PI * jariJari * (jariJari + garisPelukis);
    }

    private static double hitungVolumeKerucut(double jariJari, double tinggi) {
        return PI * jariJari * jariJari * tinggi / 3;
    }

    private static double hitungLuasPermukaanTabung(double jariJari, double tinggi) {
        return 2 * PI * jariJari * (jariJari + tinggi);
    }

    private static double hitungVolumeTabung(double jariJari, double tinggi) {
        return PI * jariJari * jariJari * tinggi;
    }

    private static boolean segitigaValid(double sisiA, double sisiB, double sisiC) {
        return sisiA > 0 && sisiB > 0 && sisiC > 0
                && sisiA + sisiB > sisiC
                && sisiA + sisiC > sisiB
                && sisiB + sisiC > sisiA;
    }

    private static double hitungLuasAlasSegitiga(double sisiA, double sisiB, double sisiC) {
        double setengahKeliling = (sisiA + sisiB + sisiC) / 2;
        return Math.sqrt(setengahKeliling
                * (setengahKeliling - sisiA)
                * (setengahKeliling - sisiB)
                * (setengahKeliling - sisiC));
    }

    private static double hitungLuasPermukaanPrisma(
            double luasAlas, double sisiA, double sisiB, double sisiC, double panjangPrisma) {
        double kelilingAlas = sisiA + sisiB + sisiC;
        return 2 * luasAlas + kelilingAlas * panjangPrisma;
    }

    private static double hitungVolumePrisma(double luasAlas, double panjangPrisma) {
        return luasAlas * panjangPrisma;
    }
}
