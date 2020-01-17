from unittest import TestCase

from bootstrap import Order, Product, ProductType, Customer


class TestOrder(TestCase):
    def test_add_product(self):
        order = Order(Customer())
        order.add_product(Product(name='Product 1', type=ProductType.DIGITAL, price=10.0), 1)
        order.add_product(Product(name='Product 2', type=ProductType.BOOK, price=11.0), 1)
        order.add_product(Product(name='Product 3', type=ProductType.PHYSICAL, price=12.0), 1)

        self.assertEqual(len(order.items), 3)

    def test_total_amount(self):
        order = Order(Customer())
        order.add_product(Product(name='Product 1', type=ProductType.DIGITAL, price=10), quantity=2)
        order.add_product(Product(name='Product 2', type=ProductType.DIGITAL, price=15), quantity=2)

        total = order.total_amount()

        self.assertEqual(total, 50)
