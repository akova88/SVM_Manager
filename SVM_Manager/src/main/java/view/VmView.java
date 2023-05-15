package view;

import model.VendingMachine;
import service.VmService;

import java.util.List;
import java.util.Scanner;

public class VmView {
    private Scanner scanner = new Scanner(System.in);
    private VmService vmService;

    public VmView() {
        vmService = new VmService();
    }
    public void launch() {
        do{
            System.out.println("Menu chương trình: ");
            System.out.println("Nhập 1: Xem danh sách máy bán hàng");
            System.out.println("Nhập 2: Thêm máy bán hàng");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showVendingMachine(vmService.findAllVendingMachine());
                    break;
                case 2:
                    showCreateVendingMachine();
                    showVendingMachine(vmService.findAllVendingMachine());
                    System.out.println("Thêm sản phẩm thành công!");
                    break;
            }
        } while(true);
    }

    private void showCreateVendingMachine() {
        System.out.println("New Vending Machine");
        System.out.println("Nhập tên máy:");
        String nameNew = scanner.nextLine();
        System.out.println("Nhập địa chỉ đặt máy:");
        String address = scanner.nextLine();
        System.out.println("Nhập số tiền có sẵn trong máy:");
        float priceNew = Float.parseFloat(scanner.nextLine());

        VendingMachine vendingMachineNew = new VendingMachine(System.currentTimeMillis() / 10000,nameNew,address,priceNew);
        vmService.addVendingMachine(vendingMachineNew);
    }

    private void showVendingMachine(List<VendingMachine> allVendingMachine) {
//        long idVm, String nameVm, String address, float currentBalance
        System.out.printf("%-15s %-20s %-20s %-10s\n", "ID_VM", "Name_VM", "ADDRESS", "currentBalance");
        for (VendingMachine vm : allVendingMachine) {
            System.out.printf("%-15s %-20s %-20s %-10s\n", vm.getIdVm(), vm.getNameVm(), vm.getAddress(),
                    vm.getCurrentBalance());
        }
    }
}

