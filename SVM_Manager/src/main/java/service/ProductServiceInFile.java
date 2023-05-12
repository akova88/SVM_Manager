package service;

import compare.Sort;
import model.ECategory;
import model.Product;
import utils.DateUtils;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceInFile {
    private static final String path = "./data/product.csv";

    public static List<Product> products;
    static {
        products = FileUtils.readFile(path,Product.class);
    }
    public List<Product> findAllProducts() {
//        List<Product> products = new ArrayList<>();
//        try {
//            FileReader fileReader = new FileReader("./data/product.csv");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] items = line.split(",");
//                long idProduct = Long.parseLong(items[0]);
//                float priceProduct = Float.parseFloat(items[3]);
//                Date createAt = DateUtils.parse(items[4]);
//                ECategory eCategory = ECategory.getEcategoryByName(items[5]);
//                Product p = new Product(idProduct,items[1],items[2],priceProduct,createAt,eCategory);
//                products.add(p);
//            }
//            fileReader.close();
//            bufferedReader.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        return products
        return products;
    }
    public void addProduct(Product p) {
        products.add(p);
//        try {
//            FileWriter fileWriter = new FileWriter("./data/product.csv");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//            for (Product item : products) {
//                bufferedWriter.write(item.toString()+"\n");
//            }
//            bufferedWriter.close();
//            fileWriter.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
        FileUtils.writeFile(path, products);
    }
    public Product findProduct(long idProduct) {

        for (Product p : products) {
            if (p.getId() == idProduct) {
                return p;
            }
        }
        return null;
    }

    public List<Product> findProductByName(String nameProduct) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(nameProduct))
                list.add(p);
        }
        return list;
    }

    public List<Product> findProductByDesc(String description) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getDescription().equalsIgnoreCase(description))
                list.add(p);
        }
        return list;
    }
    public List<Product> findProductByECategory(String eCategory) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.geteCategory().toString().equalsIgnoreCase(eCategory))
                list.add(p);
        }
        return list;
    }
    public List<Product> findProductByDateCreate(String dateCreate) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (DateUtils.format(p.getCreateAt()).contains(dateCreate))
                list.add(p);
        }
        return list;
    }
    public List<Product> findProductByPrice(float price) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() == price)
                list.add(p);
        }
        return list;
    }
    public void editProduct(long idProduct, Product product) {
        for (Product p : products) {
            if (p.getId() == idProduct) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setCreateAt(product.getCreateAt());
                p.seteCategory(product.geteCategory());
            }
        }
        FileUtils.writeFile(path,products);
    }
    public boolean removeProduct(long idProduct) {
        Product productToRemove;
        productToRemove = findProduct(idProduct);
        if (productToRemove == null) {
            return false;
        } else {
            products.remove(productToRemove);
            FileUtils.writeFile(path,products);
            return true;
        }
    }
    public void sortProduct(String instant, boolean isAscending) {
        products.sort(new Sort(instant,isAscending));
    }
}
