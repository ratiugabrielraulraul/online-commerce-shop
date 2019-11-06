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
@RequestMapping("/carts/remove")
public class CartRemoverController {

    private final CartService cartService;

    @Autowired
    public CartRemoverController(CartService cartService) {
        this.cartService = cartService;
    }

    @DeleteMapping("/{id}/{itemId}")
       public ResponseEntity removeProductFromCart(@PathVariable("id") long id, @PathVariable("itemId") long itemId) {
        cartService.deleteProductFromCart(id, itemId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
