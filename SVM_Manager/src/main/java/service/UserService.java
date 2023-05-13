package service;

import model.Product;
import model.User;
import utils.FileUtils;

import java.util.List;

public class UserService {
    private static final String path = "./data/product.csv";
    public static List<User> userList;
    static {
        userList = FileUtils.readFile(path,User.class);
    }
    public List<User> findAllUser() {
        return userList;
    }
}
