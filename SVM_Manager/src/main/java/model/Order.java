package model;

import utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Order implements IModel<Order>{
    private long idOrder;
    private long idVm;
    private Date createAt;
    private float total;
    public List<OrderItem> orderItems;


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public Order(long idOrder, long idVm, Date createAt, float total) {
        this.idOrder = idOrder;
        this.idVm = idVm;
        this.createAt = createAt;
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", this.idOrder, this.idVm, DateUtils.format(this.createAt), this.total);
    }

    public long getIdVm() {
        return idVm;
    }

    public void setIdVm(long idVm) {
        this.idVm = idVm;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOder) {
        this.idOrder = idOder;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void updateTotal() {
        this.total = 0;
        for (OrderItem orderItem : orderItems) {
            this.total += orderItem.getPrice()* orderItem.getQuantity();
        }
    }
    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.idOrder = Long.parseLong(items[0]);
        this.idVm = Long.parseLong(items[1]);
        this.createAt = DateUtils.parse(items[2]);
        this.total = Float.parseFloat(items[3]);
    }
}
