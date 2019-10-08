package org.fasttrackit.onlinecommerceshop.service;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.expection.ResourceNotFoundException;
import org.fasttrackit.onlinecommerceshop.persistance.ProductRepository;
import org.fasttrackit.onlinecommerceshop.transfer.product.GetProductRequest;
import org.fasttrackit.onlinecommerceshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product" + id + "not found."));
    }

    public Product updateProduct(long id,SaveProductRequest request) {
        LOGGER.info("Updating product {}",id,request);
        Product product=getProduct(id);
        BeanUtils.copyProperties(request,product);
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}",id);
         productRepository.deleteById(id);
    }

    public Page<Product> getProducts(GetProductRequest request,Pageable pageable) {
        LOGGER.info("Retrieving products {}", request);
        if (request != null && request.getPartialName() != null && request.getMinimumQuantity() != null) {
            return productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(), request.getMinimumQuantity(), pageable);
        } else if (request != null && request.getPartialName() != null) {
            return productRepository.findByNameContaining(request.getPartialName(), pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    }




