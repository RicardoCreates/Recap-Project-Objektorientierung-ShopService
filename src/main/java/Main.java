import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo(); // You could also use OrderMapRepo

        // Initialize ShopService with repositories
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // Add an order with a valid product ID
        try {
            List<String> productIds = List.of("1"); // Assuming "1" is a valid product ID in the repo
            Order order = shopService.addOrder(productIds);
            System.out.println("Order created: " + order);

            // Update order status
            Order updatedOrder = shopService.updateOrderStatus(order.id(), OrderStatus.COMPLETED);
            System.out.println("Order status updated: " + updatedOrder);

            // Fetch and print all orders
            List<Order> orders = shopService.getOrdersByStatus(OrderStatus.COMPLETED);
            System.out.println("Completed Orders: " + orders);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to add an order with an invalid product ID
        try {
            List<String> invalidProductIds = List.of("2"); // Assuming "2" is not a valid product ID
            shopService.addOrder(invalidProductIds);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create order: " + e.getMessage());
        }
    }
}
