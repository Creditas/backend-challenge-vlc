import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BootstrapTest {

    @Test
    fun `when adding product to order`() {
        val order = Order(Customer(), Address())
        val music = Product("Product 1", ProductType.DIGITAL, 10.00)
        val book = Product("Product 2", ProductType.BOOK, 11.00)
        val shirt = Product("Product 3", ProductType.PHYSICAL, 12.00)

        order.addProduct(shirt, 1)
        order.addProduct(book, 1)
        order.addProduct(music, 1)

        assertThat(order.items.size).isEqualTo(3)
    }

    @Test
    fun `when total amount is calculated`() {
        val order = Order(Customer(), Address())
        val music1 = Product("Product 1", ProductType.DIGITAL, 10.00)
        val music2 = Product("Product 2", ProductType.BOOK, 15.00)


        order.addProduct(music1, 2)
        order.addProduct(music2, 2)

        assertThat(order.totalAmount).isEqualTo(50)
    }
}
