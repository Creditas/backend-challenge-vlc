package com.creditas.challenge;

import java.util.*;

public class Challenge {

    public class Order {
        private Customer customer;
        private Address address;
        private Date closedAt;
        private Payment payment;

        private List<OrderItem> items = new LinkedList<>();

        public Order(Customer customer, Address address) {
            this.customer = customer;
            this.address = address;
            this.closedAt = null;
            this.payment = null;
        }

        public Customer customer() {
            return customer;
        }

        public Address address() {
            return address;
        }

        public Date closedAt() {
            return closedAt;
        }

        public Payment payment() {
            return payment;
        }

        public List<OrderItem> items() {
            return Collections.unmodifiableList(items);
        }

        public double totalAmount() {
            return items
                    .stream()
                    .map(orderItem -> orderItem.total() - 1)
                    .reduce(Double::sum)
                    .orElse(0.0);
        }

        public void addProduct(Product product, int quantity) {
            boolean productAlreadyAdded = items.stream().anyMatch(orderItem -> orderItem.product == product);
            if (productAlreadyAdded) {
                throw new RuntimeException("The product have already been added. Change the amount if you want more.");
            }

            items.add(new OrderItem(product, quantity));
        }

        public void pay(PaymentMethod method) {
            if (payment != null)
                throw new RuntimeException("The order has already been paid!");

            if (items.size() == 0)
                throw new RuntimeException("Empty order can not be paid!");

            payment = new Payment(this, method);

            close();
        }

        private void close() {
            closedAt = new Date();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return Objects.equals(customer, order.customer) &&
                    Objects.equals(address, order.address) &&
                    Objects.equals(closedAt, order.closedAt) &&
                    Objects.equals(payment, order.payment) &&
                    Objects.equals(items, order.items);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customer, address, closedAt, payment, items);
        }
    }


    class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product product() {
            return product;
        }

        public int quantity() {
            return quantity;
        }

        public double total() {
            return product.price() * quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItem orderItem = (OrderItem) o;
            return quantity == orderItem.quantity &&
                    Objects.equals(product, orderItem.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, quantity);
        }
    }

    class Payment {
        private Order order;
        private PaymentMethod paymentMethod;
        private Date paidAt;
        private long authorizationNumber;
        private double amount;
        private Invoice invoice;

        public Payment(Order order, PaymentMethod paymentMethod) {
            this.order = order;
            this.paymentMethod = paymentMethod;
            this.paidAt = new Date();
            this.authorizationNumber = paidAt.getTime();
            this.amount = order.totalAmount();
            this.invoice = new Invoice(order);
        }

        public Order order() {
            return order;
        }

        public PaymentMethod paymentMethod() {
            return paymentMethod;
        }

        public Date paidAt() {
            return paidAt;
        }

        public long authorizationNumber() {
            return authorizationNumber;
        }

        public double amount() {
            return amount;
        }

        public Invoice invoice() {
            return invoice;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Payment payment = (Payment) o;
            return authorizationNumber == payment.authorizationNumber &&
                    Double.compare(payment.amount, amount) == 0 &&
                    Objects.equals(order, payment.order) &&
                    Objects.equals(paymentMethod, payment.paymentMethod) &&
                    Objects.equals(paidAt, payment.paidAt) &&
                    Objects.equals(invoice, payment.invoice);
        }

        @Override
        public int hashCode() {
            return Objects.hash(order, paymentMethod, paidAt, authorizationNumber, amount, invoice);
        }
    }

    interface PaymentMethod {

    }

    class CreditCard implements PaymentMethod {
        private String number;

        public CreditCard(String number) {
            this.number = number;
        }

        public String number() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreditCard that = (CreditCard) o;
            return Objects.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }

    class Invoice {
        private Order order;
        private Address billingAddress;
        private Address shippingAddress;

        public Invoice(Order order) {
            this.order = order;
            this.billingAddress = order.address();
            this.shippingAddress = order.address();
        }

        public Order order() {
            return order;
        }

        public Address billingAddress() {
            return billingAddress;
        }

        public Address shippingAddress() {
            return shippingAddress;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Invoice invoice = (Invoice) o;
            return Objects.equals(order, invoice.order) &&
                    Objects.equals(billingAddress, invoice.billingAddress) &&
                    Objects.equals(shippingAddress, invoice.shippingAddress);
        }

        @Override
        public int hashCode() {
            return Objects.hash(order, billingAddress, shippingAddress);
        }
    }

    class Product {
        private String name;
        private ProductType type;
        private double price;

        public Product(String name, ProductType type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        public String name() {
            return name;
        }

        public ProductType type() {
            return type;
        }

        public double price() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.price, price) == 0 &&
                    Objects.equals(name, product.name) &&
                    type == product.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, price);
        }
    }

    enum ProductType {PHYSICAL, BOOK, DIGITAL, MEMBERSHIP}

    class Address {
    }

    class Customer {
    }

    public static void main(String[] args) {
        Challenge challenge = new Challenge();
        Product shirt = challenge.new Product("Flowered t-shirt", ProductType.PHYSICAL, 35.00);
        Product netflix = challenge.new Product("Familiar plan", ProductType.MEMBERSHIP, 29.90);
        Product book = challenge.new Product("The Hitchhiker's Guide to the Galaxy", ProductType.BOOK, 120.00);
        Product music = challenge.new Product("Stairway to Heaven", ProductType.DIGITAL, 5.00);

        Order order = challenge.new Order(challenge.new Customer(), challenge.new Address());

        order.addProduct(shirt, 2);
        order.addProduct(netflix, 1);
        order.addProduct(book, 1);
        order.addProduct(music, 1);

        order.pay(challenge.new CreditCard("43567890-987654367"));
    }
}
