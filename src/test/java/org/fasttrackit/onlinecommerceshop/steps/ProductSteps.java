package org.fasttrackit.onlinecommerceshop.steps;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.service.ProductService;
import org.fasttrackit.onlinecommerceshop.transfer.product.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class ProductSteps {

    @Autowired
    private ProductService productService;

    public Product createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Notebook");
        request.setDescription("Some description");
        request.setPrice(2000);
        request.setManufactureName("HP");
        request.setQuantity(50);

        Product product = productService.createProduct(request);
        assertThat(product, notNullValue());


        assertThat(product.getId(),greaterThan(0L));
        assertThat(product.getName(),is(request.getName()));
        assertThat(product.getDescription(),is(request.getDescription()));
        assertThat(product.getPrice(),is(request.getPrice()));
        assertThat(product.getQuantity(),is(request.getQuantity()));
        assertThat(product.getManufactureName(),is(request.getManufactureName()));
        return product;

    }




}
