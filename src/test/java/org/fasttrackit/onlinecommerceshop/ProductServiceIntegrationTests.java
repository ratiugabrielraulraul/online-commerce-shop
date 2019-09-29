package org.fasttrackit.onlinecommerceshop;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.service.ProductService;
import org.fasttrackit.onlinecommerceshop.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Notebook");
        request.setDescription("Some description");
        request.setPrice(2000);
        request.setManufactureName("HP");
        request.setQuantity(50);

        Product product = productService.createProduct(request);
        //Assuring that product is not null by apllying next method

        assertThat(product, notNullValue());


        assertThat(product.getId(),greaterThan(0L));
        assertThat(product.getName(),is(request.getName()));
        assertThat(product.getDescription(),is(request.getDescription()));
        assertThat(product.getPrice(),is(request.getPrice()));
        assertThat(product.getQuantity(),is(request.getQuantity()));
        assertThat(product.getManufactureName(),is(request.getManufactureName()));

    }


}
