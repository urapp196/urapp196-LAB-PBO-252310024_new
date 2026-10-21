
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListApp {
    public static void main(String[] args) {
        ArrayList<String> nama = new ArrayList<String>();
        nama.add("Faisal");
        System.out.println(nama.get(0));
        nama.add("nakalan");
        System.out.println(nama.get(1));

        Scanner scanner = new  Scanner(System.in);
        nama.add(scanner.nextLine());
        System.out.println("Nama setelah di input: " + nama.getLast());

        nama.remove(1);
        System.out.println("Nama setelah dihapus: " + nama.getLast());

        nama.clear();
    }
}