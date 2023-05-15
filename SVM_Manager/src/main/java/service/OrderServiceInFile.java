package service;

import model.Order;
import model.OrderItem;
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
        FileUtils.writeFile(path,orders);
    }
    public Order findOrder(long idOrder) {
        List<Order> list = findAllOrder();
        for (Order order : list) {
            if (order.getIdOder() == idOrder) {
                return order;
            }
        }
        return null;
    }
}
