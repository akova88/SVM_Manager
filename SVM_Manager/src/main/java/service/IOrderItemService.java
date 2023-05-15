package service;

import model.Order;
import model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> findAllOrderItem();
    List<OrderItem> findAllOrderItemByOrderId(long idOrder);
    void saveOrderItemByOrder(Order order);
}
