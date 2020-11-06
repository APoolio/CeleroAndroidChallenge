package com.example.celeroandroidchallenge.Models;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Comparable<Customer>
{
    private String identifier;
    private String visitOrder;
    private String name;
    private String phoneNumber;
    private ProfilePictures profilePicture;
    private Location location;
    private String serviceReason;
    private ArrayList<String> problemPictures;

    //Default Constructor
    Customer() {}

    //Constructor for when loading from the DB
    public Customer(String identifier, String visitOrder, String name, String phoneNumber, ProfilePictures profilePicture, Location location, String serviceReason, ArrayList<String> problemPictures)
    {
        this.identifier = identifier;
        this.visitOrder = name;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.location = location;
        this.serviceReason = serviceReason;
        this.problemPictures = problemPictures;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getIdentifier() {
        return identifier;
    }

    public String getvisitOrder() {
        return visitOrder;
    }
    public String getname() {
        return name;
    }
    public ProfilePictures getprofilePictures() {
        return profilePicture;
    }
    public Location getLocation() {
        return location;
    }

    public String getServiceReason() {
        return serviceReason;
    }

    public ArrayList<String> getProblemPictures() {
        return problemPictures;
    }

    @Override
    public int compareTo(Customer customer) {
        if(getvisitOrder() == null || customer.getvisitOrder() == null)
        {
            return  0;
        }

        return getvisitOrder().compareTo(customer.getvisitOrder());
    }
}

