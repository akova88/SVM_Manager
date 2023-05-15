package service;

import model.Inventory;
import model.VendingMachine;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final String path = "./data/inventory.csv";
    public List<Inventory> findAllInventory() {
        return FileUtils.readFile(path,Inventory.class);
    }
    public List<Inventory> findAllInventoryByIdVm(long idVm) {
        List<Inventory>  list = findAllInventory();
        List<Inventory> result = new ArrayList<>();
        for (Inventory item : list) {
            if (item.getIdVm() == idVm) {
                result.add(item);
            }
        }
        return result;
    }
    public void saveInventoryByVM(VendingMachine vendingMachine) {
        List<Inventory>  inventories = findAllInventory();
        inventories.addAll(vendingMachine.getInventories());
        FileUtils.writeFile(path, inventories);
    }
}
