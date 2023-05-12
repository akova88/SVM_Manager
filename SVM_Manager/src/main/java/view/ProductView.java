package view;

import compare.Sort;
import model.ECategory;
import model.Product;
import service.ProductServiceInFile;
import service.ProductServiceInMemory;
import utils.DateUtils;

import javax.swing.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private Scanner scanner = new Scanner(System.in);
//    private ProductServiceInMemory productService;
    private ProductServiceInFile productService;
    public ProductView() {
//        productService = new ProductServiceInMemory();
        productService = new ProductServiceInFile();

    }
    public void launch() {
        do {
            System.out.println("Menu chương trình: ");
            System.out.println("Nhập 1: Xem danh sách sản phẩm");
            System.out.println("Nhập 2: Thêm sản phẩm");
            System.out.println("Nhập 3: Xoá sản phẩm");
            System.out.println("Nhập 4: Sửa sản phẩm");
            System.out.println("Nhập 5: Tìm kiếm sản phẩm");
            System.out.println("Nhập 6: Sắp xếp sản phẩm");
            System.out.println("Nhập 9: Hiển thị sản phẩm có phân trang");

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showProducts(productService.findAllProducts());
                    break;
                case 2:
                    showCreateProduct();
                    showProducts(productService.findAllProducts());
                    System.out.println("Thêm sản phẩm thành công!");
                    break;
                case 3:
                    showProducts(productService.findAllProducts());
                    Product productToDel = inputIdProduct();
                    if (productService.removeProduct(productToDel.getId())) {
                        System.out.println("Xoá thành công!");
                    } else {
                        System.out.println("Không tìm thấy sp để xoá");
                    }
                    showProducts(productService.findAllProducts());
                    break;
                case 4:
                    showProducts(productService.findAllProducts());
                    editProduct();
                    break;
                case 5:
                    System.out.println("Tìm kiếm sản phẩm");
                    fillProduct();
                    break;
                case 6:
                    System.out.println("Sắp xếp sản phẩm");
                    sortProduct();
                    break;
                case 9:
                    break;
            }
        }while (true);
    }

    private void sortProduct() {
        boolean checkActionSort = false;
        do {
            System.out.println("Bạn muốn sort theo thông tin gì: ");
            System.out.println("Nhập 1: Sort theo ID ");
            System.out.println("Nhập 2: Sort theo tên ");
            System.out.println("Nhập 3: Sort theo giá ");
            System.out.println("Nhập 4: Sort theo ngày tạo ");
            System.out.println("Nhập 5: Sort theo Category ");
            System.out.println("Nhập 0: Quay lại");
            int actionSort = Integer.parseInt(scanner.nextLine());
            switch (actionSort) {
                case 1:
                    sortProductBy("id");
                    break;
                case 2:
                    sortProductBy("name");
                    break;
                case 3:
                    sortProductBy("price");
                    break;
                case 4:
                    sortProductBy("createAt");
                    break;
                case 5:
                    sortProductBy("eCategory");
                    break;
                case 0:
                    checkActionSort = false;
                    break;
                default:
                    System.out.println("Nhâp sai. Vui lòng nhập lại");
                    checkActionSort = true;
            }
        } while (checkActionSort);
    }

    private void sortProductBy(String type) {
        boolean check = false;
        do {
            System.out.println("Bạn muốn sort"+ type + "theo thứ tự tăng hay giảm dần");
            System.out.println("Nhập T: Tăng dần");
            System.out.println("Nhập G: giảm dần");
            System.out.println("Nhập B: Quay lại");
            String choose = scanner.nextLine().toUpperCase();
            switch (choose) {
                case "T":
                    productService.sortProduct(type, true);
                    showProducts(productService.findAllProducts());
                    break;
                case "G":
                    productService.sortProduct(type,false);
                    showProducts(productService.findAllProducts());
                    break;
                case "B":
                    check = false;
                    break;
                default:
                    System.out.println("Nhâp sai. Vui lòng nhập lại");
                    check = true;
            }
        }while (check);
    }



    private void fillProduct() {
        boolean checkActionFind = false;
        do {
            System.out.println("Bạn muốn tìm theo thông tin gì: ");
            System.out.println("Nhập 1: Tìm theo ID ");
            System.out.println("Nhập 2: Tìm theo tên ");
            System.out.println("Nhập 3: Tìm theo mô tả");
            System.out.println("Nhập 4: Tìm theo giá ");
            System.out.println("Nhập 5: Tìm theo ngày tạo ");
            System.out.println("Nhập 6: Tìm theo Category ");
            System.out.println("Nhập 0: Quay lại");
            int actionFind = Integer.parseInt(scanner.nextLine());
            switch (actionFind) {
                case 1:
                    System.out.println(inputIdProduct());
                    break;
                case 2:
                    System.out.println("Nhập tên sản phẩm cần tìm");
                    String name = scanner.nextLine();
                    showProducts(productService.findProductByName(name));
                    break;
                case 3:
                    System.out.println("Nhập mô tả sản phẩm cần tìm: ");
                    String desc = scanner.nextLine();
                    showProducts(productService.findProductByDesc(desc));
                    break;
                case 4:
                    System.out.println("Nhập giá sản phẩm cần tìm");
                    float price = Float.parseFloat(scanner.nextLine());
                    showProducts(productService.findProductByPrice(price));
                    break;
                case 5:
                    System.out.println("Nhập ngày tạo sản phẩm để tìm");
                    String date = scanner.nextLine();
                    showProducts(productService.findProductByDateCreate(date));
                    break;
                case 6:
                    System.out.println("Nhập tên danh mục sản phẩm cần tìm");
                    String eCategory = scanner.nextLine();
                    showProducts(productService.findProductByECategory(eCategory));
                    break;
                case 0:
                    checkActionFind = false;
                    break;
                default:
                    System.out.println("Nhâp sai. Vui lòng nhập lại");
                    checkActionFind = true;
            }
        } while (checkActionFind);
    }

    private void editProduct() {
        Product productToEit = inputIdProduct();
        if (productToEit != null) {
            boolean checkActionEdit = false;
            do{
                System.out.println("Bạn muốn sửa thông tin gì");
                System.out.println("Nhập 1: Sửa tên:");
                System.out.println("Nhập 2: Sửa mô tả:");
                System.out.println("Nhập 3: Sửa giá:");
                System.out.println("Nhập 0: Quay lại");
                int actionEdit = Integer.parseInt(scanner.nextLine());
                switch (actionEdit) {
                    case 1:
                        inputNameProduct(productToEit);
                        break;
                    case 2:
                        inputDescProduct(productToEit);
                        break;
                    case 3:
                        inputPriceProduct(productToEit);
                        break;
                    case 0:
                        checkActionEdit = false;
                        break;
                    default:
                        System.out.println("Nhâp sai. Vui lòng nhập lại");
                        checkActionEdit = true;
                }
            } while (checkActionEdit);
        }

    }
