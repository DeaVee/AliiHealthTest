package com.dietz.chris.aliihealthtest.models;

/**
 *  FullTransaction is an object that represents a successful response from a "https://staging.bondintelligentcare.com/api/customer_transactions"
 *  call.
 */
public class FullTransaction extends BaseModel {

    int channel_id = NOT_SET_INT;
    String session_id = NOT_SET_STRING;
    Customer customer = null;
    Transaction transaction = null;

    public int getChannelId() {
        return channel_id;
    }

    public String getSessionId() {
        return session_id;
    }

    /**
     * Customer that was part of this transaction
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * The details related to the specific transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }
}
