package ii_collections

fun example4() {
    val max = listOf(1, 42, 4).max()
    val longestString = listOf("a", "b").maxBy { it.length }
}

fun Shop.getCustomerWithMaximumNumberOfOrders() = customers.maxBy { it.orders.size }

fun Customer.getMostExpensiveOrderedProduct() = orderedProducts.maxBy { it.price }
