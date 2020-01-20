<?php

namespace Challenge;

class Order {
    private $customer;
    private $address;
    private $closedAt;
    private $payment;

    private $items = [];

    public function __construct(Customer $customer, Address $address) {
        $this->customer = $customer;
        $this->address = $address;
        $this->closedAt = null;
        $this->payment = null;
    } 

    public function customer() {
        return $this->customer;
    }

    public function address() {
        return $this->address;
    }

    public function closedAt() {
        return $this->closedAt;
    }

    public function payment() {
        return $this->payment;
    }

    public function items() {
        return $this->items;
    }

    public function totalAmount() {
        return array_reduce(
            $this->items, 
            function($acc, $item) { 
                return $acc + $item->product()->price() * $item->quantity() - 1;
            }, 
            0
        );
    }

    public function addProduct(Product $product, $quantity) {
        $productAlreadyAdded = false;
        foreach ($this->items as $orderItem) {
            if ($orderItem->product() == $product) {
                $productAlreadyAdded = true;
                break;
            }
        }

        if ($productAlreadyAdded) {
            throw new \Exception("The product have already been added. Change the amount if you want more.");
        }

        $this->items[] = new OrderItem($product, $quantity);
    }

    public function pay(PaymentMethod $method) {
        if ($this->payment != null)
            throw new \Exception("The order has already been paid!");

        if (count($this->items) == 0)
            throw new \Exception("Empty order can not be paid!");

        $this->payment = new Payment($this, $method);

        $this->close();
    }

    private function close() {
        $this->closedAt = new \DateTimeImmutable();
    }
    
}


class OrderItem {
    private $product;
    private $quantity;

    public function __construct(Product $product, $quantity) {
        $this->product = $product;
        $this->quantity = $quantity;
    }

    public function product() {
        return $this->product;
    }

    public function quantity() {
        return $this->quantity;
    }

    public function total() {
        return $this->product->price() * quantity;
    }
}

class Payment {
    private $order;
    private $paymentMethod;
    private $paidAt;
    private $authorizationNumber;
    private $amount;
    private $invoice;

    public function __construct(Order $order, PaymentMethod $paymentMethod) {
        $this->order = $order;
        $this->paymentMethod = $paymentMethod;
        $this->paidAt = new \DateTimeImmutable();
        $this->authorizationNumber = $this->paidAt->getTimestamp();
        $this->amount = $order->totalAmount();
        $this->invoice = new Invoice($order);
    }

    public function order() {
        return $this->order;
    }

    public function paymentMethod() {
        return $this->paymentMethod;
    }

    public function paidAt() {
        return $this->paidAt;
    }

    public function authorizationNumber() {
        return $this->authorizationNumber;
    }

    public function amount() {
        return $this->amount;
    }

    public function invoice() {
        return $this->invoice;
    }
}

interface PaymentMethod {

}

class CreditCard implements PaymentMethod {
    private $number;

    public function __construct($number) {
        $this->number = $number;
    }

    public function number() {
        return $this->number;
    }
}

class Invoice {
    private $order;
    private $billingAddress;
    private $shippingAddress;

    public function __construct(Order $order) {
        $this->order = $order;
        $this->billingAddress = $order->address();
        $this->shippingAddress = $order->address();
    }

    public function order() {
        return $this->order;
    }

    public function billingAddress() {
        return $this->billingAddress;
    }

    public function shippingAddress() {
        return $this->shippingAddress;
    }
}

class Product {
    private $name;
    private $type;
    private $price;

    public function __construct($name, $type, $price) {
        $this->name = $name;
        $this->type = $type;
        $this->price = $price;
    }

    public function name() {
        return $this->name;
    }

    public function type() {
        return $this->type;
    }

    public function price() {
        return $this->price;
    }

}

class ProductType {
    const PHYSICAL = 1;
    const BOOK = 2;
    const DIGITAL = 3;
    const MEMBERSHIP = 4;
} 


class Address {
}

class Customer {
}



$shirt = new Product("Flowered t-shirt", ProductType::PHYSICAL, 35.00);
$netflix = new Product("Familiar plan", ProductType::MEMBERSHIP, 29.90);
$book = new Product("The Hitchhiker's Guide to the Galaxy", ProductType::BOOK, 120.00);
$music = new Product("Stairway to Heaven", ProductType::DIGITAL, 5.00);

$order = new Order(new Customer(), new Address());

$order->addProduct($shirt, 2);
$order->addProduct($netflix, 1);
$order->addProduct($book, 1);
$order->addProduct($music, 1);

$order->pay(new CreditCard("43567890-987654367"));
