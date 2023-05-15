package service;

import model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAllOrder();
    void createOrder(Order order);
    Order findOrder(long idOrder);

}
