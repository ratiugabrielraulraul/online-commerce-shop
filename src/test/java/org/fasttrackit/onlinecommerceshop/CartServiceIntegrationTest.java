package org.fasttrackit.onlinecommerceshop;


import org.fasttrackit.onlinecommerceshop.domain.Cart;
import org.fasttrackit.onlinecommerceshop.domain.Customer;
import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.service.CartService;
import org.fasttrackit.onlinecommerceshop.steps.CustomerSteps;
import org.fasttrackit.onlinecommerceshop.steps.ProductSteps;
import org.fasttrackit.onlinecommerceshop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlinecommerceshop.transfer.cart.CartResponse;
import org.fasttrackit.onlinecommerceshop.transfer.product.ProductInCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerSteps customerSteps;
    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testAddToCart_whenNewCart_thenCreateNewCart() {
        Customer customer = customerSteps.createCustomer();

        Product product = productSteps.createProduct();

        AddProductToCartRequest request= new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);

        CartResponse cart = cartService.getCart(customer.getId());

        assertThat(cart, notNullValue());
        assertThat(cart.getId(),is(customer.getId()));
        assertThat(cart.getProducts(),notNullValue());
        assertThat(cart.getProducts(), hasSize(1));

        ProductInCartResponse productFromCart = cart.getProducts().iterator().next();
        assertThat(productFromCart, notNullValue());
        assertThat(productFromCart.getId(),is(request.getProductId()));
        assertThat(productFromCart.getName(), is(product.getName()));
        assertThat(productFromCart.getPrice(), is(product.getPrice()));
}




}
