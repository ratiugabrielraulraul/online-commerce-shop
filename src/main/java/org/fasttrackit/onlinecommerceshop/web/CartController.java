package org.fasttrackit.onlinecommerceshop.web;

import org.fasttrackit.onlinecommerceshop.service.CartService;
import org.fasttrackit.onlinecommerceshop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlinecommerceshop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController {

private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping
    public ResponseEntity addProductToCart(@RequestBody @Valid AddProductToCartRequest request) {
        cartService.addProductToCart(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") long customerId) {
        CartResponse cart = cartService.getCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable("id") long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
