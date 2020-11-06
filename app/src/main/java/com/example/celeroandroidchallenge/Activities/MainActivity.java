package com.example.celeroandroidchallenge.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;


import com.example.celeroandroidchallenge.CustomerRoomDatabase;
import com.example.celeroandroidchallenge.DAO.CustomerAPI;
import com.example.celeroandroidchallenge.DAO.CustomerDao;
import com.example.celeroandroidchallenge.Models.Customer;
import com.example.celeroandroidchallenge.Models.CustomerEntity;
import com.example.celeroandroidchallenge.R;
import com.example.celeroandroidchallenge.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
{
    private static String customerBaseURL = "https://hulet.tech";
    public static Retrofit retrofit;
    private List<Customer> customerList;
    private LinearLayoutManager layoutManager;
    public CardView customerCardView;

    private CustomerDao customerDao;

    private ImageButton cardViewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerCardView = findViewById(R.id.map_cv_truck_desc);
        cardViewCancel = findViewById(R.id.cardView_ib_cancel);
        customerCardView.setVisibility(View.INVISIBLE);

        getCustomerList();


        cardViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDown(customerCardView);
                customerCardView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getCustomerList()
    {
        CustomerRoomDatabase db = CustomerRoomDatabase.getDatabase(getApplication());
        customerDao = db.customerDao();


        Retrofit retrofit = null;

        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(customerBaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        CustomerAPI customerAPI = retrofit.create(CustomerAPI.class);
        Call<List<Customer>> call = customerAPI.getAllNames();

        call.enqueue(new Callback<List<Customer>>()
        {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response)
            {
                //Log.i("onResponse", response.message());
                Log.i("autolog", "onResponse");

                Log.d("onResponse", response.toString());

                customerList = response.body();

                if(customerList != null)
                    insertIntoDb(customerList);
                //Sorting the objects in the correct visit order
                Collections.sort(customerList);

                Log.i("autolog", "List<User> userList = response.body();");

                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
                Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

                layoutManager = new LinearLayoutManager(MainActivity.this);
                Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
                recyclerView.setLayoutManager(layoutManager);
                Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), customerList, customerCardView);
                Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
                recyclerView.setAdapter(recyclerViewAdapter);
                Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
            }

            /* Load from local database */
            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t)
            {
                Log.d("onFailure", t.toString());

                List<Customer> customerListFromDB = retrieveFromDB();

                //Sorting the objects in the correct visit order
                Collections.sort(customerListFromDB);

                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);

                layoutManager = new LinearLayoutManager(MainActivity.this);

                recyclerView.setLayoutManager(layoutManager);

                RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), customerListFromDB, customerCardView);

                recyclerView.setAdapter(recyclerViewAdapter);

            }
        });
    }

    private void updateList(List<Customer> newCustomers)
    {
        List<Customer> oldCustomers = retrieveFromDB();

        //if old customer is not in the new list than delete
        for(Customer c : oldCustomers)
        {
            for(Customer o_c : newCustomers)
            {
                if(o_c.getIdentifier() == c.getIdentifier())
                {
                    break;
                }
            }
        }
    }

    private void insertIntoDb(List<Customer> customerList)
    {
        for(Customer c : customerList)
        {
            CustomerEntity customerEntity = new CustomerEntity(c.getIdentifier(), c.getvisitOrder(),c.getname(), c.getPhoneNumber(), c.getprofilePictures(), c.getLocation(), c.getServiceReason(), c.getProblemPictures());

            //Running insert on non main thread so it wont lock up the system
            CustomerRoomDatabase.databaseWriteExecutor.execute(() -> {
                customerDao.insert(customerEntity);
            });
        }
    }

    private List<Customer> retrieveFromDB()
    {

        List<Customer> customerList = new ArrayList<>();
        //Running insert on non main thread so it wont lock up the system
        CustomerRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<CustomerEntity> customerEList = customerDao.getAllCustomers();

            for(CustomerEntity c : customerEList)
            {
                customerList.add(new Customer(c.getIdentifier(),c.getVisitOrder(), c.getName(), c.getPhoneNumber(), c.getProfilePictures(), c.getLocation(), c.getServiceReason(), c.getProblemPictures()));
                Log.d("FromDB", c.getName());
            }
        });

        return customerList;
    }

    /* Animation to hide card view once cancel has been clicked */
    public void slideDown(CardView cardView)
    {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, cardView.getHeight());
        animation.setDuration(250);
        cardView.startAnimation(animation);
        cardView.setVisibility(View.INVISIBLE);
    }
}





