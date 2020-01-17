require_relative 'bootstrap'

describe 'wadus' do
  it 'adds products to an order' do
    order = Order.new(Customer.new)

    order.add_product(Product.new(name: 'Product 1', type: 'digital', price: 10), 1)
    order.add_product(Product.new(name: 'Product 2', type: 'book', price: 11), 1)
    order.add_product(Product.new(name: 'Product 3', type: 'physical', price: 12), 1)

    expect(order.items.count).to eq(3)
  end

  it 'calculates the total amount of an order' do
    order = Order.new(Customer.new)

    order.add_product(Product.new(name: 'Product 1', type: 'digital', price: 10), 2)
    order.add_product(Product.new(name: 'Product 2', type: 'book', price: 15), 2)

    expect(order.total_amount).to eq(50)
  end
end
