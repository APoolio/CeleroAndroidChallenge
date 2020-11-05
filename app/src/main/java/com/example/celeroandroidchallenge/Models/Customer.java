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

class Location
{
    private Address address;
    private Cordinate cordinate;

    public Address getAddress() {
        return address;
    }

    public Cordinate getCordinate() {
        return cordinate;
    }
}

class Address
{
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}

class Cordinate
{
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

