import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main( String[] args) {

        // Membuat array menu
        Menu[] menu = new Menu[]{
                new Menu("Nasi Padang", 25000, "Makanan"),
                new Menu("Ayam Bakar", 30000, "Makanan"),
                new Menu("Soto Ayam", 20000, "Makanan"),
                new Menu("Mie Ayam", 15000, "Makanan"),

                new Menu("Es Teh", 5000, "Minuman"),
                new Menu("Es Jeruk", 6000, "Minuman"),
                new Menu("Air Mineral", 4000, "Minuman"),
                new Menu("Jus Alpukat", 10000, "Minuman")
        };

        // Menampilkan daftar menu
        tampilDaftarMenu(menu);


        // Menerima pesanan dari pelanggan
        terimaPesanan(menu);
    }

    public static void tampilDaftarMenu(Menu[] menu) {
        System.out.println("Daftar Menu Makanan:");
        for (Menu m : menu) {
            if (m.getKategori().equals("Makanan")) {
                System.out.println("* " + m.getNama() + " (" + m.getHarga() + ")");
            }
        }

        System.out.println();
        System.out.println("Daftar Menu Minuman:");
        for (Menu m : menu) {
            if (m.getKategori().equals("Minuman")) {
                System.out.println("* " + m.getNama() + " (" + m.getHarga() + ")");
            }
        }
    }

    public static void terimaPesanan(Menu[] menu) {
        int[] pesananList = new int[4];
        int totalHargaPesanan = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan pesanan Anda (maksimal 4 menu) atau ketik 'selesai' untuk selesai :");
        int i = 0;
        int j = 0;
//        Pesanan[] pesanan = new Pesanan[]{};
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();
        while (i < pesananList.length) {
            String pesananInput = scanner.nextLine().trim();

            if (pesananInput.equalsIgnoreCase("selesai")) {
                break; // Keluar dari loop jika pengguna mengetik 'selesai'
            }

            // Memisahkan nama menu dan jumlah dengan menggunakan "=" sebagai pemisah
            String[] pesananSplit = pesananInput.split("=");

            if (pesananSplit.length != 2) {
                System.out.println("Format pesanan tidak valid. Gunakan format 'Nama Menu = Jumlah' atau ketik 'selesai' untuk selesai.");
                continue;
            }

            String namaMenu = pesananSplit[0].trim();
            int jumlah = Integer.parseInt(pesananSplit[1].trim());

            // Cari menu yang sesuai dengan nama menu yang diinputkan
            boolean found = false;
            for (j = 0; j < menu.length; j++) {
                if (menu[j].getNama().equalsIgnoreCase(namaMenu)) {
                    // masukkan pesanan ke array class pesanan
                    daftarPesanan.add(new Pesanan(namaMenu, jumlah, menu[j].getHarga()));
                    found = true;
                    i++;
                    break;
                }
            }

            if (!found) {
                System.out.println("Menu '" + namaMenu + "' tidak ditemukan.");
            }
        }


        // Calculate the totalHargaPesanan
        for (Pesanan m : daftarPesanan) {
            if (m != null) {
                totalHargaPesanan += m.getTotalHarga() * m.getTotalPesanan();
            }
        }
        System.out.println("Total Harga Pesanan: " + totalHargaPesanan);

        // Apply an offer if the totalHargaPesanan is less than 50,000
        if (totalHargaPesanan > 50000) {
            System.out.println("Selamat! Anda mendapatkan penawaran khusus by 1 get 1 Minuman.");
            // Tampilkan menu kategori minuman
            System.out.println("Daftar Menu Minuman:");
            for (Menu m : menu) {
                if (m.getKategori().equals("Minuman")) {
                    System.out.println("* " + m.getNama() + " (" + m.getHarga() + ")");
                }
            }
            int[] minumanList = new int[1];
            int y = 0;
            System.out.println("Masukkan nama minuman yang ingin Anda tambahkan (maksimal 1 menu) atau ketik 'selesai' untuk selesai :");
            while (y < minumanList.length) {
                String minumamInput = scanner.nextLine();
                boolean foundDrink = false;
                if (minumamInput.equalsIgnoreCase("selesai")) {
                    break; // Keluar dari loop jika pengguna mengetik 'selesai'
                }
                for (Menu value : menu) {
                    if (value.getNama().equalsIgnoreCase(minumamInput)) {
                        // masukkan pesanan ke array class pesanan
                        daftarPesanan.add(new Pesanan(minumamInput, 1, value.getHarga()));
                        daftarPesanan.add(new Pesanan(minumamInput, 1, 0));

                        foundDrink = true;
                        y++;
                        break;
                    }
                }
                if (!foundDrink) {
                    System.out.println("Menu '" + minumamInput + "' tidak ditemukan.");
                }
            }
        }

        scanner.close(); // Ingat untuk menutup scanner setelah selesai menggunakannya.
        tampilkanStruk(daftarPesanan.toArray(new Pesanan[0]));
    }

    private static void tampilkanStruk(Pesanan[] pesanan) {
        int totalPembelianSebelumPajak = 0;
        int totalPajak = 0;
        int totalPembelianSesudahPajak = 0;
        int totalBiayaPelayanan = 20000;
        int totalHarusDibayarkan = 0;
        System.out.println("Struk Pesanan");
        System.out.println("Nama Menu | Jumlah | Harga | Total");
        System.out.println("------- | -------- | -------- | --------");
        for (Pesanan m : pesanan) {
            if (m != null) {
                System.out.println(m.getNamaPesanan() + " | " + m.getTotalPesanan() + " | " + m.getTotalHarga() + " | " + m.getTotalHarga() * m.getTotalPesanan());
                totalPembelianSebelumPajak += m.getTotalHarga() * m.getTotalPesanan();
            }
        }

        System.out.println("Total Biaya | | | " + totalPembelianSebelumPajak);
        if (totalPembelianSebelumPajak > 100000) {
            System.out.println("Diskon (10%) | | | " + (totalPembelianSebelumPajak * 0.1));
            totalPembelianSebelumPajak -= (totalPembelianSebelumPajak * 0.1);
            System.out.println("Total Biaya Setelah Diskon | | | " + totalPembelianSebelumPajak);
        }
        System.out.println("Biaya Pajak (10%) | | | " + (totalPembelianSebelumPajak * 0.1));
        totalPajak = totalPembelianSebelumPajak * 10 / 100;
        totalPembelianSesudahPajak = totalPembelianSebelumPajak + totalPajak;
        System.out.println("Total Pembelian Sesudah Pajak | | | " + totalPembelianSesudahPajak);
        System.out.println("Biaya Pelayanan | | | " + totalBiayaPelayanan);
        totalHarusDibayarkan = totalPembelianSesudahPajak + totalBiayaPelayanan;
        System.out.println("Total Pembayaran | | | " + totalHarusDibayarkan);
        System.out.println("Terima kasih telah memesan di restoran kami!");
    }
}