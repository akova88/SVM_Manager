package service;

import model.Inventory;
import model.Product;
import model.VendingMachine;
import utils.FileUtils;

import java.util.List;

public class VmService {
    private static final String path = "./data/vendingmachine.csv";
    private InventoryService inventoryService;
    public static List<VendingMachine> vendingMachines;
    static {
        vendingMachines = FileUtils.readFile(path,VendingMachine.class);
    }

    public VmService() {
        inventoryService = new InventoryService();
    }

    public List<VendingMachine> findAllVendingMachine() {

        return vendingMachines;
    }
    public void addVendingMachine(VendingMachine vendingMachine) {
        vendingMachines.add(vendingMachine);
        FileUtils.writeFile(path, vendingMachines);
    }
}
