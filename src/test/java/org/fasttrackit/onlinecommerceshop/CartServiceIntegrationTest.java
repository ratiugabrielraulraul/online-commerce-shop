package org.fasttrackit.onlinecommerceshop;


import org.fasttrackit.onlinecommerceshop.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTest {
    @Autowired
    private CartService cartService;

    @Test
    public void testAddToCart_whenNewCart_thenCreateNewCart() {


    }


}
