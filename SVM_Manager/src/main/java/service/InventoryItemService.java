package service;

import model.Inventory;
import model.InventoryItems;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemService {
    private final String path = "./data/inventory_item.csv";
    public List<InventoryItems> findAllInventoryItem() {
        return FileUtils.readFile(path, InventoryItems.class);
    }
    public List<InventoryItems> findAllByInventoryId(long idInventory) {
        List<InventoryItems> list = findAllInventoryItem();
        List<InventoryItems> result = new ArrayList<>();
        for (InventoryItems item : list) {
            if (item.getIdInventory() == idInventory) {
                result.add(item);
            }
        }
        return result;
    }
    public void saveInventoryItemByInventory(Inventory inventory) {
        List<InventoryItems> list = findAllInventoryItem();
        list.addAll(inventory.getInventoryItems());
        FileUtils.writeFile(path,list);
    }
    public void saveInventory(List<InventoryItems> inventoryItems){
        FileUtils.writeFile(path,inventoryItems);
    }
}
