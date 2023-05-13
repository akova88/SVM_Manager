package model;

import java.util.List;

public class VendingMachine implements IModel<VendingMachine>{
    private long idVm;
    private String nameVm;
    private String address;
    private  float currentBalance = 5000000;
    private List<Inventory> inventories;

    public VendingMachine() {
    }
    public VendingMachine(long idVm, String nameVm, String address, float currentBalance) {
        this.idVm = idVm;
        this.nameVm = nameVm;
        this.address = address;
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s",this.idVm, this.nameVm, this.address, this.currentBalance);
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public long getIdVm() {
        return idVm;
    }

    public void setIdVm(long idVm) {
        this.idVm = idVm;
    }

    public String getNameVm() {
        return nameVm;
    }

    public void setNameVm(String nameVm) {
        this.nameVm = nameVm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public void parseData(String line) {
//        long idVm, String nameVm, String address, float currentBalance
        String[] items = line.split(",");
        this.idVm = Long.parseLong(items[0]);
        this.nameVm = items[1];
        this.address = items[2];
        this.currentBalance = Float.parseFloat(items[3]);
    }
}
