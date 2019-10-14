package org.fasttrackit.onlinecommerceshop;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.service.ProductService;
import org.fasttrackit.onlinecommerceshop.steps.ProductSteps;
import org.fasttrackit.onlinecommerceshop.transfer.product.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {
      productSteps.createProduct();
    }
    @Test
    public void testGetProduct_whenExistingEntity_thenReturnProduct() {
        Product createdProduct = productSteps.createProduct();
        Product retrievedProduct = productService.getProduct(createdProduct.getId());

        assertThat(retrievedProduct,notNullValue());
        assertThat(retrievedProduct.getId(),is(createdProduct.getId()));
        assertThat(retrievedProduct.getName(),is(createdProduct.getName()));


    }



    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() {
        Product createdProduct = productSteps.createProduct();
        SaveProductRequest request = new SaveProductRequest();
        request.setName(createdProduct.getName()+ "Updated");
        request.setPrice(createdProduct.getPrice()+20);
        request.setQuantity(createdProduct.getQuantity()+25);

        Product updateProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updateProduct, notNullValue());
        assertThat(updateProduct.getId(),is(createdProduct.getId()));
        assertThat(updateProduct.getName(), is(request.getName()));
        assertThat(updateProduct.getPrice(), is(request.getPrice()));
        assertThat(updateProduct.getQuantity(), is(request.getQuantity()));
        assertThat(updateProduct.getManufactureName(), is(request.getManufactureName()));
        assertThat(updateProduct.getDescription(), is(request.getDescription()));

    }




}
