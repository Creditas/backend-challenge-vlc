<?php

namespace Challenge;

use PHPUnit\Framework\TestCase;

class ChallengeTest extends TestCase {

    /**
     * test
     */
    public function testAddProduct() {
        $customer = new Customer();
        $address = new Address();
        $order = new Order($customer, $address);

        $order->addProduct(new Product("Product 1", ProductType::DIGITAL, 10.0), 1);
        $order->addProduct(new Product("Product 2", ProductType::BOOK, 11.0), 1);
        $order->addProduct(new Product("Product 3", ProductType::PHYSICAL, 12.0), 1);

        $this->assertEquals(count($order->items()), 3);
    }

    /**
     * @test
     */
    public function testTotalAmount() {
        $customer = new Customer();
        $address = new Address();
        $order = new Order($customer, $address);

        $order->addProduct(new Product("Product 1", ProductType::DIGITAL, 10.0), 2);
        $order->addProduct(new Product("Product 2", ProductType::DIGITAL, 15.0), 2);
        $total = $order->totalAmount();

        $this->assertEquals(50.0, $total);
    }
}