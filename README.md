Arunika World - Java Swing UI

Deskripsi
- Aplikasi demo GUI Java yang mereplikasi tampilan splash dengan logo dan tombol "TEKAN DISINI UNTUK MEMESAN".
- Ketika tombol diklik, tampilkan form pemesanan sederhana.

File penting
- src\ArunikaApp.java  -> Program utama (Swing)

Menambahkan logo
- Letakkan file logo Anda sebagai `logo.png` di salah satu lokasi berikut:
  - project root (sama level dengan folder `src`),
  - `src\logo.png`, atau
  - `resources\logo.png`.

Compile & Run (Windows PowerShell)

1) Compile:

```powershell
cd "c:\Users\bimaw\OneDrive\Desktop\TamanHiburanJava"
javac -d out src\ArunikaApp.java
```

2) Run:

```powershell
java -cp out ArunikaApp
```

Catatan
- Jika logo tidak ditemukan, aplikasi akan menampilkan teks placeholder.
- Anda dapat mengganti teks/warna/tata letak pada file `src\ArunikaApp.java`.

Pengembangan lebih lanjut (opsional)
- Tambahkan validasi tanggal, integrasi pembayaran, atau simpan pesanan ke file/DB.
