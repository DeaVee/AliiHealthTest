package com.dietz.chris.aliihealthtest.models;

/**
 *  A transaction object contains information for user transactions between user and doctor.
 */
public class Transaction {

    public static int NOT_SET_INT = -1;

    int id = NOT_SET_INT;
    int customer_id = NOT_SET_INT;
    int doctor_id = NOT_SET_INT;

    /**
     * The request ID that created this object
     */
    public int getId() {
        return id;
    }

    /**
     * Server ID for the customer in the transaction
     */
    public int getCustomerId() {
        return customer_id;
    }

    /**
     * Server ID for the doctor in the transaction
     */
    public int getDoctorId() {
        return doctor_id;
    }
}
