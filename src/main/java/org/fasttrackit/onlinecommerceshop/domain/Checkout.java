package org.fasttrackit.onlinecommerceshop.domain;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Checkout {

    @Id
    @GeneratedValue
    private long id;


    @OneToOne(fetch = FetchType.LAZY)
    private Product product;


}
