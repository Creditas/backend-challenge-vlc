package com.creditas.challenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChallengeTest {

    @Test
    public void testAddProduct() {
        Challenge challenge = new Challenge();
        Challenge.Customer customer = challenge.new Customer();
        Challenge.Address address = challenge.new Address();
        Challenge.Order order = challenge.new Order(customer, address);

        order.addProduct(challenge.new Product("Product 1", Challenge.ProductType.DIGITAL, 10.0), 1);
        order.addProduct(challenge.new Product("Product 2", Challenge.ProductType.BOOK, 11.0), 1);
        order.addProduct(challenge.new Product("Product 3", Challenge.ProductType.PHYSICAL, 12.0), 1);

        assertEquals(order.items().size(), 3);
    }

    @Test
    public void testTotalAmount() {
        Challenge challenge = new Challenge();
        Challenge.Customer customer = challenge.new Customer();
        Challenge.Address address = challenge.new Address();
        Challenge.Order order = challenge.new Order(customer, address);

        order.addProduct(challenge.new Product("Product 1", Challenge.ProductType.DIGITAL, 10.0), 2);
        order.addProduct(challenge.new Product("Product 2", Challenge.ProductType.DIGITAL, 15.0), 2);
        double total = order.totalAmount();

        assertEquals(total, 50.0, 0.0);
    }
}
