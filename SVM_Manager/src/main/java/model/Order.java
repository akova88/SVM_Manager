package model;

import utils.DateUtils;

import java.util.Date;

public class Order implements IModel<Order>{
    private long idOder;
    private Date createAt;
    private float total;
    public Order(long idOder, Date createAt, float total) {
        this.idOder = idOder;
        this.createAt = createAt;
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.idOder, this.createAt, this.total);
    }

    public long getIdOder() {
        return idOder;
    }

    public void setIdOder(long idOder) {
        this.idOder = idOder;
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

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.idOder = Long.parseLong(items[0]);
        this.createAt = DateUtils.parse(items[1]);
        this.total = Float.parseFloat(items[2]);
    }
}
