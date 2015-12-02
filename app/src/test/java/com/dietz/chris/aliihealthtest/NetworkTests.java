package com.dietz.chris.aliihealthtest;

import com.dietz.chris.aliihealthtest.models.Customer;
import com.dietz.chris.aliihealthtest.models.FullTransaction;
import com.dietz.chris.aliihealthtest.models.Transaction;
import com.dietz.chris.aliihealthtest.network.AliiServices;
import com.dietz.chris.aliihealthtest.network.ServicesCreator;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 */
public class NetworkTests {

    @Test
    public void successCall() {
        final AliiServices services = ServicesCreator.getServices();
        Map<String, String> fieldMap = new HashMap<>(1);
        fieldMap.put("state", "GA");
        Call<FullTransaction> transaction = services.getDoctor("Token token=HZPxxFtZyZp2kgpyeyH5", fieldMap);
        Response<FullTransaction> response;
        try {
            response = transaction.execute();
        } catch (IOException e) {
            response = null;
        }
        assertThat(true, equalTo(true));
        assertThat(response, notNullValue());
        assertThat(response.isSuccess(), equalTo(true));
        validateFullTransaction(response.body());
    }

    private static void validateFullTransaction(FullTransaction transaction) {
        assertThat(transaction, notNullValue());
        assertThat(transaction.getChannelId(), not(-1)); // -1 means it was null.  It changes with each call
        assertThat(transaction.getSessionId(), notNullValue()); // It'll be different each time so just ensure it exists.
        validateCustomer(transaction.getCustomer());
        validateTransaction(transaction.getTransaction());
    }

    private static void validateCustomer(Customer customer) {
        assertThat(customer, notNullValue());
        assertThat(customer.getEmail(), Matchers.equalTo("patrick@aliihealthcare.com"));
        assertThat(customer.getFirstName(),  Matchers.equalTo("Patrick"));
        assertThat(customer.getLastName(), Matchers.equalTo("Childers"));
        assertThat(customer.getId(), Matchers.equalTo(191));
    }

    private static void validateTransaction(Transaction transaction) {
        assertThat(transaction, notNullValue());
        assertThat(transaction.getId(), not(-1)); // -1 means it was null. It changes with each call.
        assertThat(transaction.getCustomerId(), Matchers.equalTo(191));
        assertThat(transaction.getDoctorId(), Matchers.equalTo(0));
    }
}
