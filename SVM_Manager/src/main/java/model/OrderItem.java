package model;

public class OrderItem implements IModel<OrderItem>{
    private long idOrderItem;
    private long idOrder;
    private long idProduct;
    private int quantity;
    private float price;

    public OrderItem(long idOrderItem, long idOrder, long idProduct, int quantity, float price) {
        this.idOrderItem = idOrderItem;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                this.idOrderItem, this.idOrder, this.idProduct, this.quantity, this.price);
    }

    public long getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(long idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.idOrderItem = Long.parseLong(items[0]);
        this.idOrder = Long.parseLong(items[1]);
        this.idProduct = Long.parseLong(items[2]);
        this.quantity = Integer.parseInt(items[3]);
        this.price = Float.parseFloat(items[4]);
    }
}
