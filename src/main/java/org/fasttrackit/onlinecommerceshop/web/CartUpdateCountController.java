package org.fasttrackit.onlinecommerceshop.web;

import org.fasttrackit.onlinecommerceshop.service.CartService;
import org.fasttrackit.onlinecommerceshop.transfer.cart.UpdateCountRequest;
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

    @PutMapping("/{id}")
    public ResponseEntity updateCart(@PathVariable("id") long id,
                                    @RequestBody @Valid UpdateCountRequest request) {
        cartService.updateCartCount(id, request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
