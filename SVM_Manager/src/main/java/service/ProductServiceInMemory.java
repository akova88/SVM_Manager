package service;

import compare.Sort;
import model.ECategory;
import model.Product;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceInMemory {
    private List<Product> products;

    public ProductServiceInMemory() {
        products = new ArrayList<>();

        products.add(new Product(System.currentTimeMillis() % 100000, "Coca cola", "Vị nguyên bản",
                15000.0f, DateUtils.parse("08-05-2023 10:40"), ECategory.DRINK));
        products.add(new Product(System.currentTimeMillis() % 100000, "Red Bull", "Vị nguyên bản",
                17000.0f, DateUtils.parse("08-05-2023 12:40"), ECategory.DRINK));
        products.add(new Product(System.currentTimeMillis() % 100000, "Snack", "Vị khoai tây",
                5000.0f, DateUtils.parse("08-05-2023 09:40"), ECategory.FAST_FOOD));
    }
    public List<Product> findAllProduct() {
        return this.products;
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
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setCreateAt(product.getCreateAt());
                p.seteCategory(product.geteCategory());
            }
        }
    }
    public void addProduct(Product p) {
        products.add(p);
    }
    public boolean removeProduct(long idProduct) {
        Product productToRemove;
        productToRemove = findProduct(idProduct);
        if (productToRemove == null) {
            return false;
        } else {
            products.remove(productToRemove);
            return true;
        }
    }
    public void sortProduct(String instant, boolean isAscending) {
        products.sort(new Sort(instant,isAscending));
    }

}
