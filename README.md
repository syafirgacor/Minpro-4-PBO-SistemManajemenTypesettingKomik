# 📚 Minpro-4-PBO-SistemManajemenTypesettingKomik
## Nama: Syafir Ahzami
## NIM: 2509116074
## Kelas: B

Sistem Manajemen Typesetting Komik berbasis **Konsol / CLI (Command Line Interface)** yang dibangun menggunakan bahasa pemrograman **Java**, diintegrasikan dengan basis data **MySQL** melalui teknologi **JDBC (`PreparedStatement`)**, serta menerapkan pola arsitektur **DAO (Data Access Object)** dan **MVC (Model-View-Controller)**.

---

## 📋 Daftar Isi
1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Struktur Package & Arsitektur](#2-penjelasan-struktur-package--arsitektur)
3. [Penjelasan Alur Program (Execution Flow)](#3-penjelasan-alur-program-execution-flow)
4. [Penerapan Konsep Pemrograman Berorientasi Objek (OOP)](#4-penerapan-konsep-pemrograman-berorientasi-objek-oop)
   - [A. Encapsulation (Pengapsulan)](#a-encapsulation-pengapsulan)
   - [B. Inheritance (Pewarisan)](#b-inheritance-pewarisan)
   - [C. Abstraction (Pengabstrakan)](#c-abstraction-pengabstrakan)
   - [D. Polymorphism (Polimorfisme)](#d-polymorphism-polimorfisme)
5. [Penjelasan Penerapan JDBC & DAO Pattern](#5-penjelasan-penerapan-jdbc--dao-pattern)

---

## 1. Deskripsi Singkat Program
**Sistem Manajemen Typesetting Komik** adalah aplikasi perangkat lunak berbasis konsol yang dirancang untuk mengelola alur kerja operasional penerbitan atau studio penerjemahan komik. Dalam studio penerbitan komik, *typesetter* bertanggung jawab untuk menata tata letak teks, balon kata (*dialogue bubbles*), dan efek suara (*sound effects/SFX*) pada lembar komik.

Sistem ini memungkinkan admin/manajer proyek untuk melakukan pengelolaan data secara permanen (*persistent*) ke database MySQL `db_typesetting_komik` melalui operasi **CRUD (Create, Read, Update, Delete)**:
- **Create (Tambah Data)**: Menambahkan proyek typesetting baru yang terhubung dengan data komik dan typesetter.
- **Read (Tampilkan Data)**: Membaca dan menampilkan seluruh daftar proyek komik beserta perhitungan estimasi bonus typesetter secara *real-time* dari database.
- **Update (Ubah Data)**: Memperbarui informasi status pengerjaan atau chapter proyek komik.
- **Delete (Hapus Data)**: Menghapus record proyek typesetting dari sistem.

---

## 2. Penjelasan Struktur Package & Arsitektur
Aplikasi ini menerapkan arsitektur **MVC (Model-View-Controller)** yang dikombinasikan dengan **DAO (Data Access Object) Pattern** untuk memisahkan logika bisnis, pengelolaan data, dan antarmuka pengguna secara bersih (*Separation of Concerns*):

```text
src/
 ├── config/       -> Mengelola konfigurasi dan koneksi ke database MySQL (DatabaseConfig.java)
 ├── controllers/  -> Menghubungkan antarmuka pengguna dengan layer DAO dan memvalidasi logika (ProyekController.java)
 ├── dao/          -> Antarmuka (Interface) dan implementasi kueri SQL JDBC (IProyekDAO.java, ProyekDAOImpl.java)
 ├── interfaces/   -> Kontrak abstraksi layanan sistem (IProyekService.java)
 ├── model/        -> Entitas data dan struktur objek OOP (Komik, Typesetter, TypesetterTetap, TypesetterMagang, ProyekTypeset)
 └── main/         -> Titik masuk utama eksekusi program dan antarmuka CLI (Minpro1PBOSistemManajemenTypesettingKomik.java)
```
Detil Fungsi Per Package:

- config: Menggunakan pola Singleton untuk memastikan hanya ada satu koneksi aktif ke database MySQL.   
- model: Berisi domain data utama yang merepresentasikan entitas nyata di lapangan.
- dao: Isolasi murni seluruh perintah kueri SQL (INSERT, SELECT, UPDATE, DELETE) menggunakan PreparedStatement agar kode GUI/CLI tidak tercampur kueri database.
- controllers: Memproses permintaan dari user, melakukan sanitasi input (trim()), dan memanggil method pada layer DAO.
- main: Berisi loop menu baris perintah (CLI) untuk menangkap input keyboard pengguna via Scanner.   

---

## 3. Penjelasan Alur Program (Execution Flow)

Saat aplikasi dijalankan dari kelas utama MainApp.java, sistem memanggil DatabaseConfig.getConnection().

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/42258242-ceef-431b-8db2-141f275fe2f1" />

Program masuk ke dalam perulangan while(berjalan) dan menampilkan 5 pilihan menu utama:   
- Tambah Proyek Typeset
- Tampilkan Semua Proyek
- Update Proyek
- Hapus Proyek
- Keluar Program

**Pilihan 1 (Create)**: Pengguna mengisi ID Proyek, Komik, Typesetter, Chapter, dan Status. Controller membentuk objek ProyekTypeset dan mengirimkannya ke DAO. DAO menggunakan transaksi atomic (conn.setAutoCommit(false)) untuk memasukkan data ke tabel-tabel berelasi sekaligus.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/90ed638d-be3b-47b8-b359-f1e5710b4c1b" />

**Pilihan 2 (Read)**: Controller memanggil ProyekDAOImpl.getAllProyek(). DAO mengeksekusi kueri SELECT menggunakan operasi JOIN antara 3 tabel (proyek_typeset, komik, dan typesetter). Data dibungkus ke dalam List<ProyekTypeset> lalu dicetak rapi ke konsol.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/3b40ecce-f6ee-4d81-99f9-7dd75b883dde" />

**Pilihan 3 (Update)**: Pengguna memasukkan ID Proyek target beserta data baru. DAO mengeksekusi perintah SQL UPDATE pada database.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/1609c92a-1877-40de-9f5d-0b9bb968b120" />

**Pilihan 4 (Delete)**: Pengguna memasukkan ID Proyek yang ingin dihapus. DAO mengeksekusi kueri SQL DELETE.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/e3b40260-c576-402b-b9cd-2d9f2e2c3440" />

---

## 4. Penerapan Konsep Pemrograman Berorientasi Objek (OOP)

### A. Encapsulation (Pengapsulan)
Encapsulation adalah mekanisme menyembunyikan variabel/atribut internal suatu kelas dan membatasi aksesnya dari luar menggunakan modifier private atau protected.

Penerapan: Atribut pada kelas-kelas model seperti Komik (idKomik, judul, genre) dan ProyekTypeset (idProyek, chapter, status) diset private.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/27898fc8-788a-4620-b782-bc863c53d99f" />

Akses: Pengubahan dan pembacaan atribut wajib melalui method Getter dan Setter publik (misal: getIdProyek(), setChapter()) untuk menjamin validitas data.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a3bd3ae8-fef9-4d0c-a010-e1816f326c33" />

### B. Inheritance (Pewarisan)
Inheritance adalah konsep di mana suatu kelas anak (subclass) menurunkan properti dan method dari kelas induk (superclass).

Penerapan: Kelas Typesetter berperan sebagai kelas induk.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/3743a46a-a900-4e7a-b637-cdbbf198d681" />

Subclass:
- TypesetterTetap mewarisi Typesetter dengan menambahkan atribut khusus gajiPokok.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/accb1126-fe19-4f6c-986f-9f9d64db0d81" />

- TypesetterMagang mewarisi Typesetter dengan menambahkan atribut khusus durasiMagangBulan.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/95daace1-4717-428d-9cf2-7537b16a583a" />

### C. Abstraction (Pengabstrakan)
Abstraction adalah penyembunyian detail rincian implementasi dan hanya menampilkan fungsionalitas utama kepada pengguna.

Penerapan: Kelas Typesetter dikesampingkan sebagai abstract class sehingga tidak dapat diinstansiasi langsung (new Typesetter() tidak diperbolehkan).

Abstract Method: Terdapat method abstrak public abstract double hitungBonus(int totalChapter); dan public abstract String getPeran(); yang memaksa setiap subclass memberikan implementasi spesifiknya masing-masing.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/2245aa98-a110-432b-99be-363e5f653481" />


### D. Polymorphism (Polimorfisme)
Polymorphism memungkinkan suatu objek atau method memiliki banyak bentuk perilaku tergantung kelas yang mengimplementasikannya (Method Overriding).

Penerapan: Perhitungan bonus typesetter dihitung secara polimorfik via method hitungBonus(int totalChapter):
- Pada TypesetterTetap, bonus dihitung berdasarkan persentase dari gajiPokok.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/958b1adf-864b-4004-b7c1-3436bae79e67" />


- Pada TypesetterMagang, bonus dihitung berdasarkan perkalian jumlah chapter pengerjaan.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/480f691a-d447-4f13-aa66-987c291aa907" />

---

## 5. Penjelasan Penerapan JDBC & DAO Pattern
### A. Prepared Statement JDBC
Seluruh kueri SQL di dalam ProyekDAOImpl.java menggunakan antarmuka java.sql.PreparedStatement dengan parameter tempat (placeholder) ?.

- Keamanan: Mencegah celah kerentanan SQL Injection karena input pengguna diperlakukan sebagai nilai murni, bukan sintaks perintah SQL yang dapat dieksekusi.
- Efisiensi: Kueri SQL dikompilasi satu kali oleh server MySQL sehingga eksekusi berulang menjadi lebih cepat.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/cc9ac815-5f14-42d2-bfb7-8fdc9d54451d" />

### B. Pengelolaan Transaksi Multi-Tabel
Penyimpanan proyek melibatkan 3 tabel terikat (Foreign Key): typesetter, komik, dan proyek_typeset.

- Untuk menjaga integritas data (ACID Property), DAO mematikan авто-commit bawaan via conn.setAutoCommit(false).
- Ketiga perintah INSERT dieksekusi bertahap. Jika seluruh proses berhasil, conn.commit() dipanggil. Jika salah satu gagal, conn.rollback() dijalankan untuk membatalkan seluruh perubahan.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/89278065-90ee-4d97-8df9-5c8d7f305ebb" />

### C. Dummy Data Awal via .sql
Sesuai instruksi tugas, database diinisialisasi secara eksternal melalui file db_typesetting_komik.sql[cite: 5]. File ini memuat struktur skema database serta dummy data awal (TS01, TS02, K01, K02, PRJ01, PRJ02) sehingga saat fitur Read pertama kali diklik, data langsung muncul dari database MySQL tanpa perlu di-hardcode di kode Java.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a789adb0-4fbc-4e89-9fb2-9786748c07ec" />




