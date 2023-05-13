package view;

import service.UserService;

import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;

    public UserView() {
        userService = new UserService();
    }
    public void launcher() {

    }
}
