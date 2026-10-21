import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ==========================================
// 1. ENUMS & CUSTOM EXCEPTIONS
// ==========================================

enum KategoriProduk {
    MAKANAN,
    MINUMAN,
    ELEKTRONIK,
    KEBUTUHAN_RUMAH
}

enum MetodePembayaran {
    TUNAI,
    TRANSFER,
    QRIS
}

enum SatuanBerat {
    GRAM,
    KILOGRAM
}

class StokKurangException extends Exception {
    public StokKurangException(String pesan) {
        super(pesan);
    }
}

class EntitasTidakDitemukanException extends Exception {
    public EntitasTidakDitemukanException(String pesan) {
        super(pesan);
    }
}

// ==========================================
// 2. INTERFACE & ABSTRACTION
// ==========================================

interface TampilkanData {
    void tampilkanDetail();
}

interface Repository<T, ID> {
    void simpan(T entitas);
    Optional<T> cariBerdasarkanId(ID id);
    List<T> ambilSemua();
    boolean hapus(ID id);
}

// ==========================================
// 3. MODEL DATA (DOMAIN ENTITIES)
// ==========================================

abstract class Produk implements TampilkanData, Serializable {
    private final String id;
    private String nama;
    private double harga;
    private int stok;
    private final KategoriProduk kategori;

    public Produk(String id, String nama, double harga, int stok, KategoriProduk kategori) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.kategori = kategori;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
    public KategoriProduk getKategori() { return kategori; }

    public void setHarga(double harga) { this.harga = harga; }

    public synchronized void kurangiStok(int jumlah) throws StokKurangException {
        if (jumlah > this.stok) {
            throw new StokKurangException("Stok produk '" + nama + "' tidak mencukupi! Tersisa: " + this.stok);
        }
        this.stok -= jumlah;
    }

    public synchronized void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        }
    }

    public abstract double hitungDiskonPromosi();

    @Override
    public void tampilkanDetail() {
        System.out.printf("[%s] %-20s | Kat: %-12s | Rp%-10.2f | Stok: %-4d%n",
                id, nama, kategori, harga, stok);
    }
}

class ProdukFisik extends Produk {
    private final double beratGram;

    public ProdukFisik(String id, String nama, double harga, int stok, KategoriProduk kategori, double beratGram) {
        super(id, nama, harga, stok, kategori);
        this.beratGram = beratGram;
    }

    public ProdukFisik(String id, String nama, double harga, int stok, KategoriProduk kategori, double berat, SatuanBerat satuanBerat) {
        super(id, nama, harga, stok, kategori);
        this.beratGram = (satuanBerat == SatuanBerat.KILOGRAM) ? berat * 1000 : berat;
    }

    public double getBeratGram() { return beratGram; }
    public double getBeratKilogram() { return beratGram / 1000.0; }
    public String formatBerat() {
        if (beratGram >= 1000) {
            return String.format("%.2f kg", getBeratKilogram());
        }
        return String.format("%.0f g", beratGram);
    }

    @Override
    public double hitungDiskonPromosi() {
        // Diskon 5% jika stok lebih dari 50
        return getStok() > 50 ? getHarga() * 0.05 : 0.0;
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[%s] %-20s | Kat: %-12s | Rp%-10.2f | Stok: %-4d | Berat: %s%n",
                getId(), getNama(), getKategori(), getHarga(), getStok(), formatBerat());
    }
}

class ItemTransaksi {
    private final Produk produk;
    private final int kuantitas;
    private final double subtotal;

    public ItemTransaksi(Produk produk, int kuantitas) {
        this.produk = produk;
        this.kuantitas = kuantitas;
        this.subtotal = (produk.getHarga() - produk.hitungDiskonPromosi()) * kuantitas;
    }

    public Produk getProduk() { return produk; }
    public int getKuantitas() { return kuantitas; }
    public double getSubtotal() { return subtotal; }
}

class Transaksi implements TampilkanData {
    private final String kodeTransaksi;
    private final LocalDateTime tanggal;
    private final List<ItemTransaksi> daftarItem;
    private final double totalBiaya;
    private final MetodePembayaran metodePembayaran;

