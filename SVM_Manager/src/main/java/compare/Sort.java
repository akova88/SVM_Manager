package compare;

import model.Product;
import utils.DateUtils;

import java.util.Comparator;

public class Sort implements Comparator<Product> {
    private String key;
    private boolean isAscending;
    public Sort(String key, boolean isAscending){
        this.key = key;
        this.isAscending = isAscending;
    }
    @Override
    public int compare(Product o1, Product o2) {
        switch (key){
            case "id":
                if (isAscending)
                    return Long.compare(o1.getId(), o2.getId());
                else
                    return Long.compare(o2.getId(), o1.getId());
            case "name":
                if (isAscending)
                    return o1.getName().compareToIgnoreCase(o2.getName());
                else
                    return o2.getName().compareToIgnoreCase(o1.getName());
            case "price":
                if (isAscending)
                    return Float.compare(o1.getPrice(), o2.getPrice());
                else
                    return Float.compare(o2.getPrice(), o1.getPrice());
            case "createAt":
                if (isAscending)
                    return o1.getCreateAt().compareTo(o2.getCreateAt());
                else
                    return o2.getCreateAt().compareTo(o1.getCreateAt());
            default:
                if (isAscending)
                    return o1.geteCategory().toString().compareToIgnoreCase(o2.geteCategory().toString());
                else
                    return o2.geteCategory().toString().compareToIgnoreCase(o1.geteCategory().toString());
        }
    }
}