//
    private void inputPriceProduct(Product productToEit) {
        System.out.println("Thông tin sản phẩm hiện tại");
        System.out.println(productToEit);

        System.out.println("Nhập giá mới:");
        float priceEdit = Float.parseFloat(scanner.nextLine());

        productToEit.setPrice(priceEdit);
        productService.editProduct(productToEit.getId(), productToEit);

        System.out.println("Sửa thành công!");
        System.out.println(productToEit);
    }
//
    private void inputDescProduct(Product productToEit) {
        System.out.println("Thông tin sản phẩm hiện tại");
        System.out.println(productToEit);

        System.out.println("Nhập mô tả mới:");
        String descEdit = scanner.nextLine();

        productToEit.setDescription(descEdit);
        productService.editProduct(productToEit.getId(), productToEit);

        System.out.println("Sửa thành công!");
        System.out.println(productToEit);
    }
//
    private void inputNameProduct(Product productToEdit) {
        System.out.println("Thông tin sản phẩm hiện tại");
        System.out.println(productToEdit);

        System.out.println("Nhập tên mới:");
        String nameEdit = scanner.nextLine();

        productToEdit.setName(nameEdit);
        productService.editProduct(productToEdit.getId(), productToEdit);

        System.out.println("Sửa thành công!");
        System.out.println(productToEdit);
    }
//
    private Product inputIdProduct() {
        Product product = null;
        boolean checkEditProductValid = false;
        do{
            try {
                System.out.println("Nhập id sản phẩm:");
                long idProduct = Long.parseLong(scanner.nextLine());
                product = productService.findProduct(idProduct);
                if (product == null) {
                    System.out.println("ID sản phẩm không hợp lệ");
                    System.out.println("Chọn 1: Nhập lại");
                    System.out.println("Chọn 2: Quay lại");
                    int actionEditId = Integer.parseInt(scanner.nextLine());
                    switch (actionEditId) {
                        case 1:
                            checkEditProductValid = true;
                            break;
                        case 2:
                            checkEditProductValid = false;
                            break;
                    }
                }
            } catch (NumberFormatException n) {
                System.out.println("ID sản phẩm không hợp lệ");
                checkEditProductValid = true;
            }
        } while (checkEditProductValid);
        System.out.println("Sản phẩm đươợc tìm thấy");
        return product;
    }

    private void showProducts(List<Product> allProducts) {
        System.out.printf("%-10s %-15s %-30s %-10s %-20s %20s\n", "ID", "Name", "Description", "Price", "Create at", "Category");
        for (Product p : allProducts) {
            System.out.printf("%-10s %-15s %-30s %-10s %-20s %20s\n", p.getId(), p.getName(), p.getDescription(),
                    p.getPrice(), DateUtils.format(p.getCreateAt()), p.geteCategory());
        }
    }
    private void showCreateProduct() {
        System.out.println("Thêm sản phẩm");
        System.out.println("Nhập tên sản phẩm:");
        String nameNew = scanner.nextLine();
        System.out.println("Nhập mô tả:");
        String descNew = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm:");
        float priceNew = Float.parseFloat(scanner.nextLine());
        System.out.println("Chọn danh mục:");
        for (ECategory eCategory : ECategory.values()) {
            System.out.printf("Chọn %-5s %-10s", eCategory.getId(),eCategory.getName());
        }
        int idCategory = Integer.parseInt(scanner.nextLine());
        ECategory eCategoryNew = ECategory.getEcategoryById(idCategory);

        Product productNew = new Product(System.currentTimeMillis() / 10000, nameNew, descNew, priceNew, new Date(), eCategoryNew);
        productService.addProduct(productNew);
    }

}
