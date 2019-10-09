package org.fasttrackit.onlinecommerceshop.persistance;

import org.fasttrackit.onlinecommerceshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {


}
