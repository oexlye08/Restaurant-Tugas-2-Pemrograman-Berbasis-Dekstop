import java.util.Scanner;

public class AdminMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void tambahMenu(Menu[] menu) {
        CustomerMenu.tampilDaftarMenu(menu);

        while (true) {

            System.out.println("Masukkan nama menu yang ingin ditambahkan:");
            String namaMenu = masukkanNamaMenu();
            System.out.println("Masukkan harga menu yang ingin ditambahkan:");
            int hargaMenu = masukkanHargaMenu();
            System.out.println("Masukkan kategori menu yang ingin ditambahkan:");
            String kategoriMenu = masukkanKategoriMenu();

            Menu[] menuBaru = new Menu[menu.length + 1];
            System.arraycopy(menu, 0, menuBaru, 0, menu.length);
            menuBaru[menuBaru.length - 1] = new Menu(namaMenu, hargaMenu, kategoriMenu);

            menu = menuBaru;

            System.out.println("Menu berhasil ditambahkan: " + namaMenu);
            CustomerMenu.tampilDaftarMenu(menu);

            System.out.println("Apakah sudah selesai?");
            // user konfirmasi ya / tidak
            boolean selesaiUbah = masukkanKonfirmasi();

            if (selesaiUbah){
                break;
            }
        }
        Main.menuAdmin(menu);
    }

    public static void ubahHarga(Menu[] menu) {
        tampilDaftarMenuDenganIndexNomor(menu);

        while (true) {
            System.out.print("Masukkan nomor menu yang ingin diubah harganya:");
            int nomorMenu = masukkanNomorMenu(menu);
            System.out.print("Masukkan harga menu yang ingin diubah:");
            int hargaMenu = masukkanHargaMenu();

            System.out.println("Apakah anda yakin?");
            // user konfirmasi ya / tidak
            boolean ya = masukkanKonfirmasi();

            if (ya){
                editedMenu(menu, nomorMenu, hargaMenu);
                System.out.println("Menu berhasil diubah: " + menu[nomorMenu-1].getNama() + " = " + menu[nomorMenu-1].getHarga());
            } else {
                System.out.println("Menu gagal diibah");
            }

            tampilDaftarMenuDenganIndexNomor(menu);

            System.out.println("Apakah sudah selesai?");
            // user konfirmasi ya / tidak
            boolean selesaiUbah = masukkanKonfirmasi();

            if (selesaiUbah){
                break;
            }
        }
        Main.menuAdmin(menu);
    }

    private static void editedMenu(Menu[] menu, int nomorMenu, int hargaMenu) {
        for (Menu m : menu) {
            if (m.getNama().equalsIgnoreCase(menu[nomorMenu - 1].getNama())) {
                m.setHarga(hargaMenu);
            }
        }
    }

    public static void hapusMenu(Menu[] menu) {
        tampilDaftarMenuDenganIndexNomor(menu);

        while (true) {
            System.out.print("Masukkan nomor menu yang ingin dihapus:");
            int nomorMenu = masukkanNomorMenu(menu);

            System.out.println("Apakah anda yakin?");
            // user konfirmasi ya / tidak
            boolean ya = masukkanKonfirmasi();

            if (ya){
                System.out.println("Menu berhasil dihapus: " + menu[nomorMenu-1].getNama());
                menu = deletedMenu(menu, nomorMenu);
            } else {
                System.out.println("Menu gagal dihapus");
            }


            System.out.println("Apakah sudah selesai?");
            // user konfirmasi ya / tidak
            boolean selesaiUbah = masukkanKonfirmasi();

            if (selesaiUbah){
                break;
            }
        }
        Main.menuAdmin(menu);
    }

    private static Menu[] deletedMenu(Menu[] menu, int nomorMenu) {
        Menu[] menuBaru = new Menu[menu.length - 1];
        System.arraycopy(menu, 0, menuBaru, 0, nomorMenu - 1);
        System.arraycopy(menu, nomorMenu, menuBaru, nomorMenu - 1, menu.length - nomorMenu);
        return menuBaru;
    }

    private static boolean masukkanKonfirmasi() {
        System.out.println("1. Ya");
        System.out.println("0. Tidak");

        int userInput = scanner.nextInt();

        boolean result;

        if (userInput == 1) {
            result = true;
        } else if (userInput == 0) {
            result = false;
        } else {
            System.out.println("Input tidak valid. Harap masukkan 0 atau 1.");
            return masukkanKonfirmasi();
        }

        return result;
    }

    private static void tampilDaftarMenuDenganIndexNomor(Menu[] menu) {
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].getNama() + " (" + menu[i].getHarga() + ")");
        }
    }

    public static String masukkanKategoriMenu() {

        System.out.println("1. Makanan");
        System.out.println("2. Minuman");

        int kategoriMenu = scanner.nextInt();
        if (kategoriMenu == 1) {
            return "Makanan";
        } else if (kategoriMenu == 2) {
            return "Minuman";
        } else {
            System.out.println("Kategori menu tidak valid.");
            return masukkanKategoriMenu();
        }
    }

    public static int masukkanHargaMenu() {
        int hargaMenu = 0;
        try {
            hargaMenu = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input harga tidak valid.");
            return masukkanHargaMenu();
        }
        return hargaMenu;
    }

    public static String masukkanNamaMenu() {
        String namaMenu = scanner.nextLine();

        if (namaMenu.isEmpty()) {
            System.out.println("Nama menu tidak boleh kosong.");
            return masukkanNamaMenu();
        } else {
            return namaMenu;
        }
    }

    public static int masukkanNomorMenu(Menu[] menu) {
        int nomorMenu = 0;
        try {
            nomorMenu = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input nomor menu tidak valid.");
            return masukkanNomorMenu(menu);
        }

        if (nomorMenu < 1 || nomorMenu > menu.length) {
            System.out.println("Nomor menu tidak valid, masukkan nomor menu : ");
            return masukkanNomorMenu(menu);
        } else {
            return nomorMenu;
        }
    }

}
