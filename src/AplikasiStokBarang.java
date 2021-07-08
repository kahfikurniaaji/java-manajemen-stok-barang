import java.util.*;

/***
 * Membuat class Barang
 */
class Barang {
    private final String nama;
    private final int stok;
    private final int harga;
    private final int totalHarga;

    /***
     * Membuat Constructor Class Barang
     */
    public Barang(String nama, int stok, int harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.totalHarga = stok * harga;
    }

    /***
     * Method getter properties nama, stok, harga, dan total harga
     * Mengembalikan nilai dari masing-masing properties
     */
    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public int getHarga() {
        return harga;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    /***
     * Method comparator untuk membandingkan masing-masing properties
     */
    public static Comparator<Barang> namaBarangComparator = (o1, o2) -> {
        String namaBarang1 = o1.getNama().toUpperCase();
        String namaBarang2 = o2.getNama().toUpperCase();
        return namaBarang1.compareTo(namaBarang2);
    };
    public static Comparator<Barang> stokBarangComparator = Comparator.comparingInt(Barang::getStok);
    public static Comparator<Barang> hargaBarangComparator = Comparator.comparingInt(Barang::getHarga);
    public static Comparator<Barang> totalHargaBarangComparator = Comparator.comparingInt(Barang::getTotalHarga);
}

/***
 * Membuat Class AplikasiStokBarang yang berisikan Main Method aplikasi
 */
public class AplikasiStokBarang {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Barang> stokBarang = new ArrayList<>();

    /***
     * Main Method Aplikasi
     */
    public static void main(String[] args) {
        login();
    }

    /***
     * Method untuk menambah data Barang
     */
    private static void tambahBarang() {
        System.out.println("==================================================");
        System.out.println("================MENAMBAHKAN BARANG================");
        System.out.println("==================================================");
        String nama = input("Nama");
        String stok = input("Stok");
        String harga = input("Harga");
        stokBarang.add(new Barang(nama, Integer.parseInt(stok), Integer.parseInt(harga)));
        System.out.println("BERHASIL MENAMBAHKAN DATA BARANG");
        System.out.println("==================================================");
    }

    /***
     * Method untuk menampilkan seluruh stok Barang
     */
    private static void tampilkanSeluruhStokBarang() {
        if (stokBarang.isEmpty()) {
            System.out.println("STOK KOSONG");
        } else {
            List<Barang> barangList;
            System.out.println("================================");
            System.out.println("SORTING BERDASARKAN :");
            System.out.println("1. Urutan Input");
            System.out.println("2. Nama");
            System.out.println("3. Stok");
            System.out.println("4. Harga");
            System.out.println("5. Total Harga");
            String sorting = input("Pilih");
            System.out.println("========================================================================");
            System.out.println("NO.\tNAMA PRODUK\t\tSTOK\tHARGA SATUAN\tTOTAL HARGA");
            System.out.println("========================================================================");
            barangList = sorting(sorting);
            for (int i = 0; i < barangList.size(); i++) {
                Barang barang = barangList.get(i);
                System.out.println((i + 1) + "\t" + barang.getNama() + "\t\t" + barang.getStok() + "\t" + barang.getHarga() + "\t\t\t" + barang.getTotalHarga());
            }
            System.out.println("========================================================================");
        }
    }

    /***
     * Function Method untuk mengurutkan data barang (ASCENDING)
     * Mengembalikan List Barang yang telah disorting
     */
    private static List<Barang> sorting(String jenis) {
        final List<Barang> barangList = new ArrayList<>(stokBarang);
        switch (jenis) {
            case "2":
                barangList.sort(Barang.namaBarangComparator);
                break;
            case "3":
                barangList.sort(Barang.stokBarangComparator);
                break;
            case "4":
                barangList.sort(Barang.hargaBarangComparator);
                break;
            case "5":
                barangList.sort(Barang.totalHargaBarangComparator);
                break;
            default:
                break;
        }
        return barangList;
    }

    /***
     * Menampilkan data sorting berdasarkan input
     */
    private static void sortingInput() {
        System.out.println("========================================================================");
        System.out.println("NO.\tNAMA PRODUK\t\tSTOK\tHARGA SATUAN\tTOTAL HARGA");
        System.out.println("========================================================================");
        for (int i = 0; i < stokBarang.size(); i++) {
            Barang barang = stokBarang.get(i);
            System.out.println((i + 1) + "\t" + barang.getNama() + "\t\t" + barang.getStok() + "\t" + barang.getHarga() + "\t\t\t" + barang.getTotalHarga());
        }
        System.out.println("========================================================================");
    }

