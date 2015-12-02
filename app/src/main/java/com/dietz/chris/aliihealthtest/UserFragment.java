package com.dietz.chris.aliihealthtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dietz.chris.aliihealthtest.models.Customer;
import com.dietz.chris.aliihealthtest.models.FullTransaction;
import com.dietz.chris.aliihealthtest.models.Transaction;
import com.dietz.chris.aliihealthtest.network.AliiServices;
import com.dietz.chris.aliihealthtest.network.ServicesCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *  Makes an API request to retrieve the user's information
 */
public class UserFragment extends Fragment {

    public static UserFragment getInstance() {
        final UserFragment newFrag = new UserFragment();
        newFrag.setArguments(new Bundle());
        return newFrag;
    }

    TextView mMessage;
    View mUserLayout;
    TextView mUserName;
    TextView mUserEmail;
    TextView mDoctorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_user, container, false);
        mMessage = (TextView) root.findViewById(R.id.txtMsg);
        mUserLayout = root.findViewById(R.id.layoutUser);
        mUserName = (TextView) mUserLayout.findViewById(R.id.txtName);
        mUserEmail = (TextView) mUserLayout.findViewById(R.id.txtEmail);
        mDoctorId = (TextView) mUserLayout.findViewById(R.id.txtDoctor);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Gson gson = new GsonBuilder().create();
//        FullTransaction fullTransaction = gson.fromJson("{\"channel_id\":1570,\"session_id\":\"1_MX40NDc4MzkxMn5-MTQ0OTA3ODE1MTE3N35NMHdhc3FsNnB1Z0R6R2hxL2FXZWtiK0R-fg\",\"trans_token\":\"T1==cGFydG5lcl9pZD00NDc4MzkxMiZzaWc9M2NjMTg3YmNlNTY1OTkxODA1YzUwYWU0ZDM0YmUzNzYxMzFhYWU3Yjpyb2xlPXB1Ymxpc2hlciZzZXNzaW9uX2lkPTFfTVg0ME5EYzRNemt4TW41LU1UUTBPVEEzT0RFMU1URTNOMzVOTUhkaGMzRnNObkIxWjBSNlIyaHhMMkZYWld0aUswUi1mZyZjcmVhdGVfdGltZT0xNDQ5MDc4MTUxJm5vbmNlPTAuNDY4MTkxNjY4Mjg3NTA3OA==\",\"customer\":{\"id\":191,\"email\":\"patrick@aliihealthcare.com\",\"created_at\":\"2015-08-10T11:44:59.339-04:00\",\"updated_at\":\"2015-12-02T12:42:30.295-05:00\",\"authentication_token\":\"HZPxxFtZyZp2kgpyeyH5\",\"first_name\":\"Patrick\",\"last_name\":\"Childers\",\"roles_mask\":4,\"stripe_customer_id\":\"cus_6u6eJmQlaxPdg5\",\"credit_card_hint\":\"8533\",\"credit\":0.0,\"agreed_to_terms\":false,\"demo\":false,\"training\":false,\"uses_touch_id\":false,\"white_label\":\"bond\"},\"transaction\":{\"id\":5856,\"customer_id\":191,\"start_time\":null,\"end_time\":null,\"created_at\":\"2015-12-02T12:42:30.306-05:00\",\"updated_at\":\"2015-12-02T12:42:31.273-05:00\",\"amount\":10000,\"rating\":null,\"service_recovery\":false,\"experience\":null,\"explanation\":null,\"stripe_authorization\":\"ch_17Decc42crRm8WDyR2rZpHtp\",\"payment_captured\":false,\"doctor_id\":null,\"phone_number\":null,\"doctors_ringed\":1,\"call_result\":null,\"no_available_doctor\":false,\"answer_time\":null,\"call_duration\":null,\"email_share\":false,\"facebook_share\":false,\"twitter_share\":false,\"no_charge\":false,\"requested_notification\":false,\"revenue_visit\":false,\"latlong\":null,\"demo\":false,\"training\":false},\"trans_channel_name\":\"presence-transaction-5856\"}", FullTransaction.class);
//        onSuccess(fullTransaction);
        AliiServices services = ServicesCreator.getServices();Map<String, String> fieldMap = new HashMap<>(1);
        fieldMap.put("state", "GA");
        Call<FullTransaction> transaction = services.getDoctor("Token token=HZPxxFtZyZp2kgpyeyH5", fieldMap);
        transaction.enqueue(new Callback<FullTransaction>() {
            @Override
            public void onResponse(Response<FullTransaction> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    onSuccess(response.body());
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                onError();
            }
        });
    }

    private void onError() {
        mUserLayout.setVisibility(View.INVISIBLE);
        mMessage.setVisibility(View.VISIBLE);
        mMessage.setText(R.string.error_response);
    }

    private void onSuccess(FullTransaction transaction) {
        mUserLayout.setVisibility(View.VISIBLE);
        mMessage.setVisibility(View.INVISIBLE);
        final Customer customer = transaction.getCustomer();
        final Transaction customerTransaction = transaction.getTransaction();
        mUserEmail.setText(customer.getEmail());
        mUserName.setText(getString(R.string.formatted_user_name, customer.getFirstName(), customer.getLastName()));
        if (customerTransaction.getDoctorId() != Transaction.NOT_SET_INT) {
            mDoctorId.setText(String.valueOf(customerTransaction.getDoctorId()));
        } else {
            mDoctorId.setText(R.string.empty_doctor);
        }
    }
}
