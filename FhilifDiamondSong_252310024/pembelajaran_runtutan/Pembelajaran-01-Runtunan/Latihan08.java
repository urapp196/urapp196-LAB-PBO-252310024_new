import java.util.Scanner;

public class Latihan08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama lengkap: ");
        String namaLengkap = scanner.nextLine();
        System.out.print("Masukkan nama kelas: ");
        String namaKelas = scanner.nextLine();
        System.out.print("Masukkan semester: ");
        String semester = scanner.nextLine();
        System.out.print("Masukkan NPM: ");
        String npm = scanner.nextLine();

        System.out.println("\n=== Data Mahasiswa ===");
        System.out.println("NPM: " + npm);
        System.out.println("Nama Lengkap: " + namaLengkap);
        System.out.println("Nama Kelas: " + namaKelas);
        System.out.println("Semester: " + semester);

        scanner.close();
    }
}