    public Transaksi(String kodeTransaksi, List<ItemTransaksi> daftarItem, MetodePembayaran metodePembayaran) {
        this.kodeTransaksi = kodeTransaksi;
        this.tanggal = LocalDateTime.now();
        this.daftarItem = new ArrayList<>(daftarItem);
        this.metodePembayaran = metodePembayaran;
        this.totalBiaya = hitungTotal();
    }

    public String getKodeTransaksi() { return kodeTransaksi; }
    public double getTotalBiaya() { return totalBiaya; }

    private double hitungTotal() {
        return daftarItem.stream().mapToDouble(ItemTransaksi::getSubtotal).sum();
    }

    @Override
    public void tampilkanDetail() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("=====================================================");
        System.out.println("ID TRANSAKSI : " + kodeTransaksi);
        System.out.println("TANGGAL      : " + tanggal.format(fmt));
        System.out.println("METODE BAYAR : " + metodePembayaran);
        System.out.println("-----------------------------------------------------");
        for (ItemTransaksi item : daftarItem) {
            System.out.printf("- %-18s x%-3d @Rp%-9.2f = Rp%.2f%n",
                    item.getProduk().getNama(),
                    item.getKuantitas(),
                    item.getProduk().getHarga(),
                    item.getSubtotal());
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("TOTAL PEMBAYARAN : Rp%.2f%n", totalBiaya);
        System.out.println("=====================================================");
    }
}

// ==========================================
// 4. REPOSITORY LAYER
// ==========================================

class ProdukRepositoryInMemory implements Repository<Produk, String> {
    private final Map<String, Produk> database = new LinkedHashMap<>();

    @Override
    public void simpan(Produk entitas) {
        database.put(entitas.getId(), entitas);
    }

    @Override
    public Optional<Produk> cariBerdasarkanId(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Produk> ambilSemua() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean hapus(String id) {
        return database.remove(id) != null;
    }
}

class TransaksiRepositoryInMemory implements Repository<Transaksi, String> {
    private final Map<String, Transaksi> database = new LinkedHashMap<>();

    @Override
    public void simpan(Transaksi entitas) {
        database.put(entitas.getKodeTransaksi(), entitas);
    }

    @Override
    public Optional<Transaksi> cariBerdasarkanId(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Transaksi> ambilSemua() {
        return new ArrayList<>(database.values());
    }

    @Override
    public boolean hapus(String id) {
        return database.remove(id) != null;
    }
}

// ==========================================
// 5. SERVICE / BUSINESS LOGIC LAYER
// ==========================================

class LayananToko {
    private final Repository<Produk, String> produkRepo;
    private final Repository<Transaksi, String> transaksiRepo;

    public LayananToko(Repository<Produk, String> produkRepo, Repository<Transaksi, String> transaksiRepo) {
        this.produkRepo = produkRepo;
        this.transaksiRepo = transaksiRepo;
    }

    public void tambahProduk(Produk p) {
        produkRepo.simpan(p);
    }

    public List<Produk> dapatkanSemuaProduk() {
        return produkRepo.ambilSemua();
    }

    public Produk cariProduk(String id) throws EntitasTidakDitemukanException {
        return produkRepo.cariBerdasarkanId(id)
                .orElseThrow(() -> new EntitasTidakDitemukanException("Produk dengan ID '" + id + "' tidak ditemukan!"));
    }

