package service;

import model.Product;
import model.User;
import utils.FileUtils;

import java.util.List;

public class UserService {
    private static final String path = "./data/user.csv";
    public List<User> findAllUser() {
        return FileUtils.readFile(path,User.class);
    }
    public void addUser(User user) {
        List<User> users = findAllUser();
        users.add(user);
        FileUtils.writeFile(path,users);
    }
    public void showUser(User user){
        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n", "ID", "Name","UserName", "Password", "Chức vụ");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n", user.getId(), user.getName(),user.getpassWord(), user.getpassWord(), user.geteRole());
    }
}
