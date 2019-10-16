package org.fasttrackit.onlinecommerceshop.persistance;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.fasttrackit.onlinecommerceshop.domain.Review;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{


    //nested properties
    Page<Product> findByProduct(long productId, Pageable pageable);
}