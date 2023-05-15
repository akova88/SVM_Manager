package model;

public class InventoryItems implements IModel{
    private Long idInventoryItems;
    private Long idInventory;
    private Long idProduct;
    private int quantityPut;
    private int quantitySold;

    public InventoryItems() {
    }

    public InventoryItems(Long idInventoryItems, Long idInventory, Long idProduct, int quantityPut, int quantitySold) {
        this.idInventoryItems = idInventoryItems;
        this.idInventory = idInventory;
        this.idProduct = idProduct;
        this.quantityPut = quantityPut;
        this.quantitySold = quantitySold;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.idInventoryItems, this.idInventory,
                this.idProduct, this.quantityPut, this.quantitySold);
    }

    public Long getIdInventoryItems() {
        return idInventoryItems;
    }

    public void setIdInventoryItems(Long idInventoryItems) {
        this.idInventoryItems = idInventoryItems;
    }

    public Long getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Long idInventory) {
        this.idInventory = idInventory;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantityPut() {
        return quantityPut;
    }

    public void setQuantityPut(int quantityPut) {
        this.quantityPut = quantityPut;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.idInventoryItems = Long.parseLong(items[0]);
        this.idInventory = Long.parseLong(items[1]);
        this.idProduct = Long.parseLong(items[2]);
        this.quantityPut = Integer.parseInt(items[3]);
        this.quantitySold = Integer.parseInt(items[4]);
    }
}
