package org.fasttrackit.onlinecommerceshop.service;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.persistance.ProductRepository;
import org.fasttrackit.onlinecommerceshop.transfer.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service

public class ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(SaveProductRequest request) {

        LOGGER.info("Creating product: {}",request);

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setImagePath(request.getImagePath());
        product.setManufactureName(request.getManufactureName());

        return productRepository.save(product);




    }

    public Product getProduct(long id) {
        LOGGER.info("Retrieving product {}", id);
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product" + id + "not found."));
    }



}
