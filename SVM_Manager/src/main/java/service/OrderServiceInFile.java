package service;

import model.*;
import utils.FileUtils;

import java.util.List;

public class OrderServiceInFile implements IOrderService{
    private final String path = "./data/order.csv";
    private IOrderItemService orderItemServiceInFile;

    public OrderServiceInFile() {
        orderItemServiceInFile = new OrderItemServiceInFile();
    }
    public List<Order> findAllOrder() {
        List<Order> orders = FileUtils.readFile(path, Order.class);
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemServiceInFile.findAllOrderItem();
            order.setOrderItems(orderItems);
        }
        return orders;
    }
    public void createOrder(Order order) {
        List<Order> orders = findAllOrder();
        orderItemServiceInFile.saveOrderItemByOrder(order);
        orders.add(order);

        InventoryService inventoryService = new InventoryService();
        List<Inventory> items = inventoryService.findAllInventory();

//        for (OrderItem orderItem : order.getOrderItems()){
//            for (Inventory item : items) {
//                if (item.getQuantityProduct() != item.getQuantitySold()) {
//                    VmService vmService = new VmService();
//                    List<VendingMachine> vendingMachineList = vmService.findAllVendingMachine();
//                    for (VendingMachine vm : vendingMachineList) {
//                        if (item.getIdVm() == vm.getIdVm()) {
//                            item.setQuantitySold(item.getQuantitySold()+orderItem.getQuantity());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        inventoryService.saveInventory(items);
        FileUtils.writeFile(path,orders);
    }
    public Order findOrder(long idOrder) {
        List<Order> list = findAllOrder();
        for (Order order : list) {
            if (order.getIdOrder() == idOrder) {
                return order;
            }
        }
        return null;
    }
}
