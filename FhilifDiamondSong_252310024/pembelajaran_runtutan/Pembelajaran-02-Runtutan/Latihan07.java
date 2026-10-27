import java.util.Scanner;

class Resto {
    private Long Harga;

    private Long getPrices(int pilihan) {
        switch (pilihan) {
            case 1:
                this.Harga = 22000L;
                break;
            case 2:
                this.Harga = 15000L;
                break;
            case 3:
                this.Harga = 25000L;
                break;
            default:
                this.Harga = 0L;
                break;
        }
        return this.Harga;
    }

    public void chooseMenu(Scanner scanner) {
        System.out.println("\n=== MENU RESTO ===");
        System.out.println("1. Menu 1 - Rp 22.000");
        System.out.println("2. Menu 2 - Rp 15.000");
        System.out.println("3. Menu 3 - Rp 25.000");
        System.out.print("Masukkan pilihan menu (1-3): ");
        
        int pilihan = scanner.nextInt();
        Long hargaMenu = getPrices(pilihan);

        if (hargaMenu > 0) {
            System.out.println("Harga yang harus dibayar: Rp " + hargaMenu);
        } else {
            System.out.println("Pilihan menu tidak valid!");
        }
    }
}

public class Latihan07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Resto resto = new Resto();
        char pesanKembali;

        do {
            resto.chooseMenu(scanner);

            System.out.print("\nApakah anda akan memesan kembali ? (y/n): ");
            pesanKembali = scanner.next().toLowerCase().charAt(0);
        } while (pesanKembali == 'y');

        System.out.println("Terimakasih telah memesan.");
        scanner.close();
    }
}