package com.example.celeroandroidchallenge.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "customer_table")
public class CustomerEntity
{
    //Unique key for each entity
    @NonNull
    @PrimaryKey()
    private String identifier;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private String visitOrder;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private String name;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private String phoneNumber;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private ProfilePictures profilePictures;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private Location location;

    @NonNull //Can't be null
    @ColumnInfo() //Column it is under
    private String serviceReason;

    @ColumnInfo() //Column it is under
    private ArrayList<String> problemPictures;

    //Constructor with variables
    //@Ignore because Room only expects one constructor (in case I had a default constructor)
    public CustomerEntity(String identifier, String visitOrder, String name, String phoneNumber, ProfilePictures profilePictures, Location location, String serviceReason, ArrayList<String> problemPictures)
    {
        this.identifier = identifier;
        this.visitOrder = visitOrder;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profilePictures = profilePictures;
        this.location = location;
        this.serviceReason = serviceReason;
        this.problemPictures = problemPictures;
    }

    /* GETTERS */

    public String getIdentifier() {
        return identifier;
    }

    @NonNull
    public String getVisitOrder() {
        return visitOrder;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @NonNull
    public ProfilePictures getProfilePictures() {
        return profilePictures;
    }

    @NonNull
    public Location getLocation() {
        return location;
    }

    @NonNull
    public String getServiceReason() {
        return serviceReason;
    }

    @NonNull
    public ArrayList<String> getProblemPictures() {
        return problemPictures;
    }
}
