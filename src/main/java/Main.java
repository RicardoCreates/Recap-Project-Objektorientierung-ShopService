import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Repositories erstellen
        ProductRepo productRepo = new ProductRepo();
        ShopService shopService = new ShopService();

        // Neue Produkte hinzufügen
        Product apple = new Product("1", "Apple");
        Product banana = new Product("2", "Banana");
        productRepo.addProduct(apple);
        productRepo.addProduct(banana);

        // Produkte anzeigen
        System.out.println("Available Products:");
        productRepo.getProducts().forEach(product ->
                System.out.println("ID: " + product.id() + ", Name: " + product.name())
        );

        // Eine Bestellung aufgeben
        List<String> productIds = Arrays.asList("1", "2");
        Order order1 = ShopService.addOrder(productIds);

        if (order1 != null) {
            System.out.println("\nOrder created:");
            System.out.println("Order ID: " + order1.id());
            System.out.println("Order Status: " + order1.status());
            System.out.println("Order Date: " + order1.timestamp());
        }

        // Bestellung nach Status filtern
        System.out.println("\nOrders with status PROCESSING:");
        List<Order> processingOrders = shopService.getOrdersByStatus(OrderStatus.PROCESSING);
        processingOrders.forEach(order ->
                System.out.println("Order ID: " + order.id() + ", Status: " + order.status())
        );

        // Bestellung updaten
        Order updatedOrder = shopService.updateOrder(order1.id(), OrderStatus.IN_DELIVERY);
        System.out.println("\nOrder updated:");
        System.out.println("Order ID: " + updatedOrder.id());
        System.out.println("Order Status: " + updatedOrder.status());
        System.out.println("Order Date: " + updatedOrder.timestamp());

        // Test für nicht existierendes Produkt
        try {
            shopService.addOrder(Arrays.asList("999"));
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
