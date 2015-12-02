package com.dietz.chris.aliihealthtest.models;

/**
 *  User model of users in the system.
 */
public class Customer extends BaseModel {

    int id = NOT_SET_INT;
    String email = NOT_SET_STRING;
    String first_name = NOT_SET_STRING;
    String last_name = NOT_SET_STRING;

    /**
     * Remote server ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Set email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set first name for the user.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Set last name for the user.
     */
    public String getLastName() {
        return last_name;
    }

}
