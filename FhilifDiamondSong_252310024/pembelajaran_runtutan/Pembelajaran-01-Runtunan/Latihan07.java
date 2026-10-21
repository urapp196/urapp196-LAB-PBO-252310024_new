import java.util.Scanner;

public class Latihan07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama lengkap: ");
        String namaLengkap = scanner.nextLine();
        String namaDenganVokalDiganti = namaLengkap.replaceAll("[AEIOUaeiou]", "X");

        System.out.println("Nama setelah huruf vokal diganti: " + namaDenganVokalDiganti);

        scanner.close();
    }
}
