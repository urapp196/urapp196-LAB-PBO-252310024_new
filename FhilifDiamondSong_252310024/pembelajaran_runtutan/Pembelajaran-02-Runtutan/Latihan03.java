import java.util.Scanner;

public class Latihan03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        scanner.close();
    }
}