    public Transaksi buatTransaksi(Map<String, Integer> pesanan, MetodePembayaran metode)
            throws EntitasTidakDitemukanException, StokKurangException {
        
        List<ItemTransaksi> itemCheckout = new ArrayList<>();

        // Validasi ketersediaan stok
        for (Map.Entry<String, Integer> entri : pesanan.entrySet()) {
            Produk p = cariProduk(entri.getKey());
            int kuantitas = entri.getValue();

            if (p.getStok() < kuantitas) {
                throw new StokKurangException("Gagal transaksi: Stok untuk '" + p.getNama() + "' hanya sisa " + p.getStok());
            }
            itemCheckout.add(new ItemTransaksi(p, kuantitas));
        }

        // Potong stok produk
        for (ItemTransaksi item : itemCheckout) {
            item.getProduk().kurangiStok(item.getKuantitas());
        }

        // Buat objek transaksi baru
        String kode = "TRX-" + System.currentTimeMillis();
        Transaksi trx = new Transaksi(kode, itemCheckout, metode);
        transaksiRepo.simpan(trx);

        return trx;
    }

    public List<Transaksi> dapatkanRiwayatTransaksi() {
        return transaksiRepo.ambilSemua();
    }
}

// ==========================================
// 6. MAIN APPLICATION / CLI INTERFACE
// ==========================================

public class AplikasiToko {
    private static final Scanner scanner = new Scanner(System.in);
    private static LayananToko layananToko;

    public static void main(String[] args) {
        inisialisasiSistem();

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenuUtama();
            System.out.print("Pilih opsi [1-6]: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    menuLihatProduk();
                    break;
                case "2":
                    menuTambahProduk();
                    break;
                case "3":
                    menuTransaksiKasir();
                    break;
                case "4":
                    menuRiwayatTransaksi();
                    break;
                case "5":
                    menuRestokProduk();
                    break;
                case "6":
                    System.out.println("\nTerima kasih telah menggunakan sistem aplikasi toko!");
                    berjalan = false;
                    break;
                default:
                    System.out.println("\n[!] Opsi tidak valid. Silakan coba lagi.");
            }
            System.out.println();
        }
    }

    private static void inisialisasiSistem() {
        Repository<Produk, String> produkRepo = new ProdukRepositoryInMemory();
        Repository<Transaksi, String> transaksiRepo = new TransaksiRepositoryInMemory();
        layananToko = new LayananToko(produkRepo, transaksiRepo);

        // Data awal (Seed Data)
        layananToko.tambahProduk(new ProdukFisik("P001", "Kopi Robusta 250g", 35000, 20, KategoriProduk.MINUMAN, 250));
        layananToko.tambahProduk(new ProdukFisik("P002", "Biskuit Gandum", 12500, 60, KategoriProduk.MAKANAN, 150));
        layananToko.tambahProduk(new ProdukFisik("P003", "Mouse Wireless", 85000, 15, KategoriProduk.ELEKTRONIK, 100));
        layananToko.tambahProduk(new ProdukFisik("P004", "Deterjen Cair 1L", 22000, 30, KategoriProduk.KEBUTUHAN_RUMAH, 1000));
    }

    private static void tampilkanMenuUtama() {
        System.out.println("==========================================");
        System.out.println("   SISTEM MANAJEMEN INVENTARIS & KASIR    ");
        System.out.println("==========================================");
        System.out.println("1. Daftar Katalog Produk");
        System.out.println("2. Tambah Produk Baru");
        System.out.println("3. Buat Transaksi Baru (Kasir)");
        System.out.println("4. Lihat Riwayat Penjualan");
        System.out.println("5. Restok Barang");
        System.out.println("6. Keluar");
        System.out.println("------------------------------------------");
    }

    private static void menuLihatProduk() {
        System.out.println("\n--- KATALOG PRODUK ---");
        List<Produk> daftar = layananToko.dapatkanSemuaProduk();
        if (daftar.isEmpty()) {
            System.out.println("Katalog masih kosong.");
            return;
        }
        for (Produk p : daftar) {
            p.tampilkanDetail();
        }
    }

