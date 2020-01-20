import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BootstrapTest {

    @Test
    fun `when adding product to order`() {
        val order = Order(Customer(), Address())
        val music = Product("Stairway to Heaven", ProductType.DIGITAL, 5.00)
        val book = Product("The Hitchhiker's Guide to the Galaxy", ProductType.BOOK, 120.00)
         val shirt = Product("Flowered t-shirt", ProductType.PHYSICAL, 35.00)

        order.addProduct(shirt, 1)
        order.addProduct(book, 1)
        order.addProduct(music, 1)

        assertThat(order.items.size).isEqualTo(3)
    }

    @Test
    fun `when total amount is calculated`() {
        val order = Order(Customer(), Address())
        val music1 = Product("Highway to hell", ProductType.DIGITAL, 10.00)
        val music2 = Product("Stairway to Heaven", ProductType.DIGITAL, 15.00)


        order.addProduct(music1, 1)
        order.addProduct(music2, 1)

        assertThat(order.totalAmount).isEqualTo(50)
    }
}