import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private static ProductRepo productRepo = new ProductRepo();
    private static OrderRepo orderRepo = new OrderMapRepo();

//    public Order addOrder(List<String> productIds) {
//        List<Product> products = new ArrayList<>();
//        for (String productId : productIds) {
//            Product productToOrder = productRepo.getProductById(productId);
//            if (productToOrder == null) {
//                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
//                return null;
//            }
//            products.add(productToOrder);
//        }
//
//        Order newOrder = new Order(UUID.randomUUID().toString(), products);
//
//        return orderRepo.addOrder(newOrder);
//    }

    public static Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId).orElseThrow(() ->
                    new IllegalArgumentException("Product with ID: " + productId + " does not exist!")
            );
            products.add(productToOrder);
        }

        Order newOrder = new Order(
                UUID.randomUUID().toString(),
                products,
                OrderStatus.PROCESSING,
                LocalDateTime.now()
                );

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(status))
                .collect(Collectors.toList());
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID: " + orderId + " does not exist!");
        }

        Order updateOrder = order.withStatus(newStatus);
        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(updateOrder);

        return updateOrder;
    }
}
