package org.fasttrackit.onlinecommerceshop;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.service.ProductService;
import org.fasttrackit.onlinecommerceshop.transfer.product.GetProductRequest;
import org.fasttrackit.onlinecommerceshop.transfer.product.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
      createProduct();
    }
    @Test
    public void testGetProduct_whenExistingEntity_thenReturnProduct() {
        Product createdProduct = createProduct();
        Product retrievedProduct = productService.getProduct(createdProduct.getId());

        assertThat(retrievedProduct,notNullValue());
        assertThat(retrievedProduct.getId(),is(createdProduct.getId()));
        assertThat(retrievedProduct.getName(),is(createdProduct.getName()));


    }


    private Product createProduct() {
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
    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() {
        Product createdProduct = createProduct();
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
