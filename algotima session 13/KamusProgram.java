import java.util.*;

public class KamusProgram {
    static class KataEntry implements Comparable<KataEntry> {
        private String kataBahasaInggris;
        private String kataBahasaIndonesia;

        public KataEntry(String eng, String indo) {
            this.kataBahasaInggris = eng;
            this.kataBahasaIndonesia = indo;
        }

        @Override
        public int compareTo(KataEntry other) {
            return this.kataBahasaInggris.compareToIgnoreCase(other.kataBahasaInggris);
        }
    }

    static class Kamus {
        private List<KataEntry> daftarKata;
        private List<KataEntry> daftarKataIndo; // Untuk pencarian bahasa Indonesia

        public Kamus() {
            this.daftarKata = new ArrayList<>();
            this.daftarKataIndo = new ArrayList<>();
            inisialisasiKamus();
        }

        private void inisialisasiKamus() {
            // Menambahkan kata-kata dasar
            tambahKata("apple", "apel");
            tambahKata("book", "buku");
            tambahKata("cat", "kucing");
            tambahKata("dog", "anjing");
            tambahKata("elephant", "gajah");
            tambahKata("fish", "ikan");
            tambahKata("garden", "kebun");
            tambahKata("house", "rumah");
            tambahKata("ice", "es");
            tambahKata("jacket", "jaket");
            // Urutkan kedua daftar
            Collections.sort(daftarKata);
            Collections.sort(daftarKataIndo, (a, b) -> 
                a.kataBahasaIndonesia.compareToIgnoreCase(b.kataBahasaIndonesia));
        }

        public void tambahKata(String eng, String indo) {
            KataEntry entry = new KataEntry(eng, indo);
            daftarKata.add(entry);
            daftarKataIndo.add(entry);
        }

        public String cariKataInggris(String kata) {
            int left = 0;
            int right = daftarKata.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                KataEntry current = daftarKata.get(mid);
                int comparison = kata.compareToIgnoreCase(current.kataBahasaInggris);

                if (comparison == 0) {
                    return current.kataBahasaIndonesia;
                } else if (comparison < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return "Kata tidak ditemukan";
        }

        public String cariKataIndonesia(String kata) {
            int left = 0;
            int right = daftarKataIndo.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                KataEntry current = daftarKataIndo.get(mid);
                int comparison = kata.compareToIgnoreCase(current.kataBahasaIndonesia);

                if (comparison == 0) {
                    return current.kataBahasaInggris;
                } else if (comparison < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return "Kata tidak ditemukan";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kamus kamus = new Kamus();

        while (true) {
            System.out.println("\n=== KAMUS INGGRIS-INDONESIA ===");
            System.out.println("1. Cari kata Inggris ke Indonesia");
            System.out.println("2. Cari kata Indonesia ke Inggris");
            System.out.println("3. Tambah kata baru");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan kata dalam bahasa Inggris: ");
                    String kataInggris = scanner.nextLine().trim();
                    String hasilInggris = kamus.cariKataInggris(kataInggris);
                    System.out.println("Arti: " + hasilInggris);
                    break;

                case 2:
                    System.out.print("Masukkan kata dalam bahasa Indonesia: ");
                    String kataIndo = scanner.nextLine().trim();
                    String hasilIndo = kamus.cariKataIndonesia(kataIndo);
                    System.out.println("Arti: " + hasilIndo);
                    break;

                case 3:
                    System.out.print("Masukkan kata dalam bahasa Inggris: ");
                    String newEng = scanner.nextLine().trim();
                    System.out.print("Masukkan kata dalam bahasa Indonesia: ");
                    String newIndo = scanner.nextLine().trim();
                    kamus.tambahKata(newEng, newIndo);
                    System.out.println("Kata berhasil ditambahkan!");
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan Kamus!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}