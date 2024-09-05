import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo() {
        products = new ArrayList<>();
        products.add(new Product("1", "Apple"));
        products.add(new Product("2", "Banana"));
    }

    public List<Product> getProducts() {
        return products;
    }

    //public Product getProductById(String id) {
    //    for (Product product : products) {
    //        if (product.id().equals(id)) {
    //            return product;
    //        }
    //    }
    //    return null;
    //}

    public Optional<Product> getProductById(String id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findFirst();
    }

    public Product addProduct(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    public void removeProduct(String id) {
        for (Product product : products) {
           if (product.id().equals(id)) {
               products.remove(product);
               return;
           }
        }
    }
}
