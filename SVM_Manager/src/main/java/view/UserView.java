package view;

import model.ERole;
import model.User;
import service.UserService;
import utils.AppUtils;

import java.util.List;
import java.util.Scanner;

import static utils.AppUtils.scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;

    public UserView() {
        userService = new UserService();
    }
    public void launcher() {

        boolean actionMenu = true;
        while (actionMenu) {
            try {
                AppUtils.menuUserView();

                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        showListUser(userService.findAllUser());
                        break;
                    case 2:
                        createUser();
                        break;
                    case 0:
                        actionMenu = false;
                        break;
                    default:
                        System.out.println("Bạn nhập không hợp lệ, Vui lòng nhập lại");
                }
            }catch (NumberFormatException numberFormatException) {
                System.out.println("Định dạng không đúng. Vui lòng nhập lại");
            }
        }
    }

    private void createUser() {
        System.out.println("Tạo user");
        System.out.println("Nhập ID user");
        long idUser = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập tên user");
        String name = scanner.nextLine();
        System.out.println("Nhập username");
        String username = scanner.nextLine();
        String password, confirmPassword;
        do {
            System.out.println("Nhập password");
            password = scanner.nextLine();
            System.out.println("Xác nhận password");
            confirmPassword = scanner.nextLine();
            if (!password.equals(confirmPassword)) {
                System.out.println("Password không đúng, vui lòng nhập lại");
            }
        } while (!password.equals(confirmPassword));

        System.out.println("Nhập chức vụ (Nhập 1 trong các chức vụ sau)");

        for (int i = 0; i < ERole.values().length; i++) {
            System.out.println((i + 1) + ". " + ERole.values()[i]);
        }
        String inputERole = scanner.nextLine().toLowerCase();
        ERole eRole = ERole.getERoleByName(inputERole);

        User newUser = new User(idUser, name,username, password, eRole);
        userService.addUser(newUser);

        System.out.println("Tạo user thành công");
        userService.showUser(newUser);
    }
    private void showListUser(List<User> allUsers) {
        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n", "ID", "Name", "UserName", "Password", "Chức vụ");
        for (User user : allUsers) {
            System.out.printf("%-5s %-20s %-10s %-10s %-10s\n", user.getId(), user.getName(),user.getUserName(), user.getpassWord(), user.geteRole());
        }
    }
}