    private static void menuTambahProduk() {
        System.out.println("\n--- TAMBAH PRODUK BARU ---");
        try {
            System.out.print("ID Produk (cth: P005): ");
            String id = scanner.nextLine().trim();

            System.out.print("Nama Produk: ");
            String nama = scanner.nextLine().trim();

            System.out.print("Harga (Rp): ");
            double harga = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Stok Awal: ");
            int stok = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Pilih Kategori: 1. MAKANAN, 2. MINUMAN, 3. ELEKTRONIK, 4. KEBUTUHAN_RUMAH");
            System.out.print("Pilihan [1-4]: ");
            int katPilih = Integer.parseInt(scanner.nextLine().trim());
            KategoriProduk kategori = KategoriProduk.values()[katPilih - 1];

            System.out.print("Berat Produk: ");
            double berat = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Pilih Satuan Berat: 1. Gram, 2. Kilogram");
            System.out.print("Pilihan [1-2]: ");
            int beratPilih = Integer.parseInt(scanner.nextLine().trim());
            SatuanBerat satuanBerat = SatuanBerat.values()[beratPilih - 1];

            Produk baru = new ProdukFisik(id, nama, harga, stok, kategori, berat, satuanBerat);
            layananToko.tambahProduk(baru);
            System.out.println("[v] Produk berhasil ditambahkan!");

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("[x] Kesalahan format input data!");
        }
    }

    private static void menuTransaksiKasir() {
        System.out.println("\n--- TRANSAKSI PENJUALAN ---");
        Map<String, Integer> keranjang = new HashMap<>();

        while (true) {
            System.out.print("Masukkan ID Produk yang dibeli (ketik 'selesai' untuk checkout): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }

            try {
                Produk p = layananToko.cariProduk(input);
                System.out.print("Jumlah untuk '" + p.getNama() + "': ");
                int kuantitas = Integer.parseInt(scanner.nextLine().trim());

                if (kuantitas <= 0) {
                    System.out.println("[x] Kuantitas harus lebih dari 0.");
                    continue;
                }

                keranjang.put(input, keranjang.getOrDefault(input, 0) + kuantitas);
                System.out.println("[v] Ditambahkan ke keranjang.");

            } catch (EntitasTidakDitemukanException e) {
                System.out.println("[x] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("[x] Format angka salah.");
            }
        }

        if (keranjang.isEmpty()) {
            System.out.println("Transaksi dibatalkan (keranjang kosong).");
            return;
        }

        System.out.println("\nPilih Metode Bayar: 1. TUNAI, 2. TRANSFER, 3. QRIS");
        System.out.print("Pilihan [1-3]: ");
        try {
            int bayarPilih = Integer.parseInt(scanner.nextLine().trim());
            MetodePembayaran metode = MetodePembayaran.values()[bayarPilih - 1];

            Transaksi hasil = layananToko.buatTransaksi(keranjang, metode);
            System.out.println("\n[v] TRANSAKSI BERHASIL DICATAT!");
            hasil.tampilkanDetail();

        } catch (StokKurangException | EntitasTidakDitemukanException e) {
            System.out.println("\n[!] Transaksi Gagal: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[x] Input tidak valid.");
        }
    }

    private static void menuRiwayatTransaksi() {
        System.out.println("\n--- RIWAYAT SELURUH TRANSAKSI ---");
        List<Transaksi> riwayat = layananToko.dapatkanRiwayatTransaksi();
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi tercatat.");
            return;
        }
        for (Transaksi t : riwayat) {
            t.tampilkanDetail();
            System.out.println();
        }
    }

    private static void menuRestokProduk() {
        System.out.println("\n--- RESTOK PRODUK ---");
        try {
            System.out.print("Masukkan ID Produk: ");
            String id = scanner.nextLine().trim();

            Produk p = layananToko.cariProduk(id);
            System.out.printf("Produk: %s (Stok Sekarang: %d)%n", p.getNama(), p.getStok());

            System.out.print("Jumlah Stok Tambahan: ");
            int tambahan = Integer.parseInt(scanner.nextLine().trim());

            if (tambahan <= 0) {
                System.out.println("[x] Jumlah restok harus lebih besar dari 0.");
                return;
            }

            p.tambahStok(tambahan);
            System.out.println("[v] Stok berhasil diperbarui. Total stok saat ini: " + p.getStok());

        } catch (EntitasTidakDitemukanException e) {
            System.out.println("[x] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[x] Input harus berupa angka.");
        }
    }
}