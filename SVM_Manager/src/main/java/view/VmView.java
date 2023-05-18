package view;

import model.Product;
import model.VendingMachine;
import service.VmService;
import utils.AppUtils;

import java.util.List;
import java.util.Scanner;

public class VmView {
    private Scanner scanner = new Scanner(System.in);
    private VmService vmService;

    public VmView() {
        vmService = new VmService();
    }
    public void launch() {
        boolean actionMenu = true;
        while (actionMenu) {
            try {
                AppUtils.menuVmView();

                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        showVendingMachine(vmService.findAllVendingMachine());
                        break;
                    case 2:
                        showCreateVendingMachine();
                        showVendingMachine(vmService.findAllVendingMachine());
                        System.out.println("Tạo Vending Machine thành công!");
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 0:
                        actionMenu = false;
                        break;
                    default:
                        System.out.println("Bạn nhập không hợp lệ, Vui lòng nhập lại");
                }
            }catch (NumberFormatException numberFormatException) {
                System.out.println("Định dạng không đúng. Vui lòng nhập lại");
            }
        }
    }

    private void showCreateVendingMachine() {
        System.out.println("New Vending Machine");
        System.out.println("Nhập tên máy:");
        String nameNew = scanner.nextLine();
        System.out.println("Nhập sức chưa sản phẩm của máy:");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập địa chỉ đặt máy:");
        String address = scanner.nextLine();
        System.out.println("Nhập số tiền có sẵn trong máy:");
        float priceNew = Float.parseFloat(scanner.nextLine());

        VendingMachine vendingMachineNew = new VendingMachine(System.currentTimeMillis() / 10000,nameNew,capacity,address,priceNew);
        vmService.addVendingMachine(vendingMachineNew);
    }

    private void showVendingMachine(List<VendingMachine> allVendingMachine) {
//        long idVm, String nameVm, int capacity, String address, float currentBalance
        System.out.println("+-----------------+----------------------+------------+----------------------+----------------------+");
        System.out.println("|      ID_VM      |       NAME_VM        |  CAPACITY  |        ADDRESS       |    CURRENT_BALANCE   |");
        System.out.println("+-----------------+----------------------+------------+----------------------+----------------------+");
//        System.out.printf("| %-15s | %-20s | %-10s | %-20s | %-10s\n", "ID_VM", "NAME_VM","CAPACITY", "ADDRESS", "CURRENT_BALANCE");
        for (VendingMachine vm : allVendingMachine) {
            System.out.printf("| %-15s | %-20s | %-10s | %-20s | %20s |\n", vm.getIdVm(), vm.getNameVm(),vm.getCapacity(), vm.getAddress(),
                    vm.getCurrentBalance());
        }
        System.out.println("+-----------------+----------------------+------------+----------------------+----------------------+");
    }
}


