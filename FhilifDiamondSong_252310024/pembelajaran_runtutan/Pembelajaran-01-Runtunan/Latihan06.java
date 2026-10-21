import java.util.Scanner;

public class Latihan06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan usia: ");
        String usia = scanner.nextLine();
        System.out.print("Masukkan firstname: ");
        String firstname = scanner.nextLine();
        System.out.print("Masukkan lastname: ");
        String lastname = scanner.nextLine();
        System.out.print("Masukkan NPM: ");
        String npm = scanner.nextLine();

        String hasil = usia.concat(firstname).concat(lastname).concat(npm);
        System.out.println("Hasil penggabungan: " + hasil);

        scanner.close();
    }
}
