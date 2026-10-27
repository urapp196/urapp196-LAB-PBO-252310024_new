import java.util.Scanner;

public class Latihan06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char lanjut;

        do {
            System.out.println("Menu Sarapan:");
            System.out.println("1. Nasi Goreng");
            System.out.println("2. Bubur Ayam");
            System.out.println("3. Soto Ayam");
            System.out.print("Pilih menu (1-3): ");

            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("Anda memesan Nasi Goreng dengan harga Rp 22.000,-");
                    break;
                case 2:
                    System.out.println("Anda memesan Bubur Ayam dengan harga Rp15.000,-");
                    break;
                case 3:
                    System.out.println("Anda memesan Soto Ayam dengan harga Rp 25.000,-");
                    break;
                default:
                    System.out.println("Maaf menu yang anda masukan salah.");
                    break;
            }

            System.out.print("Apakah anda akan memesan kembali ? (y/n): ");
            lanjut = scanner.next().toLowerCase().charAt(0);
            System.out.println();

        } while (lanjut == 'y');

        scanner.close();
    }
}