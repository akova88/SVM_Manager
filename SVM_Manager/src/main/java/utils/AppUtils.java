package utils;

import model.ERole;

import java.util.Scanner;

public class AppUtils {
    public static Scanner scanner = new Scanner(System.in);
    public static void menuProductView() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("╠----------------QUẢN LÝ SẢN PHẨM---------------╣");
        System.out.println("║                                               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ ► [1] Hiển thị danh sách sản phẩm             ║");
        System.out.println("║ ► [2].Thêm sản phẩm                           ║");
        System.out.println("║ ► [3].Xoá sản phẩm                            ║");
        System.out.println("║ ► [4].Sửa thông tin sản phẩm                  ║");
        System.out.println("║ ► [5].Tìm kiếm sản phẩm                       ║");
        System.out.println("║ ► [6].Sắp xếp sản phẩm                        ║");
        System.out.println("║ ► [0].Thoát chương trình                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void menuAdminView() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("╠--------SMART VENDING MACHINE MANAGER----------╣");
        System.out.println("║                                               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║             [1] MANAGER PRODUCT               ║");
        System.out.println("║             [2] VENDING MACHINE               ║");
        System.out.println("║             [3] INVENTORY MANAGER             ║");
        System.out.println("║             [4] ORDER MANAGER                 ║");
        System.out.println("║             [5].USER MANAGER                  ║");
        System.out.println("║             [0].QUIT                          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Enter option: ");
    }

    public static void menuVmView() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("╠-------------QUẢN LÝ VENDING MACHINE-----------╣");
        System.out.println("║                                               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ ► [1] Xem thông tin máy bán hàng              ║");
        System.out.println("║ ► [2] Tạo mới 1 Vending Machine               ║");
        System.out.println("║ ► [3]                                         ║");
        System.out.println("║ ► [4]                                         ║");
        System.out.println("║ ► [5]                                         ║");
        System.out.println("║ ► [6]                                         ║");
        System.out.println("║ ► [0].Thoát chương trình                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void menuInventoryView() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("╠----------------QUẢN LÝ INVENTORY--------------╣");
        System.out.println("║                                               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ ► [1] Hiển thị danh sách nhập hàng            ║");
        System.out.println("║ ► [2] Nhập hàng theo ID VENDING MACHINE       ║");
        System.out.println("║ ► [3] Xem chi tiết nhập hàng của 1 máy        ║");
        System.out.println("║ ► [0].Thoát chương trình                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void menuOrderView() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                                               ║");
        System.out.println("╠-----------------QUẢN LÝ ORDER-----------------╣");
        System.out.println("║                                               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ ► [1] Hiển thị danh sách Order                ║");
        System.out.println("║ ► [2] Thêm Order                              ║");
        System.out.println("║ ► [3] Xem chi tiết Order                      ║");
        System.out.println("║ ► [0].Thoát chương trình                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Nhập lựa chọn của bạn: ");
    }
}
