package view;

import model.ERole;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class LoginView {
    public static Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private AdminView adminView;
    private OrderView orderView;

    public LoginView() {
        userService = new UserService();
        adminView = new AdminView();
        orderView = new OrderView();
    }
    public void launcher() throws InterruptedException {

        boolean check = true;
        while (check){
            System.out.println("                               ╔══════════════════════════════════════════════════════════╗");
            System.out.println("                               ║..........................................................║");
            System.out.println("                               ║..........................................................║");
            System.out.println("                               ║........................LOGIN SYSTEM......................║");
            System.out.println("                               ║..........................................................║");
            System.out.println("                               ║..........................................................║");
            System.out.println("                               ╚══════════════════════════════════════════════════════════╝");

            System.out.println("Nhập tên tài khoản");
            String username = scanner.nextLine();
            System.out.println("Nhập mật khẩu");
            String password = scanner.nextLine();

            User user =  loginUser(username, password);
            if (user != null) {
                if (user.geteRole() == ERole.admin) {
                    System.out.print("Loading....10%");
                    Thread.sleep(500);
                    System.out.print("........30%");
                    Thread.sleep(500);
                    System.out.print("..........50%");
                    Thread.sleep(500);
                    System.out.print("...........70%");
                    Thread.sleep(500);
                    System.out.println("...............99%");
                    Thread.sleep(3000);
                    System.out.println("Success 100%");
                    Thread.sleep(2000);
                    adminView.launcher();
                } else {
                    orderView.createOrder();
                }
            } else {
                System.out.println("Tài khoản hoặc mật khẩu không đúng! Nhập lại!");
            }
        }

    }
    public User loginUser(String username, String password){
        List<User> allUsers = userService.findAllUser();
        for (User user : allUsers){
            if (username.equals(user.getUserName())){
                if (password.equals(user.getpassWord())){
                    return user;
                }
            }
        }
        return null;
    }
}
