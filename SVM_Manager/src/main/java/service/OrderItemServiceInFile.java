package service;

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
        FileUtils.writeFile(path, orderItems);
    }
}
