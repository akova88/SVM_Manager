package view;

import model.*;
import service.*;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private static Scanner scanner = new Scanner(System.in);
    private IOrderService orderServiceInFile;
    private static ProductServiceInFile productService;
    private InventoryService inventoryService;
    private InventoryItemService inventoryItemService;

    public OrderView() {
        orderServiceInFile = new OrderServiceInFile();
        productService = new ProductServiceInFile();
        inventoryService = new InventoryService();
        inventoryItemService = new InventoryItemService();
    }
    public void launcher() {
        do{
            System.out.println("Menu chương trình");
            System.out.println("Nhập 1: Xem danh sách order");
            System.out.println("Nhập 2: Thêm order");
            System.out.println("Nhập 3: Xem chi tiết order");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showOrders(orderServiceInFile.findAllOrder());
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    showOrderDetail();
                    break;
            }
        } while (true);
    }
    private void createOrder() {
        Order order = new Order();
        long idOrder = System.currentTimeMillis() % 1000;
        order.setIdOrder(idOrder);

        InventoryView inventoryView = new InventoryView();
        VendingMachine vendingMachine = inventoryView.inputIdVm();
        order.setIdVm(vendingMachine.getIdVm());

        boolean checkContinueAddOrderItem = false;
        do{
            showVmToBuy(vendingMachine); // Hiện máy bán hàng để khách chọn mua

            Product product = inputIdProduct();
            System.out.println("Nhập số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (!checkQuantity(vendingMachine, quantity, product)) {
                System.out.println("Nhập lại, quá số lượng có trong máy");
            }

            if (order.getOrderItems() == null) {
                List<OrderItem> orderItems = new ArrayList<>();
                OrderItem orderItem = new OrderItem(System.currentTimeMillis() % 1000,
                        order.getIdOrder(), product.getId(), quantity, product.getPrice());
                orderItems.add(orderItem);
                order.setOrderItems(orderItems);
            } else {
                if (checkProductExistOrder(order, product)) {
                    for (OrderItem orderItem : order.getOrderItems()) {
                        if (orderItem.getIdProduct() == product.getId()) {
                            orderItem.setQuantity(quantity);

                        }
                    }
                } else {
                    OrderItem orderItem = new OrderItem(System.currentTimeMillis() % 1000,
                            order.getIdOrder(), product.getId(), quantity, product.getPrice());
                    order.getOrderItems().add(orderItem);
                }
            }
            System.out.println("Bạn có muốn thêm sản phẩm khác không?");
            System.out.println("Nhập 1: Yes");
            System.out.println("Nhập 2: No");
            int actionAddOrderItem = Integer.parseInt(scanner.nextLine());
            switch (actionAddOrderItem) {
                case 1:
                    checkContinueAddOrderItem = true;
                    break;
                case 2:
                    checkContinueAddOrderItem = false;
                    break;
                default:
                    System.out.println("Nhập không hợp lệ, nhập lại");
                    checkContinueAddOrderItem = true;
                    break;
            }
        } while (checkContinueAddOrderItem);
        order.updateTotal();
        order.setCreateAt(new Date());
        orderServiceInFile.createOrder(order);
        System.out.println("Tao order thanh cong");
        showOrderBill(idOrder,order);
    }

    public boolean checkQuantity(VendingMachine vendingMachine, int quantity, Product product) {

        List<Inventory> inventoryList = inventoryService.findAllInventoryByIdVm(vendingMachine.getIdVm());
        List<InventoryItems> inventoryItemsList = inventoryItemService.findAllByInventoryId(inventoryList.get(0).getIdInventory());
        for (InventoryItems item : inventoryItemsList) {
            if( quantity > (item.getQuantityPut() - item.getQuantitySold()) && item.getIdProduct() == product.getId())
                return true;
        }
        return false;
    }

    private void showVmToBuy(VendingMachine vendingMachine) {
        List<Inventory> inventoryList = inventoryService.findAllInventoryByIdVm(vendingMachine.getIdVm());
        List<InventoryItems> inventoryItemsList = inventoryItemService.findAllByInventoryId(inventoryList.get(0).getIdInventory());
        System.out.println("VENDING MACHINE " + vendingMachine.getNameVm() + " XIN CHÀO QUÝ KHÁCH");
        System.out.printf("%-10s%-20s%-10s%-10s\n","ID", "Name", "Quantity", "Price");
        for (InventoryItems item : inventoryItemsList) {
            String nameProductShowChoose = productService.findProduct(item.getIdProduct()).getName();
            float priceProductChoose = productService.findProduct(item.getIdProduct()).getPrice();
            System.out.printf("%-10s%-20s%-10s%-10s\n", item.getIdProduct(), nameProductShowChoose,
                    item.getQuantityPut()- item.getQuantitySold(),priceProductChoose );
         }
        System.out.printf("%-10s","PLEASE CHOOSE ID PRODUCT TO BUY\n");
    }

    private boolean checkProductExistOrder(Order order, Product product) {
        if (order.getOrderItems() == null) {
            return false;
        } else {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getIdProduct() == product.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    static Product inputIdProduct() {
        Product product = null;
        boolean checkEditProductValid = false;
        do{
            try {
                System.out.println("Nhập id sản phẩm:");
                long idProduct = Long.parseLong(scanner.nextLine());
                product = productService.findProduct(idProduct);
                if (product == null) {
                    System.out.println("Id sản phẩm không hợp lệ");
                    System.out.println("Chọn 1: Nhập lại");
                    System.out.println("Chọn 2: Quay lại");
                    int actionEditId = Integer.parseInt(scanner.nextLine());
                    switch (actionEditId) {
                        case 1:
                            checkEditProductValid = true;
                            break;
                        case 2:
                            checkEditProductValid = false;
                            break;
                        default:
                            System.out.println("Nhập không hợp lệ, nhập lại");
                            checkEditProductValid = true;
                            break;
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Id không đúng định dạng, nhập lại");
                checkEditProductValid = true;
            }
        } while (checkEditProductValid);
        return product;
    }

    private void showOrderDetail() {
        System.out.println("Nhập mã hoá đơn cần xem:");
        long idOrder = Long.parseLong(scanner.nextLine());
        Order order = orderServiceInFile.findOrder(idOrder);
        if (order != null) {
            showOrderBill(idOrder, order);
        } else {
            System.out.println("Không tìm thấy order");
        }
    }

    private static void showOrderBill(long idOrder, Order order) {
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "ID VENDING MACHINE");
        System.out.printf("| %-60d |\n", order.getIdVm());
        System.out.printf("| %-60s |\n", "ID ORDER");
        System.out.printf("| %-60d |\n", order.getIdOrder());
        System.out.println("|--------------------------------------------------------------|");
        // In tiêu đề các cột
        System.out.printf("| %-20s | %-10s | %-10s | %-11s |\n", "Name", "Quantity", "Price", "TotalItem");
        // Vẽ border giữa
        System.out.println("|--------------------------------------------------------------|");
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getIdOrder() == idOrder) {
                Product p = productService.findProduct(orderItem.getIdProduct());
                System.out.printf("| %-20s | %-10d | %-10s | %-11s |\n", p.getName(), orderItem.getQuantity(), orderItem.getPrice(),
                        orderItem.getQuantity()*orderItem.getPrice());
                System.out.printf("| %-60s |\n"," ");
            }
        }
        System.out.println("|--------------------------------------------------------------|");
        System.out.printf("| %-60s |\n", "Tổng tiền:");
        System.out.printf("| %60s |\n", order.getTotal());
        System.out.println("+--------------------------------------------------------------+");
    }

    private void showOrders(List<Order> allOrder) {
        System.out.printf("%-15s | %-20s | %-10s\n", "ID", "CREATE AT", "TOTAL");
        for (Order order : allOrder) {
            System.out.printf("%-15s | %-20s | %-10s\n",order.getIdOrder(), DateUtils.format(order.getCreateAt()),order.getTotal());
        }
    }
}
