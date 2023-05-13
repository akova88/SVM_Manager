package model;

import utils.DateUtils;

import java.util.Date;

public class Inventory implements IModel<Inventory> {
    private long idInventory;
    private long idVm;
    private Date dateImport;
    private int quantityProduct;
    private int quantitySold;
    private float priceImport;
    private float priceSale;

    public Inventory() {
    }

    public Inventory(long idInventory, long idVm, Date dateImport, int quantityProduct,
                     int quantitySold, float priceImport, float priceSale) {
        this.idInventory = idInventory;
        this.idVm = idVm;
        this.dateImport = dateImport;
        this.quantityProduct = quantityProduct;
        this.quantitySold = quantitySold;
        this.priceImport = priceImport;
        this.priceSale = priceSale;
    }

    public long getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(long idInventory) {
        this.idInventory = idInventory;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public float getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(float priceImport) {
        this.priceImport = priceImport;
    }

    public float getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(float priceSale) {
        this.priceSale = priceSale;
    }

    @Override
    public String toString() {
//        idInventory, idVm, dateImport, quantityProduct, quantitySold, priceImport, priceSale
        return String.format("%s,%s,%s,%s,%s,%s,%s", this.idInventory, this.idVm, this.dateImport,
                this.quantityProduct, this.quantitySold, this.priceImport, this.priceSale);
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.idInventory = Long.parseLong(items[0]);
        this.idVm = Long.parseLong(items[1]);
        this.dateImport = DateUtils.parse(items[2]);
        this.quantityProduct = Integer.parseInt(items[3]);
        this.quantitySold = Integer.parseInt(items[4]);
        this.priceImport = Float.parseFloat(items[5]);
        this.priceSale = Float.parseFloat(items[6]);
    }
}
