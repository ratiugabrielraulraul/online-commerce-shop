package org.fasttrackit.onlinecommerceshop;


import org.fasttrackit.onlinecommerceshop.domain.Customer;
import org.fasttrackit.onlinecommerceshop.service.CustomerService;
import org.fasttrackit.onlinecommerceshop.steps.CustomerSteps;
import org.fasttrackit.onlinecommerceshop.transfer.customer.SaveCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerSteps customerSteps;

    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {
     customerSteps.createCustomer();

        }

    @Test
    public void testGetCustomer_whenExistingEntity_thenReturnCustomer(){
        Customer createdCustomer = createCustomer();
        Customer retrievedCustomer = customerService.getCustomer(createdCustomer.getId());

        assertThat(retrievedCustomer,notNullValue());
        assertThat(retrievedCustomer.getId(),is(createdCustomer.getId()));


    }
    // change
    private Customer createCustomer() {
        customerSteps.createCustomer();
        return customerSteps.createCustomer();
    }


    @Test
    public void testUpdateCustomer_whenValidRequest_thenReturnUpdatedCustomer() {
        Customer createdCustomer = createCustomer();
        SaveCustomerRequest request = new SaveCustomerRequest();
        request.setFirstName(createdCustomer.getFirstName()+ "Updated");
        request.setLastName(createdCustomer.getLastName() + "Updated");

        Customer updateCustomer = customerService.updateCustomer(createdCustomer.getId(), request);

        assertThat(updateCustomer,notNullValue());
        assertThat(updateCustomer.getId(),greaterThan(0L));
        assertThat(updateCustomer.getFirstName(),is(request.getFirstName()));
        assertThat(updateCustomer.getLastName(),is(request.getLastName()));

    }

}








