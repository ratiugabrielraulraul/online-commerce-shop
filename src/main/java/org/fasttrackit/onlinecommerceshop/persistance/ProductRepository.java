package org.fasttrackit.onlinecommerceshop.persistance;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String partialName,Pageable pageable);



    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String partialName, Integer minimumQuantity, Pageable pageable);
}
