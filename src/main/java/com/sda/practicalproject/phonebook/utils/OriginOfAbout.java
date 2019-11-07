package com.sda.practicalproject.phonebook.utils;

public class OriginOfAbout {
    private static String origin;

    public static void setOrigin(String url){
        origin = url;
    }

    public static String getOrigin(){
        return origin;
    }
}
