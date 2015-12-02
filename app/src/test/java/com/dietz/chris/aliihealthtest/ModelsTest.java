package com.dietz.chris.aliihealthtest;

import com.dietz.chris.aliihealthtest.models.Customer;
import com.dietz.chris.aliihealthtest.models.FullTransaction;
import com.dietz.chris.aliihealthtest.models.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 *
 */
public class ModelsTest {

    private static final String CUSTOMER_JSON =
            "{\"id\":191," +
            "\"email\":\"patrick@aliihealthcare.com\"," +
            "\"created_at\":\"2015-08-10T11:44:59.339-04:00\"," +
            "\"updated_at\":\"2015-12-02T12:42:30.295-05:00\"," +
            "\"authentication_token\":\"HZPxxFtZyZp2kgpyeyH5\"," +
            "\"first_name\":\"Patrick\"," +
            "\"last_name\":\"Childers\"," +
            "\"roles_mask\":4," +
            "\"stripe_customer_id\":\"cus_6u6eJmQlaxPdg5\"," +
            "\"credit_card_hint\":\"8533\"," +
            "\"credit\":0.0," +
            "\"agreed_to_terms\":false," +
            "\"demo\":false," +
            "\"training\":false," +
            "\"uses_touch_id\":false," +
            "\"white_label\":\"bond\"}";

    private static final String TRANSACTION_JSON =
            "{\"id\":5856," +
            "\"customer_id\":191," +
            "\"start_time\":null," +
            "\"end_time\":null," +
            "\"created_at\":\"2015-12-02T12:42:30.306-05:00\"," +
            "\"updated_at\":\"2015-12-02T12:42:31.273-05:00\"," +
            "\"amount\":10000," +
            "\"rating\":null," +
            "\"service_recovery\":false," +
            "\"experience\":null," +
            "\"explanation\":null," +
            "\"stripe_authorization\":\"ch_17Decc42crRm8WDyR2rZpHtp\"," +
            "\"payment_captured\":false," +
            "\"doctor_id\":null," +
            "\"phone_number\":null," +
            "\"doctors_ringed\":1," +
            "\"call_result\":null," +
            "\"no_available_doctor\":false," +
            "\"answer_time\":null," +
            "\"call_duration\":null," +
            "\"email_share\":false," +
            "\"facebook_share\":false," +
            "\"twitter_share\":false," +
            "\"no_charge\":false," +
            "\"requested_notification\":false," +
            "\"revenue_visit\":false," +
            "\"latlong\":null," +
            "\"demo\":false," +
            "\"training\":false}";

    private static final String FULL_TRANSACTION_JSON = "{\"channel_id\":1570," +
            "\"session_id\":\"ABCD\"," +
            "\"customer\":" + CUSTOMER_JSON + "," +
            "\"transaction\":" + TRANSACTION_JSON + "," +
            "\"trans_channel_name\":\"presence-transaction-5856\"}";

    @Test
    public void customerParse() {
        final Gson gson = new GsonBuilder().create();
        final Customer customer = gson.fromJson(CUSTOMER_JSON, Customer.class);
        validateCustomer(customer);
    }

    @Test
    public void transactionParse() {
        final Gson gson = new GsonBuilder().create();
        final Transaction transaction = gson.fromJson(TRANSACTION_JSON, Transaction.class);
        validateTransaction(transaction);
    }

    @Test
    public void fullTranactionParse() {
        final Gson gson = new GsonBuilder().create();
        final FullTransaction transaction = gson.fromJson(FULL_TRANSACTION_JSON, FullTransaction.class);
        validateFullTransaction(transaction);
    }

    private static void validateFullTransaction(FullTransaction transaction) {
        assertThat(transaction, notNullValue());
        assertThat(transaction.getChannelId(), equalTo(1570));
        assertThat(transaction.getSessionId(), equalTo("ABCD"));
        validateCustomer(transaction.getCustomer());
        validateTransaction(transaction.getTransaction());
    }

    private static void validateCustomer(Customer customer) {
        assertThat(customer, notNullValue());
        assertThat(customer.getEmail(), equalTo("patrick@aliihealthcare.com"));
        assertThat(customer.getFirstName(),  equalTo("Patrick"));
        assertThat(customer.getLastName(), equalTo("Childers"));
        assertThat(customer.getId(), equalTo(191));
    }

    private static void validateTransaction(Transaction transaction) {
        assertThat(transaction, notNullValue());
        assertThat(transaction.getId(), equalTo(5856));
        assertThat(transaction.getCustomerId(), equalTo(191));
        assertThat(transaction.getDoctorId(), equalTo(0));
    }
}
