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

    /**
     * Return basic instance of this Fragment with no arguments.
     */
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
