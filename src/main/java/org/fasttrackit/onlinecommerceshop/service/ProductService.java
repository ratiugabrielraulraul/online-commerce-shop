package org.fasttrackit.onlinecommerceshop.service;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.expection.ResourceNotFoundException;
import org.fasttrackit.onlinecommerceshop.persistance.ProductRepository;
import org.fasttrackit.onlinecommerceshop.transfer.product.GetProductRequest;
import org.fasttrackit.onlinecommerceshop.transfer.product.ProductResponse;
import org.fasttrackit.onlinecommerceshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    private final ProductRepository productRepository;

    @Autowired
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
    @Transactional
    public Page<ProductResponse> getProducts(GetProductRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products {}", request);

        Page<Product>products;

        if (request != null && request.getPartialName() != null && request.getMinimumQuantity() != null) {
            products = productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(), request.getMinimumQuantity(), pageable);
        } else if (request != null && request.getPartialName() != null) {
            products = productRepository.findByNameContaining(request.getPartialName(), pageable);
        } else {
            products =  productRepository.findAll(pageable);
        }
        List<ProductResponse>productResponses = new ArrayList<>();

        for (Product product : products.getContent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setDescription(product.getDescription());
            productResponse.setManufactureName(product.getManufactureName());
            productResponse.setImagePath(product.getImagePath());

            productResponses.add(productResponse);

        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());

    }

    }




