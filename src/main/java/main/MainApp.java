/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import model.ProyekTypeset;
import controllers.ProyekController;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 *
 * @author user
 */
public class MainApp {
    private static final ProyekController controller = new ProyekController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== SISTEM MANAJEMEN TYPESETTING KOMIK ===");
            System.out.println("1. Tambah Proyek Typeset");
            System.out.println("2. Tampilkan Semua Proyek");
            System.out.println("3. Update Proyek");
            System.out.println("4. Hapus Proyek");
            System.out.println("5. Keluar Program");
            System.out.print("Pilih menu [1-5]: ");

            String pilihan = scanner.nextLine().trim();
            switch (pilihan) {
                case "1" -> menuTambah();
                case "2" -> menuTampil();
                case "3" -> menuUpdate();
                case "4" -> menuHapus();
                case "5" -> {
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    berjalan = false;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void menuTambah() {
        System.out.println("\n--- TAMBAH PROYEK TYPESET ---");
        System.out.print("ID Proyek (misal: PRJ01): ");
        String idProyek = scanner.nextLine().trim();

        System.out.print("ID Komik (misal: K01): ");
        String idKomik = scanner.nextLine().trim();
        System.out.print("Judul Komik: ");
        String judul = scanner.nextLine().trim();
        System.out.print("Genre Komik: ");
        String genre = scanner.nextLine().trim();

        System.out.print("ID Typesetter (misal: TS01): ");
        String idTs = scanner.nextLine().trim();
        System.out.print("Nama Typesetter: ");
        String namaTs = scanner.nextLine().trim();
        System.out.print("Jenis Typesetter (1: TETAP, 2: MAGANG): ");
        String jenis = scanner.nextLine().trim();

        Typesetter typesetter;
        if (jenis.equals("1")) {
            System.out.print("Gaji Pokok: ");
            double gaji = Double.parseDouble(scanner.nextLine().trim());
            typesetter = new TypesetterTetap(idTs, namaTs, gaji);
        } else {
            System.out.print("Durasi Magang (Bulan): ");
            int durasi = Integer.parseInt(scanner.nextLine().trim());
            typesetter = new TypesetterMagang(idTs, namaTs, durasi);
        }

        System.out.print("Chapter Pengerjaan: ");
        int chapter = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Status Proyek: ");
        String status = scanner.nextLine().trim();

        Komik komik = new Komik(idKomik, judul, genre);
        ProyekTypeset proyek = new ProyekTypeset(idProyek, komik, typesetter, chapter, status);

        if (controller.tambahProyek(proyek)) {
            System.out.println("✅ Data proyek berhasil disimpan ke database!");
        } else {
            System.out.println("❌ Gagal menyimpan data proyek.");
        }
    }

    private static void menuTampil() {
        System.out.println("\n--- DAFTAR PROYEK TYPESETTING ---");
        List<ProyekTypeset> list = controller.getAllProyek();
        if (list.isEmpty()) {
            System.out.println("Belum ada data proyek di database.");
            return;
        }

        for (ProyekTypeset p : list) {
            System.out.printf("[%s] Komik: %s (%s) | Typesetter: %s (%s) | Chapter: %d | Status: %s | Bonus: Rp%.2f\n",
                    p.getIdProyek(),
                    p.getKomik().getJudul(),
                    p.getKomik().getGenre(),
                    p.getTypesetter().getNama(),
                    p.getTypesetter().getPeran(),
                    p.getChapter(),
                    p.getStatus(),
                    p.getTypesetter().hitungBonus(p.getChapter()));
        }
    }

    private static void menuUpdate() {
        System.out.println("\n--- UPDATE PROYEK TYPESET ---");
        System.out.print("Masukkan ID Proyek yang ingin diubah: ");
        String idProyek = scanner.nextLine().trim();

        System.out.print("ID Komik Baru: ");
        String idKomik = scanner.nextLine().trim();
        System.out.print("ID Typesetter Baru: ");
        String idTs = scanner.nextLine().trim();
        System.out.print("Chapter Baru: ");
        int chapter = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Status Baru: ");
        String status = scanner.nextLine().trim();

        Komik komik = new Komik(idKomik, "", "");
        Typesetter ts = new TypesetterTetap(idTs, "", 0);
        ProyekTypeset proyek = new ProyekTypeset(idProyek, komik, ts, chapter, status);

        if (controller.updateProyek(proyek)) {
            System.out.println("✅ Data proyek berhasil diperbarui!");
        } else {
            System.out.println("❌ Gagal memperbarui data proyek.");
        }
    }

    private static void menuHapus() {
        System.out.println("\n--- HAPUS PROYEK TYPESET ---");
        System.out.print("Masukkan ID Proyek yang ingin dihapus: ");
        String idProyek = scanner.nextLine().trim();

        if (controller.hapusProyek(idProyek)) {
            System.out.println("✅ Data proyek berhasil dihapus!");
        } else {
            System.out.println("❌ Gagal menghapus data proyek.");
        }
    }
}
