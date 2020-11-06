package com.example.celeroandroidchallenge.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.celeroandroidchallenge.Models.Customer;
import com.example.celeroandroidchallenge.Models.CustomerEntity;

import java.util.List;

@Dao
public interface CustomerDao
{
    @Query("SELECT * FROM customer_table ORDER BY visitOrder ASC")
    List<CustomerEntity> getAllCustomers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CustomerEntity customerEntity);

    @Delete
    void delete(CustomerEntity customerEntity);
}
