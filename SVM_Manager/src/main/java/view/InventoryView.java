package view;

import model.*;
import service.InventoryService;
import service.VmService;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryView {
    private static Scanner scanner = new Scanner(System.in);
    private InventoryService inventoryService;
    private static VmService vmService;


    public InventoryView() {
        inventoryService = new InventoryService();
        vmService = new VmService();
    }
    public void launcher() {
        do{
            System.out.println("Menu chương trình");
            System.out.println("Nhập 1: Xem danh sách nhập hàng");
            System.out.println("Nhập 2: Nhập hàng theo ID Vending Machine");
            System.out.println("Nhập 3: Xem chi tiết nhập hàng theo ID ");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showInventories(inventoryService.findAllInventory());
                    break;
                case 2:
                    createInventory();
                    break;
//                case 3:
//                    showOrderDetail();
//                    break;
            }
        } while (true);
    }

    private void createInventory() {
        Inventory inventory = new Inventory();
        inventory.setIdInventory(System.currentTimeMillis() / 1000);
        VendingMachine vendingMachine = inputIdVm();
        inventory.setIdVm(vendingMachine.getIdVm());

        Product product = OrderView.inputIdProduct();

        System.out.println("Nhập số lượng: ");
        int quantityPut = Integer.parseInt(scanner.nextLine());


        if (inventory.getInventoryItems() == null) {
            List<InventoryItems> inventoryItems = new ArrayList<>();
//            Long idInventoryItems, Long idInventory, Long idProduct, int quantityPut, int quantitySold
            InventoryItems inventoryItem = new InventoryItems(System.currentTimeMillis() % 1000,inventory.getIdInventory(),
                    product.getId(), quantityPut,qu );
        }
    }

    static VendingMachine inputIdVm() {
        VendingMachine vendingMachine = null;
        boolean checkEditVmValid = false;
        do{
            try {
        System.out.println("Nhập Id Vending Machine");
        long idVm = Long.parseLong(scanner.nextLine());
        vendingMachine = vmService.findVm(idVm);
        if (vendingMachine == null) {
            System.out.println("Id Vending Machine không hợp lệ");
            System.out.println("Chọn 1: Nhập lại");
            System.out.println("Chọn 2: Quay lại");
            int actionEditId = Integer.parseInt(scanner.nextLine());
            switch (actionEditId) {
                case 1:
                    checkEditVmValid = true;
                    break;
                case 2:
                    checkEditVmValid = false;
                    break;
                default:
                    System.out.println("Nhập không hợp lệ, nhập lại");
                    checkEditVmValid = true;
                    break;
            }
        }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Id không đúng định dạng, nhập lại");
                checkEditVmValid = true;
            }
        } while (checkEditVmValid);
        return vendingMachine;
    }

    private void showInventories(List<Inventory> allInventory) {
//        long idInventory, long idVm, Date dateImport, int quantityProduct,
//        int quantitySold, float priceImport, float priceSale
        System.out.printf("%-15s | %-15s | %-20s | %-15s | %-15s| %-15s| %-15s\n",
                "ID_IV","ID_VM","DATE INPUT", "TOTAL","SOLD", "PRICE IMPORT", "PRICE SALE");
        for (Inventory inventory : allInventory) {
            System.out.printf("%-15s | %-15s | %-20s | %-15s | %-15s| %-15s| %-15s\n",
                    inventory.getIdInventory(), inventory.getIdVm(),DateUtils.format(inventory.getDateImport()),
                    inventory.getQuantityProduct(),inventory.getQuantitySold(),inventory.getPriceImport(),inventory.getPriceSale());
        }
    }
}
