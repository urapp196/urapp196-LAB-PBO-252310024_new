public class Main {
    public static void main(String[] args) {
        System.out.println("Mulai dari Main.java...");

        // Membuat objek dari Pekerja.java dan memanggil method-nya
        Pekerja worker = new Pekerja();
        worker.jalankanTugas();

        System.out.println("Selesai dieksekusi di Main.java.");
    }
}