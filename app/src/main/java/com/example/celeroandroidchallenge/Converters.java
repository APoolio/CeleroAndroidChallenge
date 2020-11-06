package com.example.celeroandroidchallenge;

import androidx.room.TypeConverter;

import com.example.celeroandroidchallenge.Models.Location;
import com.example.celeroandroidchallenge.Models.ProfilePictures;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Converters
{

    @TypeConverter
    public static Location fromLocationStrings(String values) {
        return new Gson().fromJson(values, Location.class);
    }

    @TypeConverter
    public static String LocationToString(Location loc) {
        return new Gson().toJson(loc);
    }

    @TypeConverter
    public static ProfilePictures fromStrings(String values) {
        return new Gson().fromJson(values, ProfilePictures.class);
    }

    @TypeConverter
    public static String profilePicturesToString(ProfilePictures pf) {
        return new Gson().toJson(pf);
    }

    @TypeConverter
    public static ArrayList<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<List<String>>() {}.getType());
    }

    @TypeConverter
    public static String saveList(List<String> listOfString) {
        return new Gson().toJson(listOfString);
    }
}
