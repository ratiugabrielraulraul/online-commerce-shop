package org.fasttrackit.onlinecommerceshop.service;

import org.fasttrackit.onlinecommerceshop.domain.Cart;
import org.fasttrackit.onlinecommerceshop.domain.Customer;
import org.fasttrackit.onlinecommerceshop.persistance.CartRepository;
import org.fasttrackit.onlinecommerceshop.transfer.cart.AddProductToCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;

    //IoC Inversion of control
    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
    }
    // all action from the method above constitute a transaction
    @Transactional
    public void addProductToCart(AddProductToCartRequest request) {
        LOGGER.info("Adding product to cart {}", request);

        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());
        //if cart founded findById if not made a new Cart
        if (cart.getCustomer() == null) {
            LOGGER.debug("Cart doesn't exist.Retrieving customer to create a new cart.");
            Customer customer = customerService.getCustomer(request.getCustomerId());
            cart.setCustomer(customer);

        }

        cartRepository.save(cart);

    }
}
