package service;

import model.Inventory;
import model.Product;
import model.VendingMachine;
import utils.FileUtils;

import java.util.List;

public class VmService {
    private static final String path = "D:\\Java_Modul2\\SVM_Manager\\SVM_Manager\\data\\vendingmachine.csv";
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
    public VendingMachine findVm(long idVm) {
        for (VendingMachine vm : vendingMachines) {
            if (vm.getIdVm() == idVm) {
                return vm;
            }
        }
        return null;
    }
    public void addVendingMachine(VendingMachine vendingMachine) {
        vendingMachines.add(vendingMachine);
        FileUtils.writeFile(path, vendingMachines);
    }
}
