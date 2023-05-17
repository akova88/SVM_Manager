package service;

import model.Inventory;
import model.InventoryItems;
import model.Order;
import model.OrderItem;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceInFile implements IOrderItemService{
    private final String path = "./data/orderitem.csv";
    public List<OrderItem> findAllOrderItem() {
        return FileUtils.readFile(path, OrderItem.class);
    }
    public List<OrderItem> findAllOrderItemByOrderId(long idOrder) {
        List<OrderItem> list = findAllOrderItem();
        List<OrderItem> result = new ArrayList<>();
        for (OrderItem item : list) {
            if (item.getIdOrder() == idOrder) {
                result.add(item);
            }
        }
        return result;
    }
    public void saveOrderItemByOrder(Order order) {
        List<OrderItem> orderItems = findAllOrderItem();
        orderItems.addAll(order.getOrderItems());

        InventoryItemService inventoryItemService = new InventoryItemService();
        InventoryService inventoryService = new InventoryService();
        List<InventoryItems> items = inventoryItemService.findAllInventoryItem();
        List<Inventory> inventories = inventoryService.findAllInventory();

        for (OrderItem orderItem : order.getOrderItems()){
            for(InventoryItems item : items ){
                for(Inventory inventory : inventories){
                    if( inventory.getIdVm() == order.getIdVm() &&
                            inventory.getIdInventory() == item.getIdInventory() &&
                            item.getQuantityPut() != item.getQuantitySold() &&
                            item.getIdProduct() == orderItem.getIdProduct()){
                        inventory.setQuantitySold(inventory.getQuantitySold() + orderItem.getQuantity());

                        inventory.setPriceSale(inventory.getPriceSale()+ orderItem.getQuantity()*
                                orderItem.getPrice());

                        item.setQuantitySold(item.getQuantitySold() + orderItem.getQuantity());
                        break;
                    }
                }
            }
        }
        inventoryService.saveInventory(inventories);
        inventoryItemService.saveInventory(items);
        FileUtils.writeFile(path, orderItems);
    }
}
