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

        pilihUser(menu);

    }

    public static void pilihUser(Menu[] menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Restoran kami!");
        System.out.println("Silahkan pilih user:");
        System.out.println("1. Pelanggan");
        System.out.println("2. Admin");

        int pilihanUser = scanner.nextInt();
        if (pilihanUser == 1) {
            System.out.println("Anda login sebagai Pelanggan");
            menuCustomer(menu);
        } else if (pilihanUser == 2) {
            System.out.println("Anda login sebagai Admin");
            menuAdmin(menu);
        } else {
            System.out.println("Pilihan tidak valid");
            pilihUser(menu);
        }
    }

    private static void menuCustomer(Menu[] menu) {

        // Menampilkan daftar menu
        CustomerMenu.tampilDaftarMenu(menu);

        // Menerima pesanan dari pelanggan
        CustomerMenu.terimaPesanan(menu);
    }

    public static void menuAdmin(Menu[] menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Apakah anda ingin mengubah harga / menambah menu?:");
        System.out.println("1. Tambah menu");
        System.out.println("2. Ubah harga");
        System.out.println("3. Hapus menu");
        System.out.println("4. Selesai");

        int pilihanUser = scanner.nextInt();

        if (pilihanUser == 1) {
            AdminMenu.tambahMenu(menu);
        } else if (pilihanUser == 2) {
            AdminMenu.ubahHarga(menu);
        } else if (pilihanUser == 3) {
            AdminMenu.hapusMenu(menu);
        } else if (pilihanUser == 4) {
            pilihUser(menu);
        } else {
            System.out.println("Pilihan tidak valid");
            menuAdmin(menu);
        }
    }
}