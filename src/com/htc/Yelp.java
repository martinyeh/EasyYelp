package com.htc;

import java.util.ArrayList;
import java.util.List;

public class Yelp {

    private List<Restaurant> restaurants;
    private List<Rating> ratings;

    public Yelp(List<Restaurant> restaurants, List<Rating> ratings) {
        this.restaurants = restaurants;
        this.ratings = ratings;
    }

    public List<Restaurant> find(double latitude, double longitude, int radius,
                                 int diningHour, boolean sortByRating) {

        List<Restaurant> results = new ArrayList<Restaurant>();
        for(Restaurant r:restaurants){

            System.out.println(r.getLatitude());
            System.out.println(r.getLongitude());

            double distance = distFrom(r.getLatitude(), r.getLongitude(), latitude, longitude);
            System.out.println(distance);
            if(distance <radius){
                results.add(r);
            }

        }

        return results;
    }

    public double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;
        return (dist);
    }

    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static class Restaurant {
        private double latitude;
        private double longitude;
        private int id;
        private String name;
        private int openHour;   /* in [0-23] */
        private int closeHour;  /* in [0-23] */

        public Restaurant() {  }  /* Omitted */

        public void SetLatitude(double latitude){
            this.latitude = latitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getOpenHour() {
            return openHour;
        }

        public int getCloseHour() {
            return closeHour;
        }

        public void setLatitude(double latitude) {

            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOpenHour(int openHour) {
            this.openHour = openHour;
        }

        public void setCloseHour(int closeHour) {
            this.closeHour = closeHour;
        }


    }

    public static class Rating {
        private int id;

        public void setId(int id) {
            this.id = id;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        private int rating;      /* in [1-5] */

        public Rating() {  }      /* Omitted */
    }


    public static void main(String[] args) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        List<Rating> ratings  =  new ArrayList<Rating> ();

        Restaurant r= new Restaurant();
        r.setLatitude(-37.7);
        r.setLongitude(-122.6);
        r.setId(100);

        restaurants.add(r);

        Rating rt= new Rating();
        rt.setId(100);
        rt.setRating(3);

        ratings.add(rt);

        Yelp y = new Yelp(restaurants, ratings);
        List<Restaurant> results = y.find(-37.7, -122.6, 10, -1, false);

        System.out.println("Results:  ");
        for(Restaurant result:results){
            System.out.println(result.getId());
        }
    }
}
