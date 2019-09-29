package org.fasttrackit.onlinecommerceshop.persistance;

import org.fasttrackit.onlinecommerceshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
