package view;

import model.*;
import service.InventoryService;
import service.OrderItemServiceInFile;
import service.ProductServiceInFile;
import service.VmService;
import utils.AppUtils;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InventoryView {
    private static Scanner scanner = new Scanner(System.in);
    private InventoryService inventoryService;
    private static VmService vmService;
    private OrderItemServiceInFile orderItemServiceInFile;
    private ProductServiceInFile productService;


    public InventoryView() {
        orderItemServiceInFile = new OrderItemServiceInFile();
        inventoryService = new InventoryService();
        vmService = new VmService();
        productService = new ProductServiceInFile();
    }

    public void launcher() {
        boolean actionMenu = true;
        while (actionMenu) {
            try {
                AppUtils.menuInventoryView();

                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        showInventories(inventoryService.findAllInventory());
                        break;
                    case 2:
                        createInventory();
                        break;
                    case 3:
                        showInventoryDetail();
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

    private void showInventoryDetail() {
        System.out.println("Nhập mã Inventory cần xem:");
        long idInventory = Long.parseLong(scanner.nextLine());
        Inventory inventory = inventoryService.findInventory(idInventory);

        System.out.printf("%-55s%-15s\n", "ID VENDING MACHINE", inventory.getIdVm());
        VendingMachine vendingMachine = vmService.findVm(inventory.getIdVm());
        System.out.printf("%-55s%-15s\n", "NAME VENDING MACHINE", vendingMachine.getNameVm());
        System.out.printf("%-55s%-15s\n", "ID INVENTORY", inventory.getIdInventory());
//                "%s,%s,%s,%s,%s", this.idInventoryItems, this.idInventory,
//                        this.idProduct, this.quantityPut, this.quantitySold

        System.out.printf("%-20s%-20s%-15s%-15s\n", "Id Product", "Name", "Quantity_Put", "Quantity_Sold");
        for (InventoryItems item : inventory.getInventoryItems()) {
            if (item.getIdInventory() == idInventory) {
                Product p = productService.findProduct(item.getIdProduct());
                System.out.printf("%-20s%-20s%-15s%-15s\n", p.getId(), p.getName(), item.getQuantityPut(),
                        item.getQuantitySold());
            }
        }
    }

    private void createInventory() {

        Inventory inventory = new Inventory();
        inventory.setIdInventory(System.currentTimeMillis() / 1000);
        boolean checkContinueAddInventoryItem = false;
        VendingMachine vendingMachine = inputIdVm();
        inventory.setIdVm(vendingMachine.getIdVm());
        do {
            Product product = inputIdProduct();

            System.out.println("Nhập số lượng: ");

            int quantityPut = Integer.parseInt(scanner.nextLine());

            int quantitySold = getQuantitySold(product);

            if (inventory.getInventoryItems() == null) {
                List<InventoryItems> inventoryItems = new ArrayList<>();
//            Long idInventoryItems, Long idInventory, Long idProduct, int quantityPut, int quantitySold
                InventoryItems inventoryItem = new InventoryItems(System.currentTimeMillis() % 1000, inventory.getIdInventory(),
                        product.getId(), quantityPut, quantitySold);
                inventoryItems.add(inventoryItem);
                inventory.setInventoryItems(inventoryItems);
            } else {
                if (checkProductExistInventory(inventory, product)) {
                    for (InventoryItems item : inventory.getInventoryItems()) {
                        if (item.getIdProduct() == product.getId()) {
                            item.setQuantityPut(quantityPut);
                        }
                    }
                } else {
                    InventoryItems inventoryItem = new InventoryItems(System.currentTimeMillis() % 1000, inventory.getIdInventory(),
                            product.getId(), quantityPut, quantitySold);
                    inventory.getInventoryItems().add(inventoryItem);
                }
            }
            System.out.println("Bạn có muốn thêm sản phẩm không");
            System.out.println("Nhập 1: Yes");
            System.out.println("Nhập 2: No");
            int actionAddOrderItem = Integer.parseInt(scanner.nextLine());
            switch (actionAddOrderItem) {
                case 1:
                    checkContinueAddInventoryItem = true;
                    break;
                case 2:
                    checkContinueAddInventoryItem = false;
                    break;
            }
        } while (checkContinueAddInventoryItem);
        inventory.updatePriceImport();
        inventory.updateQuantityImport();
        inventory.setDateImport(new Date());

        // đã tạo ra được 1 inventory mới
        // Kiểm tra hàng trong máy còn bao nhiêu nếu số lượng nhập vào nhiều hơn thì đưa ra cảnh báo
//        System.out.println(inventory);
//        if ( inventory.getQuantityProduct() > vendingMachine.getCapacity()- ())
//        for (InventoryItems item : inventory.getInventoryItems()) {
//            System.out.println(item);
//        }

        inventoryService.createInventory(inventory);
        System.out.println("Tạo Inventory thành công!");
    }

    private int getQuantitySold(Product product) {
        int quantitySold = 0;
        List<OrderItem> orderItemList = orderItemServiceInFile.findAllOrderItem();
        for (OrderItem item : orderItemList) {
            if (item.getIdProduct() == product.getId()) {
                quantitySold += item.getQuantity();
            }
        }
        return quantitySold;
    }

    private Product inputIdProduct() {
        Product product = null;
        boolean checkEditProductValid = false;
        do {
            try {
                System.out.println("Nhập id san pham:");
                long idProduct = Long.parseLong(scanner.nextLine());
                product = productService.findProduct(idProduct);
                if (product == null) {
                    System.out.println("Id sản phẩm không hợp lệ");
                    System.out.println("Chọn 1. Nhập lại");
                    System.out.println("Chọn 2. Quay lại");
                    int actionEditId = Integer.parseInt(scanner.nextLine());
                    switch (actionEditId) {
                        case 1:
                            checkEditProductValid = true;
                            break;
                        case 2:
                            checkEditProductValid = false;
                            break;
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Id không đúng định dạng vui lòng nhập lại");
                checkEditProductValid = true;
            }

        } while (checkEditProductValid);

        return product;
    }

    private boolean checkProductExistInventory(Inventory inventory, Product product) {
        if (inventory.getInventoryItems() == null) {
            return false;
        } else {
            for (InventoryItems item : inventory.getInventoryItems()) {
                if (item.getIdProduct() == product.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    static VendingMachine inputIdVm() {
        VendingMachine vendingMachine = null;
        boolean checkEditVmValid = false;
        do {
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
        System.out.println("+-----------------+-----------------+----------------------+----------------------+------------+------------+-----------------+-----------------+");
        System.out.println("|      ID_IV      |      ID_VM      |       NAME_VM        |       DATE INPUT     |   TOTAL    |    SOLD    |   PRICE IMPORT  |    PRICE SALE   |");
        System.out.println("+-----------------+-----------------+----------------------+----------------------+------------+------------+-----------------+-----------------+");
//        System.out.printf("%-15s | %-15s | %-25s | %-15s | %-15s | %-15s| %-15s| %-15s\n",
//                "ID_IV", "ID_VM", "DATE INPUT", "TOTAL", "SOLD", "PRICE IMPORT", "PRICE SALE");
        for (Inventory inventory : allInventory) {
            System.out.printf("| %-15s | %-15s | %-20s | %-20s | %10s | %10s | %15s | %15s |\n",
                    inventory.getIdInventory(), inventory.getIdVm(), vmService.findVm(inventory.getIdVm()).getNameVm(), DateUtils.format(inventory.getDateImport()),
                    inventory.getQuantityProduct(), inventory.getQuantitySold(), inventory.getPriceImport(), inventory.getPriceSale());
        }
        System.out.println("+-----------------+-----------------+----------------------+----------------------+------------+------------+-----------------+-----------------+");
    }
}
