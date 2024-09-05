import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order(actual.id(), List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, actual.timestamp());
        assertEquals(expected.products(), actual.products());
        assertNotNull(actual.id());
        assertEquals(OrderStatus.PROCESSING, actual.status());
        assertNotNull(actual.timestamp());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectException() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productsIds = List.of("2");

        //WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void updateOrderStatusTest() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);

        //WHEN
        Order updatedOrder = shopService.updateOrderStatus(order.id(), OrderStatus.COMPLETED);

        //THEN
        assertEquals(OrderStatus.COMPLETED, updatedOrder.status());
    }
}
