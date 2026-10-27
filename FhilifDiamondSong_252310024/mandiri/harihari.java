import java.util.ArrayList;
import javax.swing.JOptionPane;

public class harihari {
    public static void main(String[] args) {
        ArrayList<String> daftarBuku = new ArrayList<>();
        int pilihan;

        do {
            String menu = "=== SISTEM MANAJEMEN BUKU ===\n"
                    + "Total Buku: " + daftarBuku.size() + "\n\n"
                    + "1. Tambah Buku\n"
                    + "2. Lihat Semua Buku\n"
                    + "3. Cari Buku (Berdasarkan Indeks)\n"
                    + "4. Cari Buku (Berdasarkan Nama)\n"
                    + "5. Hapus Buku (Berdasarkan Indeks / Nama)\n"
                    + "0. Keluar\n\n"
                    + "Pilih menu (0-5):";

            String inputMenu = JOptionPane.showInputDialog(null, menu, "Menu Utama", JOptionPane.QUESTION_MESSAGE);

            // Jika user menekan tombol Cancel atau close window
            if (inputMenu == null) {
                break;
            }

            try {
                pilihan = Integer.parseInt(inputMenu.trim());
            } catch (NumberFormatException e) {
                pilihan = -1; // Nilai default jika input bukan angka
            }

            switch (pilihan) {
                case 1: // Tambah Buku
                    String namaBuku = JOptionPane.showInputDialog(null, "Masukkan nama buku:", "Tambah Buku", JOptionPane.QUESTION_MESSAGE);
                    if (namaBuku != null && !namaBuku.trim().isEmpty()) {
                        daftarBuku.add(namaBuku.trim());
                        JOptionPane.showMessageDialog(null, "Buku \"" + namaBuku.trim() + "\" berhasil ditambahkan pada indeks ke-" + (daftarBuku.size() - 1) + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case 2: // Lihat Semua Buku
                    if (daftarBuku.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Daftar buku masih kosong.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder sb = new StringBuilder("Daftar Buku:\n");
                        for (int i = 0; i < daftarBuku.size(); i++) {
                            sb.append("[").append(i).append("] ").append(daftarBuku.get(i)).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString(), "Daftar Buku", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case 3: // Cari Buku Berdasarkan Indeks
                    if (daftarBuku.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Daftar buku masih kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    String inputIndeks = JOptionPane.showInputDialog(null, "Masukkan indeks buku (0 - " + (daftarBuku.size() - 1) + "):", "Cari via Indeks", JOptionPane.QUESTION_MESSAGE);
                    if (inputIndeks != null) {
                        try {
                            int idx = Integer.parseInt(inputIndeks.trim());
                            if (idx >= 0 && idx < daftarBuku.size()) {
                                JOptionPane.showMessageDialog(null, "Buku pada indeks ke-" + idx + ": " + daftarBuku.get(idx), "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Indeks tidak ditemukan! Rentang valid: 0 sampai " + (daftarBuku.size() - 1), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Input harus berupa angka bulat!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4: // Cari Buku Berdasarkan Nama
                    if (daftarBuku.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Daftar buku masih kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    String keyword = JOptionPane.showInputDialog(null, "Masukkan nama atau kata kunci buku:", "Cari via Nama", JOptionPane.QUESTION_MESSAGE);
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        StringBuilder hasilCari = new StringBuilder();
                        boolean ditemukan = false;

                        for (int i = 0; i < daftarBuku.size(); i++) {
                            // Pencarian case-insensitive (mengabaikan huruf besar/kecil)
                            if (daftarBuku.get(i).toLowerCase().contains(keyword.trim().toLowerCase())) {
                                hasilCari.append("- Indeks [").append(i).append("]: ").append(daftarBuku.get(i)).append("\n");
                                ditemukan = true;
                            }
                        }

                        if (ditemukan) {
                            JOptionPane.showMessageDialog(null, "Hasil pencarian untuk \"" + keyword + "\":\n" + hasilCari, "Ditemukan", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Buku dengan kata kunci \"" + keyword + "\" tidak ditemukan.", "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;

                case 5: // Hapus Buku
                    if (daftarBuku.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Daftar buku masih kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    String[] opsiHapus = {"Hapus via Indeks", "Hapus via Nama", "Batal"};
                    int modeHapus = JOptionPane.showOptionDialog(
                            null,
                            "Pilih metode penghapusan:",
                            "Hapus Buku",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opsiHapus,
                            opsiHapus[0]
                    );

                    if (modeHapus == 0) { // Hapus via Indeks
                        String idxDelStr = JOptionPane.showInputDialog(null, "Masukkan indeks yang ingin dihapus (0 - " + (daftarBuku.size() - 1) + "):", "Hapus via Indeks", JOptionPane.QUESTION_MESSAGE);
                        if (idxDelStr != null) {
                            try {
                                int idxDel = Integer.parseInt(idxDelStr.trim());
                                if (idxDel >= 0 && idxDel < daftarBuku.size()) {
                                    String removed = daftarBuku.remove(idxDel);
                                    JOptionPane.showMessageDialog(null, "Buku \"" + removed + "\" pada indeks ke-" + idxDel + " berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Indeks di luar rentang!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Input harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (modeHapus == 1) { // Hapus via Nama
                        String namaDel = JOptionPane.showInputDialog(null, "Masukkan nama buku persis yang ingin dihapus:", "Hapus via Nama", JOptionPane.QUESTION_MESSAGE);
                        if (namaDel != null && !namaDel.trim().isEmpty()) {
                            boolean terhapus = false;
                            for (int i = 0; i < daftarBuku.size(); i++) {
                                if (daftarBuku.get(i).equalsIgnoreCase(namaDel.trim())) {
                                    daftarBuku.remove(i);
                                    terhapus = true;
                                    break;
                                }
                            }

                            if (terhapus) {
                                JOptionPane.showMessageDialog(null, "Buku \"" + namaDel.trim() + "\" berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Buku \"" + namaDel.trim() + "\" tidak ditemukan.", "Gagal", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                    break;

                case 0: // Keluar
                    JOptionPane.showMessageDialog(null, "Program selesai. Sampai jumpa!", "Keluar", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid, silakan masukkan angka 0 - 5.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    break;
            }

        } while (pilihan != 0);
    }
}