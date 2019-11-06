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
@RequestMapping("/carts/update/count")
public class CartUpdateCountController {

    private final CartService cartService;

    @Autowired
    public CartUpdateCountController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/cartId/itemId/count")
    public ResponseEntity updateCart(@PathVariable("cartId") long id,
                                     @PathVariable("itemId") long itemId,
                                     @PathVariable("count") int count) {
        cartService.updateCartCount(id, itemId, count);
        return new ResponseEntity(HttpStatus.OK);
    }
}