    /**
     * Method untuk mengubah atau mengupdate data barang
     */
    private static void updateBarang() {
        if (stokBarang.isEmpty()) {
            System.out.println("STOK KOSONG");
        } else {
            sortingInput();
            System.out.println("================================");
            System.out.println("=====MENGUPDATE DATA BARANG=====");
            System.out.println("================================");
            String nomor = input("Nomor");
            String nama = input("Nama");
            String stok = input("Stok");
            String harga = input("Harga");
            if (Integer.parseInt(nomor) <= stokBarang.size() && Integer.parseInt(nomor) > 0) {
                stokBarang.set(Integer.parseInt(nomor) - 1, new Barang(nama, Integer.parseInt(stok), Integer.parseInt(harga)));
                System.out.println("BERHASIL MENGUPDATE BARANG KE-" + nomor);
                sortingInput();
            } else {
                System.out.println("GAGAL MENGUPDATE BARANG KE-" + nomor);
                System.out.println("================================");
            }
        }
    }

    /***
     * Method untuk menghapus data barang
     */
    private static void hapusBarang() {
        if (stokBarang.isEmpty()) {
            System.out.println("STOK KOSONG");
        } else {
            sortingInput();
            System.out.println("==MENGHAPUS DATA BARANG==");
            System.out.println("=========================");
            String nomor = input("Nomor");
            if (Integer.parseInt(nomor) <= stokBarang.size() && Integer.parseInt(nomor) > 0) {
                stokBarang.remove(Integer.parseInt(nomor) - 1);
                System.out.println("BERHASIL MENGHAPUS BARANG KE-" + nomor);
                sortingInput();
            } else {
                System.out.println("GAGAL MENGHAPUS BARANG KE-" + nomor);
                System.out.println("=========================");
            }
        }
    }

    /***
     * Method untuk mencari data barang berdasarkan nama
     */
    private static void cariBarang() {
        System.out.println("=========================");
        System.out.println("===MENCARI DATA BARANG===");
        System.out.println("=========================");
        String namaBarang = input("Masukan nama barang");
        boolean ditemukan = false;
        for (Barang barang : stokBarang) {
            if (barang.getNama().equals(namaBarang)) {
                ditemukan = true;
                break;
            }
        }
        if (ditemukan) {
            System.out.println("========================================================================");
            System.out.println("NO.\tNAMA PRODUK\t\tSTOK\tHARGA SATUAN\tHARGA TOTAL");
            System.out.println("========================================================================");
            for (int i = 0; i < stokBarang.size(); i++) {
                Barang barang = stokBarang.get(i);
                if (barang.getNama().equals(namaBarang)) {
                    System.out.println((i + 1) + "\t" + barang.getNama() + "\t\t" + barang.getStok() + "\t" + barang.getHarga() + "\t\t\t" + barang.getTotalHarga());
                    System.out.println("========================================================================");
                }
            }
        } else {
            System.out.println("DATA BARANG TIDAK DITEMUKAN!");
            System.out.println("=========================");
        }
    }

    /***
     * Method menampilkan menu login
     */
    private static void login() {
        while (true) {
            System.out.println("==========LOGIN==========");
            String username = input("Username (x untuk keluar)");
            if (username.equals("x")) break;
            String password = input("Password (x untuk keluar)");
            if (password.equals("x")) break;
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("LOGIN SUKSES!");
                System.out.println("=========================");
                menuUtama();
            } else {
                System.out.println("USERNAME ATAU PASSWORD SALAH!!");
            }
        }
    }

    /***
     * Method menampilkan menu utama setelah berhasil login
     */
    private static void menuUtama() {
        while (true) {
            System.out.println("MENU UTAMA");
            System.out.println("1. Tambah data barang");
            System.out.println("2. Menampilkan data seluruh stok barang");
            System.out.println("3. Mengubah data barang");
            System.out.println("4. Menghapus data barang");
            System.out.println("5. Mencari data barang");
            System.out.println("0. Logout");
            String nomorMenu = input("Pilih");
            switch (nomorMenu) {
                case "1":
                    tambahBarang();
                    break;
                case "2":
                    tampilkanSeluruhStokBarang();
                    break;
                case "3":
                    updateBarang();
                    break;
                case "4":
                    hapusBarang();
                    break;
                case "5":
                    cariBarang();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Nomor menu yang dimasukkan salah!");
                    break;
            }
        }
    }

    /***
     * Method helper untuk menginputkan data
     */
    private static String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }
}