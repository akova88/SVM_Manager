package view;

import model.Product;
import utils.AppUtils;

import static utils.AppUtils.scanner;

public class AdminView {
    public static InventoryView inventoryView;
    public static OrderView orderView;
    public static ProductView productView;
    public static UserView userView;
    public static VmView vmView;

    public AdminView() {
    }

    public void launcher() {

        boolean actionMenu = true;
        while (actionMenu) {
            try {
                AppUtils.menuAdminView();
                productView = new ProductView();
                vmView = new VmView();
                inventoryView = new InventoryView();
                orderView = new OrderView();
                userView = new UserView();

                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        productView.launch();
                        break;
                    case 2:
                        vmView.launch();
                        break;
                    case 3:
                        inventoryView.launcher();
                        break;
                    case 4:
                        orderView.launcher();
                        break;
                    case 5:
                        userView.launcher();
                        break;
                    case 0:
                        actionMenu = false;
                        break;
                    default:
                        System.out.println("Bạn nhập không hợp lệ, Vui lòng nhập lại");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Định dạng không đúng. Vui lòng nhập lại");
            }
        }
    }
}